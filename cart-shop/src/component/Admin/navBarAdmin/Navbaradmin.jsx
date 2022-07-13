import React, { useState } from "react"



import {
    Link
} from "react-router-dom";

import './navbaradmin.css'
export default function NavbarAdmin()  {


    return (
        <>

            <div className="navbar-color">

                <nav className="navbar navbar-expand-lg navbar-light bg-white fixed-top">
                    <div className="container">
                        <a className="navbar-brand d-flex justify-content-between align-items-center order-lg-0"
                           href="index.html">
                            <span className="text-uppercase fw-lighter ms-2">Attire</span>
                        </a>
                        <div className="order-lg-2 d-flex nav-btns">
                            <div>
                                <img className="avatar rounded-circle" src="/avatar1.png" alt='' style={{marginLeft: "14px"}}/>
                                <button className="btn" >
                                    <Link className="dropdown-item" to="/logout">Đăng xuất</Link>
                                </button>

                            </div>
                        </div>

                        <button className="navbar-toggler border-0" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navMenu">
                            <span className="navbar-toggler-icon"></span>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarNav">
                            <span style={{ fontSize :"25px" , paddingLeft: "150px", textAlign:"center"}} className="text-uppercase fw-lighter ms-2"> Welcome to page Admin</span>
                        </div>
                    </div>
                </nav>
            </div>
        </>
    )
}

