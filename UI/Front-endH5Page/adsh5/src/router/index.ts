import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router"
import PageLayout from '@/components/PageLayout.vue'
import CustomForm  from '@/components/CustomForm.vue';
import MessageFeedback  from '@/components/MessageFeedback.vue';

//const home=()=>import('@/components/PageLayout.vue')
//const CustomForm=()=>import('@/components/CustomForm.vue')
//const MessageFeedback=()=>import('@/components/MessageFeedback.vue')
//定义routes路由的集合，数组类型
const routes:Array<RouteRecordRaw>=[
    //单个路由均为对象类型，path代表的是路径，component代表组件
    {
        path:'/', 
        redirect:'/Home'
    },
    {
        path:'/Home',
        name:'home',
        component:PageLayout,
    },
    {
        path:'/CustomForm', 
        name:'CustomForm', 
        component:CustomForm
    },
    {
        path:'/MessageFeedback', 
        name:'MessageFeedback', 
        component:MessageFeedback}

    
]



export const router=createRouter({
    history: createWebHashHistory(),
    routes:routes
})