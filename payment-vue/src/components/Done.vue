<template>
<div id="done">
  <p>Payment Result</p>
  <p id="message">Please wait...</p>
</div>
</template>
<script>
var websocket = null;
if ('WebSocket' in window) {
  websocket = new WebSocket("ws://localhost:3000/websocket");
} else {
  alert('Not support websocket')
}

// websocket.onopen = function(event){
//     document.getElementById('message').innerHTML += 'open<br/>';
// }

websocket.onmessage = function(event){
    document.getElementById('message').innerHTML = event.data;
}

// websocket.onclose = function(){
//     document.getElementById('message').innerHTML += 'close<br/>';
// }
//
// websocket.onerror = function(){
//     document.getElementById('message').innerHTML += 'error<br/>';
// }

window.onbeforeunload = function(){
    websocket.close();
}

export default {
  name: 'done'
}
</script>
<style scoped>
</style>
