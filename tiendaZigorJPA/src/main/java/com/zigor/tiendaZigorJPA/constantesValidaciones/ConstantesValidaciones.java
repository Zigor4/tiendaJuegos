package com.zigor.tiendaZigorJPA.constantesValidaciones;

public class ConstantesValidaciones {

	public final static String regExpNombreRegistroUsuario = "^[a-z áéíóúñ]{2,10}$";
	
	public final static String regExpEmailRegistroUsuario = "^([a-z0-9_\\.-]+)@([0-9a-z\\.-]+)\\.([a-z\\.]+)$";
	
	public final static String regExpPassRegistroUsuario = "^[a-z0-9 áéíóúñ]{3,10}$";
	
	public final static String regExpTelefonoRegistroUsuario = "^([+]{1})?([0-9 ]{9,20})";
	
	public final static String regExpDireccionRegistroUsuario = "^[a-z0-9 áéíóúñ,]{5,40}$";
	
	public final static String regExpDNIUsuario = "^([0-9]{8})([a-z]{1})$";
	
	public final static String regExpNombreCompletoCheckout = "^[a-záéíóúñ\\s]{5,40}$";
	
	public final static String regExpDireccionCheckout = "^[a-z0-9áéíóúñ\\s,\\.\\-]{5,60}$";
	
	public final static String regExpProvinciaCheckout = "^[a-záéíóúñ\\s]{2,30}$";
	
	public final static String regExpLocalidadCheckout = "^[a-záéíóúñ\\s]{2,30}$";
	
	public final static String regExpCodigoPostalCheckout = "^[0-9]{4,6}$";
	
	public final static String regExpTelefonoContactoCheckout = "^\\+?[0-9\\s]{9,20}$";
	
	public final static String regExpNumeroTarjetaCheckout = "^[0-9]{13,19}$";
	
	public final static String regExpTitularTarjetaCheckout = "^[a-záéíóúñ\\s'.\\-]{5,40}$";
	
	public final static String regExpCVVCheckout = "^[0-9]{3,4}$";
	
	public final static String regExpFechaCaducidadCheckout = "^(0[1-9]|1[0-2])/([0-9]{2})$";
	
	public final static String regExpDetallesEntregaCheckout = "^[a-z0-9áéíóúñ\\s.,!¡?()]{0,100}$";

	
}
