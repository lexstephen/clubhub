
$(".qty").on('input', function () {
var self = $(this);
// var unitVal = self.next().val();
//var unitVal = self.parent().next().val();
var unitVal = self.closest(".row").find(".unit").val();
self.closest(".row").find(".amount").val(unitVal * self.val());
	fnAlltax();
   	fnAlltotal();
});

$(".unit").on('input', function () {
var self = $(this);
//$(this).parent().find('#cl_zipcode').val()
var qtyVal = self.closest(".row").find(".qty").val();
self.closest(".row").find(".amount").val(qtyVal * self.val());
	fnAlltax();
   	fnAlltotal();
});

function fnAlltotal(){
  var total=0;
    $(".amount").each(function(){
     total += parseFloat($(this).val()||0);
});
$(".result").val(total);
   	fnFinalTotal();
}

    

function fnFinalTotal(addThis){
  	var subtotal=$(".result").val();		
var tax=$(".taxes").val();					

var total = parseFloat(subtotal) + parseFloat(tax);

$(".finalresult").val(total);
}






$(document).ready(function(){
    $("select[name='charge01']").change(function(){
    $(this).find("option:selected").each(function(){
		for	(index = 0; index < lineItems.length; index++) {
            if($(this).attr("value")==lineItems[index].id){
				  document.getElementById('charge01cost').value = parseFloat(lineItems[index].cost);
	            }
            }
        });
    }).change();
});

$(document).ready(function(){
    $("select[name='charge02']").change(function(){
    $(this).find("option:selected").each(function(){
		for	(index = 0; index < lineItems.length; index++) {
            if($(this).attr("value")==lineItems[index].id){
				  document.getElementById('charge02cost').value = parseFloat(lineItems[index].cost);
	            }
            }
        });
    }).change();
});

$(document).ready(function(){
    $("select[name='charge03']").change(function(){
    $(this).find("option:selected").each(function(){
		for	(index = 0; index < lineItems.length; index++) {
            if($(this).attr("value")==lineItems[index].id){
				  document.getElementById('charge03cost').value = parseFloat(lineItems[index].cost);
	            }
            }
        });
    }).change();
});

$(document).ready(function(){
    $("select[name='charge04']").change(function(){
    $(this).find("option:selected").each(function(){
		for	(index = 0; index < lineItems.length; index++) {
            if($(this).attr("value")==lineItems[index].id){
				  document.getElementById('charge04cost').value = parseFloat(lineItems[index].cost);
	            }
            }
        });
    }).change();
});
$(document).ready(function(){
    $("select[name='charge05']").change(function(){
    $(this).find("option:selected").each(function(){
		for	(index = 0; index < lineItems.length; index++) {
            if($(this).attr("value")==lineItems[index].id){
				  document.getElementById('charge05cost').value = parseFloat(lineItems[index].cost);
	            }
            }
        });
    }).change();
});