import { createApp } from 'vue'
import App from '@/App.vue'
import { router } from '@/router/index'
import axios from 'axios'
import {Request} from '@/utils/request'
//import Mock from '@/mock/mock'

createApp(App)
.use(router, axios as any, Request.init(),
//Mock,
)

.mount('#app')
