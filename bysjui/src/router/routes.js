import Login from '../components/Login'
import Index from '../components/index'
import Forbidden from '../components/403'
import User from '../components/admin/User'
import Title from '../components/Title'
import AdminTitle from "../components/admin/AdminTitle";
import MyReleaseTitle from "../components/teacher/MyReleaseTitle";
import MySelTitle from "../components/student/MySelTitle";
import StudentTitle from "../components/student/StudentTitle";
import TeacherTitle from "../components/teacher/TeacherTitle";


export default [
  {
    name: 'login',
    path: '/login',
    component: Login,
    meta: {
      title: '用户登录',
      hideInMenu: true,
      requiresAuth: false,
      permissions: []
    }
  },
  {
    name: 'forbidden',
    path: '/403',
    component: Forbidden,
    meta: {
      title: '403页面',
      hideInMenu: true,
      requiresAuth: false,
      permissions: []
    }
  },
  {
    name: 'index',
    path: '/index',
    component: Index,
    meta: {
      title: '首页',
      requiresAuth: true,
      permissions: ['index']
    }
  },
  {
    name: 'User',
    path: '/User',
    component: User,
    meta: {
      title: '用户管理',
      requiresAuth: true,
      permissions: ['admin']
    }
  },
  {
    name: 'AdminTitle',
    path: '/AdminTitle',
    component: AdminTitle,
    meta: {
      title: '题目管理',
      requiresAuth: true,
      permissions: ['admin']
    }
  },
  {
    name: 'TeacherTitle',
    path: '/TeacherTitle',
    component: TeacherTitle,
    meta: {
      title: '题目列表',
      requiresAuth: true,
      permissions: ['teacher']
    }
  },
  {
    name: 'MyReleaseTitle',
    path: '/MyReleaseTitle',
    component: MyReleaseTitle,
    meta: {
      title: '我的发布',
      requiresAuth: true,
      permissions: ['teacher']
    }
  },
  {
    name: 'StudentTitle',
    path: '/StudentTitle',
    component: StudentTitle,
    meta: {
      title: '题目列表',
      requiresAuth: true,
      permissions: ['student']
    }
  },
  {
    name: 'MySelTitle',
    path: '/MySelTitle',
    component: MySelTitle,
    meta: {
      title: '已选题目',
      requiresAuth: true,
      permissions: ['student']
    }
  }
]
