<template>
  <div class="layout" >
    <div class="title">
        <div class="titlestyle">
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
                <div class="feature-title">{{item.functionName}}</div>
                <div class="feature-content">{{item.functionContent}}</div>
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
import { ref, toRefs } from 'vue';
import { useRouter } from 'vue-router'
export default defineComponent({
  name: "BorderedLayoutStyle",
  components:{
      Footer
  },
   props: {
      productName: String,
      description: String,
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
    // methods:{
    //     toForm(){
    //        this.$router.push({
    //             name:'CustomForm'
    //         })

    //     }
    // },
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
    max-width:750px;
    margin-bottom:40px;
    display:flex;
    flex-direction:column;
    justify-content:flex-start;
    text-align:left;
    border-left:8px solid rgba(235, 123, 122, 1);
}
.features-content0{
    margin-top:56px;
}
.features-content0,
.features-content3{
    border-left:8px solid rgba(235, 123, 122, 1);
}
.features-content1,
.features-content4{
    border-left:8px solid rgba(242, 185, 92, 1);
}
.features-content2,
.features-content5{
    border-left:8px solid rgba(95, 195, 207, 1);
}
.feature-title{
    font-size: 32px;
    font-family: PingFang SC;
    font-weight: 500;
    line-height: 36px;
    display:inline-block;
    color: #333333;
    margin-left:18px;
    margin-right:18px;
    padding-bottom:14px;
}
.feature-content{
    font-size: 24px;
    font-family: PingFang SC;
    font-weight: 400;
    color: #666666;
    display:inline-block;
    margin-left:18px;
    margin-right:18px;
    padding-bottom:10px;
    word-break: break-all;;
}
.feature{
    display:flex;
    flex-direction:column;
    margin:0 50px;
}
.productAdvantage{
    /*
    position:relative; */
    margin-top:10px;
    padding-bottom:190px;
}
.advantages-content{
    /*
    position:relative;
    top:260px;
    */
    margin-top:40px;
}
.advantages{
    display:flex;
    flex-direction:column;
    margin:0 24px;
    /*
    position:relative;
    top:-260px;
    */
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
