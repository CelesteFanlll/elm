<script setup>
import {useCustomerStore} from "@/store/useCustomerStore"
import {onMounted, ref} from "vue";
import axios from "axios";
import base_url from "@/store/constants";
import {ElMessage} from "element-plus";
import router from "@/router";
const customerStore = useCustomerStore(); // 获取 useCustomerStore 的实例

onMounted(() => {

  getAllOrder()

})

const AllOrder = ref([])
const getAllOrder = async ()=> {

  var res = await axios.post(base_url + "/sys/order/search", customerStore.customer)

  let data;
  data = res.data.data
  AllOrder.value = data


}

const dialogVisible = ref(false)
const DVisible = ref(false)
const DVisible2 = ref(false)
let CurrentOrder = ref([])
const startPayment = (corder)=>{
  dialogVisible.value = true
  CurrentOrder = corder
}
const handlePayment = async ()=>{
  ElMessage({
    message: '支付成功',
    type: 'success',
  })

  await axios.post(base_url+'/sys/order/pay',CurrentOrder)
  dialogVisible.value = false
  await getAllOrder()
}

const toOrderDetail = (corder)=>{

  let text = router.resolve({ name: 'OrderDetail', query: { keyword: corder.orderId } });
  window.open(text.href, '_blank');

}

const Refund = (corder) =>{
  DVisible.value = true;
  CurrentOrder = corder

}

const handleRefund =async ()=>{
  ElMessage({
    message: '退款成功',
    type: 'success',
  })
  await axios.post(base_url+'/sys/order/refund',CurrentOrder)
  DVisible.value = false
  await getAllOrder()


}

const CancelOrder =(corder) =>{

  DVisible2.value = true
  CurrentOrder = corder
  console.log(CurrentOrder)
}

const handleCancelOrder = async ()=>{
  ElMessage({
    message: '取消成功',
    type: 'success',
  })
  console.log(parseInt(CurrentOrder.orderId))
  await axios.delete(base_url+`/sys/order/delete?id=${parseInt(CurrentOrder.orderId)}`)
  DVisible2.value = false
  await getAllOrder()

}



</script>

<template>
  <div class="card" v-for="(item, index) in AllOrder" :key="index" @click = "toOrderDetail(item.corder)">
    <div class="card-content">
      <el-row style="height: 20px;margin-bottom: 30px">
        <el-col :span="3">
          <img :src="item.shop.shopImg" style="height: 40px;width: auto">
        </el-col>
        <el-col :span="6" style="display: flex; align-items: center;">{{item.shop.shopName}} ></el-col>
      </el-row>
      <div style="display: flex; align-items: center;">
        <p style="margin-right:20px ">总金额: {{item.corder.totalAmount}}￥</p>
        <div>
          <div v-if="item.corder.orderStatus === 0">
            <el-button type="primary" @click.stop="startPayment(item.corder)">去支付</el-button>
            <el-button  type="danger" @click.stop="CancelOrder(item.corder)">取消订单</el-button>
          </div>

          <div v-else-if="item.corder.orderStatus === 1">
            <el-button  type="success" disabled>已支付</el-button>
            <el-button  type="danger" @click.stop="Refund(item.corder)">退款</el-button>
          </div>
          <el-button v-else-if="item.corder.orderStatus === 2" type="danger" disabled>已退款</el-button></div>
      </div>


    </div>
  </div>


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


  <el-dialog
      v-model="DVisible"
      title="退款界面"
      width="30%"
  >
    <span>正在退款中，点击确定进行退款</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="DVisible = false">取消</el-button>
        <el-button type="primary" @click="handleRefund">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog
      v-model="DVisible2"
      title="取消订单界面"
      width="30%"
  >
    <span>正在取消中，点击确定取消该订单</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="DVisible = false">取消</el-button>
        <el-button type="primary" @click="handleCancelOrder">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>


</template>

<style scoped>
.card {
  margin-top: 20px;
  margin-left: 30px;
  width: 600px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
  transition: transform 0.3s ease-in-out;
}

.card:hover {
  transform: scale(1.05);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}






</style>
