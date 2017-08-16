<template>
  <div id="carousel">
    <el-carousel :interval="4000" trigger="click" height="300px">
      <el-carousel-item v-for="item in content" :key="item.img">
        <img :src="'static/images/carousel/'+item.img+'.png'" height="100%"/>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
import { Carousel, CarouselItem } from 'element-ui';
export default {
  name: 'carousel',
  props: ['content'],
  components: {
    'el-carousel': Carousel,
    'el-carousel-item': CarouselItem
  }
}
</script>
<style>
.el-carousel{
    width: 100%;
}
.el-carousel__item{
    background: #f7f7f7;
}
  /*.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
    background-repeat: no-repeat;
  }*/

  /*.el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }*/
</style>
