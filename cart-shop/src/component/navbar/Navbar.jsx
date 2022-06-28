import React, { useState } from "react"
import logo from "../DINH.svg"
import { IconContext } from "react-icons"
import { AiOutlineSearch } from 'react-icons/ai'
import {AiOutlineShoppingCart} from 'react-icons/ai'
import {useNavigate} from "react-router-dom";


import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";


import './navbar.css'
const Navbar = ({ CartItem }) => {
    function handleSubmit(e) {
        e.preventDefault();
        console.log('You clicked submit.');
    }
    const navigate = useNavigate();

    const backToHome = () => {
        navigate("/movie");
    }


    return (
        <>

            <div className="navbar-color">
                <nav className="navbar navbar-expand-lg navbar-light bg-white fixed-top">
                    <div className="container">
                        <a className="navbar-brand d-flex justify-content-between align-items-center order-lg-0"
                           href="index.html">
                            {/*<img src={logo} alt='' />*/}
                            <span className="text-uppercase fw-lighter ms-2">Attire</span>
                        </a>

                        <div className="order-lg-2 nav-btns">
                            <button type="button" className="btn position-relative" >
                                <i className="fa fa-shopping-cart"></i>
                                <span
                                    className="position-absolute top-0 start-100 translate-middle badge bg-primary">5</span>
                            </button>
                            {/*<button type="button" className="btn position-relative">*/}
                            {/*    <i className="fa fa-user-circle"></i>*/}
                            {/*    <span*/}
                            {/*        className="position-absolute top-0 start-100 translate-middle badge bg-primary">2</span>*/}
                            {/*</button>*/}
                            <button className="btn position-relative" data-bs-toggle="dropdown" to="#"
                                    role="button" aria-expanded="false">
                                <img className="avatar rounded-circle" src="/avatar1.png" alt='' />

                                <ul className="dropdown-menu">
                                    <li><Link className="dropdown-item" to="/thoitrang">Đăng nhập</Link></li>
                                    <li><Link className="dropdown-item" to="/dientu">Đăng xuất</Link></li>
                                </ul>
                            </button>
                        </div>

                        <button className="navbar-toggler border-0" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navMenu">
                            <span className="navbar-toggler-icon"></span>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarNav">
                             <ul className="navbar-nav">
                                <li className="nav-item">
                                    <Link className="nav-link active" aria-current="page" to="/">Home</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/product">Products</Link>
                                </li>
                                <li className="nav-item dropdown">
                                    <Link className="nav-link dropdown-toggle" data-bs-toggle="dropdown" to="#"
                                       role="button" aria-expanded="true">Category</Link>
                                    <ul className="dropdown-menu">
                                        <li><Link className="dropdown-item" to="/thoitrang">Thời trang</Link></li>
                                        <li><Link className="dropdown-item" to="/dientu">Điện tử</Link></li>
                                        <li><Link className="dropdown-item" to="#/dogiadung">Đồ gia dụng</Link></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </>
    )
}

export default Navbar