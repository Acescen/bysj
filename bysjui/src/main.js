import Vue from 'vue'
import Vuex from 'vuex'
import store from './store'
import router from './router'
import App from './App.vue'
//导入elementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//导入Axios
import axios from 'axios'


Vue.config.productionTip = false
Vue.use(Vuex);


//可以给axios的ajax请求设置统一的主机和端口号
axios.defaults.baseURL = "http://127.0.0.1/";
//将axios这个对象添加到Vue的原型对象中，在使用的时候就只需要使用this.对象名就可以了
Vue.prototype.axios = axios;

// 跨域携带cookie
axios.defaults.withCredentials = true


Vue.use(ElementUI);

new Vue({
  store,
  el: '#app',
  router,
  render: h => h(App)
}).$mount('#app')
