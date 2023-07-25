import axios, {AxiosRequestConfig, AxiosInstance, AxiosResponse} from 'axios';

//const baseURL=window.location.origin
const baseURL=window.location.origin
const url=baseURL//+'/service-core/rest'
//const url=baseURL+'/api'
export class Request {
    public static axiosInstance: AxiosInstance;
    public static init(){
        this.axiosInstance=axios.create({
            baseURL:url,
           // timeout:6000
        });
        this.initInterceptors();
        return axios;
    }

    public static initInterceptors(){
        this.axiosInstance.interceptors.request.use(
            (config:AxiosRequestConfig)=>{
                const token=localStorage.getItem('ACCESS_TOKEN');
                if(token && config.headers){
                    config.headers.Authorization='Bearer'+token;
                }
                return config;
            },
            (error:any)=>{
                console.log(error)
            },
        );

        this.axiosInstance.interceptors.response.use(
            (response:AxiosResponse)=>{
                if(response.status===200){
                    return response;
                }else{
                    Request.errorHandle(response);
                    return response;
                }
            },

            (error:any)=>{
                const {response}=error;
                if(response){
                    Request.errorHandle(response);
                    return Promise.reject(response.data);
                }else{
                    console.log('网络连接异常，请稍后重试！');
                }
            }
        );
    }

    private static errorHandle(res:any){
        switch(res.status){
            case 401:break;
            case 403:break;
            case 404:break;
            default:console.log('连接错误');
        }
    }
}

