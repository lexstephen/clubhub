
$(document).ready(function(){
    $("select[name='id']").change(function(){
    $(this).find("option:selected").each(function(){
		for	(index = 0; index < Schemes.length; index++) {
            if($(this).attr("value")==Schemes[index].id){
				  document.getElementById('schemeName').value = Schemes[index].name;
				  document.getElementById('schemeDark_colour').value = Schemes[index].dark_colour;
				  document.getElementById('schemeMed_colour').value = Schemes[index].med_colour;
				  document.getElementById('schemeLight_colour').value = Schemes[index].light_colour;
				  document.getElementById('schemeText_colour').value = Schemes[index].text_colour;
	            }
            }
        });
    }).change();
});