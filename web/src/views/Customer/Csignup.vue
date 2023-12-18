<template>
  <div style="height: 150px"></div>

  <div class="registration-page">
    <h1>顾客注册</h1>
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="70px"
        class="demo-ruleForm"
        status-icon>

      <el-form-item style="margin-bottom: 20px" label="用户名" prop="customerName">
        <el-input v-model="ruleForm.customerName" />
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" type="password" />
      </el-form-item>

      <el-form-item style="margin-bottom: 30px;margin-top: 20px" label="手机号" prop="phone">
        <el-input v-model="ruleForm.phone" />
      </el-form-item>

      <el-form-item style="margin-top: 20px">

        <el-button type="success" @click="Signup">注册</el-button>

      </el-form-item>


    </el-form>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import router from "@/router";
import axios from "axios";
import base_url from "@/store/constants";


const Signup = async () => {

  await axios.post(base_url + '/sys/customer/signup', ruleForm)


  ElMessage({
    message: '注册成功，2秒后将跳转登录界面',
    type: 'success',
  })


  setTimeout(function () {
    router.push('/CustomerLogin')
  }, 2000);
}

const ruleFormRef = ref()
const ruleForm = reactive({
  customerName: "",
  password: "",
  phone: ''
})

const rules = reactive({
  customerName: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
  ],
  phone: [
    {required: true, message: '请输入手机号', trigger: 'blur'},
  ]
})

</script>

<style scoped>
.registration-page {
  width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f5f5f5;
}

.registration-page h1 {
  text-align: center;
  font-size: 24px;
  color: #333;
}

.registration-page .el-form {
  margin-top: 20px;
}

.registration-page .el-form-item {
  margin-bottom: 10px;
}

.registration-page .el-form-item label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.registration-page .el-input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.registration-page .el-button {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: #fff;
  text-align: center;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

/* 美化样式 */
.registration-page .form-group {
  margin-bottom: 20px; /* 可根据需要调整 */
}

.registration-page .form-group label {
  font-size: 16px; /* 可根据需要调整 */
}
</style>
