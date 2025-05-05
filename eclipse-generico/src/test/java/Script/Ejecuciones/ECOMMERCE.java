package Script.Ejecuciones;

import java.util.Hashtable;

import Core.Selenium.InitSelenium;

public class ECOMMERCE {
	
	Business.Ecommers			Ecom 			= new Business.Ecommers();
	Business.OperEcommerce 		OperForm 		= new Business.OperEcommerce();
	Core.Selenium.AccionesWeb  	AccionesWeb 	= new Core.Selenium.AccionesWeb();
	InitSelenium 				init 			= new InitSelenium();
	
	public void Prepago
(Hashtable<String, String> Data) throws Exception{
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		String PagoTarjeta = Data.get("PagoTarjeta");
		System.out.println("Se da inicio a flujo: "+ Flujo + " / Tipo de Flujo: "+TipoFlujo);
		
		Ecom.IniciarEcomm(Flujo, Data);		
//		OperForm.capturarPantalla("1 Home Catálogo", Data);		
		OperForm.buscarEquipo(Data);
//		OperForm.capturarPantalla("2 Búsqueda de equipo", Data);
		OperForm.seleccionarEquipo(Data);
//		OperForm.capturarPantalla("3 Equipo seleccionado", Data);	
		OperForm.presionarBtnContinuarCarritoPrepago(Data);
		OperForm.presionarBtnSiguienteConfirmaEquipo(Data);
		OperForm.ingresarNombre(Data);
		OperForm.ingresarApellido(Data);
		OperForm.ingresarRUT(Data);
		OperForm.ingresarNumeroTelefonico(Data);
		OperForm.ingresarCorreoElectronico(Data);
		OperForm.ingresarFechaNacimiento(Data);
//		OperForm.capturarPantalla("4 Datos del titular 1", Data);	
		OperForm.ingresarRegion(Data);
		OperForm.ingresarComuna(Data);
		OperForm.escribirSesionPostpago(Data);
		OperForm.ingresarCalleNumero(Data);
//		OperForm.capturarPantalla("5 Datos del titular 2", Data);	
		OperForm.presionarBtnSiguienteValidacionIdentidad(Data);
		OperForm.marcarCheckboxTerminos(Data);
		OperForm.presionarBtnSiguienteTipoEnvio(Data);		
//		OperForm.capturarPantalla("6 Acepta términos y condiciones", Data);	
//		OperForm.presionarBtnSiguienteDireccionEntregaPrepago(Data);
		Thread.sleep(15000);
		OperForm.PagarWebpay(PagoTarjeta);
		OperForm.escribirNroOrden(Data);	
//		OperForm.escribirTipoProducto(Data);
//		OperForm.escribirFechaTransaccion(Data);
//		OperForm.escribirTipoPago(Data);
//		OperForm.escribirTipoEnvio(Data);
//		OperForm.capturarPantalla("7 Confirmación de compra", Data);
	}
		
