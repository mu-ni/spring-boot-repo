<template>
    <div id="header">
        <a id="back" type="button" class="btn btn-primary btn-xs" @click="backClicked">Back</a>
        <router-link id="logout" to="/signin" type="button" class="btn btn-danger btn-xs">Signout</router-link>
    </div>
</template>
<script>
export default {
  name: 'header',
  methods: {
      backClicked : function (event) {
          this.$router.go(-1);
      }
  }
}
</script>
<style scoped>
#header{
    overflow: hidden;
    padding: 10px;
}
#back{
    float: left;
}
#logout{
    float: right;
}
</style>
