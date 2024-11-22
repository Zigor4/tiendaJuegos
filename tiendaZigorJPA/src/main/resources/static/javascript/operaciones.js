
let titulo_a_buscar = ""
let comienzo_resultados = 0


$("#titulo_buscador").keyup(function(){
	titulo_a_buscar = $(this).val()
	comienzo_resultados = 0
	obtenerProductos()
})


function obtenerProductos(){
	$.post("obtener-productos-json",{
		titulo: titulo_a_buscar,
		comienzo: comienzo_resultados
	}).done( function( respuesta ) {
		//vamos a procesar la respuesta con el json de los libros:
		//transformamos el json recibido a array de jacascript:
		let juegos = respuesta.juegos
		console.log(juegos)
		let texto_html = ""
		
		texto_html = Mustache.render( html_listado_productos, juegos )
		
		$("#contenedor").html(texto_html)
		//Una vez volcado el listado, decimos que tiene que hacer
		//el enlace ver detalles:
		$(".enlace_ver_detalles_juego").click(mostrarDetallesProducto)
		
		


		$("#titulo_buscador").keyup(function(){
			titulo_a_buscar = $(this).val()
			comienzo_resultados = 0
			obtenerProductos()
		})
		
		if ((comienzo_resultados+10)<respuesta.totalJuegos){
			$("#enlace_siguiente").show()
		}else{
			$("#enlace_siguiente").hide()
		}
		$("#enlace_siguiente").click(function(){
			comienzo_resultados += 10
			obtenerProductos()
		})

		//anterior
		if (comienzo_resultados<=0){
			$("#enlace_anterior").hide()
		}else{
			$("#enlace_anterior").show()
		}
		
		$("#enlace_anterior").click(function(){
			comienzo_resultados -= 10
			obtenerProductos()
		})
	})//end ajax
}//end obtenerProductos



function mostrarDetallesProducto() {
	//this en JS para eventos, en este caso un click de raton
	//indica el elemento sobre el que se ha hecho click
	let idProducto = $(this).attr("id-producto")
	$.getJSON("obtener-detalles-juego", {
		id: idProducto
	}).done(function(res){
		let html = Mustache.render(html_detalles_juego, res)
		$("#contenedor").html(html)
		$("#enlace_agregar_al_carrito").click(agregarProductoAlCarrito)
	})
}
function agregarProductoAlCarrito(){
	if (nombre_login == "") {
		alert("Tienes que identificarte para poder comprar productos")
		return;
	}
	let idProducto = $(this).attr("id-producto")
	alert("Agregar al carrito del usuario el producto de id " + idProducto)
	$.post("agregar-producto-carrito",{
		id: idProducto,
		cantidad: 1
	}).done(function(res){
		if(res == "ok"){
			alert("Producto agregado al carrito correctamente")
		}
	})
}

function enviarInfoUsuarioAlServidor(){
	let nombre = $("#nombre").val()
	let email = $("#email").val()
	let pass = $("#pass").val()
	let telefono = $("#telefono").val()
	let direccion = $("#direccion").val()
	let dni = $("#dni").val()
	
	if (!validarNombre(nombre) || !validarEmail(email) || !validarPass(pass) || !validarTelefono(telefono) || !validarDireccion(direccion)  || !validarDNI(dni)) {
			alert("Hay datos incorrectos")
			return 
	}
	
	$.post("registrar-usuario-cliente", 
		{
			nombre: nombre,
			email: email,
			pass: pass,
			telefono: telefono,
			direccion: direccion,
			dni: dni
		}		
	).done(function(res){
		alert(res)
	})
	
}//end enviarInfoUsuarioAlServidor

function mostrarFormularioRegistroUsuario(){
    $("#contenedor").html(html_formulario_registro_usuario)
    $("#boton_registro_usuario").click(enviarInfoUsuarioAlServidor)
}//end mostrarFormulario

function mostrarFormularioLogin(){
	$("#contenedor").html(html_formulario_identificacion_usuario)
	
	if ( typeof(Cookies.get("pass")) != "undefined" ){
			$("#pass").val(Cookies.get("pass"))
	}
	if ( typeof(Cookies.get("email")) != "undefined" ){
			$("#email").val(Cookies.get("email"))
	}
	// Para evitar el comportamiento por defecto de un form al 
	// hacer click en el boton de submit
	$("#form_login").submit(
		function(e){
			//La siguiente linea es necesaria para que la página
			//no se refresque con el envío de formulario
			e.preventDefault()
			//Vamos a mandar por post el email y el pass introducido
			//a un servicioWEB de identificacion de usuario
			$.post("identificar-usuario", {
				email: $("#email").val(),
				pass: $("#pass").val()
			}).done(function(res){
				if($("#recordar_datos").prop("checked")){
					Cookies.set("email", $("#email").val(), {expires: 100})
					Cookies.set("pass", $("#pass").val(), {expires: 100})
				}else{
					if ( typeof(Cookies.get("email")) != "undefined" ){
						Cookies.remove("email")
					}
					if ( typeof(Cookies.get("pass")) != "undefined" ){
						Cookies.remove("pass")
					}
				}	
				if(res.operacion == "ok"){
					nombre_login = res.usuario
					alert("Bienvenido " + nombre_login + " ya puedes comprar juegos")
					obtenerProductos()
					mostrarElementosAutenticados()
				}else{
					alert("email o pass incorrecto")
				}
			})
		}//end function		
	)//end submit
}//end mostrarFormularioLogin

