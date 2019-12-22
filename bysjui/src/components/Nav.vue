<template>
    <div class="nav">

      <el-row>
        <el-col :span="19">
          <el-menu
            class="el-menu-demo"
            mode="horizontal"
            background-color="#004c99"
            text-color="#f2f2f2"
            active-text-color="#001933">

            <el-menu-item>导航栏</el-menu-item>
            <el-menu-item v-for="item in menus" v-bind:key="item.name">
              <router-link v-bind:to="item.path">{{ item.title }}</router-link>
            </el-menu-item>

          </el-menu>
          </el-col>
        <el-col :span="5">
          <el-menu
            class="el-menu-demo"
            mode="horizontal"
            background-color="#004c99"
            text-color="#f2f2f2">

            <el-menu-item >欢迎你: {{name}}</el-menu-item>
            <el-menu-item @click="logout">退出</el-menu-item>

          </el-menu>
        </el-col>
      </el-row>


    </div>
</template>

<script>
import routes from '../router/routes'
import store from '../store'

export default {
  data(){
    return{
      name: store.state.user.name
    }
  },
    computed: {
        menus() {
            const ms = routes.filter(item => item.meta && !item.meta.hideInMenu).map(item => ({
                title: item.meta.title,
                path: item.path,
                name: item.name,
                permissions: item.meta.permissions,
            }))
            // eslint-disable-next-line no-console
            console.log(ms, this.$store.state.permissions)
            return ms.filter(item => item.permissions.some(ite => this.$store.state.permissions.some(it => it === ite)));
        }
    },
    methods: {
        logout: function() {
            this.$store.commit('clearUserPermission')
            this.$router.push('/login')
        }
    }
}
</script>

<style>

</style>
