import Vue from 'vue'
import Router from 'vue-router'
import Signin from 'pages/Signin'
import Main from 'pages/Main'
import Check from 'pages/Check'

Vue.use(Router)

export default new Router({
  routes: [
      {
          path: '/',
          redirect: '/signin'
      },
      {
          path: '/signin',
          component: Signin
      },
      {
          path: '/main',
          component: Main
      },
      {
          path: '/review/:imgid',
          component: Check
      },
      {
          path: '/check/:imgid',
          component: Check
      }
  ]
})
