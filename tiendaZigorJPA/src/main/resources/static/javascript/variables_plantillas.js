//Estas variables tienen los bloques de HTML que iremos mostrando
//al usuario donde haga click
let html_formulario_registro_usuario = ""
let html_listado_productos = ""
let html_formulario_identificacion_usuario = ""
let html_detalles_juego = ""
let html_carrito = ""
let html_checkout_1 = ""
let html_checkout_2 = ""
let html_checkout_3 = ""
let html_checkout_4 = ""

//Vamos a descargar todas las plantillas:
$.get("plantillas"+ sufijo_idioma +"/formulario-registro-usuario.html").done(function(res){
		html_formulario_registro_usuario = res
	})
	
$.get("plantillas"+ sufijo_idioma +"/listado-productos.html").done(function(res){
		html_listado_productos = res
	})
	
$.get("plantillas"+ sufijo_idioma +"/formulario-identificacion-usuario.html").done(function(res){
		html_formulario_identificacion_usuario = res
	})
$.get("plantillas"+ sufijo_idioma +"/detalles-juego.html").done(function(res){
		html_detalles_juego = res
	})
$.get("plantillas"+ sufijo_idioma +"/carrito.html").done(function(res){
		html_carrito = res
	})
$.get("plantillas"+ sufijo_idioma +"/checkout_1.html").done(function(res){
		html_checkout_1 = res
	})
$.get("plantillas"+ sufijo_idioma +"/checkout_2.html").done(function(res){
		html_checkout_2 = res
	})
$.get("plantillas"+ sufijo_idioma +"/checkout_3.html").done(function(res){
		html_checkout_3 = res
	})
$.get("plantillas"+ sufijo_idioma +"/checkout_4.html").done(function(res){
		html_checkout_4 = res
	})
//fin operaciones con plantillas



