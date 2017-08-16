<template>
<div id="signup">
  <el-dialog title="Signup" :visible.sync="visible" size="large" @close="signupDialogCloseHandler">
    <el-form :model="signupForm" :rules="rules" ref="signupForm">
      <el-form-item label="Username" prop="username">
        <el-input v-model="signupForm.username"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input type="password" v-model="signupForm.password"></el-input>
      </el-form-item>
      <el-form-item label="Repeat Password" prop="password2">
        <el-input type="password" v-model="signupForm.password2"></el-input>
      </el-form-item>
      <el-form-item label="Email" prop="email">
        <el-input v-model="signupForm.email"></el-input>
      </el-form-item>
      <el-form-item label="Location" prop="location">
        <el-input v-model="signupForm.location"></el-input>
      </el-form-item>
      <el-form-item label="Role" prop="role">
        <el-radio class="radio" v-model="signupForm.role" label="buyer">Buyer</el-radio>
        <el-radio class="radio" v-model="signupForm.role" label="seller">Seller</el-radio>
      </el-form-item>
      <el-form-item label="Signup source" prop="source" style="display:none">
        <el-input v-model="signupForm.source" :disabled="true"></el-input>
      </el-form-item>
      <!-- <el-form-item label="Location" prop="location">
        <el-cascader :options="locationData" v-model="signupForm.location"></el-cascader>
      </el-form-item> -->
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visible = false">Cancel</el-button>
      <el-button type="primary" @click="signupHandler('signupForm')">Signup</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import { Dialog, Cascader, Radio } from 'element-ui';
import Axios from 'axios'
export default {
  name: 'signup',
  props: ['content'],
  components: {
    'el-dialog': Dialog,
    'el-cascader': Cascader,
    'el-radio': Radio
  },
  data() {
    var validateUsername = (rule, value, callback) => {
        if (!value) {
          callback(new Error('Please input your username'));
        } else {
          Axios.post('/username', {username: this.signupForm.username})
          .then((response) => {
            if(response.data.code != 0){
              callback(new Error(response.data.status));
            }else{
              callback();
            }
          })
          .catch((error) => {
              alert('Username check error! ' + error);
              callback();
          });
        }
      };
    var validatePassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('Please input your password'));
        } else {
          if (this.signupForm.password2 !== '') {
            this.$refs.signupForm.validateField('password2');
          }
          callback();
        }
      };
    var validatePassword2 = (rule, value, callback) => {
        if (!value) {
          callback(new Error('Please repeat your password'));
        } else if (value !== this.signupForm.password) {
          callback(new Error('Inconsistent password'));
        } else {
          callback();
        }
      };
    var validateEmail = (rule, value, callback) => {
        if (!value) {
          callback(new Error('Please input your email'));
        } else {
          callback();
        }
      };
    var validateLocation = (rule, value, callback) => {
        if (!value) {
          callback(new Error('Please input your location'));
        } else {
          callback();
        }
      };
    var validateRole = (rule, value, callback) => {
        if (!value) {
          callback(new Error('Please select a role'));
        } else {
          callback();
        }
      };

    return {
      visible: false,
      signupForm: {
        username: '',
        password: '',
        password2: '',
        email: '',
        location: '',
        role: 'buyer',
        source: 'local'
      },
      rules: {
        username: [
           { validator: validateUsername, trigger: 'blur' }
        ],
        password: [
           { validator: validatePassword, trigger: 'blur' }
        ],
        password2: [
           { validator: validatePassword2, trigger: 'blur' }
        ],
        email: [
           { validator: validateEmail, trigger: 'blur' },
           { type: 'email', message: 'Invalid email', trigger: 'blur' }
        ],
        location: [
           { validator: validateLocation, trigger: 'blur' }
        ],
        role: [
           { validator: validateRole, trigger: 'change' }
        ]
      }
      // locationData: [
      //   {
      //     value: 'france',
      //     label: 'France',
      //     children: [{value: 'paris', label: 'Paris'},{value: 'lyon', label: 'Lyon'},{value: 'marseille', label: 'Marseille'}]
      //   },
      //   {
      //     value: 'china',
      //     label: 'China',
      //     children: [{value: 'beijing', label: 'Beijing'},{value: 'canton', label: 'Canton'}]
      //   }
      // ]
    }
  },
  methods: {
    signupHandler(formName){
      var userData = {
        username: this.signupForm.username,
        password: this.signupForm.password,
        email: this.signupForm.email,
        location: this.signupForm.location,
        role: this.signupForm.role,
        source: this.signupForm.source
      }

      this.$refs[formName].validate((valid) => {
          if (!valid) {
            return false;
          } else {
            Axios.post('/signup', userData)
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
              this.$root.bus.$emit('clear-signup-form');
            })
            .catch((error) => {
                alert('Signup error! ' + error);
            });
          }
        });
    },
    signupDialogCloseHandler: function(){
      this.$root.bus.$emit('clear-signup-form');
    }
  },
  created: function() {
    this.$root.bus.$on('clear-signup-form', () => {
      this.signupForm = {
        username: '',
        password: '',
        password2: '',
        email: '',
        location: '',
        role: 'buyer',
        source: 'local'
      }
    })
    this.$root.bus.$on('signup-show', (value) => {
      this.visible = value;
    })
    this.$root.bus.$on('auto-fill-signup-form', (user) => {
      this.signupForm = user;
    })
  }
}
</script>
<style>
.radio{
  float: left;
}
#signup .el-form-item__content {
    display: inline-block;
    float: right;
    width: 70%;
}
#signup .el-cascader {
    width: 100%;
}
</style>
