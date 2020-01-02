<template>
  <div>
    <el-row>
      <el-col :span="8"  style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
        <el-card shadow="hover" style="background-color: #80b3ff">
          团队成员及分工:
        </el-card>
        <el-card shadow="hover" style="background-color: #b3d9ff">
          琚锐: 搭建权限框架，编写前端页面
        </el-card>
        <el-card shadow="hover" style="background-color: #b3d9ff">
          贾琪: 课题功能模块的实现
        </el-card>
        <el-card shadow="hover" style="background-color: #b3d9ff">
          李慧琴: 学生功能模块的实现
        </el-card>
        <el-card shadow="hover" style="background-color: #b3d9ff">
          李亮: 老师功能模块及首页可视化数据的获取，数据库设计
        </el-card>
        <el-card shadow="hover" style="background-color: #b3d9ff">
          任冠锦: 用户功能模块的实现
        </el-card>
      </el-col>
      <el-col :span="8">
        <div id="myChart1" style="height: 400px;width:400px"></div>
      </el-col>

      <el-col :span="8">
        <div id="myChart2" style="height: 400px;width: 400px"></div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="8" >
        <el-card shadow="hover" style="background-color: #7575a3;height: 230px">
          前端技术栈:
          <el-divider/>
          基础框架: <el-link type="primary" href="https://cn.vuejs.org/" target="_blank">Vue 2.6.10</el-link>
          <br>
          UI框架: <el-link type="primary" href="https://element.eleme.cn/#/zh-CN" target="_blank">Element-ui 2.13.0</el-link>
          <br>
          通信框架: <el-link type="primary" href="http://www.axios-js.com/" target="_blank">Axios 0.19.0</el-link>
          <br>
          图表插件: <el-link type="primary" href="https://www.echartsjs.com/zh/index.html" target="_blank">Echarts 4.5.0</el-link>
          <br>
          其他
        </el-card>

      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" style="background-color: #4dffa6;height: 230px">
          后端技术栈:
          <el-divider/>
          基础框架: <el-link type="primary" href="https://spring.io/projects/spring-boot" target="_blank">Spring boot 2.2.2.RELEASE</el-link>
          <br>
          持久层框架: <el-link type="primary" href="https://mp.baomidou.com/" target="_blank">Mybatis.plus 3.1.1</el-link>
          <br>
          安全框架: <el-link type="primary" href="http://shiro.apache.org/" target="_blank">Apache Shiro 1.4.0</el-link>
          <br>
          数据库: <el-link type="primary" href="https://www.mysql.com/" target="_blank">Mysql 5.7 </el-link>
          <br>
          其他
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card shadow="hover" style="background-color: #00e5e6;height: 230px">
          开发环境及项目管理:
          <el-divider/>
          语言: <el-link type="primary" href="https://www.java.com/zh_CN/" target="_blank">java8</el-link>
          <br>
          IDE: <el-link type="primary" href="https://www.jetbrains.com/idea/" target="_blank">IntelliJ IDEA 2019.2.2 (Ultimate Edition)</el-link>
          And<el-link type="primary" href="https://www.jetbrains.com/webstorm/" target="_blank">WebStorm 2019.2.4</el-link>
          <br>
          依赖管理: <el-link type="primary" href="https://maven.apache.org/" target="_blank">Apache Maven 3.6.1</el-link>
          And
          <el-link type="primary" href="https://www.npmjs.com/" target="_blank">Npm 6.12.1</el-link>
          <br>
          GitHub地址: <el-link type="primary" href="https://github.com/Acescen/bysj" target="_blank">毕业设计选题管理系统</el-link>
          <br>
          在线体验: <el-link type="primary" href="http://www.jurui.online" target="_blank">www.jurui.online</el-link>
          <br>
        </el-card>

      </el-col>
    </el-row>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        selectTopic: '123',
        noSelTopic: '12',
        selectStudent: '32',
        noSelStudent: '323'
      }
    },
    mounted() {
      this.queryData();
    },
    methods: {
      queryData() {
        this.axios({
          method: "post",
          url: "/indexData",
          data: {

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
            this.selectTopic=res.data.selectTopic;
            this.noSelTopic=res.data.noSelTopic;
            this.selectStudent=res.data.selectStudent;
            this.noSelStudent=res.data.noSelStudent;
            this.drawLine();
          }
        }).catch(() => {
          this.$message({
            message: '非法请求！！！',
            type: 'warning'
          });
        })
      },
      drawLine() {
        let myChart1 = this.$echarts.init(document.getElementById('myChart1'));
        let option1 = {
          title: {
            text: '题目选择情况表',
            subtext: '动态数据',
            x: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: ['未选题目', '已选题目']
          },
          series: [
            {
              name: '题目数量',
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: [
                {value: this.noSelTopic, name: '未选题目'},
                {value: this.selectTopic, name: '已选题目'}
              ],
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        myChart1.setOption(option1);

        let myChart2 = this.$echarts.init(document.getElementById('myChart2'));
        let option2 = {
          title: {
            text: '学生选题情况表',
            subtext: '动态数据',
            x: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: ['已选题学生', '未选题学生']
          },
          series: [
            {
              name: '人数',
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: [
                {value: this.selectStudent, name: '已选题学生'},
                {value: this.noSelStudent, name: '未选题学生'}
              ],
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        myChart2.setOption(option2);
      }
    }
  }
</script>

<style scoped>

</style>
<!--npm run dev-->
