const globalurl = "http://localhost:8080/api/users";





function tableUpdate(promise){

    promise.then( data => {
        user_table = document.getElementById("user_table");
        if(user_table) {
            console.log(data);

            while (user_table.rows[0]) {
                user_table.deleteRow(0);
            }

            for (let i in data) {

                let user_tr = document.createElement("tr");

                let user_tdId = document.createElement("td");
                user_tdId.textContent = data[i].id;


                let user_tdName = document.createElement("td");
                user_tdName.textContent = data[i].name;

                let user_tdRole = document.createElement("td");
                user_tdRole.textContent = data[i].role;


                user_tr.appendChild(user_tdId);
                user_tr.appendChild(user_tdName);
                user_tr.appendChild(user_tdRole)


                user_table.appendChild(user_tr);

            }
        }

    });

}





async function sendRecuest(method, url, body = null) {
    if (body != null) {
        body = JSON.stringify(body);
    }

    let quary = await fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: body
    });


    return  await quary.json();

}

tableUpdate(sendRecuest('GET' , globalurl ));




