//滚动栏元素
interface IScrollItem{
    scrollName: string
}

//功能元素
interface IFunctionItem{
    functionName: string,
    functionContent: string,
    id:number|undefined,
    productId:number|undefined,
}

//优势元素
interface IAdvantageItem{
    advantageName: string,
    advantageContent: string,
    id:number|undefined,
    productId:number|undefined,
}

//产品元素信息
interface IProductItem{
    id: number,
    scrollName: string, 
    productName: string,
    productDescription: string,
    productFunctions: IFunctionItem[],
    productAdvantages: IAdvantageItem[],
    styleId: number
}

export{
    IScrollItem,
    IFunctionItem,
    IAdvantageItem,
    IProductItem,
}