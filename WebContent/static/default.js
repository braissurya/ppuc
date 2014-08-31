$(document).ready(function(){
	setting();
});

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
	return (((sign) ? '' : '-') + num );
}

function formatRate(num) {
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

function replaceComma(nilai){
	nilai=nilai.replace(/[^0-9\.]+/g,"");
	if(isNaN(nilai)){
		result=0;
	}else{
		var result=nilai;
		if(result=="")result=nilai;
	}
	//alert(result);
	return result;
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
//				dijit.byId(idSelect).set('value', '');
//				alert(idSelect);
			});
}

function resetMultiSelect(childSelectId){
	$("#ms-"+childSelectId+" .ms-selectable .ms-list").children().remove(); 
	$("#ms-"+childSelectId+" .ms-selection .ms-list").children().remove(); 
	$('#'+childSelectId).children().remove();
//	$('#'+childSelectId).multiSelect('refresh');
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
					setSelectValue(idSelect, selectedId);
				}
				
			});
}

function setSelectValue(idSelect, selectedId) {
	require([  "dijit/form/Select", "dojo/domReady!" ],
	function(Memory, Select) {
		dijit.byId(idSelect).set('value', selectedId);
		
	});
}

function setSelect2(jsonData, idSelect, selectedId, optionALLText) {
	require([ "dojo/store/Memory", "dijit/form/Select", "dojo/domReady!" ],
			function(Memory, Select) {
				dijit.byId(idSelect).store = new dojo.store.Memory({
					data : jsonData
				});
				
				alert(idSelect);
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

function autoPop2(path, ajaxType, parentSelectId, childSelectId, childSelectId2,optionALL,
		selectedId, optionALLText) {
	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/Select",
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, Select, on, request,
					JSON, arrayUtil) {
				
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value")+ '&param2='
								+ dijit.byId(childSelectId).get("value"), {
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

					setSelect(datas, childSelectId2, selectedId, optionALLText);

				}, function(error) {
					console.log("An error occurred: " + error);
				});

			}); // end of on
}

function autoPop3(path, ajaxType, parentSelectId, childSelectId, childSelectId2, childSelectId3,optionALL,
		selectedId, optionALLText) {

	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/Select",
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, Select, on, request,
					JSON, arrayUtil) {
				
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value")+ '&param2='
								+ dijit.byId(childSelectId).get("value")+ '&param3='
								+ dijit.byId(childSelectId2).get("value"), {
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

					setSelect(datas, childSelectId3, selectedId, optionALLText);

				}, function(error) {
					console.log("An error occurred: " + error);
				});

			}); // end of on
}

function autoPop4(path, ajaxType, parentSelectId, childSelectId, childSelectId2, childSelectId3, childSelectId4, optionALL,
		selectedId, optionALLText) {

	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/Select",
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, Select, on, request,
					JSON, arrayUtil) {
				
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value")+ '&param2='
								+ dijit.byId(childSelectId).get("value")+ '&param3='
								+ dijit.byId(childSelectId2).get("value")+ '&param4='
								+ dijit.byId(childSelectId3).get("value"), {
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

					setSelect(datas, childSelectId4, selectedId, optionALLText);

				}, function(error) {
					console.log("An error occurred: " + error);
				});

			}); // end of on
}

function autoPopMulti(path, ajaxType, parentSelectId, childSelectId, optionALL,
		selectedId, optionALLText) {

	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/MultiSelect", "dojo/dom", "dojo/_base/window", 
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, MultiSelect, on,dom,win, request,
					JSON, arrayUtil) {
				
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value"), {
							handleAs : "json" // < Parse data from JSON to a
												// JavaScript object
						}).then(function(data) {
							 arrayUtil.forEach(data, function(item, i) {
							        $('#'+childSelectId).multiSelect('addOption', { value: item.key, text: item.value });
								});
							   
							 for (var i in selectedId) {
								 $('#'+childSelectId).multiSelect('select', [selectedId[i]]); 
								}

				}, function(error) {
					console.log("An error occurred: " + error);
				});
				
			}); // end of on
}

