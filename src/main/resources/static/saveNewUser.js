function saveNewUser() {
    let url = "http://localhost:8080/saveNewUser";
    let data = document.forms.namedItem("newUserForm");
    let select = document.getElementById("roles");
    let selected = [];
    for (let i = 0; i < select.options.length; i++) {
        let role = select.options[i];
        if (select.options[i].selected) {
            selected.push(select.options[i].text);
        }
    }
    fetch(url, {
        method: "POST",
        body: JSON.stringify({
            name: data.elements.namedItem("newName").value,
            age: data.elements.namedItem("newAge").value,
            password: data.elements.namedItem("password").value,
            roles: selected
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => console.log(response))
        .then(switchToUsersTable)
        .then(() => setTimeout(showAllUsers, 10));
}