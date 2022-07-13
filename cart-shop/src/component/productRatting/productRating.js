import React, { useState } from "react"
import "slick-carousel/slick/slick.css"
import "slick-carousel/slick/slick-theme.css"

import {Button, Col, Row} from "antd";
import './productRating.css'
import Rating from "../../Utils/Rating/Rating";
import {get} from "../../service/httpservice";
import {useEffect} from "react";




const ProductRatings = ({pid}) => {
    const url_ratting = "/productcomment/product/";
    const [ratingList, setRatingList] = useState([]);

    function getCommentList(){
        get( url_ratting + pid).then(response => {
            if (response.status === 200) {
                setRatingList(response.data);
            }
        });
    }
    useEffect(() => {
        getCommentList();
    }, []);


    return (

        <>
            <div className="name-review" style={{textAlign:"center"}}>
                <p>REVIEW</p>
            </div>
            {ratingList.length === 0 ? <h4>No review</h4> :
                ratingList.map(rating => (
                    <div className="review-card">
                        <Row>
                            <Col className="col-review">
                                <p className="name-review">{rating.name}</p>
                                    <Rating count={rating.rating} />
                                <p style={{fontSize:"12px", fontStyle:"italic"}}>{rating.comment}</p>
                            </Col>
                        </Row>
                    </div>
                ))}

        </>

    )
}

export default ProductRatings;