function autoPopMulti2(path, ajaxType, parentSelectId, childSelectId,  childSelectId2, optionALL,
		selectedId, optionALLText) {
	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/MultiSelect", "dojo/dom", "dojo/_base/window", 
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, MultiSelect, on,dom,win, request,
					JSON, arrayUtil) {
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value") + '&param2='
								+ dijit.byId(childSelectId).get("value"), {
							handleAs : "json" // < Parse data from JSON to a
												// JavaScript object
						}).then(function(data) {
							 arrayUtil.forEach(data, function(item, i) {
							        $('#'+childSelectId2).multiSelect('addOption', { value: item.key, text: item.value });
								});
							   
							 for (var i in selectedId) {
								 $('#'+childSelectId2).multiSelect('select', [selectedId[i]]); 
								}

				}, function(error) {
					console.log("An error occurred: " + error);
				});
				
			}); // end of on
}

function autoPopMulti3(path, ajaxType, parentSelectId, childSelectId,  childSelectId2,  childSelectId3, optionALL,
		selectedId, optionALLText) {

	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/MultiSelect", "dojo/dom", "dojo/_base/window", 
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, MultiSelect, on,dom,win, request,
					JSON, arrayUtil) {
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value") + '&param2='
								+ dijit.byId(childSelectId).get("value")+ '&param3='
								+ dijit.byId(childSelectId2).get("value"), {
							handleAs : "json" // < Parse data from JSON to a
												// JavaScript object
						}).then(function(data) {
							 arrayUtil.forEach(data, function(item, i) {
							        $('#'+childSelectId3).multiSelect('addOption', { value: item.key, text: item.value });
								});
							   
							 for (var i in selectedId) {
								 $('#'+childSelectId3).multiSelect('select', [selectedId[i]]); 
								}

				}, function(error) {
					console.log("An error occurred: " + error);
				});
				
			}); // end of on
}

function autoPopMultiParam(path, ajaxType, parentSelectId, childSelectId, optionALL,
		selectedId, optionALLText) {

	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/MultiSelect", "dojo/dom", "dojo/_base/window", 
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, MultiSelect, on,dom,win, request,
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

function autoPopMultiParam2(path, ajaxType, parentSelectId, childSelectId,  childSelectId2, optionALL,
		selectedId, optionALLText) {
	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/MultiSelect", "dojo/dom", "dojo/_base/window", 
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, MultiSelect, on,dom,win, request,
					JSON, arrayUtil) {
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value") + '&param2='
								+ dijit.byId(childSelectId).get("value"), {
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

							setSelect(datas, childSelectId2, selectedId, optionALLText);

				}, function(error) {
					console.log("An error occurred: " + error);
				});
				
			}); // end of on
}

function autoPopMultiParam3(path, ajaxType, parentSelectId, childSelectId,  childSelectId2,  childSelectId3, optionALL,
		selectedId, optionALLText) {

	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/MultiSelect", "dojo/dom", "dojo/_base/window", 
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, MultiSelect, on,dom,win, request,
					JSON, arrayUtil) {
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value") + '&param2='
								+ dijit.byId(childSelectId).get("value")+ '&param3='
								+ dijit.byId(childSelectId2).get("value"), {
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
							setSelect(datas, childSelectId3, selectedId, optionALLText);
							
				}, function(error) {
					console.log("An error occurred: " + error);
				});
				
			}); // end of on
}

