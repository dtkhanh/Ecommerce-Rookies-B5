import React, {Component, useState} from "react"
import Navbar from "../navbar/Navbar"
import './cartProduct.css'
import {get, getwithAuthtication} from "../../service/httpservice";
import {useEffect} from "react";
export default function CartProduct()  {
    const [cart, setCart] = useState([])
    const [product, setProduct] = useState([])
    const [numberProduct,setNumberProduct] = useState([])
    function getCart(){
        getwithAuthtication('/cart/account/'+ JSON.parse(localStorage.getItem('User')).id).then(response =>{
            if(response.status === 200){
                setCart(response.data)
                console.log(response.data)
                setProduct(response.data.products)
            }
        });

    }
    function getNumberProduct(){
            getwithAuthtication('/orderDetails/account/'+ JSON.parse(localStorage.getItem('User')).id ).then(response =>{
                if(response.status === 200){
                    setNumberProduct(response.data)
                }
            })
    }


    useEffect(() => {
        getNumberProduct()
        getCart();
    }, []);

    function getName(tr){
        return tr.substring(0,12)+"..."
    }



    console.log(numberProduct)

    return (
        <>
            <Navbar/>
            <section className="h-100 h-custom" style={{backgroundColor: "#d2c9ff"}}>
                <div className="container py-5 h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-12">
                            <div className="card card-registration card-registration-2" style={{borderRadius: "15px"}}>
                                <div className="card-body p-0">
                                    <div className="row g-0">
                                        <div className="col-lg-8">
                                            <div className="p-5">
                                                <div className="d-flex justify-content-between align-items-center mb-5">
                                                    <h1 className="fw-bold mb-0 text-black">Shopping Cart</h1>
                                                    <h6 className="mb-0 text-muted">3 items</h6>
                                                </div>
                                                {product.map(obj=>(
                                                    <div className="my-4">
                                                        <div
                                                            className="row mb-4 d-flex justify-content-between align-items-center">
                                                            <div className="col-md-2 col-lg-2 col-xl-2">
                                                                <img
                                                                    src={obj.imageproduct}
                                                                    className="img-fluid rounded-3"
                                                                    alt="Cotton T-shirt"></img>
                                                            </div>
                                                            <div className="col-md-3 col-lg-3 col-xl-3">
                                                                <h6 className="text-muted">{obj.title}</h6>
                                                                <h6 className="text-black mb-0">{getName(obj.description)}</h6>
                                                            </div>
                                                            <div className="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                                <button className="btn btn-link px-2"
                                                                        onClick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                                                    <i className="fa fa-minus"></i>
                                                                </button>

                                                                {numberProduct.map(number=>(

                                                                    number.id_product === obj.id.toString() ?

                                                                        <input id="form1"
                                                                               style={{width: "60px", marginLeft: "12px"}}
                                                                               min="0"
                                                                               name="quantity"
                                                                               defaultValue= {number.number}
                                                                               type="number"
                                                                               className="form-control form-control-sm"/>
                                                                        :
                                                                       null

                                                                ))}

                                                                <button className="btn btn-link px-2"
                                                                        onClick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                                    <i className="fa fa-plus"></i>
                                                                </button>
                                                            </div>
                                                            {numberProduct.map(number=>(

                                                                number.id_product === obj.id.toString() ?

                                                                    <div className="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                                        <h6 className="mb-0">{obj.price * number.number}</h6>
                                                                    </div>
                                                                    :
                                                                    null

                                                            ))}
                                                            <div className="col-md-1 col-lg-1 col-xl-1 text-end">
                                                                <a href="#!" className="text-muted"><i
                                                                    className="fa fa-times"></i></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                ))}
                                            </div>
                                        </div>
                                        <div className="col-lg-4 bg-grey">
                                            <div className="p-5">
                                                <h3 className="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                                                <div className="my-4">

                                                    <div className="d-flex justify-content-between mb-4">
                                                        <h5 className="text-uppercase">items 3</h5>
                                                        <h5>€ 132.00</h5>
                                                    </div>

                                                    <h5 className="text-uppercase mb-3">Shipping</h5>

                                                    <div className="mb-4 pb-2">
                                                        <select className="select">
                                                            <option value="1">Standard-Delivery- €5.00</option>
                                                            <option value="2">Two</option>
                                                            <option value="3">Three</option>
                                                            <option value="4">Four</option>
                                                        </select>
                                                    </div>

                                                    <h5 className="text-uppercase mb-3">Give code</h5>

                                                    <div className="mb-5">
                                                        <div className="form-outline">
                                                            <input type="text" id="form3Examplea2"
                                                                   className="form-control form-control-lg"/>
                                                            <label className="form-label" htmlFor="form3Examplea2">Enter
                                                                your code</label>
                                                        </div>
                                                    </div>

                                                    <div className="my-4">

                                                        <div className="d-flex justify-content-between mb-5">
                                                            <h5 className="text-uppercase">Total price</h5>
                                                            <h5>€ 137.00</h5>
                                                        </div>

                                                        <button type="button" className="btn btn-dark btn-block btn-lg"
                                                                data-mdb-ripple-color="dark">Register
                                                        </button>
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
