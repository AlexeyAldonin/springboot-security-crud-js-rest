function fillUpdateModalForm(userId) {
    let url = "http://localhost:8080/getUser/" + userId;
    fetch(url)
        .then(response => response.json())
        .then(user => {
            console.log(user);
            document.getElementById('idToUpdate').value = user.id;
            document.getElementById('ageToUpdate').value = user.age;
            document.getElementById('nameToUpdate').value = user.username;
            document.getElementById('passwordToUpdate').value = user.password;
            let select = document.getElementById('rolesToUpdate');

            getAllRoles().then(roles => {
                for (let i = 0; i < roles.length; i++) {
                    let element = document.createElement('option');
                    element.textContent = roles[i].name;
                    element.value = roles[i].value;
                    select.appendChild(element)
                }
            });
        })
}