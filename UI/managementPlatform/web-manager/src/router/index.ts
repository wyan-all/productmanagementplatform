import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
//import Home from '../views/Home.vue'
import Login from '@/views/Login.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/home',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Home.vue')
  },
  {
    path: '/addProduct',
    name: 'addProduct',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Addproduct.vue')
  },
  {
    path: '/editorProduct',
    name: 'editorProduct',
    component: () => import(/* webpackChunkName: "about" */ '@/views/EditorProduct.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
