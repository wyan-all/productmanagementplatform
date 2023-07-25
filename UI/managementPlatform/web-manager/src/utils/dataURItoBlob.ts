//base64转二进制
export const dataURItoBlob=function(uri:any){
    let byteString=atob(uri.split(',')[1])
    let mimeString=uri.split(',')[0].split(':')[1].split(';')[0]
    let ab=new ArrayBuffer(byteString.length)
    let ia=new Uint8Array(ab)
    for(let i=0;i<byteString.length;i++){
        ia[i]=byteString.charCodeAt(i)
    }
    return new Blob([ab],{type:mimeString})


}