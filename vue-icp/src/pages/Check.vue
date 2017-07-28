<template>
    <div id="check">
        <shared-header></shared-header>
        <img-container class="col-lg-8 col-md-8 col-sm-8 col-xs-12"></img-container>
        <decision-box class="col-lg-4 col-md-4 col-sm-4 col-xs-12" :action="action" :imgid="imgid"></decision-box>
    </div>
</template>
<script>
import Header from '../components/shared/Header';
import ImgContainer from '../components/ImgContainer';
import DecisionBox from '../components/DecisionBox';
export default {
  name: 'check',
  components: {
    'shared-header': Header,
    'img-container': ImgContainer,
    'decision-box': DecisionBox
    },
    data: function () {
        var path = this.$route.path;
        var action = path.split('/')[1];
        return {
            action: action,
            imgid: this.$route.params.imgid
        }
    }
}
</script>
