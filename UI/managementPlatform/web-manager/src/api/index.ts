import { Request } from "@/utils";

export function login(authCode:string,params:any) {
    return Request.axiosInstance({
        url:`userInfo/login?authCode=${authCode}`,
        method:'post',
        data:params,
        headers: {'Content-Type': 'application/json; charset=UTF-8'
        }, 
    })
}

export function getInfo() {
    return Request.axiosInstance({
        url:'/productCard/display',
        method:'get',
    })
}

export function addProduct(params:any) {
    return Request.axiosInstance({
        url:'/productCard/add',
        method:'POST',
        data:params,
        headers: {'Content-Type': 'application/json; charset=UTF-8'
        }, 
    })
}
export function editorProduct(params:any) {
    return Request.axiosInstance({
        url:'/productInfo/update',
        method:'POST',
        data:params,
        headers: {'Content-Type': 'application/json; charset=UTF-8'
        }, 
    })
}

export function deleteProduct(params:any) {
    return Request.axiosInstance({
        url:`/productCard/delete/${params}`,
        method:'DELETE',
        headers: {'Content-Type': 'application/json; charset=UTF-8'
        }, 
        //设置请求头
    //     headers: {'X-Requested-With': 'XMLHttpRequest',
    //     'responseType': 'arraybuffer',
    //     'Content-Type':'multipart/form-data'
    // },    
    })
}

export function addIcon(id:number,params:any) {
    return Request.axiosInstance({
        url:`productCard/addIcon?id=${id}`,
        method:'POST',
        data:params,
        //设置请求头
        // headers: {
        //  'Content-Type':'multipart/form-data'
        // },    
        headers: {'Content-Type': 'application/json; charset=UTF-8'
        }, 
    })
}
