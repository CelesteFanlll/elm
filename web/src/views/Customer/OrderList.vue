<script setup>

import Cfooter from "@/views/Customer/component/Cfooter.vue";

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        我的订单
      </el-header>
      <el-main style="height: 100vh">
        <el-scrollbar>
          <router-view></router-view>
        </el-scrollbar>
      </el-main>
      <el-footer>

        <Cfooter></Cfooter>

      </el-footer>
    </el-container>
  </div>
</template>

<style scoped>

.header{
  display: flex;
  align-items: center;
  background-color: #0d47a1;
  color: white;
}

</style>
