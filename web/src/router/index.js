import{createRouter,createWebHashHistory} from 'vue-router'
import CustomerLogin from "@/views/Customer/CustomerLogin.vue";
import BusinessLogin from "@/views/Business/BusinessLogin.vue";
import CustomerIndex from "@/views/Customer/CustomerIndex.vue";
import search from "@/views/Customer/search.vue";
import OrderList from "@/views/Customer/OrderList.vue";
import MyInfo from "@/views/Customer/MyInfo.vue";
import AllOrder from "@/views/Customer/OrderListComponent/AllOrder.vue";
import OrderDetail from "@/views/Customer/OrderListComponent/OrderDetail.vue";
import Shop from "@/views/Customer/Shop.vue";
import BusinessIndex from "@/views/Business/BusinessIndex.vue";
import Bsearch from "@/views/Business/component/Bsearch.vue";
import addShop from "@/views/Business/addShop.vue";
import Bshop from "@/views/Business/Bshop.vue";
import error from "@/views/error.vue";
import axios from "axios";
import base_url from "@/store/constants";
import Csignup from "@/views/Customer/Csignup.vue";
import CshopList from "@/views/Customer/CshopList.vue";


const routes = [
    {
        path: '/error',
        name: 'error',
        component: error
    },
    {
        path: '/',
        name: 'CustomerLogin',
        component: CustomerLogin
    },
    {
      path: '/CshopList',
      name: 'CshopList' ,
        component: CshopList

    },
    {
        path: '/Csignup',
        name: 'Csignup',
        component: Csignup
    },
    {
        path:"/CustomerLogin",
        name:"CustomerLogin",
        component:CustomerLogin
    },
    {
        path:"/BusinessLogin",
        name:"BusinessLogin",
        component:BusinessLogin
    },
    {
        path:"/CustomerIndex",
        name: "CustomerIndex",
        component: CustomerIndex
    },
    {
        path: "/search",
        name: "search",
        component: search
    },
    {
        path: "/OrderList",
        name:"OrderList",
        component: OrderList,
        redirect: { name: "AllOrder" },
        children:[
            {
                path:"AllOrder",
                component: AllOrder,
                name: "AllOrder"
            }


        ]
    },
    {
        path: "/MyInfo",
        name:"MyInfo",
        component: MyInfo
    },
    {
        path: "/OrderDetail",
        name:"OrderDetail",
        component: OrderDetail
    },
    {
        path: "/Shop",
        name: "Shop",
        component: Shop
    },

//     商户
    {
        path: '/BusinessIndex',
        name: 'BusinessIndex',
        component: BusinessIndex
    },
    {
        path: "/Bsearch",
        name: "Bsearch",
        component: Bsearch
    },
    {
        path: "/addShop",
        name: "addShop",
        component: addShop
    },
    {
        path: "/Bshop",
        name: "Bshop",
        component: Bshop
    },

]

const router = createRouter({
    history: createWebHashHistory(),//#/login
    routes
})


//路由拦截
router.beforeEach((to, from, next) => {

    if (to.name === "CustomerLogin" || to.name === "BusinessLogin" || to.name === "error" || to.name === "Csignup") {
        next()
    } else {

        if (!localStorage.getItem("token")) {

            next({
                path: "/CustomerLogin"
            })

        } else {

            var res = axios.post(base_url + '/sys/token/check', localStorage.getItem("token"))
            console.log(res)
            if (res) {

                next()

            } else {

                router.push('/error')
            }


        }

    }

})

export default router
