class ItemTypesService {
    static getTypes() {
        if (this.types) {
            return this.types;
        }
        this.types = [];

        let xhr = new XMLHttpRequest();
        xhr.open('GET', '/types', false);
        xhr.onreadystatechange = function() {
            if (xhr.readyState != 4)
                return;
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                ItemTypesService.types = JSON.parse(xhr.responseText);
            }
        };
        xhr.send();
        return this.types;
    }
}