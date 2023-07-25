//滚动栏元素
interface IScrollItem{
    id: number,
    scrollName: string
}

//功能元素
interface IFunctionItem{
    functionName: string,
    functionContent: string
}

//优势元素
interface IAdvantageItem{
    advantageName: string,
    advantageContent: string
}

//产品元素信息
interface IProductItem{
    id: number,
    scrollName: string, 
    productName: string,
    description: string,
    function: IFunctionItem[],
    advantage: IAdvantageItem[],
    styleId: number
}

export{
    IScrollItem,
    IFunctionItem,
    IAdvantageItem,
    IProductItem,
}