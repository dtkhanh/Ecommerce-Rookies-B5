import React from "react";
import "./sideBar.css"

import { useHistory, Link } from "react-router-dom";

const  SideBar = () => {


    return (
        <div className="col-lg-2 side-navbar active-nav d-flex justify-content-between flex-wrap flex-column" style={{paddingTop: "20px"}} id="sidebar">
            <ul className="nav flex-column text-white w-100">
                <a href="#" className="nav-link h3 text-white my-2">
                    ADMIN
                </a>
                <Link to="/admin/product" className="nav-link ">
                    <span className="mx-2">Product</span>
                </Link>
                <Link to="/admin/category" className="nav-link ">
                    <span className="mx-2">Category</span>
                </Link>
                <Link to = "/admin/account"  className="nav-link">
                    <span className="mx-2">Account</span>
                </Link>
            </ul>
        </div>
    );
}

export default SideBar;


// const Sidebar = () => {
//     return (
//         <>
//             {/*<header>*/}
//             {/*    <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white">*/}
//             {/*        <div class="position-sticky">*/}
//             {/*            <div class="list-group list-group-flush mx-3 mt-4">*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple" aria-current="true">*/}
//             {/*                    <i class="fas fa-tachometer-alt fa-fw me-3"></i><span>Main dashboard</span>*/}
//             {/*                </a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple active">*/}
//             {/*                    <i class="fas fa-chart-area fa-fw me-3"></i><span>Webiste traffic</span>*/}
//             {/*                </a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i*/}
//             {/*                    class="fas fa-lock fa-fw me-3"></i><span>Password</span></a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i*/}
//             {/*                    class="fas fa-chart-line fa-fw me-3"></i><span>Analytics</span></a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple">*/}
//             {/*                    <i class="fas fa-chart-pie fa-fw me-3"></i><span>SEO</span>*/}
//             {/*                </a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i*/}
//             {/*                    class="fas fa-chart-bar fa-fw me-3"></i><span>Orders</span></a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i*/}
//             {/*                    class="fas fa-globe fa-fw me-3"></i><span>International</span></a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i*/}
//             {/*                    class="fas fa-building fa-fw me-3"></i><span>Partners</span></a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i*/}
//             {/*                    class="fas fa-calendar fa-fw me-3"></i><span>Calendar</span></a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i*/}
//             {/*                    class="fas fa-users fa-fw me-3"></i><span>Users</span></a>*/}
//             {/*                <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i*/}
//             {/*                    class="fas fa-money-bill fa-fw me-3"></i><span>Sales</span></a>*/}
//             {/*            </div>*/}
//             {/*        </div>*/}
//             {/*    </nav>*/}
//
//             {/*    <nav id="main-navbar" class="navbar navbar-expand-lg navbar-light bg-white fixed-top">*/}
//             {/*        <div class="container-fluid" style={{paddingTop: "0px" , paddingBottom: "20px"}}>*/}
//             {/*            <button class="navbar-toggler" type="button" data-mdb-toggle="collapse" data-mdb-target="#sidebarMenu"*/}
//             {/*                    aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">*/}
//             {/*                <i class="fas fa-bars"></i>*/}
//             {/*            </button>*/}
//
//             {/*            <a class="navbar-brand" href="#">*/}
//             {/*                <img src="https://mdbcdn.b-cdn.net/img/logo/mdb-transaprent-noshadows.webp" height="25" alt="MDB Logo"*/}
//             {/*                     loading="lazy" />*/}
//             {/*            </a>*/}
//             {/*            <form class="d-none d-md-flex input-group w-auto my-auto">*/}
//             {/*                <input autocomplete="off" type="search" class="form-control rounded"*/}
//             {/*                       placeholder='Search (ctrl + "/" to focus)' style={{minWidth: "225px"}} />*/}
//             {/*                <span class="input-group-text border-0"><i class="fas fa-search"></i></span>*/}
//             {/*            </form>*/}
//
//             {/*            <ul class="navbar-nav ms-auto d-flex flex-row">*/}
//             {/*                <li class="nav-item dropdown">*/}
//             {/*                    <a class="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow" href="#" id="navbarDropdownMenuLink"*/}
//             {/*                       role="button" data-mdb-toggle="dropdown" aria-expanded="false">*/}
//             {/*                        <i class="fas fa-bell"></i>*/}
//             {/*                        <span class="badge rounded-pill badge-notification bg-danger">1</span>*/}
//             {/*                    </a>*/}
//             {/*                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">*/}
//             {/*                        <li>*/}
//             {/*                            <a class="dropdown-item" href="#">Some news</a>*/}
//             {/*                        </li>*/}
//             {/*                        <li>*/}
//             {/*                            <a class="dropdown-item" href="#">Another news</a>*/}
//             {/*                        </li>*/}
//             {/*                        <li>*/}
//             {/*                            <a class="dropdown-item" href="#">Something else here</a>*/}
//             {/*                        </li>*/}
//             {/*                    </ul>*/}
//             {/*                </li>*/}
//
//             {/*                <li class="nav-item">*/}
//             {/*                    <a class="nav-link me-3 me-lg-0" href="#">*/}
//             {/*                        <i class="fas fa-fill-drip"></i>*/}
//             {/*                    </a>*/}
//             {/*                </li>*/}
//             {/*                <li class="nav-item me-3 me-lg-0">*/}
//             {/*                    <a class="nav-link" href="#">*/}
//             {/*                        <i class="fab fa-github"></i>*/}
//             {/*                    </a>*/}
//             {/*                </li>*/}
//
//             {/*                <li class="nav-item dropdown">*/}
//             {/*                    <a class="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow" href="#" id="navbarDropdown"*/}
//             {/*                       role="button" data-mdb-toggle="dropdown" aria-expanded="false">*/}
//             {/*                        <i class="united kingdom flag m-0"></i>*/}
//             {/*                    </a>*/}
//             {/*                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">*/}
//             {/*                        <li>*/}
//             {/*                            <a class="dropdown-item" href="#"><i class="united kingdom flag"></i>English*/}
//             {/*                                <i class="fa fa-check text-success ms-2"></i></a>*/}
//             {/*                        </li>*/}
//             {/*                        <li>*/}
//             {/*                            <hr class="dropdown-divider" />*/}
//             {/*                            <a*/}
//             {/*                                class="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow"*/}
//             {/*                                href="#"*/}
//             {/*                                id="navbarDropdown"*/}
//             {/*                                role="button"*/}
//             {/*                                data-mdb-toggle="dropdown"*/}
//             {/*                                aria-expanded="false"*/}
//             {/*                            >*/}
//             {/*                                <i class="flag-united-kingdom flag m-0"></i>*/}
//             {/*                            </a>*/}
//             {/*                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"*/}
//             {/*                                    ><i class="flag-united-kingdom flag"></i>English*/}
//             {/*                                        <i class="fa fa-check text-success ms-2"></i*/}
//             {/*                                        ></a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"><i class="flag-poland flag"></i>Polski</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"><i class="flag-china flag"></i>中文</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"><i class="flag-japan flag"></i>日本語</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"><i class="flag-germany flag"></i>Deutsch</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"><i class="flag-france flag"></i>Français</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"><i class="flag-spain flag"></i>Español</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"><i class="flag-russia flag"></i>Русский</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#"><i class="flag-portugal flag"></i>Português</a>*/}
//             {/*                                </li>*/}
//             {/*                            </ul>*/}
//             {/*                        </li>*/}
//
//             {/*                        <li class="nav-item dropdown">*/}
//             {/*                            <a class="nav-link dropdown-toggle hidden-arrow d-flex align-items-center" href="#"*/}
//             {/*                               id="navbarDropdownMenuLink" role="button" data-mdb-toggle="dropdown" aria-expanded="false">*/}
//             {/*                                <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (31).webp" class="rounded-circle"*/}
//             {/*                                     height="22" alt="Avatar" loading="lazy" />*/}
//             {/*                            </a>*/}
//             {/*                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#">My profile</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#">Settings</a>*/}
//             {/*                                </li>*/}
//             {/*                                <li>*/}
//             {/*                                    <a class="dropdown-item" href="#">Logout</a>*/}
//             {/*                                </li>*/}
//             {/*                            </ul>*/}
//             {/*                        </li>*/}
//             {/*                    </ul>*/}
//             {/*                </li></ul>*/}
//             {/*        </div>*/}
//             {/*    </nav>*/}
//             {/*</header>*/}
//
//             {/*<main style={{marginTop: "58px"}}>*/}
//             {/*    <div class="container pt-4"></div>*/}
//             {/*</main>*/}
//             <div className="side-navbar active-nav d-flex justify-content-between flex-wrap flex-column" id="sidebar">
//                 <ul className="nav flex-column text-white w-100">
//                     <a href="#" className="nav-link h3 text-white my-2">
//                         Side Nav
//                     </a>
//                     <li href="#" className="nav-link">
//                         <span className="mx-2">Home</span>
//                     </li>
//                     <li href="#" className="nav-link">
//                         <span className="mx-2">About</span>
//                     </li>
//                     <li href="#" className="nav-link">
//                         <span className="mx-2">Contact</span>
//                     </li>
//                 </ul>
//             </div>
//             <div className="p-1 my-container active-cont">
//                 Main Content Here
//                 ...
//                 Replace the menu toggle icon as per your needs
//                 <a className="btn border-0" id="menu-btn">
//                     <i className="bx bx-menu"></i>
//                 </a>
//             </div>
//
//         </>
//     );
// };
//
// export default Sidebar;