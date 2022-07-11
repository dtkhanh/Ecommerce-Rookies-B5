import React, {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import NavbarAdmin from "../navBarAdmin/Navbaradmin";
import Sidebar from "../siderBar/sideBar";
import {deleteurl, get, getwithAuthtication, post, put} from "../../../service/httpservice";
import swal from 'sweetalert';
import Modal from 'react-modal';
import {toast} from "react-toastify";


const AccountAdmin = () => {
    const url ='/account/admin'
    const [accountList , setAccountList] = useState([])
    const navigate = useNavigate();
    function getListAccount(){
        getwithAuthtication(url).then(response =>{
            if(response.status === 200){
                setAccountList(response.data)
            }
        });
    }
    useEffect(() => {
        getListAccount();
    }, []);

    const updateStatus = (item) => {

        swal({
            title: "You want to change a status user with name: "+ item.username+" ?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    console.log(item)
                    let body ;
                    if(item.check_activity) {
                        body = JSON.stringify({
                            set_activity : "0",
                        });
                    }
                    else{
                        body = JSON.stringify({
                            set_activity : "1",
                        });
                    }
                    put('/account/set_activity/'+ item.id, body)
                        .then((response) => {
                            if (response.status === 200) {
                                swal({
                                    title: "Update Account succeeded!",
                                    icon: "success"
                                })
                                    // .then( window.location.reload(true))
                                getListAccount();

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
            });
    };

    return (
        <>
            <NavbarAdmin/>
            <div className="row p-0">
                <Sidebar/>


                <div className="col-lg-10 p-0" style={{ paddingTop: "40px"}}>
                    <div className="button-add" style={{ textAlign:"center", paddingBottom:"20px", paddingTop:"35px" }}>
                        <h2>ACCOUNT MANAGEMENT</h2>


                    </div>

                    <table className="table" style={{ textAlign:"center"}}>

                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>User Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th className="text-end">Status</th>
                            <th className="text-end">Edit</th>

                        </tr>
                        </thead>
                        {/* Table Data */}
                        <tbody>
                        {accountList.map((obj, index) => (
                            <tr>
                                <td>{obj.id}</td>
                                <td>
                                    <b>{obj.username}</b>
                                </td>
                                <td>{obj.email}</td>
                                <td>{obj.phone}</td>
                                <td>{obj.address}</td>
                                <td>
                                    {obj.check_activity === true ?
                                    <input className="form-check-input me-3" type="checkbox" value="" aria-label="..." checked disabled/>
                                        :
                                    <input className="form-check-input me-3" type="checkbox" value="" aria-label="..." disabled/>
                                    }
                                </td>
                                <td className="text-end">

                                    <button type="button" onClick={()=> updateStatus(obj)} className="btn btn-outline-danger fa fa-pencil">
                                    </button>

                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>

            </div>


        </>
    );
};

export default AccountAdmin;
