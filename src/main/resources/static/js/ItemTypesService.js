class ItemTypesService {
    static getTypes() {
        return [
            {
                name: "Pythagoras",
                checkboxes: [
                    "canAnswerBeFloat",
                    "testCheckbox"
                ],
                variables:
                    [
                        "a",
                        "b",
                        "c"
                    ]
            },
            {
                name: "Angles",
                checkboxes: [],
                variables: []
            }
        ];
    }
}