function getAllRoles() {
    let url = "http://localhost:8080/roles";
    return fetch(url)
        .then(response => {
            return response.json();
        });
}