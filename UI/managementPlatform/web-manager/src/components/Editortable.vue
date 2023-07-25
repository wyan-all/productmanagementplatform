<template>
  <div>
        <div class="headline">产品图片选择</div>
        <div class="line"></div>
        <div class="head-img">
               <form enctype="multipart/form-data" id="form" name="fileinfo" method="post">
                    <input type="file" id="upload" 
                            style="display:none" 
                            accept="image/png"
                            @change="freshImage"
                            class="uploadImage"
                    />
               </form>    
                
                <div class="border" @click="uploadImage">
                    <span style="index:100">{{headTip}}</span>
                    <img :src="imagesrc" class="imgDiv">
                </div>
                <button class="add-bar" id="save-icon" @click="addicon">提交</button>
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
            
            <el-button type="primary" class="add-bar" id="save-icon" @click="dialogVisible=false"
              >确认</el-button>
              
          </span>
        </template>
    </el-dialog>
        
        <div  name="content" id="formId" @keyup ="KeyDown($event)"  >
        <div class="headline">产品基本信息</div>
        <div class="line"></div>
        <!--这一部分抽成一个单独组件（组件间通信传icon-id），或者这一部分放在基本信息上面，需要处理一下按钮激活部分的逻辑，或者放在新增、编辑页面的最顶部,
        也需要处理按钮部分的逻辑
        -->
        <div class="title">
            <div>
                <label><em class="asterisk">*</em> 产品名称:</label>
                <div style="padding:0 20px;">
                    <input type="text" name="productName" class="productName"  v-model="productName" placeholder="请输入产品名称" >
                </div>  
            </div>
            <div> 
                <label><em class="asterisk">*</em> 滚动栏标题:</label>
                <div style="padding:0 20px;">
                    <input type="text" name="scrollName" class="scrollName" v-model="scrollName" placeholder="请输入滚动栏标题" >
                </div>
            </div>
        </div>
        <label><em class="asterisk">*</em> 产品描述：</label>
        <el-input
            class="product-des"
            v-model="productDescription"
            maxlength="200"
            placeholder="请输入产品描述"
            show-word-limit
            type="textarea"
            resize="none"
        />
       <div class="pro-functions">
       <div class="headline">产品功能</div>
        <div class="line"></div>
            <button class="add-bar" @click="addfunction">新增</button>
            <div class="function-point" v-for="(item,index) in productFunctions">
                <label class="pro-function-Name"><em class="asterisk">*</em> 功能名称：</label>
                <button class="add-bar" @click="deletefunction(productFunctions,item)">删除</button>
                <el-input
                    class="el-text"
                    v-model="item.functionName"
                    maxlength="20"
                    placeholder="请输入功能名称"
                    show-word-limit
                    type="text"
                >
                </el-input>

                <label class="pro-function-des"><em class="asterisk">*</em> 功能描述：</label>
                <el-input
                    class="el-textarea"
                    v-model="item.functionContent"
                    maxlength="80"
                    placeholder="请输入功能描述"
                    show-word-limit
                    type="textarea"
                    resize="none"
                />
                <div class="space"></div>
            </div>
       </div>
       <div class="pro-advantages">
            <div class="headline">产品优势</div>
            <div class="line"></div>
            <button class="add-bar" @click="addadvantage">新增</button>
            <div class="advantage-point" v-for="(item,index) in productAdvantages">
                <label class="pro-advantage-Name"><em class="asterisk">*</em> 优势名称：</label>
                <button class="add-bar" @click="deleteadvantage(productAdvantages,item)">删除</button>
                <el-input
                    class="el-text"
                    v-model="item.advantageName"
                    maxlength="20"
                    placeholder="请输入优势名称"
                    show-word-limit
                    type="text"
                >
                </el-input>
                <label class="pro-advantage-des"><em class="asterisk">*</em> 优势描述：</label>
                <el-input
                    class="el-textarea"
                    v-model="item.advantageContent"
                    maxlength="80"
                    placeholder="请输入优势描述"
                    show-word-limit
                    type="textarea"
                    resize="none"
                />
                <div class="space"></div>
            </div> 
       </div>    
        <div class="headline">H5样式选择</div>
        <div class="line"></div>
        <div class="waring"><div class="waring-icon"></div> <label class="waring-word">请选择产品H5落地页的样式<p style="display:inline-block; color:#d81e06">（注意：为了更好的显示效果，当产品功能数量大于3时，请选择样式二）</p></label></div>
        <div class="style-chose">
                <div>
                    <label><input type="radio" name="H5style"  value=0 checked="checked" v-model="styleId" @click="getRadioVal">样式一</label>
                    <div class="BorderedStyle"></div>
                </div>
                <div>
                    <label><input type="radio" name="H5style"  value=1 v-model="styleId" @click="getRadioVal">样式二</label>
                    <div class="BulletVerticalStyle"></div>
                </div>
        </div>
  </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';
