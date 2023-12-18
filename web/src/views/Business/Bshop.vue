<script setup>
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import base_url from "@/store/constants";
import {useRoute} from "vue-router";
import { UploadFilled } from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";

const dialogVisible = ref(false)
const route = useRoute();
const tData = reactive({
  keyword: '',
})
let searchResult = ref([])
let shopMess = ref([])
const rules = reactive({



})
const updateFormRef = ref()
let updateForm = reactive({
  itemName:'',
  itemDescripition : '',
  itemPrice: '',
  isListed :''

})

onMounted(() => {
  getitems()
  getshop()

})
const getitems = async() => {
  tData.keyword = route.query.keyword.trim()
  var res = await axios.get(base_url+`/sys/item/getItem?id=${tData.keyword}`)
  searchResult.value = res.data
  console.log(searchResult.value)

}

const getshop = async ()=>{
  tData.keyword = route.query.keyword.trim()
  var res = await axios.get(base_url+`/sys/shop/get?id=${tData.keyword}`)
  shopMess.value = res.data
  console.log(shopMess.value)
}

let currentItem = ref()

const change=(row)=>{

  updateForm = row
  currentItem = row
  console.log(updateForm)
  dialogVisible.value = true




}

const handleConfirm = async ()=>{

  console.log(updateForm)
  await axios.post(base_url+'/sys/item/put',updateForm)
  ElMessage({
    message: '修改成功',
    type: 'success',
  })
  dialogVisible.value = false
  await getitems()

}


let dialogVisible2 = ref(false)
const addFormRef = ref()
let addForm = reactive({
  itemName:'',
  itemDescripition : '',
  itemPrice: '',
  isListed :'',
  itemImg : 'https://img.alicdn.com/imgextra/i1/806839897/O1CN01wfHbUM2MysSZ6IwWl_!!806839897.png',
  shopId : ''

})
const createItem =()=>{

  addForm.shopId = parseInt(parseInt(shopMess.value.shopId))
  console.log(addForm)
  dialogVisible2.value = true


}

const handleAddConfirm = async ()=>{

  console.log(addForm)
  await axios.put(base_url+'/sys/item/post',addForm)
  ElMessage({
    message: '新增成功',
    type: 'success',
  })
  dialogVisible2.value = false
  await getitems()


}
</script>

<template>
  <el-header class="header">
   {{shopMess.shopName}}的商品管理
  </el-header>

  <el-main>

    <div style="margin-bottom: 20px">
      <el-button @click="createItem">新增商品</el-button>
    </div>



    <el-table :data="searchResult" style="width: 100%">
      <el-table-column prop="itemName" label="商品名称" width="180" />
      <el-table-column prop="itemDescripition" label="标签" width="180" />
      <el-table-column prop="itemImg" label="图片" width="180">
        <template v-slot="{ row }">
          <img :src="row.itemImg" :alt="row.itemName" style="height: 30px;width: 30px">
        </template>
      </el-table-column>
      <el-table-column prop="itemPrice" label="价格" width="180" />
      <el-table-column prop="isListed" label="商品名称" width="180">
        <template v-slot="{ row }">
          <span v-if="row.isListed === 1">已上架</span>
          <span v-else>未上架</span>
        </template>
      </el-table-column>
      <el-table-column  label="操作" width="180" >
        <template v-slot="{ row }">
          <el-button type="primary" @click="change(row)">修改</el-button>
        </template>
      </el-table-column>

    </el-table>




  </el-main>

  <!--  修改框-->
  <el-dialog
      v-model="dialogVisible"
      title="修改商品"
  >

    <el-form
        ref="updateFormRef"
        :model="updateForm"
        :rules="rules"
        label-width="80px"
        class="demo-ruleForm"
        status-icon
    >

      <el-form-item label="商品名称" prop="itemName">
        <el-input v-model="updateForm.itemName" :value="currentItem.itemName"/>
      </el-form-item>

      <el-form-item label="标签" prop="itemDescripition">
        <el-input v-model="updateForm.itemDescripition" :value="currentItem.itemDescripition" />
      </el-form-item>

      <el-upload
          class="upload-demo"
          drag
          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
          multiple
      >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">
          Drop file here or <em>click to upload</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            jpg/png files with a size less than 500kb
          </div>
        </template>
      </el-upload>

      <el-form-item label="价格" prop="itemPrice">
        <el-input v-model="updateForm.itemPrice" :value="currentItem.itemPrice"/>
      </el-form-item>

      <el-form-item label="上架情况" prop="roleId">
        <el-select v-model="updateForm.isListed" class="m-2" placeholder="请选择" size="large">
          <el-option label="未上架" value="0"></el-option>
          <el-option label="上架" value="1"></el-option>
        </el-select>
      </el-form-item>


    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm()">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>


<!--  新增商品对话框-->

  <el-dialog
      v-model="dialogVisible2"
      title="新增商品"
  >

    <el-form
        ref="updateFormRef"
        :model="addForm"
        :rules="rules"
        label-width="80px"
        class="demo-ruleForm"
        status-icon
    >

      <el-form-item label="商品名称" prop="itemName">
        <el-input v-model="addForm.itemName"/>
      </el-form-item>

      <el-form-item label="标签" prop="itemDescripition">
        <el-input v-model="addForm.itemDescripition"  />
      </el-form-item>

      <el-upload
          class="upload-demo"
          drag
          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
          multiple
      >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">
          Drop file here or <em>click to upload</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            jpg/png files with a size less than 500kb
          </div>
        </template>
      </el-upload>

      <el-form-item label="价格" prop="itemPrice">
        <el-input v-model="addForm.itemPrice" />
      </el-form-item>

      <el-form-item label="上架情况" prop="roleId">
        <el-select v-model="updateForm.isListed" class="m-2" placeholder="请选择" size="large">
          <el-option label="未上架" value="0"></el-option>
          <el-option label="上架" value="1"></el-option>
        </el-select>
      </el-form-item>


    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleAddConfirm()">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>





</template>

<style scoped>
.header{
  display: flex;
  align-items: center;
  background-color: #0d47a1;
  color: white;
}
</style>