	public void LineaNueva
	(Hashtable<String, String> Data) throws Exception {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		System.out.println("Se da inicio a flujo: "+ Flujo + " / Tipo de Flujo: "+TipoFlujo);
		
		Ecom.IniciarEcomm(Flujo, Data);	
		OperForm.ingresarRut(Data);
		OperForm.ingresarNroTelefono(Data);
		OperForm.ingresarEmail(Data);
		OperForm.capturarPantalla("1 Formulario inicial", Data);	
		OperForm.marcarCheckboxTerminos(Data);
		OperForm.marcarCheckboxConsentimiento(Data);
		OperForm.capturarPantalla("2 Formulario inicial completado", Data);	
		OperForm.presionarBtnLineaNueva(Data);		
//		OperForm.seleccionarPlanCatalogo(Data);		
		OperForm.ingresarNumSerie(Data);
		OperForm.capturarPantalla("3 Nro de serie ingresado", Data);	
//		OperForm.presionarBtnSiguienteNroDeSerie(Data);			
//		OperForm.pruebaValidacionRadioButton(Data);		
		OperForm.ingresarNombre(Data);
		OperForm.escribirSesionPostpago(Data);
		OperForm.ingresarApellido(Data);
		OperForm.ingresarFechaNacimiento(Data);
		OperForm.ingresarRegion(Data);
		OperForm.ingresarComuna(Data);
		OperForm.ingresarCalleNumero(Data);
		OperForm.capturarPantalla("4 Datos ingresados", Data);	
		OperForm.presionarBtnSiguienteValidacionIdentidad(Data);		
//		OperForm.seleccionarTipoEnvio(Data);
		OperForm.capturarPantalla("5 Tipo de envío seleccionado", Data);	
		OperForm.presionarBtnSiguienteTipoEnvio(Data);
		OperForm.capturarPantalla("6 Dirección de entrega", Data);	
//		OperForm.presionarBtnSiguienteDireccionEntrega(Data);
		OperForm.escribirNroOrden(Data);
//		OperForm.escribirFechaTransaccion(Data);
//		OperForm.escribirTipoEnvio(Data);
		OperForm.capturarPantalla("7 Confirmación de compra", Data);
	}
		
	public void LineaNuevaEquipo
	(Hashtable<String, String> Data) throws Exception {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		String PagoTarjeta = Data.get("PagoTarjeta");
		System.out.println("Se da inicio a flujo: "+ Flujo + " / Tipo de Flujo: "+TipoFlujo);
		
		Ecom.IniciarEcomm(Flujo, Data);
//		OperForm.capturarPantalla("1 Pantalla inicial -  Equipo", Data);	
		OperForm.presionarBtnContinuarCarritoPostpago(Data);
		OperForm.capturarPantalla("2 Formulario", Data);	
		OperForm.ingresarRut(Data);
		OperForm.ingresarNroTelefono(Data);
		OperForm.ingresarEmail(Data);
//		OperForm.capturarPantalla("3 Formulario completo", Data);	
		OperForm.marcarCheckboxTerminos(Data);
//		OperForm.capturarPantalla("4 Checkbox", Data);	
		OperForm.presionarBtnLineaNueva(Data);	
//		OperForm.capturarPantalla("5 Formulario 2", Data);
		OperForm.ingresarNroSerieNLE(Data);			
		OperForm.ingresarNombre(Data);
		OperForm.escribirSesionPostpago(Data);
		OperForm.ingresarApellido(Data);
		OperForm.ingresarFechaNacimiento(Data);
		OperForm.ingresarRegion(Data);
		OperForm.ingresarComuna(Data);
		OperForm.ingresarCalleNumero(Data);
//		OperForm.capturarPantalla("6 Formulario 2 completo", Data);	
		OperForm.presionarBtnSiguienteValidacionIdentidad(Data);
		OperForm.seleccionarTipoEnvio(Data);
//		OperForm.capturarPantalla("7 Tipo de envío seleccionado", Data);	
		OperForm.presionarBtnSiguienteTipoEnvio(Data);
//		OperForm.presionarBtnSiguienteDireccionEntrega(Data);
//		OperForm.presionarBtnSiguientePagaCompra(Data);
		
//		OperForm.capturarPantalla("8 Dirección de entrega", Data);
				
		Thread.sleep(10000);
		OperForm.PagarWebpay(PagoTarjeta);
		OperForm.escribirNroOrden(Data);
		OperForm.escribirTipoProducto(Data);
		OperForm.escribirFechaTransaccion(Data);
		OperForm.escribirTipoPago(Data);
		OperForm.escribirTipoEnvio(Data);	
		OperForm.capturarPantalla("9 Confirmación de compra", Data);
	}
		
