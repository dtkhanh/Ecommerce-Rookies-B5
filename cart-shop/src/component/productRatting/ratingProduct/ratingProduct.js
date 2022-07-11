import ReactStars from "react-rating-stars-component";
import React from "react";
import { render } from "react-dom";
import '../productRating.css'
import {Col, Row} from "antd";
import Rating from "../../../Utils/Rating/Rating";
import {useEffect, useState} from "react";
import {get, post} from "../../../service/httpservice";
import swal from "sweetalert";
import {Navigate, useNavigate} from "react-router-dom";

import ProductDetail from "../../product/productDetails/productDetails";
export default function RatingProduct({pid}) {
    const url_ratting = "/productcomment";
    const [rating, setRating] = useState([]);
    const url_rattings = "/productcomment/product/";
    const [ratingList, setRatingList] = useState([]);
    const navigate = useNavigate();

    function getCommentList(){
        get( url_rattings + pid).then(response => {
            if (response.status === 200) {
                setRatingList(response.data);
            }
        });
    }
    const [ratingProduct, setRatingProduct] = useState({
        id_account: JSON.parse(localStorage.getItem('User')).id,
        rating: 0.0,
        comment: '' ,
    })

    const handlecontentChange = event => {
        ratingProduct.comment = event.target.value
        setRatingProduct(ratingProduct)
    };
    const ratingChanged = (newRating) => {
        ratingProduct.rating = newRating
        setRatingProduct(ratingProduct)
    };


    const postComment = (e) => {
        e.preventDefault();
        const body = JSON.stringify({
            id_account: ratingProduct.id_account,
            rating: ratingProduct.rating,
            comment: ratingProduct.comment,
            id_product: pid
        });
        console.log(body)
        post(url_ratting, body)
            .then((response) => {
                if (response.status === 200) {
                    swal({
                        title: "Add product succeeded!",
                        icon: "success"
                    }).then( navigate('/product/'+pid))



                }
            }).catch((error) => {
            let message = "Add product failed!";
            if (!error.response) message = "Connection error! Please try again later";
            else {
                console.log(error.response.status);
                switch (error.response.status) {
                    case 400: message = "The product name is already exist!";
                        swal({
                            icon: 'error',
                            title:'Connection error! Please try again later',
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
        });
    }

    useEffect(() => {
        getCommentList();
    }, []);


    return (
        <>
            <div className="name-review" style={{textAlign:"center"}}>
                <p>COMMENT PRODUCT</p>
            </div>
        <div className="review-card">
            <form onSubmit={postComment}>
            <ReactStars
                count={5}
                onChange={ratingChanged}
                size={24}
                isHalf={true}
                emptyIcon={<i className="far fa-star"></i>}
                halfIcon={<i className="fa fa-star-half-alt"></i>}
                fullIcon={<i className="fa fa-star"></i>}
                activeColor="#ffd700"
            />
            <div style={{textAlign:"center"}}>
                <input  type="text"
                        placeholder="content comment product"
                        className="form-control"
                        id="product_title"
                        onChange={handlecontentChange}
                >
                </input>
                <div style={{textAlign:"right"}}>
                <input style={{textAlign:"right", marginTop:"12px"}}className="btn btn-primary" type="submit" value="Submit"></input>
                </div>
            </div>
            </form>

        </div>
        </>
    );
}
