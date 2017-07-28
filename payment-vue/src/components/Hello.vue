<!-- <template>
  <div class="hello">
    <input id="text" type="text"/>
    <button @click="send">send</button>
    <button @click="close">close</button>
    <p id="message"></p>
  </div>
</template>

<script type="text/javascript">
    var websocket = null;
    if ('WebSocket' in window){
        websocket = new WebSocket("ws://localhost:3000/websocket");
    } else {
        alert('Not support websocket')
    }

    websocket.onopen = function(event){
        setMessageInnerHTML("open");
    }

    websocket.onmessage = function(event){
        setMessageInnerHTML(event.data);
    }

    websocket.onclose = function(){
        setMessageInnerHTML("close");
    }

    websocket.onerror = function(){
        setMessageInnerHTML("error");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    }

    function setMessageInnerHTML(innerHTML){
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    export default {
        methods: {
            send: function (event) {
                var message = document.getElementById('text').value;
                websocket.send(message);
            },
            close: function (event) {
                websocket.close();
            }
        }
    }
</script> -->

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--style scoped>

</style-->
