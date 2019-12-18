// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import router from './router'
//导入elementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

//导入Axios
import axios from 'axios'
//可以给axios的ajax请求设置统一的主机和端口号
axios.defaults.baseURL = "http://127.0.0.1/";
//将axios这个对象添加到Vue的原型对象中，在使用的时候就只需要使用this.对象名就可以了
Vue.prototype.axios = axios;

// 跨域携带cookie
axios.defaults.withCredentials = true

Vue.config.productionTip = false

Vue.use(ElementUI);

Vue.use(VueRouter);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
