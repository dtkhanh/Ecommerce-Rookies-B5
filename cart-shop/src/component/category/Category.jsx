import React, {Component, useState, useEffect} from "react"
import Slider from "react-slick"
import "slick-carousel/slick/slick.css"
import "slick-carousel/slick/slick-theme.css"

import './category.css'
import dataSlider from "../carousel/dataSlider";
import {Link} from "react-router-dom";
import { toast } from "react-toastify";
import {get} from "../../service/httpservice"
import {getCookie} from "../../service/Cookie";

export default function Category()  {
    const [cateList, setCateList] = useState([])

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
                            <Link className="btn btn-outline-dark me-2" to={"/category/" + `${obj.id}`}> {obj.name.toUpperCase()}</Link>
                        ))}
                </div>



        </>
    )
}
