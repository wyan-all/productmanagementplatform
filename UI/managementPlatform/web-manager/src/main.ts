import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
//import Mock from '@/mock'
import axios from 'axios'
import {Request} from '@/utils'
//import { ElButton,ElPopover } from 'element-plus'
import 'element-plus/dist/index.css'
import '@/css/global.css'

createApp(App)
.use(router,  axios as any, Request.init(),
//Mock,
)
.mount('#app')
