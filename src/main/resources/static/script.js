showAllUsers();

function showAllUsers() {
    let tBody = document.getElementById("allUsers");
    tBody.innerHTML = "";

    fetch('http://localhost:8080/users')
        .then(response => response.json())
        .then(users => {
            console.log(users)
            users.forEach(user => {

                let row = tBody.insertRow();
                let cell0 = row.insertCell();
                cell0.innerHTML = user.id;
                let cell1 = row.insertCell();
                cell1.innerHTML = user.username;
                let cell3 = row.insertCell();
                cell3.innerHTML = user.age;
                let cell5 = row.insertCell();
                cell5.innerHTML = getRolesFromUser(user).textContent;

                let cell6 = row.insertCell();
                cell6.innerHTML =
                    '<button type="button" ' +
                    'onclick="fillUpdateModalForm(' + user.id + ')" ' +
                    'data-toggle="modal" data-target="#updateModal" ' +
                    'class="btn btn-primary btn-sm">\n' +
                    '   Update</button>\n';

                let cell7 = row.insertCell();
                cell7.innerHTML =
                    '<a role="button" class="btn btn-danger btn-sm" ' +
                    'data-toggle="modal" data-target="#deleteModal" ' +
                    'onclick="fillDeleteModalWindow(' + user.id + ')">Delete</a>';
            })
        });
}

function getRolesFromUser(user) {
    let rolesList = document.createElement('ul');

    for (let i = 0; i < user.authorities.length; i++) {
        let role = document.createElement('li');
        role.textContent = user.authorities[i].name + " ";
        rolesList.appendChild(role);
    }
    return rolesList;
}

function fillDeleteModalWindow(userId) {
    let url = "http://localhost:8080/getUser/" + userId;
    fetch(url)
        .then(response => response.json())
        .then(user => {
            console.log(user);
            document.getElementById('idToDelete').value = user.id;
            document.getElementById('ageToDelete').value = user.age;
            document.getElementById('nameToDelete').value = user.username;
            document.getElementById('passwordToDelete').value = user.password;

            let select = document.getElementById('rolesToDelete');
            for (let i = 0; i < user.roles.length; i++) {
                let element = document.createElement('option');
                element.textContent = user.roles[i].name;
                element.value = user.roles[i];
                select.appendChild(element)
            }
        })
}