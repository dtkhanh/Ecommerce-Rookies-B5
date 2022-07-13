
import React, { useState } from "react"
import './help.css'
const Help = () => {

    return (

        <footer className="mt-3">
            <div className="content">
                <div className="footer-grids">
                    <div className="footer one">
                        <h3>More About Company</h3>
                        <p>
                            {" "}
                            Since 2021 BikeCo.com has been the web incarnation of our brick
                            and mortar located in Lake Forest, California. We provide sport to
                            pro race MTB riders access to unmatched expertise, the best
                            products from leading manufacturers as well as unique content and
                            insight.
                        </p>
                        <p className="adam">- BikeCo.com – Race Proven!</p>
                        <div className="clear"></div>
                    </div>
                    <div className="footer two">
                        <h3>Keep Connected</h3>
                        <ul>
                            <li>
                                <a className="fb" href="">
                                    <i></i>Like us on Facebook
                                </a>
                            </li>
                            <li>
                                <a className="fb1" href="#">
                                    <i></i>Follow us on Twitter
                                </a>
                            </li>
                            <li>
                                <a className="fb2" href="#">
                                    <i></i>Add us on Google Plus
                                </a>
                            </li>
                            <li>
                                <a className="fb4" href="#">
                                    <i></i>Follow us on Pinterest
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div className="footer three">
                        <h3>Contact Information</h3>
                        <ul>
                            <li>134 Nguyen Thi Thap P.Binh Thuan Q.7</li>
                            <li> (+84) 383 314 312</li>
                            <li>
                                <a href="mailto:info@example.com">Trongkhanh2k1@gmail.com</a>{" "}
                            </li>
                        </ul>
                    </div>
                    <div className="clear"></div>
                </div>
                <div className="copy-right-grids">
                    <div className="copy-left">
                        <p className="footer-gd"></p>
                    </div>
                    <div className="copy-right">
                        <ul>
                            <li>
                                <a href="#">Company Information</a>
                            </li>
                            <li>
                                <a href="#">Privacy Policy</a>
                            </li>
                            <li>
                                <a href="#">Terms & Conditions</a>
                            </li>
                        </ul>
                    </div>
                    <div className="clear"></div>
                </div>
            </div>
        </footer>
    )

}
export default Help


