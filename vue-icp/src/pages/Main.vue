<template>
    <div id="main">
        <shared-header></shared-header>
        <review-list class="col-lg-6 col-md-6 col-sm-6 col-xs-12" :content="reviewData" :action="reviewAction"></review-list>
        <check-list class="col-lg-6 col-md-6 col-sm-6 col-xs-12" :content="checkData" :action="checkAction"></check-list>
    </div>
</template>
<script>
  import Header from '../components/shared/Header';
  import DataTb from '../components/DataTb';
  import Axios from 'axios'
  export default {
    name: 'main',
    data: function () {
      return {
        reviewAction: 'review',
        checkAction: 'check',
        reviewData: '',
        checkData: ''
      }
    },
    components: {
      'shared-header': Header,
      'review-list': DataTb,
      'check-list': DataTb
    },
    created: function () {
        Axios.get('/initData')
        .then((response) => {
            this.reviewData = response.data['reviewList'];
            this.checkData = response.data['checkList'];
        })
        .catch((error) => {
            alert('Init data error! ' + error);
        });
    }

  }
</script>
<style scoped>

</style>
