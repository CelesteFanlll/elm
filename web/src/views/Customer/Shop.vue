<script setup>

import {ElMessage} from "element-plus";

const scrollToCategory = (uniqueDescription) => {
  const targetElement = document.getElementById(uniqueDescription);

  // 确保目标元素存在
  if (targetElement) {
    targetElement.scrollIntoView({ behavior: 'smooth' });
  } else {
    console.error(`Element with id ${uniqueDescription} not found.`);
  }

}

import {useRoute} from "vue-router";
import {computed, onMounted, reactive, ref, watch, watchEffect} from "vue";
import axios from "axios";
import base_url from "@/store/constants";
import {useCustomerStore} from "@/store/useCustomerStore"
import router from "@/router";

const customerStore = useCustomerStore();
const route = useRoute();

const tData = reactive({
  keyword: '',
})

const shopData = ref([])
let searchResult = ref([])
onMounted(() => {
  getListitems()
  getShop()

})

// 监听query参数
watch(() => route.query, (newPath, oldPath) => {
  getListitems()
}, {immediate: false});

let OrderData = reactive([])

const getListitems = async () => {
  tData.keyword = route.query.keyword.trim()
  var res = await axios.get(base_url + `/sys/item/getItem?id=${tData.keyword}`)
  searchResult.value = res.data
  OrderData = searchResult.value
  OrderData.forEach(item => {
    item.value = 0;
  });

  console.log(searchResult)
  console.log(OrderData)
}

const getShop = async () => {
  var res = await axios.get(base_url + `/sys/shop/get?id=${tData.keyword}`)
  console.log(res.data)
  shopData.value = res.data
}

const uniqueDescriptions = computed(() => {
  const uniqueSet = new Set();
  searchResult.value.forEach(item => {
    uniqueSet.add(item.itemDescripition);
  });
  return Array.from(uniqueSet);

})

let totalMoney = ref(0)

const handleInputChange = (item) => {
  console.log(item.itemName + '的数量改为' + item.value)
  console.log(OrderData)
  totalMoney.value = computeMoney.value
}


const computeMoney = computed(() => {
  let total = 0;
  OrderData.forEach(item => {
    total += item.itemPrice * item.value
  });
  console.log(total)
  return total;
})

const COrder = reactive({
  customerName: customerStore.customer.customerName,
  orderStatus: 0,
  totalAmount: totalMoney.value + shopData.delPrice,
  shopId: parseInt(route.query.keyword.trim())
})

watchEffect(() => {
  COrder.totalAmount = totalMoney.value;
})

const OrderId = ref(0)
const Listitem = []
const createOrder = async () => {
  try {
    const response = await axios.post(base_url + '/sys/order/getid', COrder);
    OrderId.value = response.data;
    console.log(OrderId.value);

    // 当订单ID获取成功后，再进行列表项的创建
    for (let i = 0; i < OrderData.length; i++) {
      // 创建一个新的Listitem对象
      const newListitem = {
        orderId: parseInt(OrderId.value),
        itemId: parseInt(OrderData[i].itemId),
        quantity: parseInt(OrderData[i].value)
      };

      // 将新的Listitem对象添加到数组中
      Listitem.push(newListitem);
    }

    console.log(Listitem);
    await axios.post(base_url + '/sys/listItem/createListitem', Listitem)

    ElMessage({
      message: '添加成功，即将跳转查看',
      type: 'success',
    })

    setTimeout(function () {
      router.push(`/OrderDetail?keyword=${OrderId.value}`)
    }, 2000);


  } catch (error) {
    console.error(error);
  }
}

</script>

