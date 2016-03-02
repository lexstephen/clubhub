$('.hiddenest').hide();
$('#inptCountry').bind('change', function(event) {
	var i= $('#inptCountry').val();

	if(i=="United States of America") {
		$('#inptState').show();
		$('#inptProvince').hide(); // hide the first one
	}
	else if(i=="Canada") {
		$('#inptState').hide(); // hide the first one
		$('#inptProvince').show(); // show the other one
	}
});

$('#editPageType').bind('change', function(event) {
	var i = $('#editPageType').val();
	
	if(i=="2") {
		$('#editPageCategory').prop('disabled', true);
	}
	else {
		$('#editPageCategory').prop('disabled', false);
	}
});