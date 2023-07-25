<template>
  <div>
    <div class="headertab" v-if="listArray.length" >
        <div class="header_tab" ref="headertab"  id="headertab">
            <ul ref="tabitem" id="tabitem">
                <li
                  v-for="(item,index) in listArray"
                  :key="index"
                  :class="index==current?'activeheader':`li${index}`"
                  @click="getTab(index,$event)"
                >
                {{item.scrollName}}
                </li>
            </ul>
        </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';
import { ref } from 'vue';
import {IScrollItem} from '@/typings'
export default defineComponent({
  name: 'HeaderTab',
  props:{
      listArray:Array as PropType<IScrollItem[]>,
      current:{
          type: Number,
          default:0,
      }
  },
  methods:{
      getTab(index:number,e:MouseEvent):void {
          this.$emit('change',index,e)
      }
  } ,
});
</script>

<style type="text/css" scoped>
.headertab {
  width: 750px;
  height: 110px;
  background: #FFFFFF;
  display: flex;
  border-radius: 24px 24px 0px 0px;
  box-sizing:border-box;
  padding: 0 30px;
  flex-direction: row;
  justify-content:space-around;
  border-bottom: 1px solid #EEEEEE;
}
.header_tab {
  width: 750px;
  display: flex;
  flex-wrap: nowrap;
  overflow-x: scroll;
  margin-top:36px;
}
.header_tab::-webkit-scrollbar {
  display: none;
}
ul {
  display: inline-block;
  white-space: nowrap;
}
li {
  display: inline-block;
  padding-left:58px;
  font-size: 30px;
  font-family: PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 50px;  
}
li:first-child {
  margin-left:-58px;
}
.activeheader { 
  position: relative;
  display:inline-block;
  font-size: 38px;
  font-family: PingFang SC;
  font-weight: bold;
  color: #0D47E7;
}
.activeheader:after {
  position: absolute;
  display:block;
  content: "";
  bottom:-22px;
  left: 50%;
  transform:translate(50%,0%);
  width: 32px;
  height: 7px;
  background: #0D47E7;
  border-radius: 4px;
}
</style>
