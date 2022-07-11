import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";
import NavbarAdmin from "../navBarAdmin/Navbaradmin";
import Sidebar from "../siderBar/sideBar";
import {deleteurl, get, getwithAuthtication, post} from "../../../service/httpservice";
import swal from 'sweetalert';
import Modal from 'react-modal';
import {toast} from "react-toastify";
import {cloudinaryUpload} from "../../../service/upload";


const CategoriesAdmin = () => {
    const url ='/category'

    const [categorylist , setCategory] = useState([])
    const [image,setImage] = useState('')
    const [loading,setLoading] = useState(false)
    let subtitle;
    const [modalIsOpen, setIsOpen] = React.useState(false);

    function openModal() {
        setIsOpen(true);
    }

    function afterOpenModal() {
        // references are now sync'd and can be accessed.
        subtitle.style.color = '#f00';
    }

    function closeModal() {
        setIsOpen(false);
    }


    function getListCategory(){
        get('/category').then(response =>{
            if(response.status === 200){
                setCategory(response.data)
            }
        });
    }
        useEffect(() => {
        getListCategory();
    }, []);

    const handleDelete = (item) => {
        swal({
            title: "You want to delete a category with the name: "+ item.name+" ?",
            text: "Once deleted, you will lose all products in this category!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    console.log(item)
                    deleteurl(url + `/admin/` + item.id)
                        .then((response) => {
                            if (response.status === 200) {
                                swal({
                                    title: "Add product succeeded!",
                                    icon: "success"
                                })
                                getListCategory();

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
                        toast.error(message, {
                            position: toast.POSITION.TOP_RIGHT,
                            autoClose: 3000,
                        });
                    });
                } else {
                    swal("Your imaginary file is safe!");
                }
            });
    };

    return (
        <>
            <NavbarAdmin/>
            <div className="row">
                <Sidebar/>


                <div className="col-lg-10 p-0" style={{ paddingTop: "40px"}}>
                    <div className="button-add" style={{ textAlign:"center", paddingBottom:"20px", paddingTop:"35px" }}>
                        <h2>CATEGORY MANAGEMENT</h2>

                        <div  style={{height:"50px",color:"red", marginTop:"35px", width:"250px", textAlign:"right"}}>

                            <Link to="/admin/category/add" className="btn btn-info btn-lg btn-block nav-link " >
                                Add Category
                            </Link>
                        </div>

                    </div>

                    <table className="table" style={{ textAlign:"center"}}>

                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th className="text-end">Edit</th>
                            <th className="text-end">Delete</th>

                        </tr>
                        </thead>
                        {/* Table Data */}
                        <tbody>
                        {categorylist.map((obj, index) => (
                            <tr>
                                <td>{obj.id}</td>
                                <td>
                                    <b>{obj.name}</b>
                                </td>
                                <td>{obj.name}</td>
                                <td className="text-end">
                                    <Link to={`/admin/category/${obj.id}`}  className="btn btn-warning fa fa-pencil">
                                    </Link>
                                </td>
                                <td className="text-end">

                                    <button type="button" onClick={()=> handleDelete(obj)} className="btn btn-outline-danger fa fa-trash">
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

export default CategoriesAdmin;
