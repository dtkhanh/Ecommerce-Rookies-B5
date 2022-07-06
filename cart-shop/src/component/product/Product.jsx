import React, {Component, useState, useEffect} from "react"
import FlashProduct from "./productDetails/FlashProduct"
import './product.css'
import {get} from "../../service/httpservice";


export default function Product() {
    const option = ["Idcate","NameCategory"]

    const [productList , setProductList] = useState([]);

    useEffect(() => {
        getListProduct();
    }, []);

    function getListProduct(){
        get('/products').then(response =>{
            if(response.status === 200){
                setProductList(response.data)
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
                    <FlashProduct productItems={productList} />
                </div>
                {/*<div id="testimonial-card" className="card text-center">*/}
                {/*    <div className="card-body">*/}
                {/*        <div className="row row-cols-1 row-cols-md-3 row-cols-lg-5">*/}
                {/*            <div className="col mb-3">*/}
                {/*            <div className="card h-100">*/}
                {/*            <img src={process.env.PUBLIC_URL + `/Imgs/img2.jpg`} class="card-img-top" alt="..."/>*/}
                {/*            <div className="card-body">*/}
                {/*            <h5 className="card-title">*/}
                {/*            ABCSDESD*/}
                {/*            </h5>*/}
                {/*            <p className="text-product" id="">Combo 5 miếng mặt nạ làm sáng da*/}
                {/*            </p>*/}
                {/*            <p className="card-text" id="cp-buyPrice<%=element.name %>">Giá mua ngay:30.000*/}
                {/*            </p>*/}
                {/*            <p className="card-text">Số lượt ra giá: <span class="text-dark font-weight-bold">*/}
                {/*            2*/}
                {/*            </span></p>*/}
                {/*            </div>*/}
                {/*            /!*<div class="card-footer" style="background-color: transparent; border-top: none;">*!/*/}
                {/*            /!*<p class="more-info">*!/*/}
                {/*            /!*<a href="/view-product-list/view-product/<%= element._id %>" class="text-decoration-none">Read more...</a>*!/*/}
                {/*            /!*</p>*!/*/}
                {/*            /!*</div>*!/*/}
                {/*            </div>*/}
                {/*            </div>*/}
                {/*            <div className="col mb-3">*/}
                {/*                <div className="card h-100">*/}
                {/*                    <img src={process.env.PUBLIC_URL + `/Imgs/img2.jpg`} className="card-img-top"*/}
                {/*                         alt="..."/>*/}
                {/*                    <div className="card-body">*/}
                {/*                        <h5 className="card-title">*/}
                {/*                            ABCSDESD*/}
                {/*                        </h5>*/}
                {/*                        <p className="text-product" id="">Combo 5 miếng mặt nạ làm sáng da*/}
                {/*                        </p>*/}
                {/*                        <p className="card-text" id="cp-buyPrice<%=element.name %>">Giá mua ngay:30.000*/}
                {/*                        </p>*/}
                {/*                        <p className="card-text">Số lượt ra giá: <span className="text-dark font-weight-bold"> 2*/}
                {/*                        </span></p>*/}
                {/*                    </div>*/}
                {/*                </div>*/}
                {/*            </div>*/}
                {/*        </div>*/}

                {/*    </div>*/}

                {/*    <div className="card-header bg-light">*/}
                {/*        <h2 className="my-0">xxxxxxxxxxxxxxxxxxxxxxxxxxxxx</h2>*/}
                {/*    </div>*/}
                {/*    <div className="card-body">*/}

                {/*    </div>*/}
                {/*    <div className="card-header bg-light">*/}

                {/*    </div>*/}
                {/*    <div className="card-body">*/}

                {/*    </div>*/}
                {/*</div>*/}
            </section>
        </>
    )

}
