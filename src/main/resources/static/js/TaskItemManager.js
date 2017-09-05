class TaskItemManager {

    constructor(itemTypes) {
        this.itemTypes = itemTypes;
        this.items = [];
    }

    addItem() {
        let mainArea = document.getElementById("main-area");
        let item = new TaskItem(this, this.itemTypes);
        this.items.push(item);
        mainArea.appendChild(item.getNode());
    }

    removeItem(item) {
        this.items = this.items.filter(t => t !== item);
        item.getNode().remove();
    }

    sendExam() {
        let examInfo = [];
        for (let task of this.items) {
            examInfo.push(task.getInfo());
        }

        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/newexam', true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify(examInfo));

        xhr.onreadystatechange = function() {
            if (xhr.readyState != 4)
                return;
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                alert(xhr.responseText);
            }
        }
    }
}