const globalurl = "http://localhost:8080/api/users";



function sendRecuest(method , url , bady = null ){

    const xhr = new XMLHttpRequest()

    xhr.open(method , url)

    xhr.responseType  = 'json'

    xhr.onload = () => {
        console.log(xhr.response);
    }


    xhr.send( JSON.stringify( bady));
}


body = {
    login : 'test',
    password : '1',
    role : 'ADMIN'
}

sendRecuest('POST' , globalurl , body )
