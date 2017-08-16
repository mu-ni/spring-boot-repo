<template>
<div id="main" v-loading.fullscreen.lock="fullscreenLoading" :element-loading-text="loadingText">
  <!-- <main-carousel :content="carouselData"></main-carousel> -->
  <signin-dialog></signin-dialog>
  <signup-dialog></signup-dialog>
  <visit-method></visit-method>
</div>
</template>
<script>
import Carousel from '../components/Carousel';
import Signin from '../components/Signin';
import Signup from '../components/Signup';
import VisitMethod from '../components/VisitMethod';
export default {
  name: 'main',
  components: {
    'main-carousel': Carousel,
    'signin-dialog': Signin,
    'signup-dialog': Signup,
    'visit-method': VisitMethod
  },
  data: () => {
    return {
      fullscreenLoading: false,
      loadingText: 'Loading...',
      carouselData: [{
        img: 'cpb'
      }, {
        img: 'skii'
      }, {
        img: 'givenchy'
      }, {
        img: 'shiseido'
      } ]
    }
  },
  created: function() {
    if(localStorage.getItem("access-token")){
      this.$root.axios.post('/accesstoken')
      .then((response) => {
        if(response.data.code != 0){
          alert(response.data.status);
          return;
        }
        this.$root.bus.$emit('signed-in', true, response.data.user);
      })
      .catch((error) => {
          alert('Post /accesstoken error! ' + error);
      });
    }else{
      //without token
    }
    this.$root.bus.$on('full-screen-loading', (value, text) => {
      this.fullscreenLoading = value;
      this.loadingText = text;
    })
  }
}
</script>
<style scoped>

</style>
