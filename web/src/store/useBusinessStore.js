import {defineStore} from 'pinia'
import {ref} from "vue";

export const useBusinessStore = defineStore("user", () => {

    const business = ref({})
    let businessId = ref(0)

    const changeBusiness = (value) => {
        business.value = value
    }


    return {
        business,
        businessId,
        changeBusiness
    }

}, {
    // 刷新后不会使登录信息消失
    persist: true
})
