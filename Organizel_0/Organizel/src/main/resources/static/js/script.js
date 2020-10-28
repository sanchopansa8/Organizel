'use strict';


function setValues(id, task, date) {

    if ($('#editModal').hasClass('in')) {
            console.log("function started");
        let idField = document.getElementById("idInput");
        let taskField = document.getElementById("taskInput");
        let dateField = document.getElementById("dateInput");

        idField.value = id;
        taskField.value = task;
        date.value = dateField;

    }
}