import { ref,toRaw,reactive } from 'vue';
import { IFunctionItem, IAdvantageItem, IProductItem } from '@/typings';
import { addIcon } from '@/api';
import { dataURItoBlob } from '@/utils/dataURItoBlob'
export default defineComponent({
  name: '',
  props:{
      productInfo:Object,
      iconId:Number
  },
  setup(props,context){
      //console.log('3333hhhhhhhh',props.productInfo,props.iconId)
      const iconId=ref<number>(0)
      if(props.iconId){
         iconId.value=Number(props.iconId)
        }
      
      const productDescription= ref<string>('')
      //const textarea= ref<string>('')
      const productName= ref<string>('')
      const scrollName= ref<string>('')
      const productFunctions=ref<IFunctionItem[]>([{functionName:'',functionContent:'',id:undefined,
                        productId: undefined   }])
      const productAdvantages=ref<IAdvantageItem[]>([{advantageName:'',advantageContent:'',id:undefined,
                        productId: undefined}])
      const styleId=ref<number>(0)
      const productId=ref<number>(0)
      //提示框
      const dialogVisible = ref<boolean>(false)
      const message=ref<string>('')

      if(props.productInfo){
          productDescription.value=props.productInfo.productDescription
          productName.value=props.productInfo.productName
          scrollName.value=props.productInfo.scrollName
          productFunctions.value=props.productInfo.productFunctions
          productAdvantages.value=props.productInfo.productAdvantages
          styleId.value=props.productInfo.styleId
          productId.value=props.productInfo.id
        }
        const iconid=ref<number>(0)
        iconid.value=productId.value?productId.value:iconId.value
        //console.log('55555555',iconid.value)
      
      const infoToParent=():void=>{
            context.emit('isfinished',complete,
          {'productName':productName.value,'scrollName':scrollName.value,'productDescription':productDescription.value,
          'productFunctions':toRaw(productFunctions.value),'productAdvantages':toRaw(productAdvantages.value),'styleId':styleId.value,
         // 'icon':imagesrc.value
          
          })
        }

      const getRadioVal=(e:any):void=>{
          styleId.value=Number(e.target.value)
          //console.log(styleId.value)
          infoToParent()
        }
      const addfunction=():void=>{
          productFunctions.value.push({functionName:'',functionContent:'',id:undefined,
                        productId: undefined})
          //有未填项，提交按钮失效
          complete.value=false
          infoToParent()
        }
      
      const addadvantage=():void=>{
          productAdvantages.value.push({advantageName:'',advantageContent:'',id:undefined,
                        productId: undefined})
          complete.value=false
          infoToParent()
        }
      const deletefunction=(arr:IFunctionItem[],val:{}):void=>{
          for(var i =0; i<arr.length; i++){
              if(arr[i]==val){
                  arr.splice(i,1)
                  if(arr.length===0){
                      complete.value=false
                      
                   }else{
                       complete.value=true
                   }
                   //tableState()
                  infoToParent()
                  break;
               }
           }
        }
      const deleteadvantage=(arr:IAdvantageItem[],val:{}):void=>{
          for(var i =0; i<arr.length; i++){
              if(arr[i]==val){
                  arr.splice(i,1)
                  if(arr.length===0){
                      complete.value=false
                       //infoToParent()
                   }else{
                        complete.value=true
                       //infoToParent()
                   }
                  // tableState()
                 infoToParent()
                  break;
               }
           }
        }

     //校验表单是否全部填写完毕
      const countInput=ref<number>(0)
      const countArea=ref<number>(0)
      const complete=ref<boolean>(false) 
      const tableState=():void=>{
            countInput.value=0
            countArea.value=0
            const form:HTMLElement| null=document.getElementById("formId");
            if(form){
                const tagEles:HTMLCollectionOf<HTMLInputElement>=form.getElementsByTagName('input');
             //console.log('33333333333',tagEles.length) //7
                for(var j=0;j<tagEles.length;j++){
                        if(tagEles[j].value) {
                            countInput.value+=1
                        }
                }
                const textareas:HTMLCollectionOf<HTMLTextAreaElement>=document.getElementsByTagName('textarea');
                for(var j=0;j<textareas.length;j++){
                        if(textareas[j].value) {
                            countArea.value+=1
                        }
                }
                complete.value=false
                //且产品功能与产品优势的不能为空
                //console.log(countInput.value,tagEles.length)
                if(countInput.value===tagEles.length && countArea.value===textareas.length && productFunctions.value.length && productAdvantages.value.length ){
                    complete.value=true
                    infoToParent()
                }

            }
        }

      const KeyDown=():void=>{
            tableState()
            infoToParent()
        }
      
      //图片处理
        let headTip=ref("上传产品icon")   
        let imagesrc=ref<string | ArrayBuffer | null>('')
        const baseUrl=window.location.origin+'/service-core/rest'
        imagesrc.value=productId.value?`${baseUrl}/productCard/getIconById?id=${productId.value}`:''

        //headTip的值的显示
        if(imagesrc.value){
            headTip.value=''
        }else{
            headTip.value='上传产品icon'
        }

        const uploadImage=():void=>{
            const uploadEle=document.getElementById('upload')
            
            if(uploadEle){
                uploadEle.click();
            }
        }

        const imageBlob= ref<Blob>()
        const  imgObj=ref()
        const datauri=ref<string | ArrayBuffer | null>('')
        const freshImage=(e:any):void=>{
            imgObj.value=e.target.files[0];

            console.log('00000000000000',imgObj.value)
            
            let fr=new FileReader()
            //console.log('kkkkkk',fr.result)
            fr.readAsDataURL(imgObj.value)
            fr.onload=function(){
                imagesrc.value=fr.result
                datauri.value=fr.result
                //console.log('2kkkkkk',datauri.value)
            }
            headTip.value=''
        }
        //提交icon图片
        const addicon=():void=>{
            var form:HTMLFormElement | null=document.forms.namedItem('fileinfo')
            //console.log('gggggggggg',form)
            //console.log('00000000000000',imgObj.value)
            //let name=file.name
            if(form){
                var fileData=new FormData()
                let blob=dataURItoBlob(datauri.value)
                //fileData.append('file',imgObj.value)
                fileData.append('file',blob)

                //console.log('33hhhhhhhhh',fileData)
                if(imagesrc.value){
                    // 需修改icon id
                    addIcon(iconid.value,blob).then(response=>{          
                        const res:any=response.data
                        if(res.code=='200'){
                            dialogVisible.value=true;
                            message.value=res.message
                            //alert("图片上传成功")
                        }else{
                            dialogVisible.value=true;
                            message.value=res.message
                        }
                    }).catch((err) => console.log(err));
                }else{
                    dialogVisible.value=true;
                    message.value='请先上传icon图片'
                }
            }
        }

        //console.log()

      return {
          productDescription,
          productFunctions,
          addfunction,
          productAdvantages,
          addadvantage,
          deleteadvantage,
          deletefunction,
          KeyDown,
          complete,
          tableState,
          productName,
          scrollName,
          styleId,
          productId,
          getRadioVal,
          infoToParent,
          uploadImage,
          freshImage,
          headTip,
          imagesrc ,
          imageBlob   ,
          addIcon  ,
          addicon,
          iconid,
          message,
          dialogVisible,
          imgObj
          
       }
  }
});
</script>

