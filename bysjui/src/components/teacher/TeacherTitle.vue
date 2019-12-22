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



      <el-button @click="newTable()" icon="el-icon-refresh" circle/>
    </div>

    <el-table :data="tableData" height="500" border style="width: 100%;">
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="introduction" label="简介"/>
      <el-table-column prop="details" label="详情"/>
      <el-table-column prop="userName" label="发布老师"/>
      <el-table-column prop="phoneNum" label="联系方式"/>
      <el-table-column prop="createTime" label="发布时间" sortable/>
      <el-table-column prop="status" label="题目状态" />

      <el-table-column fixed="right" label="操作" width="100">


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
          url: "/nucTopic/list",
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
