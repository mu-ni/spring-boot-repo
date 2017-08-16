<template>
<div id="signin">
  <el-dialog title="Signin" :visible.sync="visible" @close="signinDialogCloseHandler">
    <el-form :model="signinForm" :rules="rules" ref="signinForm">
      <el-form-item label="Username" prop="username">
        <el-input v-model="signinForm.username" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input type="password" v-model="signinForm.password" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="Signin via:">
        <img class="third-party-logo" v-for="item in thirdPartyLogo" :src="'static/images/third-party/'+item.img+'.png'" @click="thirdPartyLoginHandler(item.img)"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visible = false">Cancel</el-button>
      <el-button type="primary" @click="signinHandler('signinForm')">Signin</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import { Dialog } from 'element-ui';
import Axios from 'axios'
export default {
  name: 'signin',
  components: {
    'el-dialog' : Dialog
  },
  data: () => {
    var validateUsername = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Username can\'t be empty'));
        } else {
          callback();
        }
      };
    var validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Password can\'t be empty'));
        } else {
          callback();
        }
      };

    return {
      visible: false,
      signinForm: {
        username: '',
        password: '',
      },
      rules: {
        username: [
           { validator: validateUsername, trigger: 'blur' }
        ],
        password: [
           { validator: validatePassword, trigger: 'blur' }
        ]
      },
      thirdPartyLogo: [{
        img: 'github'
      }, {
        img: 'wechat'
      }, {
        img: 'facebook'
      }, {
        img: 'weibo'
      }, {
        img: 'linkedin'
      } ]
    }
  },
  methods: {
    signinHandler(formName){
      this.$refs[formName].validate((valid) => {
          if (!valid) {
            return false;
          }else{
            Axios.post('/signin', {username: this.signinForm.username, password: this.signinForm.password})
            .then((response) => {
              if(response.data.code != 0){
                alert(response.data.status);
                return;
              }
              this.visible = false;
              this.$root.bus.$emit('signed-in', true, response.data.user);
              localStorage.setItem("access-token", response.data.accessToken);
              this.$message({
                  message: "Welcome " + response.data.user.username,
                  type: 'success'
              });
              this.$root.bus.$emit('clear-signin-form');
            })
            .catch((error) => {
                alert('Signin error! ' + error);
            });
          }
        });
    },
    thirdPartyLoginHandler(value){
      if(value != 'github'){
        alert('Only Github is supported now.');
        return;
      }
      this.$root.bus.$emit('full-screen-loading', true, 'Getting user info from ' + value);
      Axios.post('/oauth?provider=' + value)
      .then((response) => {
        if(response.data.code != 0){
          this.$root.bus.$emit('full-screen-loading', false, '');
          alert(response.data.status);
          return;
        }
        var win = window.open(response.data.status);
      })
      .catch((error) => {
          alert('OAuth error! ' + error);
          this.$root.bus.$emit('full-screen-loading', false, '');
      });
    },
    signinDialogCloseHandler: function(){
      this.$root.bus.$emit('clear-signin-form');
    }
  },
  created: function() {
    this.$root.bus.$on('clear-signin-form', () => {
      this.signinForm = {
        username: '',
        password: '',
      }
    })
    this.$root.bus.$on('signin-show', (value) => {
      this.visible = value;
    })
  }
}
</script>
<style scoped>
.third-party-logo {
  width: 35px;
  margin-right: 10px;
  cursor: pointer;
}

.third-party-logo:hover{
  opacity: 0.5;
}
</style>