<style type="text/css" scoped>
.content{
    display:flex; 
}
.headline{
  margin-left:26px;
  margin-right:26px;
  margin-bottom:20px;
  border-left: 6px solid #1371f0;
  font-size: 24px !important;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333 !important;
  line-height: 30px;
  padding-left:10px;
  -webkit-appearance: none!important;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
.line{
  margin-left:26px;
  margin-right:26px;
  border-bottom: 1px solid #BBBBBB;
  margin-bottom:20px;
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

#save-icon{
    position:relative;
    left:80px;
    top:-12px;
}



.title{
    display:flex;
}
.scrollName{
    width:510px;
}
.productName{
    width:510px;
}
.scrollName,
.productName
{
  display:block;
  margin-left:26px;
  margin-right:26px;
  margin-bottom:30px;
  width: 480px;
  height: 43px;
  border-radius: 4px;
  border: 1px solid #BBBBBB;
  font-size: 22px !important;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333 !important;
  line-height: 30px;
  padding-left:20px;
  -webkit-appearance: none!important;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
input:focus{
  border: 1px solid #FFFFFF;
  box-shadow: 0 0 0 2px #0D47E7;
  outline:none;
}
input::-webkit-input-placeholder{  
    color: #d6d6d6;
}
input::-moz-placeholder{   
    color: #d6d6d6;
} 
input::-ms-input-placeholder{ 
    color: #d6d6d6;
}
label{
  display:inline-block;
  margin-left:24px;
  margin-right:24px;
  text-align:left;
  font-size: 22px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 37px;
  margin-bottom:15px;
  padding:0 20px;
}
.asterisk{
  color:red;
}
.pro-functions{
    margin-top:30px;
    display:flex;
    flex-direction:column;
}
.function-point{
    display:flex;
    flex-direction:column;
}
.pro-function-name{
    display:block;
}

.pro-function-des{
    display:block;
}
.add-bar{
  width:80px;
  height:34px;
  font-size:18px;
  position:absolute;
  right:90px;
  color:white;
  background-color:#1463f3;
  border:2px solid #1463f3 ;
  border-radius:6px;
}
.space{
    margin-top:20px;
}
.pro-advantages{
    display:flex;
    flex-direction:column;
}
.advantage-point{
    display:flex;
    flex-direction:column;
}
.pro-advantage-name{
    display:block;
}
.pro-advantage-des{
    display:block;
}
.BorderedStyle{
    width:200px;
    height:400px;
    background: url(../assets/BorderedStyle.png) no-repeat;
    background-size: contain;
}
.BulletVerticalStyle{
    width:200px;
    height:400px;
    background: url(../assets/BulletVerticalStyle.png) no-repeat;
    background-size: contain;
}
.style-chose{
    display:flex;
    justify-content:space-around;
}
.waring{
    display:flex;
    margin-left:24px;
    margin-right:24px;
    text-align:left;
    font-size: 22px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #333333;
    line-height: 37px;
    margin-bottom:15px;
    padding:0 20px;
}
.waring-icon{
    width:40px;
    height:40px;
    background: url(../assets/waring.png) no-repeat;
    background-size: contain;
}
.waring-word{
    margin-left:0px;
    padding:0 10px;
}
</style>
