<template>
  <div>

    <el-table :data="tableData" height="500" border style="width: 100%;">
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="introduction" label="简介"/>
      <el-table-column prop="details" label="详情"/>
      <el-table-column prop="userName" label="发布老师"/>
      <el-table-column prop="phoneNum" label="联系方式"/>
      <el-table-column prop="createTime" label="发布时间" sortable/>
      <el-table-column prop="status" label="题目状态" />

      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
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

  </div>
</template>

<script>
  export default {
    data() {
      return {
        condition: "",
        pageSize: 10,
        pageNum: 1,
        tableData: [],
        totalnum: 1,
      };
    },
    methods: {
      deleteById() {

        this.$alert('确定删除已选课程么', '提示', {
          confirmButtonText: '确定',
          callback: action => {

            this.axios({
              method: "post",
              url: "/student/delete",
              data: {

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
                if (res.data.code === 200) {
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

          }});


      },

      newTable() {
        this.getData(this.pageSize, this.pageNum, '');
      },


      getData(size, num, condition) {
        //axios-post请求传值
        this.axios({
          method: "post",
          url: "/student/list",
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
