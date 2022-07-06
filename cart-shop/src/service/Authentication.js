import {getCookie} from "./Cookie";

export  function checkAccountTrue(){
    if( localStorage.getItem("User") !== null ){
        return true;
    }else{
        console.log("login Admin fail");
        return false;
    }
}
