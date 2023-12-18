<script setup>
import Cfooter from "@/views/Customer/component/Cfooter.vue";
import {onMounted, reactive, ref} from "vue";
import {useRoute} from "vue-router";
import axios from "axios";
import base_url from "@/store/constants";
import router from "@/router";

onMounted(() => {
  getShop()

})
const route = useRoute();
const tData = reactive({
  keyword: '',
})
let searchResult = ref([])

const getShop =async ()=>{
  tData.keyword = route.query.keyword.trim()
  var res = await axios.get(base_url+`/sys/shop/getByTag?tag=${tData.keyword}`)
  searchResult.value = res.data
  console.log(searchResult)

}

const toShop = (item) =>{

  let text = router.resolve({ name: 'Shop', query: { keyword: item.shopId } });
  window.open(text.href, '_blank');

}

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">商家列表</el-header>
      <el-main style="height: 100vh">


        <div class="result-container">
        <el-card v-for="(item, index) in searchResult" :key="index" :body-style="{ padding: '0px' }" class="card" @click = "toShop(item)">

          <div class="image-container">
            <img :src="item.shopImg" class="image" style="width: 200px; height: auto;" />
          </div>

          <div style="padding: 14px" class="text-content">
            <span>{{ item.shopName }}</span>
            <div style="display: flex;padding-top: 5px">
              <div style="padding-right: 10px;font-size: 10px">起送费：{{item.startPrice}}￥</div>
              <div style="padding-right: 10px;font-size: 10px">配送费：{{item.delPrice}}￥</div>
            </div>
          </div>


        </el-card>

        </div>




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
