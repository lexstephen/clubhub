    $("#loadPref").click(function () {
    	$("select[name='prefid']").val($("#activePrefId").text()).change();
    });

    $("select[name='csid']").change(function(){
        $(this).find("option:selected").each(function(){
    		for	(index = 0; index < Schemes.length; index++) {
                if($(this).attr("value")==Schemes[index].csid){
    				  document.getElementById('schemeName').value = Schemes[index].name;
    				  document.getElementById('schemeDark_colour').value = Schemes[index].dark_colour;
    				  document.getElementById('schemeMed_colour').value = Schemes[index].med_colour;
    				  document.getElementById('schemeLight_colour').value = Schemes[index].light_colour;
    				  document.getElementById('schemeText_colour').value = Schemes[index].text_colour;
      				  $('#inpt_delete').val(Schemes[index].csid);
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

    $("select[name='prefid']").val(this).change(function(){
        $(this).find("option:selected").each(function(){
        	for	(index = 1; index < Prefs.length; index++) {
    			var colour_id;
                if($(this).attr("value")==Prefs[index].prefid){
	  				var currentImage = (Prefs[index].id);
    				$('#inpt_delete').val(Prefs[index].prefid);
    				$('#inpt_preference_name').val(Prefs[index].preference_name);
      				$('#inpt_club_name_long').val(Prefs[index].club_name_long);
      				$('#inpt_club_name_short').val(Prefs[index].club_name_short);
      				$('#inptTelephone').val(Prefs[index].telephone);
      				$('#inptAddress').val(Prefs[index].address);
      				$('#inptCity').val(Prefs[index].city);
      				$('#inptPostalCode').val(Prefs[index].postalcode);
      				$('#inptCountry').val(Prefs[index].country);
      				if(Prefs[index].country == 'United States of America') {
      					$('#inptState').show();
      					$('#inptProvince').hide();
          				$('#inptState').val(Prefs[index].province);
      				} else {
      					$('#inptProvince').show();
      					$('#inptState').hide();
          				$('#inptProvince').val(Prefs[index].province);
      				}
      				$('#tax_rate').val(Prefs[index].tax_rate);
      				$('#inptSchemeID').val(Prefs[index].colour_schemeid);
					if(Prefs[index].image_logo_exists == "true") {
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

	   	    		for	(idx = 0; idx < Schemes.length; idx++) {
	   	                if(Prefs[index].colour_schemeid ==Schemes[idx].csid){
	   	    				  $('#preview_Dark_colour').css({'background-color':Schemes[idx].dark_colour});
	   	    				  $('#preview_Med_colour').css({'background-color':Schemes[idx].med_colour});
	   	    				  $('#preview_Light_colour').css({'background-color':Schemes[idx].light_colour});
	   	    				  $('#preview_Text_colour').css({'background-color':Schemes[idx].text_colour}); 
	   	    	            }
	   	                }
    	        }
    		}
        });
    }).change();

	$(".hex_code").on('input', function () {
		var self = $(this);
		var hex_colour = self.val();
		self.closest(".preview_colour").css({'background-color':hex_colour});
	});