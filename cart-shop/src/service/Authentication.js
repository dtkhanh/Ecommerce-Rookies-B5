import {getCookie} from "./Cookie";

export  function checkAccountTrue(){
    if( localStorage.getItem("User") !== null ){
        return true;
    }else{
        console.log("login Admin fail");
        return false;
    }
}

export  function checkAccountUSER(){
    let user = JSON.parse(localStorage.getItem('User'));
    if( user.roles !== "ROLE_USER" ){
        return true;
    }else{
        console.log("no account DB");
        return false;
    }
}
