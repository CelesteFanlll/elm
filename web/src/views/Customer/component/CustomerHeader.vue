<script setup>

import router from "@/router";
import {reactive, ref} from "vue";


const keywordRef = ref()

const data = reactive({
  searchText: '请输入关键字',
  isFirstSearch: true,
});

const search = () => {

  const keyword = keywordRef.value.value;
  data.searchText = keyword;

  // 确保你传递给 resolve 的是一个字符串
  let text = router.resolve({ name: 'search', query: { keyword: keyword } });

  // 打开新窗口
  window.open(text.href, '_blank');
}

</script>

<template>

  <svg style="padding-left: 20px;padding-right: 20px" t="1701771899348" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7657" width="32" height="32"><path d="M966.4 924.8l-230.4-227.2c60.8-67.2 96-156.8 96-256 0-217.6-176-390.4-390.4-390.4-217.6 0-390.4 176-390.4 390.4 0 217.6 176 390.4 390.4 390.4 99.2 0 188.8-35.2 256-96l230.4 227.2c9.6 9.6 28.8 9.6 38.4 0C979.2 950.4 979.2 934.4 966.4 924.8zM102.4 441.6c0-185.6 150.4-339.2 339.2-339.2s339.2 150.4 339.2 339.2c0 89.6-35.2 172.8-92.8 233.6-3.2 0-3.2 3.2-6.4 3.2-3.2 3.2-3.2 3.2-3.2 6.4-60.8 57.6-144 92.8-233.6 92.8C256 780.8 102.4 627.2 102.4 441.6z" p-id="7658"></path></svg>
  <input
      class="custom-input"
      placeholder="请输入关键词"
      ref="keywordRef"
      @keyup.enter="search"
  />

</template>

<style scoped>
.custom-input {
  padding: 10px; /* 添加内边距 */
  font-size: 16px; /* 设置字体大小 */
  border: 1px solid #ccc; /* 添加边框 */
  border-radius: 5px; /* 圆角边框 */
  outline: none; /* 去除默认的轮廓样式 */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); /* 添加轻微阴影效果 */
  transition: border-color 0.3s ease; /* 添加过渡效果 */
  padding-left: 20px;
}

.custom-input:focus {
  border-color: #007bff; /* 设置获取焦点时的边框颜色 */
}

</style>
