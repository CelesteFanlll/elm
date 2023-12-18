<script setup>

import router from "@/router";

const toLogin = ()=>{

  router.push('/CustomerLogin')

}
</script>

<template>
<div>
  出现错误，请重新登录
</div>
  <el-button @click="toLogin">点击跳转重新登录</el-button>
</template>

<style scoped>

</style>
