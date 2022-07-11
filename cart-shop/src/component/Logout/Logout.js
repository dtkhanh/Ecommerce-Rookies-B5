import React, {Component, useState, useEffect} from "react"


import { useNavigate } from 'react-router-dom';

const Logout = () => {

    const navigate = useNavigate();


    const signOutHandle = (e) => {
        e.preventDefault();
        localStorage.clear();
        navigate('/');
    }

    return (
        <>

            <form id="signin-form" onSubmit={signOutHandle} style={{width: "23rem"}}>
                <h3 className="fw-normal mb-3 pb-3" style={{letterSpacing: "1px"}}>Log in</h3>

                <div className="pt-1 mb-4">
                    <button className="btn btn-info btn-lg btn-block" type="submit">Logout
                    </button>
                </div>

            </form>

        </>
    )
}
export default Logout;
