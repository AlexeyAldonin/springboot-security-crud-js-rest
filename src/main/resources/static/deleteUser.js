
function deleteUser() {
    let id = document.getElementById("idToDelete")
    let url = "http://localhost:8080/deleteUser/" + id.value;
    fetch(url, {method: 'DELETE'})
        .then(() => setTimeout(showAllUsers, 50))
        .then(() => clearDeleteForm());
}

function clearDeleteForm() {
    document.getElementById('idToDelete').value = "";
    document.getElementById('ageToDelete').value = ""
    document.getElementById('nameToDelete').value = "";
    document.getElementById('passwordToDelete').value = "";
    let select = document.getElementById("rolesToDelete");
    for (let i = 0; i < select.children.length; i++) {
        select.children[i].remove();
    }
}