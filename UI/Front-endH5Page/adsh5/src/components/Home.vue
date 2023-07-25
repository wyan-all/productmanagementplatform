<template>
  <div class="home">
    <HeaderTab
      :listArray="list"
      :current="current"
      @change="changeTab"
      ref="tab"
      id="nav"
      class="nav"
    >
    </HeaderTab>
    <div
          v-for="(item, index) in list"
          :key="index"
    >
        <div v-if="index == current">
            <component :is="item.styleId==1?'BulletVerticalStyle':'BorderedLayoutStyle'" 
                    :productName="item.productName"
                    :description="item.productDescription"
                    :productFunction="item.productFunctions"
                    :advantage="item.productAdvantages"
            ></component>
        </div>
    </div> 
      
  </div>
</template>

<script lang="ts">
import HeaderTab from '@/components/HeaderTab.vue';
import BulletVerticalStyle from "@/components/BulletVerticalStyle.vue";
import BorderedLayoutStyle from "@/components/BorderedLayoutStyle.vue";
import { defineComponent,PropType } from 'vue';
import { ref } from 'vue';
import { IScrollItem, IProductItem } from '@/typings';
import {getlist} from '@/api/request'

export default defineComponent({
  name: 'scrollTable',
  components:{
      HeaderTab,
      BulletVerticalStyle,
      BorderedLayoutStyle,
    },
  setup(props){
      //list中的数据应该从接口中获取
      const list=ref<IProductItem[] | undefined>([])
      getlist().then(response=>{
          const res:any=response.data
          if(res.code=='200'){
              //console.log(1111111111)
              //console.log(res.data.result.results)
              list.value=res.result
            }
        }).catch((err) => console.log(err)); 
      const navBarFixed=ref<boolean>(false);
      const current=ref<number>(0);
      const liList=ref<EventTarget | null>() 
      const changeTab=(index:number, e:MouseEvent):void=>{
          current.value=index;  
        }
      return {
          getlist,
          list,
          navBarFixed,
          current,
          changeTab,
        }
    }
});
</script>

<style type="text/css" scoped>
.home{
    position:relative
}
.nav{
    position: -webkit-sticky;
    position:sticky;
    top:0;
    z-index:999
}
</style>
