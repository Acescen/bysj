<template>
  <div>
    <vue-canvas-nest style="background-color: cadetblue"></vue-canvas-nest>

    <el-container>
      <el-header style="margin-top: 5%">
        <span style="font-family: cursive ;font-size: 40px;color: darkslategrey">
          毕业设计选题管理系统
        </span><span>v1.0</span></el-header>
      <el-divider></el-divider>
      <el-main>
        <div style="padding: 2% 40%">

          <el-input
            suffix-icon="el-icon-user-solid"
            placeholder="请输入学号/工号"
            v-model="num">
          </el-input>

          <el-input style="padding-top: 10px"
                    show-password
                    placeholder="请输入密码"
                    v-model="password">
          </el-input>

          <el-row :gutter="20" style="padding-top: 15px">
            <el-col :span="12">
              <el-button type="info" round @click="registerFormVisible = true">注册</el-button>
              <div class="grid-content bg-purple"></div>
            </el-col>
            <el-col :span="12">
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
          <el-input v-model="registerUser.phoneNum"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="registerUser.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="学号/工号" prop="stuOrWorId">
          <el-input v-model="registerUser.stuOrWorId"></el-input>
        </el-form-item>

        <el-form-item label="姓名" prop="name">
          <el-input v-model="registerUser.name"></el-input>
        </el-form-item>

        <el-form-item label="角色" prop="userRole">
          <el-select v-model="registerUser.userRole" placeholder="请选择角色" style="margin-right: 270px">
            <el-option label="老师" value="1"></el-option>
            <el-option label="学生" value="2"></el-option>
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
    import vueCanvasNest from 'vue-canvas-nest'

    export default {
        name: "login",
        components: {vueCanvasNest},
        data() {
            return {
                num: '',
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

            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    // if (!Number.isInteger(this.registerUser.phoneNum)) {
                    //     this.$message({
                    //         message: '手机号非法',
                    //         type: 'warning'
                    //     });
                    //     return false;
                    // }
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
                            console.log(res);
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
                this.axios({
                    method: "post",
                    url: "/login",
                    data: {
                        username: this.num,
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

                }).catch(() => {
                    this.$message({
                        message: '非法请求！！！',
                        type: 'warning'
                    });
                })
            }
        }
    }


</script>

<style scoped>

</style>
