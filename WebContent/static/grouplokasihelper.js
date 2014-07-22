$(document).ready(function(){
			
	function genTableRate(grouplokasiDList){
		var row = "<table class=\"gridTables\" id=\"listRate\" border=0 cellspacing=0 cellpadding=0>"+
					"<thead>" +
						"<tr>"+
							"<th style=\"width: 205px;\">KD Lokasi</th>"+
							"<th style=\"width: 150px;\">Nama Lokasi</th>"+
							"<th style=\"width: 150px;\">Propinsi</th>"+
							"<th style=\"width: 150px;\">Kota</th>"+
							"<th style=\"width: 150px;\">Email</th>"+
							"<th style=\"width: 20px;\"></th>"+
						"</tr>" +
					"</thead>";
		row += "<tbody>";
		if(grouplokasiDList!=null){
			var lngt=grouplokasiDList.length;
			
			if(lngt!=0){
				
				for(var i=0;i<lngt;i++){
					var rowNum=i+1;
					var lok_kd=grouplokasiDList[i].lok_kd;
					var lok_nm=grouplokasiDList[i].lok_nm;
					var propinsi=grouplokasiDList[i].propinsi;
					var kota=grouplokasiDList[i].kota;
					var email=grouplokasiDList[i].email;
					row +=  "<tr>"+
								"<td  style=\"width: 205px;\"><input type=\"hidden\" name=\"lok_kd_"+rowNum+"\" id=\"lok_kd_"+rowNum+"\" value=\""+lok_kd+"\" />"+lok_kd+"<input type=\"hidden\" name=\"idx\" id=\"idx\" value=\""+rowNum+"\" /></td>"+			 	
								"<td  style=\"width: 150px;\"><input type=\"hidden\" name=\"lok_nm_"+rowNum+"\" id=\"lok_nm_"+rowNum+"\" value=\""+lok_nm+"\"   />"+lok_nm+"</td>"+
								"<td  style=\"width: 150px;\"><input type=\"hidden\" name=\"propinsi_"+rowNum+"\" id=\"propinsi_"+rowNum+"\" value=\""+propinsi+"\"   />"+propinsi+"</td>"+
								"<td  style=\"width: 150px;\"><input type=\"hidden\" name=\"kota_"+rowNum+"\" id=\"kota_"+rowNum+"\" value=\""+kota+"\"   />"+kota+"</td>"+
								"<td  style=\"width: 150px;\"><input type=\"hidden\" name=\"email_"+rowNum+"\" id=\"email_"+rowNum+"\" value=\""+email+"\"   />"+email+"</td>"+
							 	"<td  style=\"width: 20px;\"><a href=\"#\" class=\"remove\" id=\"remove\" rel=\""+rowNum+"\"> <img src=\""+path+"/resources/images/delete.png\" alt=\"Delete\" /> </a> </td>"+
							"</tr>";
				}
				
			}else{
				row +=  "<tr>"+
							"<td  style=\"width: 205px;\">&nbsp;</td>"+			 	
							"<td  style=\"width: 150px;\"></td>"+
							"<td  style=\"width: 150px;\"></td>"+
							"<td  style=\"width: 150px;\"></td>"+
							"<td  style=\"width: 150px;\"></td>"+
						 	"<td  style=\"width: 20px;\"> </td>"+
						"</tr>";
			}
		}
		row += "</tbody>";
		row += "</table>";
		
		return row;
	}
	
	function addRow(type){
		if($('#lok_kd').val()=='') alert('KD Lokasi Belum diisi');
		else{	
			
			var arr = type.closest('form').serializeArray();
			var tbl = $('#listRate tr').length;
			$.getJSON(path+'/master/grouplokasih/json/addData?tbl='+tbl, arr, function(data) {
				$('#table_wrapper').html(
					genTableRate($(data))
				);
				setting();
				$('#lok_kd').val('');
				
			}, 'json');
		}
		return false;
	}
	
	
			
	$('#table_wrapper').on('click',"#remove", function() {
		var trigger = $(this);
		var arr = $(this).closest('form').serializeArray();
		var id = $(this).attr('rel');
		$.getJSON(path+'/master/grouplokasih/json/removeData?id='+id, arr, function(data) {
			$('#table_wrapper').html(
					genTableRate($(data))
				);
			setting();
		});
		
		return false;
	});

				
	$('#lok_kd').keypress(function(e) {
		if (e.keyCode == '13') {
	  		e.preventDefault();
	  		addRow($(this));						
	  	}
	});
	
	$('#add').click(function() {		
		addRow($(this));
	});
	

	
});