import React ,  { Component }from "react"
import '../../product/product.css'
import {get} from "../../../service/httpservice"
import FlashProduct from "../../product/productCard/FlashProduct";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import Navbar from "../../navbar/Navbar"
import {render} from "react-dom";
export default function ProductByCategory()  {

    let {id } = useParams();

    const [productList , setProductList] = useState([]);
    const [category , setCategory] = useState([]);


    function getListProduct(){
        get('/products/category/'+ id).then(response =>{
            if(response.status === 200){
                setProductList(response.data)
            }
        });
    }
    function getCategory(){
        get('/category/'+ id).then(response =>{

            if(response.status === 200){
                setCategory(response.data)
                // getListProduct();
                // getCategory();
            }
        });
    }


    useEffect(() => {
        getListProduct();
        getCategory();
    }, []);

    return (
        <>
            <Navbar/>
            <section className="bg-white position-relative">
                <div
                    className="wpb_text_column wpb_content_element">
                    <div className="wpb_wrapper">
                        <div className="col-md-12" style={{marginTop: "80px"}}>
                            <div className="top-5 text-center">
                                <h2>SẢN PHẨM THUỘC CATEGORY {category.name}</h2>
                            </div>
                        </div>
                    </div>
                    <FlashProduct productItems={productList} check={true}/>
                </div>
            </section>
        </>
    )
}