function autoPopMultiParam4(path, ajaxType, parentSelectId, childSelectId,  childSelectId2,  childSelectId3, childSelectId4, optionALL,
		selectedId, optionALLText) {

	require(
			[ "dojo/store/Memory", "dojo/on", "dijit/form/MultiSelect", "dojo/dom", "dojo/_base/window", 
					"dojo/request", "dojo/json", "dojo/_base/array",
					"dojo/domReady!" ], function(Memory, MultiSelect, on,dom,win, request,
					JSON, arrayUtil) {
				// get json data
				request(
						path + 'json/' + ajaxType + '?param='
								+ dijit.byId(parentSelectId).get("value") + '&param2='
								+ dijit.byId(childSelectId).get("value")+ '&param3='
								+ dijit.byId(childSelectId2).get("value")+ '&param4='
								+ dijit.byId(childSelectId3).get("value"), {
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
							setSelect(datas, childSelectId4, selectedId, optionALLText);
							
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

function autoPopulateSelect4(path, ajaxType, parentSelectId, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2,childSelectId3,childSelectId4) {
	
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
				if(childSelectId4!=null)reset(childSelectId4);
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
			}) // end of event handler
			
			reset(childSelectId);
			reset(childSelectId2);
			reset(childSelectId3);
			reset(childSelectId4);
			autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
					selectedId, optionALLText);
		});
		/*
		*/
	}); // end of on

}

function autoPopulateSelect5(path, ajaxType, parentSelectId, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2,childSelectId3,childSelectId4,childSelectId5) {
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
//			var s = dijit.byId(parentSelectId);
			
			/*on(s, "change", function(e) {
				
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null)reset(childSelectId2);
				if(childSelectId3!=null)reset(childSelectId3);
				if(childSelectId4!=null)reset(childSelectId4);
				if(childSelectId5!=null)reset(childSelectId5);
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
			}) */// end of event handler
			
			reset(childSelectId);
			reset(childSelectId2);
			reset(childSelectId3);
			reset(childSelectId4);
			reset(childSelectId5);
			
			autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
					selectedId, optionALLText);
		});
		/*
		*/
	}); // end of on

}

function autoPopulateMultiSelect(path, ajaxType, parentSelectId,
		optionALL, optionALLText, selectedId, childSelectId) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				resetMultiSelect(childSelectId);
				autoPopMulti(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
				
			}) // end of event handler
			resetMultiSelect(childSelectId);
			autoPopMulti(path, ajaxType, parentSelectId, childSelectId,
					optionALL, selectedId, optionALLText);
		});
	}); // end of on
	 
	/**/
}


function autoPopulateMultiSelect2(path, ajaxType, parentSelectId, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			var s = dijit.byId(parentSelectId);
			
			on(s, "change", function(e) {
				
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null){resetMultiSelect(childSelectId2);}
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
			}) // end of event handler
			
			reset(childSelectId);
			resetMultiSelect(childSelectId2);
			autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
					selectedId, optionALLText);
		});
		/*
		*/
	}); // end of on

}

function autoPopulateMultiSelect3(path, ajaxType, parentSelectId, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2, childSelectId3) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			var s = dijit.byId(parentSelectId);
			
			on(s, "change", function(e) {
				
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null)reset(childSelectId2);
				if(childSelectId3!=null){resetMultiSelect(childSelectId3);}
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
			}) // end of event handler
			
			reset(childSelectId);
			reset(childSelectId2);
			resetMultiSelect(childSelectId3);
			autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
					selectedId, optionALLText);
		});
		/*
		*/
	}); // end of on

}

function autoPopulateMultiParamSelectMulti(path, ajaxType, parentSelectId,parentSelectId2,parentSelectId3,
		optionALL, optionALLText, selectedId, childSelectId) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				resetMultiSelect(childSelectId);

				autoPopMulti3(path, ajaxType, parentSelectId3, parentSelectId2,parentSelectId,childSelectId,
						optionALL, selectedId, optionALLText);
				
			}) // end of event handler
			resetMultiSelect(childSelectId);
			autoPopMulti3(path, ajaxType,  parentSelectId3, parentSelectId2,parentSelectId,childSelectId,
					optionALL, selectedId, optionALLText);
		});
	}); // end of on
	 
	/**/
}