	public void Portabilidad
	(Hashtable<String, String> Data) throws Exception {
		String Flujo = Data.get("Flujo");
		System.out.println("Se da inicio a flujo "+ Flujo);
		
		Ecom.IniciarEcomm(Flujo, Data);
//		OperForm.capturarSesionPostpago(Data);
		OperForm.capturarPantalla("1 Formulario de contratación en línea", Data);	
		OperForm.ingresarRut(Data);
		OperForm.ingresarNroTelefono(Data);
		OperForm.ingresarEmail(Data);
		OperForm.capturarPantalla("2 Datos de ingresados", Data);	
		OperForm.marcarCheckboxTerminos(Data);
		Thread.sleep(5000);
		OperForm.marcarCheckboxConsentimiento(Data);
		OperForm.capturarPantalla("3 Check de terminos y consentimiento", Data);	
		OperForm.presionarBtnPortarLinea(Data);
//		OperForm.btnPortabilidad(Data);
//		OperForm.seleccionarPlanCatalogo(Data);	
//		OperForm.capturarPantalla("4 Plan seleccionado", Data);	
		OperForm.ingresarNumSerie(Data);
		OperForm.capturarPantalla("5 Numero de serie ingresado", Data);	
//		OperForm.ingresarCodigoPortabilidad(Data);
//		OperForm.capturarPantalla("6 Código de portabilidad ingresado", Data);	
//		OperForm.presionarBtnSiguienteNroDeSerie(Data);
		Thread.sleep(30000);
		OperForm.ingresarNombre(Data);
		OperForm.ingresarApellido(Data);
		OperForm.ingresarFechaNacimiento(Data);
		OperForm.ingresarRegion(Data);
		OperForm.ingresarComuna(Data);
		OperForm.ingresarCalleNumero(Data);
		OperForm.capturarPantalla("7 Formulario de datos completado", Data);	
		OperForm.presionarBtnSiguienteValidacionIdentidad(Data);
		OperForm.seleccionarTipoEnvio(Data);
		OperForm.capturarPantalla("8Tipo de envio seleccionado", Data);	
		OperForm.presionarBtnSiguienteTipoEnvio(Data);
		OperForm.presionarBtnSiguienteDireccionEntrega(Data);
		OperForm.escribirNroOrden(Data);
		OperForm.escribirTipoProducto(Data);
		OperForm.escribirFechaTransaccion(Data);
		OperForm.escribirTipoPago(Data);
		OperForm.escribirTipoEnvio(Data);	
		OperForm.capturarPantalla("9 Confirmación de compra", Data);
	}
	
	public void PortabilidadEquipo
	(Hashtable<String, String> Data) throws Exception {
		String Flujo = Data.get("Flujo");
		String PagoTarjeta = Data.get("PagoTarjeta");
		System.out.println("Se da inicio a flujo "+ Flujo);
		
		Ecom.IniciarEcomm(Flujo, Data);
		OperForm.capturarPantalla("1 Home portabilidad equipo", Data);
		OperForm.presionarBtnContinuarCarritoPostpago(Data);
		OperForm.capturarPantalla("2 Carrito de compras", Data);
//		OperForm.escribirSesionPostpago(Data);
		OperForm.ingresarRut(Data);
		OperForm.ingresarNroTelefono(Data);
		OperForm.ingresarEmail(Data);
		OperForm.capturarPantalla("3 Datos Formulario", Data);
		OperForm.marcarCheckboxTerminos(Data);
		OperForm.capturarPantalla("4 Checkbox términos", Data);
		OperForm.presionarBtnPortarLinea(Data);
		OperForm.capturarPantalla("5 Catálogo de planes", Data);
		Thread.sleep(10000);
//		OperForm.btnPortabilidad(Data);
//		OperForm.seleccionarPlanCatalogo(Data);	
//		OperForm.capturarPantalla("6 Plan seleccionado", Data);
		OperForm.ingresarNumSerie(Data);
		
		
		OperForm.ingresarCodigoPortabilidad(Data);
//		OperForm.capturarPantalla("7 Código de portabilidad ingresado", Data);
//		OperForm.presionarBtnSiguienteNroDeSerie(Data);
		
		
		OperForm.ingresarNombre(Data);
		OperForm.ingresarApellido(Data);
		OperForm.ingresarFechaNacimiento(Data);
		OperForm.capturarPantalla("8 Datos formulario 2", Data);
		OperForm.ingresarRegion(Data);
		OperForm.ingresarComuna(Data);
		OperForm.ingresarCalleNumero(Data);
		OperForm.capturarPantalla("9 Formulario 2 completo", Data);
		OperForm.presionarBtnSiguienteValidacionIdentidad(Data);
		OperForm.seleccionarTipoEnvio(Data);
		OperForm.capturarPantalla("10 Tipo de envío seleccionado", Data);
		OperForm.presionarBtnSiguienteTipoEnvio(Data);
		OperForm.presionarBtnSiguienteDireccionEntrega(Data);
		Thread.sleep(15000);
		OperForm.PagarWebpay(PagoTarjeta);
		OperForm.escribirNroOrden(Data);
		OperForm.escribirTipoProducto(Data);
		OperForm.escribirFechaTransaccion(Data);
		OperForm.escribirTipoPago(Data);
		OperForm.escribirTipoEnvio(Data);	
		OperForm.capturarPantalla("11 Confirmación de compra", Data);
	}
		
