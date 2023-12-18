import {defineStore} from 'pinia'
import {ref} from "vue";
export const useCustomerStore = defineStore("user",()=>{

    const customer = ref({})

    const changeCustomer = (value)=>{
        customer.value = value

    }
    return{
        customer,
        changeCustomer
    }

},{
    // 刷新后不会使登录信息消失
    persist:true
})
