<template>
  <div class="page-background">
    <div class="logo">
      <div class='telecom-logo'></div>
      <div class="vertical-line"></div>
      <div class='security-logo'></div>
    </div>
    <div class="content">
      <div class='pictuer'>
      </div>
      <div class="log-form">
        <div class="title">天翼安全产品管理平台</div>
        <div class="code">
        <input type="text" name="managerName"  v-model="managerName" placeholder="请输入用户名" >
        </div>
        <div class="code">
        <input type="password" name="code"  v-model="managerCode" placeholder="请输入密码" >
        </div>
        <div class="code">
          <input class="inputCode" type="text" name="securityCode"  v-model="securityCode" placeholder="请输入验证码" >
          <img class="getCode" :src="codesrc"  @click="freshCode"/>
        </div>
          
          
        <button class="register" @click="logIn">登录</button>
      </div>
    </div>
    <el-dialog
          v-model="dialogVisible"
          title="提示"
          width="30%"
          :show-close="false"
          :close-on-click-modal="false"
          :close-on-press-escape="false"
          :modal="true"
          :append-to-body="true"
        >
        <span style="font-size:18px;">{{message}}</span>
        <template #footer>
          <span class="dialog-footer" style="display:flex,justify-content:center">
            <el-button type="primary" @click="goHome"
              >确认</el-button>
          </span>
        </template>
      </el-dialog>
    
  </div>
</template>

<script lang="ts">
import { defineComponent, ref ,toRefs, reactive,toRaw} from 'vue';
import { login } from '@/api'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'Home',
  components: {
  },
  setup(){
    const managerName=ref<string>('')
    const managerCode=ref<string>('')
    const securityCode=ref<string>('')
    const baseCodeUrl:string=window.location.origin+'/service-core/rest'+'/userInfo/getVerifyCode'
    const codesrc=ref<string>(baseCodeUrl)
    const params=reactive({
      'id':1,
      'userName':'',
      'password':'',
      //'securityCode':'',
    })
    const router = useRouter();

    const dialogVisible = ref<boolean>(false)
    const message=ref<string>('')
    const resultcode=ref<number|string>()

    const logIn=():void=>{
      params.userName=managerName.value
      params.password=managerCode.value
      //params.securityCode=securityCode.value
      login(securityCode.value,params).then(response=>{
            
            const res:any=response.data
            resultcode.value=res.code
            message.value=res.message
       // console.log(res.code,res.message) 
            if(res.code=='200'){
              //console.log(res.code,res.message) 
              router.push({
                name:'home',
              });
            }else{
              dialogVisible.value=true
              //alert('网络异常，请稍后重试')
            }
          }).catch((err) => console.log(err));
    }
    const goHome=():void=>{
       dialogVisible.value=false
        //console.log('22222')
           //getpro()   
      //console.log('666666666666',paramsValue)
    }
    const freshCode=():void=>{
      codesrc.value=baseCodeUrl+"?time="+new Date().getTime()
      //console.log(codesrc.value)
    }
    

    return {
      managerName,
      managerCode,
      securityCode,
      params,
      logIn,
      dialogVisible,
      goHome,
      message,
      codesrc,
      freshCode
    }
  }
});
</script>
<style type="text/css" scoped>

.page-background{
  width:100%;
	height: 100%;
	background:#4f7bff;
  position:absolute;
}
.logo{
  width:20%;
  height:10%;
  display:flex;
  flex-direction: row;
  vertical-align:center;
  position:absolute;
}
.pictuer{
  width:50%;
	height: 60%;
  display:inline-block;
  background: url(../assets/picture@2x.png) no-repeat;
  background-size: cover;
  position:relative;
  top:20%;
  left:5%
}
.telecom-logo{
  width:48%;
	height:40%;
  background: url(../assets/telecom-logo.png) no-repeat;
  background-size: contain;
  display:inline-block;
  margin-left:5%;
  position:relative;
  top:50%;
  transform:translateY(-50%);
  margin-right:4%;
}

.security-logo{
  width:60%;
	height:70%;
  background: url(../assets/security-logo.png) no-repeat;
  background-size: contain;
  display:inline-block; 
  margin-left:4%;
  position:relative;
  top:50%;
  transform:translateY(-50%)
}
.vertical-line{
  height:36%;
  border-right:2px solid white;
  position:relative;
  top:50%;
  transform:translateY(-50%)
}
.content{
  width:100%;
  height:100%;
  display:flex;
  flex-direction:row;
}
.log-form{
  width:30%;
	height:56%;
  background:white;
  display:inline-block;
  position:relative;
  top:24%;
  left:10%;
  text-align: center;
}
.title{
  width:100%;
  font-size:4vh;
  color:#0D47E7;
  margin-top:7%;
  margin-bottom:5%;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: bold;
}
 input{
  width: 90%;
  height: 100%;
  border-radius: 4px;
  border: 1px solid #BBBBBB;
  font-size: 3vh;
  font-family: PingFangSC-Regular, PingFang SC;
  color: #333333;
  padding-left:20px;
  -webkit-appearance: none!important;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  
}
input:focus{
  outline: none;
  border: 1px solid #FFFFFF;
  box-shadow: 0 0 0 1.5px #0D47E7;
}.
.code-area{
  display:flex;
  position:relative;
}

.code{
    margin-left:5%;
  margin-right:5%;
  margin-bottom:5%;
  width: 80%;
  height: 10%;
  border-radius: 4px;
  padding-left:20px;
  -webkit-appearance: none!important;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  display:flex;
  justify-content: space-between;
  
 
}
.inputCode{ 
  width: 50%;
  height: 100%;
  border-radius: 4px;
  border: 1px solid #BBBBBB;
  font-size: 3vh;
  font-family: PingFangSC-Regular, PingFang SC;
  color: #333333;
  padding-left:20px;
  -webkit-appearance: none!important;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
.getCode{
  display:inline-block;
  width: 40%;
  height: 100%;
  border: 1px solid #BBBBBB;
}
.register{
  width: 84%;
  height: 10%;
  font-size: 3vh;
  background:#3466ff;
  font-family: PingFangSC-Regular, PingFang SC;
  border:none;
  border-radius: 8px;
  color: #FFFFFF;
}
</style>