function autoPopulateMultiParamSelectMulti2(path, ajaxType,ajaxType2, parentSelectId, parentSelectId2, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null){resetMultiSelect(childSelectId2);}
				
				autoPop2(path, ajaxType, parentSelectId2,parentSelectId,childSelectId,
						optionALL, selectedId, optionALLText);
				autoPopMulti3(path, ajaxType2,  parentSelectId2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId, optionALLText);
			}) // end of event handler
			
			reset(childSelectId);
			resetMultiSelect(childSelectId2);
			
			autoPop2(path, ajaxType, parentSelectId2,parentSelectId,childSelectId,
					optionALL, selectedId, optionALLText);
		});
		/*
		*/
	}); // end of on

}

function autoPopulateMultiParamSelectMulti3(path, ajaxType,ajaxType2,ajaxType3, parentSelectId, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2, childSelectId3) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null)reset(childSelectId2);
				if(childSelectId3!=null){resetMultiSelect(childSelectId3);}
				
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
				autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId, optionALLText);
				autoPopMulti3(path, ajaxType3,  parentSelectId, childSelectId,childSelectId2,childSelectId3,
						optionALL, selectedId, optionALLText);
				
			}) // end of event handler
			
			reset(childSelectId);
			reset(childSelectId2);
			resetMultiSelect(childSelectId3);
			autoPop(path, ajaxType, parentSelectId, childSelectId, optionALL,
					selectedId, optionALLText);
			
		});
		/*
		*/
	}); // end of on

}

function autoPopulateMultiParamSelect(path, ajaxType, parentSelectId,parentSelectId2,parentSelectId3,
		optionALL, optionALLText, selectedId, childSelectId) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				reset(childSelectId);

				autoPopMultiParam3(path, ajaxType, parentSelectId3, parentSelectId2,parentSelectId,childSelectId,
						optionALL, selectedId, optionALLText);
				
			}) // end of event handler
			reset(childSelectId);
			autoPopMultiParam3(path, ajaxType,  parentSelectId3, parentSelectId2,parentSelectId,childSelectId,
					optionALL, selectedId, optionALLText);
		});
	}); // end of on
	 
	/**/
}


function autoPopulateMultiParamSelect2(path, ajaxType,ajaxType2, parentSelectId, parentSelectId2, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null){reset(childSelectId2);}
				
				autoPop2(path, ajaxType, parentSelectId2,parentSelectId,childSelectId,
						optionALL, selectedId, optionALLText);
				autoPopMultiParam3(path, ajaxType2,  parentSelectId2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId, optionALLText);
			}) // end of event handler
			
			reset(childSelectId);
			reset(childSelectId2);
			
			autoPop2(path, ajaxType, parentSelectId2,parentSelectId,childSelectId,
					optionALL, selectedId, optionALLText);
		});
		/*
		*/
	}); // end of on

}

