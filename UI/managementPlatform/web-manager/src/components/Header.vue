<template>
    <div class='main-content'>
        <div class='logo'>
            <div class='telecom-logo'></div>
            <div class='vertical-line'></div>
            <div class='damddos-logo'></div>
        </div>
        <div class='title'>天翼安全产品管理平台</div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { ref } from 'vue';
export default defineComponent({
  name: 'Header',
});
</script>

<style type="text/css" scoped>
.main-content{
    width:100%;
    height:100%;
    display:flex;
    align-items:center;
}
.logo{
    display:flex;
    width:18%;
    height:100%;
    align-items:center;
    margin-right:-4%;
}
.telecom-logo{
    width:40%;
    height:56%;
    background: url(../assets/telecom-logo.png) no-repeat;
    background-size: contain;
    margin-left:2%;
    margin-right:1%;
}
.damddos-logo{
    width:22%;
    height:100%;
    background: url(../assets/security-logo.png) no-repeat;
    background-size: contain;
    margin-left:1%;
}
.vertical-line{
    height:30%;
    width:0px;
    border-right:1px solid white;
    margin:0 1%;
}
.title{
    font-size:3.6vh;
    font-family: PingFang SC, PingFang SC-Bold;
    color:#ffffff
}
</style>
