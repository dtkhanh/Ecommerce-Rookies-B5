import React, { useState } from "react"
import logo from "../DINH.svg"
import { IconContext } from "react-icons"
import { AiOutlineSearch } from 'react-icons/ai'
import {AiOutlineShoppingCart} from 'react-icons/ai'
import {useNavigate} from "react-router-dom";
import {get, getwithAuthtication} from "../../service/httpservice"


import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

import './navbar.css'
import {useEffect} from "react";
import {checkAccountTrue} from "../../service/Authentication";
export default function Navbar()  {

    const url_rattings ="/account/"
    const [account , setAccount] = useState([])
        function getAccount() {
            if(JSON.parse(localStorage.getItem('User')) !== null) {
                getwithAuthtication(url_rattings + JSON.parse(localStorage.getItem('User')).id).then(response => {
                    if (response.status === 200) {
                        setAccount(response.data);
                        console.log(account)
                    }
                });
            }
            else{}
        }



    const [cateList, setCateList] = useState([])
    function getListCategory(){
        get('/category').then(response =>{
            if(response.status === 200){
                setCateList(response.data)
            }
        });
    }

    useEffect(() => {
        getAccount();
        getListCategory();
    }, []);

    const navigate = useNavigate();

    const backToHome = () => {
        navigate("/movie");
    }

    let   isLogin = checkAccountTrue();

    console.log(isLogin)


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



                        <div className="order-lg-2 d-flex nav-btns">
                            <button type="button" className="btn position-relative" >
                                <Link to="/cart">
                                  <i className="fa fa-shopping-cart"></i>
                                </Link>
                                <span
                                    className="position-absolute top-0 start-100 translate-middle badge bg-primary">5</span>
                            </button>
                            {/*<button className="btn position-relative" data-bs-toggle="dropdown" to=""*/}
                            {/*        role="button" aria-expanded="false">*/}
                            {/*    <img className="avatar rounded-circle" src="/avatar1.png" alt='' />*/}

                            {/*    <ul className="dropdown-menu">*/}
                            {/*        <li><Link className="dropdown-item" to="/login">Đăng nhập</Link></li>*/}
                            {/*        <li><Link className="dropdown-item" to="/logout">Đăng xuất</Link></li>*/}
                            {/*    </ul>*/}
                            {/*</button>*/}
                                {isLogin ?
                                    <div>
                                        {JSON.parse(localStorage.getItem('User')).id === null ? <></>:
                                            <Link to="/profile">
                                                <img className="avatar rounded-circle" src={account.avatar} alt=''
                                                     style={{marginLeft: "14px"}}/>
                                            </Link>
                                        }
                                        <button className="btn" >
                                            <Link className="dropdown-item" to="/logout">Đăng xuất</Link>
                                        </button>

                                    </div>

                                    :
                                    <button className="btn position-relative" >
                                        <Link className="dropdown-item" to="/login">Đăng nhập</Link>
                                    </button>
                                }
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
                                        {cateList.map((obj, index) => (
                                            <li><Link className="dropdown-item" to={'/category/' + obj.id}>{obj.name}</Link></li>
                                        ))}
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