function cerrarSesionUsuario() {
	
	$.get("cerrar-sesion-usuario").done(function(res){
		if (res == "ok") {
			alert("Hasta pronto " + nombre_login)
				nombre_login = ""
				mostrarElementosNoAutenticados()
		}
	})
}//end cerrarSesionUsuario

function obtenerProductosCarrito() {
	if(nombre_login == ""){
		alert("Tienes que identificarte para acceder a tu carrito")
		mostrarFormularioLogin()
		return
	}
	$.getJSON("obtener-productos-carrito").done(function(res){
		console.log("Productos del carrito:")
		console.log(res)
		let res_html = Mustache.render(html_carrito,res)
		$("#contenedor").html(res_html)
		$(".enlace_ver_detalles_juego").click(mostrarDetallesProducto)
		let total_productos = 0;
		for(indice in res){
			total_productos += res[indice].cantidad
		}
		$("#total_productos").html(total_productos)
		$("#realizar-pedido").click(checkout_paso_0)
	})
}//end obtenerProductosCarrito

/*function mostrarCerrarSesion() {
  const cerrarSesionLink = document.getElementById('menu-cerrar-sesion');
  if (cerrarSesionLink) {
    cerrarSesionLink.style.display = 'inline-block';
  }
}

// Function to hide the "CERRAR SESION" link when user logs out
function ocultarCerrarSesion() {
  const cerrarSesionLink = document.getElementById('menu-cerrar-sesion');
  if (cerrarSesionLink) {
    cerrarSesionLink.style.display = 'none';
  }
}*/

// Function to check if user is logged in
function comprobarSiUsuarioIdentificado() {
    const token = Cookies.get('token');
    return token !== undefined;
}

function mostrarElementosAutenticados() {
  document.body.classList.add('usuario-identificado');
  actualizarVisibilidadCarrito();
}

function mostrarElementosNoAutenticados() {
  document.body.classList.remove('usuario-identificado');
  actualizarVisibilidadCarrito();
}

// Function to update UI based on authentication state
function actualizarInterfazSegunAutenticacion() {
  if (nombre_login !== "") {
    mostrarElementosAutenticados();
  } else {
    mostrarElementosNoAutenticados();
  }
  actualizarVisibilidadCarrito();
}

// Function to handle cart visibility
function actualizarVisibilidadCarrito() {
  const cartIcon = document.querySelector('.cart-icon');
  if (cartIcon) {
    if (nombre_login !== "") {
      cartIcon.classList.remove('hidden');
    } else {
      cartIcon.classList.add('hidden');
    }
  }
}

// Function to handle logout
function cerrarSesion() {
    Cookies.remove('token');
    document.body.classList.remove('usuario-identificado');
    window.location.href = '/';
}

function cerrarSesionUsuario() {
  $.get("cerrar-sesion-usuario").done(function(res){
    if (res == "ok") {
      alert("Hasta pronto " + nombre_login);
      nombre_login = "";
      mostrarElementosNoAutenticados();
      actualizarVisibilidadCarrito();
    }
  });
}

// Add event listeners
document.addEventListener('DOMContentLoaded', function() {
    actualizarInterfazSegunAutenticacion();
    actualizarVisibilidadCarrito();
    
    // Add click event listener for logout
    const cerrarSesionBtn = document.getElementById('menu-cerrar-sesion');
    if (cerrarSesionBtn) {
        cerrarSesionBtn.addEventListener('click', function(e) {
            e.preventDefault();
            cerrarSesion();
        });
    }
});

// Export functions for use in other scripts
window.comprobarSiUsuarioIdentificado = comprobarSiUsuarioIdentificado;
window.actualizarInterfazSegunAutenticacion = actualizarInterfazSegunAutenticacion;
window.cerrarSesion = cerrarSesion;
window.cerrarSesionUsuario = cerrarSesionUsuario;

// Call this function when the page loads
document.addEventListener('DOMContentLoaded', actualizarInterfazSegunAutenticacion);