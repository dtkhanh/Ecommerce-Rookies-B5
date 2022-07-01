import React, {Component, useState} from "react"
import Slider from "react-slick"
import "slick-carousel/slick/slick.css"
import "slick-carousel/slick/slick-theme.css"

import './category.css'
import dataSlider from "../carousel/dataSlider";
import {Link} from "react-router-dom";
const Category = () => {
    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 1,
    }

    return (
        <>
                <div className="col-md-12" style={{marginTop: "80px"}}>
                    <div className="title text-center">
                        <h2>DANH MỤC SẢN PHẨM</h2>
                    </div>
                </div>
                {/*<div id="testimonial-card" className="card text-center" style={{marginTop: "10px"}}>*/}
                {/*    <div className="card-header bg-light">*/}
                {/*        <h2 className="my-0">DANH MUC</h2>*/}
                {/*    </div>*/}
                {/*</div>*/}
                <Slider {...settings} >
                    {dataSlider.map((obj, index) => (

                        <div className="img_category" style={{
                            width: "250px",
                        }}>
                            <div className="card">
                                <Link to={"/" + `${obj.title}`} >
                                    <img className="card-img-top"
                                         src={process.env.PUBLIC_URL + `/Imgs/img${index + 1}.jpg`}/>
                                </Link>

                                <div className="card-body">
                                    <h5>
                                        <p
                                            style={{
                                                fontSize: "18px",
                                                padding: "5px 0",
                                                color: "black",
                                                fontWeight: 400,
                                                textAlign: "center",
                                                textTransform: "uppercase",
                                                letterSpacing: "2px",
                                            }}
                                        >
                                            ${obj.title}
                                        </p>
                                    </h5>
                                </div>
                            </div>

                            {/*<Link to={"/" + `${obj.title}`} >*/}
                            {/*    <img className="image_category" src={process.env.PUBLIC_URL + `/Imgs/img${index + 1}.jpg`} alt='banner' width="100%" height="100%" aria-hidden="true" preserveAspectRatio="xMidYMid slice"*/}
                            {/*    ></img>*/}
                            {/*</Link>*/}
                            {/*<p*/}
                            {/*    style={{*/}
                            {/*        fontSize: "18px",*/}
                            {/*        padding: "5px 0",*/}
                            {/*        color: "gray",*/}
                            {/*        fontWeight: 400,*/}
                            {/*        textAlign: "center"*/}
                            {/*    }}*/}
                            {/*>*/}
                            {/*    ${obj.title}*/}
                            {/*</p>*/}
                        </div>

                    ))}
                </Slider>

        </>
    )
}

export default Category