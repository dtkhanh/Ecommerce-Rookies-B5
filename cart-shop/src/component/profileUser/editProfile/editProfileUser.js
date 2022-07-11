import React, {Component, useState, useEffect} from "react"


import "../profileUser.css"
import Navbar from "../../navbar/Navbar";
import {get, getwithAuthtication, post, put} from "../../../service/httpservice";
import swal from "sweetalert";
import {Link, useNavigate} from "react-router-dom";
import {toast} from "react-toastify";



const EditProfile = (props) => {
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
    const [editAccount, setEditAccount] = useState({
        username: '',
        password:'123',
        address: '' ,
        phone: '',
        email:'',
        avatar:''
    })
    const handleUsernameChange = event => {
        editAccount.username = event.target.value
        setEditAccount(editAccount)
    };
    const handleAddressChange = event => {
        editAccount.address = event.target.value
        setEditAccount(editAccount)
    };
    const handlePhoneChange = event => {
        editAccount.phone = event.target.value
        setEditAccount(editAccount)
    };
    const handleEmailChange = event => {
        editAccount.email = event.target.value
        setEditAccount(editAccount)
    };
    const [loading,setLoading] = useState(false)

    const  uploadAvatar = async e=>{
        const files = e.target.files
        const data = new FormData()
        data.append('file',files[0])
        data.append('upload_preset','darwin')
        setLoading(true)
        const res = await fetch(
            'https://api.cloudinary.com/v1_1/dkzkr4aqz/image/upload',
            {
                method: 'POST',
                body: data
            }
        )
        const file = await res.json()
        editAccount.avatar = (file.secure_url)
        console.log(editAccount.avatar)
        if(editAccount.avatar == null){
            editAccount.avatar = account.avatar
        }
        setEditAccount(editAccount)
        setLoading(false)

    }
    const navigate = useNavigate();

    const updateAccount = (e) => {
        e.preventDefault();
        if(editAccount.username == ""){
            editAccount.username=account.username
        }
        if(editAccount.address == ""){
            editAccount.address = account.address
        }
        if(editAccount.phone == ""){
            editAccount.phone = account.phone
        }
        if(editAccount.email == ""){
            editAccount.email = account.email
        }
        if(editAccount.avatar == ""){
            editAccount.avatar = account.avatar
        }
        const body = JSON.stringify({
            username: editAccount.username,
            password:'123',
            address: editAccount.address,
            phone: editAccount.phone,
            email:editAccount.email,
            avatar:editAccount.avatar
        });
        console.log(body)
        put('/account/'+JSON.parse(localStorage.getItem('User')).id, body)
            .then((response) => {
                if (response.status === 200) {
                    swal({
                        title: "Edit Category succeeded!",
                        icon: "success"
                    }).then( navigate('/profile'))
                }
            }).catch((error) => {
            console.log(error.response.data.message)
            let message = error.response.message;
            if (!error.response){
                message = "Connection error! Please try again later";
                swal({
                    icon: 'error',
                    title:'Connection error! Please try again later',
                    footer: '<a href="">Why do I have this issue?</a>'
                })
            }
            else {
                switch (error.response.status) {
                    case 400: message = "The product name is already exist!";
                        swal({
                            icon: 'error',
                            title:'The product name is already exist!',
                            footer: '<a href="">Why do I have this issue?</a>'
                        })
                        break;
                    default: break;

                }
            }
            swal({
                icon: 'error',
                title: error.response.data.message,
                footer: '<a href="">Why do I have this issue?</a>'
            })
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
                                <form onSubmit={updateAccount}>
                                <div className="row g-0">
                                    <div className="col-md-4 gradient-custom text-center text-white"
                                         style={{borderTopLeftRadius: ".5rem", borderBottomLeftRadius: ".5rem"}}>

                                        {editAccount.avatar === ''?
                                            <>
                                                {account.avatar === null?
                                                        <img
                                                            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                                            alt="Avatar" className="img-fluid my-5" style={{width: "80px"}}/>
                                                        :
                                                        <img
                                                            src={account.avatar}
                                                            alt="Avatar" className="img-fluid my-5" style={{width: "80px"}}/>
                                                }
                                            </>
                                            :
                                            <img
                                                src={editAccount.avatar}
                                                alt="Avatar" className="img-fluid my-5" style={{width: "80px"}}/>
                                        }
                                        <div>
                                            <input
                                                type ="file"
                                                onChange={uploadAvatar}
                                                className=" fa fa-image"
                                            />
                                        </div>
                                        <h5>    </h5>
                                        <h5 style={{color:"black"}}>{account.username}</h5>


                                    </div>
                                    <div className="col-md-8">
                                        <div className="card-body p-4">
                                            <h6>Information</h6>
                                                <div className="mt-0 mb-4">
                                                    <div className="row pt-1">
                                                        <div className="col-6 mb-3">
                                                            <h6>Email</h6>
                                                            <input type="email"
                                                                   onChange={handleEmailChange}
                                                                   id="form3Example3cg"
                                                                   className="form-control form-control-lg"
                                                                   placeholder="email"
                                                                   defaultValue={account.email}
                                                                   required="required"
                                                                   minLength="10"
                                                            />

                                                        </div>
                                                        <div className="col-6 mb-3">
                                                            <h6>Phone</h6>
                                                            <input type="number"
                                                                   onChange={handlePhoneChange}
                                                                   id="form3Example3cg"
                                                                   className="form-control form-control-lg"
                                                                   defaultValue={account.phone}
                                                                   required="required"
                                                                   minLength="8"
                                                            />

                                                        </div>
                                                    </div>
                                                    <div className="mt-0 mb-4">
                                                        <div className="row pt-1">
                                                            <div className="col-6 mb-3">
                                                                <h6>Name</h6>
                                                                <input type="text"
                                                                       id="form3Example3cg"
                                                                       className="form-control form-control-lg"
                                                                       defaultValue={account.username}
                                                                       onChange={handleUsernameChange}
                                                                       required="required"
                                                                       minLength="10"
                                                                       maxLength="30"
                                                                />
                                                            </div>
                                                            <div className="col-6 mb-3">
                                                                <h6>Address</h6>
                                                                <input type="text"
                                                                       onChange={handleAddressChange}
                                                                       id="form3Example3cg"
                                                                       className="form-control form-control-lg"
                                                                       defaultValue={account.address}
                                                                       required="required"
                                                                       minLength="10"
                                                                       maxLength="50"
                                                                />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div className="mt-0 mb-4">
                                                        <div className="row pt-1">
                                                            <div className="col-6 mb-3">
                                                                <button className="btn btn-primary"
                                                                        type="submit">Save
                                                                </button>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}
export default EditProfile;
