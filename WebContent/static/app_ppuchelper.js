function enableField(idx) {
	var countpp = $('#_countpp_' + idx).val();
	if ($("#_ids_" + idx).is(':checked')) {
		for (var i = 1; i <= countpp; i++) {
			accept(idx + "_" + i);
		}

	} else {
		for (var i = 1; i <= countpp; i++) {
			decline(idx + "_" + i);
		}

	}
}

function accept(idx) {
	$('#_qty_' + idx).removeAttr("disabled");
	$('#_harga_' + idx).removeAttr("disabled");
	$('#_qty_' + idx).val($('#_qty_old_' + idx).val());
	$('#_harga_' + idx).val($('#_harga_old_' + idx).val());
	$('#_qty_' + idx).focus().select();
	$('#dec_' + idx).hide();
	$('#acc_' + idx).show();
}

function decline(idx) {
	$('#_qty_' + idx).attr('disabled', true);
	$('#_harga_' + idx).attr('disabled', true);
	$('#_qty_' + idx).val('0');
	$('#_harga_' + idx).val('0');
	$('#dec_' + idx).show();
	$('#acc_' + idx).hide();
}

require([ "dojo/query", "dojo/on", "dojo/domReady!" ], function(query, on) {
	var masterSelect = null;

	query("input.masterSelectBox_id").forEach(
			function(node) {
				masterSelect = node;
				on(node, "click", function(e) {
					query("table td.selectBox input[type=checkbox]").forEach(
							function(node) {
								node.checked = masterSelect.checked;
								if (node.title != null)
									enableField(node.title);
							});
				})
			})

	masterSelect.checked = false;
	query("table td.selectBox input[type=checkbox]").forEach(function(node) {
		on(node, "click", function(e) {
			if (node.checked == false) {
				masterSelect.checked = false;
			}
		})
	});
});

$(document)
		.ready(
				function() {
					function syncPrice(idx) {
						var qty = Number(isNaN($('#_qty_' + idx).val()) ? 0
								: $('#_qty_' + idx).val());
						var harga = parseFloat(replaceComma($('#_harga_' + idx)
								.val()));
						$('#_total_' + idx).text(formatCurrency(qty * harga));
					}

					$("#table_wrapper").delegate('input:text', 'keyup',
							function(e) {
								syncPrice($(this).attr("rel"));
							});

					$("#table_wrapper").delegate('input:checkbox', 'click',
							function(e) {
								enableField($(this).attr("rel"));
							});

				});

dojo
		.ready(function() {
			autoPopulateMultiParamSelect4Field("${path}", true, "All",
					"_nb_id", "noppuc", "_np_id", "${np}", "lokasi6", "_lk_id",
					"${lk}", "groupbiaya", "_gb_id", "${gb}", "detailbiaya4",
					"_kb_id", "${kb}");
		});
