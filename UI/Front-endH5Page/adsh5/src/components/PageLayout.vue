<template>
    <div class="header" id="header">
        <div class="logo"></div>
        <span class="companyName">{{CompanyName}}</span>
        <p class="companyDescription">{{CompanyDescription}}</p>
    </div>
    <div class="content" >
        <div class="tabs">
        <Home id="content">
        </Home>
    </div>
  </div> 
</template>

<script lang="ts">
import Home from '@/components/Home.vue'
import { defineComponent } from 'vue';
import { ref, onMounted, nextTick, onUnmounted } from 'vue';
export default defineComponent({
  name: 'PageLayout',
  components:{
      Home
  },
  setup(){
      const CompanyName=ref<string>('天翼安全科技有限公司')
      const CompanyDescription=ref<string>('中国电信天翼安全科技有限公司是中国电信旗下集约化开展网络安全业务的服务型、科技型、安全型专业公司')
      return{
          CompanyName,
          CompanyDescription,
        }
    }
});
</script>

<style type="text/css" scoped>
.header{
    width:750px;
    height:750px;
    background: url(../assets/bj@2x.png) no-repeat;
    background-size: cover;
    position:relative;
    display:flex;
    flex-direction:column;
    align-items:center;
    justify-content:center
}
.logo{
    width:262px;
    height:44px;
    background: url(../assets/logo@2x.png) no-repeat;
    background-size: cover;
    position: absolute;
    top:74px;
    left:244px
}
.companyName{
    font-size: 58px;
    font-family: Source Han Sans CN;
    font-weight: bold;
    color: #F2F4FF;
    display:block;
    position: absolute;
    top:180px; 
}
.companyDescription{
    width: 638px;
    font-size: 24px;
    font-family: PingFang SC;
    font-weight: 400;
    color: #FFFFFF;
    /*
    */
    display: block;
    position: absolute;
    top:260px;
    height: 58px;
    line-height: 34px;
}
.content{
  width:750px;
  position:relative;
}
.tabs{
  position:absolute;
  top: -20px
}
.main{
  position:relative;
  width:750px;
}
.maininfo{
  position:absolute;
  top:820px
}
</style>
