package Business;

import java.awt.AWTException;
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

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Key;

import java.util.ArrayList;

import Core.Selenium.AccionesWeb;
import Core.Sikuli.Sikuli;
import Core.Xml.LeerPasos;
import junit.framework.Assert;
import Core.Selenium.InitSelenium;


public class Operaciones extends Ecommers {
	
	public static WebDriver driver = null;
	private AccionesWeb acciones = new AccionesWeb();
	private Core.Ini.LeerData DataIni = new Core.Ini.LeerData();
	private LeerPasos xml = new LeerPasos();
	private String plataforma = "Ecommerce";
	private Sikuli sikuli= new Sikuli();
	private Ecommers ecom = new Ecommers();
	public String Modulo;
	private InitSelenium init = new InitSelenium();
	Core.ScreenShot.ScreenShot Screen = new Core.ScreenShot.ScreenShot();

	
    public void IngresoInicial(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
	 	String ID = Data.get("ID");
	 	String Flujo = Data.get("Flujo");
	 	String TipoFlujo = Data.get("TipoFlujo");
		String Check = Data.get("Check");
		String Rut  = Data.get("Rut");
		String Telefono  = Data.get("Telefono");
		String Correo  = Data.get("Correo");
		String CasoDePrueba = Data.get("CasoDePrueba");	
		String PlanHogar = Data.get("NombrePlan");
	
		WebElement elemento;
	    boolean elem;

	    //Ingresa al flujo de Hogar
		if(Flujo.equalsIgnoreCase("Hogar")) {
	    
		switch(TipoFlujo){
    	case "1P":
    		if (acciones.FindElementoWaitBoo("checkInternet", Flujo, 80)) {
    			System.out.println("Ejecutando flujo de Hogar 1P");
    			acciones.ClickEcom("checkTelevision", "Hogar", 10);
    			acciones.ClickEcom("btnSiguientea", "Hogar", 10);
            }
    		break;
         
		case "2P":
            if (acciones.FindElementoWaitBoo("checkInternet", Flujo, 80)) {
    			System.out.println("Ejecutando flujo de Hogar 2P");
    			acciones.ClickEcom("checkInternet", "Hogar", 10);
    			acciones.ClickEcom("checkTelevision", "Hogar", 10);			
    			acciones.ClickEcom("btnSiguientea", "Hogar", 10);
	    }
            break;

		case "3P":
            if (acciones.FindElementoWaitBoo("checkInternet", Flujo, 80)) {
    			System.out.println("Ejecutando flujo de Hogar 3P");
    			acciones.ClickEcom("checkInternet", "Hogar", 10);
    			acciones.ClickEcom("checkTelevision", "Hogar", 10);	
    			acciones.ClickEcom("checkTelefonia", "Hogar", 10);				
    			acciones.ClickEcom("btnSiguientea", "Hogar", 10);
    			
            }
            break;
		}
		}
		
	    	    
		if(CasoDePrueba.equals("null")) {
			CasoDePrueba = "Flujo completo";
		}
		
		String Fecha =  ecom.fechaHora();    
		DataRowExcel("ID", ID);
		DataRowExcel("Flujo", Flujo);
		DataRowExcel("Fecha", Fecha);
		DataRowExcel("CasoDePrueba", CasoDePrueba);
		DataRowExcel("Estado", "Fallido");
			
		try {			
			//rut
			acciones.ClickEcom("inputRut", "EcommerceGlobales", 10);  
			acciones.scrollMonitor(500);
	        acciones.InputText(Rut,"inputRut","EcommerceGlobales");
	        DataRowExcel("Rut", Rut);
	       
	        boolean RutValida = acciones.ValidaRut(Rut);
	        if (CasoDePrueba.equals("Rut invalido")) {
		        if (!RutValida){
		        	System.out.println("Rut ingresado es invalido");
		        	DataRowExcel("Estado", "Exitoso");
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Rut,"Exitoso");
		        } else {
		        	System.out.println("Rut ingresado es valido");
		        	DataRowExcel("Estado", "Fallido");
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Rut,"Fallido");
		        }
		        
		        elemento = acciones.FindElemento("inputRut","EcommerceGlobales");
		        elemento.sendKeys(Keys.TAB);  
	
		        if (sikuli.FindImgWait("ValidaIngreso\\RutInvalido.png", 1)){
		        	System.out.println("Rut Incorrecto");
		        	DataRowExcel("Estado", "Exitoso");
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Rut,"Exitoso");
		        }
		        return;
	        }
	        if (CasoDePrueba.equals("Rut valido")) {
	        	 if (!RutValida){
			        	System.out.println("Rut ingresado es invalido");
			        	DataRowExcel("Estado", "Fallido");
			        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Rut,"Fallido");
			        } else{
	        		 System.out.println("Rut valido");
	 	        	 DataRowExcel("Estado", "Exitoso");
	 	        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Rut,"Exitoso");
	        	 }
	        	 return;
	        }
	           
	        //Telefono
	        acciones.ClickEcom("inputTelefono", "EcommerceGlobales", 10);
		    acciones.InputText(Telefono,"inputTelefono","EcommerceGlobales");
		    elemento = acciones.FindElemento("inputTelefono","EcommerceGlobales");
	        elemento.sendKeys(Keys.TAB); 
	        DataRowExcel("Telefono", Telefono);
	        
	        int FonoVal = Telefono.length();
	        
	        if (CasoDePrueba.equals("Telefono invalido")) {
			    if (FonoVal != 9){ 
		        	System.out.println("Telefono Incorrecto, debe tener 9 digitos");
		        	DataRowExcel("Estado", "Exitoso"); 
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Telefono,"Exitoso");
		        } else{
	        		System.out.println("Telefono valido");
		        	DataRowExcel("Estado", "Fallido"); 
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Telefono,"Fallido");
	        	}
			    return;
	        }
	     
	        if (CasoDePrueba.equals("Telefono valido")) {
	        	if (FonoVal != 9){ 
		        	System.out.println("Telefono Incorrecto, debe tener 9 digitos");
		        	DataRowExcel("Estado", "Fallido");  
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Telefono,"Fallido");
		        } else{
	        		System.out.println("Telefono valido");
		        	DataRowExcel("Estado", "Exitoso"); 
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Telefono,"Exitoso");
	        	}
	        	return;
	        }
		    
		    //Correo
		    acciones.ClickEcom("inputCorreo", "EcommerceGlobales", 10);
		    acciones.InputText(Correo,"inputCorreo","EcommerceGlobales");	
		    elemento = acciones.FindElemento("inputCorreo","EcommerceGlobales");
	        elemento.sendKeys(Keys.TAB); 
	        
	        boolean CorreoValida = acciones.ValidarMail(Correo);
		    if (CasoDePrueba.equals("Correo invalido")) {				    
		        if (!CorreoValida){
		        	System.out.println("Correo ingresado es invalido, formato incorrecto");
		        	DataRowExcel("Estado", "Exitoso"); 
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Correo,"Exitoso");
		        } else {
			        System.out.println("Correo ingresado es valido");
			        DataRowExcel("Estado", "Fallido");
			        //LlenaExcelSalida(ID, CasoDePrueba, Modulo,Correo,"Fallido");
		        }
			    if (sikuli.FindImgWait("ValidaIngreso\\CorreoInvalido.png", 1)){ 
		        	System.out.println("Correo Incorrecto");
		        	DataRowExcel("Estado", "Exitoso");
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Correo,"Exitoso");
		        }
	        	return;
		    }
		    if (CasoDePrueba.equals("Correo valido")) {
		    	 if (!CorreoValida){
		        	System.out.println("Correo ingresado es invalido2, formato incorrecto");
		        	DataRowExcel("Estado", "Fallido");
		        	//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Correo,"Fallido");
			        	
			     } else {
		    		 System.out.println("Correo valido");
		    		 DataRowExcel("Estado", "Exitoso");
		    		 //LlenaExcelSalida(ID, CasoDePrueba, Modulo,Correo,"Exitoso");
		    	 }
		    	 return;
	        }
		    			
		    //check
		    switch (Flujo) {
			    case "Hogar":
			    	elem = acciones.FindElementoWaitBoo("checkHogar","EcommerceGlobales",2);
				    if(Check.equals("Checkeado")) 
			    		acciones.ClickEcom("checkHogar", "EcommerceGlobales", 10);
			    	else 
			    		System.out.println("Prueba check no seleccionado");
					break;
				case "Recambio":
					elem = acciones.FindElementoWaitBoo("check","EcommerceGlobales",2);
				    if(Check.equals("Checkeado")) 
			    		acciones.ClickEcom("check", "EcommerceGlobales", 10);
				    else 
			    		System.out.println("Prueba check no seleccionado");
				    break;  
		    }
		    
		    if (CasoDePrueba.equals("Terminos checkeado")) {
		    	if(Check.equals("Checkeado")){
		    		DataRowExcel("Estado", "Fallido");
		    		//LlenaExcelSalida(ID, CasoDePrueba, Modulo,Check,"Exitoso");
		    	}else {
		    		LlenaExcelSalida(ID, CasoDePrueba, Modulo,Check,"Fallido");
		    	}
	        	return;
		    }
			if (CasoDePrueba.equals("Terminos No checkeado")) {
				if(Check.equals("Checkeado")){
					LlenaExcelSalida(ID, CasoDePrueba, Modulo,Check,"Fallido");
		    	} else {
		        	LlenaExcelSalida(ID, CasoDePrueba, Modulo,Check,"Exitoso");
		    	}
				return;
			}
		    
		    //Next
		    acciones.scrollMonitor(500);	
		 
			if(Flujo.equalsIgnoreCase("Recambio")) {
		        if (acciones.FindElementoWaitBoo("btnSiguiente", "EcommerceGlobales", 10)) {
		        	ecom.TimeSleep(8000);
		        	acciones.ClickEcom("btnSiguiente", "EcommerceGlobales", 10);
		        }
		        if (acciones.FindElementoWaitBoo("btnSigPostPago", "EcommerceGlobales", 10)) {
		        	ecom.TimeSleep(8000);
		        	acciones.ClickEcom("btnSigPostPago", "EcommerceGlobales", 10);
		        }
		        
		    }
		} catch(InterruptedException e){
    		DataRowExcel("Estado", "Fallido");
		}
    }	
				
    
	public void ValidacionDatos(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {		 		
	 	Modulo = "Validación de datos ";
 		String NumSerie = DataIni.GetValue("Datos", "Identificacion", "NumeroSerie");
		String NumMensaje = DataIni.GetValue("Datos", "Identificacion", "NumeroMensaje");
		String CasoDePrueba = Data.get("CasoDePrueba"); 
		String ID = Data.get("ID");
		String Flujo =  Data.get("Flujo");
		
		ecom.TimeSleep(5000);
		acciones.scrollMonitor(900);
		 
    	acciones.ClickEcom("inputNroSerie", Flujo, 10);
		WebElement elemento = acciones.FindElemento("inputNroSerie",Flujo);
        acciones.InputText(NumSerie,"inputNroSerie",Flujo);
	    elemento.sendKeys(Keys.TAB);
	    
	    //boolean SerieVal = acciones.EsNumero(NumSerie);
	    int NumSerieVal = NumSerie.length();
	    
	   //No se valida numero de serie ya que no se conocen los parametros a validar		
		acciones.ClickEcom("inputMensaje", Flujo, 10);
		elemento = acciones.FindElemento("inputMensaje",Flujo);
		acciones.InputText(NumMensaje,"inputMensaje",Flujo);
		elemento.sendKeys(Keys.TAB);
		
		boolean SMSVal = acciones.EsNumero(NumMensaje);
		int NumSMSVal = NumMensaje.length();				
		
		if (CasoDePrueba.equals("Codigo SMS valido")) {					  
			//Valida que codigo Sms sea numerico
			if(!SMSVal) {
			    	System.out.println("Codigo SMS invalido ");
		        	LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumMensaje,"Fallido");
			} else {
				  	System.out.println("Codigo SMS valido ");
		        	LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumMensaje,"Exitoso");
			}
			//NumSMSVal
			if(NumSMSVal < 6) {
	    	 	System.out.println("Prueba Numero de serie invalido ");
	        	LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumMensaje,"Fallido");
        	} else {
	    		System.out.println("Prueba Numero de serie valido ");
	    		LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumMensaje,"Exitoso");
	    	}					
			return;
		}
		  
		if (CasoDePrueba.equals("Codigo SMS invalido")) {
			if (sikuli.FindImgWait("ValidaIdentidad\\SmsInvalido.png", 1)){ 
				System.out.println("Codigo SMS valido ");
				LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumMensaje,"Exitoso");
			} else {
				System.out.println("Codigo SMS valido ");
				LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumMensaje,"Fallido");
			}
			
			if(NumSMSVal < 6) {
	    	 	System.out.println("Prueba Numero de serie invalido ");
	    	 	LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumMensaje,"Exitoso");
        	} else {
	    		System.out.println("Prueba Numero de serie valido ");
	    		LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumMensaje,"Fallido");
	    	}
			return;
		}
		
		
		//Next
		acciones.FindElementoWait("btnSigIden", Flujo, 10);
        acciones.ClickEcom("btnSigIden", Flujo, 10);
        
        if (CasoDePrueba.equals("Numero serie valido")) {
	    	//Valida que numero de serie sea de 9 o 6 digitos
	    	if(NumSerieVal == 9 || NumSerieVal == 6) {
	    	 	System.out.println("Prueba Numero de serie valido ");
	    	 	LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumSerie,"Exitoso");
        	}else {
	    		System.out.println("Prueba Numero de serie invalido ");
	    		LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumSerie,"Fallido");
	    	} 	
	    	if (sikuli.FindImgWait("ValidaIdentidad\\NumSerieInvalido.png", 1)){ 
	        	System.out.println("Prueba Numero de serie invalido ");
	        	LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumSerie,"Fallido");
	        }
        	return;
	    }
	    
	    if (CasoDePrueba.equals("Numero serie invalido")) {
	      	//Valida que numero de serie sea de 9 o 6 digitos
	    	if(NumSerieVal == 9 || NumSerieVal == 6) {
	    	 	System.out.println("Prueba Numero de serie invalido ");
	        	LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumSerie,"Fallido");
        	}
	    	else {
	    		System.out.println("Prueba Numero de serie valido ");
	        	LlenaExcelSalida(ID, CasoDePrueba, Modulo,NumSerie,"Exitoso");
	    	}
	    	return;
	    }    	
 	}
	

 	public void FormularioEnvioClte(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {		
 		String ID = Data.get("ID");
 		String TipoFlujo = Data.get("TipoFlujo");
		String Nombre  = Data.get("Nombre");
 		String Apellidos  = Data.get("Apellidos");
 		String Dia = Data.get("Dia");
 		String Mes = Data.get("Mes");
 		String Anio = Data.get("Anio");	 	
 		String Rut = Data.get("Rut");	
 		String FonoDatPersonal = Data.get("FonoDatPersonal");
 		String CorreoDatPersonal = Data.get("CorreoDatPersonal");
 		String Region = Data.get("Region");
 		String Comuna = Data.get("Comuna");
 		String Calle = Data.get("Calle");
 		String Numeracion = Data.get("Numeracion");
 		String CasoDePrueba = Data.get("CasoDePrueba"); 
 		String Flujo = Data.get("Flujo"); 	
 		String NumMensaje = DataIni.GetValue("Datos", "Identificacion", "NumeroMensaje");
 		
 		Calendar cal= Calendar.getInstance();
 		int AnioActual = cal.get(Calendar.YEAR);
 		int EdadClte = AnioActual - Integer.parseInt(Anio); 	
 		int ValNumeracion = Numeracion.length();
 		List<WebElement> datos;
 		DataRowExcel("Estado", "Fallido");
 		
	 	try {
	 		 	
	 		//Nombre
	        acciones.clearText("inputNombre", Flujo);
	        acciones.InputText(Nombre,"inputNombre", Flujo);
	        
	        //apellidos
	        acciones.clearText("inputApellidos", Flujo);
	        acciones.InputText(Apellidos,"inputApellidos", Flujo);
	        
	        //dia
	        acciones.ClickEcom("selectDia",Flujo,10);
	        String xpathDia = "//*[@id='WC_PersonalInfoExtension_birth_date-menu']/li["+(Integer.parseInt(Dia)+1)+"]";
	        acciones.ClickXpath(xpathDia);
	        ecom.TimeSleep(500);
	       
	        //mes
	        acciones.ClickEcom("selectMes",Flujo,10);
	        String xpathMes = "//*[@id='WC_PersonalInfoExtension_birth_month-menu']/li["+(Integer.parseInt(Mes)+1)+"]";
	        acciones.ClickXpath(xpathMes);
	        ecom.TimeSleep(500);
	
	    	//año
	        acciones.ClickEcom("selectAno",Flujo,10);
	        datos = acciones.findElementosWait("listAno",Flujo,15);
	        String xpathAnio = "//*[@id='WC_PersonalInfoExtension_birth_year-menu']/li[@value="+Anio+"]";
	        acciones.ClickXpath(xpathAnio);
	        ecom.TimeSleep(500);
	        
	        if (CasoDePrueba.equals("Edad invalido")) {
		        if (EdadClte <= 18) {
		        	System.out.println("El cliente es menor de edad");
		        	DataRowExcel("Estado", "Exitoso");
		        }else {
		        	System.out.println("El cliente es mayor de edad");
		        	DataRowExcel("Estado", "Fallido");
		        }
		        return;
	        }
	        if (CasoDePrueba.equals("Edad valido")) {
	        	if (EdadClte >= 18) {
		        	System.out.println("El cliente es mayor de edad");
		        	DataRowExcel("Estado", "Exitoso");
		        }else {
		        	System.out.println("El cliente es menor de edad");
		        	DataRowExcel("Estado", "Fallido");
		        }
		        return;
	        }     		        
		    acciones.scrollMonitor(250);
		    
		    if(Flujo.equalsIgnoreCase("Prepago") || Flujo.equalsIgnoreCase("Recambio"))
			{
		    	//Rut
			    acciones.clearText("inputRut", Flujo);
		        acciones.InputText(Rut,"inputRut", Flujo);
		        DataRowExcel("Rut", Rut);
		        
			    //Telefono formulario
		        acciones.clearText("NumFono", Flujo);
		        acciones.InputText(FonoDatPersonal,"NumFono", Flujo);
		        acciones.ClickEcom("CorreoDatPer",Flujo,10); 
		        ecom.TimeSleep(5000);
		        
		        int FonoVal = FonoDatPersonal.length();
		        
		        if (CasoDePrueba.equals("Telefono formulario invalido")) {
				    
				    if (FonoVal != 9){ 
			        	System.out.println("Telefono formulario invalido");
			        	DataRowExcel("Estado", "Exitoso");
			        }else {
			        	System.out.println("Telefono formulario valido");
			        	DataRowExcel("Estado", "Fallido");
		        	}
				    return;
		
		        }
		        if (CasoDePrueba.equals("Telefono formulario valido")) {
		        	if (FonoVal != 9){ 
			        	System.out.println("Telefono formulario invalido");
			        	DataRowExcel("Estado", "Fallido");
			        }else {
			        	System.out.println("Telefono formulario valido");
			        	DataRowExcel("Estado", "Exitoso");
		        	}
		        	return;
		        }
		        
			    if(Flujo.equalsIgnoreCase("Recambio"))
				{
			    	if (acciones.FindElementoWaitBoo("inputMensaje",Flujo,5)) {
			    		acciones.ClickEcom("inputMensaje", Flujo, 5);
			    		acciones.InputText(NumMensaje,"inputMensaje",Flujo);
			    		acciones.ClickEcom("btnSigIden", Flujo, 5);
			    	}	
			    }		        
		       
		        //Correo formulario
		        acciones.clearText("CorreoDatPer", Flujo);
		        acciones.InputText(CorreoDatPersonal,"CorreoDatPer", Flujo);
		       
		        boolean CorreoValidaForm = acciones.ValidarMail(CorreoDatPersonal);
		        
		        if (CasoDePrueba.equals("Correo formulario valido")) {
		        	if(!CorreoValidaForm) {
			        	System.out.println("Correo formulario invalido");
			        	DataRowExcel("Estado", "Fallido");
			        }else {
			        	System.out.println("Correo formulario valido");
			        	DataRowExcel("Estado", "Exitoso");
		        	}
		        	return;
		        }
		        
		        if (CasoDePrueba.equals("Correo formulario invalido")) {
		        	if(CorreoValidaForm) {
			        	System.out.println("Correo formulario valido");
			        	DataRowExcel("Estado", "Fallido");
			        }else {
			        	System.out.println("Correo formulario invalido");
			        	DataRowExcel("Estado", "Exitoso");
			       	}
		        	return;
		        }
		        ecom.TimeSleep(1500);
		    }
		    
	        //region
	        ecom.OptRegion("selectRegion", Flujo, "listRegion", Region);
	        acciones.ClickEcom("FocusRegion",Flujo,10); 
	        ecom.TimeSleep(3000);
	              
	        //Comuna 	        
	        ecom.OptComuna("selectComuna", Flujo, "listComuna", Comuna); 
	        ecom.TimeSleep(2000);
	        
	        //Calle
	        Calle = (Calle.toUpperCase()).trim();
	        int ValCalle = Calle.length();
	                	
	        if(ValCalle > 0) {
	        	ecom.OptCalle("inputCalle", Flujo, Calle);
	        	acciones.clearText("inputCalle", Flujo);
	        	ecom.TimeSleep(1000);
	        	ecom.OptCalle("inputCalle", Flujo, Calle);
	        	acciones.FindElementoWaitBoo("resultadoMaps", Flujo, 10);
	        	acciones.ClickEcom("resultadoMaps", Flujo, 10); 
	        	
	        }
	        if (CasoDePrueba.equals("Calle/Avenida/Pasaje valido")) {
	        	if(ValCalle > 0) {
	        		System.out.println("Calle valida");
	        		DataRowExcel("Estado", "Exitoso");
	        	}else {
	        		System.out.println("Calle invalida");
	        		DataRowExcel("Estado", "Fallido");
	        	}
	        	return;
	        }
	        if (CasoDePrueba.equals("Calle/Avenida/Pasaje invalido")) {
	        	if(ValCalle > 0) {
	        		System.out.println("Calle invalida");
	        		DataRowExcel("Estado", "Fallido");
	        	}else {
	        		System.out.println("Calle valida");
	        		DataRowExcel("Estado", "Exitoso");
	        	}
	        	return;
	        }
	  
	        //numeracion 
	        boolean ValNotxt = acciones.NumLetra(Numeracion);        
	        int numer = Numeracion.length();
			 
	        if(!Numeracion.equals("null") || numer > 0) {
	        	acciones.clearText("inputNumeracion", Flujo);
	        	ecom.TimeSleep(2000);
	        	acciones.InputText(Numeracion,"inputNumeracion", Flujo); 	 
	        }        
	        if (CasoDePrueba.equals("Numeracion valida")) {
	        	 if(!Numeracion.equals("null") || numer > 0) {
	        		 if(ValNotxt) {
		        		 System.out.println("Numeracion valida111");
		        		 DataRowExcel("Estado", "Exitoso");
		        	 }else {
		        		 System.out.println("Numeracion invalida8");
		        		 DataRowExcel("Estado", "Fallido");
	        		 }	        	 
	        	 }else {
	        		 System.out.println("Numeracion invalida2");
	        		 DataRowExcel("Estado", "Fallido");
	        	 }
	        	 return;
	        }
	        
	        if (CasoDePrueba.equals("Numeracion invalida")) {
	        	 if(!Numeracion.equals("null") || numer > 0) {
	        		 if(!ValNotxt) {
		        		 System.out.println("Numeracion invalida3");
		        		 DataRowExcel("Estado", "Exitoso");
		        	 }else {
		        		 System.out.println("Numeracion valida4");
		        		 DataRowExcel("Estado", "Fallido");
		        	 }
	        	 }else {
	        		 System.out.println("Numeracion invalida");
	        		 DataRowExcel("Estado", "Exitoso");
	        	 }		 
	        	 return;
	        }	        
			
		   ecom.TimeSleep(4000);
	 		
			if(Flujo.equalsIgnoreCase("Recambio")) 
			{
				acciones.ClickEcom("btnContratarPlan", Flujo, 10);
				ecom.TimeSleep(2000);
			}		   
			else
				
		   //Next
//	       acciones.ClickEcom("btnContratarPlan", Flujo, 10);		   
		   acciones.ClickEcom("btnContinuar", Flujo, 10);
		   
		   //Prueba solo para los casos de linea nueva
			if(TipoFlujo.equalsIgnoreCase("Linea Nueva") && Flujo.equalsIgnoreCase("PostPago")) 
			{
				//Selecciona el tipo de envío y termina la ejecución
				seleccionaTipoEnvio(Data);
				ConfirmaSolicitud(Data);				
			}
		       
			//pasa al siguiente - solo para sanity de Prepago y Postpago
			if(Flujo.equalsIgnoreCase("Prepago") || Flujo.equalsIgnoreCase("PostPago")) 
			{
				//ResumenCompra(Data);
				NuevoResumenCompara(Data);
			}

								
		} catch(InterruptedException e){
			DataRowExcel("Estado", "Fallido");
		}          
 	}
 	
 	
 	
 	public void NuevoResumenCompara(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
 		String Flujo = Data.get("Flujo");
 		String TipoFlujo = Data.get("TipoFlujo");	
 		ecom.TimeSleep(5000);
 		
 		try {
 			if(!TipoFlujo.equalsIgnoreCase("Migracion")) { 

		 		if(!Flujo.equalsIgnoreCase("Recambio")) { 
		 			
		 			if(Flujo.equalsIgnoreCase("Prepago")) { 
					    String textoSession = "";
					    WebElement session = acciones.FindElementoWait("session", Flujo, 10);
					    textoSession = session.getAttribute("Value");
					    System.out.println("sessionId - Prepago: "+textoSession);
					    DataRowExcel("SessionId", textoSession);    			
					    } 			 			
		 		}
		 		
				if(Flujo.equalsIgnoreCase("Prepago")) {
					acciones.ClickEcom("xxSiguienteOtraPer", Flujo, 10);
					ecom.TimeSleep(7000);
				}
		 					
				////////// Confirmacion de direccion de resumen de compra
				if(acciones.FindElementoWaitBoo("checkTerminos", "PostPago", 5))
						acciones.ClickEcom("checkTerminos", Flujo, 10);
				
				acciones.scrollMonitor(250);
				 //Siguiente
				if(acciones.FindElementoWaitBoo("SiguienteOtraPerFac", Flujo, 5)) {
					acciones.ClickEcom("SiguienteOtraPerFac", Flujo, 10);
					ecom.TimeSleep(7000);
				}
				
				
				if( Flujo.equalsIgnoreCase("Prepago") || Flujo.equalsIgnoreCase("PostPago")) {
					ConfirmaSolicitud(Data);
				}
			
			} else {

				if(TipoFlujo.equalsIgnoreCase("Migracion")) {
					seleccionaTipoEnvio(Data);
					}
				
				 //Siguiente
				if(acciones.FindElementoWaitBoo("SiguienteOtraPerFacMig", Flujo, 5)) {
					System.out.println("SiguienteOtraPerFacMig encontrado");			
					ecom.TimeSleep(5000);
					acciones.ClickEcom("SiguienteOtraPerFacMig", Flujo, 10);
				}
				
				
				ConfirmaSolicitud(Data);				
				//DataRowExcel("Estado", "Exitoso");
				//DataRowExcel("Oden", "");
			}
	
 		} catch(InterruptedException e){
    		DataRowExcel("Estado", "Fallido");	
    	}		    
 	}
 	
 	
 	public void ResumenCompra(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException { 		
 		String QuienRecibe  = Data.get("QuienRecibe");
 		String RutOtraPers  = Data.get("RutOtraPers");
 		String NomOtraPers  = Data.get("NomOtraPers");
 		String ApellOtraPers  = Data.get("ApellOtraPers");
 		String FonoOtraPers = Data.get("FonoOtraPers");
 		String CasoDePrueba = Data.get("CasoDePrueba");
 		String Flujo = Data.get("Flujo");
 		String TipoFlujo = Data.get("TipoFlujo"); 		
 		
  		if(CasoDePrueba.equalsIgnoreCase("null")) {
 			CasoDePrueba = "0";
    	}

 		ecom.TimeSleep(3000);
 		
 		try {
 			if(!TipoFlujo.equalsIgnoreCase("Migracion")) { 
		 		if(!Flujo.equalsIgnoreCase("Recambio")) { 
		 			
		 			if(Flujo.equalsIgnoreCase("Prepago")) { 
					    String textoSession = "";
					    WebElement session = acciones.FindElementoWait("session", Flujo, 10);
					    textoSession = session.getAttribute("Value");
					    System.out.println("sessionId - Prepago: "+textoSession);
					    DataRowExcel("SessionId", textoSession);
		 			}
		 			
			 		switch (QuienRecibe){
			        	case "YoRecibire":
			        			acciones.ClickEcom("checkYoRecibo", Flujo, 10);
				        		if (CasoDePrueba.equals("Recepcion pedido valido")) {
					  					System.out.println("Recepcion pedido valido - YoRecibire");
					  					DataRowExcel("Estado", "Exitoso");
					  					return;
				        		}
					        break;
						case "OtraPersona":
							acciones.ClickEcom("checkOtraPer", Flujo, 10);
							
					       //Nombre
				       		acciones.clearText("NomOtraPers", Flujo);
					        acciones.InputText(NomOtraPers,"NomOtraPers", Flujo);			        
					        
					        //Apellido
			          		acciones.clearText("ApellOtraPers", Flujo);
					        acciones.InputText(ApellOtraPers,"ApellOtraPers", Flujo);
				          				        
					        //Rut
				 			acciones.clearText("RutOtraPers", Flujo);
					        acciones.InputText(RutOtraPers,"RutOtraPers", Flujo);  
			        
					        boolean ValRutRecep = acciones.ValidaRut(RutOtraPers);
			
					        if (CasoDePrueba.equals("Recepcion pedido valido")) {
			  					if(ValRutRecep) {
			  						System.out.println("Recepcion pedido valido - OtraPersona");
			  						DataRowExcel("Estado", "Exitoso");
			  					}else {
			  						System.out.println("Recepcion pedido invalido - OtraPersona");
			  						DataRowExcel("Estado", "Fallido");
			  					}
			  					return;
					        }	
					        if (CasoDePrueba.equals("Recepcion pedido invalido")) {
					        	if(!ValRutRecep) {
			  						System.out.println("Recepcion pedido invalido - OtraPersona");
			  						DataRowExcel("Estado", "Exitoso");
			  					}else {
			  						System.out.println("Recepcion pedido valido - OtraPersona");
			  						DataRowExcel("Estado", "Fallido");
			  					}
					        	return;
					        }	
					        
					        //Telefono 
				 			acciones.clearText("FonoOtraPers", Flujo);
					        acciones.InputText(FonoOtraPers,"FonoOtraPers", Flujo); 
					        break;
						case "Conserjeria":
							acciones.ClickEcom("checkConserj", Flujo, 10);
							if (CasoDePrueba.equals("Recepcion pedido valido")) {
			  					System.out.println("Recepcion pedido valido - Conserjeria");
			  					DataRowExcel("Estado", "Exitoso");
			  					return;
							}
					        break;
					    default:
					    	break;
			 		}
		 		}
		 		acciones.scrollMonitor(250);
		 		
		 		if(acciones.FindElementoWaitBoo("checkTerminos", Flujo, 5))
		 			acciones.ClickEcom("checkTerminos", Flujo, 10);
		 		
		 		acciones.scrollMonitor(250);
		 		ecom.TimeSleep(1500);
		 		//Siguiente
		 		acciones.ClickEcom("SiguienteOtraPer", Flujo, 10);
		 		
		 		//pasa al siguiente - solo para sanity de Prepago y Postpago
				if( Flujo.equalsIgnoreCase("Prepago") || Flujo.equalsIgnoreCase("PostPago")) 
				{
					
					ConfirmaSolicitud(Data);
				}
				
 			} else {
 				DataRowExcel("Estado", "Exitoso");
 				DataRowExcel("Oden", "");
 			}
 			
	 		
 		} catch(InterruptedException e){
    		DataRowExcel("Estado", "Fallido");	
    	}					
 	}
 	
 	
	public void ConfirmaSolicitud(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {    	
		String Flujo = Data.get("Flujo");
		
		ecom.TimeSleep(2000);
        WebElement elemento;
        Pattern p = Pattern.compile("[0-9]+$");
        String textoSolicitud = "";
        String Solicitud = "";
        String PagoTarjeta = Data.get("PagoTarjeta");

        try {
        	if(Flujo.equalsIgnoreCase("PrePago")) {		
	 			System.out.println("El método de pago es: " + PagoTarjeta);
//				ecom.Webpay(PagoTarjeta);
			}
	 		
		        if (acciones.FindElementoWaitBoo("NumPedido", Flujo, 30)) {
		            elemento = acciones.FindElementoWait("NumPedido", Flujo, 10);
		            textoSolicitud = elemento.getText();
		            Matcher m = p.matcher(textoSolicitud);
		            while (m.find()){
		            	Solicitud = Solicitud+m.group();
		            }
		            		      	            
		            System.out.println("Confirmacion de compra - Solicitud finalizada "+Solicitud);
		            DataRowExcel("Orden", Solicitud);
		            DataRowExcel("Estado", "Exitoso");
		        }
		        if (Solicitud.equalsIgnoreCase("")) {
		        	DataRowExcel("Estado", "Fallido");
		        }
	        
        } catch(InterruptedException e){
    		DataRowExcel("Estado", "Fallido");
        }
	}
	

	public void DatosVivienda(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {		
 		String ID = Data.get("ID");
 		String Region = Data.get("Region");
 		String Comuna = Data.get("Comuna");	
 		String TipoVivienda = Data.get("TipoVivienda");
 		String Calle = Data.get("Calle");
 		String Numeracion = Data.get("Numeracion");
 		String NumeroInt = Data.get("NumeroInt");
 		String Flujo = Data.get("Flujo");
 		DataRowExcel("Estado", "Fallido");
 		
 		try {
	        //region
	        ecom.OptRegionHogar("selectRegionx", Flujo, "listRegionx", Region);
	        
	        //Comuna 
	        Comuna = ecom.acentoEñe(Comuna);
	        Comuna = (Comuna.toUpperCase()).trim();
	        WebElement dir = acciones.FindElementoWait("inputComuna", Flujo, 5);
	        TimeSleep(800);
	        acciones.InputText(Comuna,"inputComuna",Flujo);
	        dir.sendKeys(Keys.TAB);
	        dir.sendKeys(Keys.TAB);
	        TimeSleep(800); 
	        
		    try {
	        	switch(TipoVivienda){
		        	case "condominio":
		        		acciones.ClickEcom("radioCondominio", Flujo, 10);	
		        		acciones.InputText(Calle,"inputDireccion",Flujo);
		        		acciones.InputText(Numeracion,"inputNumeracion",Flujo);
		        		acciones.InputText(NumeroInt,"inputCasaCondomin",Flujo);
				        break;
					case "casaParticular":
					case "Casa":
						acciones.ClickEcom("radioCasa", Flujo, 10); 
						acciones.InputText(Calle,"inputDireccion",Flujo);
						//acciones.InputText(Numeracion,"inputNumeracion",Flujo);
						System.out.println("casa.......");
						WebElement casa = acciones.FindElementoWait("inputNumeracionxx", Flujo, 5);
						TimeSleep(3000);
						acciones.InputText(Numeracion,"inputNumeracionxx",Flujo);
						TimeSleep(3000);
						casa.sendKeys(Keys.DOWN);TimeSleep(2000);
						casa.sendKeys(Keys.TAB);
						TimeSleep(800); 
						
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
					    break;
		        }
	        	acciones.ClickEcom("foco", Flujo, 10); 
			    TimeSleep(3800);
		        //Next
		        acciones.ClickEcom("btnSiguientex", Flujo, 10);
		    } catch(InterruptedException e){
	    		DataRowExcel("Estado", "Fallido");
	    	}
		} catch(InterruptedException e){
				DataRowExcel("Estado", "Fallido");
		}
 	}
	
	
	public void SelectPlan(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
 		String NombrePlan = Data.get("NombrePlan");
 		String Flujo = Data.get("Flujo"); 
 		
 		try {
 			if(acciones.FindElementoWaitBoo("tagPlanes", Flujo, 30)) {
 				boolean existe = false;
	 	            
 				System.out.println("planes: "+NombrePlan);
 				List<WebElement> planes = acciones.findElementosWait("tituloPlanes",Flujo,10);

				for (int i = 1; i < planes.size();i++) {
					if(planes.get(i).getText().equals(NombrePlan)) {
						i=i+1;
						String xpath = "/html/body/div[8]/div[4]/div[2]/div/div[3]/div[2]/div[2]/div/div/div["+i+"]/article/div[2]/div";
						acciones.Click(xpath);
						existe = true;
						ecom.TimeSleep(5800);
						break;
					}
				}
 			}
	 	} catch(InterruptedException e){ 
	 		DataRowExcel("Estado", "Fallido");
	 	}
 		
 		acciones.ClickEcom("btnSigtePlan", Flujo, 10); 		
 	}
	

	public void ServAdicionales(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {		
 		String ID = Data.get("ID");
 		String Flujo = Data.get("Flujo"); 
 		String TipoFlujo = Data.get("TipoFlujo");
 		String CasoDePrueba = Data.get("CasoDePrueba");
 		String NombrePlan = Data.get("NombrePlan");
 		String Equipo = Data.get("Equipo");
 		
 		NombrePlan = (NombrePlan.toUpperCase()).trim();
 		int i = 0;
 		int flag= 0;
 		String xpath = "";
 		// Recorre Widget
 		try {
 			DataRowExcel("Estado", "Fallido");
 	        if(acciones.FindElementoWaitBoo("tagServicios", Flujo, 10)) {
 	        	ecom.TimeSleep(3000);
 	        
 	        	if(acciones.FindElementoWaitBoo("contenedorServ",Flujo,5)) {
	 	        	List<WebElement> servicios = acciones.findElementosWait("btnSgteServContra",Flujo,10);
//	 	        	List<WebElement> servicios = acciones.findElementosWait("contenedorServ",Flujo,10);
//	 	        	for (i = 0; i < servicios.size()+1; i++) {
//	 	        		if(i==3 || i == 6) {
//	 	        			acciones.ClickEcom("btnSlide", Flujo, 10); //press Widget
	 	        			xpath = "/html/body/div[8]/div[4]/div[2]/div/div[3]/div[2]/div[2]/div/div/div[2]/article/div[2]/div/div";
		                    acciones.ClickXpath(xpath);
		                    
		                    acciones.ClickEcom("btnSgteServContra", Flujo, 5);
		                    ecom.TimeSleep(5000);
		                    
		                    //Prueba agregar un canal adicional SVA
		                	if(TipoFlujo.contains("SVA")) {	
			                    acciones.ClickEcom("btnCanalesAdicionales", Flujo, 5);
			                    System.out.println("Desplego los canales");		                    
			                    acciones.ClickEcom("checkCanalAdicional", Flujo, 5);
			                    ecom.TimeSleep(5000);
		            		}	

	                    

//		                    acciones.ClickEcom("btnSgteServ", Flujo, 5);
	 	        		}
 	        }

 		} catch(InterruptedException e){ 
 	    	DataRowExcel("Estado", "Fallido");
 	    }
 	}
	
	
	public void ResumenSolicitud(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {		
 		String Flujo = Data.get("Flujo");
 		String pedido = null;
 		
 		try {
	 		if (acciones.FindElementoWaitBoo("TagSolicitud",Flujo,30)) {	
	 			System.out.println("Encontró la solicitud");
	 			pedido = ecom.DatosSolicitud("txtPedido", Flujo);
	 			//System.out.println("pedido: "+pedido);
	 			DataRowExcel("Orden", pedido);
	 			DataRowExcel("Estado", "Exitoso");
	 		}
 		} catch(InterruptedException e){
    		DataRowExcel("Estado", "Fallido");
 		}	
 	}


    public void busquedaDeEquipo(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
    	String ID = Data.get("ID");
    	String Equipo = Data.get("Equipo");
    	String Flujo = Data.get("Flujo");
    	String CasoDePrueba = Data.get("CasoDePrueba");
    	
    	int i = 0;
    	String xpath = "";
        ecom.TimeSleep(500);
        String nombreBusqueda = Equipo;
        List<WebElement> datos ;  
        
        acciones.InputText(nombreBusqueda,"inputBuscar",Flujo);
        acciones.ClickEcom("btnBuscar",Flujo,20); 
        Equipo = Equipo.toUpperCase();
        System.out.println("buscando: "+Equipo);

        if(CasoDePrueba.equals("null")) {
			CasoDePrueba = "Flujo completo";
		}
        
        try {
        	String Fecha =  ecom.fechaHora();
        	DataRowExcel("Id", ID);
            DataRowExcel("Flujo", Flujo);
    		DataRowExcel("Fecha", Fecha);
    		DataRowExcel("CasoDePrueba", CasoDePrueba);
    		DataRowExcel("Estado", "Fallido");

    		datos = acciones.findElementosWait("txtNombreEquipo",Flujo,3);
    		List<WebElement> otro = acciones.findElementosWait("otro",Flujo,10);
    		if(acciones.FindElementoWaitBoo("txtNombreEquipo",Flujo,3) ) {	 

		        for ( i = 0; i < datos.size() ;i++){
		        	//System.out.println("Lista: "+datos.get(i).getText().toUpperCase()+" /i: "+i);
					 if((datos.get(i).getText().toUpperCase().trim()).equalsIgnoreCase(Equipo)){
						//System.out.println("encontro: "+datos.get(i).getText()+" i: "+i);
						xpath = "//*[@id='tab1Widget']/div/div[1]/div[2]/ul/li["+(i+1)+"]/div[2]/div[3]/div/a/h3";

						acciones.Click(xpath);
						break;
					}
				}
				
    		}else if(acciones.FindElementoWaitBoo("txtNombreAcce",Flujo,3)) {
					datos = acciones.findElementosWait("txtNombreAcce",Flujo,60);
					for (i = 0; i < datos.size();i++){
//						System.out.println("Entre accesorios...");
//						System.out.println(datos.get(i).getText().toUpperCase());
						if((datos.get(i).getText().toUpperCase()).equals(Equipo)){
//							System.out.println(datos.get(i).getText());
							xpath = "//html/body/div[2]/div[3]/div/div/div[3]/div[3]/div[2]/div/div[1]/div[2]/ul/li["+(i+1)+"]/div[2]/div[2]/div[1]/a/h3";
							acciones.Click(xpath);
							break;
						}
					}
					
			}

//			Pasa al siguiente flujo
			comprarEquipo(Data);
			
        }catch(InterruptedException e) {
    		DataRowExcel("Estado", "Fallido");
        }		
    }
	
    
    public void comprarEquipo(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {       
    	String Rut = Data.get("Rut");
    	String Flujo = Data.get("Flujo");
       
        try{
        	
        	if(acciones.FindElementoWaitBoo("txtTagEquipo",Flujo,1)){
        		acciones.ClickEcom("btnComprarEquipo", Flujo, 10);
        		ecom.TimeSleep(2000);
        		if(acciones.FindElementoWaitBoo("btnContinuarEquipo",Flujo,10)){
        			acciones.ClickEcom("btnContinuarEquipo", Flujo, 10); 
        		}
	        } else {
        		if(acciones.FindElementoWaitBoo("btnComprar",Flujo,1)){
        			acciones.ClickEcom("btnComprar", Flujo, 10);
		            
        		}else{
	                DataRowExcel("Rut", Rut);
	                DataRowExcel("Estado", "Fallido");
           
        		}
        	}
        	//Pasa al siguiente flujo
        	//confirmarEnCarritoDeCompra(Data);
        	FormularioEnvioClte(Data);
        	
        }catch(InterruptedException e) {
        	DataRowExcel("Estado", "Fallido");
        }
    }
    
    
    public void confirmarEnCarritoDeCompra(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException{    	
    	String Flujo = Data.get("Flujo");
        ecom.TimeSleep(3000);
        if(acciones.FindElementoWaitBoo("imgEquipo",Flujo,20)){
            ecom.TimeSleep(500);
            acciones.ClickEcom("btnInvitado", Flujo, 10);
        }
        FormularioEnvioClte(Data);
    }
  
 
    public void SelecFlujoPostpago(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {   	
    	String ID = Data.get("ID");
    	String TipoFlujo = Data.get("TipoFlujo");
    	String Rut = Data.get("Rut");
    	String Telefono = Data.get("Telefono");
    	String Flujo = Data.get("Flujo");
    	String Correo = Data.get("Correo");
    	String CasoDePrueba = Data.get("CasoDePrueba");
	
		System.out.println("Flujo: "+TipoFlujo);
		
		if(CasoDePrueba.equals("null")) {
			CasoDePrueba = "Flujo completo";
		}
	    
		try {	        
			acciones.ClickEcom("inputRut", Flujo, 10);	
			acciones.InputText(Rut,"inputRut",Flujo);
			acciones.ClickEcom("inputNumTelefono", Flujo, 10);	
			acciones.InputText(Telefono,"inputNumTelefono",Flujo);
			acciones.ClickEcom("inputCorreo", Flujo, 10);	
			acciones.InputText(Correo,"inputCorreo",Flujo);
			acciones.ClickEcom("checkTerminos", Flujo, 10);
			ecom.TimeSleep(2000);
			acciones.ClickEcom("checkConsentimiento", Flujo, 10);
			acciones.scrollMonitor(550);
			ecom.TimeSleep(5000);
			String Fecha =  ecom.fechaHora();
			String textoSession = "";
			
		    WebElement session = acciones.FindElementoWait("session", Flujo, 10);
		    textoSession = session.getAttribute("Value");
		    System.out.println("sessionId: "+textoSession);
			
		    DataRowExcel("SessionId", textoSession);
		    DataRowExcel("Id", ID);
	        DataRowExcel("Rut", Rut);
	        DataRowExcel("Flujo", Flujo);
	        DataRowExcel("TipoFlujo", TipoFlujo);
			DataRowExcel("Fecha", Fecha);
			DataRowExcel("CasoDePrueba", CasoDePrueba);
			
			switch(TipoFlujo){
		    	case "Linea Nueva":
		            if (acciones.FindElementoWaitBoo("btnLineaNueva", Flujo, 80)) {
		            	ecom.TimeSleep(3000);
		            	acciones.ClickEcom("btnLineaNueva", Flujo, 10);
		            }
		         
			        break;

				case "Migracion":
		    		if (acciones.FindElementoWaitBoo("btnLineaMigracion", Flujo, 10)) {
			            acciones.ClickXpath("/html/body/div[5]/div[5]/div/div/div[7]/ul/div[2]/div/div[3]/li[2]/div/button[1]");
		            }
		            break;
		            
		    	case "Portabilidad Postpago":
		            if (acciones.FindElementoWaitBoo("btnLineaMiNumero", Flujo, 80)) {
		            	ecom.TimeSleep(3000);
		            	acciones.ClickEcom("btnLineaMiNumero", Flujo, 10);
		            	acciones.FindElementoWaitBoo("btnPostPago", Flujo, 80);
			            ecom.TimeSleep(3000);
			            acciones.ClickEcom("btnPostPago", Flujo, 10);
		            }
		            break;
		            
				case "Portabilidad Prepago":
		            if (acciones.FindElementoWaitBoo("btnLineaMiNumero", Flujo, 80)) {
			            ecom.TimeSleep(3000);
			            acciones.ClickEcom("btnLineaMiNumero", Flujo, 80);            	
		            	acciones.FindElementoWaitBoo("btnPrePago", Flujo, 80);
			            ecom.TimeSleep(3000);
			            acciones.ClickEcom("btnPrePago", Flujo, 10);
		            }
		            break;
		     }
			
			//pasa al siguiente - solo para sanity de Prepago y Postpago
//			SeleccionPlan(Data);
			
		} catch(InterruptedException e){
    		DataRowExcel("Estado", "Fallido");
		}
    }
    
     
    public void SelecFlujoLineaNueva(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {  	
    	String ID = Data.get("ID");
    	String TipoFlujo = Data.get("TipoFlujo");
    	String Rut = Data.get("Rut");
    	String Telefono = Data.get("Telefono");
    	String Flujo = Data.get("Flujo");
    	String Correo = Data.get("Correo");
    	String CasoDePrueba = Data.get("CasoDePrueba");
	
		System.out.println("Flujo: "+TipoFlujo);
		if(CasoDePrueba.equals("null")) {
			CasoDePrueba = "Flujo completo";
		}
	    
		try {
			acciones.ClickEcom("inputRut", Flujo, 10);	
			acciones.InputText(Rut,"inputRut",Flujo);
			acciones.ClickEcom("inputNumTelefono", Flujo, 10);	
			acciones.InputText(Telefono,"inputNumTelefono",Flujo);
			acciones.ClickEcom("inputCorreo", Flujo, 10);	
			acciones.InputText(Correo,"inputCorreo",Flujo);
			acciones.ClickEcom("checkTerminos", Flujo, 10);
			acciones.ClickEcom("checkConsentimiento", Flujo, 10);
			acciones.scrollMonitor(550);
			ecom.TimeSleep(5000);
			String Fecha =  ecom.fechaHora();
			String textoSession = "";
			
		    WebElement session = acciones.FindElementoWait("session", Flujo, 10);
		    textoSession = session.getAttribute("Value");
		    System.out.println("sessionId: "+textoSession);
			
		    DataRowExcel("SessionId", textoSession);
		    DataRowExcel("Id", ID);
	        DataRowExcel("Rut", Rut);
	        DataRowExcel("Flujo", Flujo);
	        DataRowExcel("TipoFlujo", TipoFlujo);
			DataRowExcel("Fecha", Fecha);
			DataRowExcel("CasoDePrueba", CasoDePrueba);
			
			switch(TipoFlujo){
		    	case "Linea Nueva":
		            if (acciones.FindElementoWaitBoo("btnLineaNueva", Flujo, 80)) {
		            	ecom.TimeSleep(3000);
		            	acciones.ClickEcom("btnLineaNueva", Flujo, 10);
		            }
		         
			        break;
				case "Portabilidad Prepago":
		            if (acciones.FindElementoWaitBoo("btnLineaMiNumero", Flujo, 80)) {
			            ecom.TimeSleep(5000);
			            acciones.ClickEcom("btnLineaMiNumero", Flujo, 10);
		            }
		            if (acciones.FindElementoWaitBoo("btnPrePago", Flujo, 80)) {
			            ecom.TimeSleep(5000);
			            acciones.ClickEcom("btnPrePago", Flujo, 10);
		            }
		            break;
				case "Portabilidad Postpago":
		            if (acciones.FindElementoWaitBoo("btnLineaMiNumero", Flujo, 80)) {
			            ecom.TimeSleep(5000);
			            acciones.ClickEcom("btnLineaMiNumero", Flujo, 10);
		            }
		            if (acciones.FindElementoWaitBoo("btnPostPago", Flujo, 80)) {
			            ecom.TimeSleep(5000);
			            acciones.ClickEcom("btnPostPago", Flujo, 10);
		            }
		            break;
				case "Migracion":
		    		if (acciones.FindElementoWaitBoo("btnLineaMigracion", Flujo, 80)) {
			            ecom.TimeSleep(7000);
			            acciones.ClickEcom("btnLineaMigracion", Flujo, 10);
		            }
		            break;
		     }
			
			//pasa al siguiente - solo para sanity de Prepago y Postpago
			SeleccionPlan(Data);
			
		} catch(InterruptedException e){
    		DataRowExcel("Estado", "Fallido");
		}
    }
    
      
    public void SeleccionPlan(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {      
    	String NombrePlan = Data.get("NombrePlan");
    	String Flujo = Data.get("Flujo");
    	NombrePlan = (NombrePlan.toUpperCase()).trim();
    	String xpath = "";
         int art = 1; 
         int i = 0;
         int sec = 1;
         int flag = 0;

        try {
	        if(acciones.FindElementoWaitBoo("tagPlanes", Flujo, 30)) {
	            List<WebElement> planes = acciones.findElementosWait("tituloPlanes",Flujo,10);
	            for (i = 0; i < planes.size(); i++) {
	            	if(i == 4) { 
	            		sec = 2; 
	            		art = 1;	
	            	}
	       
	            	if(((planes.get(i).getText()).toUpperCase()).trim().equals(NombrePlan)) {
	            		//System.out.println("entro...");
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
	        
	        //pasa al siguiente - solo para sanity de Prepago y Postpago
//	        IngresaNumSerie(Data);
	        
        } catch(InterruptedException e){ 
        	DataRowExcel("Estado", "Fallido");
        	init.FinalizarSelenium();
        }	    	
    }
    
    
    public void IngresaNumSerie(Hashtable<String, String> Data) throws InterruptedException, AWTException, IOException {	 
    	String NumSerie = DataIni.GetValue("Datos", "Identificacion", "NumeroSerie");
    	String NumMensaje = DataIni.GetValue("Datos", "Identificacion", "NumeroMensaje");
    	String NumCap = DataIni.GetValue("Datos", "Identificacion", "NumeroCap");
    	String TipoFlujo = Data.get("TipoFlujo");
    	String Flujo = Data.get("Flujo");
	 
	    	try {
	    		acciones.scrollMonitor(500);
		        if (acciones.FindElementoWaitBoo("inputNroSerie",Flujo,20)) {
		        	acciones.ClickEcom("inputNroSerie", Flujo, 10);
		            acciones.InputText(NumSerie,"inputNroSerie",Flujo);
		            ecom.TimeSleep(1500);
		        }  
		        
		        if(TipoFlujo.equals("Portabilidad Prepago") || TipoFlujo.equals("Portabilidad Postpago") || Flujo.equals("Recambio"))	{
		  
			        if (acciones.FindElementoWaitBoo("inputMensaje",Flujo,5)) {
			            acciones.ClickEcom("inputMensaje", Flujo, 5);
			            acciones.InputText(NumMensaje,"inputMensaje",Flujo);
			        }
		        }
		        
		        
		        if(Flujo.equalsIgnoreCase("PostPago") && TipoFlujo.equalsIgnoreCase("Portabilidad Prepago")) {
					System.out.println("El tipo de envío seteado es: " + TipoFlujo);
					acciones.InputText(NumCap,"inputMensaje",Flujo);
					TimeSleep(3000);
				}
		        
		        if(Flujo.equalsIgnoreCase("PostPago") && TipoFlujo.equalsIgnoreCase("Portabilidad Postpago")) {
					System.out.println("El tipo de envío seteado es: " + TipoFlujo);
					acciones.InputText(NumMensaje,"inputMensaje",Flujo);
					TimeSleep(3000);
				}
		        
		        //Hace click en el botón Siguiente para el caso de migración
		        if (acciones.FindElementoWaitBoo("btnSiguiente",Flujo,10)) {
		        	 acciones.ClickEcom("btnSiguiente",Flujo,20);
		        }		      

            
          //pasa al siguiente - solo para sanity de Prepago y Postpago
//          FormularioEnvioClte(Data);
            
    	  }   catch(InterruptedException e){ }
    }
 
    
	 public void DatosFormContratacion(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException {
		 String Nombre = Data.get("Nombre");
		 String Apellidos = Data.get("Apellidos");
		 String FonoOtraPers = Data.get("FonoOtraPers");
		 String Flujo = Data.get("Flujo");
		 String NumSerie = DataIni.GetValue("Datos", "Identificacion", "NumeroSerie");		 
		 
		 	String textoSession = "";
		    WebElement session = acciones.FindElementoWait("sessionHogar", Flujo, 10);
		    textoSession = session.getAttribute("Value");
		    System.out.println("sessionId - Hogar: "+textoSession);
		    DataRowExcel("SessionId", textoSession);
		    
			acciones.ClickEcom("inputCarnetHogar", Flujo, 10);
			acciones.clearText("inputCarnetHogar", Flujo);
			acciones.InputText(NumSerie,"inputCarnetHogar",Flujo);
			
			acciones.ClickEcom("inputNombreHogar", Flujo, 30);
			acciones.clearText("inputNombreHogar", Flujo);
			acciones.InputText(Nombre, "inputNombreHogar", Flujo);
			
			acciones.ClickEcom("inputApellidosHogar", Flujo, 10);
			acciones.clearText("inputApellidosHogar", Flujo);
			acciones.InputText(Apellidos, "inputApellidosHogar", Flujo);
			
			if(FonoOtraPers.equalsIgnoreCase("null"))	{
				acciones.scrollMonitor(500);
				ecom.TimeSleep(500);
			}
			else { 
				acciones.ClickEcom("inputNumeroHogar", Flujo, 10);	
				acciones.clearText("inputNumeroHogar", Flujo);
				acciones.InputText(FonoOtraPers, "inputNumeroHogar", Flujo);
			}

			
	 		Calendar calendar = Calendar.getInstance();
	 		int semana = calendar.get(Calendar.WEEK_OF_MONTH);
	 		int dia = calendar.get(Calendar.DAY_OF_WEEK );
	 		acciones.scrollMonitor(500);
	 		System.out.println("La semana es: "+semana);
	 		System.out.println("El dia es: "+dia);
	 		ecom.TimeSleep(500);
	 		String xpath =  "/html/body/div[1]/div[11]/div/div[4]/div[1]/div[1]/div/form[1]/div[2]/div[12]/div/fieldset/div/ul/li[1]/div/div[1]/table/tbody/tr["+(semana+1)+"]/td["+(dia)+"]";
	 		acciones.ClickXpath(xpath);
	 		
	 		acciones.ClickEcom("radioAm", Flujo, 10);
		 	ecom.TimeSleep(500);	
			acciones.ClickEcom("btnFinalizarHogar", Flujo, 10);			
		}
	 

	public Date sumarDiasFechas(Date fecha, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); 
		calendar.add(Calendar.DAY_OF_YEAR, dias);  
		return calendar.getTime(); 
	}

	
	public void LlenaExcelSalida(String Id, String CasoDePrueba, String Modulo, String Valor, String Estado) throws AWTException {
		DataRowExcel("CasoDePrueba", CasoDePrueba);
    	DataRowExcel("Observaciones", Modulo);
    	DataRowExcel("Valor", Valor);
    	DataRowExcel("Estado", Estado );
    	Screen.Capturar("", Id);
    }
	
	
	public void Calendario(String Flujo) throws InterruptedException, IOException, AWTException {
 		Calendar calendar = Calendar.getInstance();
 		int semana = calendar.get(Calendar.WEEK_OF_MONTH);
 		int dia = calendar.get(Calendar.DAY_OF_WEEK );
 		acciones.scrollMonitor(500);
 		//System.out.println("la semana es: "+semana);
 		//System.out.println("El día es: "+dia);
 		ecom.TimeSleep(1000);
 		String xpath =  
 				"/html/body/div[1]/div[11]/div/div[4]/div[1]/div[1]/div/form[1]/div[2]/div[12]/div/fieldset/div/ul/li[1]/div/div[1]/table/tbody/tr["+semana+"]/td["+dia+"]";
 		acciones.ClickXpath(xpath);
 		acciones.ClickEcom("radioAm", Flujo, 10);
	 	ecom.TimeSleep(500);		
	}

	
	public Date sumarDiasFecha(Date fecha, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); 
		calendar.add(Calendar.DAY_OF_YEAR, dias);  
		return calendar.getTime(); 	
	}
	
	
	public void ServiciosAdic(String servicio) {
		//Se debe agregar la funcionalidad de agregar 
		//los dispositivos como extensores, moden, etc.
	}
	
	
	public void cierreTemp() {
		System.out.println("Finalizo correctamente");
		DataRowExcel("Estado", "Exitoso" );
	}
	
	
	public void seleccionaTipoEnvio(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException{		
 		String Flujo = Data.get("Flujo");
 		String TipoEnvio = Data.get("TipoEnvio"); 
 		String PagoTarjeta = Data.get("PagoTarjeta");
 		
		if(Flujo.equalsIgnoreCase("PostPago") && TipoEnvio.equalsIgnoreCase("Normal") || Flujo.equalsIgnoreCase("LineaNuevaEquipo") || Flujo.equalsIgnoreCase("Recambio")) {			
			System.out.println("El tipo de envío seteado es: " + TipoEnvio);
			TimeSleep(5000);
			acciones.ClickEcom("radioBtnNormal", Flujo, 10);
			acciones.ClickEcom("SiguienteOtraPer", Flujo, 10);
			TimeSleep(1000);
			}
		
		if(Flujo.equalsIgnoreCase("PostPago") && TipoEnvio.equalsIgnoreCase("Express")) {
			System.out.println("El tipo de envío seteado es: " + TipoEnvio);
			TimeSleep(8000);
			acciones.ClickEcom("radioBtnExpress", Flujo, 10);
			acciones.ClickEcom("SiguienteOtraPer", Flujo, 10);
			TimeSleep(1000);
			}
		
		if(Flujo.equalsIgnoreCase("PostPago")) {
	 		ecom.TimeSleep(3000);
			//Siguiente
	 		acciones.FindElementoWaitBoo("XSiguienteOtraPer", Flujo, 5);
			acciones.ClickEcom("XSiguienteOtraPer", Flujo, 10);
			}
		
		if(Flujo.equalsIgnoreCase("Recambio")) {
	 		ecom.TimeSleep(3000);
			acciones.ClickEcom("checkTerminos", Flujo, 10);
			acciones.ClickEcom("SiguienteOtraPerFac", Flujo, 10);
			ecom.TimeSleep(10000);
//			ecom.Webpay(PagoTarjeta);
			}
		
	}
	
    public void InicioLNE(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException{
    	
    	driver = InitSelenium.getDriver();   	
    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.MAX_VALUE));
    	
    	String Flujo = Data.get("Flujo");
    	String Rut  = Data.get("Rut");
    	String Telefono  = Data.get("Telefono");
		String Correo  = Data.get("Correo");
		String NumSerie = DataIni.GetValue("Datos", "Identificacion", "NumeroSerie");
    
        if(Flujo.equals("LineaNuevaEquipo")) {       	
        	System.out.println("Entró por el flujo de Linea Nueva" + Flujo);
        	
        	Thread.sleep(8000);
      
        	WebElement boton = driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[3]/div[3]/div[2]/div/div[3]/div[1]/a"));
        	boton.isEnabled();
        	boton.click();
        	     	
        	acciones.ClickXpath("//*[@id='rut']"); 
        	acciones.InputText(Rut,"inputRut","PostPago");
        	System.out.println("Ingresó el rut");
            
        	acciones.ClickXpath("//*[@id='telefono']"); 
        	acciones.InputText(Telefono,"inputNumTelefono","PostPago");
        	System.out.println("Ingresó el teléfono");
        	
        	acciones.ClickXpath("//*[@id='correo']"); 
        	acciones.InputText(Correo,"inputCorreo","PostPago");
        	System.out.println("Ingresó el correo");
        	
        	acciones.ClickXpath("//*[@id='checkbox']"); 
        	System.out.println("Marcó el checkbox");
        	Thread.sleep(5000);
        	
        	acciones.ClickXpath("/html/body/div[5]/div[5]/div/div/div[7]/ul/div[2]/div/div[4]/li[2]/div/button[2]");
        	
        	Thread.sleep(5000);
        	
        	WebElement nroSerie = driver.findElement(By.xpath("//*[@id='carnetIdentidad']"));
//        	nroSerie.isEnabled();
        	nroSerie.click();
        	nroSerie.sendKeys(NumSerie);
        	nroSerie.sendKeys(Key.TAB);
        	System.out.println("Ingresó el nro de serie");
        	
        	acciones.ClickXpath("/html/body/div[8]/div/div[3]/ul/li[3]/button"); 
        	Thread.sleep(5000);
        	
        	
	        if (acciones.FindElementoWaitBoo("Autentikar",Flujo,10)) {
	        	acciones.ClickEcom("Autentikar",Flujo,20);
	        	}
        	      
//	        if (acciones.FindElementoWaitBoo("btnSiguiente",Flujo,10)) {
//	        	 acciones.ClickEcom("btnSiguiente",Flujo,20);
//	        }
	        
//        	WebElement autentikar = driver.findElement(By.xpath("//*[@id='liveness-button']"));
//        	autentikar.isEnabled();
//        	autentikar.click();
//          System.out.println("Hizo el click de Autentikar");
//          Thread.sleep(30000);	
	        
        }     
    }
	
    
    public void LineaNuevaEquipo(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException{    	    
    	
    	String Flujo = Data.get("Flujo");
    	String TipoFlujo = Data.get("TipoFlujo");
    	String CasoDePrueba = Data.get("CasoDePrueba");
    	String PagoTarjeta = Data.get("PagoTarjeta");
	
		System.out.println("Flujox: "+TipoFlujo);
				
		if(Flujo.equals("LineaNuevaEquipo") && CasoDePrueba.equals("null")) {
			InicioLNE(Data);
			FormularioEnvioClte(Data);
			seleccionaTipoEnvio(Data);			
			acciones.ClickEcom("SiguienteOtraPerPrueba", Flujo, 10);
			ecom.TimeSleep(10000);
//			ecom.Webpay(PagoTarjeta);
		}
    }
    
    public void inicioRecambio(Hashtable<String, String> Data) throws InterruptedException, IOException, AWTException{  
    	String Flujo = Data.get("Flujo");
    	String TipoFlujo = Data.get("TipoFlujo");

			
//		if(TipoFlujo.equals("Tarjeta")) {
//			System.out.println("Está entrando a la condición" + TipoFlujo);
//			acciones.FindElementoWaitBoo("financiaTarjetax",Flujo,10);
//			acciones.ClickEcom("financiaTarjetax", Flujo, 10);
//		}
//		
//		if(TipoFlujo.equals("Boleta")) {
//			System.out.println("Está entrando a la condición" + TipoFlujo);
//			acciones.FindElementoWaitBoo("financiaBoletax",Flujo,10);
//			acciones.ClickEcom("financiaBoletax", Flujo, 10);
//		}
		
			acciones.FindElementoWaitBoo("btnContinuarCarrito",Flujo,10);
			acciones.ClickEcom("btnContinuarCarrito", Flujo, 10);
		
    
	if(TipoFlujo.equals("Tarjeta")) {
		FormularioEnvioClte(Data);
		}
	else
		FormularioEnvioClte(Data);
		
    }	    
}