function autoPopulateMultiParamSelect3(path, ajaxType,ajaxType2,ajaxType3, parentSelectId, optionALL,
		optionALLText, selectedId, childSelectId, childSelectId2, childSelectId3) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				
				if(childSelectId!=null)reset(childSelectId);
				if(childSelectId2!=null)reset(childSelectId2);
				if(childSelectId3!=null){reset(childSelectId3);}
				
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
				autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId, optionALLText);
				autoPopMultiParam3(path, ajaxType3,  parentSelectId, childSelectId,childSelectId2,childSelectId3,
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

//6 - Tambahan Yusuf -----------------------------------------------------------------------
function autoPopulateSelectJQuery(path, ajaxType, parentSelectId,optionALL, optionALLText, selectedId, childSelectId){
	//ajax autopopulate select box 
	$("#"+childSelectId).children().remove(); 
	$("#"+parentSelectId).on('change',function(){
		
		$("#"+childSelectId).children().remove(); //remove all isinya

		//populate ulang
		$.getJSON(path + 'json/' + ajaxType, { param:$("#"+parentSelectId).val()}, function(data) {
			var html = '';
			if(optionALLText==null)optionALLText="ALL";
			if(optionALL == true) html = '<option value="">'+optionALLText+'</option>';
		    var len = data.length;
		    for (var i = 0; i< len; i++) {
		    	if(selectedId!=null){
		    		var select="";
		    		if(selectedId==data[i].key){
		    			select="selected=\"selected\"";		    			
		    		}
		    		html += '<option value="' + data[i].key + '" '+select+' >' + data[i].value + '</option>';
		    	}else{
		        	html += '<option value="' + data[i].key + '">' + data[i].value + '</option>';
		        }
		    }
		    $("#"+childSelectId).append(html);
		});

	});
}

function autoPopulateSelectJQuery2(path, ajaxType, parentSelectId,optionALL, optionALLText, selectedId, childSelectId, childSelectId2, childSelectId3){
	//ajax autopopulate select box 
	$(parentSelectId).change(function(){
		$(childSelectId).children().remove(); //remove all isinya
		$(childSelectId2).children().remove(); //remove all isinya
		$(childSelectId3).children().remove(); //remove all isinya
		
		//populate ulang
		$.getJSON(path + 'json/' + ajaxType, { param:$(parentSelectId).val()},  function(data) {
			var html = '';
			if(optionALLText==null)optionALLText="ALL";
			if(optionALL == true) html = '<option value="">'+optionALLText+'</option>';
		    var len = data.length;
		    for (var i = 0; i< len; i++) {
		    	if(selectedId!=null){
		    		var select="";
		    		if(selectedId==data[i].key){
		    			select="selected=\"selected\"";		    			
		    		}
		    		html += '<option value="' + data[i].key + '" '+select+' >' + data[i].value + '</option>';
		    	}else{
		        	html += '<option value="' + data[i].key + '">' + data[i].value + '</option>';
		        }
		    }
		    $(childSelectId).append(html);
		});
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
}

function multiselect(multi_id){
	$('#'+multi_id).multiSelect({
		  selectableHeader: "<div class='multi'><a href='#' id='select-all-"+multi_id+"'>Select all >></a></div>",
		  selectionHeader: "<div class='multi'><a href='#' id='deselect-all-"+multi_id+"'><< Deselect all</a></div>"
	});
	
	$('#select-all-'+multi_id).click(function(){
	 $('#'+multi_id).multiSelect('select_all');
	  return false;
	});
	
	$('#deselect-all-'+multi_id).click(function(){
	  $('#'+multi_id).multiSelect('deselect_all');
	  return false;
	});
}

function autoPopulateMultiParamSelect2FieldNoAll(path,optionALL,	optionALLText, parentSelectId, 
		ajaxType,  childSelectId, selectedId,
		ajaxType2, childSelectId2,selectedId2) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
			//untuk parent1
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				
				reset(childSelectId);
				reset(childSelectId2);
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
				
			}) // end of event handler
			
			//untuk parent2
			var s2 = dijit.byId(childSelectId);
			on(s2, "change", function(e) {
				
				reset(childSelectId2);
				
				autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId2, optionALLText);
				
			}) // end of event handler
			

			reset(childSelectId);
			reset(childSelectId2);
			
			autoPop(path, ajaxType, parentSelectId, childSelectId,
					optionALL, selectedId, optionALLText);
			
		});
		/*
		*/
	}); // end of on

}

function autoPopulateMultiParamSelect2Field(path,optionALL,	optionALLText, parentSelectId, 
		ajaxType,  childSelectId, selectedId,
		ajaxType2, childSelectId2,selectedId2) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
			//untuk parent1
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				
				reset(childSelectId);
				reset(childSelectId2);
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
				autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId, optionALLText);
				
			}) // end of event handler
			
			//untuk parent2
			var s2 = dijit.byId(childSelectId);
			on(s2, "change", function(e) {
				
				reset(childSelectId2);
				
				autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId2, optionALLText);
				
			}) // end of event handler
			

			reset(childSelectId);
			reset(childSelectId2);
			
			autoPop(path, ajaxType, parentSelectId, childSelectId,
					optionALL, selectedId, optionALLText);
			autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
					optionALL, selectedId, optionALLText);
			
		});
		/*
		*/
	}); // end of on

}



