const globalurl = "http://localhost:8080/api/users";
const editurl = "http://localhost:8080/api/users/edit";
const delurl = "http://localhost:8080/api/users/del";
const roleurl = "http://localhost:8080/api/users/roles";

function click_addUsers(){
    document.getElementById("content").style.display = 'none';
    document.getElementById("add_user").style.display = '';
    tableUpdate(sendRecuest('GET' , globalurl ));

}

function click_users(){
    document.getElementById("content").style.display = '';
    document.getElementById("add_user").style.display = 'none';
    tableUpdate(sendRecuest('GET' , globalurl ));
}

function click_save(){

    let body = {
        name: document.getElementById("inp_name").value,
        password: document.getElementById("inp_pass").value,
        // role: document.getElementById("inp_role").value
    }

    let data = sendRecuest("POST", globalurl, body);
    tableUpdate(sendRecuest('GET' , globalurl ));
    alert("пользователь добален");
}

function click_admin(){
    div = document.getElementById("user_content");
    div.style.display = 'none';
    div = document.getElementById("admin_content");
    div.style.display = '';
}

function click_user() {
    div = document.getElementById("user_content");
    div.style.display = '';
    div = document.getElementById("admin_content");
    div.style.display = 'none';


}


function tableUpdate(promise){

    promise.then( data => {
        table = document.getElementById("table");
        user_table = document.getElementById("user_table");

        if(table) {

            console.log(data);

            while (table.rows[0]) {
                table.deleteRow(0);
            }

            while (user_table.rows[0]) {
                user_table.deleteRow(0);
            }

            for (let i in data) {
                let tr = document.createElement("tr");
                let user_tr = document.createElement("tr");

                let tdId = document.createElement("td");
                tdId.textContent = data[i].id;

                let user_tdId = document.createElement("td");
                user_tdId.textContent = data[i].id;


                let tdName = document.createElement("td");
                tdName.textContent = data[i].name;

                let user_tdName = document.createElement("td");
                user_tdName.textContent = data[i].name;

                let tdRole = document.createElement("td");
                tdRole.textContent = data[i].role;

                let user_tdRole = document.createElement("td");
                user_tdRole.textContent = data[i].role;

                let tdEdit = document.createElement("td");
                let edit_but = document.createElement("button");
                edit_but.textContent = "edit";
                edit_but.className = "btn-primary"
                edit_but.onclick = function () {
                    edit_user(data[i].id)
                }

                tdEdit.appendChild(edit_but);


                let tdDel = document.createElement("td");
                let Del_but = document.createElement("button");
                Del_but.textContent = "delete";
                Del_but.className = "btn-primary";
                Del_but.onclick = function () {
                    data = sendRecuest("POST", delurl + "/" + data[i].id);
                    tableUpdate(data);
                }
                tdDel.appendChild(Del_but);

                tr.appendChild(tdId);
                tr.appendChild(tdName);
                tr.appendChild(tdRole)
                tr.appendChild(tdEdit)
                tr.appendChild(tdDel)

                user_tr.appendChild(user_tdId);
                user_tr.appendChild(user_tdName);
                user_tr.appendChild(user_tdRole)

                table.appendChild(tr);

                user_table.appendChild(user_tr);

            }
        }

    });

}


function edit_user(id){

    permise = sendRecuest("GET" , globalurl+"/"+id);
    permise.then(data => {
        let div = document.createElement("div");
        div.className = "editDiv";

        let wind = document.createElement("div");
        wind.className = "modal-window";

        let title = document.createElement("h1");
        title.textContent = "Edit user";

        let close_but = document.createElement("button");
        close_but.textContent = "Close";
        close_but.className = "btn-primary";
        close_but.onclick = function (){
            div.remove();
        }


        let div_name = document.createElement("div");
        let label_name = document.createElement("h5");
        label_name.textContent = "name";
        let input_name = document.createElement("input");
        input_name.value = data.name;

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
        input_role.value = data.role;
        div_role.appendChild(label_role)
        div_role.appendChild(input_role)


        let save_but = document.createElement("button");
        save_but.className = "btn-primary";
        save_but.textContent = "save";
        save_but.onclick = function (){

            body = {
                name : input_name.value ,
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
    })
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




