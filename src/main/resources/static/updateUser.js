async function updateUser() {
    let url = "http://localhost:8080/updateUser/";
    let data = document.forms.namedItem("formUpdateUser");
    let select = document.getElementById("rolesToUpdate");
    let selected = [];
    for (let i = 0; i < select.options.length; i++) {
        let role = select.options[i];
        if (select.options[i].selected) {
            selected.push(select.options[i].text);
        }
    }
    try {
        const response = await fetch(url, {
            method: 'POST', // или 'PUT'
            body: JSON.stringify({
                id: data.elements.namedItem("idToUpdate").value,
                name: data.elements.namedItem("nameToUpdate").value,
                age: data.elements.namedItem("ageToUpdate").value,
                password: data.elements.namedItem("passwordToUpdate").value,
                roles: selected
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const user = await response.json();
        console.log(user);
    } catch (error) {
        console.error(error);
    }
    setTimeout(showAllUsers, 10);
}