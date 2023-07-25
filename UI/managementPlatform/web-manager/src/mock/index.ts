import Mock from 'mockjs'

var Random=Mock.Random

var login={
    'code': Random.pick(['200']),
    'result': {
        "Message":Random.pick(['信息有误，重新输入']),   
    } ,
    "message":Random.pick(['信息有误，重新输入','有误，请稍后重试dvdvdfbfbcbvdfg ggrtg ggegtrb gg rggehgetn  bffghgf bsbsb bdbfn ']), 
}

var getInfo={
    'code': '200',
    'message': '请求成功',
    'result|2-6': [
        {'id|+2': 1,
                'scrollName': Random.cword(2, 4) ,
                'productName': Random.cword(3, 5),
                'productDescription': Random.cparagraph(1, 2),
                'productFunctions|2-3': 
                [
                    {
                        "functionName": Random.csentence(),
                        "functionContent": Random.cparagraph(1, 4),
                        "id|+2": 1,
                        "productId": 2       
                    }, 
                    
                ],
                'productAdvantages|2-5': 
                [
                    {
                        "advantageName": Random.csentence(6,8),
                        "advantageContent": Random.cparagraph(1, 4),
                        "id|+2": 1,
                        "productId": 2        
                    },
                ],
                'styleId|0-1': 1
        },
        {'id|+2': 2,
                'scrollName': Random.cword(2, 4) ,
                'productName': Random.cword(3, 5),
                'productDescription': Random.cparagraph(5, 6),
                'productFunctions|2-3': 
                [
                    {
                        "functionName": Random.csentence(),
                        "functionContent": Random.cparagraph(1, 4) ,
                        "id|+2": 2,
                        "productId": 2       
                    }, 
                    
                ],
                'productAdvantages|2-5': 
                [
                    {
                        "advantageName": Random.csentence(3,6),
                        "advantageContent": Random.cparagraph(1, 4),
                        "id|+2": 2,
                        "productId": 2         
                    },
                ],
                'styleId|0-1': 0
        },
              
    ]    
         
}

var addmessage={
    'code': '200',
    'result': {
        "Message":Random.pick(['成功','有误，重新输入']),   
    },
    "message":Random.pick(['成功','有误，请稍后重试dvdvdfbfbcbvdfg ggrtg ggegtrb gg rggehgetn  bffghgf bsbsb bdbfn ']),

}




Mock.mock(/\/login/, 'post', login)
Mock.mock(/\/display/, 'get', getInfo)
Mock.mock(/\/add/, 'post', addmessage)
Mock.mock(/\/update/, 'post', addmessage)
Mock.mock(/\/delete/, 'delete', addmessage)
export default Mock