<template>
  <div class="shadow-div">
    <el-row :gutter="20">
      <el-col :span="16" style="display: flex; align-items: center; font-size: 25px;">
        <div style="margin-left: 20px;font-size:25px ">{{shopData.shopName}}</div>

      </el-col>
      <el-col :span="8">
        <img :src="shopData.shopImg" alt="" style="height: 50px;width: 50px">
      </el-col>
    </el-row>

    <div style="margin-left: 20px;font-size:20px;margin-top: 20px">{{shopData.shopDes}}</div>
    <div style="margin-left: 20px;font-size:15px;margin-top: 20px">店铺地址 : {{shopData.shopLoc}}</div>

    <div style="height: 30px"></div>

  </div>

  <div class="shadow2">
    <el-row>
      <el-col :span="24" style="margin-left: 20px;align-items: center;display: flex">
        <svg t="1702306629852" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4294" width="32" height="32"><path d="M859.1872 638.7968H189.9776l-21.248-69.4528h711.7056z" fill="#8FC5E1" p-id="4295"></path><path d="M806.656 503.3472c0-150.4-121.9328-272.3072-272.3072-272.3072s-272.3072 121.9328-272.3072 272.3072H806.656z" fill="#FFDE77" p-id="4296"></path><path d="M524.5696 230.528s183.3472 99.6864 184.9856 272.8192h92.7488s0.2816-246.016-277.7344-272.8192z" fill="#FFA869" p-id="4297"></path><path d="M524.5696 187.136m-43.392 0a43.392 43.392 0 1 0 86.784 0 43.392 43.392 0 1 0-86.784 0Z" fill="#FE697D" p-id="4298"></path><path d="M176.3328 697.088l88.2176-28.1856v167.6032l-88.2176 49.3824z" fill="#8FC5E1" p-id="4299"></path><path d="M402.7904 638.7968l-82.9952 33.6384v139.9552l55.3216-21.9648s162.7392 38.2464 231.9104-3.2512 133.1968-91.648 145.3824-106.4448c12.2112-14.7968 8.8064-37.1712-9.4976-41.9328-12.928-3.3536-63.4624 14.4128-108.2112 41.9328-44.7488 27.52-48.3584 38.0928-112.0768 38.0928s-68.0448-3.2512-68.0448-16.2816 81.92-25.4976 98.176-29.2864c16.2816-3.7888 22.784-23.8592 10.8544-34.4576h-160.8192z" fill="#FABE75" p-id="4300"></path><path d="M514.56 49.3056C258.0224 49.3056 49.3056 258.0224 49.3056 514.56c0 62.7968 12.3136 123.7248 36.5568 181.0944a17.93536 17.93536 0 0 0 23.4752 9.5232c9.1136-3.8656 13.3888-14.3616 9.5232-23.4752-22.3488-52.9152-33.7152-109.1584-33.7152-167.1424 0-236.7744 192.64-429.4144 429.4144-429.4144S943.9744 277.7856 943.9744 514.56 751.3344 943.9744 514.56 943.9744c-71.808 0-142.848-18.0736-205.4656-52.2496-8.6784-4.7616-19.584-1.536-24.32 7.1424-4.736 8.6784-1.536 19.584 7.1424 24.32 67.8656 37.0432 144.8448 56.6272 222.6432 56.6272 256.5376 0 465.2544-208.7168 465.2544-465.2544S771.0976 49.3056 514.56 49.3056z" fill="#474E54" p-id="4301"></path><path d="M311.2704 656.6912a17.86112 17.86112 0 0 0-9.3952 15.7184v139.9552c0 5.9392 2.944 11.4944 7.8592 14.8224a17.91744 17.91744 0 0 0 16.6656 1.8432l50.2016-19.9424c30.336 6.5536 171.8784 34.0736 239.6416-6.5792 65.7152-39.424 134.6048-91.7248 149.9904-110.4128 8.3712-10.1376 12.288-23.0912 11.0592-35.4048h81.8688c7.8848 0 14.8224-5.1456 17.1264-12.672l21.248-69.4272c1.664-5.4272 0.64-11.3408-2.7392-15.8976a17.96864 17.96864 0 0 0-14.4128-7.2704H168.7296c-5.6832 0-11.0336 2.688-14.4128 7.2704a17.98912 17.98912 0 0 0-2.7392 15.8976l21.248 69.4272c2.304 7.5264 9.2672 12.672 17.1264 12.672h53.8624l-72.96 23.3216c-7.424 2.3808-12.4672 9.2672-12.4672 17.0752v188.7744c0 6.3488 3.3792 12.2368 8.8576 15.4624a17.92768 17.92768 0 0 0 17.8176 0.1792l88.2432-49.3568a17.92 17.92 0 0 0 9.1648-15.6416v-167.6288c0-4.5824-1.7664-8.9088-4.8128-12.1856h33.6128z m-64.64 169.3184l-52.4032 29.312v-145.1264l52.4032-16.7424v132.5568z m494.72-167.2448c0.8448 2.5856 0.3072 6.8352-2.7648 10.5472-10.368 12.5696-71.68 61.0048-140.8 102.4768-51.5328 30.9248-175.2576 11.3408-218.5984 1.1776-1.3568-0.3072-2.7136-0.4864-4.096-0.4864-2.2528 0-4.5056 0.4352-6.6048 1.2544l-30.7968 12.2368v-101.4784l68.5824-27.776h138.0352c-2.7904 0.5632-6.3488 1.2288-10.2144 1.9712-73.1136 13.8496-97.4336 22.144-97.4336 43.8528 0 34.2016 30.5408 34.2016 85.9648 34.2016 56.2432 0 71.1936-8.2944 102.5536-28.6976 5.4784-3.5584 11.6992-7.6032 18.8928-12.0576 39.2448-24.1152 76.5184-36.8896 90.6752-39.2704H739.84c0.7424 0.4352 1.2032 1.0752 1.5104 2.048z m-100.992-2.0736c-5.4016 2.9696-10.4704 5.9136-15.0528 8.7296-7.6032 4.6848-14.0288 8.8576-19.6864 12.544-27.008 17.5872-35.1488 22.8864-82.9952 22.8864-3.6096 0-9.472 0-16.0512-0.0768 13.568-2.9952 27.136-5.5808 34.2016-6.912 7.04-1.3312 12.6208-2.3808 16.0768-3.2 14.6176-3.4048 25.984-14.5152 29.6448-28.9536 0.4352-1.6896 0.7424-3.3536 0.9216-5.0176h52.9408zM192.9472 587.264h663.2704l-10.2912 33.5872h-101.6576c-3.0464-0.384-6.5536-0.384-10.3936 0H203.2384l-10.2912-33.5872zM244.096 503.3472c0 9.9072 8.0128 17.92 17.92 17.92H806.656c9.9072 0 17.92-8.0128 17.92-17.92 0-145.0496-106.9568-265.6-246.1696-286.8992 4.7616-8.704 7.4752-18.7136 7.4752-29.312 0-33.8176-27.52-61.312-61.312-61.312S463.2576 153.344 463.2576 187.136c0 11.9808 3.456 23.1424 9.4208 32.5888-130.5088 28.3648-228.5824 144.7424-228.5824 283.6224zM524.5696 161.664c14.0544 0 25.472 11.4176 25.472 25.472s-11.4176 25.472-25.472 25.472-25.472-11.4176-25.472-25.472 11.4432-25.472 25.472-25.472z m9.7792 87.296c134.2464 0 244.5312 104.5248 253.7728 236.4672H280.576c9.216-131.9424 119.5264-236.4672 253.7728-236.4672z" fill="#474E54" p-id="4302"></path><path d="M371.8656 413.824c-2.6624 0-5.3504-0.5888-7.8848-1.8432-8.8832-4.352-12.544-15.104-8.192-23.9872 23.7312-48.3328 69.0176-80.3072 70.912-81.664a17.93792 17.93792 0 0 1 24.96 4.4288c5.6576 8.1152 3.6864 19.2768-4.4032 24.9344-0.384 0.2816-39.7056 28.2112-59.2896 68.0704a17.89952 17.89952 0 0 1-16.1024 10.0608z" fill="#F7F8F8" p-id="4303"></path></svg>
        <div style="display: flex;align-items: center;margin-left: 10px">
          点单
        </div></el-col>
    </el-row>
    <div class="common-layout">
      <el-container>
        <el-aside width="150px" style="margin-top: 20px;background-color: #e9e9eb">

          <div v-for="(uniqueDescription, index) in uniqueDescriptions" :key="index" class="centered-button" @click="() => scrollToCategory(uniqueDescription)">
            {{ uniqueDescription }}
          </div>


        </el-aside>
        <el-main>

            <div v-for="(uniqueDescription, index) in uniqueDescriptions" :key="index" :id=  uniqueDescription >
              <p>| {{ uniqueDescription }}</p>
              <div v-for="(item,index) in OrderData" :key="index" style="margin-bottom: 20px">
                <el-row v-if="item.itemDescripition === uniqueDescription && item.isListed === 1">
                  <el-col :span="2"><img :src="item.itemImg" alt="" style="height: 100px;width: 100px"></el-col>
                  <el-col :span="2">
                    <div style="display: flex; flex-direction: column; align-items: center; justify-content: center;">
                      <div>{{ item.itemName }}</div>
                      <div style="margin-top: 40px">￥{{ item.itemPrice }}</div>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <el-input-number style="margin-left: 650px;margin-top: 20px" v-model="item.value" :min="0" :max="10" @change="handleInputChange(item)" />
                  </el-col>
                </el-row>
              </div>
            </div>


          <el-affix position="bottom" :offset="50">

            <div class="rounded-bar" style="overflow: visible; display: flex; justify-content: space-between; align-items: center;">

              <div class="circle" style="position: absolute; top: -60px; left: 100px;">
                <svg style="transform: translate(30%, 30%);" t="1702395701508" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4601" width="64" height="64"><path d="M928.922574 99.438757l-67.214844 0c-15.159258 0-28.122501 10.878768-30.755468 25.803689l-15.35164 86.96977L222.034932 212.212216c-17.253966 0-31.233352 13.979386-31.233352 31.233352s13.979386 31.233352 31.233352 31.233352l582.540589 0-49.773613 281.97407c-8.103562 37.283138-29.688159 81.4388-86.410022 81.4388l-375.389646 0c-32.819476 0-57.504692-5.947456-76.324316-82.851986-0.11154-0.477884-0.243547-0.945535-0.376577-1.413186l-90.304725-306.293965c-4.89038-16.531512-22.23542-25.986861-38.797631-21.12718-16.541745 4.880147-25.997094 22.245653-21.12718 38.797631l90.101087 305.603233c15.830547 64.286142 44.582381 129.752156 136.829342 129.752156l375.389646 0c74.79959 0 129.97626-49.107441 147.605779-131.348514 0.081864-0.376577 0.152473-0.741897 0.213871-1.118474l71.697949-406.186046 41.014112 0c17.253966 0 31.233352-13.979386 31.233352-31.233352S946.17654 99.438757 928.922574 99.438757z" fill="#999999" p-id="4602"></path><path d="M361.610828 333.028862c-17.253966 0-31.233352 13.979386-31.233352 31.233352l0 30.470989c0 17.253966 13.979386 31.233352 31.233352 31.233352 17.253966 0 31.233352-13.979386 31.233352-31.233352l0-30.470989C392.84418 347.008248 378.86377 333.028862 361.610828 333.028862z" fill="#999999" p-id="4603"></path><path d="M605.376691 333.028862c-17.253966 0-31.233352 13.979386-31.233352 31.233352l0 30.470989c0 17.253966 13.979386 31.233352 31.233352 31.233352 17.253966 0 31.233352-13.979386 31.233352-31.233352l0-30.470989C636.610042 347.008248 622.630656 333.028862 605.376691 333.028862z" fill="#999999" p-id="4604"></path><path d="M560.28982 474.086505c-14.539134-9.292644-33.845853-5.083785-43.149752 9.455349-0.121773 0.193405-13.379729 19.449981-33.968649 19.449981-20.008706 0-32.453133-18.127869-33.287127-19.378349-9.17087-14.407128-28.274974-18.809391-42.824341-9.750061-14.650675 9.099239-19.155269 28.355815-10.044774 43.00649 11.214413 18.047028 41.980113 48.588625 86.156242 48.588625 43.952025 0 75.094302-30.308283 86.572728-48.222281C579.048045 502.717589 574.818721 483.389382 560.28982 474.086505z" fill="#999999" p-id="4605"></path><path d="M296.093649 739.519854c-51.668777 0-93.700055 42.031279-93.700055 93.700055 0 51.668777 42.031279 93.700055 93.700055 93.700055 51.668777 0 93.700055-42.031279 93.700055-93.700055C389.793704 781.550109 347.762425 739.519854 296.093649 739.519854zM296.093649 864.453261c-17.223267 0-31.233352-14.010085-31.233352-31.233352 0-17.223267 14.010085-31.233352 31.233352-31.233352s31.233352 14.010085 31.233352 31.233352C327.327 850.443176 313.316915 864.453261 296.093649 864.453261z" fill="#999999" p-id="4606"></path><path d="M670.89387 739.519854c-51.668777 0-93.700055 42.031279-93.700055 93.700055 0 51.668777 42.031279 93.700055 93.700055 93.700055 51.668777 0 93.700055-42.031279 93.700055-93.700055C764.593925 781.550109 722.56367 739.519854 670.89387 739.519854zM670.89387 864.453261c-17.223267 0-31.233352-14.010085-31.233352-31.233352 0-17.223267 14.010085-31.233352 31.233352-31.233352s31.233352 14.010085 31.233352 31.233352C702.127222 850.443176 688.117137 864.453261 670.89387 864.453261z" fill="#999999" p-id="4607"></path></svg>
              </div>

              <div style="color: #e9e9eb; font-size: 40px;margin-left: 250px">￥ {{totalMoney + shopData.delPrice}}</div>

              <div>
                <div v-if="totalMoney >= shopData.startPrice ">
                  <el-button type="warning" style="height: 60px;margin-right: 20px;width: 200px" @click="createOrder">下单</el-button>
                </div>
                <div v-else>
                  <p style="color: white;margin-right: 40px;font-size: 20px">起送费：{{shopData.startPrice}}</p>
                </div>
              </div>

            </div>


          </el-affix>

        </el-main>

      </el-container>
    </div>

  </div>

