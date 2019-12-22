<template>
  <div>
    <div>
      模糊查询：
      <el-input
        placeholder="支持所有条件模糊查询"
        v-model="condition"
        class="input-with-select"
        style="width: 280px;padding-right: 900px;"
      >
        <el-button @click="query()" type="primary" icon="el-icon-search" circle slot="append"/>
      </el-input>

      <el-button @click="adduser()" icon="el-icon-plus" circle/>
      <el-button @click="newTable()" icon="el-icon-refresh" circle/>
    </div>

    <el-table :data="tableData" height="500" border style="width: 100%;">
      <el-table-column prop="title" label="标题" sortable/>
      <el-table-column prop="introduction" label="简介"/>
      <el-table-column prop="details" label="详情"/>
      <el-table-column prop="userName" label="发布老师"/>
      <el-table-column prop="phoneNum" label="联系方式"/>
      <el-table-column prop="createTime" label="发布时间" sortable/>
      <el-table-column prop="status" label="题目状态" sortable/>

      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="update(scope.row)" type="text" size="small">编辑</el-button>
          <el-button
            @click="deleteById(scope.row.userId)"
            type="text"
            size="small"
            style="color: red"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="10"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalnum"
    />

    <el-dialog title="更改用户信息" :visible.sync="dialogVisible" width="40%">
      <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm" :rules="rules">
        <el-form-item label="学号/工号" prop="stuOrWorId">
          <el-input v-model="ruleForm.stuOrWorId" :disabled="true"/>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password" placeholder="不输入默认使用原密码"/>
        </el-form-item>

        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name"/>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNum">
          <el-input v-model="ruleForm.phoneNum"/>
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="ruleForm.note"/>
        </el-form-item>

        <el-form-item label="角色" prop="userRole">
          <el-radio-group v-model="ruleForm.userRole">
            <el-radio label="管理员">管理员</el-radio>
            <el-radio label="老师">老师</el-radio>
            <el-radio label="学生">学生</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="是否可用" prop="status">
          <el-radio-group v-model="ruleForm.status">
            <el-radio label="可用">可用</el-radio>
            <el-radio label="锁定">锁定</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="ruleForm.createTime" :disabled="true"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateinfo">立即更新</el-button>
          <el-button @click="closeDia" style="margin-left: 30%">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="添加用户" :visible.sync="dialogVisibleadd" width="40%">
      <el-form :model="ruleFormadd" ref="ruleFormadd" label-width="100px" class="demo-ruleForm" :rules="rules">
        <el-form-item label="学号/工号" prop="stuOrWorId">
          <el-input v-model="ruleFormadd.stuOrWorId"/>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleFormadd.password"/>
        </el-form-item>

        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleFormadd.name"/>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNum">
          <el-input v-model="ruleFormadd.phoneNum"/>
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="ruleFormadd.note"/>
        </el-form-item>

        <el-form-item label="角色" prop="userRole">
          <el-radio-group v-model="ruleFormadd.userRole">
            <el-radio label="管理员">管理员</el-radio>
            <el-radio label="老师">老师</el-radio>
            <el-radio label="学生">学生</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="是否可用" prop="status">
          <el-radio-group v-model="ruleFormadd.status">
            <el-radio label="可用">可用</el-radio>
            <el-radio label="锁定">锁定</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="adduserto">立即添加</el-button>
          <el-button @click="resetForm('ruleFormadd')" style="margin-left: 30%">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        dialogVisible: false,
        dialogVisibleadd: false,
        condition: "",
        pageSize: 10,
        pageNum: 1,
        tableData: [],
        totalnum: 1,
        ruleForm: {
          stuOrWorId: "",
          phoneNum: "",
          password: "",
          name: "",
          userRole: "",
          status: "",
          note: "",
          createTime: ""
        },
        ruleFormadd: {
          stuOrWorId: "",
          phoneNum: "",
          password: "",
          name: "",
          userRole: "",
          status: "",
          note: "",
          createTime: ""
        },
        rules: {
          stuOrWorId: [
            {required: true, message: "学号/工号不能为空", trigger: "blur"}
          ],
          phoneNum: [
            {required: true, message: "手机号不能为空", trigger: "blur"}
          ],
          password: [
            {required: true, message: "密码不能为空", trigger: "blur"}
          ],
          name: [
            {required: true, message: "姓名不能为空", trigger: "blur"}

          ],
          userRole: [
            {required: true, message: "必须选择角色", trigger: "blur"}

          ],
          status: [
            {required: true, message: "请选择账号状态", trigger: "blur"}

          ]
        }
      };
    },
    name: "list",
    methods: {
      adduserto() {

        this.$refs['ruleFormadd'].validate((valid) => {
          if (valid) {
            this.axios({
              method: "post",
              url: "/nucUser/regist",
              data: this.ruleFormadd,
              transformRequest: [
                function (data) {
                  let ret = "";
                  for (let it in data) {
                    ret +=
                      encodeURIComponent(it) +
                      "=" +
                      encodeURIComponent(data[it]) +
                      "&";
                  }
                  return ret;
                }
              ],
              //设置请求头
              headers: {
                "Content-Type": "application/x-www-form-urlencoded"
              }
            })
              .then(res => {
                if (res.data.code === 200) {
                  this.closeDiaadd('ruleformadd');
                  this.dialogVisibleadd = false;
                  this.newTable();
                  this.$message({
                    message: "添加成功",
                    type: "success"
                  });
                } else {
                  this.$message({
                    message: res.data.message,
                    type: "warning"
                  });
                }

              })
              .catch(() => {
                this.$message({
                  message: "非法请求！！！",
                  type: "warning"
                });
              });
          } else {
            return false;
          }
        });
      },

      adduser() {
        this.resetForm("ruleFormadd");
        this.dialogVisibleadd = true;
      },
      deleteById(id) {
        this.axios({
          method: "post",
          url: "/nucUser/delet",
          data: {
            id: id
          },
          transformRequest: [
            function (data) {
              let ret = "";
              for (let it in data) {
                ret +=
                  encodeURIComponent(it) +
                  "=" +
                  encodeURIComponent(data[it]) +
                  "&";
              }
              return ret;
            }
          ],
          //设置请求头
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          }
        })
          .then(res => {
            if (res.data.code == 200) {
              this.newTable();
              this.$message({
                message: "删除成功",
                type: "success"
              });
            } else {
              this.$message({
                message: "删除失败，请联系管理员",
                type: "warning"
              });
            }
          })
          .catch(() => {
            this.$message({
              message: "删除失败，请联系管理员",
              type: "warning"
            });
          });
      },
      closeDia() {
        this.resetForm("ruleForm");
        this.dialogVisible = false;
      },
      closeDiaadd() {
        this.resetForm("ruleFormadd");
        this.dialogVisibleadd = false;
      },

      updateinfo() {
        this.ruleForm.createTime = "";

        this.axios({
          method: "post",
          url: "/nucUser/update",
          data: this.ruleForm,
          transformRequest: [
            function (data) {
              let ret = "";
              for (let it in data) {
                ret +=
                  encodeURIComponent(it) +
                  "=" +
                  encodeURIComponent(data[it]) +
                  "&";
              }
              return ret;
            }
          ],
          //设置请求头
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          }
        })
          .then(res => {
            if (res.data.code === 200) {
              this.resetForm("ruleForm");
              this.newTable();
              this.$message({
                message: "更新成功",
                type: "success"
              });
            } else {
              this.$message({
                message: "更新失败，请联系管理员",
                type: "warning"
              });
            }
            this.closeDia();
          })
          .catch(() => {
            this.$message({
              message: "非法请求！！！",
              type: "warning"
            });

            this.closeDia();
          });
      },


      update(res) {
        this.ruleForm.stuOrWorId = res.stuOrWorId;
        this.ruleForm.createTime = res.createTime;
        this.ruleForm.name = res.name;
        this.ruleForm.note = res.note;
        this.ruleForm.phoneNum = res.phoneNum;
        this.ruleForm.status = res.status;
        this.ruleForm.userId = res.userId;
        this.ruleForm.userRole = res.userRole;
        this.dialogVisible = true;
      },
      newTable() {
        this.getData(this.pageSize, this.pageNum, '');
      },
      query() {
        this.getData(this.pageSize, this.pageNum, this.condition);
      },
      handleSizeChange(val) {
        this.pageSize = val;
        this.getData(this.pageSize, this.pageNum, this.condition);
      },

      handleCurrentChange(val) {
        this.pageNum = val;
        this.getData(this.pageSize, this.pageNum, this.condition);
      },

      resetForm(formName) {
        if (this.$refs[formName] !== undefined) {
          this.$refs[formName].resetFields();
        }
      },

      getData(size, num, condition) {
        //axios-post请求传值
        this.axios({
          method: "post",
          url: "/nucUser/list",
          data: {
            any: condition,
            pageSize: size,
            pageNum: num
          },
          transformRequest: [
            function (data) {
              let ret = "";
              for (let it in data) {
                ret +=
                  encodeURIComponent(it) +
                  "=" +
                  encodeURIComponent(data[it]) +
                  "&";
              }
              return ret;
            }
          ],
          //设置请求头
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          }
        })
          .then(r => {
            this.totalnum = r.data.total;
            this.tableData = r.data.data;
          })
          .catch(err =>
            this.$message({
              showClose: true,
              message: err,
              type: "error"
            })
          );
      }
    },
    mounted() {
      //模板或el对应的html渲染完成后再调用里面的方法

      this.getData(this.pageSize, this.pageNum,'');
    }
  };
</script>

<style scoped>
</style>
