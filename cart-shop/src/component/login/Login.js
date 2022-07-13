import React, {Component, useState, useEffect} from "react"
import {Form, FormGroup, Label, Navbar} from 'reactstrap'
import { toast } from "react-toastify";
import swal from 'sweetalert';

import "./login.css"

import {Button, Input} from "antd";
import {post, postlogin} from "../../service/httpservice";
import {checkAdminTrue, checkUserTrue} from "../../service/Authentication";
import {getCookie} from "../../service/Cookie";
import Product from "../product/Product";
import { useNavigate } from 'react-router-dom';
import data from "../Data";
import Help from "../about/help";

const Login = (props) => {
    // const [username, setUsername]= useState("")
    // const [password, setPassword]=useState("")

    const navigate = useNavigate();


    const [lognin, setLognin] = useState({
        username: '',
        password: ''

    })

    const handleUsernameChange = event => {
        lognin.username = event.target.value
        setLognin(lognin)
    };

    const handlePasswordChange = event => {
        lognin.password = event.target.value
        setLognin(lognin)
    };
    const signInHandle = (e) => {
        e.preventDefault();
        postlogin(`/auth/signin`, lognin)
            .then((response) => {
                if (response.status === 200) {
                    console.log(response.data.name)
                    localStorage.setItem("User", JSON.stringify(response.data))

                    document.cookie = `access_token=${response.data.token}; httpOnly: true;  max-age=86400; path=/;`;

                    if(response.data.roles ==="ROLE_ADMIN"){
                        swal({
                            title: "Good job!",
                            text: "You clicked the button!",
                            icon: "success"
                        }).then( navigate('/admin'))

                    }
                    if(response.data.roles ==="ROLE_USER"){
                        swal({
                            title: "Good job!",
                            text: "You clicked the button!",
                            icon: "success"
                        }).then( navigate('/'))
                    }

                }
            }).catch((error) => {
            swal({
                icon: 'error',
                title: error.response.data.message,
                footer: '<a href="">Why do I have this issue?</a>'
            })
            let message = "Sign in failed!";
            if (!error.response) message = "Connection error! Please try again later";
            else {
                console.log(error.response.status);
                switch (error.response.status) {
                    case 401:
                        message = "Login Failed! Check your username and password again";
                        break;
                    default:
                        break;
                }
            }

            toast.error(message, {

                position: toast.POSITION.TOP_RIGHT,
                autoClose: 3000,
            });
        });

    }

    return (
        <>
            <section className="vh-100">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-sm-6 text-black">
                            <div className="px-5 ms-xl-4">
                                <i className="fas fa-crow fa-2x me-3 pt-5 mt-xl-4" style={{color: "#709085"}}></i>
                                <span className="h1 fw-bold mb-0">Logo</span>
                            </div>
                            <div
                                className="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">

                                <form id="signin-form" onSubmit={signInHandle} style={{width: "23rem"}}>
                                    <h3 className="fw-normal mb-3 pb-3" style={{letterSpacing: "1px"}}>Log in</h3>

                                    <div className="form-outline mb-4">
                                        <input
                                            onChange={handleUsernameChange}
                                            type="text"
                                            id="form2Example28"
                                            className="form-control form-control-lg"
                                            required
                                            placeholder="Input your username"
                                            title="Name must contains 2-100 characters"
                                            pattern=".{2,100}"/>
                                        <label className="form-label" htmlFor="form2Example18">Username</label>
                                    </div>

                                    <div className="form-outline mb-4">
                                        <input
                                            onChange={handlePasswordChange}
                                            required
                                            placeholder="Input your password"
                                            title="Password must contains 6-100 characters"
                                            type="password" id="form2Example28"
                                            className="form-control form-control-lg"
                                            pattern=".{6,100}"/>

                                        <label className="form-label" htmlFor="form2Example28">Password</label>
                                    </div>

                                    <div className="pt-1 mb-4">
                                        <button className="btn btn-info btn-lg btn-block" type="submit">Login
                                        </button>
                                    </div>

                                    <p className="small mb-5 pb-lg-2"><a className="text-muted" href="#!">Forgot
                                        password?</a></p>
                                    <p>Don't have an account? <a href="/register" className="link-info">Register here</a>
                                    </p>

                                </form>

                            </div>
                        </div>

                        <div className="col-sm-6 px-0 d-none d-sm-block">
                            <img style={{height:"500px"}}
                                src="https://mona.media/wp-content/uploads/2021/07/ecommerce.png"
                                alt="Login image"/>
                        </div>
                    </div>
                </div>
            </section>
            <Help/>

        </>
    )
}
export default Login;
