class Checkbox {
    constructor(checkboxType) {
        let checkboxContainer = document.createElement("div");
        checkboxContainer.className += "item-row";

        let checkbox = document.createElement("input");
        checkbox.setAttribute("type", "checkbox");

        let label = document.createElement("label");
        label.textContent = checkboxType;

        checkboxContainer.appendChild(checkbox);
        checkboxContainer.appendChild(label);

        this.node = checkboxContainer;
        this.checkbox = checkbox;
        this.type = checkboxType;
    }

    getNode() {
        return this.node;
    }

    toJSON() {
        return {
            type: this.type,
            value: this.checkbox.checked
        }
    }
}