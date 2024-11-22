function checkout_paso_0(){
	//mostrar el formulario donde insertar la informacion de envio
	$("#contenedor").html(html_checkout_1)
	$("#aceptar_paso_1").click(checkout_paso_1_aceptar)
}//end checkout_paso_0

function checkout_paso_1_aceptar(){
	let nombre = $("#campo_nombre").val()
	let direccion = $("#campo_direccion").val()
	let provincia = $("#campo_provincia").val() 
	let localidad = $("#campo_localidad").val() 
	let codigoPostal = $("#campo_codigo_postal").val() 
	let telefono = $("#campo_telefono").val() 
	
	if (!validarNombreCompleto(nombre) || 
	        !validarDireccion(direccion) || 
	        !validarProvincia(provincia) || 
	        !validarLocalidad(localidad) || 
	        !validarCodigoPostal(codigoPostal) || 
	        !validarTelefonoContacto(telefono)) {
	        alert("Hay datos incorrectos en el Paso 1");
	        return;
	 }
	
	
	//Ahora lo suyo sería validar los valores recogidos
	//TODO
	
	$.post("realizar-pedido-paso1",{
		nombre: nombre,
		direccion: direccion,
		provincia: provincia,
		localidad: localidad,
		codigoPostal: codigoPostal,
		telefono: telefono
	}).done(function(res){
		if(res == "ok") {
			$("#contenedor").html(html_checkout_2)
			$("#aceptar_paso_2").click(checkout_paso_2_aceptar)
		}else{
			alert(res)
		}
	})
}//end checkout_paso_1_aceptar

function checkout_paso_2_aceptar() {
	let tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val()
	let numero_tarjeta = $("#numero_tarjeta").val()
	let titular_tarjeta = $("#titular_tarjeta").val()
	let numero_seguridad_tarjeta = $("#numero_seguridad_tarjeta").val()
	let fecha_caducidad_tarjeta = $("#fecha_caducidad_tarjeta").val()
	
	if (tipo_tarjeta === "0") {
	       alert("Por favor, selecciona un tipo de tarjeta.");
	       event.preventDefault();
	       return false;
	   }
	
	if (!validarNumeroTarjeta(numero_tarjeta) || 
	       !validarTitularTarjeta(titular_tarjeta) || 
	       !validarCVV(numero_seguridad_tarjeta) || 
	       !validarFechaCaducidad(fecha_caducidad_tarjeta)) {
	       alert("Hay datos incorrectos en el Paso 2");
	       return;
	}
	
	$.post("realizar-pedido-paso2",{
		tarjeta: tipo_tarjeta,
		numero: numero_tarjeta,
		titular: titular_tarjeta,
		numeroSeguridadTarjeta: numero_seguridad_tarjeta,
		fechaCaducidadTarjeta: fecha_caducidad_tarjeta
	}).done(function(res){
		if(res == "ok") {
			$("#contenedor").html(html_checkout_3)
			$("#aceptar_paso_3").click(checkout_paso_3_aceptar)
		}else{
			alert(res)
		}
	})
	
}//end checkout_paso_2_aceptar

function checkout_paso_3_aceptar() {
	let detallesEntrega = $("#detalles_entrega").val()
	let valoracionWeb = $("#valoracion_web").find(":selected").val()
	
	if (!validarDetallesEntrega(detallesEntrega)) {
	        alert("Hay datos incorrectos en el Paso 3");
	        return;
	}
	
	if (valoracionWeb === "0") {
	        alert("Por favor, selecciona una valoración del 1 al 5.");
	        event.preventDefault();
	        return false;
	}
	
	$.post("realizar-pedido-paso3",{
		detallesEntrega: detallesEntrega,
		valoracionWeb: valoracionWeb
		}).done(function(res){
			console.log("resumen del pedido:")
			console.log(res)
			let html = Mustache.render(html_checkout_4, res)
			$("#contenedor").html(html)
			$("#boton_confirmar_pedido").click(confirmar_pedido)
		})
	
}// end checkout_paso_3_aceptar

function confirmar_pedido() {
	$.post("confirmar_pedido").done(function(res){
		alert("respuesta del REST de Pedidos: " + res)
		if(res == "pedido completado") {
			alert("Gracias por realizar tu pedido")
			obtenerProductos()
		}
	})
}//end confirmar_pedido
