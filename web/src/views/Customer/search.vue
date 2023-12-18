<script setup>
import {useRoute} from "vue-router";
import {onMounted, reactive, ref, watch} from "vue";
import axios from "axios";
import base_url from "@/store/constants";
import CustomerHeader from "@/views/Customer/component/CustomerHeader.vue";
import Cfooter from "@/views/Customer/component/Cfooter.vue";

const route = useRoute();

const tData = reactive({
  keyword: '',
})


let searchResult = ref([])
onMounted(() => {
  search()
})

// 监听query参数
watch(() => route.query, (newPath, oldPath) => {
  search()
}, {immediate: false});


const search = async () => {
  tData.keyword = route.query.keyword.trim()
  var res = await axios.get(base_url + `/sys/shop/search?keyword=${tData.keyword}`)
  searchResult.value = res.data
}


</script>

<template>


  <div class="common-layout">
    <el-container>
      <el-header class="header">

        <CustomerHeader></CustomerHeader>

      </el-header>


      <el-main style="height: 100vh">

        <div class="result-container">
          <el-card v-for="(item, index) in searchResult" :key="index" :body-style="{ padding: '0px' }" class="card">
            <img :src="item.shopImg" class="image" style="width: 200px; height: auto;" />
            <div style="padding: 14px">
              <span>{{ item.shopName }}</span>
              <div class="bottom">
                <el-button text class="button">Operating</el-button>
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

.result-container {
  display: flex;
  flex-wrap: nowrap; /* 设置为 nowrap 以确保在一行上显示 */
  overflow-x: auto; /* 如果内容过多，允许水平滚动 */
}

.card {
  margin-right: 40px; /* 为卡片之间添加间距 */
}

.image {
  width: 100%;
  height: auto;
}

.header{
  display: flex;
  align-items: center;
  background-color: #0d47a1;
  color: white;
}

</style>
