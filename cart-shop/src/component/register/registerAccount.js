import React, { useState } from "react"
import "./register.css"
import Navbar from "../navbar/Navbar"
import {post, postlogin} from "../../service/httpservice";
import swal from "sweetalert";
import {useNavigate} from "react-router-dom";



const Register = () => {
    const url ='/auth/signup'
    const [isChecked, setIsChecked] = useState(false);
    const navigate = useNavigate();

    const [account, setAccount] = useState({
        username: '',
        name:'',
        email:'',
        password:'',
        repassword:'',
        role: '1'
    })

    const handleusernameChange = event => {
        account.username = event.target.value
        setAccount(account)
    };
    const handlenameChange = event => {
        account.name = event.target.value
        setAccount(account)
    };
    const handleemailChange = event => {
        account.email = event.target.value
        setAccount(account)
    };
    const handlepassword= event => {
        account.password = event.target.value
        setAccount(account)
    };
    const handlerepassword= event => {
        account.repassword = event.target.value
        setAccount(account)
    };
    const handleOnChange = () => {
        setIsChecked(!isChecked);
    };


    const postAccount = (e) => {
        e.preventDefault();
        if(account.password === account.repassword) {
            if (isChecked) {
                const body = JSON.stringify({
                    username: account.username,
                    name: account.name,
                    email: account.email,
                    password: account.password,
                    role: account.role
                });
                console.log(body)
                postlogin(url, body)
                    .then((response) => {
                        if (response.status === 200) {
                            swal({
                                title: "Register account successfully!",
                                text: "You clicked the button!",
                                icon: "success"
                            }).then(navigate('/login'))

                        }
                    }).catch((error) => {
                    let message = error.response.message;
                    if (!error.response) {
                        swal({
                            icon: 'error',
                            title: 'Connection error! Please try again later',
                            footer: '<a href="">Why do I have this issue?</a>'
                        })
                    } else {
                        switch (error.response.status) {
                            case 400:
                                message = "The product name is already exist!";
                                swal({
                                    icon: 'error',
                                    title: 'The product name is already exist!',
                                    footer: '<a href="">Why do I have this issue?</a>'
                                })
                                break;
                            default:
                                break;

                        }
                    }
                    swal({
                        icon: 'error',
                        title: error.response.data.message,
                        footer: '<a href="">Why do I have this issue?</a>'
                    })
                });
            } else {
                swal({
                    icon: 'error',
                    title: "You can agree all statements in ",
                    footer: '<a href="">Why do I have this issue?</a>'
                })
            }
        }
        else{
            swal({
                icon: 'error',
                title: "You confirm password false ",
                footer: '<a href="">Why do I have this issue?</a>'
            })
        }

    }



    return (
        <>
            <Navbar/>
            <section className="vh-100 bg-image"
                     style={{backgroundImage: "url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp')"}}>
                <div className="mask d-flex align-items-center h-100 gradient-custom-3">
                    <div className="container h-100">
                        <div className="row d-flex justify-content-center align-items-center h-100">
                            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                                <div className="card" style={{borderRadius: "15px"}}>
                                    <div className="card-body p-5">
                                        <h2 className="text-uppercase text-center mb-5">Create an account</h2>

                                        <form onSubmit={postAccount}>

                                            <div className="form-outline mb-4">
                                                <label className="form-label" htmlFor="form3Example1cg">Your
                                                    User name</label>
                                                <input
                                                    onChange={handleusernameChange}
                                                    type="text"
                                                    id="form3Example1cg"
                                                    placeholder="user name"
                                                    className="form-control form-control-lg"
                                                    required="required"
                                                    minLength="8"
                                                />

                                            </div>

                                            <div className="form-outline mb-4">
                                                <label className="form-label" htmlFor="form3Example3cg">Your
                                                    Name</label>
                                                <input type="text"
                                                       onChange={handlenameChange}
                                                       id="form3Example3cg"
                                                       className="form-control form-control-lg"
                                                       placeholder="name"
                                                       required="required"
                                                       minLength="10"
                                                />

                                            </div>
                                            <div className="form-outline mb-4">
                                                <label className="form-label" htmlFor="form3Example3cg">Your
                                                    Email</label>
                                                <input type="email"
                                                       onChange={handleemailChange}
                                                       id="form3Example3cg"
                                                       className="form-control form-control-lg"
                                                       placeholder="email"
                                                       required="required"
                                                       minLength="20"
                                                />

                                            </div>


                                            <div className="form-outline mb-4">
                                                <label className="form-label" htmlFor="form3Example4cg">Password</label>
                                                <input type="password"
                                                       onChange={handlepassword}
                                                       id="form3Example4cg"
                                                       className="form-control form-control-lg"
                                                       placeholder="password"
                                                       required="required"
                                                       minLength="6"
                                                />

                                            </div>

                                            <div className="form-outline mb-4">
                                                <label className="form-label" htmlFor="form3Example4cdg">Repeat your
                                                    password</label>
                                                <input type="password"
                                                       onChange={handlerepassword}
                                                       id="form3Example4cdg"
                                                       className="form-control form-control-lg"
                                                       placeholder="confirm password"
                                                       required="required"
                                                       minLength="6"
                                                />
                                            </div>

                                            <div className="form-check d-flex justify-content-center mb-5">
                                                <input
                                                    type="checkbox"
                                                    onChange={handleOnChange}
                                                    className="form-check-input me-2"
                                                    value=""
                                                    id="form2Example3cg"/>
                                                <label className="form-check-label" htmlFor="form2Example3g">
                                                    I agree all statements in <a href="#!" className="text-body"><u>Terms
                                                    of service</u></a>
                                                </label>
                                            </div>

                                            <div className="d-flex justify-content-center">
                                                <button type="submit"
                                                        className="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register
                                                </button>
                                            </div>

                                            <p className="text-center text-muted mt-5 mb-0">Have already an account? <a
                                                href="/login"
                                                className="fw-bold text-body"><u>Login here</u></a></p>

                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default Register;