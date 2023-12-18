<script setup>


import {reactive, ref} from "vue";
import { UploadFilled } from '@element-plus/icons-vue'
import {useBusinessStore} from "@/store/useBusinessStore"
import axios from "axios";
import base_url from "@/store/constants";
import {ElMessage} from "element-plus";

const businessStore = useBusinessStore();
const shopFormRef = ref()
const shopForm = reactive({

  shopName : '',
  shopDes : '',
  shopImg : 'https://p1.ssl.qhmsg.com/t019ae7feb90fdc5c2a.jpg',
  businessId : parseInt(businessStore.businessId)


})

const rules = reactive({
  shopName: [
    { required: true, message: '请输入商店名', trigger: 'blur' },
  ],
  shopDes: [
    { required: true, message: '请输入描述', trigger: 'blur' },
  ],
})

const add = async (formEl)=>{

  console.log(shopForm)
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      await axios.put(base_url+'/sys/shop/post',shopForm)
      ElMessage({
        message: '新增成功',
        type: 'success',
      })
    } else {
      console.log('error submit!', fields)
    }
  })

}

</script>

<template>


    <div class="common-layout">
      <el-container>
        <el-header class="header">新增店铺</el-header>
        <el-main>
          <el-upload
              class="upload-demo"
              drag
              action="D:\c"
              multiple
          >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              拖动图片或者<em>点击上传</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                请小于5Mb
              </div>
            </template>
          </el-upload>

          <el-form
              ref="shopFormRef"
              :model="shopForm"
              :rules="rules"
              label-width="70px"
              class="demo-ruleForm"
              status-icon>

            <el-form-item  label="名称" prop="shopName">
              <el-input  v-model="shopForm.shopName" />
            </el-form-item>

            <el-form-item label="简介" prop="shopDes">
              <el-input v-model="shopForm.shopDes"  />
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="add((shopFormRef))">
                新增
              </el-button>

            </el-form-item>


          </el-form>






        </el-main>
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
