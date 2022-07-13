import React from "react";
import SideBar from "./siderBar/sideBar"
import './admin.css'


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