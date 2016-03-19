
$(document).ready(function(){
    $("select[name='csid']").change(function(){
        $(this).find("option:selected").each(function(){
    		for	(index = 0; index < Schemes.length; index++) {
                if($(this).attr("value")==Schemes[index].csid){
    				  document.getElementById('schemeName').value = Schemes[index].name;
    				  document.getElementById('schemeDark_colour').value = Schemes[index].dark_colour;
    				  document.getElementById('schemeMed_colour').value = Schemes[index].med_colour;
    				  document.getElementById('schemeLight_colour').value = Schemes[index].light_colour;
    				  document.getElementById('schemeText_colour').value = Schemes[index].text_colour;
    				  $('#preview_Dark_colour').css({'background-color':'#'+Schemes[index].dark_colour});
    				  $('#preview_Med_colour').css({'background-color':'#'+Schemes[index].med_colour});
    				  $('#preview_Light_colour').css({'background-color':'#'+Schemes[index].light_colour});
    				  $('#preview_Text_colour').css({'background-color':'#'+Schemes[index].text_colour}); 
    	            }
                }
            });
        }).change();
});

$(document).ready(function(){
    $("select[name='prefid']").change(function(){
        $(this).find("option:selected").each(function(){
    		for	(index = 0; index < prefs.length; index++) {
                if($(this).attr("value")==prefs[index].prefid){
  				  document.getElementById('inpt_preference_name').value = prefs[index].preference_name;
				  document.getElementById('inpt_club_name_long').value = prefs[index].club_name_long;
				  document.getElementById('inpt_club_name_short').value = prefs[index].club_name_short;
    	            }
                }
            });
        }).change();
});


$(".hex_code").on('input', function () {
var self = $(this);
// var unitVal = self.next().val();
//var unitVal = self.parent().next().val();
var hex_colour = self.val();
console.log("I am here " + hex_colour);
self.closest(".preview_colour").css({'background-color':'#'+hex_colour});

});