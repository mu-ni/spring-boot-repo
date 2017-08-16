// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'element-ui/lib/theme-default/index.css'
import { Form, FormItem, Input, Button, Message, Loading } from 'element-ui'
import Axios from 'axios'

Vue.config.productionTip = false
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Button)
Vue.use(Loading)
Vue.prototype.$message = Message

Axios.defaults.headers.common['Authorization'] = 'bearer ' + localStorage.getItem("access-token");

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  data: {
    bus: new Vue(),
    axios: Axios,
    // websocket: new WebSocket("ws://" + window.location.host + "/websocket")
    websocket: new WebSocket("ws://localhost:3000/websocket")
  }
})
