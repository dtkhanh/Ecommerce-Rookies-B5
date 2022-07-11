import React, {useEffect, useState} from "react";
import {get, post, put} from "../../../../service/httpservice";
import swal from "sweetalert";
import {toast} from "react-toastify";
import NavbarAdmin from "../../navBarAdmin/Navbaradmin";
import Sidebar from "../../siderBar/sideBar";
import {useParams} from "react-router-dom";


export default function EditCategory()  {
    const url ='/category'
    const [category, setcategory] = useState({
        categoryname: '',
    })
    let {id } = useParams();
    const [categoryId , setcategoryId] = useState([])

    function getListCategory(){
        get('/category/' + id ).then(response =>{
            if(response.status === 200){
                setcategoryId(response.data)
            }
        });
    }

    useEffect(() => {
        getListCategory();
    }, []);

    const handlenameChange = event => {
        category.categoryname = event.target.value
        setcategory(category)
    };


    const editCategory = (e) => {
        e.preventDefault();
        if(category.categoryname===""){
            category.categoryname =categoryId.name;
        }
        const body = JSON.stringify({
            categoryname: category.categoryname,
        });
        put(url + `/admin/`+id, body)
            .then((response) => {
                if (response.status === 200) {
                    swal({
                        title: "Edit Category succeeded!",
                        icon: "success"
                    })
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
    }


    return (
        <div>
            <NavbarAdmin/>
            <div className="row">
                <Sidebar/>

                <div className="col-lg-8 p-0 " style={{ paddingTop: "10px" , marginLeft:"100px"}}>
                    <section className="content-main" style={{ maxWidth: "1200px" }}>
                        <form onSubmit={editCategory}>
                            <div className="content-header" style={{ marginTop: "60px" , color:"red" , textAlign:"center"}}>
                                <h2 className="content-title" >Add product</h2>
                            </div>
                            <div className="card mb-4 shadow-sm">
                                <div className="card-body">
                                    <div className="mb-4">
                                        <label htmlFor="product_title" className="form-label">
                                            Product title
                                        </label>
                                        <input
                                            defaultValue={categoryId.name}
                                            onChange={handlenameChange}
                                            type="text"
                                            placeholder="Type here"
                                            className="form-control"
                                            id="product_title"
                                            required
                                        />
                                    </div>
                                </div>
                            </div>

                            <div>
                                <button type="submit" className="btn btn-primary" style={{textAlign:"center"}}>
                                    Save
                                </button>
                            </div>
                        </form>

                    </section>
                </div>
            </div>

        </div>
    )
}