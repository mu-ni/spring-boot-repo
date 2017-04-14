<template>
    <div id="signin">
        <label for="username" class="">Username</label>
        <input id="username" name="username" class="form-control" placeholder="Username" required = "true" autofocus = "true"></input>
        <label for="password" class="">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required = "true"></input>
        <router-link to="/main">Signin</router-link>
    </div>
</template>

<script>
export default {
  name: 'signin'
}
</script>
<style scoped>
</style>
