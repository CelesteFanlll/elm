<script setup>

import Cfooter from "@/views/Customer/component/Cfooter.vue";

import {useCustomerStore} from "@/store/useCustomerStore"


const customerStore = useCustomerStore(); // 获取 useCustomerStore 的实例
</script>

<template>
  <div >
    <el-container>
      <el-header class="header">
        我的信息
      </el-header>
      <el-main style="height: 100vh">

        <el-row>
          <el-col :span="2">
            <div>
              <el-avatar style="height: 100px;width: 100px"
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              />
            </div>
          </el-col>

          <el-col :span="6" style="display: flex; justify-content: center; align-items: center;">
            <el-text style="font-size: 50px;">用户 {{customerStore.customer.customerName}} 您好</el-text>
          </el-col>


        </el-row>






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
