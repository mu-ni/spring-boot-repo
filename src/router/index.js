import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Pay from '@/components/Pay'
import Done from '@/components/Done'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
  },
  {
    path: '/pay',
    name: 'pay',
    component: Pay
  },
  {
    path: '/done',
    name: 'done',
    component: Done
  }
  ]
})
