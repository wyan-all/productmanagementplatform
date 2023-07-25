<template>
  <div id="app">
  <!--
    <PageLayout />
    <Home></Home>
    -->
    <router-view></router-view> 
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import PageLayout from '@/components/PageLayout.vue';
import Home from '@/components/Home.vue';

export default defineComponent({
  name: 'App',
  components: {
    PageLayout,
    Home
  }
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
