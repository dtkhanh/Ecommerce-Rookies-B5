import React, {useEffect, useState} from "react"
import {Link} from "react-router-dom";
import Sidebar from "../siderBar/sideBar"
import NavbarAdmin from "../navBarAdmin/Navbaradmin";
import {Table} from "antd";
import {deleteurl, get, getwithAuthtication, post} from "../../../service/httpservice";
import Rating from "../../../Utils/Rating/Rating";
import {toast} from "react-toastify";
import swal from "sweetalert";



export default function ProductAdmin()  {
    const endpoint = "/products";
    const [productList, setProductList] = useState([]);
    const [cateList, setCateList] = useState([]);
    const [productListCate, setlistProduct] = useState([]);

    function getProductList(){
        get(endpoint ).then(response => {
            if (response.status === 200) {
                setProductList(response.data);
            }
        });
    }
    function getListCategory(){
        get('/category').then(response =>{
            if(response.status === 200){
                setCateList(response.data)
            }
        });
    }
    const [category, setCate] = useState({
        id : ''
    })
    const handleCategoryChange = event => {
        category.id = event.target.value
        setCate(category)
    };

    const getProductByCategory = (e) => {
        e.preventDefault();
        get(endpoint+'/category/'+category.id).then(response =>{
            if(response.status === 200){
                setlistProduct(response.data)
            }
        }).catch((error) => {

        });
    }



    useEffect(() => {
        getListCategory();
        getProductList();
        // getListCate();
    }, []);

    const handleDelete = (item) => {
        swal({
            title: "You want to delete a Product with the name: "+ item.title+" ?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    console.log(item)
                    deleteurl( `/products/admin/` + item.id)
                        .then((response) => {
                            if (response.status === 200) {
                                swal({
                                    title: "Delete Product succeeded!",
                                    icon: "success"
                                })
                                getProductList();

                            }
                        }).catch((error) => {
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

                <div className="col-lg-10 p-0" style={{ paddingTop: "20px"}}>
                    <div className = "product-background" style={{marginTop : "35px", textAlign:"center"}}>
                        <h2>PRODUCT MANAGEMENT</h2>

                        <div className="modal fade" id="exampleModal" tabIndex="-1"
                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <h5 className="modal-title" id="exampleModalLabel">Modal title</h5>
                                        <button type="button" className="close" data-dismiss="modal"
                                                aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div className="modal-body">
                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-secondary"
                                                data-dismiss="modal">Close
                                        </button>
                                        <button type="button" className="btn btn-primary">Save changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <form onSubmit={getProductByCategory}>
                        {/*<div className="mb-4">*/}
                        {/*    <label htmlFor="product_price" className="form-label">*/}
                        {/*        Category*/}
                        {/*    </label>*/}
                        {/*    <select  onChange={handleCategoryChange} id="category-select" name="category" form="edit-form" style={{marginLeft:"15px"}}>*/}
                        {/*        <option key="" value="1"></option>*/}
                        {/*        {cateList.map((cate) => (*/}
                        {/*            (cate.name !== "") ? <option key={cate.name} value={cate.id}>{cate.name}</option> : null*/}
                        {/*        ))}*/}
                        {/*    </select>*/}
                        {/*</div>*/}
                            {/*<button type="submit" className="btn btn-primary">*/}
                            {/*    Save*/}
                            {/*</button>*/}
                        </form>
                        <br/>

                        <div className="button-add" style={{ textAlign:"right", paddingBottom:"20px"}}>

                            <button  className="btn btn-info btn-lg btn-block" type="submit" style={{color:"red"}}>
                                <Link to="/admin/product/add" className="nav-link " >
                                    Add Product
                                </Link>
                            </button>

                        </div>
                        <table className="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">NAME</th>
                                <th scope="col">RATTING</th>
                                <th scope="col">PRICE</th>
                                <th scope="col">IMAGE</th>
                                <th scope="col">CREATED DATE</th>
                                <th scope="col">UPDATED DATE</th>
                                <th scope="col">EDIT</th>
                                <th scope="col">DELETE</th>
                            </tr>

                            </thead>
                            {productList.map((obj, index) => (
                                <tbody className="table-product" style={{textAlign:"center" ,
                                    // backgroundColor:"#d2f8fb",
                                    // textAlignLast:"inherit",
                                    // fontSize:"20px",
                                    // color: "#7A7777"
                                }}>
                                    <td  scope="row">{obj.id}</td>
                                    <td>{obj.title}</td>
                                    <td>{obj.ratting}</td>
                                    <td>{obj.price}vnd</td>
                                    <td><img src={obj.imageproduct} style={{width:"100px", height:"100px"}}/></td>
                                    <td>{obj.createdate}</td>
                                    <td>{obj.updatedate}</td>
                                    <td>
                                        <div type="button" style={{}}>
                                            <Link to={`/admin/product/${obj.id}`}  className="btn btn-outline-danger fa fa-pencil">
                                            </Link>
                                        </div>
                                    </td>
                                    <td className="button" >

                                        <button onClick={()=> handleDelete(obj)} style={{marginRight:"20px" , marginTop: "8px"}} type="button" className="btn btn-outline-danger fa fa-trash">
                                        </button>

                                    </td>
                                </tbody>
                            ))}

                        </table>
                    </div>
                </div>
            </div>
        </>
    )
}

