class TaskItem {

    constructor(manager, itemTypes) {
        this.manager = manager;
        this.itemTypes = itemTypes;
        this.typesMap = {};
        for (let type of this.itemTypes) {
            this.typesMap[type.type] = type;
        }

        let item = this;
        let node = document.createElement("div");
        node.className += "task-item";

        let selectBox = document.createElement("div");
        selectBox.className += "item-row";
        node.appendChild(selectBox);

        let select = document.createElement("select");
        select.className += "item-row-element";
        this.select = select;
        selectBox.appendChild(select);

        let deleteButton = document.createElement("button");
        deleteButton.textContent = "Delete question";
        deleteButton.onclick = function() {
            item.manager.removeItem(item);
        };
        selectBox.appendChild(deleteButton);

        let amountBox = document.createElement("div");
        node.appendChild(amountBox);
        let label = document.createElement("label");
        label.textContent = "Amount: ";
        amountBox.appendChild(label);
        this.amount = ElementConstructor.createNumberInput("tasks-amount");
        amountBox.appendChild(this.amount);

        let upperRow = document.createElement("div");
        node.appendChild(upperRow);

        let lowerRow = document.createElement("div");
        node.appendChild(lowerRow);

        select.onchange = function() {
            item.value = select.value;
            item.onTypeChange(upperRow, lowerRow);
        };

        for (let type of itemTypes) {
            let option = document.createElement("option");
            option.textContent = type.type;
            select.appendChild(option);
        }

        select.value = itemTypes[0].type;
        select.onchange();

        this.node = node;
    }

    getNode() {
        return this.node;
    }

     onTypeChange(upperRow, lowerRow) {
        let itemType = this.typesMap[this.value];
        upperRow.textContent = "";
        lowerRow.textContent = "";
        this.checkboxes = [];
        this.variables = [];

        for (let checkboxType of itemType.checkboxes) {
            let checkbox = ElementConstructor.createCheckbox(checkboxType);
            this.checkboxes.push(checkbox);
            upperRow.appendChild(checkbox.getNode());
        }

        for (let variable of itemType.variables) {
            let constraints = ElementConstructor.createVariableConstraints(variable);
            this.variables.push(constraints);
            lowerRow.appendChild(constraints.getNode());
        }
    }

    getInfo() {
        let info = {};
        info.type = this.select.value;
        info.amount = this.amount.value;
        info.checkboxes = [];
        info.constraints = [];

        this.checkboxes.forEach(t => info.checkboxes.push(t.toJSON()));
        this.variables.forEach(t => info.constraints.push(t.toJSON()));

        return info;
    }
}