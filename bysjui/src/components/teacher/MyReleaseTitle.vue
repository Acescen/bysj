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

      <el-button @click="addTopic()" icon="el-icon-plus" circle/>
      <el-button @click="newTable()" icon="el-icon-refresh" circle/>
    </div>

    <el-table :data="tableData" height="500" border style="width: 100%;">
      <el-table-column prop="title" label="标题"/>
      <el-table-column prop="introduction" label="简介"/>
      <el-table-column prop="details" label="详情"/>
      <el-table-column prop="userName" label="发布老师"/>
      <el-table-column prop="phoneNum" label="联系方式"/>
      <el-table-column prop="createTime" label="发布时间" sortable/>
      <el-table-column prop="status" label="题目状态"/>

      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="update(scope.row)" type="text" size="small">编辑</el-button>
          <el-button
            @click="deleteById(scope.row.topicId)"
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


    <el-dialog title="更改题目信息" :visible.sync="dialogVisible" width="40%">
      <el-form :model="topicForm" ref="topicForm" label-width="100px" class="demo-ruleForm" >

        <el-form-item label="标题" prop="title">
          <el-input v-model="topicForm.title" />
        </el-form-item>

        <el-form-item label="简介" prop="introduction">
          <el-input v-model="topicForm.introduction" />
        </el-form-item>

        <el-form-item label="详情" prop="details">
          <el-input type="textarea" v-model="topicForm.details" :autosize="{ minRows: 7, maxRows: 7}"/>
        </el-form-item>


        <el-form-item label="我的联系方式" prop="phoneNum">
          <el-input v-model="topicForm.phoneNum"/>
        </el-form-item>

        <el-form-item label="创建时间" prop="createTime">
          {{topicForm.createTime}}
        </el-form-item>

        <el-form-item label="题目状态" prop="status">
          {{topicForm.status}}
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="updateinfo">立即更新</el-button>
          <el-button @click="closeDia" style="margin-left: 30%">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="添加题目" :visible.sync="dialogVisibleadd" width="40%">
      <el-form :model="topicAddForm" ref="topicAddForm" label-width="100px" class="demo-ruleForm" >

        <el-form-item label="标题" prop="title">
          <el-input v-model="topicAddForm.title" />
        </el-form-item>

        <el-form-item label="简介" prop="introduction">
          <el-input v-model="topicAddForm.introduction" />
        </el-form-item>

        <el-form-item label="详情" prop="details">
          <el-input type="textarea" v-model="topicAddForm.details" :autosize="{ minRows: 7, maxRows: 7}"/>
        </el-form-item>


        <el-form-item>
          <el-button type="primary" @click="addTopicto">立即添加</el-button>
          <el-button @click="closeDiaadd" style="margin-left: 30%">取消</el-button>
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
        topicForm: {
          topicId: '',
          title: '',
          introduction: '',
          details: '',
          phoneNum: '',
          createTime: '',
          status: '',
          userId: '',
          userName: '',
        },
        topicAddForm: {
          title: '',
          introduction: '',
          details: '',
        }
      };
    },
    methods: {

      addTopicto() {

        this.$refs['topicAddForm'].validate((valid) => {
          if (valid) {
            this.axios({
              method: "post",
              url: "/nucTopic/add",
              data: this.topicAddForm,
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
                  this.closeDiaadd();
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


      addTopic() {
        this.topicAddForm.title = '';
        this.topicAddForm.introduction = '';
        this.topicAddForm.details = '';

        this.dialogVisibleadd = true;
      },

      update(res) {
        this.topicForm.topicId = res.topicId;
        this.topicForm.title = res.title;
        this.topicForm.introduction = res.introduction;
        this.topicForm.details = res.details;
        this.topicForm.phoneNum = res.phoneNum;
        this.topicForm.createTime = res.createTime;
        this.topicForm.status = res.status;
        this.topicForm.userId = res.userId;
        this.topicForm.userName = res.userName;

        this.dialogVisible = true;
      },

      updateinfo() {
        this.topicForm.createTime = '';

        this.axios({
          method: "post",
          url: "/nucTopic/update",
          data: this.topicForm,
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

      deleteById(id) {
        this.axios({
          method: "post",
          url: "/nucTopic/delete",
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


      getData(size, num, condition) {
        //axios-post请求传值
        this.axios({
          method: "post",
          url: "/teacher/list",
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
      },

      closeDia() {
        this.topicForm.topicId = '';
        this.topicForm.title = '';
        this.topicForm.introduction = '';
        this.topicForm.details = '';
        this.topicForm.phoneNum = '';
        this.topicForm.createTime = '';
        this.topicForm.status = '';
        this.topicForm.userId = '';
        this.topicForm.userName = '';

        this.dialogVisible = false;
      },
      closeDiaadd() {
        this.topicAddForm.title = '';
        this.topicAddForm.introduction = '';
        this.topicAddForm.details = '';

        this.dialogVisibleadd = false;
      },
    },
    mounted() {
      //模板或el对应的html渲染完成后再调用里面的方法

      this.getData(this.pageSize, this.pageNum, '');
    }
  };
</script>

<style scoped>
</style>
