import React from "react";
import Navbar  from "./component/navbar/Navbar";
import Carousel from "./component/carousel/carousel";
import Product from "./component/product/Product";
import Category from "./component/category/Category";
import AddProduct from "./component/addProduct/addProduct"

import { BrowserRouter as Router, Switch, Route } from "react-router-dom"
import './App.css'


const App = () =>{
    return(
        <>
            <Router>
                <Navbar/>
                <AddProduct/>
                {/*<Carousel/>*/}
                {/*<Category/>*/}
                {/*<Product/>*/}
            </Router>
        </>
    )
}

export default App