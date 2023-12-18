<script setup>

import {useRoute} from "vue-router";
import {computed, onMounted, reactive, ref, watch} from "vue";
import axios from "axios";
import base_url from "@/store/constants";
import Cfooter from "@/views/Customer/component/Cfooter.vue";
import {useCustomerStore} from "@/store/useCustomerStore"
import {ElMessage} from "element-plus";

const customerStore = useCustomerStore(); // 获取 useCustomerStore 的实例

const route = useRoute();

const tData = reactive({
  keyword: '',
})


let searchResult = ref([])
onMounted(() => {
  getListitems()

})

// 监听query参数
watch(() => route.query, (newPath, oldPath) => {
  getListitems()
}, {immediate: false});

let delPrice =ref(0)
let shopName = ref()
let loc = ref()
let tele = ref()
let orderStatus = ref()
const dialogVisible = ref(false)
let CurrentOrder = ref([])

const startPayment = ()=>{
  dialogVisible.value = true

}
const getListitems = async() => {
  tData.keyword = route.query.keyword.trim()
  var res = await axios.get(base_url+`/sys/listItem/getOrderDetail?keyword=${tData.keyword}`)
  searchResult.value = res.data
  console.log(searchResult.value)

  delPrice = searchResult.value[0].shop.delPrice
  shopName = searchResult.value[0].shop.shopName
  orderStatus = searchResult.value[0].corder.orderStatus
  CurrentOrder = searchResult.value[0].corder

  // 获取地址
  var data = await axios.post(base_url+`/sys/customer/searchLoc?name=${customerStore.customer.customerName}`)
  loc.value = data.data


//   获取电话
  var data1 = await axios.get(base_url+`/sys/customer/getTele?name=${customerStore.customer.customerName}`)
  tele.value = data1.data
}


const totalAmount = ref(0);

// 使用 computed 计算总金额
const calculateTotalAmount = computed(() => {

  return searchResult.value.reduce((total, item) => {
    return total + item.item.itemPrice * item.listItem.quantity ;
  }, 0);
});

// 监听计算总金额的变化
watch(calculateTotalAmount, (newValue) => {
  totalAmount.value = newValue;
});

const handlePayment = async ()=>{
  ElMessage({
    message: '支付成功',
    type: 'success',
  })

  await axios.post(base_url+'/sys/order/pay',CurrentOrder)
  dialogVisible.value = false
  await getListitems()
}


</script>

<template>

  <div class="common-layout">
    <el-container>
      <el-header class="header">
        订单详情
      </el-header>



      <el-main style="height: 100vh">

        <div style="background-color: #007bff">
          <div style="margin-left: 10px;color: white;padding-top: 10px">订单配送至:</div>

          <div style="margin-top: 10px; color: white; font-size: 25px;">
            <div style="float: left;">{{loc}}</div>
            <div style="text-align: right;"> > </div>
          </div>

          <div style="margin-left: 10px;color: white;margin-top: 10px;padding-bottom: 10px"> 用户{{customerStore.customer.customerName}} {{tele}}</div>

        </div>

        <div style="height: 20px"></div>

        <div style="margin-bottom: 20px;font-size: large">{{shopName}}</div>

        <hr>

        <div style="height: 20px"></div>

        <div v-for="(item, index) in searchResult" :key="index" style="display: flex; margin-bottom: 20px;" >

          <img :src="item.item.itemImg" style="height: 100px; width: 100px;" v-if="item.listItem.quantity != 0">
          <div class="content" style="flex: 1;" v-if="item.listItem.quantity != 0">

            <div class="row" style="display: flex; justify-content: space-between; align-items: center;">
              <span>{{item.item.itemName}}</span>
              <span style="margin-left: 20px;">￥{{item.item.itemPrice}}</span>
            </div>
            <div class="row">
              {{item.listItem.quantity}}份
            </div>

          </div>
        </div>

        <div style="position: relative;">
          <div style="position: absolute; right: 0;">配送费 ：￥{{delPrice}}</div>
        </div>




        <hr>
        <div style="height: 30px"></div>
        <span class="right-align">总计￥{{ totalAmount + delPrice}}</span>



        <el-button v-if="orderStatus === 0" style="width: 100%;margin-top: 40px" type="success" @click="startPayment">支付</el-button>
        <el-button v-if="orderStatus === 1" style="width: 100%;margin-top: 40px" type="success" disabled>已支付</el-button>

      </el-main>
      <el-footer><Cfooter></Cfooter></el-footer>
    </el-container>
  </div>

<!--支付界面-->
  <el-dialog
      v-model="dialogVisible"
      title="支付界面"
      width="30%"
  >
    <span>正在支付中，点击确定进行支付</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">不支付</el-button>
        <el-button type="primary" @click="handlePayment">
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

.content {
  display: grid; /* 内容区域也使用 Grid 布局 */
  grid-template-rows: 1fr 1fr; /* 两行等高 */
}

.row {
  margin-left: 20px;
}

.right-align {
  float: right;
}
</style>