</template>

<style scoped>
.shadow-div {
  margin-left: 200px;
  margin-right: 200px;
  margin-top: 50px;
  height: 150px; /* 设置高度 */
  background-color: #fff; /* 设置背景颜色 */
  border-radius: 8px; /* 设置圆角 */
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2); /* 添加阴影效果 */
}

.shadow2{
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  background-color: #fff; /* 设置背景颜色 */
  border-radius: 8px; /* 设置圆角 */
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2); /* 添加阴影效果 */
}

.centered-button {
  display: block; /* 让按钮独占一行 */
  margin-bottom: 8px; /* 可以根据需要添加间距 */
  width: 100%; /* 按钮宽度100% */
  text-align: center; /* 文字居中 */
  display: flex; /* 使用flex布局 */
  justify-content: center; /* 在主轴上居中 */
  align-items: center; /* 在交叉轴上居中 */
  height: 40px; /* 按钮高度，可以根据需要调整 */
}

.rounded-bar {
  position: relative;
  background-color: black;
  height: 60px; /* 设置长条的高度 */
  width: 100%; /* 设置长条的宽度 */
  border-radius: 20px; /* 设置边角的圆角大小 */
}

.circle {

  width: 100px;
  height: 100px;
  background-color: #007bff;
  border-radius: 50%; /* 设置为50%使div变成圆形 */
}
</style>
