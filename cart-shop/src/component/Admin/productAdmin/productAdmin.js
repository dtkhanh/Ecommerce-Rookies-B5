import React, {useEffect, useState} from "react"
import {Link} from "react-router-dom";
import Sidebar from "../siderBar/sideBar"
import NavbarAdmin from "../navBarAdmin/Navbaradmin";
import {Table} from "antd";
import {get, getwithAuthtication, post} from "../../../service/httpservice";
import Rating from "../../../Utils/Rating/Rating";
import {toast} from "react-toastify";



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
                        {productListCate ?
                        <table className="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">NAME</th>
                                <th scope="col">RATTING</th>
                                <th scope="col">PRICE</th>
                                <th scope="col">DESCRIPTION</th>
                                <th scope="col">IMAGE</th>
                                <th scope="col">CREATED DATE</th>
                                <th scope="col">UPDATED DATE</th>
                                <th scope="col">EDIT</th>
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
                                    <td>{(obj.description).substring(0,12) }...</td>
                                    <td><img src={obj.imageproduct} style={{width:"100px", height:"100px"}}/></td>
                                    <td>{obj.createdate}</td>
                                    <td>{obj.updatedate}</td>
                                    <td>
                                        <div type="button" style={{}}>
                                            <Link to={`/admin/product/${obj.id}`}  className="btn btn-outline-danger fa fa-pencil">
                                            </Link>
                                        </div>
                                    </td>
                                </tbody>
                            ))}

                        </table>
                            :
                            <table className="table table-bordered">
                                <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">NAME</th>
                                    <th scope="col">RATTING</th>
                                    <th scope="col">PRICE</th>
                                    <th scope="col">DESCRIPTION</th>
                                    <th scope="col">IMAGE</th>
                                    <th scope="col">CREATED DATE</th>
                                    <th scope="col">UPDATED DATE</th>
                                    <th scope="col">BUTTON</th>
                                </tr>

                                </thead>
                                {productListCate.map((obj, index) => (
                                    <tbody className="table-product" style={{textAlign:"center" ,
                                        backgroundColor:"#d2f8fb",
                                        textAlignLast:"inherit",
                                        fontSize:"20px",
                                        color: "#7A7777"
                                    }}>
                                    <td  scope="row">{obj.id}</td>
                                    <td>{obj.title}</td>
                                    <td>{obj.ratting}</td>
                                    <td>{obj.price}vnd</td>
                                    <td>{(obj.description).substring(0,12) }...</td>
                                    <td><img src={obj.imageproduct} style={{width:"100px", height:"100px"}}/></td>
                                    <td>{obj.createdate}</td>
                                    <td>{obj.updatedate}</td>
                                    <td>
                                        <Link to={"/" + `${obj.id}`} className="nav-link " >
                                           edit
                                        </Link>
                                    </td>
                                    </tbody>
                                ))}

                            </table>
                        }
                    </div>
                </div>
            </div>
        </>
    )
}

