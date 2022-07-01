import React, { useRef, useState } from 'react';
import { Upload, Modal } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import {useNavigate} from "react-router-dom";

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
const AddProduct = () => {

    const navigate = useNavigate();

    const [form, setForm] = useState({
        title : '',
        photos : [],
    })

    const handleTitleChange = event => {
        form.title = event.target.value
        setForm(form)
    };

    const handlePhotosChange =(photo) => {
        form.photos= photo
        setForm(form)
    };
    const handleSubmit = async event => {
        event.preventDefault();
        try {
            console.log(form)

        } catch (error) {

            console.log('Error', error)
        }
        navigate("/movie");
    };

    return (
        <div>
            <section className="info1" >
                <form className="row" onSubmit={handleSubmit}>

                    <div className="mb-1">
                        <div className="title">
                            <h2 className="text-title">Tên Sản phẩm</h2>
                            <div className="line"></div>
                        </div>
                        <input type="text" className="form-control" name="name_product" placeholder="Tên sản phẩm" onChange={handleTitleChange}/>
                    </div>


                    <div className="mb-1">
                        <div className="title">
                            <h2 className="text-title">Hình ảnh </h2>
                            <div className="line"></div>
                        </div>
                        <div className="container">
                            <div className="MainDiv">
                                <div className="container">
                                    <PicturesWall setphoto={handlePhotosChange}/>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div className="mb-1">
                        <button type="submit" className="btn btn-outline-primary d-block w-100">Đăng</button>
                    </div>

                </form>
            </section>
        </div>
    )
}
export default AddProduct;

