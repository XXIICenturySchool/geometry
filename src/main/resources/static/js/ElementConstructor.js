class ElementConstructor {
    static createNumberInput(id) {
        let input = document.createElement("input");
        input.type = "number";
        input.className += "label-size";
        input.id = id;
        return input;
    }

    static createCheckbox(checboxType) {
        return new Checkbox(checboxType);
    }

    static createVariableConstraints(variable) {
        return new VariableConstraints(variable);
    }
}