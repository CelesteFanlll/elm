<script setup>
import BHeader from "@/views/Business/component/BHeader.vue";
import {useBusinessStore} from "@/store/useBusinessStore";
import {onMounted, ref} from "vue";
import axios from "axios";
import base_url from "@/store/constants";
import Bfooter from "@/views/Business/component/Bfooter.vue";
import router from "@/router";

const businessStore = useBusinessStore();
onMounted(() => {

  getShop()

})
const AllData = ref([])
const getShop =async ()=>{
  console.log(businessStore.business)
  var res = await axios.post(base_url+'/sys/shop/getShop',businessStore.business)
  AllData.value = res.data
  console.log(AllData)
}


const toShop = (item)=>{
  let text = router.resolve({ name: 'Bshop', query: { keyword: item.shopId } });
  window.open(text.href, '_blank');



}

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <BHeader></BHeader>
      </el-header>
      <el-main style="height: 100vh">

        <div class="result-container">
          <el-card v-for="(item, index) in AllData" :key="index" :body-style="{ padding: '0px' }" class="card" @click = "toShop(item)">

            <div class="image-container">
              <img :src="item.shopImg" class="image" style="width: 200px; height: auto;" />
            </div>

            <div style="padding: 14px" class="text-content">
              <span>{{ item.shopName }}</span>
              <div style="display: flex;padding-top: 5px">
                <div style="padding-right: 10px;font-size: 10px">起送费：0￥</div>
                <div style="padding-right: 10px;font-size: 10px">配送费：0￥</div>
              </div>
            </div>


          </el-card>
        </div>



      </el-main>
      <el-footer>
        <Bfooter></Bfooter>
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

.result-container {
  display: flex;
  flex-wrap: wrap; /* 允许元素换行 */
}

.card {
  height: 280px;
  margin-right: 30px; /* 为卡片之间添加间距 */
  margin-bottom: 30px;
}


.image {
  width: 100%;
  height: auto;
}



</style>

