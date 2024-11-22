//Validaciones
let regexp_nombre = /^[a-z áéíóúñ]{2,10}$/i
let regexp_email = /^([a-z0-9_\.-]+)@([0-9a-z\.-]+)\.([a-z\.]+)$/i
let regexp_pass = /^[a-z0-9áéíóúñ]{3,10}$/i
let regexp_telefono = /^([+]{1})?([0-9 ]{9,20})$/i;
let regexp_direccion = /^[a-z0-9áéíóúñ, ]{5,40}$/i;
let regexp_dni = /^([0-9]{8})([a-z]{1})$/i;

let regexp_nombre_completo = /^[a-záéíóúñ\s]{5,40}$/i;
let regexp_provincia = /^[a-záéíóúñ\s]{2,30}$/i;
let regexp_localidad = /^[a-záéíóúñ\s]{2,30}$/i;
let regexp_codigo_postal = /^[0-9]{4,6}$/;
let regexp_telefono_contacto = /^(\+?[0-9\s]{9,20})$/;
let regexp_numero_tarjeta = /^[0-9]{13,19}$/;
let regexp_titular_tarjeta = /^[a-záéíóúñ\s'.\-]{5,40}$/i;
let regexp_cvv = /^[0-9]{3,4}$/;
let regexp_fecha_caducidad = /^(0[1-9]|1[0-2])\/([0-9]{2})$/;
let regexp_detalles_entrega = /^[a-z0-9áéíóúñ\s.,!¡?()]{0,100}$/i;


function validarNombre(nombre) {
	if (regexp_nombre.test(nombre)) {
		return true
	}else{
		alert("Nombre incorrecto, solo letras de 2 a 10 caracteres.")
	}
}

function validarEmail(email) {
	if (regexp_email.test(email)){
		return true
	}else{
		alert("Email incorrecto")
	}
}

function validarPass(pass) {
	if(regexp_pass.test(pass)){
		return true
	}else{
		alert("Password incorrecto: Solo letras y numeros de 3 a 10 caracteres")
	}
}
function validarTelefono(telefono) {
	if(regexp_telefono.test(telefono)){
		return true
	}else{
		alert("Telefono incorrecto: de 9 a 20 números")
	}
}
function validarDireccion(direccion) {
	if(regexp_direccion.test(direccion)){
		return true
	}else{
		alert("Direccion incorrecto: Solo letras, numeros y espacios de 5 a 40 caracteres")
	}
}
function validarDNI(dni) {
	if(regexp_dni.test(dni)){
		return true
	}else{
		alert("DNI incorrecto: 8 números y una letra")
	}
}

function validarNombreCompleto(nombre) {
    if (regexp_nombre_completo.test(nombre)) {
        return true;
    } else {
        alert("Nombre completo incorrecto: Solo letras de 5 a 40 caracteres.");
    }
}


function validarProvincia(provincia) {
    if (regexp_provincia.test(provincia)) {
        return true;
    } else {
        alert("Provincia incorrecta: Solo letras de 2 a 30 caracteres.");
    }
}

function validarLocalidad(localidad) {
    if (regexp_localidad.test(localidad)) {
        return true;
    } else {
        alert("Localidad incorrecta: Solo letras de 2 a 30 caracteres.");
    }
}

function validarCodigoPostal(codigoPostal) {
    if (regexp_codigo_postal.test(codigoPostal)) {
        return true;
    } else {
        alert("Código Postal incorrecto: Debe contener de 4 a 6 dígitos.");
    }
}

function validarTelefonoContacto(telefono) {
    if (regexp_telefono_contacto.test(telefono)) {
        return true;
    } else {
        alert("Teléfono de contacto incorrecto: Debe contener de 9 a 20 dígitos.");
    }
}

function validarNumeroTarjeta(numero) {
    if (regexp_numero_tarjeta.test(numero)) {
        return true;
    } else {
        alert("Número de tarjeta incorrecto: Debe contener de 13 a 19 dígitos.");
    }
}

function validarTitularTarjeta(titular) {
    if (regexp_titular_tarjeta.test(titular)) {
        return true;
    } else {
        alert("Titular de la tarjeta incorrecto: Solo letras y espacios de 5 a 40 caracteres.");
    }
}

function validarCVV(cvv) {
    if (regexp_cvv.test(cvv)) {
        return true;
    } else {
        alert("CVV incorrecto: Debe contener 3 o 4 dígitos.");
    }
}

function validarFechaCaducidad(fecha) {
    if (regexp_fecha_caducidad.test(fecha)) {
        return true;
    } else {
        alert("Fecha de caducidad incorrecta: Formato MM/AA.");
    }
}

function validarDetallesEntrega(detalles) {
    if (regexp_detalles_entrega.test(detalles)) {
        return true;
    } else {
        alert("Detalles de entrega incorrectos: Solo letras, números y ciertos caracteres especiales, hasta 100 caracteres.");
    }
}
