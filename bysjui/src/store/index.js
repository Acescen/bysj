import Vue from 'vue';
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: [],
    isLogin: false,
    permissions: [],
  },
  mutations: {
    setUser(state,curruser) {
      state.user = curruser;
    },
    setUserPermission(state, permissions) {
      state.isLogin = true;
      state.permissions = permissions;
    },
    clearUserPermission(state) {
      state.isLogin = false;
      state.permissions = [];
    }
  },
})
