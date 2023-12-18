<script setup>

import {reactive, ref} from "vue";
import {useRouter} from "vue-router";
import base_url from "@/store/constants";
import axios from "axios";
import {ElMessage} from "element-plus";

import {useCustomerStore} from "@/store/useCustomerStore"

const customerStore = useCustomerStore(); // 获取 useCustomerStore 的实例

const router = useRouter();
const ruleFormRef = ref()
const ruleForm = reactive({
  customerName: "",
  password: ""
})

const rules = reactive({
  customerName: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
  ],
})

const toBusiness = () => {

  router.push("/BusinessLogin")

}

const Login = async (formEl) => {

  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      const res = await axios.post(base_url + '/sys/customer/login', ruleForm)
      console.log(res)
      let {code} = res.data
      let data;
      data = res.data.data


      if (code === 20000) {
        // 下一步 把顾客用户的数据存入Customer里
        customerStore.changeCustomer(ruleForm)
        localStorage.setItem("token", JSON.stringify(data.token))
        router.push("/CustomerIndex")

      } else {
        ElMessage.error('用户名密码不匹配')

      }
    } else {
      console.log('error submit!', fields)
    }
  })

}

const toSignup = () => {
  router.push('/Csignup')
}
</script>

<template>
  <div class="common-layout">
    <el-container style="height: 100vh">
      <el-aside
          style="background-color: #a0cfff; width: 300px; display: flex; flex-direction: column; justify-content: center; align-items: center;">

        <div style="font-size: 25px; padding-bottom: 5px; text-align: center;">你好</div>

        <div style="font-size: 25px; padding-bottom: 5px; text-align: center;">
          欢迎来到顾客端登录界面
          <el-button type="primary" @click="toBusiness">我是商家？</el-button>
        </div>

      </el-aside>


      <el-main
          style="display: flex; flex-direction: column; justify-content: center; align-items: center;background-color: #e9e9eb">

        <el-card>
          <el-form
              ref="ruleFormRef"
              :model="ruleForm"
              :rules="rules"
              label-width="70px"
              class="demo-ruleForm"
              status-icon>

            <el-form-item label="用户名" prop="customerName">
              <el-input v-model="ruleForm.customerName"/>
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input v-model="ruleForm.password" type="password"/>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="Login((ruleFormRef))">
                登录
              </el-button>

              <el-button type="success" @click="toSignup">注册</el-button>

            </el-form-item>

          </el-form>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>

</style>
