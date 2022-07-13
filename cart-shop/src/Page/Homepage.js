import React, { useState, useEffect} from "react";
import Navbar from "../component/navbar/Navbar";
import Carousel from"../component/carousel/carousel"
import Category from "../component/category/Category";
import Product from "../component/product/Product";
import Help from "../component/about/help";


export default function HomePage() {
    return (
           <>
               <Navbar/>
               <Carousel/>
               <Category/>
               <Product/>
               <Help/>
           </>
    );
}