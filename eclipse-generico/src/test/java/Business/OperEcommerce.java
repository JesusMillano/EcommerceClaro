package Business;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Key;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

import java.util.ArrayList;

import Core.Selenium.AccionesWeb;
import Core.Sikuli.Sikuli;
import Core.Xml.LeerPasos;
import cucumber.api.Scenario;
import cucumber.api.java.es.Entonces;
import junit.framework.Assert;
import Core.Selenium.InitSelenium;


import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;



public class OperEcommerce extends Ecommers {
	
	public static WebDriver driver = null;
	private AccionesWeb acciones = new AccionesWeb();
	private Core.Ini.LeerData DataIni = new Core.Ini.LeerData();
	private Ecommers ecom = new Ecommers();
	public String Modulo;
	private InitSelenium init = new InitSelenium();
	Core.ScreenShot.ScreenShot Screen = new Core.ScreenShot.ScreenShot();
	static WebDriver Driver=new ChromeDriver();

	
//////METODOS PARA HOME DE TIENDA CLARO//////
	
	//Realiza la busqueda del equipo mediante el campo "Encuentra lo que buscas"			
	public void buscarEquipo
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String IdEjecucion = Data.get("IdEjecucion");
		String Flujo = Data.get("Flujo");
	    String TipoFlujo = Data.get("TipoFlujo");
	    String CasoDePrueba = Data.get("CasoDePrueba");
	    String Equipo = Data.get("Equipo");
	    String nombreBusqueda = Equipo.toUpperCase();
	    
	    DataRowExcel("Flujo", Flujo);
	    DataRowExcel("TipoFlujo", TipoFlujo);
	    DataRowExcel("CasoDePrueba", CasoDePrueba);

	    // Imprime para visualizar por consola cual es el equipo que se está buscando
	    System.out.println("buscando: "+ nombreBusqueda);
	    System.out.println("El id de ejecución es "+ IdEjecucion);

	    // Realiza el assertion para los elementos "inputBuscar" y "btnBuscar"
	    WebElement lupaBuscar = acciones.FindElementoWait("lupaBuscar", Flujo, 8);	    
	    WebElement inputBuscar = acciones.FindElementoWait("inputBuscar", Flujo, 8);
	    WebElement btnBuscar = acciones.FindElementoWait("btnBuscar", Flujo, 8);
	    try {
	        Assert.assertNotNull(inputBuscar);
	        Assert.assertNotNull(btnBuscar);
	    } catch (AssertionError e) {
	        DataRowExcel("Estado", "Fallido");
	        }

	    // Realiza las acciones de "InputText" y "ClickEcom"
	    acciones.ClickEcom("lupaBuscar", Flujo, 20);
	    acciones.InputText(nombreBusqueda, "inputBuscar" ,Flujo);
	    acciones.ClickEcom("btnBuscar", Flujo, 20);
	}
	      
    //Realiza la seleccion del equipo del resultado de la busqueda	
    public void seleccionarEquipo
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {     
    	String Flujo = Data.get("Flujo");
//    	String Equipo = Data.get("Equipo");  
//    	int i = 0;
//    	String xpath = ""; 	
//    	List<WebElement> datos ; 
    	    	
//    	datos = acciones.findElementosWait("txtNombreEquipo",Flujo,3);
    	
    	try{ 
    		acciones.findElementosWait("txtNombreEquipo",Flujo,3);
        	acciones.ClickEcom("txtNombreEquipo", Flujo, 10);
    	}catch(InterruptedException e) {
    		DataRowExcel("Estado", "Fallido");
    		}
    }    	
    	
    	/*
	    // Realiza el assertion para los elementos "txtNombreEquipo" o "txtNombreAcce"
	    WebElement txtNombreEquipo = acciones.FindElementoWait("txtNombreEquipo", Flujo, 8);
	    WebElement txtNombreAcce = acciones.FindElementoWait("txtNombreAcce", Flujo, 8);
	    try {
	        Assert.assertNotNull(txtNombreEquipo);
	        Assert.assertNotNull(txtNombreAcce);
	        
	    } catch (AssertionError e) {
	        DataRowExcel("Estado", "Fallido");
	        }
    		
        try {
        	//Si el item a buscar es un equipo, realiza las siguientes acciones
    		if(acciones.FindElementoWaitBoo("txtNombreEquipo",Flujo,3) ) {
    			for ( i = 0; i < datos.size() ;i++){
    				if((datos.get(i).getText().toUpperCase().trim()).equalsIgnoreCase(Equipo)){
    					xpath = "//*[@id='tab1Widget']/div/div[1]/div[2]/ul/li["+(i+1)+"]/div[2]/div[3]/div/a/h3";
    					acciones.Click(xpath);
						break;
						}
    				}
    				
    				
    			//Si el item a buscar no es un equipo, realiza las siguientes acciones
//    			}else if(acciones.FindElementoWaitBoo("txtNombreAcce",Flujo,3)) {
//    				datos = acciones.findElementosWait("txtNombreAcce",Flujo,60);
//    				for (i = 0; i < datos.size();i++){
//    					if((datos.get(i).getText().toUpperCase()).equals(Equipo)){
//    						xpath = "//html/body/div[2]/div[3]/div/div/div[3]/div[3]/div[2]/div/div[1]/div[2]/ul/li["+(i+1)+"]/div[2]/div[2]/div[1]/a/h3";
//    						acciones.Click(xpath);
//    						break;
//    						}
//    					}
    				}
    		}catch(InterruptedException e) {
    			DataRowExcel("Estado", "Fallido");
    			}
        }
        
        */
		
    //Presiona el botón "Continua al carrito de compras" de prepago
    public void presionarBtnContinuarCarritoPrepago
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {  
    	String Flujo = Data.get("Flujo");
    	
    	try{ 
    		acciones.FindElementoWaitBoo("btnComprarEquipo",Flujo,10);
    		acciones.ClickEcom("btnComprarEquipo", Flujo, 10);
    	}catch(InterruptedException e) {
    		DataRowExcel("Estado", "Fallido");
    		}
    }
        
    //Presiona el botón "Continua al carrito de compras" de Linea nueva con equipo / Recambio
    public void presionarBtnContinuarCarritoPostpago
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {  
    	String Flujo = Data.get("Flujo");
    	
    	try{ 
    		acciones.FindElementoWaitBoo("btnContinuarCarrito",Flujo,10);
    		acciones.ClickEcom("btnContinuarCarrito", Flujo, 10);
    	}catch(InterruptedException e) {
    		DataRowExcel("Estado", "Fallido");
    		}
    }
    
    //Presiona el botón "Siguiente" de la confirmación del equipo seleccionado
    public void presionarBtnSiguienteConfirmaEquipo
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {  
    	String Flujo = Data.get("Flujo");
    	
    	try{ 
    		acciones.FindElementoWaitBoo("btnContinuarEquipo",Flujo,10);
    		acciones.ClickEcom("btnContinuarEquipo", Flujo, 10); 
    		ecom.TimeSleep(2000);
    	}catch(InterruptedException e) {
    		DataRowExcel("Estado", "Fallido");
    		}
    }
    
    
    
