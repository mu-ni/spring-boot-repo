<template>
<div id="visitMethod">
  <el-dialog :title="'Hello '+ user.username + '! Now you can:'" :visible.sync="visible">
    <div class="dialog-body">
      <el-button type="success" @click="visitorHandler">Visit as a visitor</el-button>
      <el-button type="primary" style="margin:0" @click="signupHandler">Set password for signup</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import { Dialog } from 'element-ui';
import Axios from 'axios'
export default {
  name: 'visit-method',
  components: {
    'el-dialog' : Dialog
  },
  data: ()=>{
    return {
      visible: false,
      user: {
        username: '',
        email: '',
        location: '',
        role: ''
      }

    }
  },
  methods: {
    visitorHandler: function(){
      this.visible = false;
      // this.user.role = 'visitor';
      Axios.post('/visitor')
      .then((response) => {
        if(response.data.code != 0){
          alert(response.data.status);
          return;
        }
        this.$root.bus.$emit('signed-in', true, response.data.user);
        localStorage.setItem("access-token", response.data.accessToken);
        this.$message({
            message: "Welcome " + response.data.user.username,
            type: 'success'
        });
      })
      .catch((error) => {
          alert('Post /visitor error! ' + error);
      });
    },
    signupHandler: function(){
      this.visible = false;
      this.user.role = 'buyer';
      this.$root.bus.$emit('signup-show', true);
      this.$root.bus.$emit('auto-fill-signup-form', this.user);
    }
  },
  created: function() {
    this.$root.bus.$on('visit-method-show', (value, user) => {
      this.visible = value;
      this.user = user;
    })
  }
}
</script>
<style scoped>
.dialog-body{
  text-align: -webkit-center
}
button{
  display: block;
  width: 80%;
  margin-bottom: 20px;
}
</style>
