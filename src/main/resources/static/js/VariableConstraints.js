class VariableConstraints {
    constructor(variable) {
        let limitations = document.createElement("div");
        limitations.className += "item-row";

        this.lowerBorder = ElementConstructor.createNumberInput("");
        limitations.appendChild(this.lowerBorder);

        let label = document.createElement("label");
        label.textContent = " <= " + variable + " <= ";
        limitations.appendChild(label);

        this.upperBorder = ElementConstructor.createNumberInput();
        limitations.appendChild(this.upperBorder);

        this.variable = variable;
        this.node = limitations;
    }

    getNode() {
        return this.node;
    }

    toJSON() {
        return {
            "variable": this.variable,
            "lower": parseInt(this.lowerBorder.value),
            "upper": parseInt(this.upperBorder.value)
        };
    }
}