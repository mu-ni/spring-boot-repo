<template>
<div id="app">
  <shared-header></shared-header>
  <router-view></router-view>
</div>
</template>

<script>
import Header from './components/Header';
export default {
  name: 'app',
  components: {
      'shared-header': Header
  },
  created: function() {
    if (typeof(Storage) !== "undefined") {
    } else {
        alert('Not support local/session storage')
    }
    if ('WebSocket' in window) {} else {
        alert('Not support websocket')
    }
    this.$root.websocket.onopen = (event)=>{
        this.$root.websocket.send("Client side websocket: open");
    }
    this.$root.websocket.onclose = ()=>{
        this.$root.websocket.send("Client side websocket: close");
    }
    this.$root.websocket.onerror = ()=>{
        this.$root.websocket.send("Client side websocket: error");
        alert('websocket error!');
    }
    this.$root.websocket.onmessage = (event)=>{
      this.$root.websocket.send("Client side websocket: Message received");
      var ws = JSON.parse(event.data);
      if(ws.event === 'oauth'){
        this.$root.websocket.send("Websocket event = " + ws.event);
        this.$root.bus.$emit('full-screen-loading', true, "Loading...");
        this.$root.websocket.send("Info provider: " + ws.user.source);

        this.$root.bus.$emit('signin-show', false);
        this.$root.bus.$emit('visit-method-show', true, ws.user);
        this.$root.websocket.send("Visit method dialog is visible");
        this.$root.bus.$emit('full-screen-loading', false, '');
      }else(
        alert('Websocket error! Event = ' + ws.event)
      )
    }
  }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
}
</style>
