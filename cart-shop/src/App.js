import React, {useEffect, useState} from "react";
import Navbar  from "./component/navbar/Navbar";
import Product from "./component/product/Product";

import {
    BrowserRouter,
    Routes,
    Route
} from 'react-router-dom';
import Login from "./component/login/Login";
import './App.css'

import Logout from "./component/Logout/Logout";
import HomePage from "./Page/Homepage";


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
                <Navbar/>
                <Routes>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/login" element={<Login />} />
                    <Route path='/admin' element={<Product/>}/>
                    <Route path='/logout' element={<Logout/>}/>
                </Routes>
            </BrowserRouter>
        </>
    )
}

export default App