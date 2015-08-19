function openDialogCrearDefecto(){
	console.log("entre");
    //var modal = showModalDialog ("views/reportarDefecto/reportarDefecto.jsf", "Nombre", "dialogWidth:200px; dialogHeight:200px; dialogLeft:300px;").focus();
    //var modal2 = window.open("http://localhost:8080/sgdbex/views/reportarDefecto/reportarDefecto.jsf");//, "Nombre","resizable=yes,scrollbars=yes,status=yes,modal=yes");
	var url="http://localhost:8080/sgdbex/views/reportarDefecto/reportarDefecto.jsf";
    //openPopUp(url);
	opendialog(url);
}

 //-----------------------------------------------------------------
	function opendialog(page) {
		//$('#overlay').css('height', $(document.body).height() + 'px')
	    //$('#overlay').show()
		//entre opendialog page
		//disableParentWin();
		console.log("ëntre open dialog page: "+page);
		//+'<script>$("#confirmacion").click(function() {console.log("confirmacion");closeDialog();});'+
		//'$("#cancelar").click(function() {console.log("cancelado");closeDialog();});</script></iframe>'
		var $dialog = $("#dialog")
			.html('<iframe id="iframe" style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
			.dialog({
				title: "Reportar Defecto",
				autoOpen: false,
				//dialogClass: 'dialog_fixed,ui-widget-header',
				modal: true,
				height: 500,
				width: 650,
				draggable:true,
				//resizable:true,
				scrollbars:true
			});
		$(".ui-dialog").css("z-index", "100");
		$dialog.dialog('open');
	 };
	/*$("#iframe").load(function(){
		console.log("cargando canc");
        var iframe = $('#iframe').contents();
        iframe.find("#cancelar").click(function(){
               alert("test canc");
               $('#dialog').hide().html('');
        });
	});
	$("#iframe").load(function(){
		console.log("cargando");
        var iframe = $('#iframe').contents();
        iframe.find("#confirmacion").click(function(){
               alert("test conf");
               $('#dialog').hide().html('');
        });
	});*/
	$(document).ready(function () {
	    $('#iframe').load(function () {
	        var iframe = $('#iframe').contents();

	        iframe.find("#cancelar").click(function(){
	    		console.log("cargando");
	        });

	    });
	});
	function closeDialog() {
	    //$('#overlay').hide();
	    $('#dialog').hide().html('');
	}