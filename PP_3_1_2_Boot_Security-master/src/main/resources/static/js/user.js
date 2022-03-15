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



function edit_user(id){

    let div = document.createElement("div");
    div.className = "editDiv";

    let wind = document.createElement("div");
    wind.className = "modal-window";

    let title = document.createElement("h1");
    title.textContent = "Edit user";

    let close_but = document.createElement("button");
    close_but.textContent = "Close";
    close_but.onclick = function (){
        div.remove();
    }

    let div_name = document.createElement("div");
    let label_name = document.createElement("h5");
    label_name.textContent = "name";
    let input_name = document.createElement("input");

    div_name.appendChild(label_name)
    div_name.appendChild(input_name)


    let div_pass = document.createElement("div");
    let label_pass = document.createElement("h5");
    label_pass.textContent = "password";
    let input_pass = document.createElement("input");
    input_pass.type = "password";

    div_pass.appendChild(label_pass)
    div_pass.appendChild(input_pass)


    let div_role = document.createElement("div");
    let label_role = document.createElement("h5");
    label_role.textContent = "role";
    let input_role = document.createElement("input");

    div_role.appendChild(label_role)
    div_role.appendChild(input_role)


    let save_but = document.createElement("button");
    save_but.textContent = "save";
    save_but.onclick = function (){

        body = {
            login : input_name.value ,
            password : input_pass.value,
            role : input_role.value
        }

        data = sendRecuest(
            "POST" ,
            editurl + "/" + id,
            body);

        tableUpdate(data);


    }

    wind.appendChild(title);
    wind.appendChild(div_name);
    wind.appendChild(div_pass);
    wind.appendChild(div_role);
    wind.appendChild(document.createElement("br"))
    wind.appendChild(save_but);
    wind.appendChild(document.createElement("br"))
    wind.appendChild(close_but);

    div.appendChild(wind);
    document.body.appendChild(div);
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




