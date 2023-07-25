<template>
  <div>
  <div class="topbar">
    <div class="icon"></div>
    <div class="headline">申请试用</div>
  </div>
   <div class="formContent">
  <div  name="content" id="formId" @keyup ="KeyDown($event)"  >
   <label>试用产品名称:</label>
   <input type="text" name="productName"  v-model="productName"  readonly>

   <label><em class="asterisk">*</em> 企业名称：</label>
   <input type="text" name="qiyeName"  v-model="company" placeholder="请输入真实企业名称" >
   <label><em class="asterisk">*</em> 真实姓名：</label>
   <input type="text" name="name"  v-model="name" placeholder="请输入真实姓名便于联系">
   <label><em class="asterisk">*</em> 电话号码：</label>
   <input type="tel" name="tel"   v-model="telephone" placeholder="请输入真实电话号码方便联系">
   <label class="tishi" v-show="istel?false:true"> 号码输入有误</label>

   <label><em class="asterisk">*</em> 邮箱：</label>
   <input type="email" name="email" v-model="email" placeholder="请输入真实邮箱地址">
   <!--
   <label><em class="asterisk">*</em> 验证码：</label>
   <div class="auth">
     <input type="text" class="authInput" v-model="securityCode" placeholder="请输入验证码">
     <div class="authCode">{{authCode}}</div>
     <div class="changeAuth" @click="changeAuth">换一张</div>
   </div>
   <label class="tishi" v-show="trueCode?false:true"> 验证码输入有误</label>
   -->
   
   
  </div>
  <button  @click="submitForm" :class="complete ? 'bar' : 'invalid'">提交</button>
   
  <button class="disbar" @click="cancel">取消</button>
   
  </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { ref, reactive, toRefs, toRaw, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { sendemail } from '@/api/request'

interface IProductInfo{
  [index: string ]:string;
}
export default defineComponent({
  name: 'CustomForm',
  setup(){
      const route = useRoute();
      const router = useRouter();
      const istel=ref<boolean>(true)
      const complete=ref<boolean>(false)
      const count=ref<number>(1)
      const information=reactive<IProductInfo>({})
      const company=ref<string>('')
      const name=ref<string>('')
      const telephone =ref<string>('')
      const email=ref<string>('')
      const productName=ref<string | string[]>(route.params.productName)
      const appMessage=ref<string>('')
      const code=ref<string>('')
      const securityCode=ref<string>('')
      const authCode=ref<string>('') //从后台获取的验证码
      const trueCode=ref<boolean>(true)
      //初始化验证码
      // getSecurityCode().then(response=>{
      //       const res:any=response.data
      //       if(res.code){
      //         code.value=res.code
      //         authCode.value=res.result.securityCode
      //         console.log(res.result.securityCode)
      //       }else{
      //         alert('网络异常，请稍后重试')
      //       }
      //     }).catch((err) => console.log(err)); 
     //改变验证码
        // const changeAuth=():void=>{
        //   getSecurityCode().then(response=>{
        //     const res:any=response.data
        //     if(res.code==='200'){
        //       code.value=res.code
        //       authCode.value=res.result.securityCode
        //       console.log('验证码获取成功')
        //     }else{
        //       alert('网络异常，请稍后重试')
        //     }
        //   }).catch((err) => console.log(err)); 
        // }

      watch(telephone,(newVlaue:any,oldVlaue:any)=>{
        istel.value=/^[1][3,4,5,7,8][0-9]{9}$/.test(telephone.value);
      })

      // watch(securityCode,(newVlaue:any,oldVlaue:any)=>{
      //   trueCode.value= securityCode.value==authCode.value?true:false;
      //   console.log(trueCode.value)
      // })

      const KeyDown=():void=>{
        count.value=1
        const form:HTMLElement| null=document.getElementById("formId");
        const tagEles:HTMLCollectionOf<HTMLInputElement>=document.getElementsByTagName('input');
        for(var j=0;j<tagEles.length;j++){
          if(tagEles[j].value) {
            count.value+=1
          }
          information[tagEles[j].name]=tagEles[j].value
        }
       // istel.value=/^[1][3,4,5,7,8][0-9]{9}$/.test(telephone.value);
        //trueCode.value=securityCode.value == authCode.value?true:false
        complete.value=false
        if(count.value===6 && istel.value ){
          complete.value=true
        }
      }
      const submitForm=():void=>{
        if(complete.value){
          //把数据提交给后台
          sendemail(toRaw(information)).then(response=>{
            const res:any=response.data
            if(res.code){
              //console.log(1111111111)
              code.value=res.code
              appMessage.value=res.result.appMessage
              router.push({
                name:'MessageFeedback',
                params:{
                  appMessage:appMessage.value,
                  code:code.value
                }
              });
              console.log(res.result.appMessage)
            }else{
              alert('网络异常，请稍后重试')
            }
          }).catch((err) => console.log(err)); 
        }
      }

      const cancel=():void=>{
        history.back();
      }
      
      return {
          productName,
          istel,
          telephone,
          information,
          complete,
          KeyDown,
          submitForm,
          sendemail,
          cancel,
          appMessage,
          code,
          securityCode,
          authCode,
          //changeAuth,
          trueCode
      }
  }
});
</script>

<style type="text/css" scoped>
.topbar{
  box-sizing:border-box;
  display:flex;
  width:750px;
  height:88px;
  padding-left:24px;
  padding-right:24px;
  padding-top:12px;
  background: #FFFFFF;
  margin-bottom:44px;
  box-shadow: 0px 10px 30px 0px rgba(0, 0, 0, 0.08);

}
.icon{
  width:262px;
  height:44px;
  background: url(../assets/biaozhi@2x.png) no-repeat;
  background-size: contain;
}
.headline{
  margin-left:14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
  font-size: 38px;
  line-height: 53px;
}
.formContent{
  display:flex;
  flex-direction:column;
  text-align:left;
  background: #FFFFFF;
}
 input{
  display:block;
  margin-left:26px;
  margin-right:26px;
  margin-bottom:50px;
  width: 680px;
  height: 80px;
  border-radius: 4px;
  border: 1px solid #BBBBBB;
  font-size: 28px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 40px;
  padding-left:20px;
  -webkit-appearance: none!important;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
input:focus{
  outline: none;
  border: 1px solid #FFFFFF;
  box-shadow: 0 0 0 1px #0D47E7;
}
.auth{
  display:flex;
  position:relative;
}
.authInput{
  width: 240px;
  margin-right:10px;
}
.authCode{
  display:block;
  margin-bottom:50px;
  width: 280px;
  height: 80px;
  border-radius: 4px;
  border: 1px solid #BBBBBB;
  font-size: 48px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 40px;
  padding-left:20px;
  -webkit-appearance: none!important;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
.changeAuth{
  font-size: 28px;
  height: 80px;
  font-family: PingFangSC-Regular, PingFang SC;
  line-height: 40px;
  font-weight: 400;
  color: #0D47E7;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  left:620px
}


label{
  display:inline-block;
  margin-left:24px;
  margin-right:24px;
  text-align:left;
  font-size: 28px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 37px;
  margin-bottom:15px;
}
.asterisk{
  color:red;
}

button{
  display:block;
  width: 702px;
  height: 98px;
  border:none;
  border-radius: 8px;
  margin:0 auto;
  margin-bottom:20px;
  font-size: 36px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 50px;
  color: #FFFFFF;
}
.invalid{
  background: #6B8CE6;
}
.bar{
  background: #0D47E7;
}
.disbar{
  color: #333333;
  background: #E5E5E5;
}
.tishi{
  display:block;
  font-size: 24px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #D0021B;
  line-height: 33px;
  position:relative;
  top:-40px;
  margin-bottom:-36px;
}
</style>
