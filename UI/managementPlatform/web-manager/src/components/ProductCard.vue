<template>
    <div class='container' >
      <div class="topbar">
        <p class="headline">产品列表</p>
        <button class="add-bar" @click="addPro">新增</button>
      </div>
      <div class="card" v-for="(item,index) in prolist">
      
        <div class="icon-name">
        <!--图片的src需要修改，补齐成完整的后台的地window.location.origin+'/service-core/rest'+址
        :src="`${baseUrl}/productCard/getIconById?id=${item.id}`"
        -->
        
          <img class="product-icon"  :src="`${baseUrl}/productCard/getIconById?id=${item.id}`" style="border:none"/>
          <p class="name"><p class="des-title">{{item.productName}}</p></p>
        </div>
        <el-popover
            title="产品描述："
            placement="bottom"
            :width="300"
            trigger="hover"
            :content="item.productDescription"
            hide-after=0
          >
          <template #reference>
            <div class="description"><p class="des-title">产品描述：</p>{{item.productDescription}}</div>
          </template>
        </el-popover>
        
        <div class="bar">
            <button class="editor" @click="editorPro(item)">编辑</button>        
            <button class="delete" @click="deletePro(item.id)">删除</button>
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
import { defineComponent } from 'vue';
import { ref,toRaw } from 'vue';
import { getInfo, deleteProduct } from '@/api';
import { IProductItem } from '@/typings';
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'ProductCard',
  components:{
  },
  setup(){
    const prolist=ref<IProductItem[] | undefined>([])
    const productLen=ref<number>(0)
    const productId=ref<number>(0)
    const dialogVisible = ref<boolean>(false)
    const message=ref<string>('')
    const resultcode=ref<number|string>()
    const baseUrl=window.location.origin+'/service-core/rest'
    
    //console.log(baseUrl)
    

    const getpro=():void=>{
      getInfo().then(response=>{
        const res:any=response.data
        //console.log('1111111111response.data',res)
        if(res.code===200){
            //console.log('1111111111,res.result',res.result)
            prolist.value=res.result
            console.log('1111fsdfsgswefwe1',prolist.value)
            if(prolist.value){
              productLen.value=prolist.value.length
              productId.value=prolist.value[productLen.value-1].id
              //console.log('hhhhhhhh',productId.value)
            }
        }
      }).catch((err) => console.log(err));
    }
    getpro()

    const router = useRouter();
    const addPro=():void=>{
      router.push({
          name:'addProduct',
          params:{
            productId:productId.value
          }
      });
    }

    const editorPro=(e:any):void=>{
      //console.log(JSON.stringify(e))
      router.push({
          name:'editorProduct',
          params:{
                  productInfo:JSON.stringify(e),
                }
      });
    }
    const deletePro=(e:any):void=>{
         deleteProduct(e).then(response=>{
          dialogVisible.value=true
          const res:any=response.data
          resultcode.value=res.code
          message.value=res.message
          // if(res.code==='200'){
          //   alert(res.message)
          //  // prolist.value=res.result
          //   getpro()
          // }
          
        }).catch((err) => console.log(err)); 
    }

     const goHome=():void=>{
       dialogVisible.value=false
       // console.log('22222')
            getpro()   
      //console.log('666666666666',paramsValue)
    }
   
    return{
      prolist,
      addPro,
      editorPro,
      deletePro,
      deleteProduct,
      getpro,
      dialogVisible,
      goHome,
      message,
      productId,
      productLen,
      baseUrl
      
    }
  }
});
</script>

<style type="text/css" scoped>
.container{
  width:100%;
  display:flex;
  flex-wrap:wrap;
  justify-content:start;
  padding:0 6px;
  padding-bottom:20px;
}
.card{
  width:260px;
  margin:14px 8px;
  background:#ffffff;
  display:inline-block;
  padding:0 12px;
}
.topbar{
  width:100%;
  height:40px;
  display:flex;
  align-items:center;
  margin-top:16px;
}
.headline{
  font-size:22px;
  margin-left:4px;
}
.add-bar{
  width:80px;
  height:34px;
  font-size:18px;
  position:absolute;
  right:30px;
  color:white;
  background-color:#1463f3;
  border:2px solid #1463f3 ;
  border-radius:6px;
}
.product-icon{
  width:80px;
  height:80px;
  /*
  background: url(../assets/Ddos.png) no-repeat;
  */
  background-size: contain;
  margin:0 10px;
}
.icon-name{
  display:flex;
  align-items:center;
}
.name{
  font-size:16px;
}
.description{
  width:260px;
  font-size:14px;
  min-height:56px;
  max-height:60px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow-y:hidden;
  position:relative;
}
.des-title{
  display:inline-block;
  font-weight:600;
}
.bar{
  display:flex;
  justify-content:space-around;
  margin:20px 0;
}
.editor, 
.delete{
  width:60px;
  height:30px;
  font-size:14px;
  color:white;
  background-color:#1463f3;
  border:2px solid #1463f3;
  border-radius:6px;
}
.delete{
  background-color:#dddddd;
  color:#333333;
  border:2px solid #dddddd;
}

</style>
