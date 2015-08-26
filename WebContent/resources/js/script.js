function limpiarFormularioAdm(){
	$("#formDialog").find('input:text, input:password, select,input:file, textarea').val('');
}
/*function openDialogCrearDefecto(){
	console.log("entre");
	var url="http://localhost:8080/sgdbex/views/reportarDefecto/reportarDefecto.jsf";
	opendialog(url);
}

 //-----------------------------------------------------------------
	function opendialog(page) {
		console.log("ëntre open dialog page: "+page);
		var $dialog = $("#dialog")
			.html('<iframe id="iframe" style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
			.dialog({
				title: "Reportar Defecto",
				autoOpen: false,
				modal: true,
				height: 500,
				width: 650,
				draggable:true,
				scrollbars:true
			});
		$(".ui-dialog").css("z-index", "100");
		$dialog.dialog('open');
	 };
	$(document).ready(function () {
	    $('#iframe').load(function () {
	        var iframe = $('#iframe').contents();

	        iframe.find("#cancelar").click(function(){
	    		console.log("cargando");
	        });

	    });
	});
	function closeDialog() {
	    $('#dialog').hide().html('');
	}*/