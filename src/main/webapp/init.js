function initText(control,value) {
// Initialize a text field
	control.value = value;
}

function initTextArea(control,value) {
// Initialize a text area field
	control.value = value;
}

function initCheckBox(control,value) {
// Initialize a check box
	control.checked = (value != ""); 
}

function initRadio(control,value) {
// Initialize a radio button
	for (var i = 0; i < control.length; i++) { 
		if (control[i].value == value) {
			control[i].checked = true;
			break;
		}
	}
}

function initSelect(control,value) {
// Initialize a selection list (single valued)
	if (value == "") return;
	for (var i = 0; i < control.length; i++) {
		if (control.options[i].value == value) {
			control.options[i].selected = true;
			break;
		}
	}
}

function initSelectMulti(control,value) {
// Initialize a selection list (multi valued)
	if (value == "") return;
	var a = value.split(","); // make array
	for (var i = 0; i < a.length; i++) {
		for (var j = 0; j < control.length; j++) {
			if (control.options[j].value == a[i]) {
				control.options[j].selected = true;
				break;
			}
		}
	}
}

function initCheckBoxMulti(control,value) {
// Initialize a list of check boxes
	if (value == "") return;
	var a = value.split(","); // make array
	for (var i = 0; i < a.length; i++) {
		if (typeof(control.length) == "undefined") {
			if (control.value == a[i]) {
				control.checked = true;
				return;
			}
		}
		for (var j = 0; j < control.length; j++) {
			if (control[j].value == a[i]) {
				control[j].checked = true;
				break;
			}
		}
	}
}

