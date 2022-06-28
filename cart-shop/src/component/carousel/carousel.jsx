import React, { useState } from "react"

import './Slider.css'
import dataSlider from "./dataSlider";


const Carousel = () => {
    const [slideIndex] = useState(1)
    const abc = slideIndex+1
    return (

        <>
            <div id="myCarousel" className="carousel slide" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    {dataSlider.map((obj, index) => (
                        <button
                            key={index}
                            data-bs-target="#myCarousel"
                            type="button"
                            className={slideIndex === index+1 ? "active" : ""}
                            data-bs-slide-to={index}
                            aria-current="true"
                            aria-label={"Slide " + {abc}}
                        >
                        </button>
                    ))}
                </div>
                <div className="carousel-inner">
                    {dataSlider.map((obj, index) => (
                        <div
                            key={index}
                            className={slideIndex === index + 1 ? "carousel-item active" : "carousel-item"}>
                            <img src={process.env.PUBLIC_URL + `/Imgs/img${index + 1}.jpg`} alt='banner' width="100%" height="100%" aria-hidden="true" preserveAspectRatio="xMidYMid slice"
                                   focusable="false"/>
                        </div>
                    ))}
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#myCarousel"
                        data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#myCarousel"
                        data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
        </>
    )

}
export default Carousel

