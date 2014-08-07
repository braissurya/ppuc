$(document).ready(function(){
			
	function genTableRate(ppuchList){
		
		var row = "<table class=\"gridTables\" id=\"listRate\" border=0 cellspacing=0 cellpadding=0>"+
					"<thead>" +
						"<tr>"+
							"<th >PPUC No</th>"+
							"<th >Tgl PPUC</th>"+
							"<th >KD Group</th>"+
							"<th >KD Biaya</th>"+
							"<th >Keterangan</th>"+
							"<th >QTY</th>"+
							"<th >Harga</th>"+
							"<th >Total</th>"+
							"<th class=\"utilbox\"></th>"+
						"</tr>" +
					"</thead>";
		row += "<tbody>";
		
		if(ppuchList!=null){
			var lngt=ppuchList.length;
			
			if(lngt!=0){
				
				for(var i=0;i<lngt;i++){
					var rowNum=i+1;
					var ppuc_no=ppuchList[i].ppuc_no;
					var tgl_ppuc=ppuchList[i].tgl_ppuc;
					var kd_group=ppuchList[i].kd_group;
					var nm_group=ppuchList[i].nm_group;
					var kd_biaya=ppuchList[i].kd_biaya;
					var keterangan=ppuchList[i].keterangan;
					var qty=ppuchList[i].qty;
					var harga=ppuchList[i].harga;
					var total=ppuchList[i].total;
					row +=  "<tr>"+
								"<td><input type=\"hidden\" name=\"ppuc_no_"+rowNum+"\" id=\"ppuc_no_"+rowNum+"\" value=\""+ppuc_no+"\" />"+ppuc_no+"<input type=\"hidden\" name=\"idx\" id=\"idx\" value=\""+rowNum+"\" /></td>"+			 	
								"<td><input type=\"hidden\" name=\"tgl_ppuc_"+rowNum+"\" id=\"tgl_ppuc_"+rowNum+"\" value=\""+tgl_ppuc+"\" />"+tgl_ppuc+"</td>"+
								"<td><input type=\"hidden\" name=\"kd_group_"+rowNum+"\" id=\"kd_group_"+rowNum+"\" value=\""+kd_group+"\" /><input type=\"hidden\" name=\"nm_group_"+rowNum+"\" id=\"nm_group_"+rowNum+"\" value=\""+nm_group+"\" />"+nm_group+"</td>"+
								"<td><input type=\"hidden\" name=\"kd_biaya_"+rowNum+"\" id=\"kd_biaya_"+rowNum+"\" value=\""+kd_biaya+"\" />"+kd_biaya+"</td>"+
								"<td><input type=\"hidden\" name=\"keterangan_"+rowNum+"\" id=\"keterangan_"+rowNum+"\" value=\""+keterangan+"\" />"+keterangan+"</td>"+
								"<td><input type=\"text\" name=\"qty_"+rowNum+"\" id=\"qty_"+rowNum+"\" value=\""+qty+"\" /></td>"+
								"<td><input type=\"text\" name=\"harga_"+rowNum+"\" id=\"harga_"+rowNum+"\" value=\""+harga+"\" class=\"nominal\" /></td>"+
								"<td><input type=\"text\" name=\"total_"+rowNum+"\" id=\"total_"+rowNum+"\" value=\""+total+"\" class=\"nominal\" /></td>"+
							 	"<td class=\"utilbox\"><a href=\"#\" class=\"remove\" id=\"remove\" rel=\""+rowNum+"\"> <img src=\""+path+"/resources/images/delete.png\" alt=\"Delete\" /> </a> </td>"+
							"</tr>";
				}
				
			}else{
				row +=  "<tr>"+
							"<td  >&nbsp;</td>"+			 	
							"<td  ></td>"+
							"<td  ></td>"+
							"<td  ></td>"+
							"<td  ></td>"+
							"<td  >&nbsp;</td>"+
							"<td  >&nbsp;</td>"+
							"<td  >&nbsp;</td>"+
						 	"<td  class=\"utilbox\"> </td>"+
						"</tr>";
			}
		}
		row += "</tbody>";
		row += "</table>";
		
		return row;
	}
	
	function addRow(type){
		if($('#_kd_group_id').val()=='') alert('KD Group Belum diisi');
		else if($('#_kd_biaya_id').val()=='') alert('KD Biaya Belum diisi');
		else if($('#_qty_id').val()=='') alert('QTY Belum diisi');
		else if($('#_harga_id').val()=='') alert('Harga Belum diisi');
		else{	
			
			var arr = type.closest('form').serializeArray();
			var tbl = $('#listPPUC tr').length;
			$.getJSON(path+'/trans/ppuch/json/addData?tbl='+tbl, arr, function(data) {
				
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
		$.getJSON(path+'/trans/ppuch/json/removeData?id='+id, arr, function(data) {
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