import React from "react";
import SideBar from "./siderBar/sideBar"
import {
    BrowserRouter,
    Routes,
    Route, Link, BrowserRouter as Router, Switch
} from 'react-router-dom';
import {Row, Col, Navbar} from "reactstrap";

import './admin.css'
import Product from "../product/Product";
import HomePage from "../../Page/Homepage";
import Login from "../login/Login";
import Logout from "../Logout/Logout";


export default function AdminPage() {
    const choices = [["PRODUCT", '/admin/product'], ["CATEGORY", '/admin/category'], ["ACCOUNT", '/admin/account']]

    return (
        <SideBar/>
        // <Row className="admin-background">
        //     <Router>
        //         <Col className="col-2 admin-sidebar">
        //             <SideBar/>
        //         </Col>
        //         {/*<Col className="admin-content">*/}
        //         {/*    <Routes>*/}
        //         {/*        <Route path='/admin/category' exact component={Product}/>*/}
        //         {/*        <Route path='/admin/product' exact component={Product}/>*/}
        //         {/*    </Routes>*/}
        //         {/*</Col>*/}
        //     </Router>
        // </Row>


    );
}