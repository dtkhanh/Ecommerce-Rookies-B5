import React, {Component, useState, useEffect} from "react"
import Slider from "react-slick"
import "slick-carousel/slick/slick.css"
import "slick-carousel/slick/slick-theme.css"

import './category.css'
import dataSlider from "../carousel/dataSlider";
import {Link} from "react-router-dom";
import { toast } from "react-toastify";
import {get} from "../../service/httpservice"

export default function Category()  {
    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 1,
    }
    const [cateList, setCateList] = useState([])
    const option = ["Idcate","NameCategory"]

    function getListCategory(){
        get('/category').then(response =>{
            if(response.status === 200){
                setCateList(response.data)
            }
        });
    }
    console.log(cateList)
    useEffect(() => {
        getListCategory();
    }, []);

    return (
        <>
                <div className="col-md-12" style={{marginTop: "80px"}}>
                    <div className="title text-center">
                        <h2>DANH MỤC SẢN PHẨM</h2>
                    </div>
                </div>
                <div className="buttons d-flex justify-content-center mb-5 pb-5">
                        {cateList.map((obj, index) => (
                            <button className="btn btn-outline-dark me-2" to={"/" + `${obj.id}`}> {obj.name.toUpperCase()}</button>
                        ))}
                </div>



        </>
    )
}
