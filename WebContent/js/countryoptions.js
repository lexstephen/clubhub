$('.hiddenest').hide(); // default hides all American labels and inputs

$('#inptCountry').bind('change', function(event) {
	var i= $('#inptCountry').val();

	if(i=="United States of America") {
		// show American labels and inputs
		$('#inptState').show();
		$('#lblZipCode').show();
		$('#lblState').show();
		
		// hide Canadian labels and inputs
		$('#inptProvince').hide(); 
		$('#lblPostalCode').hide();
		$('#lblProvince').hide();
	}
	else if(i=="Canada") {
		// show Canadian labels and inputs
		$('#inptProvince').show(); 
		$('#lblPostalCode').show();
		$('#lblProvince').show();
		
		// hide American labels and inputs
		$('#inptState').hide(); 
		$('#lblZipCode').hide();
		$('#lblState').hide();
	}
});

$('#editPageType').bind('change', function(event) {
	var i = $('#editPageType').val();
	
	if(i=="2") {
		$('#editPageCategory').prop('disabled', true);
		$('#editAccess').prop('disabled', true);
		$('#editAccess>option:eq(0)').prop('selected', true);
	}
	else {
		$('#editPageCategory').prop('disabled', false);
		$('#editAccess').prop('disabled', false);
	}
});

function clearItem(row) {
	$("#inptCharge0"+row+">option:eq(0)").prop("selected", true);
	$("#inptCharge0"+row+"qty").val("0");
	$("#charge0"+row+"cost").val("0");
	$("#charge0"+row+"subtotal").val("0");
	/*var self = $(this);
	self.closest(".row").find(".qty").val("0");
	$("#inptCharge01qty").val("0");*/
}

function clearRow() {
	var self = $(this);
	self.closest('.row').find('.dd>option:eq(0)').prop('selected', true);
}