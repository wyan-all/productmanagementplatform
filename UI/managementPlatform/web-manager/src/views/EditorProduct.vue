<template>
  <div class="page">
    <div class="topbar"><p class="headline">产品编辑页面</p></div>
    <div class="content">
          <Editortable class="editor-table" @isfinished="isactive" :productInfo="productInfo"/>
    </div>
    <div class="bar">
      <button :class="complete?'active':'disabled'" @click="submitForm">提交</button>
      <button class="cancel" @click="cancel">取消</button>
    </div>
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
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { ref,toRaw,reactive } from 'vue';
import Editortable from '@/components/Editortable.vue'
import { useRoute, useRouter } from 'vue-router';
import { editorProduct } from '@/api';
import { compareObject } from '@/utils/compareObject'
export default defineComponent({
  name: '',
  components:{
      Editortable
  },
  methods:{
     
 },
  setup(props,context){
    const router = useRouter();
    const complete=ref<boolean>(false)
    var paramsValue=ref({})

    const route = useRoute();
    const productInfo=JSON.parse(route.params.productInfo as string)
    //console.log('222222222',productInfo.id)
    const copyproductInfo=JSON.parse(JSON.stringify(productInfo));
    const dialogVisible = ref<boolean>(false)
    const message=ref<string>('')
    const resultcode=ref<number|string>()

    const isactive=(context:any,params:any):void=>{
      // console.log('55555555',context.value)
      // console.log('6666666',params)
      paramsValue=params
      complete.value=context.value
      const copyParams=JSON.parse(JSON.stringify(paramsValue))
     
      copyParams.styleId=copyproductInfo.styleId
      copyParams.id=copyproductInfo.id
      
      if(productInfo.styleId!==JSON.parse(JSON.stringify(paramsValue)).styleId && compareObject(copyproductInfo,copyParams)){
        complete.value=true
      }

    }

    const submitForm=():void=>{
      if(complete.value){
         //console.log('ooooo',JSON.parse(JSON.stringify(paramsValue)))
         const paramsVal=JSON.parse(JSON.stringify(paramsValue))
          //console.log('1111ooooo',paramsValue)
          paramsVal["id"]=productInfo.id
          //console.log('2222ooooo',paramsVal)
          editorProduct(paramsVal).then(response=>{
            //console.log('请求发过去了')
          dialogVisible.value=true
          const res:any=response.data
          resultcode.value=res.code
          message.value=res.message
          
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
      productInfo,
      dialogVisible,
      goHome,
      message
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
</style>
