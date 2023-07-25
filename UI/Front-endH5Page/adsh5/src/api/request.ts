import { Request } from "@/utils/request";

export function getlist() {
    return Request.axiosInstance({
        url:'/service-core/rest/productCard/display',
        method:'get',
       // data:params
    })
}

export function sendemail(params:any) {
    return Request.axiosInstance({
        url:'/sendemail',
        method:'post',
        data:params
    })
}

// export function getSecurityCode () {
//     return Request.axiosInstance({
//         url:'/app/clientInfo/securityCode ',
//         method:'get',
//        // data:params
//     })
// }