	public void Migracion
	(Hashtable<String, String> Data) throws Exception {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		System.out.println("Se da inicio a flujo: "+ Flujo + " / Tipo de Flujo: "+TipoFlujo);
		
		Ecom.IniciarEcomm(Flujo, Data);
//		OperForm.escribirSesionPostpago(Data);
		OperForm.ingresarRut(Data);
		OperForm.ingresarNroTelefono(Data);
		OperForm.ingresarEmail(Data);
		OperForm.capturarPantalla("1 Datos Formulario", Data);	
		OperForm.marcarCheckboxTerminos(Data);
		OperForm.marcarCheckboxConsentimiento(Data);
		OperForm.capturarPantalla("2 Checkbox términos", Data);	
		OperForm.presionarBtnMigracion(Data);
		OperForm.capturarPantalla("3 Migración seleccionado", Data);	
		OperForm.seleccionarPlanCatalogo(Data);
		OperForm.capturarPantalla("4 Plan seleccionado", Data);	
		OperForm.ingresarNumSerie(Data);
		OperForm.ingresarCodigoSms(Data);
		OperForm.capturarPantalla("5 Numero de serie y codigo SMS ingresado", Data);	
		OperForm.presionarBtnSiguienteNroDeSerie(Data);		
		OperForm.ingresarNombre(Data);
		OperForm.ingresarApellido(Data);
		OperForm.ingresarFechaNacimiento(Data);
		OperForm.capturarPantalla("6 Datos del titular 1", Data);	
		OperForm.ingresarRegion(Data);
		OperForm.ingresarComuna(Data);
		OperForm.ingresarCalleNumero(Data);
		OperForm.capturarPantalla("7 Datos del titular 2", Data);	
		OperForm.presionarBtnSiguienteValidacionIdentidad(Data);
		OperForm.capturarPantalla("8 Dirección de entrega", Data);	
		OperForm.presionarBtnSiguienteDireccionEntrega(Data);
		OperForm.escribirNroOrden(Data);
		OperForm.escribirFechaTransaccion(Data);
		OperForm.escribirTipoEnvio(Data);
		OperForm.capturarPantalla("9 Confirmación de compra", Data);	
	}
		
