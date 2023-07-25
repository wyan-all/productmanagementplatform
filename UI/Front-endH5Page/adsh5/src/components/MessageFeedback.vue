<template>
  <div class="all">
     <div class="box">
         <div :class="code==='200'?'sucess-icon':'fail-icon'"></div>
         <span class="message">{{message}}</span>
         <p class="appMessage">{{appMessage}}</p>
     </div>
     <button @click="back">我知道了</button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

export default defineComponent({
  name: 'MessageFeedback',
  setup(){
      const route = useRoute();
      const router=useRouter()
      const appMessage=ref<string | string[]>(route.params.appMessage)
      const code=ref<string | string[]>(route.params.code)
      const message=ref<string>('')
      if(code.value==='200'){
          message.value='操作成功'
      }else{
          message.value='操作失败'
      }
      //console.log(appMessage.value)
      //console.log('gggggggggggg',code.value)
      const back=():void=>{
          router.push({ name: 'home'})
        }
        return{
            back,
            appMessage,
            code,
            message
        }
  }
});
</script>

<style type="text/css" scoped>
.all{
    width: 750px;
    height: 100vh;
    background: #F5F5F5;
}
.box{
    width: 750px;
    background: #FFFFFF;
    display:flex;
    flex-direction:column;
    padding-bottom:64px;
}
.sucess-icon{
    width: 128px;
    height: 128px;
    background: url(../assets/shape@2x.png) no-repeat;
    background-size: cover;
    margin:0 auto;
    margin-top:76px;
}
.fail-icon{
    width: 128px;
    height: 128px;
    background: url(../assets/error.png) no-repeat;
    background-size: cover;
    margin:0 auto;
    margin-top:76px;
}
.message{
    margin-top:48px;
    font-size: 36px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #333333;
    line-height: 50px;
    margin-bottom:16px;
}
p{
    display:inline-block;
    font-size: 26px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
    margin:0 80px;
}
button{
    box-sizing:border-box;
    font-size: 36px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #FFFFFF;
    line-height: 50px;
    width: 702px;
    margin:0 auto;
    margin-top:40px;
    height: 98px;
    background: #0D47E7;
    border-radius: 8px;
    border:none;
}
</style>
