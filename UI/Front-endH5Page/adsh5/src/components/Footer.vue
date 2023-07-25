<template>
  <div class="footer">
    <div class="icons">
       <div class="icon1">
       <span class="des">7*24小时</span>
       </div>
       <div class="icon2">
       <span class="des">专业团队</span>
       </div>
       <div class="icon3">
       <span class="des">运营商骨干网络</span>
       </div>
    </div>
    <div class="line"></div>
    <span class="copyright">京ICP备14062412号-1Copyright © 云堤 版权所有</span>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  name: 'Footer',
});
</script>

<style type="text/css" scoped>
.footer{
    width: 750px;
    height: 150px;
    background: #1A1A1A;
    position:absolute;
    /*bottom:-260px*/
    bottom:0px;
}
.erweima{
    width:144px;
    height:144px;
    background: url(../assets/QR@2x.png) no-repeat;
    background-size: cover;
    position:absolute;
    right:65px;
    top:40px
}
.icons{ 
    width:750px;
    font-size: 24px;
    font-family: Alibaba PuHuiTi;
    font-weight: 400;
    color: #999999;
    display:flex;
    justify-content: center;
    margin:0 auto;
    margin-top:25px;
}
.icon1,
.icon2,
.icon3{
    width:28px;
    height:28px;
    margin-right:170px;
    display:inline-block;
    position:relative;
    margin-bottom:28px
}
.icon1{
    margin-left:-50px;
    background: url(../assets/tm@2x.png) no-repeat;
    background-size: contain ; 
}
.icon2{
    background: url(../assets/td@2x.png) no-repeat;
    background-size: contain ; 
}
.icon3{
    margin-right:150px;
    background: url(../assets/CC@2x.png) no-repeat;
    background-size: contain ; 
}
.line{
    width: 750px;
    height: 1px;
    border-top: 1px solid #313131;
    position: absolute;
    bottom: 60px; 
}
.des{
    position:absolute;
    left:40px;
    min-width:200px;
    text-align:left;
}
.copyright{
    height: 20px;
    font-size: 20px;
    font-family: PingFang SC;
    font-weight: 500;
    color: #777777;
    line-height: 30px;
    display:inline-block;
    margin-top:20px
}
</style>
