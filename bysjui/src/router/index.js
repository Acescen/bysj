import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './routes'
import store from '../store'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'hash',
  routes
})

router.beforeEach(function (to, from, next) {
  if (to.meta.requiresAuth) {
    if (!store.state.isLogin) next('/login')
    else {
      const beAllowed = to.meta.permissions.some(item => store.state.permissions.some(it => it === item));
      // const beAllowed = true;
      if (beAllowed) next()
      else next('/403')
    }
  }
  else next()
})

export default router
