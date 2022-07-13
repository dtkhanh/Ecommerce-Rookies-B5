import React, { useState, useEffect} from "react";
import Sidebar from "../component/Admin/siderBar/sideBar"
import NavbarAdmin from "../component/Admin/navBarAdmin/Navbaradmin";


export default function adminPage() {
    return (
        <>
            <NavbarAdmin/>
            <Sidebar />

        </>
    );
}