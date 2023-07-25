<template>
  <div class="content" >
      <ProductCard ></ProductCard>
  </div>
  
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { ref } from 'vue';
import ProductCard from '@/components/ProductCard.vue'
export default defineComponent({
  name: 'Content',
  components:{
    ProductCard
  }
});
</script>

<style type="text/css" scoped>
.content{
  box-sizing:content-box;
  width:96%;
  height:100%;
  margin:0 auto;
}
.card{
  width:20%;
  height:30%;
}
</style>