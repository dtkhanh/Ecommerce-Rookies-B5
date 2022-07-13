
import React, { useState } from "react"
import "slick-carousel/slick/slick.css"
import "slick-carousel/slick/slick-theme.css"

import {Button, Col, Row} from "antd";
import './productDetails.css'
import Navbar  from "../../navbar/Navbar";
import Rating from "../../../Utils/Rating/Rating";
import {useParams} from "react-router-dom";
import {get} from "../../../service/httpservice";
import {useEffect} from "react";
import {Link} from "react-router-dom";
import Help from "../../about/help";
import ProductRatings from "../../productRatting/productRating";
import ReactStars from "react-rating-stars-component";
import "./productDetails.css"
import RatingProduct from "../../productRatting/ratingProduct/ratingProduct";

const ProductDetail = ( ) => {
    const url_product = "/products";
    let {id } = useParams();
    const [productList, setProductList] = useState([]);
    console.log(id)
    function getProductList(){
        get( url_product+"/" + id).then(response => {
            if (response.status === 200) {
                setProductList(response.data);
                console.log(productList)
            }
        });
    }
    useEffect(() => {
        getProductList();
    }, []);

    function custormerMoney(money){
        if(money >999){
            return money /1000 +'.'+ '000'
        }
        if(money>999999)
            return money/1000000+'.000.000'
        else
            return money

    }


    return(
        <>
            <Navbar/>
            <body>
            <main>
                <div className="card">
                    <div className="card__title">
                        <div className="icon">
                            <Link to="/"><i className="fa fa-arrow-left"></i></Link>
                        </div>
                        <h3>Products</h3>
                    </div>

                    <div className="card__body">
                        <div className="half">
                            <div className="featured_text">
                                <h1>{productList.name}</h1>
                                <p className="price" style={{color:"red"}}>{custormerMoney(productList.price)} vnd</p>
                            </div>
                            <div className="image" style={{ paddingTop: "15px", "width": "100%", height:"100%"}}>
                                <img style={{
                                    display: "block",
                                    maxWidth: "100%",
                                    height: "auto"
                                }} src={productList.image}
                                     alt=""></img>
                            </div>
                        </div>
                        <div className="half">
                            <div className="description">
                                <p>
                                    {productList.description}
                                </p>
                            </div>
                            <span className="stock"><i style={{color:"#f7c01b"}} className="fa fa-pen"></i> In stock</span>
                            <div className="reviews">
                                    <ul className="stars">
                                        {productList.rating === 0 ?
                                            <div>
                                                <Rating count ={productList.rating}/><span>{productList.rating +" star"}</span>
                                            </div>
                                            : (
                                                <div>
                                                    <Rating count ={productList.rating}/><span>{productList.rating +" star"}</span>
                                                </div>
                                            )}
                                    </ul>
                            </div>
                        </div>
                    </div>
                    <div className="card__footer">
                        <div className="recommend">
                            <p>Recommended by</p>
                        </div>
                        <div className="action">
                            <button className="button-addcart" type="button">Add to cart</button>
                        </div>
                    </div>
                    <ProductRatings pid={id}/>
                    {localStorage.getItem('User') ===null ?<> </>
                        :
                        <RatingProduct pid={id}/>
                    }
                </div>
            </main>
            </body>
            <Help/>
        </>
    )
}
export default ProductDetail
