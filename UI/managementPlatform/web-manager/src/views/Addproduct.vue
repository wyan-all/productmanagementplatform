<template>
  <div class="page">
    <div class="topbar"><p class="headline">新增产品页面</p></div>
    <div class="content">
    
          <Editortable class="editor-table" @isfinished="isactive"
          :iconId="iconId"
          />
    </div>
    <div class="bar">
      <button :class="complete?'active':'disabled'" @click="submitForm">
      提交
      </button>
      <button class="cancel" @click="cancel">取消</button>
      <el-dialog
        v-model="dialogVisible"
        title="提示"
        width="30%"
        :show-close="false"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
      >
        <span style="font-size:18px;">{{message}}</span>
        <template #footer>
          <span class="dialog-footer">
            
            <el-button type="primary" @click="goHome"
              >确认</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
    
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { ref } from 'vue';
import Editortable from '@/components/Editortable.vue'
import { useRoute, useRouter } from 'vue-router';
import {addProduct} from '@/api'
export default defineComponent({
  name: '',
  components:{
      Editortable
  },
  setup(props,context){
    const router = useRouter();
    const route = useRoute();
    const complete=ref<boolean>(false)
    var paramsValue=ref({})
    const dialogVisible = ref<boolean>(false)
    const message=ref<string>('')
    const iconId:number=Number(route.params.productId)+1

    //console.log('443324444434444',iconId)

    const isactive=(context:any,params:any):void=>{
      // console.log('55555555',context.value)
      //console.log('6666666',params)
      paramsValue=params
      //console.log('777777777',paramsValue)
      complete.value=context.value
    }

    const resultcode=ref<number|string>()
      const submitForm=():void=>{
        if(complete.value){
            dialogVisible.value=true
            //console.log('333333',dialogVisible.value)
            addProduct(paramsValue).then(response=>{
            const res:any=response.data
            resultcode.value=res.code
            message.value=res.message
            //console.log(4444444444444,message.value)
          }).catch((err) => console.log(err));
        }
    }

    const goHome=():void=>{
            router.push({
            name:'home'})     
      //console.log('666666666666',paramsValue)
    }

    const cancel=():void=>{
      router.push({
                name:'home',
        });
    }


    

    return {
      isactive,
      complete,
      submitForm,
      cancel,
      dialogVisible,
      goHome,
      message,
      iconId
      // uploadImage,
      //     freshImage,
      //     headTip,
      //     imagesrc 
    }
  }
});
</script>

<style type="text/css" scoped>
.page{
  padding:0 20px;
}
.topbar{
  width:100%;
  height:40px;
  display:flex;
  margin-top:16px;
}
.headline{
  font-size:22px;
  margin-left:4px;
}
.content{
  width:98%;
  box-sizing:border-box;
  display:flex;
  margin-left:10px;
  background:#ffffff;
  display:inline-block;
  padding:10px 10px;
}
.editor-table{
    padding:20px 0;
}
.bar{
  display:flex;
  margin:20px 0;
}
button{
  display:block;
  border:none;
  border-radius: 8px;
  margin:0 auto;
  margin-bottom:40px;
  font-size: 26px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #FFFFFF;
}
 
.active{
  width:180px;
  height:40px;
  background:blue;
  background: #0D47E7; 
}
.disabled{
  width:180px;
  height:40px;
  color: #333333;
  background: #E5E5E5;
}
.cancel{
  width:180px;
  height:40px;
  color: #333333;
  background: #E5E5E5;
}
.dialog-footer{
  display:flex;
}


.head-img{
    text-align:center;
    height:160px;
}
.border{
    display: inline-block;
    width: 120px;
    height: 120px;
    border: 1px solid #c3c3c3;
    overflow: hidden;
    line-height: 5rem;
    text-align: center;
    position: relative;
}
.imgDiv{
    width: 100%;
    height: 100%;
    position: absolute;
    left:0;
    top:0;
    z-index:200
}
</style>
