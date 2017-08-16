<template>
<div id="header">
  <header>
    <img id="logo" src="../assets/lbm.png" @click="backToHome">
    <a id="title">Le Bon Marché</a>
    <a class="link signin" @click="signinClick" v-show="!signedIn" v-cloak>Signin</a>
    <a class="link signup" @click="signupClick" v-show="!signedIn" v-cloak>Signup</a>
    <el-dropdown trigger="hover" v-show="signedIn" @command="handleCommand">
      <span class="el-dropdown-link link">{{username}}</span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="profile">Profile</el-dropdown-item>
        <el-dropdown-item command="order" v-if="role == 'buyer'">My Order</el-dropdown-item>
        <el-dropdown-item command="product" v-if="role == 'seller'">My Product</el-dropdown-item>
        <el-dropdown-item command="chatroom">Chat Room</el-dropdown-item>
        <el-dropdown-item command="signout" divided>Signout</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </header>
</div>
</template>

<script>
import { Dropdown, DropdownMenu, DropdownItem } from 'element-ui';
export default {
  name: 'header',
  components: {
    'el-dropdown': Dropdown,
    'el-dropdown-menu': DropdownMenu,
    'el-dropdown-item': DropdownItem
  },
  data: ()=>{
      return {
        signedIn: false,
        username: '',
        role: ''
      }
  },
  methods: {
      backToHome(){
          this.$router.push('/main');
      },
      signinClick(){
          this.$root.bus.$emit('signin-show', true);
      },
      signupClick(){
          this.$root.bus.$emit('signup-show', true);
      },
      handleCommand(command) {
        if(command === 'signout'){
          this.signedIn = false;
          this.$router.push('/main');
          this.$message({
              message: "Signed out",
              type: 'success'
          });
          localStorage.clear();
          return;
        }
        this.$router.push('/' + command);
      }
  },
  created: function() {
    this.$root.bus.$on('signed-in', (value, user) => {
      this.signedIn = value;
      this.username = user.username;
      this.role = user.role
    })
  }
}
</script>
<style>
[v-cloak] {
  display: none;
}
header {
  width: 100%;
  height: 50px;
  background-color: #000;
  overflow: hidden;
}

#logo {
  float: left;
  height: 50px;
}

a {
  color: #fff;
  text-decoration: none;
  line-height: 50px;
}

#title {
  font-size: 24px;
  position: absolute;
  left: 0;
  right: 0;
  margin-left: auto;
  margin-right: auto;
  pointer-events: none;
}

.link {
  float: right;
  margin-right: 20px;
  cursor: pointer;
  color: #fff;
  font-size: 16px;
}

.link:hover {
  color: gray;
}

.el-dropdown{
  float: right;
  margin-right: 20px;
  line-height: 50px;
}
</style>
