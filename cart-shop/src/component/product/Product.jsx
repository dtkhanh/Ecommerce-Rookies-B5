import React, {Component, useState, useEffect} from "react"
import FlashProduct from "./productCard/FlashProduct"
import './product.css'
import {get} from "../../service/httpservice";


export default function Product() {
    const option = ["Idcate","NameCategory"]

    const [productList , setProductList] = useState([]);

    useEffect(() => {
        getListProduct();
    }, []);

    function getListProduct(){
        get('/products/sort').then(response =>{
            if(response.status === 200){
                setProductList(response.data)
                console.log(productList)
            }
        });
    }

    return (
        <>
            <section className="bg-white position-relative">
                <div
                    className="wpb_text_column wpb_content_element">
                    <div className="wpb_wrapper">
                        <div className="col-md-12" style={{marginTop: "80px"}}>
                            <div className="top-5 text-center">
                                <h2>TOP 5 SẢN PHẨM ĐÁNH GIÁ CAO NHẤT</h2>
                            </div>
                        </div>
                    </div>
                    <FlashProduct productItems={productList} check={false}/>
                </div>
            </section>
        </>
    )

}
