import React, {Component, useState, useEffect} from "react"


import "./profileUser.css"
import Navbar from "../navbar/Navbar";
import {get, getwithAuthtication, post} from "../../service/httpservice";
import swal from "sweetalert";
import {Link} from "react-router-dom";



const ProfileUser = (props) => {
    const url_rattings ="/account/"
    const [account , setAccount] = useState([])
    let{id} = JSON.parse(localStorage.getItem('User')).id
    console.log(id)

    function getAccount(){

        getwithAuthtication( url_rattings + JSON.parse(localStorage.getItem('User')).id).then(response => {
            if (response.status === 200) {
                setAccount(response.data);
                console.log(account)
            }
        });
    }


    useEffect(() => {
        getAccount();
    }, []);



    return (
        <>
            <Navbar/>
            <section className="vh-100" style={{backgroundColor: "#f4f5f7"}}>
                <div className="container py-5 h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col col-lg-6 mb-4 mb-lg-0">
                            <div className="card mb-3" style={{borderRadius: ".5rem"}}>
                                <div className="row g-0">
                                    <div className="col-md-4 gradient-custom text-center text-white"
                                         style={{borderTopLeftRadius: ".5rem", borderBottomLeftRadius: ".5rem"}}>
                                        {account.avatar === null?
                                            <img
                                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                                alt="Avatar" className="img-fluid my-5" style={{width: "80px"}}/>
                                            :
                                            <img
                                                src={account.avatar}
                                                alt="Avatar" className="img-fluid my-5" style={{width: "80px"}}/>
                                        }
                                        <h5>{account.username}</h5>
                                        <p>Edit profile</p>
                                        <Link to="/profile/edit">
                                            <i className="fa fa-edit mb-5"></i>
                                        </Link>

                                    </div>
                                    <div className="col-md-8">
                                        <div className="card-body p-4">
                                            <h6>Information</h6>
                                            <div className="mt-0 mb-4">
                                                <div className="row pt-1">
                                                    <div className="col-6 mb-3">
                                                        <h6>Email</h6>
                                                        <p className="text-muted">{account.email}</p>
                                                    </div>
                                                    <div className="col-6 mb-3">
                                                        <h6>Phone</h6>
                                                        <p className="text-muted">{account.phone}</p>
                                                    </div>
                                                </div>
                                                <div className="mt-0 mb-4">
                                                    <div className="row pt-1">
                                                        <div className="col-6 mb-3">
                                                            <h6>Recent</h6>
                                                            <p className="text-muted">{account.username}</p>
                                                        </div>
                                                        <div className="col-6 mb-3">
                                                            <h6>Address</h6>
                                                            <p className="text-muted">{account.address}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
export default ProfileUser;
