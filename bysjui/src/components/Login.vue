<template>
  <div ref="root" style="
    background-size: cover;
    height: 100%;
    width: 100%;
    ">
<!--    <vue-canvas-nest/>-->

    <el-container>
      <el-header style="margin-top: 3%">
        <span style="font-family: cursive ;font-size: 40px;color: darkslategrey;padding-left: 35%">
          中北大学学生管理系统
        </span><span>v1.0</span></el-header>
      <el-divider/>
      <el-main>
        <div style="padding: 2% 40%">
          <el-form label-width="0px" class="ms-content">
            <el-form-item prop="">
              <el-input v-model="stuOrWorId" placeholder="请输入学号或工号">
                <el-button slot="prepend" icon="el-icon-user-solid"/>
              </el-input>
            </el-form-item>

            <el-form-item prop="">
              <el-input
                type="password"
                placeholder="请输入密码"
                v-model="password"
                @keyup.enter.native="login"
              >
                <el-button slot="prepend" icon="el-icon-key"/>
              </el-input>
            </el-form-item>
            <p class="login-tips">Tips: 管理员账号(z z)<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp教师账号(x x)<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp学生账号(c c)</p>
          </el-form>

          <el-row :gutter="20" style="padding-top: 15px;padding-left: 15px">
            <el-col :span="12">
              <el-button type="info" round @click="registerFormVisible = true">注册</el-button>
              <div class="grid-content bg-purple"></div>
            </el-col>
            <el-col :span="12" style="padding-left: 45px">
              <el-button type="success" round @click="loginup()">登录</el-button>
              <div class="grid-content bg-purple"></div>
            </el-col>
          </el-row>

        </div>
      </el-main>
    </el-container>


    <el-dialog title="注册账号" :visible.sync="registerFormVisible" width="40%">
      <el-form :model="registerUser" :rules="rules" ref="registerUser" label-width="100px" class="demo-ruleForm">

        <el-form-item label="手机号" prop="phoneNum">
          <el-input v-model="registerUser.phoneNum"/>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="registerUser.password" show-password/>
        </el-form-item>
        <el-form-item label="学号/工号" prop="stuOrWorId">
          <el-input v-model="registerUser.stuOrWorId"/>
        </el-form-item>

        <el-form-item label="姓名" prop="name">
          <el-input v-model="registerUser.name"/>
        </el-form-item>

        <el-form-item label="角色" prop="userRole">
          <el-select v-model="registerUser.userRole" placeholder="请选择角色" style="margin-right: 270px">
            <el-option label="老师" value="老师"/>
            <el-option label="学生" value="学生"/>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button @click="resetForm('registerUser')" style="margin-right: 30%">重置</el-button>
          <el-button type="primary" @click="submitForm('registerUser')" style="margin-left: 20%">立即注册</el-button>
        </el-form-item>

      </el-form>

    </el-dialog>

  </div>
</template>

<script>
  import request from '../request';

  // import vueCanvasNest from 'vue-canvas-nest'
  import CanvasNest from 'canvas-nest.js';

  const config = { // 配置
    color: '153, 153, 153', // 线条颜色
    pointColor: '255, 155, 0', // 节点颜色
    opacity: 2, // 线条透明度
    count: 130, // 线条数量
    zIndex: 1 // 画面层级
  };


  export default {
    created() {
      this.$nextTick(() => {
        let root=this.$refs['root'];
        const cn = new CanvasNest(root, config);
      })
    },
    name: "login",
    // components: {vueCanvasNest},
    data() {
      return {
        stuOrWorId: '',
        password: '',
        registerFormVisible: false,
        registerUser: {
          phoneNum: '',
          password: '',
          stuOrWorId: '',
          name: '',
          userRole: ''
        },
        rules: {
          phoneNum: [
            {required: true, message: '请输入手机号', trigger: 'blur'},
            {min: 11, max: 11, message: '手机号非法', trigger: 'change'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 5, max: 15, message: '密码长度最小5位，最大15位', trigger: 'change'}
          ],
          stuOrWorId: [
            {required: true, message: '请输入学号或者工号', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'}
          ],
          userRole: [
            {required: true, message: '请选择角色', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {

      setPermission: async function (curruser) {
        let permissions = [];
        //admin
        if (curruser.userRole === '管理员') {
          permissions = await request('/static/admin.json');
        } else if (curruser.userRole === '老师') {

          permissions = await request('/static/teacher.json');
        } else if (curruser.userRole === '学生') {

          permissions = await request('/static/student.json');
        } else {
        }

        this.$store.commit('setUserPermission', permissions);
        this.$store.commit('setUser', curruser);
        await this.$router.push('/index')
      },

      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            //提交表单
            this.axios({
              method: "post",
              url: "/nucUser/regist",
              data: this.registerUser,
              transformRequest: [
                function (data) {
                  let ret = '';
                  for (let it in data) {
                    ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&';
                  }
                  return ret;
                }
              ],
              //设置请求头
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            }).then((res) => {
              if (res.data.code === 200) {
                this.$message('注册成功！');
                this.resetForm('registerUser');
                this.registerFormVisible = false;
              } else if (res.data.message === "已注册") {
                this.$message({
                  showClose: true,
                  message: '该学号/工号的用户已经注册',
                  type: 'error'
                });
              } else {
                this.$message({
                  showClose: true,
                  message: '注册失败，请联系网站管理员',
                  type: 'error'
                });
              }
            }).catch((res) => {
              this.$message({
                showClose: true,
                message: '系统异常，请联系网站管理员',
                type: 'error'
              });
            })
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      loginup() {
        if (this.stuOrWorId !== '' && this.password !== '') {
          this.axios({
            method: "post",
            url: "/login",
            data: {
              stuOrWorId: this.stuOrWorId,
              password: this.password
            },
            transformRequest: [
              function (data) {
                let ret = '';
                for (let it in data) {
                  ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&';
                }
                return ret;
              }
            ],
            //设置请求头
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          }).then((res) => {
            if (res.data.code === 200) {
              this.setPermission(res.data.user);

            } else {
              this.$message.error(res.data.message);
            }
          }).catch(() => {
            this.$message({
              message: '非法请求！！！',
              type: 'warning'
            });
          });
        } else {
          this.$message.error('请输入学号密码！');

          return false;
        }

      }
    }
  }


</script>

<style scoped>

</style>
