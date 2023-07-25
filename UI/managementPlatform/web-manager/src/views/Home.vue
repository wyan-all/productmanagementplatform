<template>
  <div class="home">
   <div class="header">
      <Header/>
   </div>
   <div class="content-body">
     <div class="sider"></div>
     <div class="content">
        <Content/>
     </div>
   </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Header from '@/components/Header.vue'
import Content from '@/components/Content.vue'

export default defineComponent({
  name: 'Home',
  components: {
    Header,
    Content
   
  },
});
</script>
<style type="text/css" scoped>
.home{
  width:100%;
  height:100%;
}
.header{
  width:100%;
  height:8%;
  background:#1371f0;
}
.content-body{
  width:100%;
  height:90%;
  float:left;
}
/*
.sider{
  width:16%;
  float:left;
  height:100%;
  background:blue;
}
*/
.content{
  height:100%;
  background-color:#f2f8fc;
}


</style>
