$(document).ready(function(){
	
	function addRowJson(type,pathAdd){
		var arr = type.closest('form').serializeArray();
		var tbl = $('#listTable tr').length;
		var result=null;
		$.getJSON(path+pathAdd+'?tbl='+tbl, arr, function(data) {
			genTableRate($(data));
		}, 'json');
		return result;
	}
	
	function deleteRow(del,pathDel){
		var arr = del.closest('form').serializeArray();
		var id = del.attr('rel');
		$.getJSON(path+pathDel+'?id='+id, arr, function(data) {
			genTableRate($(data));
		});
		return false;
	}
			
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
							"<th class=\"utilbox\"></th>"+
						"</tr>" +
					"</thead>";
		row += "<tbody>";
		
		if(ppuchList!=null){
			var lngt=ppuchList.length;
			
			if(lngt!=0){
				
				for(var i=0;i<lngt;i++){
					var rowNum=i+1;
					var no_ppuc=ppuchList[i].no_ppuc;
					var tgl_ppuc=ppuchList[i].tgl_ppuc;
					var kd_group=ppuchList[i].kd_group;
					var nm_group=ppuchList[i].nm_group;
					var kd_biaya=ppuchList[i].kd_biaya;
					var nm_biaya=ppuchList[i].nm_biaya;
					var keterangan=ppuchList[i].keterangan;
					var qty=ppuchList[i].qty;
					var harga=ppuchList[i].harga;
					var total=ppuchList[i].total;
					row +=  "<tr>"+
								"<td><input type=\"hidden\" name=\"no_ppuc_"+rowNum+"\" id=\"no_ppuc_"+rowNum+"\" value=\""+no_ppuc+"\" />"+no_ppuc+"<input type=\"hidden\" name=\"idx\" id=\"idx\" value=\""+rowNum+"\" /></td>"+			 	
								"<td><input type=\"hidden\" name=\"tgl_ppuc_"+rowNum+"\" id=\"tgl_ppuc_"+rowNum+"\" value=\""+tgl_ppuc+"\" />"+tgl_ppuc+"</td>"+
								"<td><input type=\"hidden\" name=\"kd_group_"+rowNum+"\" id=\"kd_group_"+rowNum+"\" value=\""+kd_group+"\" /><input type=\"hidden\" name=\"nm_group_"+rowNum+"\" id=\"nm_group_"+rowNum+"\" value=\""+nm_group+"\" />"+nm_group+"</td>"+
								"<td><input type=\"hidden\" name=\"kd_biaya_"+rowNum+"\" id=\"kd_biaya_"+rowNum+"\" value=\""+kd_biaya+"\" />"+nm_biaya+"</td>"+
								"<td><input type=\"hidden\" name=\"keterangan_"+rowNum+"\" id=\"keterangan_"+rowNum+"\" value=\""+keterangan+"\" />"+keterangan+"</td>"+
								"<td><input type=\"hidden\" name=\"qty_"+rowNum+"\" id=\"qty_"+rowNum+"\" value=\""+qty+"\" />"+qty+"</td>"+
								"<td><input type=\"hidden\" name=\"harga_"+rowNum+"\" id=\"harga_"+rowNum+"\" value=\""+harga+"\" />"+harga+"</td>"+
								"<td><input type=\"hidden\" name=\"total_"+rowNum+"\" id=\"total_"+rowNum+"\" value=\""+total+"\"  />"+total+"</td>"+
								"<td class=\"utilbox\"><a href=\"#\" class=\"edit\" id=\"edit\" rel=\""+rowNum+"\"> <img src=\""+path+"/resources/images/update.png\" alt=\"Edit\" /> </a> </td>"+
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
						 	"<td  class=\"utilbox\"> </td>"+
						"</tr>";
			}
		}
		row += "</tbody>";
		row += "</table>";
		
		$('#table_wrapper').html(
			row
		);
		
		setting();
		
	}
	
	$('#table_wrapper').on('click',"#remove", function() {
		deleteRow($(this),'/trans/ppuch/json/removeData');
	});
	
	$('#table_wrapper').on('click',"#edit", function() {
		var id=$(this).attr('rel');
		setSelectValue('_kd_group_id', $('#kd_group_'+id).val());
		setSelectValue('_kd_biaya_id', $('#kd_biaya_'+id).val());
		$('#_qty_id').val($('#qty_'+id).val());
		$('#_harga_id').val($('#harga_'+id).val());
		$('#_keterangan_id').val($('#keterangan_'+id).val());
		$('#_no_ppuc_id').val($('#no_ppuc_'+id).val());
		deleteRow($(this),'/trans/ppuch/json/removeData');
	});
	
	function addRow(dis){
		if($('#_kd_group_id').val()=='-- choose KD Group --') alert('KD Group Belum diisi');
		else if($('#_kd_biaya_id').val()=='-- choose KD Biaya --') alert('KD Biaya Belum diisi');
		else if($('#_qty_id').val()=='') alert('QTY Belum diisi');
		else if($('#_qty_id').val()<=0) alert('QTY minimal 1');
		else if($('#_harga_id').val()=='') alert('Harga Belum diisi');
		else if($('#_tgl_ppuc_id').val()=='') alert('Tgl PPUC Belum diisi');
		else{	
			addRowJson(dis,'/trans/ppuch/json/addData');
			$('#_kd_group_id').val('-- choose KD Group --');
			$('#_kd_biaya_id').val('-- choose KD Biaya --');
			$('#_qty_id').val('');
			$('#_harga_id').val('');
			$('#_keterangan_id').val('');
			$('#_no_ppuc_id').val('');
		}
		return false;
	}
				
	$('#_kd_group_id,#_kd_biaya_id,#_qty_id,#_harga_id,#_keterangan_id').keypress(function(e) {
		if (e.keyCode == '13') {
	  		e.preventDefault();
	  		addRow($(this));						
	  	}
	});
	
	$('#add').click(function() {	
		addRow($(this));
	});
	

	
});