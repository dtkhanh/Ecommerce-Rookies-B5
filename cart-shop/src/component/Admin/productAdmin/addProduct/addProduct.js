import React, { useRef, useState } from 'react';
import { Upload, Modal } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import {Link, useNavigate} from "react-router-dom";
import NavbarAdmin from "../../navBarAdmin/Navbaradmin";
import Sidebar from "../../siderBar/sideBar";
import {get, getwithAuthtication, post} from "../../../../service/httpservice"
import {useEffect} from "react";
import {toast} from "react-toastify";
import swal from "sweetalert";


function getBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
}
const PicturesWall = (props) => {
    const [state, setState] = useState({
        previewVisible: false,
        previewImage: '',
        previewTitle: '',
        fileList: [],
    })

    const handleCancel = () => {
        state.previewVisible = false
        setState(state)
    }

    //Image Preview
    const handlePreview = async file => {
        if (!file.url && !file.preview) {
            file.preview = await getBase64(file.originFileObj);
        }
        state.previewImage = file.url || file.preview
        state.previewVisible = true
        state.previewTitle = file.name || file.url.substring(file.url.lastIndexOf('/') + 1)
        setState(state);
    };

    const handleChange = async ({fileList}) => {
        console.log(fileList)
        setState({fileList})
        var array_img=[]
        for(let i=0 ; i <fileList.length ; i++){
            var url_image =  await getBase64 (fileList[i].originFileObj)
            console.log(url_image)
            array_img.push(url_image)
        }
        props.setphoto(array_img)
    }


    return (
        <>
            <Upload
                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                listType="picture-card"
                fileList={state.fileList}
                onPreview={handlePreview}
                onChange={handleChange}

            >
                {state.fileList.length >= 8 ? null : (
                    <div>
                        <PlusOutlined/>
                        <div style={{marginTop: 8}}>Upload</div>
                    </div>
                )}

            </Upload>
            <Modal
                visible={state.previewVisible}
                title={state.previewTitle}
                footer={null}
                onCancel={handleCancel}
            >

                <img alt="example" style={{width: '100%'}} src={state.previewImage}/>
            </Modal>
        </>
    );
}
export default function AddProduct()  {
    const endpoint = "/products";
    const [imageProduct,setImage] = useState('')
    const [loading,setLoading] = useState(false)



    const navigate = useNavigate();

    const [cateList, setCateList] = useState([])
    const [productList, setProductList] = useState([]);

    const option = ["Idcate","NameCategory"]
    function getProductList(){
        get( '/products').then(response => {
            if (response.status === 200) {
                setProductList(response.data);
                console.log("successsssssssssss")
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
    useEffect(() => {
        getProductList();
        getListCategory();
    }, []);
    const [form, setForm] = useState({
        title : '',
        photos : [],
    })


    const [product, setproduct] = useState({
        name: '',
        description: '',
        price: 0.0 ,
        image: [],
        categoryid:''
    })

    const handletitleChange = event => {
        product.name = event.target.value
        setproduct(product)
    };

    const handleDescriptionChange = event => {
        product.description = event.target.value
        setproduct(product)
    };

    const handlePriceChange = event => {
        product.price = parseFloat(event.target.value)
        setproduct(product)
    };
    const handleCategoryChange = event => {
        product.categoryid = event.target.value
        setproduct(product)
    };


    const postProduct = (e) => {
        e.preventDefault();
        console.log(product)
        const body = JSON.stringify({
            name: product.name,
            price: product.price,
            categoryid: product.categoryid,
            description: product.description,
            image: product.image
        });
        console.log(body);
        post(endpoint + `/admin`, body)
            .then((response) => {
                if (response.status === 200) {
                    swal({
                        title: "Add product succeeded!",
                        icon: "success"
                    })
                    getProductList();

                }
            }).catch((error) => {
            let message = "Add product failed!";
            if (!error.response) message = "Connection error! Please try again later";
            else {
                console.log(error.response.status);
                switch (error.response.status) {
                    case 400: message = "The product name is already exist!";
                        swal({
                            icon: 'error',
                            title:'Connection error! Please try again later',
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
    const handleImageChange = event => {
        product.image.push(event.target.value)
        setproduct(product)
    };
    const  uploadImage = async e=>{
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
        setImage(file.secure_url)
        product.image.push(file.secure_url)
        setproduct(product)
        setLoading(false)

    }
    const handleDelete = (item) => {
        swal({
            title: "You want to delete one picture: " ,
            text: "Once deleted, you will lose all products in this category!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    product.image.pop()
                    setproduct(product)
                    {product.image.map(obj => (
                        <img src={obj} style={{width:'100px'}}   />
                    ))}
                }
                else {
                    swal("Your imaginary file is safe!");
                }
            });
    };




    return (
        <div>
            <NavbarAdmin/>
            <div className="row">
                <Sidebar/>

                <div className="col-lg-8 p-0 " style={{ paddingTop: "10px" , marginLeft:"100px"}}>
            <section className="content-main" style={{ maxWidth: "1200px" }}>
                <form onSubmit={postProduct}>
                    <div className="content-header" style={{ marginTop: "60px" , color:"red"}}>
                        <Link to="/products" className="btn btn-danger text-white">
                            Go to products
                        </Link>
                        <h2 className="content-title" >Add product</h2>
                    </div>

                    <div className="row mb-4">
                        <div className="col-xl-8 col-lg-8">
                            <div className="card mb-4 shadow-sm">
                                <div className="card-body">
                                    <div className="mb-4">
                                        <label htmlFor="product_title" className="form-label">
                                            Product title
                                        </label>
                                        <input
                                            onChange={handletitleChange}
                                            type="text"
                                            placeholder="Type here"
                                            className="form-control"
                                            id="product_title"
                                            required
                                        />
                                    </div>
                                    <div className="mb-4">
                                        <label htmlFor="product_price" className="form-label">
                                            Price
                                        </label>
                                        <input
                                            onChange={handlePriceChange}
                                            type="number"
                                            placeholder="Type here"
                                            className="form-control"
                                            id="product_price"
                                            required
                                        />
                                    </div>

                                    <div className="mb-4">
                                        <label htmlFor="product_price" className="form-label">
                                            Category
                                        </label>
                                        <select  onChange={handleCategoryChange} id="category-select" name="category" form="edit-form" style={{marginLeft:"15px"}}>
                                            <option key="" value="1"></option>
                                            {cateList.map((cate) => (
                                                (cate.name !== "") ? <option key={cate.name} value={cate.id}>{cate.name}</option> : null
                                            ))}
                                        </select>
                                    </div>
                                    <div className="mb-4">
                                        <label className="form-label">Description Product</label>
                                        <textarea
                                            onChange={handleDescriptionChange}
                                            placeholder="Type here"
                                            className="form-control"
                                            rows="7"
                                            required
                                        ></textarea>
                                    </div>
                                    <div className="mb-4">
                                        <label className="form-label">Images</label>
                                        {/*<input*/}
                                        {/*    type ="file"*/}
                                        {/*    name="file"*/}
                                        {/*    placeholder="Upload an Image"*/}
                                        {/*    onChange={uploadImage}*/}
                                        {/*/>*/}
                                        {/*{loading ? (*/}
                                        {/*    <h3>Loading...</h3>*/}
                                        {/*): (*/}
                                        {/*    <img src={image} style={{width:'300px'}}/>*/}
                                        {/*)}*/}
                                        {product.image.length >= 5 ?

                                            <div>
                                                <button type="button" onClick={()=> handleDelete(product)} className="btn btn-outline-danger fa fa-trash">
                                                </button>
                                            </div>
                                            : (
                                            <div>
                                                <input
                                                    type ="file"
                                                    name="file"
                                                    placeholder="Upload an Image"
                                                    onChange={uploadImage}
                                                    className="form-control"
                                                    required
                                                />
                                            </div>
                                        )}

                                        {product.image.map(obj => (
                                            <img src={obj} style={{width:'100px'}}   />
                                        ))}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <button type="submit" className="btn btn-primary">
                            Save
                        </button>
                    </div>
                </form>

            </section>
                </div>
            </div>
            {/*<section className="info1" >*/}
            {/*    <form className="row" onSubmit={handleSubmit}>*/}

            {/*        <div className="mb-1">*/}
            {/*            <div className="title">*/}
            {/*                <h2 className="text-title">Tên Sản phẩm</h2>*/}
            {/*                <div className="line"></div>*/}
            {/*            </div>*/}
            {/*            <input type="text" className="form-control" name="name_product" placeholder="Tên sản phẩm" onChange={handleTitleChange}/>*/}
            {/*        </div>*/}


            {/*        <div className="mb-1">*/}
            {/*            <div className="title">*/}
            {/*                <h2 className="text-title">Hình ảnh </h2>*/}
            {/*                <div className="line"></div>*/}
            {/*            </div>*/}
            {/*            <div className="container">*/}
            {/*                <div className="MainDiv">*/}
            {/*                    <div className="container">*/}
            {/*                        <PicturesWall setphoto={handlePhotosChange}/>*/}
            {/*                    </div>*/}
            {/*                </div>*/}
            {/*            </div>*/}

            {/*        </div>*/}
            {/*        <div className="mb-1">*/}
            {/*            <button type="submit" className="btn btn-outline-primary d-block w-100">Đăng</button>*/}
            {/*        </div>*/}

            {/*    </form>*/}
            {/*</section>*/}
        </div>
    )
}
