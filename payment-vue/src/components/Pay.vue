<template>
  <div id="pay">
    <p>Pay</p>
    <el-button type="primary" @click="pay">去支付</el-button>
  </div>
</template>
<script>
import Axios from 'axios'
export default {
  name: 'pay',
  methods: {
      pay: function(){
          Axios.post('/alipay')
            .then((response) => {
                document.body.innerHTML = response.data.form;
                document.forms[0].submit();
            })
            .catch((error) => {
                alert('Oppps! ' + error);
            });
      }
  }
}
</script>
<style scoped>
</style>
