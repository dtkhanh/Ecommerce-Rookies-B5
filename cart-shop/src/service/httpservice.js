import axios from "axios";
import {getCookie} from "./Cookie";

const url_start ="http://localhost:8080/api"

export function get(url) {
    return axios.get(url_start + url, {
        headers: {
            "Access-Control-Allow-Origin": "*",
        },
    });
}
// export function getWithAuth(url) {
//     const token = getCookie("token");
//     return axios.get(endpoint + url, {
//         headers: {
//             Authorization: `Bearer ${token}`,
//             "Access-Control-Allow-Origin": "*",
//             "Content-Type": "application/json; charset=utf-8",
//         },
//     });
// }

export function getwithAuthtication(url){
    let user = JSON.parse(localStorage.getItem('User'));
    return axios.get(url_start + url ,{
        headers:{
            Authorization: `Bearer ${user.token}`,
            "Access-Control-Allow-Origin": "*",
            'Content-Type': 'application/json; charset=utf-8',
        },
    });
}
export function postlogin(url, body) {
        return axios.post(url_start + url, body, {
            headers: {
                "Access-Control-Allow-Origin": "*",
                'Content-Type': 'application/json; charset=utf-8',
            },
        });

}


export function post(url, body) {
    let user = JSON.parse(localStorage.getItem('User'));
    if(user != null) {
        return axios.post(url_start + url, body, {
            headers: {
                Authorization: `Bearer ${user.token}`,
                "Access-Control-Allow-Origin": "*",
                'Content-Type': 'application/json; charset=utf-8',
            },
        });
    }
}
export function put(url, body) {
    let user = JSON.parse(localStorage.getItem('User'));
    return axios.put(url_start + url, body, {
        headers: {
            Authorization: `Bearer ${user.token}`,
            "Access-Control-Allow-Origin": "*",
            'Content-Type': 'application/json; charset=utf-8',
        },
    });
}
export function deleteurl(url) {
    let user = JSON.parse(localStorage.getItem('User'));
    return axios.delete(url_start + url, {
        headers: {
            Authorization: `Bearer ${user.token}` ,
            "Access-Control-Allow-Origin": "*",
            'Content-Type': 'application/json; charset=utf-8',
        },
    });
}