function autoPopulateMultiParamSelect4Field(path,optionALL,	optionALLText, parentSelectId, 
		ajaxType,  childSelectId, selectedId,
		ajaxType2, childSelectId2,selectedId2,
		ajaxType3, childSelectId3,selectedId3,
		ajaxType4, childSelectId4,selectedId4) {
	
	require([ "dojo/query", "dojo/ready", "dojo/dom-attr", "dijit/form/Select",
			"dijit/form/SimpleTextarea", "dojo/on", "dojo/parser",
			"dojo/domReady!" ], function(query, ready, domAttr, Select,
			SimpleTextarea, on, parser) {
		ready(function() {
			
			//untuk parent1
			var s = dijit.byId(parentSelectId);
			on(s, "change", function(e) {
				
				reset(childSelectId);
				reset(childSelectId2);
				reset(childSelectId3);
				reset(childSelectId4);
				
				autoPop(path, ajaxType, parentSelectId, childSelectId,
						optionALL, selectedId, optionALLText);
				autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId, optionALLText);
				autoPop3(path, ajaxType3, parentSelectId,childSelectId,childSelectId2,childSelectId3,
						optionALL, selectedId, optionALLText);
				autoPop4(path, ajaxType4,  parentSelectId, childSelectId,childSelectId2,childSelectId3,childSelectId4,
						optionALL, selectedId, optionALLText);
				
			}) // end of event handler
			
			//untuk parent2
			var s2 = dijit.byId(childSelectId);
			on(s2, "change", function(e) {
				
				reset(childSelectId2);
				reset(childSelectId3);
				reset(childSelectId4);
				
				autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
						optionALL, selectedId2, optionALLText);
				autoPop3(path, ajaxType3, parentSelectId,childSelectId,childSelectId2,childSelectId3,
						optionALL, selectedId2, optionALLText);
				autoPop4(path, ajaxType4,  parentSelectId, childSelectId,childSelectId2,childSelectId3,childSelectId4,
						optionALL, selectedId2, optionALLText);
				
			}) // end of event handler
			
			//untuk parent2
			var s3 = dijit.byId(childSelectId2);
			on(s3, "change", function(e) {
				
				reset(childSelectId3);
				reset(childSelectId4);
				
				autoPop3(path, ajaxType3, parentSelectId,childSelectId,childSelectId2,childSelectId3,
						optionALL, selectedId3, optionALLText);
				autoPop4(path, ajaxType4,  parentSelectId, childSelectId,childSelectId2,childSelectId3,childSelectId4,
						optionALL, selectedId3, optionALLText);
				
			}) // end of event handler
			
			//untuk parent2
			var s4 = dijit.byId(childSelectId3);
			on(s4, "change", function(e) {
				
				reset(childSelectId4);
				
				autoPop4(path, ajaxType4,  parentSelectId, childSelectId,childSelectId2,childSelectId3,childSelectId4,
						optionALL, selectedId4, optionALLText);
				
			}) // end of event handler

			reset(childSelectId);
			reset(childSelectId2);
			reset(childSelectId3);
			reset(childSelectId4);
			
			autoPop(path, ajaxType, parentSelectId, childSelectId,
					optionALL, selectedId, optionALLText);
			autoPop2(path, ajaxType2, parentSelectId,childSelectId,childSelectId2,
					optionALL, selectedId, optionALLText);
			autoPop3(path, ajaxType3, parentSelectId,childSelectId,childSelectId2,childSelectId3,
					optionALL, selectedId, optionALLText);
			autoPop4(path, ajaxType4,  parentSelectId, childSelectId,childSelectId2,childSelectId3,childSelectId4,
					optionALL, selectedId, optionALLText);
			
		});
		/*
		*/
	}); // end of on

}
