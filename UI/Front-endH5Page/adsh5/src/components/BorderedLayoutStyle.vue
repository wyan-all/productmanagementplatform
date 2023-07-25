<template>
  <div class="layout">
    <div class="title">
        <div class="titlestyle" id="title">
        {{productName}}
        </div>
        <span class="description" v-html="description">  
        </span>
        <button class="buttontbar" @click="toForm">立即试用</button>  
    </div>
    <div class="productfunction">
        <span class="headline">
        产品功能
        </span>
        <div class="feature" >
            <div class="features-content"  v-for="(item,index) in productFunction" :key="index" 
            :class="`features-content${index}`">
                <span class="feature-title">{{item.functionName}}</span>
                <p class="feature-content" v-html="item.functionContent"></p>
            </div>   
        </div>
    </div>
    <div class="productAdvantage">
        <span class="headline">
            产品优势
        </span> 
        <div class="advantages" >
            <div class="advantages-content"  v-for="(item,index) in advantage" :key="index" >
                <span class="advantage-title">{{item.advantageName}}</span>
                <p class="advantage-content" v-html="item.advantageContent"></p>
                <div class="linebar" v-show="index===advantage.length-1?false:true" >
                </div>
            </div>  
      </div>
    </div>
    <Footer></Footer>  
</div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Footer from '@/components/Footer.vue';
import { ref ,toRefs} from 'vue';
import { useRouter } from 'vue-router'
export default defineComponent({
  name: "BulletVerticalStyle",
  components:{
      Footer
  },
   props: {
      productName:{
         type: String,
        },
      description:{
         type: String,
        },
     productFunction:{
         type: Array,
         default:()=>[{
             functionName: String,
             functionContent: String,
           }]  
        },
     advantage:{
         type:Array,
         default:()=>[]
        }
    },
    setup (props) {
        const { productName } = toRefs(props);
        //console.log(productName.value)
        const router = useRouter();
        const toForm=():void=>{
            //console.log(productName.value)
          router.push({
              name:'CustomForm',
              params:{productName:productName.value}
            });
        }
        return {
            productName,
            toForm,
        }
  }
});
</script>

<style type="text/css" scoped>
.layout{
    display:flex;
    flex-direction:column;
}
.title{
    width:750px;
    position:relative;
    background: #FFFFFF;
    display:flex;
    flex-direction:column;
}
.titlestyle{
    padding-top:50px;
    font-size: 46px;
    font-family: PingFang SC;
    font-weight: bold;
    color: #423F5C;
    line-height: 30px;
}
.description{
    display:inline-block;
    /*position:absolute;
    */
    line-height: 36px;
    top:94px; 
    margin:0px 50px;
    font-size: 24px;
    font-family: PingFang SC;
    font-weight: 400;
    color: #666666;
    margin-top:32px;
    padding-bottom:160px;
}
.buttontbar{
    width: 255px;
    height: 78px;
    background: #3366ff;
    border-radius: 39px;
    border:none;
    font-size: 38px;
    font-family: PingFang SC;
    font-weight: 400;
    color: #FFFFFF;
    /*
    position:relative;
    top:258px;
    */  
    position:absolute;
    bottom:40px;
    left:376px;
    transform:translate(-50%,0)
}
.productfunction{
    position:relative;
    /*top:300px;*/
    width: 750px;
    background: #F7F7F7;
}
.headline{
    display:inline-block;
    margin-top:50px;
    font-size: 36px;
    font-family: PingFang SC;
    font-weight: 400;
    color: #333333;
    line-height: 33px;
}
.features-content{
    min-width:204px;
    max-width:313px;
    min-height: 230px;
    background: linear-gradient(140deg, #EB7B7A 0%, #EDAC88 100%);
    border-radius: 0px 0px 40px 0px;
    margin-top:40px;
    margin-bottom:50px;
}
.features-content1{
    background: linear-gradient(0deg, #5EC2D1 0%, #83D5A8 100%);
    margin-left:16px;
}
.features-content2{
    margin-left:16px;
    background: linear-gradient(145deg, #F2B85C 0%, #EFDA86 100%);
}
.feature-title{
    font-size: 32px;
    font-family: PingFang SC;
    font-weight: 500;
    color: #FFFFFF;
    line-height: 36px;
    display:inline-block;
    margin-top:40px;
}
.feature-content{
    font-size: 24px;
    font-family: PingFang SC;
    font-weight: 400;
    color: #FFFFFF;
    display:inline-block;
    margin:24px 18px;
    word-break: break-all;;
}
.feature{
    display:flex;
    justify-content: space-around;
    margin:0 24px;
}
.productAdvantage{
    margin-top:10px;
    padding-bottom:190px;
}
.advantages-content{
    margin-top:40px;
}
.advantages{
    display:flex;
    flex-direction:column;
    margin:0 24px;
    text-align: left;
}
.advantage-title{   
    display:inline-block;
    padding:6px 16px;
    background-color: #3366ff;
    border-radius: 0px 0px 12px 0px;
    font-size: 26px;
    font-family: PingFang SC;
    font-weight: bold;
    color: #FFFFFF;
    line-height: 33px;
}
.advantage-content{
    display:block;
    margin-top:20px;
    margin-bottom:14px;
    font-size: 24px;
    font-family: PingFang SC;
    font-weight: 400;
    color: #777777;
    line-height: 36px;
    word-break: break-all;;
}
.linebar{
    width: 680px;
    background: #FFFFFF;
    height: 1px;
    border-top: 1px solid #EEEEEE;    
    overflow:hidden;
}
</style>
