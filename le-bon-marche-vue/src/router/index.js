import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Main from '@/pages/Main'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/main'
    },
    {
        path: '/main',
        name: 'main',
        component: Main
    }
  ]
})