	public void Hogar
	(Hashtable<String, String> Data) throws Exception {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		System.out.println("Se da inicio a flujo: "+ Flujo + " / Tipo de Flujo: "+TipoFlujo);
		
		Ecom.IniciarEcomm(Flujo, Data);
		OperForm.capturarPantalla("1 Home Hogar", Data);		
		OperForm.seleccionarServicioHogar(Data);
		OperForm.capturarPantalla("2 Servicio hogar seleccionado", Data);		
		OperForm.ingresarRut(Data);
		OperForm.ingresarNroTelefono(Data);
		OperForm.ingresarEmail(Data);
		OperForm.capturarPantalla("3 Datos ingresados", Data);		
		OperForm.marcarCheckboxTerminosHogar(Data);	
		OperForm.capturarPantalla("4 Checkbox términos", Data);		
		OperForm.seleccionarRegionHogar(Data);
		OperForm.seleccionarComunaHogar(Data);
		OperForm.seleccionarTipoViviendaHogar(Data);
		OperForm.capturarPantalla("5 Datos dirección", Data);		
		OperForm.seleccionarPlanHogar(Data)	;
		OperForm.capturarSesionHogar(Data);
		OperForm.ingresarCarnetHogar(Data);
//		OperForm.ingresarApellidoHogar(Data);
		OperForm.ingresarNumeroContactoHogar(Data);
		OperForm.seleccionarFechaCalendario(Data);
		OperForm.capturarPantalla("6 Otros datos", Data);		
		OperForm.marcarRadioButtonAm(Data);
		OperForm.presionarBtnFinalizarDatosInstallHogar(Data);
		OperForm.escribirNroOrdenHogar(Data);
		OperForm.escribirFechaTransaccionHogar(Data);	
		OperForm.capturarPantalla("7 Confirmación de compra", Data);		
	}
	
	public void HogarSVA
(Hashtable<String, String> Data) throws Exception {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		System.out.println("Se da inicio a flujo: "+ Flujo + " / Tipo de Flujo: "+TipoFlujo);
		
		Ecom.IniciarEcomm(Flujo, Data);
		OperForm.capturarPantalla("1 Home Hogar", Data);
		OperForm.seleccionarServicioHogar(Data);
		OperForm.capturarPantalla("2 Servicio hogar seleccionado", Data);	
		OperForm.ingresarRut(Data);
		OperForm.ingresarNroTelefono(Data);
		OperForm.ingresarEmail(Data);
		OperForm.capturarPantalla("3 Datos ingresados", Data);	
		OperForm.marcarCheckboxTerminosHogar(Data);	
		OperForm.capturarPantalla("4 Checkbox términos", Data);	
		OperForm.seleccionarRegionHogar(Data);
		OperForm.seleccionarComunaHogar(Data);
		OperForm.seleccionarTipoViviendaHogar(Data);
		OperForm.capturarPantalla("5 Datos dirección", Data);	
		OperForm.seleccionarPlanHogar(Data);
		OperForm.agregarSvaHogar(Data);
		OperForm.capturarPantalla("6 SVA agregado", Data);
		OperForm.capturarSesionHogar(Data);
		OperForm.ingresarCarnetHogar(Data);
//		OperForm.ingresarApellidoHogar(Data);
		OperForm.ingresarNumeroContactoHogar(Data);
		OperForm.seleccionarFechaCalendario(Data);
		OperForm.capturarPantalla("7 Otros datos", Data);	
		OperForm.marcarRadioButtonAm(Data);
		OperForm.presionarBtnFinalizarDatosInstallHogar(Data);
		OperForm.escribirNroOrdenHogar(Data);
		OperForm.escribirFechaTransaccionHogar(Data);
		OperForm.capturarPantalla("7 Confirmación de compra", Data);
	}
	
