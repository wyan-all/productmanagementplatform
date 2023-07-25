import Mock from 'mockjs'

var Random=Mock.Random
var template = {
    'code': '200',
    'message': '请求成功',
    'result|2-10': [
              {'id|+1': 1,
                      'scrollName': Random.cword(2, 5) ,
                      'productName': Random.cword(3, 5),
                      'productDescription': Random.cparagraph(1, 3),
                      'productFunctions|1-3': 
                      [
                          {
                              "functionName": Random.csentence(2,4),
                              "functionContent": Random.cparagraph(1, 4)       
                          }, 
                         
                      ],
                       'productAdvantages|2-5': 
                      [
                          {
                              "advantageName": Random.csentence(3,6),
                              "advantageContent": Random.cparagraph(1, 4)        
                          },
                      ],
                      'styleId|0': 0
                },
              
            ],
        
}

var mail = {
    'code': Random.pick(['200','400']),
    'result': {
        "appMessage":Random.pick(["已经收到您的试用提交 , 工作人员会尽快联系您，请关注电话或邮箱反馈" ,'网络错误，请您稍后重试，或联系管理员']),   
    }  
}
  
var securityCode={
    'code': Random.pick(['200','400']),
    'result': {
        "securityCode":Random.natural(1000, 9999),   
    }  
}
  Mock.mock(/\/display/, 'get', template)
  Mock.mock(/\/commit/, 'post', mail)
  Mock.mock(/\/securityCode/, 'get', securityCode)
  export default Mock
  