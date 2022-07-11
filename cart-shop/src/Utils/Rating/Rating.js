import React, {useMemo, useState} from 'react';
import ReactStars from "react-rating-stars-component";

const Rating = ({ count}) => {

    const thirdExample = {
        size: 20,
        count: 5,
        isHalf: true,
        value: count,
        color: "blue",
        activeColor: "red",
        edit: false
    };
    return(
        <>
            <ReactStars {...thirdExample} />
        </>
    );
}

export default Rating;
