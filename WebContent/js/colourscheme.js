
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
    				  $('#preview_Dark_colour').css({'background-color':Schemes[index].dark_colour});
    				  $('#preview_Med_colour').css({'background-color':Schemes[index].med_colour});
    				  $('#preview_Light_colour').css({'background-color':Schemes[index].light_colour});
    				  $('#preview_Text_colour').css({'background-color':Schemes[index].text_colour}); 
    	            }
                }
            });
    }).change();

    $("select[name='colour_schemeid']").change(function(){
        $(this).find("option:selected").each(function(){
    		for	(index = 0; index < Schemes.length; index++) {
                if($(this).attr("value")==Schemes[index].csid){
    				  $('#preview_Dark_colour').css({'background-color':Schemes[index].dark_colour});
    				  $('#preview_Med_colour').css({'background-color':Schemes[index].med_colour});
    				  $('#preview_Light_colour').css({'background-color':Schemes[index].light_colour});
    				  $('#preview_Text_colour').css({'background-color':Schemes[index].text_colour}); 
    	            }
                }
            });
    }).change();

    $("select[name='prefid']").change(function(){
        $(this).find("option:selected").each(function(){
        	for	(index = 0; index < Prefs.length; index++) {
    			var colour_id;
                if($(this).attr("value")==Prefs[index].prefid){
    				  $('#inpt_preference_name').val(Prefs[index].preference_name);
      				  $('#inpt_club_name_long').val(Prefs[index].club_name_long);
      				  $('#inpt_club_name_short').val(Prefs[index].club_name_short);
      				  $('#inptCountry').val(Prefs[index].country);
      				  $('#tax_rate').val(Prefs[index].tax_rate);
      				  $('#inptSchemeID').val(Prefs[index].colour_schemeid);

  					console.log("I am looking at " + Prefs[index] + " which is ");
    				if(Prefs[index].image_logo_exists == "true") {
    					console.log("I think the image logo exists");
        				var image_logo = document.createElement("IMG");
        				image_logo.alt = "Preview of image"
        				image_logo.setAttribute('class', 'photo');
        				image_logo.src="/clubhub/ImageDao?t=image_logo&p=" + Prefs[index].prefid;
        				$('#display_image_logo').html(image_logo);
  				}


  				if(Prefs[index].image_small_logo_exists == "true") {
      				var image_small_logo = document.createElement("IMG");
      				image_small_logo.alt = "Preview of image"
      				image_small_logo.setAttribute('class', 'photo');
      				image_small_logo.src="/clubhub/ImageDao?t=image_small_logo&p=" + Prefs[index].prefid;
      				$('#display_image_small_logo').html(image_small_logo);
				}
    					
  				  


      				console.log("Preference ID is " + Prefs[index].prefid);
      				var currentImage = (Prefs[index].id);
      				console.log("Current image is " + currentImage + " and featured images is " + Prefs[currentImage].featured_images);
      				
    				if(Prefs[currentImage].featured_images > 0) {
    					for (var i = 1; i <= Prefs[currentImage].featured_images; i++) {
	    					var featured_image_01 = document.createElement("IMG");
	    					featured_image_01.alt = "Preview of image"
	    					featured_image_01.setAttribute('class', 'photo');
	    					featured_image_01.src="/clubhub/ImageDao?t=featured_image_"+i+"&p=" + Prefs[index].prefid;
	    					$('#display_featured_image_' + i).html(featured_image_01);
    					}
   				  	} else {
    					for (var i = 1; i <= 10; i++) {
	    					var featured_image_01 = document.createElement("IMG");
	    					featured_image_01.alt = "Preview of image"
	    					featured_image_01.setAttribute('class', 'photo');
	    					featured_image_01.src="/clubhub/ImageDao?t=featured_image_0"+i+"&p=" + Prefs[index].prefid;
	    					$('#display_featured_image_0' + i).html("");
    					}
   				  		
   				  	}
    				  
//      				$('#display_image_logo').prepend('<img src=>');
				  colour_id = (Prefs[index].colour_schemeid - 1);
				  $('#preview_Dark_colour').css({'background-color':Schemes[(Prefs[index].colour_schemeid-1)].dark_colour});
   				  $('#preview_Med_colour').css({'background-color':Schemes[colour_id].med_colour});
   				  $('#preview_Light_colour').css({'background-color':Schemes[colour_id].light_colour});
   				  $('#preview_Text_colour').css({'background-color':Schemes[colour_id].text_colour}); 
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
self.closest(".preview_colour").css({'background-color':hex_colour});

});