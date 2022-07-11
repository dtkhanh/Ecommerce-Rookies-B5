import React, {useEffect, useState} from "react";
import Navbar  from "./component/navbar/Navbar";
import Carousel from "./component/carousel/carousel";
import Product from "./component/product/Product";
import Category from "./component/category/Category";
import AddProduct from "./component/Admin/productAdmin/addProduct/addProduct"
import ProductAdmin from "../src/component/Admin/productAdmin/productAdmin"
import {
    BrowserRouter,
    Routes,
    Route
} from 'react-router-dom';
import Login from "./component/login/Login";
import './App.css'

import {checkAccountTrue} from "./service/Authentication";
import Logout from "./component/Logout/Logout";
import HomePage from "./Page/Homepage";
import AdminPage from "./Page/adminPage";
import EditProduct from "./component/Admin/productAdmin/editProduct/editProduct";
import CategoryAdmin from "./component/Admin/Category/categoryAdmin";
import AddCategory from "./component/Admin/Category/addCategory/addCategory";
import EditCategory from "./component/Admin/Category/editProduct/editCategory"
import Help from "./component/about/help"
import ProductDetail from "./component/product/productDetails/productDetails";
import ProductByCategory from "./component/category/productCategory/productCategory";
import Register from "./component/register/registerAccount";
import ProfileUser from "./component/profileUser/profileUser";
import EditProfile from "./component/profileUser/editProfile/editProfileUser";
import AccountAdmin from "./component/Admin/Account/accountAdmin";
import CartProduct from "./component/cartProduct/cartProduct";

const App = () =>{

    const [loginStatus, setLoginStatus] = useState(localStorage.getItem("roles"));

    useEffect(() => {
        console.log(`STATUS CHANGED: ${loginStatus}`);
    }, [loginStatus]);

    const handleLogIn = (e) => {
        console.log("hello logged in");
        setLoginStatus(true);
    }
    const handleLogOut = (e) => {
        console.log("Logging out at App.js");
        setLoginStatus(false);
    }

    return(
        <>
            <BrowserRouter>
                {/*<Navbar/>*/}
                <Routes>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/login" element={<Login />} />
                    <Route path='/admin' element={<AdminPage/>}/>
                    <Route path='/logout' element={<Logout/>}/>
                    <Route path='/register' element={<Register/>}/>
                    <Route path='/cart' element={<CartProduct/>}/>
                    <Route path='/profile' element ={<ProfileUser/>}/>
                    <Route path='/profile/edit' element={<EditProfile/>}/>
                    <Route path="/category/:id" element ={<ProductByCategory/>} />
                    <Route path='/admin/product' element = {<ProductAdmin/>}/>
                    <Route path='/admin/product/add' element ={<AddProduct/>}/>
                    <Route path='/admin/product/:id' element ={<EditProduct/>}/>
                    <Route path='/admin/category' element ={<CategoryAdmin/>}/>
                    <Route path='/admin/category/add' element ={<AddCategory/>}/>
                    <Route path='/admin/category/:id' element ={<EditCategory/>}/>
                    <Route path ='/product/:id' element={<ProductDetail/>}/>
                    <Route path='/admin/account' element = {<AccountAdmin/>}/>
                </Routes>
                {/*<Help/>*/}
            </BrowserRouter>
        </>
    )
}

export default App