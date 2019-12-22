export default url => {
    return fetch(url).then(response => response.json());
}