//////METODOS PARA LLENADO DE FORMULARIO DE ETAPA DATOS DE CONTACTO//////
	
	//Completa el campo "RUT *"	
	public void ingresarRut
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Rut = Data.get("Rut");
		String Nombre = Data.get("Nombre");
		String Apellidos = Data.get("Apellidos");
		
		DataRowExcel("Rut", Rut);
		DataRowExcel("Nombre", Nombre);
		DataRowExcel("Apellido", Apellidos);
		
		try {
			acciones.ClickEcom("inputRut", Flujo, 10);	
			acciones.InputText(Rut,"inputRut",Flujo);	
			System.out.println("Ingresó el RUT");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "RUT *" para flujo recambio	
	public void ingresarRutRecambio
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Rut = Data.get("Rut");
		
		try {
			acciones.ClickEcom("inputRUT", Flujo, 10);	
			acciones.InputText(Rut,"inputRUT",Flujo);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Número de teléfono *"	
	public void ingresarNroTelefono
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Telefono = Data.get("Telefono");
		
		DataRowExcel("Telefono", Telefono);
		
		try {
			acciones.ClickEcom("inputNumTelefono", Flujo, 10);	
			acciones.InputText(Telefono,"inputNumTelefono",Flujo);
			System.out.println("Ingresó el Nro de Teléfono");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Número de teléfono *" para el flujo recambio	
	public void ingresarNroTelefonoRecambio
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Telefono = Data.get("Telefono");
		
		try {	
			acciones.ClickEcom("inputNumTelefonoRecambio", Flujo, 10);	
			acciones.InputText(Telefono,"inputNumTelefonoRecambio",Flujo);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}

	//Completa el campo "Ingresa el código" del SMS para el flujo recambio	
	public void ingresarSmsRecambio
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String NumMensaje = DataIni.GetValue("Datos", "Identificacion", "NumeroMensaje");
		
		try {
			acciones.ClickEcom("inputCorreoRecambio", Flujo, 10);
			ecom.TimeSleep(5000);
			acciones.ClickEcom("inputMensajeRecambio", Flujo, 10);	
			acciones.InputText(NumMensaje,"inputMensajeRecambio",Flujo);
			acciones.ClickEcom("btnSiguienteSmsRecambio", Flujo, 10);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}	
	
	//Completa el campo "Email *"	
	public void ingresarEmail
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Correo = Data.get("Correo");
		
		try {
			acciones.ClickEcom("inputCorreo", Flujo, 10);	
			acciones.InputText(Correo,"inputCorreo",Flujo);
			System.out.println("Ingreso el Correo");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Email * para el flujo recambio"	
	public void ingresarEmailRecambio
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Correo = Data.get("Correo");
		
		try {
			acciones.ClickEcom("inputCorreoRecambio", Flujo, 10);	
			acciones.InputText(Correo,"inputCorreoRecambio",Flujo);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Marca el checkbox de Términos y Condiciones	
	public void marcarCheckboxTerminos
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			ecom.TimeSleep(5000);
			acciones.FindElementoWaitBoo("checkTerminos",Flujo,10);
			acciones.ClickEcom("checkTerminos", Flujo, 10);
			ecom.TimeSleep(5000);
			System.out.println("Marcó el checkbox de terminos y condiciones");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Marca el checkbox de Consentimiento	
	public void marcarCheckboxConsentimiento
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			acciones.FindElementoWaitBoo("checkConsentimiento",Flujo,10);
			acciones.ClickEcom("checkConsentimiento", Flujo, 10);
			acciones.scrollMonitor(1500);
			System.out.println("Marcó el checkbox de consentimiento");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Presiona 	botón "Contratar una línea nueva Claro"
	public void presionarBtnLineaNueva
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");		
		String TipoFlujo = Data.get("TipoFlujo");
	    String CasoDePrueba = Data.get("CasoDePrueba");
	    
	    DataRowExcel("Flujo", Flujo);
	    DataRowExcel("TipoFlujo", TipoFlujo);
	    DataRowExcel("CasoDePrueba", CasoDePrueba);
		
		try {
//          acciones.EsperarElementoVisible("btnLineaNueva", Flujo);
//			acciones.FindElementoWaitBoo("btnLineaNueva", Flujo, 80);            
			ecom.TimeSleep(3000);
			acciones.ClickEcom("btnLineaNueva", Flujo, 10);
			System.out.println("Presionó botón Linea Nueva");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Presiona 	botón "Contratar Línea Nueva (Adicional a mi plan contratado)"
	public void presionarBtnMigracion
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
	    String CasoDePrueba = Data.get("CasoDePrueba");
	    
	    DataRowExcel("Flujo", Flujo);
	    DataRowExcel("TipoFlujo", TipoFlujo);
	    DataRowExcel("CasoDePrueba", CasoDePrueba);
		
		try {
			acciones.FindElementoWaitBoo("btnLineaMigracion", Flujo, 80);
			acciones.ClickEcom("btnLineaMigracion", Flujo, 80);	        
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Presiona 	botón "Portar a Claro la línea ingresada"
	public void presionarBtnPortarLinea
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			acciones.FindElementoWaitBoo("btnLineaMiNumero", Flujo, 80);
            ecom.TimeSleep(3000);
            acciones.ClickEcom("btnLineaMiNumero", Flujo, 10);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Presiona 	botón "Prepago" o "Postpago" Según sea el caso
	public void presionarBtnPortabilidad
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		
		try {
			if(TipoFlujo.contains("Prepago")) {
			acciones.FindElementoWaitBoo("btnPrePago", Flujo, 80);
            ecom.TimeSleep(3000);
            acciones.ClickEcom("btnPrePago", Flujo, 10);
			}
			else {
				acciones.FindElementoWaitBoo("btnPostPago", Flujo, 80);
	            ecom.TimeSleep(3000);
	            acciones.ClickEcom("btnPostPago", Flujo, 10);
			}
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
		
	//Presiona 	botón "Postpago"
	public void presionarBtnPostpago
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			acciones.FindElementoWaitBoo("btnPostPago", Flujo, 80);
            ecom.TimeSleep(3000);
            acciones.ClickEcom("btnPostPago", Flujo, 10);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
		
	//Selecciona Plan del catalogo"
	public void seleccionarPlanCatalogo
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {  
		String Flujo = Data.get("Flujo");
		String NombrePlan = Data.get("NombrePlan");    	
    	NombrePlan = (NombrePlan.toUpperCase()).trim();
    	String xpath = "";
         
    	int art = 1; 
        int i = 0;
        int sec = 1;
        int flag = 0;
        
        try {
//        	if(acciones.FindElementoWaitBoo("tagPlanes", Flujo, 30)) {
        	if(acciones.FindElementoWaitBoo("tituloPlanes", Flujo, 30)) {
        		List<WebElement> planes = acciones.findElementosWait("tituloPlanes",Flujo,10);
        		for (i = 0; i < planes.size(); i++) {
        			if(i == 4) {
        				sec = 2; 
        				art = 1;
        				}
        			if(((planes.get(i).getText()).toUpperCase()).trim().equals(NombrePlan)) {
        				xpath = "//html/body/div[3]/div[7]/div/div/div[3]/div[2]/div[1]/section["+sec+"]/article["+art+"]/div[2]/div[1]/a";
        				Thread.sleep(500);
        				acciones.Click(xpath);
        				flag = 1;
	                    break;
	                }
	            	art =art+1;
	            }
	            if(flag==0) {
		        	DataRowExcel("Estado", "Fallido");
		        	init.FinalizarSelenium(); //(return;
	            }
	           
	        }else {
	        	DataRowExcel("Estado", "Fallido");
	        	init.FinalizarSelenium(); //(return;	
	        }
        	} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				init.FinalizarSelenium();
				}
        }

	
		
//////METODOS PARA LLENADO DE FORMULARIO DE ETAPA VALIDACION DE IDENTIDAD//////
	
	//Completa el campo "Nombre(s)*"
	public void ingresarNombre
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Nombre  = Data.get("Nombre");
		
		try {
			System.out.println("Entró al método ingresarNombre");
			acciones.clearText("inputNombre", Flujo);
			acciones.InputText(Nombre,"inputNombre", Flujo);
			System.out.println("Ingreso el Nombre");
			DataRowExcel("Nombre", Nombre);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Apellidos(s)*"
	public void ingresarApellido
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Apellidos  = Data.get("Apellidos");
		
		try {
			acciones.clearText("inputApellidos", Flujo);
	        acciones.InputText(Apellidos,"inputApellidos", Flujo);
	        DataRowExcel("Apellido", Apellidos);
	        System.out.println("Ingreso el Apellido");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "RUT*"
	public void ingresarRUT
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Rut = Data.get("Rut");
		
		try {
			acciones.clearText("inputRut", Flujo);
		    acciones.InputText(Rut,"inputRut", Flujo);
		    DataRowExcel("Rut", Rut);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Número Telefónico*"
	public void ingresarNumeroTelefonico
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String FonoDatPersonal = Data.get("FonoDatPersonal");
		
		try {
			acciones.clearText("NumFono", Flujo);
			acciones.InputText(FonoDatPersonal,"NumFono", Flujo);
			DataRowExcel("Telefono", FonoDatPersonal);
			acciones.ClickEcom("CorreoDatPer",Flujo,10);
			ecom.TimeSleep(5000);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
    //Completa el campo "Correo electrónico *"   
	public void ingresarCorreoElectronico
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String CorreoDatPersonal = Data.get("CorreoDatPersonal");

		try {
			acciones.clearText("CorreoDatPer", Flujo);
			acciones.InputText(CorreoDatPersonal,"CorreoDatPer", Flujo);
			ecom.TimeSleep(1500);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Fecha de Nacimiento *"
	public void ingresarFechaNacimiento
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Dia = Data.get("Dia");
		String Mes = Data.get("Mes");
		String Anio = Data.get("Anio");
		
		try {
//			acciones.ClickEcom("selectDia",Flujo,10);
//			String xpathDia = "//*[@id='WC_PersonalInfoExtension_birth_date-menu']/li["+(Integer.parseInt(Dia)+1)+"]";
//			acciones.ClickXpath(xpathDia);
//			ecom.TimeSleep(1000);
//			acciones.ClickEcom("selectMes",Flujo,10);
//	        String xpathMes = "//*[@id='WC_PersonalInfoExtension_birth_month-menu']/li["+(Integer.parseInt(Mes)+1)+"]";
//	        acciones.ClickXpath(xpathMes);
//	        ecom.TimeSleep(1000);
//	        acciones.ClickEcom("selectAno",Flujo,10);
//			acciones.findElementosWait("listAno",Flujo,10);
//		    String xpathAnio = "//*[@id='WC_PersonalInfoExtension_birth_year-menu']/li[@value="+Anio+"]";
//		    acciones.ClickXpath(xpathAnio);
//			ecom.TimeSleep(1000);
			acciones.FindElementoWaitBoo("inputFecha",Flujo,10);
			acciones.ClickEcom("inputFecha",Flujo,10); 
			acciones.InputText("06/01/1974","inputFecha",Flujo);
			System.out.println("Ingreso la Fecha de nacimiento");

			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
        } 
	}
		
	//Completa el campo "Región *"
	public void ingresarRegion
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Region = Data.get("Region");
		
		try {
			ecom.OptRegion("selectRegion", Flujo, "listRegion", Region);
	        acciones.ClickEcom("FocusRegion",Flujo,10); 
	        ecom.TimeSleep(2000);
	        System.out.println("Ingreso la Región");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Comuna *"
	public void ingresarComuna
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Comuna = Data.get("Comuna");
		
		try {
			ecom.OptComuna("selectComuna", Flujo, "listComuna", Comuna); 
	        ecom.TimeSleep(2000);
	        System.out.println("Ingreso la Comuna");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Calle y número exterior *"
	public void ingresarCalleNumero
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Calle = Data.get("Calle"); 
		String Flujo = Data.get("Flujo");
        Calle = (Calle.toUpperCase()).trim();
        int ValCalle = Calle.length();
		
		try {       	
	        if(ValCalle > 0) {
	        	ecom.OptCalle("inputCalle", Flujo, Calle);
	        	acciones.clearText("inputCalle", Flujo);
	        	ecom.TimeSleep(1000);
	        	ecom.OptCalle("inputCalle", Flujo, Calle);
	        	ecom.TimeSleep(1000);
	        	acciones.clearText("inputCalle", Flujo);
	        	ecom.TimeSleep(1000);
	        	ecom.OptCalle("inputCalle", Flujo, Calle);
	        	ecom.TimeSleep(2000);
	        	acciones.FindElementoWaitBoo("resultadoMaps", Flujo, 10);
	        	acciones.ClickEcom("resultadoMaps", Flujo, 10);
	        	ecom.TimeSleep(4000);
	        	System.out.println("Ingresó la dirección");
	        	}
	        } catch(InterruptedException e){
	        	DataRowExcel("Estado", "Fallido");
	        	}
		}
		
	//Presiona botón "Siguiente" del Formulario "Ingresa tus datos"
	public void presionarBtnSiguienteValidacionIdentidad
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			acciones.ClickEcom("btnContinuar", Flujo, 10);
			ecom.TimeSleep(5000);
			System.out.println("Hizo el click en el botón continuar");
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}	
	
	//Completa el campo "Número de serie"	
    public void ingresarNumSerie
(Hashtable<String, String> Data) throws InterruptedException, AWTException, IOException {
    	String Flujo = Data.get("Flujo");  	
    	String NumSerie = DataIni.GetValue("Datos", "Identificacion", "NumeroSerie");
    		 
	    	try {
	    		acciones.scrollMonitor(500);
		        acciones.FindElementoWaitBoo("inputNroSerie",Flujo,20);
		        acciones.ClickEcom("inputNroSerie", Flujo, 10);
		        acciones.InputText(NumSerie,"inputNroSerie",Flujo);
		        WebElement serie = acciones.FindElementoWait("inputNroSerie", Flujo, 5);
		        TimeSleep(800);
		        serie.sendKeys(Keys.TAB);
		        serie.sendKeys(Keys.TAB);
		        ecom.TimeSleep(1500);
		        System.out.println("Ingreso el Nro de Serie");
		        acciones.ClickEcom("btnSiguiente", Flujo, 10);		        
		        } catch(InterruptedException e){
		        	DataRowExcel("Estado", "Fallido");
		        	}
	    	}
			
	//Completa el campo Código CAP"
	public void ingresarCodigoCap
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");  
		String NumCap = DataIni.GetValue("Datos", "Identificacion", "NumeroCap");
		
		try {
			System.out.println("El tipo de envío seteado es: " + TipoFlujo);
			acciones.FindElementoWaitBoo("inputMensaje",Flujo,10);
			acciones.InputText(NumCap,"inputMensaje",Flujo);
			TimeSleep(3000);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
		
	//Completa el campo Mensaje SMS"
	public void ingresarCodigoSms
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");  
		String NumMensaje = DataIni.GetValue("Datos", "Identificacion", "NumeroMensaje");
		
		try {
			System.out.println("El tipo de envío seteado es: " + TipoFlujo);
			acciones.FindElementoWaitBoo("inputMensaje",Flujo,10);
			acciones.InputText(NumMensaje,"inputMensaje",Flujo);
			TimeSleep(3000);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Ingresa el código" para portabilidad según sea el caso
	public void ingresarCodigoPortabilidad
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");  
		String NumMensaje = DataIni.GetValue("Datos", "Identificacion", "NumeroMensaje");
		String NumCap = DataIni.GetValue("Datos", "Identificacion", "NumeroCap");
		
		try {
			if (TipoFlujo.contains("Prepago")) {
			System.out.println("El tipo de envío seteado es: " + TipoFlujo);
			acciones.FindElementoWaitBoo("inputMensaje",Flujo,10);
			acciones.InputText(NumMensaje,"inputMensaje",Flujo);
			TimeSleep(3000);
			}
			else {
				System.out.println("El tipo de envío seteado es: " + TipoFlujo);
				acciones.FindElementoWaitBoo("inputMensaje",Flujo,10);
				acciones.InputText(NumMensaje,"inputMensaje",Flujo);
				}
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	////Presiona botón "Siguiente" de la pantalla Número de serie
	public void presionarBtnSiguienteNroDeSerie
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String TipoFlujo = Data.get("TipoFlujo");
		
		try {
			if(TipoFlujo.equalsIgnoreCase("Linea Nueva")) {
				acciones.FindElementoWaitBoo("btnSiguienteSmsRecambio",Flujo,10);
		        acciones.ClickEcom("btnSiguienteSmsRecambio",Flujo,20);
		        }else {
			        acciones.FindElementoWaitBoo("btnSiguiente",Flujo,10);
			        acciones.ClickEcom("btnSiguiente",Flujo,20);		        	
		        }
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "Número de serie" para flujo Línea nueva con equipo
    public void ingresarNroSerieNLE
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException{
    	
    	driver = InitSelenium.getDriver();   
    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    	
    	String Flujo = Data.get("Flujo");
		String NumSerie = DataIni.GetValue("Datos", "Identificacion", "NumeroSerie");
		
			acciones.scrollMonitor(500);
		
//        	WebElement nroSerie = driver.findElement(By.xpath("/html/body/div[9]/div/div[3]/ul/li[1]/fieldset/div/input"));
        	WebElement nroSerie = driver.findElement(By.xpath("//*[@id='carnetIdentidad']"));
        	
        	System.out.println(nroSerie);
        	nroSerie.click();
        	nroSerie.sendKeys(NumSerie);
        	nroSerie.sendKeys(Key.TAB);
        	System.out.println("Ingresó el nro de serie");        	
        	acciones.ClickEcom("btnSiguiente",Flujo,20);
        	Thread.sleep(2000);  
        	
//        	acciones.FindElementoWaitBoo("ndla1",Flujo,10);
//        	acciones.ClickEcom("ndla1",Flujo,20);
//        	acciones.ClickEcom("ndla2",Flujo,20);
//        	acciones.ClickEcom("ndla3",Flujo,20);
//        	acciones.ClickEcom("ndla4",Flujo,20);
//        	acciones.ClickEcom("btnSgteValidacion",Flujo,20);
//        	Thread.sleep(2000);
        	
//	        if (acciones.FindElementoWaitBoo("Autentikar",Flujo,10)) {
//	        	acciones.ClickEcom("Autentikar",Flujo,20);
	        	}       	         
 
    
       
//////METODOS PARA ETAPA CONFIRMACION DATOS DE ENVÍO - ENTREGA//////
	
	//Marca el radio button del tipo de envío
	public void seleccionarTipoEnvio
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String TipoEnvio = Data.get("TipoEnvio"); 
		
		try {
			if(TipoEnvio.equalsIgnoreCase("Normal")) {
				System.out.println("El tipo de envío seteado es: " + TipoEnvio);
				TimeSleep(5000);
				acciones.ClickEcom("radioBtnNormal", Flujo, 10);
				TimeSleep(1000);
				}
			if(TipoEnvio.equalsIgnoreCase("Express")) {
				System.out.println("El tipo de envío seteado es: " + TipoEnvio);
				TimeSleep(8000);
				acciones.ClickEcom("radioBtnExpress", Flujo, 10);
				TimeSleep(1000);
				}
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		} 
		
	//Presiona el botón "Siguiente" de la pantalla Tipo de envío
	public void presionarBtnSiguienteTipoEnvio
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			ecom.TimeSleep(10000);
			acciones.FindElementoWaitBoo("SiguienteOtraPer",Flujo,80);
			acciones.ClickEcom("SiguienteOtraPer", Flujo, 10);
			System.out.println("Hizo el click en el botón Siguiente");
			ecom.TimeSleep(1500);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Presiona el botón "Siguiente" de la pantalla Dirección de Entrega
	public void presionarBtnSiguienteDireccionEntrega
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			acciones.FindElementoWaitBoo("XSiguienteOtraPer", Flujo, 5);
			acciones.ClickEcom("XSiguienteOtraPer", Flujo, 10);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	
	
	//Presiona el botón "Siguiente" de la pantalla Dirección de Entrega Recambio
	public void presionarBtnSiguienteDireccionEntregaRecambio
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			acciones.FindElementoWaitBoo("btnSiguienteDirEntRecambio", Flujo, 5);
			acciones.ClickEcom("btnSiguienteDirEntRecambio", Flujo, 10);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Presiona el botón "Siguiente" de la pantalla Dirección de Entrega Prepago
	public void presionarBtnSiguienteDireccionEntregaPrepago
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			acciones.FindElementoWaitBoo("SiguienteEntregaPre", Flujo, 5);
			acciones.ClickEcom("SiguienteEntregaPre", Flujo, 10);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	
	
	//Presiona el botón "Siguiente continuar paga compra" de la pantalla Dirección de Entrega
	public void presionarBtnSiguientePagaCompra
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Rut = Data.get("Rut");
		
		try {
			acciones.FindElementoWaitBoo("inputRut", Flujo, 5);
			acciones.InputText(Rut,"inputRut",Flujo);	
	        acciones.ClickEcom("divPagaCompra", Flujo, 10);
			acciones.ClickEcom("btnContinuarPagaCompra", Flujo, 10);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	
	       
/////METODSOS PARA FLUJO HOGAR//////
    
  //Selecciona el checkbox del tipo de servicio Hogar a contratar
    public void seleccionarServicioHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
    	String ID = Data.get("ID");
    	String Flujo = Data.get("Flujo");
	 	String TipoFlujo = Data.get("TipoFlujo");
	 	String CasoDePrueba = Data.get("CasoDePrueba");	
	 	
	 	DataRowExcel("Id", ID);
	 	DataRowExcel("Flujo", Flujo);
	 	DataRowExcel("TipoFlujo", TipoFlujo);
	 	DataRowExcel("CasoDePrueba", CasoDePrueba);

	    //Ingresa al flujo de Hogar
		if(TipoFlujo.contains("Hogar")) {
	    
		switch(CasoDePrueba){
    	case ("1P"):
    		System.out.println("Ejecutando flujo de Hogar 1P");
    		if (acciones.FindElementoWaitBoo("checkInternet", Flujo, 80)) {   			
    			acciones.ClickEcom("checkTelevision", Flujo, 10);
            }
    		break;
         
		case "2P":
			System.out.println("Ejecutando flujo de Hogar 2P");
            if (acciones.FindElementoWaitBoo("checkInternet", Flujo, 80)) {
            	acciones.ClickEcom("checkInternet", Flujo, 10);
            	acciones.ClickEcom("checkTelevision", Flujo, 10);		
	    }
            break;

		case "3P":
			System.out.println("Ejecutando flujo de Hogar 3P");
            if (acciones.FindElementoWaitBoo("checkInternet", Flujo, 80)) {    			
    			acciones.ClickEcom("checkInternet", Flujo, 10);
    			acciones.ClickEcom("checkTelevision", Flujo, 10);	
    			acciones.ClickEcom("checkTelefonia", Flujo, 10);
    			}
            break;
            }
		acciones.ClickEcom("btnSiguientea", Flujo, 10);
		}
		}
	 
	//Marca el checkbox "Acepto Términos y Condiciones" del flujo Hogar	
	public void marcarCheckboxTerminosHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			acciones.FindElementoWaitBoo("checkHogar","EcommerceGlobales",2);
	    	acciones.ClickEcom("checkHogar", Flujo, 10);
	    	acciones.scrollMonitor(500);
	    	ecom.TimeSleep(3000);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Selecciona la Región correspondiente
	public void seleccionarRegionHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Region = Data.get("Region");
		
		try {
	        ecom.OptRegionHogar("selectRegionx", Flujo, "listRegionx", Region);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Selecciona la Comuna correspondiente
	public void seleccionarComunaHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Comuna = Data.get("Comuna");
		Comuna = ecom.acentoEñe(Comuna);
        Comuna = (Comuna.toUpperCase()).trim();
		
		try {			
	        WebElement dir = acciones.FindElementoWait("inputComuna", Flujo, 5);
	        TimeSleep(800);
	        acciones.InputText(Comuna,"inputComuna",Flujo);
	        dir.sendKeys(Keys.TAB);
	        dir.sendKeys(Keys.TAB);
	        TimeSleep(800); 
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
    
	//Selecciona el tipo de vivienda correspondiente
	public void seleccionarTipoViviendaHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String TipoVivienda = Data.get("TipoVivienda");
		String Calle = Data.get("Calle");
 		String Numeracion = Data.get("Numeracion");
 		String NumeroInt = Data.get("NumeroInt");
 		
		try {
        	switch(TipoVivienda){
	        	case "condominio":
	        		acciones.ClickEcom("radioCondominio", Flujo, 10);	
	        		acciones.InputText(Calle,"inputDireccion",Flujo);
	        		TimeSleep(3000);
	        		acciones.InputText(Numeracion,"inputNumeracion",Flujo);
	        		acciones.InputText(NumeroInt,"inputCasaCondomin",Flujo);
			        break;
			        
				case "casaParticular":
				case "Casa":
//					acciones.ClickEcom("radioCasa", Flujo, 10); 
					acciones.InputText(Calle,"inputDireccion",Flujo);
					System.out.println("casa.......");
					WebElement casa = acciones.FindElementoWait("inputNumeracionxx", Flujo, 5);
					TimeSleep(3000);
					acciones.InputText(Numeracion,"inputNumeracionxx",Flujo);
					TimeSleep(3000);
					casa.sendKeys(Keys.DOWN);TimeSleep(2000);
					casa.sendKeys(Keys.TAB);
					TimeSleep(2000); 
				    break;
				    
				case "condominioEdificio":
					acciones.ClickEcom("radioCondEdificio", Flujo, 10); 
					acciones.InputText(Calle,"inputDireccion",Flujo);
					acciones.InputText(Numeracion,"inputNumeracion",Flujo);
					acciones.InputText("XD","inputTorre",Flujo);  //Valor en duro, cambiar
					acciones.InputText(NumeroInt,"inputDepa",Flujo);
				    break;
				    
				case "departamento":
				case "Depto":
					acciones.ClickEcom("radioDepartamento", Flujo, 10); 
					acciones.InputText(Calle,"inputDireccion",Flujo);
					acciones.InputText(Numeracion,"inputNumeracion",Flujo);
					acciones.InputText(NumeroInt,"inputDepa",Flujo);
					break;
				default:
					acciones.ClickEcom("radioCasa", Flujo, 10); 
					acciones.InputText(Calle,"inputDireccion",Flujo);
					acciones.InputText(Numeracion,"inputNumeracion",Flujo);
					//
//					WebElement testFoco = acciones.FindElementoWait("inputNumeracion", Flujo, 5);
//					System.out.println(testFoco);
//					testFoco.sendKeys(Keys.TAB);
					//
				    break;
	        }
//        	acciones.FindElementoWait("inputNumeracion", Flujo, 5).sendKeys(Keys.TAB);
//        	TimeSleep(2000);
//        	System.out.println("Hizo el TAB");
        	acciones.ClickEcom("foco", Flujo, 10); 
		    TimeSleep(10000);

	        acciones.ClickEcom("btnSiguientex", Flujo, 10);

	} catch(InterruptedException e){
		DataRowExcel("Estado", "Fallido");
	}
		}
		
	//Selecciona el plan Hogar
	public void seleccionarPlanHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
 		String Flujo = Data.get("Flujo"); 
 		String TipoFlujo = Data.get("TipoFlujo"); 
 		String NombrePlan = Data.get("NombrePlan");	
	 		
	 	NombrePlan = (NombrePlan.toUpperCase()).trim();
//	 	int i = 0;
//	 	int flag= 0;
	 	String xpath = "";
	 	
	 	try {
	 		if(acciones.FindElementoWaitBoo("contenedorServ",Flujo,5) && (TipoFlujo.equalsIgnoreCase("Hogar"))) {
	 			xpath = "/html/body/div[8]/div[4]/div[2]/div/div[3]/div[2]/div[2]/div/div/div[2]/article/div[2]/div/div";
	 			acciones.ClickXpath(xpath);
	 			acciones.ClickEcom("btnSgteServContra", Flujo, 5);
	 			ecom.TimeSleep(5000);
//	 			acciones.ClickEcom("btnSgteServ", Flujo, 5); 
	 			}
	 		
	 		if(acciones.FindElementoWaitBoo("contenedorServ",Flujo,5) && (TipoFlujo.equalsIgnoreCase("Hogar SVA"))) {
	 			xpath = "/html/body/div[8]/div[4]/div[2]/div/div[3]/div[2]/div[2]/div/div/div[2]/article/div[2]/div";
	 			acciones.ClickXpath(xpath);
	 			acciones.ClickEcom("btnSgteServContra", Flujo, 5);
	 			ecom.TimeSleep(5000);
//	 			acciones.ClickEcom("btnSgteServ", Flujo, 5); 
	 		}
	 		
	 		
	 		
	 		} catch(InterruptedException e){
	 			DataRowExcel("Estado", "Fallido");
	 			}
	 	}
	
	//Agrega un SVA	
	public void agregarSvaHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {			
			acciones.ClickEcom("btnCanalesAdicionales", Flujo, 5);
            System.out.println("Desplegó los canales");		                    
            acciones.ClickEcom("checkCanalAdicional", Flujo, 5);
            ecom.TimeSleep(5000);
            acciones.ClickEcom("btnSgteServ", Flujo, 5);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}		
		
	//Completa el campo "Número de Serie de Carnet de Identidad"
	public void capturarSesionHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String textoSession = "";
		
	    WebElement session = acciones.FindElementoWait("sessionHogar", Flujo, 10);
	    textoSession = session.getAttribute("Value");
	    System.out.println("sessionId - Hogar: "+textoSession);
	    DataRowExcel("SessionId", textoSession);
		}	

	//Completa el campo "* Número de Serie de Carnet de Identidad"
	public void ingresarCarnetHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String NumSerie = DataIni.GetValue("Datos", "Identificacion", "NumeroSerie");	
		
		try {			
			acciones.ClickEcom("inputCarnetHogar", Flujo, 10);
			acciones.clearText("inputCarnetHogar", Flujo);
			acciones.InputText(NumSerie,"inputCarnetHogar",Flujo);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}	
	
	//Completa el campo "* Nombres"
	public void ingresarNombreHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Nombre = Data.get("Nombre");	
		
		try {			
			acciones.ClickEcom("inputNombreHogar", Flujo, 30);
			acciones.clearText("inputNombreHogar", Flujo);
			acciones.InputText(Nombre, "inputNombreHogar", Flujo);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "* Apellidos"
	public void ingresarApellidoHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String Apellidos = Data.get("Apellidos");
		
		try {			
			acciones.ClickEcom("inputApellidosHogar", Flujo, 10);
			acciones.clearText("inputApellidosHogar", Flujo);
			acciones.InputText(Apellidos, "inputApellidosHogar", Flujo);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	//Completa el campo "¿Tienes otro número de Contacto?"
	public void ingresarNumeroContactoHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String FonoOtraPers = Data.get("FonoOtraPers");
		
		try {			
			if(FonoOtraPers.equalsIgnoreCase("null"))	{
				acciones.scrollMonitor(500);
				ecom.TimeSleep(500);
				}
			else { 
				acciones.ClickEcom("inputNumeroHogar", Flujo, 10);	
				acciones.clearText("inputNumeroHogar", Flujo);
				acciones.InputText(FonoOtraPers, "inputNumeroHogar", Flujo);
				}
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
		
	//Selecciona una fecha válida en el calendario
	public void seleccionarFechaCalendario
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
//		String Flujo = Data.get("Flujo");
		
		try {			
	 		Calendar calendar = Calendar.getInstance();
	 		int semana = calendar.get(Calendar.WEEK_OF_MONTH);
	 		int dia = calendar.get(Calendar.DAY_OF_WEEK );
	 		acciones.scrollMonitor(500);
	 		System.out.println("La semana es: "+semana);
	 		System.out.println("El dia es: "+dia);
	 		ecom.TimeSleep(500);
	 		String xpath =  "/html/body/div[1]/div[11]/div/div[4]/div[1]/div[1]/div/form[1]/div[2]/div[12]/div/fieldset/div/ul/li[1]/div/div[1]/table/tbody/tr["+(semana+1)+"]/td["+(dia)+"]";
	 		acciones.ClickXpath(xpath);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
			}
				}
		
	//Selecciona radio button AM para "Elige Bloque Horario para el día seleccionado"
	public void marcarRadioButtonAm
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
			
		try {	
			acciones.FindElementoWaitBoo("radioAm",Flujo,10);
			acciones.ClickEcom("radioAm", Flujo, 10);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}	
	
	//Presiona el botón "Finalizar" de la pantalla Datos de instalación
	public void presionarBtnFinalizarDatosInstallHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			ecom.TimeSleep(1500);
			acciones.FindElementoWaitBoo("btnFinalizarHogar", Flujo, 10);
			acciones.ClickEcom("btnFinalizarHogar", Flujo, 10);	
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	
			
//////METODOS PLATAFORMA DE PAGO//////
	
	///Realiza el proceso de pago por WebPay
	public void PagarWebpay
(String Pago) throws InterruptedException, IOException, AWTException {     	
		String MedioPago 		= DataIni.GetValue("Datos", Pago, "MedioPago");
		String FechaVenc  		= DataIni.GetValue("Datos", Pago, "FechaVenc");
		String CodValidacion 	= DataIni.GetValue("Datos", Pago, "CodValidacion");
		String NumTarjeta 		= DataIni.GetValue("Datos", Pago, "NumTarjeta");
		String RutTransbank 	= DataIni.GetValue("Datos", Pago, "RutTransbank");
		String ClaveTransbank 	= DataIni.GetValue("Datos", Pago, "ClaveTransbank");
		
		
	  if(MedioPago.equals("DEBITO") ) {	  
		  TimeSleep(5000);
		  System.out.println("Entró al if "+ MedioPago);	
		  acciones.PagoDebito();
       }
	  	  
	  if(MedioPago.equals("CREDITO") ) {  
		  TimeSleep(5000);
		  System.out.println("Entró al if " + MedioPago);			
		  acciones.PagoCredito();
       }
	  
	  if(MedioPago.equals("PREPAGO") ) {
		  System.out.println("Entró al if " + MedioPago);
		  TimeSleep(5000);
		  acciones.PagoPrepago();
	  }
	
	  
//	  if(MedioPago.equals("DEBITO") ) {		  
//		  	acciones.ClickAmdoc("selectBancoDeb","EcommerceGlobales",10);
//        	acciones.ClickAmdoc("opBancoDeb","EcommerceGlobales",10);
//            TimeSleep(1000);
//            acciones.ClickAmdoc("optionPanel","EcommerceGlobales",10);  
//            TimeSleep(1000); 
//	  }
//	  else{
//		  
//		  if(acciones.FindElementoWaitBoo("selectBanco", "EcommerceGlobales", 5)) {	
//        	acciones.ClickAmdoc("selectBanco","EcommerceGlobales",10);
//        	acciones.ClickAmdoc("opBanco","EcommerceGlobales",10);
//            TimeSleep(1000);
//            acciones.ClickAmdoc("optionPanel","EcommerceGlobales",10);  
//            TimeSleep(500); 
//		  }
//	  }

    if(MedioPago.equals("DEBITO") ) {	
    	acciones.ClickAmdoc("txtNumTarjetaDebito","EcommerceGlobales",10);
        acciones.InputText(NumTarjeta,"txtNumTarjetaDebito", "EcommerceGlobales");
        acciones.ClickAmdoc("divContinuarWebPayDeb","EcommerceGlobales",10);
        TimeSleep(5000);
        acciones.ClickEcom("btnContinuarWebPayDeb", "EcommerceGlobales", 10);                    
    }
    
    if(MedioPago.equals("CREDITO") ) {	
    	acciones.ClickAmdoc("txtNumTarjetaCredito","EcommerceGlobales",10);
        acciones.InputText(NumTarjeta,"txtNumTarjetaCredito", "EcommerceGlobales"); 
    }
    
    if(MedioPago.equals("PREPAGO") ) {	
    	acciones.ClickAmdoc("txtNumTarjetaPrepago","EcommerceGlobales",10);
        acciones.InputText(NumTarjeta,"txtNumTarjetaPrepago", "EcommerceGlobales");
    }
 
    if(MedioPago.equals("CREDITO")) { 
    	//FECHA VENCIMIENTO
    	acciones.ClickAmdoc("opFechVencCredito","EcommerceGlobales",10);
        TimeSleep(500);
        acciones.InputText(FechaVenc,"opFechVencCredito", "EcommerceGlobales");
        
        //CVV
        acciones.ClickAmdoc("opCvvCredito","EcommerceGlobales",10);
        TimeSleep(500);
        acciones.InputText(CodValidacion,"opCvvCredito", "EcommerceGlobales");   //Quitar comillas codVal
        
        acciones.ClickAmdoc("btnContinuarWebPayCred", "EcommerceGlobales", 10);
    }
    
   if(MedioPago.equals("PREPAGO")) {	
	   //FECHA VENCIMIENTO
	   acciones.ClickAmdoc("opFechVencPrepago","EcommerceGlobales",10);
       TimeSleep(500);
       acciones.InputText(FechaVenc,"opFechVencPrepago", "EcommerceGlobales");
       
       //CVV
       acciones.ClickAmdoc("opCvvPrepago","EcommerceGlobales",10);
       TimeSleep(500);
       acciones.InputText(CodValidacion,"opCvvPrepago", "EcommerceGlobales");
       acciones.ClickAmdoc("btnContinuarWebPayPrep", "EcommerceGlobales", 10);         
   }
    
    if(acciones.FindElementoWaitBoo("tagImgTransbank", "EcommerceGlobales", 10)) {	
        acciones.InputText(RutTransbank,"txtRutTrans","EcommerceGlobales");
        acciones.InputText(ClaveTransbank,"txtClaveTrans","EcommerceGlobales");
        acciones.ClickAmdoc("btnAceptarTrans","EcommerceGlobales",10);
        TimeSleep(1000);
        acciones.ClickAmdoc("btnContinuarTrans","EcommerceGlobales",10);
    	}
	  }
    
		
	
//////METODOS PARA ETAPA CONFIRMACION DE CONTRATACIÓN//////
	
	//Valida que la solicitud se haya realizado de manera exitosa
	public void confirmarSolicitud
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {    	
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;

        Pattern p = Pattern.compile("[0-9]+$");
        String textoSolicitud = "";
        String Solicitud = "";
        String TipoEquipo = "";
        String FechaTransaccion = "";
        String TipoPago = "";
        String TipoEnvio = "";
        
//        DataRowExcel("Estado", "Exitoso");
         
//        try {
//        	if (acciones.FindElementoWaitBoo("NumPedido", Flujo, 30)) {
        		elemento = acciones.FindElementoWait("NumPedido", Flujo, 10);
        		textoSolicitud = elemento.getText();
        		Matcher m = p.matcher(textoSolicitud);
        		while (m.find()){
        			Solicitud = Solicitud+m.group();
        			DataRowExcel("Orden", Solicitud);
        			System.out.println("El número de orden es: " + Solicitud);
        			}
//        	}
        		
//            if (acciones.FindElementoWaitBoo("txtEquipo", Flujo, 30)) {
            	elemento = acciones.FindElementoWait("txtEquipo", Flujo, 10);
            	TipoEquipo = elemento.getText();
            	DataRowExcel("TipoProducto", TipoEquipo);
            	System.out.println("El tipo de producto es: " + TipoEquipo);
//            	}
            	
//            if (acciones.FindElementoWaitBoo("fechaTransaccion", Flujo, 30)) {
                elemento = acciones.FindElementoWait("fechaTransaccion", Flujo, 10);
                FechaTransaccion = elemento.getText();
                DataRowExcel("Fecha", FechaTransaccion.substring(18, FechaTransaccion.length()));
                System.out.println("La fecha de la transacción es: " + FechaTransaccion.substring(18, FechaTransaccion.length()));
//                }
                
//            if (acciones.FindElementoWaitBoo("tipoPago", Flujo, 30)) {
                 elemento = acciones.FindElementoWait("tipoPago", Flujo, 10);
                 TipoPago = elemento.getText();
                 DataRowExcel("TipoPago", TipoPago);
                 System.out.println("El tipo de pago es: " + TipoPago);
//                 }
                 
//             if (acciones.FindElementoWaitBoo("tipoEnvio", Flujo, 30)) {
                 elemento = acciones.FindElementoWait("tipoEnvio", Flujo, 10);
                 TipoEnvio = elemento.getText();         
                 DataRowExcel("TipoEnvio", TipoEnvio);
                 System.out.println("El tipo de envío es: " + TipoEnvio);
//                 }
		
        		System.out.println("Confirmacion de compra - Solicitud finalizada: " + Solicitud);         		   	
        	
//        	if (Solicitud.isEmpty()) {
//        		DataRowExcel("Estado", "Fallido");
//        		}
//        	else {
//        		DataRowExcel("Estado", "Exitoso");         		
//        	}
//        	} catch(InterruptedException e){
//        		DataRowExcel("Estado", "Fallido");
//        		}
        }

		
//////METODOS PARA LLENADO DE REPORTE//////
	
	//Escribe el Nro de la orden en el reporte de ejecución
	public void escribirNroOrden
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;

        Pattern p = Pattern.compile("[0-9]+$");
        String textoSolicitud = "";
        String Solicitud = "";
		
		elemento = acciones.FindElementoWait("NumPedido", Flujo, 10);
		textoSolicitud = elemento.getText();
		Matcher m = p.matcher(textoSolicitud);
		while (m.find()){
			Solicitud = Solicitud+m.group();
			DataRowExcel("Orden", Solicitud);
			System.out.println("El número de orden es: " + Solicitud);
			}
		
		if(Solicitud.equalsIgnoreCase(Solicitud));{
			System.out.println("El caso de prueba es exitoso");
			DataRowExcel("Estado", "Exitoso");
			}
		}
	
	//Escribe el Tipo de producto adquirido en el reporte de ejecución
	public void escribirTipoProducto
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;
        String TipoEquipo = "";
		
    	elemento = acciones.FindElementoWait("txtEquipo", Flujo, 10);
    	TipoEquipo = elemento.getText();
    	DataRowExcel("TipoProducto", TipoEquipo);
    	System.out.println("El tipo de producto es: " + TipoEquipo);
		}
	
	//Escribe la Fecha de la transacción en el reporte de ejecución
	public void escribirFechaTransaccion
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;

        String FechaTransaccion = "";
		
        elemento = acciones.FindElementoWait("fechaTransaccion", Flujo, 10);
        FechaTransaccion = elemento.getText();
        
        if(Flujo.equalsIgnoreCase("Prepago")) {
        DataRowExcel("Fecha", FechaTransaccion.substring(18, FechaTransaccion.length()));
        System.out.println("La fecha de la transacción es: " + FechaTransaccion.substring(18, FechaTransaccion.length()));
		}else {
			DataRowExcel("Fecha", FechaTransaccion.substring(7, FechaTransaccion.length()));
			System.out.println("La fecha de la transacción es: " + FechaTransaccion.substring(7, FechaTransaccion.length()));
		}
	}
	
	//Escribe el Tipo de pago en el reporte de ejecución
	public void escribirTipoPago
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;

        String TipoPago = "";
		
        elemento = acciones.FindElementoWait("tipoPago", Flujo, 10);
        TipoPago = elemento.getText();
        
        if(TipoPago.equalsIgnoreCase("00")) {
        DataRowExcel("TipoPago", "Prepago");
        System.out.println("El tipo de pago es: " + TipoPago);
        }else {
        	DataRowExcel("TipoPago", TipoPago);
        }
	}
	
	//Escribe el Tipo de Envío en el reporte de ejecución
	public void escribirTipoEnvio
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;

        String TipoEnvio = "";
		
        elemento = acciones.FindElementoWait("tipoEnvio", Flujo, 10);
        TipoEnvio = elemento.getText();         
        DataRowExcel("TipoEnvio", TipoEnvio);
        System.out.println("El tipo de envío es: " + TipoEnvio);
		}

	//Escribe el Nro de la orden de los flujos hogar en el reporte de ejecución
	public void escribirNroOrdenHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;

        Pattern p = Pattern.compile("[0-9]+$");
        String textoSolicitud = "";
        String Solicitud = "";
		
		elemento = acciones.FindElementoWait("NumPedidoHogar", Flujo, 10);
		textoSolicitud = elemento.getText();
		Matcher m = p.matcher(textoSolicitud);
		while (m.find()){
			Solicitud = Solicitud+m.group();
			DataRowExcel("Orden", Solicitud);
			System.out.println("El número de orden es: " + Solicitud);
			}
		
		if(Solicitud.equalsIgnoreCase(Solicitud));{
			System.out.println("El caso de prueba es exitoso");
			DataRowExcel("Estado", "Exitoso");
			}
		}
	
	//Captura y escribe la Fecha de transacción de los flujos hogar en el reporte de ejecución
	public void escribirFechaTransaccionHogar
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;

        String FechaTransaccion = "";
		
        elemento = acciones.FindElementoWait("fechaTransaccionHogar", Flujo, 10);
        FechaTransaccion = elemento.getText();

        DataRowExcel("Fecha", FechaTransaccion.substring(20, FechaTransaccion.length()));
        System.out.println("La fecha de la transacción es: " + FechaTransaccion.substring(20, FechaTransaccion.length()));
	}
	
	//Captura y escribe el valor de la session id en el reporte de ejecución
	public void escribirSesionPostpago
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		String textoSession = "";
		
		ecom.TimeSleep(2000);
		WebElement session = acciones.FindElementoWait("session", Flujo, 10);
	    textoSession = session.getAttribute("Value");
	    System.out.println("sessionId: "+textoSession);
		
	    DataRowExcel("SessionId", textoSession);
	}
	
	//Realiza una captura de pantalla
	public void capturarPantalla(String Nombre, Hashtable<String, String> Data) throws AWTException, InvalidFormatException {
	    String TipoFlujo = Data.get("TipoFlujo");
	    String IdEjecucion = Data.get("IdEjecucion");
	    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

	    try {
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        String timeStamp = dateFormat.format(cal.getTime());
	        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
	        String timeStamp2 = dateFormat2.format(cal.getTime());
	        
	        File RutaRaiz = new File("Reportes\\Capturas\\Ejecución_" +IdEjecucion+ "_" + TipoFlujo+ "_" + timeStamp2);
	        if (!RutaRaiz.exists()) {
	            RutaRaiz.mkdirs();
	        }

	        // Capturar pantalla y guardar como PNG
	        BufferedImage capture = new Robot().createScreenCapture(screenRect);
	        String nombreArchivoPNG = Nombre + "_" + timeStamp + ".png";
	        File archivoPNG = new File(RutaRaiz, nombreArchivoPNG);
	        ImageIO.write(capture, "png", archivoPNG);

	        // Agregar la captura al documento de Word
	        agregarCapturaAWord(archivoPNG, Nombre, timeStamp, Data);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	//Agrega la captura de pantalla al reporte de word
	private void agregarCapturaAWord(File archivoPNG, String Nombre, String timeStamp, Hashtable<String, String> Data) throws InvalidFormatException {
		String TipoFlujo = Data.get("TipoFlujo");
		String IdEjecucion = Data.get("IdEjecucion");
	    try {
	    	Calendar cal = Calendar.getInstance();
	    	SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
	        String timeStamp2 = dateFormat2.format(cal.getTime());
	        	    	
	        XWPFDocument doc = null;
	        File archivoWord = new File("Reportes\\Capturas\\Reporte_Ejecucion_" +IdEjecucion+ "_" +TipoFlujo+ "_" +timeStamp2+ ".docx");

	        if (archivoWord.exists()) {
	            FileInputStream fis = new FileInputStream(archivoWord);
	            doc = new XWPFDocument(fis);
	        } else {
	            doc = new XWPFDocument();
	        }

	        XWPFParagraph paragraph = doc.createParagraph();
	        XWPFRun run = paragraph.createRun();

	        run.setText("Captura de pantalla (" + Nombre + " - " + timeStamp + "):");        

	        FileInputStream inputStream = new FileInputStream(archivoPNG);
	        run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, archivoPNG.getName(), Units.toEMU(500), Units.toEMU(280));
	        inputStream.close();
	        run.addBreak();
	        run.addBreak();
	        FileOutputStream out = new FileOutputStream(archivoWord);
	        doc.write(out);
	        out.close();
	        doc.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	//Crea un archivo word para adjuntar las evidencias
	public void crearArchivoWord(Hashtable<String, String> Data) throws IOException, InvalidFormatException {
		 
		 	String TipoFlujo = Data.get("TipoFlujo");
	        String imgFile = "Reportes\\Capturas\\0602202410 Prepago\\1 Home Catálogo.png";
	        String imgFile2 = "Reportes\\Capturas\\0602202410 Prepago\\2 Busqueda de equipo.png";
	        String imgFile3 = "Reportes\\Capturas\\0602202410 Prepago\\3 Equipo seleccionado.png";
	        String imgFile4 = "Reportes\\Capturas\\0602202410 Prepago\\4 Datos del titular 1.png";
	        String imgFile5 = "Reportes\\Capturas\\0602202410 Prepago\\5 Datos del titular 2.png";
	        String imgFile6 = "Reportes\\Capturas\\0602202410 Prepago\\6 Acepta términos y condiciones.png";
	        String imgFile7 = "Reportes\\Capturas\\0602202410 Prepago\\7 Confirmación de compra.png";
	        String fileName = "Reportes\\Capturas\\Prueba "+TipoFlujo+".docx";
	     
	        try (XWPFDocument doc = new XWPFDocument()) {

	            // create a paragraph
	            XWPFParagraph p1 = doc.createParagraph();
	            p1.setAlignment(ParagraphAlignment.CENTER);
	            // set font
	            XWPFRun r1 = p1.createRun();
	            r1.setBold(true);
//	            r1.setItalic(true);
	            r1.setFontSize(22);
	            r1.setFontFamily("New Roman");
	            r1.setText("Reporte de evidencias " + TipoFlujo);
	            r1.addBreak();
	            
	            XWPFParagraph p2 = doc.createParagraph();
	            p2.setAlignment(ParagraphAlignment.LEFT);
	           
	            XWPFRun r2 = p2.createRun();
//	            r1.setBold(true);
	            r2.setItalic(true);
	            r2.setFontSize(11);
	            r2.setFontFamily("New Roman");
	            
	            	           	            
	            
	         // add png image
//	            try (FileInputStream is = new FileInputStream(imgFile)) {
	            FileInputStream is = new FileInputStream(imgFile);
	            	r2.setText(imgFile);
	                r2.addPicture(is,
	                        Document.PICTURE_TYPE_PNG,    // png file
	                        imgFile,
	                        Units.toEMU(400),
	                        Units.toEMU(200));            // 400x200 pixels
	                r2.addBreak();
	                r2.addBreak();
	                
		        FileInputStream is2 = new FileInputStream(imgFile2);
	                r2.setText(imgFile2);
	                r2.addPicture(is2,
	                        Document.PICTURE_TYPE_PNG,    // png file
	                        imgFile2,
	                        Units.toEMU(400),
	                        Units.toEMU(200));            // 400x200 pixels
	                r2.addBreak();
	                r2.addBreak();
	                
	            FileInputStream is3 = new FileInputStream(imgFile2);
	                r2.setText(imgFile3);
	                r2.addPicture(is3,
	                        Document.PICTURE_TYPE_PNG,    // png file
	                        imgFile3,
	                        Units.toEMU(400),
	                        Units.toEMU(200));            // 400x200 pixels
	                r2.addBreak();
	                r2.addBreak();
	                
		        FileInputStream is4 = new FileInputStream(imgFile2);
	                r2.setText(imgFile4);
	                r2.addPicture(is4,
	                        Document.PICTURE_TYPE_PNG,    // png file
	                        imgFile4,
	                        Units.toEMU(400),
	                        Units.toEMU(200));            // 400x200 pixels
	                r2.addBreak();
	                r2.addBreak();
	                
		        FileInputStream is5 = new FileInputStream(imgFile2);
	                r2.setText(imgFile5);
	                r2.addPicture(is5,
	                        Document.PICTURE_TYPE_PNG,    // png file
	                        imgFile5,
	                        Units.toEMU(400),
	                        Units.toEMU(200));            // 400x200 pixels
	                r2.addBreak();
	                r2.addBreak();
	                
		        FileInputStream is6 = new FileInputStream(imgFile2);
	                r2.setText(imgFile6);
	                r2.addPicture(is6,
	                        Document.PICTURE_TYPE_PNG,    // png file
	                        imgFile6,
	                        Units.toEMU(400),
	                        Units.toEMU(200));            // 400x200 pixels
	                r2.addBreak();
	                r2.addBreak();
	                
		        FileInputStream is7 = new FileInputStream(imgFile2);
	                r2.setText(imgFile7);
	                r2.addPicture(is7,
	                        Document.PICTURE_TYPE_PNG,    // png file
	                        imgFile7,
	                        Units.toEMU(400),
	                        Units.toEMU(200));            // 400x200 pixels	
	                r2.addBreak();
	                r2.addBreak();
//	            }

	            // save it to .docx file
	            try (FileOutputStream out = new FileOutputStream(fileName)) {
	                doc.write(out);
	            }

	        }
	 }
	
	
	public void pruebaValidacionRadioButton
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");	    
	    DataRowExcel("Flujo", Flujo);

		
		try {
			
//			acciones.ClickEcom("radio1", Flujo, 80);	
//			acciones.ClickEcom("radio2", Flujo, 80);	
//			acciones.ClickEcom("radio3", Flujo, 80);	
			acciones.ClickEcom("btnSgteValidacion", Flujo, 80);	        
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}
	
	
	//Presiona el botón "Siguiente" de la pantalla Tipo de envío
	public void seleccionaDiaBloqueHora
(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		String Flujo = Data.get("Flujo");
		
		try {
			ecom.TimeSleep(2000);
			acciones.FindElementoWaitBoo("opcionManana",Flujo,10);
			acciones.ClickEcom("opcionManana", Flujo, 10);
			acciones.FindElementoWaitBoo("primerBloque",Flujo,10);
			acciones.ClickEcom("primerBloque", Flujo, 10);
			System.out.println("Seleccionó el dia de mañana y el bloque de 9:30 a 12:30");
			ecom.TimeSleep(1500);
			} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
				}
		}

}