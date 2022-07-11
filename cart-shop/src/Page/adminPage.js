import React, { useState, useEffect} from "react";
import Navbar from "../component/navbar/Navbar";
import Sidebar from "../component/Admin/siderBar/sideBar"
import NavbarAdmin from "../component/Admin/navBarAdmin/Navbaradmin";
import {BrowserRouter as Router, Route, Routes, Switch} from "react-router-dom";
import HomePage from "./Homepage";
import Login from "../component/login/Login";
import Logout from "../component/Logout/Logout";
import ProductAdmin from "../component/Admin/productAdmin/productAdmin"
import {Col} from "antd";


export default function adminPage() {
    return (
        <>
            <NavbarAdmin/>
            <Sidebar />

        </>
    );
}