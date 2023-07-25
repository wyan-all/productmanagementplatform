export const compareObject=function(obj1:any, obj2:any):boolean {  
    if (typeof obj1 !== 'object' || typeof obj2 !== 'object') {
        if (obj1 === obj2) {
          return true
        } else if (obj1 !== obj2) {
          return false
        }
      }
    // 获取对象的自由属性组成的数组
    const obj1PropsArr = Object.getOwnPropertyNames(obj1)
    const obj2PropsArr = Object.getOwnPropertyNames(obj2) 
    // 如果数组的长度不相等，那么说明对象属性的个数都不同，返回 false
   // console.log(obj1PropsArr.length,obj2PropsArr.length)
    if (obj1PropsArr.length !== obj2PropsArr.length) {
      return false
    }
    // 记录当前 compareObject 的返回值，默认是 true
    let status = true
    for ( let key of obj1PropsArr) {
      status = compareObject(obj1[key], obj2[key])
      // 关键代码，当 status 为 false 时下面就不用再进行判断了，说明两个对象的内容并不相同
      // 如果没有下面这条语句，那么只要对象底层的内容是相同的那么就返回 true
      if (!status) {
        break
      }
    }
    // 每次 compareObject 执行的返回结果
    return status
  }