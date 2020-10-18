
function deleteUser() {
    let id = document.getElementById("idToDelete")
    let url = "http://localhost:8080/deleteUser/" + id.value;
    fetch(url, {method: 'DELETE'})
        .then(() =>
            setTimeout(showAllUsers, 10));
}