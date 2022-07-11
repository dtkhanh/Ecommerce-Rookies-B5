import React, { useState } from "react"
import {Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle} from 'reactstrap';
import Slider from "react-slick"
import "slick-carousel/slick/slick.css"
import "slick-carousel/slick/slick-theme.css"
import  "./FlashProduct.css"
import ReactStars from "react-rating-stars-component";

import {Link} from "react-router-dom";
import Rating from "../../../Utils/Rating/Rating";
import {Col, Row} from "antd";
import swal from "sweetalert";
import {deleteurl, post} from "../../../service/httpservice";
import {toast} from "react-toastify";


const FlashProduct = ({ productItems , check}) => {
    const [count, setCount] = useState(0)
    const increment = () => {
        setCount(count + 1)
    }
    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 1,
    }
    const addToCart = (item) => {
        if(JSON.parse(localStorage.getItem('User')) === null){
            swal({
                title: "You can login web ?",
                icon: "error",
                buttons: true,
                dangerMode: true,
            })
        }
        else {
            const body = JSON.stringify({
                number: 1,
                id_product:item.id
            });
            console.log(item)
            post( `/orderDetails/` + JSON.parse(localStorage.getItem('User')).id, body)
                .then((response) => {
                    if (response.status === 200) {
                        swal({
                            title: "Add product succeeded!",
                            icon: "success"
                        })
                    }
                }).catch((error) => {
                console.log(error.response.data.message)
                let message = error.response.message;
                if (!error.response){
                    message = "Connection error! Please try again later";
                    swal({
                        icon: 'error',
                        title:'Connection error! Please try again later',
                        footer: '<a href="">Why do I have this issue?</a>'
                    })
                }
                else {
                    switch (error.response.status) {
                        case 400: message = "The product name is already exist!";
                            swal({
                                icon: 'error',
                                title:'The product name is already exist!',
                                footer: '<a href="">Why do I have this issue?</a>'
                            })
                            break;
                        default: break;

                    }
                }
                swal({
                    icon: 'error',
                    title: error.response.data.message,
                    footer: '<a href="">Why do I have this issue?</a>'
                })
                toast.error(message, {
                    position: toast.POSITION.TOP_RIGHT,
                    autoClose: 3000,
                });
            });
        }
    };



    return (

        <>
            {check === false ?

                <Slider {...settings} >
                    {productItems.map((obj, index) => (
                        <div className="row g-4 my-5" style={{textAlign: 'center'}}>
                            <div className="product-img">
                                <img style={{height:"311px"}} src={obj.imageproduct} className="img-fluid d-block mx-auto"/>

                            </div>
                            <div className="card-body" style={{textAlign: 'center'}}>
                                <h5 className="card-title mb-0">{(obj.title).substring(0,12) }</h5>
                                <div className='rate' style={{ alignItems: "center",textAlign: 'right', marginLeft:"95px"}}>
                                    <Rating count={obj.ratting} />
                                </div>
                                <p className="card-text lead fw-bold" >
                                    ${obj.price}
                                </p>
                                <div className="btns">
                                    <button onClick={()=> addToCart(obj)} type="button-cart" className="col-6 py-2">
                                        <i className="fa fa-cart-plus"></i> Add to Cart
                                    </button>
                                    <Link to={"/product/"+ obj.id} className="btn btn-warning" style={{marginLeft:"20px",textAlign:"right"}}>
                                        <i className="fa fa-eye"></i>
                                    </Link>
                                </div>
                                <div className="btns">
                                </div>

                            </div>
                        </div>

                    ))}
                </Slider>

                :
                <div >
                    <div className="row row-cols-1 row-cols-md-3 row-cols-lg-4">
                    {productItems.map((obj, index) => (
                        <div className="row g-4 my-5" style={{textAlign: 'center'}}>
                            <div className="product-img">
                                <img style={{height:"311px"}} src={obj.imageproduct} className="img-fluid d-block mx-auto"/>

                            </div>
                            <div className="card-body" style={{textAlign: 'center'}}>
                                <h5 className="card-title mb-0">{(obj.title).substring(0,12) }</h5>
                                <div className='rate' style={{ alignItems: "center",textAlign: 'right', marginLeft:"95px"}}>
                                    <Rating count={obj.ratting} />
                                </div>
                                <p className="card-text lead fw-bold" >
                                    ${obj.price}
                                </p>
                                <div className="btns">
                                    <button type="button-cart" className="col-6 py-2">
                                        <i className="fa fa-cart-plus"></i> Add to Cart
                                    </button>
                                    <Link to={"/product/"+ obj.id} className="btn btn-warning" style={{marginLeft:"20px",textAlign:"right"}}>
                                        <i className="fa fa-eye"></i>
                                    </Link>
                                </div>
                                <div className="btns">
                                </div>

                            </div>
                        </div>

                    ))}
                    </div>
                </div>
             }

        {/*<div className="row row-cols-1 row-cols-md-3 row-cols-lg-4">*/}
        {/*            {productItems.map((obj, index) => (*/}
        {/*                <div className="col-md-3 mb-4">*/}
        {/*                    <div className="card h-100 " key={productItems.id}>*/}
        {/*                        <img src={obj.imageproduct} className="card-img-top"*/}
        {/*                             alt="..."/>*/}
        {/*                        <div className="card-body" style={{textAlign: "center"}}>*/}
        {/*                            <h5 className="card-title mb-0">{(obj.title).substring(0,12) }</h5>*/}
        {/*                            <div className='rate'>*/}
        {/*                                <i className='fa fa-star'></i>*/}
        {/*                                <i className='fa fa-star'></i>*/}
        {/*                                <i className='fa fa-star'></i>*/}
        {/*                                <i className='fa fa-star'></i>*/}
        {/*                                <i className='fa fa-star'></i>*/}
        {/*                            </div>*/}
        {/*                            <p className="card-text lead fw-bold" >*/}
        {/*                                ${obj.price}*/}
        {/*                            </p>*/}
        {/*                            <a href="#" className="btn btn-primary cart" >Add cart</a>*/}
        {/*                        </div>*/}

        {/*                        /!*</div>*!/*/}

        {/*                    </div>*/}
        {/*                </div>*/}
        {/*            ))}*/}

        {/*        ))}*/}
        {/*</div>*/}



        </>
    )
}

export default FlashProduct
