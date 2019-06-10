$.support.cors = true;
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/soba/restTx/txID/161990136",
		dataType: 'json'
    }).then(function(data) {
		alert(data.amount);
       $('.transactionId').append(data.transactionId);
       $('.amount').append(data.amount);
    });
});