	public void Recambio
	(Hashtable<String, String> Data) throws Exception {	
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		String PagoTarjeta = Data.get("PagoTarjeta");
		System.out.println("Se da inicio a flujo: "+ Flujo + " / Tipo de Flujo: "+TipoFlujo);	
		
		Ecom.IniciarEcomm(Flujo, Data);		
		OperForm.capturarPantalla("Pantalla inicial", Data);	
		OperForm.presionarBtnContinuarCarritoPostpago(Data);		
		OperForm.ingresarNombre(Data);
		OperForm.ingresarApellido(Data);
		OperForm.ingresarRutRecambio(Data);
		OperForm.ingresarNroTelefonoRecambio(Data);
		OperForm.capturarPantalla("2 Formulario Proceso de compra", Data);	
		OperForm.ingresarSmsRecambio(Data);
		OperForm.capturarPantalla("3 Ingresa SMS", Data);	
		OperForm.ingresarEmailRecambio(Data);
		OperForm.ingresarFechaNacimiento(Data);
		OperForm.capturarPantalla("4 Vuelve al formulario", Data);	
		OperForm.ingresarRegion(Data);
		OperForm.ingresarComuna(Data);
		OperForm.ingresarCalleNumero(Data);
		OperForm.capturarPantalla("5 Completa formulario", Data);	
		OperForm.presionarBtnSiguienteValidacionIdentidad(Data);
		OperForm.capturarPantalla("6 Envia formulario", Data);	
		OperForm.presionarBtnSiguienteTipoEnvio(Data);
		OperForm.marcarCheckboxTerminos(Data);
		OperForm.capturarPantalla("7 Confirmación de datos de entrega", Data);	
		OperForm.presionarBtnSiguienteDireccionEntregaRecambio(Data);
		Thread.sleep(20000);
		OperForm.PagarWebpay(PagoTarjeta);
		OperForm.escribirNroOrden(Data);
		OperForm.escribirFechaTransaccion(Data);
		OperForm.escribirTipoEnvio(Data);
		OperForm.capturarPantalla("7 Confirmación de compra", Data);
	}
	
	public void LineaNuevaEP
	(Hashtable<String, String> Data) throws Exception {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		System.out.println("Se da inicio a flujo: "+ Flujo + " / Tipo de Flujo: "+TipoFlujo);
		
		Ecom.IniciarEcomm(Flujo, Data);	
		OperForm.ingresarRut(Data);
		OperForm.ingresarNroTelefono(Data);
		OperForm.ingresarEmail(Data);
		OperForm.capturarPantalla("1 Formulario inicial", Data);	
		OperForm.marcarCheckboxTerminos(Data);
		OperForm.marcarCheckboxConsentimiento(Data);
		OperForm.capturarPantalla("2 Formulario inicial completado", Data);	
		OperForm.presionarBtnLineaNueva(Data);		
//		OperForm.seleccionarPlanCatalogo(Data);		
		OperForm.ingresarNumSerie(Data);
		OperForm.capturarPantalla("3 Nro de serie ingresado", Data);	
//		OperForm.presionarBtnSiguienteNroDeSerie(Data);			
//		OperForm.pruebaValidacionRadioButton(Data);		
		OperForm.ingresarNombre(Data);
		OperForm.escribirSesionPostpago(Data);
		OperForm.ingresarApellido(Data);
		OperForm.ingresarFechaNacimiento(Data);
		OperForm.ingresarRegion(Data);
		OperForm.ingresarComuna(Data);
		OperForm.ingresarCalleNumero(Data);
		OperForm.capturarPantalla("4 Datos ingresados", Data);	
		OperForm.presionarBtnSiguienteValidacionIdentidad(Data);
		
		OperForm.seleccionaDiaBloqueHora(Data);
//		OperForm.seleccionarTipoEnvio(Data);
		OperForm.capturarPantalla("5 Bloque de fecha y hora seleccionado", Data);	
		OperForm.presionarBtnSiguienteTipoEnvio(Data);
		OperForm.capturarPantalla("6 Dirección de entrega", Data);	
//		OperForm.presionarBtnSiguienteDireccionEntrega(Data);
		OperForm.escribirNroOrden(Data);
//		OperForm.escribirFechaTransaccion(Data);
//		OperForm.escribirTipoEnvio(Data);
		OperForm.capturarPantalla("7 Confirmación de compra", Data);
//		init.FinalizarSelenium();
	}
}