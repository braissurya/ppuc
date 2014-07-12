
function formatCurrency(num) {
	num = num.toString().replace(/\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ','
				+ num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + num + '.' + cents);
}

function dialogAlert(txtTitle, txtContent)
{
    var thisdialog = new dijit.Dialog({ title: txtTitle, content: txtContent, style: "width: 600px;height:400px; " });
    dojo.body().appendChild(thisdialog.domNode);
    thisdialog.startup();
	thisdialog.show();

}

/* Popup Window, persis ditengah layar */
function popWin(href, height, width, scrollbar, stat) {
	var vWin;
	if (scrollbar != 'no')
		scrollbar = 'yes';
	if (stat != 'yes')
		stat = 'no';

	vWin = window.open(href, '', 'height=' + height + ',width=' + width
			+ ',toolbar=no,directories=no,menubar=no,scrollbars=' + scrollbar
			+ ',resizable=yes,modal=yes,status=yes' +
			// ',toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,modal=yes,status=yes'+
			',left=' + ((screen.availWidth - width) / 2) + ',top='
			+ ((screen.availHeight - height) / 2));
	vWin.opener = self;
}

function reset(idSelect) {
	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/Select",
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, Select, on, request,
					JSON, arrayUtil) {
				// reset
				dijit.byId(idSelect).store = new dojo.store.Memory({
					data : [ {
						name : '',
						id : ''
					} ]
				});
				dijit.byId(idSelect).set('value', '');
			});
}

function setSelect(jsonData, idSelect, selectedId, optionALLText) {
	require([ "dojo/store/Memory", "dijit/form/Select", "dojo/domReady!" ],
			function(Memory, Select) {
				dijit.byId(idSelect).store = new dojo.store.Memory({
					data : jsonData
				});
				
				dijit.byId(idSelect).set('value', " ");
				var cekselectedId=selectedId.replace(/\./g,'');
				cekselectedId=cekselectedId.replace(/\ /g,'');
				
				if (cekselectedId != ""){
					
					dijit.byId(idSelect).set('value', selectedId);
				}
				
			});
}

function autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
		selectedId, optionALLText) {

	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/Select",
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, Select, on, request,
					JSON, arrayUtil) {
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value"), {
							handleAs : "json" // < Parse data from JSON to a
												// JavaScript object
						}).then(function(data) {

					var datas = [];
					if (optionALL == true)
						datas.push({
							"name" : optionALLText,
							"id" : " "
						});

					arrayUtil.forEach(data, function(item, i) {
						datas.push({
							"name" : item.value,
							"id" : item.key
						});
					});

					setSelect(datas, childSelectId, selectedId, optionALLText);

				}, function(error) {
					console.log("An error occurred: " + error);
				});

			}); // end of on
}

function autoPopulateSelect(path, ajaxType, parentSelectId,
		optionALL, optionALLText, selectedId, childSelectId) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {

			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				
				reset(childSelectId);
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
			}) // end of event handler
			reset(childSelectId);
			autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
					selectedId, optionALLText);
		});
	}); // end of on

	/**/
}

function autoPopulateSelect2(path, ajaxType, parentSelectId, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
			var s = dijit.byId(parentSelectId);

			on(s, "change", function(e) {
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null)reset(childSelectId2);
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
			}) // end of event handler
			
			reset(childSelectId);
			reset(childSelectId2);
			autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
					selectedId, optionALLText);
		});
		/*
		*/
	}); // end of on

}

function autoPopulateSelect3(path, ajaxType, parentSelectId, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2,childSelectId3) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
			var s = dijit.byId(parentSelectId);

			on(s, "change", function(e) {
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null)reset(childSelectId2);
				if(childSelectId3!=null)reset(childSelectId3);
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
			}) // end of event handler
			
			reset(childSelectId);
			reset(childSelectId2);
			reset(childSelectId3);
			autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
					selectedId, optionALLText);
		});
		/*
		*/
	}); // end of on

}

function autoCompleteSelect(path, ajaxType, inputId, selectId, minChar) {
	// ajax autopopulate select box
	$(inputId).keyup(
			function() {
				$(selectId).children().remove(); // remove all isinya
				var panjang = $(inputId).val().length;

				if (panjang >= minChar) {
					// populate ulang
					$.getJSON(path + '/json.htm', {
						t : ajaxType,
						p : $(inputId).val()
					}, function(data) {
						var html = '';
						var len = data.length;
						for (var i = 0; i < len; i++) {
							html += '<option value="' + data[i].key + '">'
									+ data[i].value + '</option>';
						}
						$(selectId).append(html);
					});
				}
			});
}

function setting() {
	$(".nominal").formatCurrency({
		symbol : '',
		colorize : true,
		negativeFormat : '-%s%n',
		roundToDecimalPlace : 0
	});

	$(".rate").formatCurrency({
		symbol : '',
		colorize : true,
		negativeFormat : '-%s%n',
		roundToDecimalPlace : 3
	});

	// untuk semua input yg mempunyai class "nominal" akan diformat 3 digit
	// secara otomatis
	$(".nominal").blur(function() {
		$(this).formatCurrency({
			symbol : '',
			colorize : true,
			negativeFormat : '-%s%n',
			roundToDecimalPlace : 0
		});

	}).keyup(function(e) {
		var e = window.event || e;
		var keyUnicode = e.charCode || e.keyCode;
		if (e !== undefined) {
			switch (keyUnicode) {
			case 16:
				break; // Shift
			case 27:
				this.value = '';
				break; // Esc: clear entry
			case 35:
				break; // End
			case 36:
				break; // Home
			case 37:
				break; // cursor left
			case 38:
				break; // cursor up
			case 39:
				break; // cursor right
			case 40:
				break; // cursor down
			case 78:
				break; // N (Opera 9.63+ maps the "." from the number key
						// section to the "N" key too!) (See:
						// http://unixpapa.com/js/key.html search for ". Del")
			case 110:
				break; // . number block (Opera 9.63+ maps the "." from the
						// number block to the "N" key (78) !!!)
			case 190:
				break; // .
			default:
				$(this).formatCurrency({
					symbol : '',
					colorize : true,
					negativeFormat : '-%s%n',
					roundToDecimalPlace : -1,
					eventOnDecimalsEntered : true
				});
			}
		}
	});

	$(".rate").blur(function() {
		$(this).formatCurrency({
			symbol : '',
			colorize : true,
			negativeFormat : '-%s%n',
			roundToDecimalPlace : 3
		});
	});

	function test() {

		require([ "dojo/store/Memory", "dojo/on", "dijit/form/Select",
				"dojo/request", "dojo/json", "dojo/_base/array",
				"dojo/domReady!" ], function(Memory, Select, on, request, JSON,
				arrayUtil) {

			// reset
			reset("_subdiv_kd_id");

			// get json data
			request("http://localhost/PPUC/json/subdivisi?param=OPT", {
				handleAs : "json" // < Parse data from JSON to a JavaScript
									// object
			}).then(function(data) {

				var optionALL = true;
				var selectedId = "EYE";
				var datas = [];
				if (optionALL == true)
					datas.push({
						"name" : "",
						"id" : ""
					});

				arrayUtil.forEach(data, function(item, i) {
					datas.push({
						"name" : item.value,
						"id" : item.key
					});
				});

				setSelect(datas, "_subdiv_kd_id", selectedId);

			}, function(error) {
				console.log("An error occurred: " + error);
			});
		});

	}

}
