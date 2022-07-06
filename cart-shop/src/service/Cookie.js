

export function getCookie(cokiname){
    let name = cokiname +"=";
    let decodedCookie = decodeURIComponent(document.cookie)
    let list = decodedCookie.split(';');
    for(let i=0 ;i<list.length;i++){
        let c= list[i];
        while (c.charAt(0)===''){
            c =  c.substring(1);
        }
        if(c.indexOf(name)===0){
            return c.substring(name.length,c.length);
        }
    }
    return ""
}