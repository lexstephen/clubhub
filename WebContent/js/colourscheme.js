
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

    $("select[name='colour_schemeid']").change(function(){
        $(this).find("option:selected").each(function(){
    		for	(index = 0; index < Schemes.length; index++) {
                if($(this).attr("value")==Schemes[index].csid){
    				  $('#preview_Dark_colour').css({'background-color':'#'+Schemes[index].dark_colour});
    				  $('#preview_Med_colour').css({'background-color':'#'+Schemes[index].med_colour});
    				  $('#preview_Light_colour').css({'background-color':'#'+Schemes[index].light_colour});
    				  $('#preview_Text_colour').css({'background-color':'#'+Schemes[index].text_colour}); 
    	            }
                }
            });
    }).change();

    $("select[name='prefid']").change(function(){
        $(this).find("option:selected").each(function(){
    		for	(index = 0; index < Schemes.length; index++) {
                if($(this).attr("value")==Schemes[index].colour_schemeid){
    				  $('#preview_Dark_colour').css({'background-color':'#'+Schemes[index].dark_colour});
    				  $('#preview_Med_colour').css({'background-color':'#'+Schemes[index].med_colour});
    				  $('#preview_Light_colour').css({'background-color':'#'+Schemes[index].light_colour});
    				  $('#preview_Text_colour').css({'background-color':'#'+Schemes[index].text_colour}); 
    	            }
                }
            });
    }).change();
    
    $("select[name='prefid']").change(function(){
        $(this).find("option:selected").each(function(){
    		for	(index = 0; index < Prefs.length; index++) {
                if($(this).attr("value")==Prefs[index].prefid){
  				  $('#inpt_preference_name').val(Prefs[index].preference_name);
				  document.getElementById('inpt_club_name_long').value = Prefs[index].club_name_long;
				  document.getElementById('inpt_club_name_short').value = Prefs[index].club_name_short;
				  document.getElementById('inptSchemeID').value = Prefs[index].colour_schemeid;
				  $('#preview_Dark_colour').css({'background-color':'#'+Schemes[Prefs[index].colour_schemeid].dark_colour});
   				  $('#preview_Med_colour').css({'background-color':'#'+Schemes[Prefs[index].colour_schemeid].med_colour});
   				  $('#preview_Light_colour').css({'background-color':'#'+Schemes[Prefs[index].colour_schemeid].light_colour});
   				  $('#preview_Text_colour').css({'background-color':'#'+Schemes[Prefs[index].colour_schemeid].text_colour}); 
		    	         
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