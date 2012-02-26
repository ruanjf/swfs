/**
 * 时间对象的格式化;
 */
function fromatdate(cellvalue, options, rowObject) {
	/*
	 * eg:format="yyyy-MM-dd hh:mm:ss";
	 */
	var format = "yyyy-MM-dd hh:mm:ss";
	var date = new Date(cellvalue);
	var o = {
		"M+" : date.getMonth() + 1, // month
		"d+" : date.getDate(), // day
		"h+" : date.getHours(), // hour
		"m+" : date.getMinutes(), // minute
		"s+" : date.getSeconds(), // second
		"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
		"S" : date.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (date.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
function addulink(cellvalue, opts, rowObject) {
	return "<a href=u/" + opts.rowId + " title='"+cellvalue+"' target=_self>" + cellvalue + "</a>";
}
function addclink(cellvalue, opts, rowObject) {
	return "<a href=u/c/" + opts.rowId + " title='"+cellvalue+"' target=_self>" + cellvalue + "</a>";
}
function adduclink(cellvalue, opts, rowObject){
	return "<span onclick=\"companyInfo('"+opts.rowId+"');\" title='"+cellvalue+"'>"+cellvalue+"</span>";
}
function companyInfo(companyId){
	$.ajax({
		  url: 'c/'+companyId,
		  success: function(company){
			//var json = eval("("+data+")"); //转换为json对象
		    var c = "<table><tr><td>公司名称： </td><td>"+company.name+"</td></tr>" +
				"<tr><td>搜索关键字： </td><td>"+company.searchAddress+"</td></tr>" +
				"<tr><td>公司地址： </td><td>"+company.address+"</td></tr>" +
				"<tr><td>公司联系人： </td><td>"+company.contactPerson+"</td></tr>" +
				"<tr><td>联系人手机： </td><td>"+company.contactPhone+"</td></tr>" +
				"<tr><td>联系人座机： </td><td>"+company.contactTelephone+"</td></tr>" +
				"<tr><td>己方负责人： </td><td>"+company.principalName+"</td></tr>" +
				"<tr><td>备注： </td><td>"+company.desc+"</td></tr></table>";
			$("#companyInfo p").html(c);
			$("#companyInfo").dialog({
				title : "客户信息",
				minWidth : 400,
				buttons : {
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			});
		  }
	});
}