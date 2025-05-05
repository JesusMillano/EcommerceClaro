package Business;


import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import Core.Selenium.AccionesWeb;
import Core.Selenium.InitSelenium;
import Core.Xml.LeerPasos;
import Core.Excel.ExcelReader;

public class Ecommers {
	
	public static WebDriver driver = null;
	static String [] DataEcommerce={"","","","","","","","","","","","","","",""};
    private String nombreExcel="DataEcommerce";
    private Core.Excel.AccionesExcel excel = new Core.Excel.AccionesExcel();
    private Core.Ini.LeerData DataIni = new Core.Ini.LeerData();
    private AccionesWeb acciones = new AccionesWeb();
    private LeerPasos xml = new LeerPasos();


//    private ExcelReader externalData = new ExcelReader();
    String Archivo = DataIni.GetValue("Datos", "Rutas", "ArchPrepago");
  

    public void IniciarEcomm
(String Flujo, Hashtable<String, String> Data) throws IOException, UnsupportedFlavorException, FindFailed, InterruptedException {	
		String Url = "";
		String StepXml = "";
		Core.Selenium.InitSelenium.getDriver();
		InitSelenium init = new InitSelenium();
		String TipoFlujo = Data.get("TipoFlujo");
		
		switch (Flujo){
		case "Prepago":		
			Url		= "UrlPrepago2";
			StepXml = "EcommerceGlobales";
			break;
		
		case "PostPago":
			if(TipoFlujo.equals("Portabilidad Prepago") || 
					TipoFlujo.equals("Portabilidad Postpago") || 
					TipoFlujo.equals("Migracion")) {				
				Url		= "UrlPostpago";
				StepXml = "EcommerceGlobales";
				}
			if(TipoFlujo.equals("Linea Nueva")) {	
				Url		= "UrlLineaNueva";
				StepXml = "EcommerceGlobales";
				}
			if(TipoFlujo.equals("Linea Nueva Equipo")) {	
				Url		= "UrlLineaNuevaEquipo";
				StepXml = "EcommerceGlobales";
				}
			if(TipoFlujo.equals("Portabilidad Prepago Equipo")) {	
				Url		= "UrlPortabilidadEquipo";
				StepXml = "EcommerceGlobales";
				}
			if(TipoFlujo.equals("Portabilidad Postpago Equipo")) {	
				Url		= "UrlPortabilidadEquipo";
				StepXml = "EcommerceGlobales";
				}
			if(TipoFlujo.contains("Hogar")) {
				Url		= "UrlHogar";
				StepXml = "EcommerceGlobales";
				}			
			if(TipoFlujo.equals("Recambio Tarjeta")) {
				Url		= "UrlRecambioTarjeta";
				StepXml = "EcommerceGlobales";
				}			
			if(TipoFlujo.equals("Recambio Boleta")) {
				Url		= "UrlRecambioBoleta";
				StepXml = "EcommerceGlobales";
				}
			if(TipoFlujo.equals("Linea Nueva EP")) {	
				Url		= "UrlLineaNueva";
				StepXml = "EcommerceGlobales";
				}
			break;		}

				
			List<String> url = xml.getxmlArch(Url, StepXml);
			
			init.IniciarSelenium();
		
			InitSelenium.getDriver().manage().window().maximize();
			InitSelenium.getDriver().get(url.get(1));
			}

    public void excelEcommerce
(String datos[]) throws InterruptedException {

        if(!excel.ExisteExcel(nombreExcel))
        {
            String [] CabeceraAmdocs= {"Rut","Nombre","Direccion","Numero Pedido","SKU Equipo","Plan / Servicio / Equipo Adquirido", "Observaciones", "Estado"};
            excel.CrearNuevoExcel(nombreExcel, "Data", CabeceraAmdocs);
        }

        excel.AgregarLineaExcel(nombreExcel, datos);

    }

    public void TimeSleep
(int Time) {
        try
        {Thread.sleep(Time);}
        catch(InterruptedException e)
        {}
    }

    public void DataRowExcel
(String Key, String Value) {
    	switch(Key)
    	{
       		case "Id"        							:DataEcommerce[0]	=Value;	break;
    		case "Flujo"       							:DataEcommerce[1]	=Value;	break;
    		case "TipoFlujo"       				    	:DataEcommerce[2]	=Value;	break;
    		case "CasoDePrueba"    						:DataEcommerce[3]	=Value;	break;
    		case "Fecha"     							:DataEcommerce[4]	=Value;	break;
    		case "Nombre"     							:DataEcommerce[5]	=Value;	break;
    		case "Apellido"     						:DataEcommerce[6]	=Value;	break;
    		case "Rut"     								:DataEcommerce[7]	=Value;	break;
    		case "Telefono"     						:DataEcommerce[8]	=Value;	break;
    		case "Orden"     							:DataEcommerce[9]	=Value;	break;
    		case "SessionId"     						:DataEcommerce[10]	=Value;	break;
    		case "TipoProducto"     					:DataEcommerce[11]	=Value;	break;
    		case "TipoEnvio"     						:DataEcommerce[12]	=Value;	break;
    		case "TipoPago"     						:DataEcommerce[13]	=Value;	break;
    		case "Estado"     							:DataEcommerce[14]	=Value;	break;  		
    	}    	
    }
    
    public void LimpiarDataRowExcel
    () {
			DataEcommerce[1]="";
			DataEcommerce[2]="";
			DataEcommerce[3]="";
			DataEcommerce[4]="";
			DataEcommerce[5]="";
			DataEcommerce[6]="";
			DataEcommerce[7]="";
			DataEcommerce[8]="";
			DataEcommerce[9]="";
			DataEcommerce[10]="";
			DataEcommerce[11]="";
			DataEcommerce[12]="";
			DataEcommerce[13]="";
			DataEcommerce[14]="";
			DataEcommerce[15]="";
			DataEcommerce[16]="";
			DataEcommerce[17]="";
			DataEcommerce[18]="";
			DataEcommerce[19]="";
			DataEcommerce[20]="";
			DataEcommerce[21]="";
			DataEcommerce[22]="";
			DataEcommerce[23]="";
			DataEcommerce[24]="";
			DataEcommerce[25]="";
			DataEcommerce[26]="";
			DataEcommerce[27]="";
			DataEcommerce[28]="";
			DataEcommerce[29]="";
			DataEcommerce[30]="";
			DataEcommerce[31]="";
			DataEcommerce[32]="";
			DataEcommerce[33]="";
			DataEcommerce[34]="";
			DataEcommerce[35]="";
    }

    public void Webpayx
(String Pago) throws InterruptedException, IOException, AWTException {  	
    		String MedioPago 		= DataIni.GetValue("Datos", Pago, "MedioPago");
    		String FechaVenc  		= DataIni.GetValue("Datos", Pago, "FechaVenc");
    		String CodValidacion 	= DataIni.GetValue("Datos", Pago, "CodValidacion");
    		String NumTarjeta 		= DataIni.GetValue("Datos", Pago, "NumTarjeta");
    		String RutTransbank 	= DataIni.GetValue("Datos", Pago, "RutTransbank");
    		String ClaveTransbank 	= DataIni.GetValue("Datos", Pago, "ClaveTransbank");
    		
    		
		  if(MedioPago.equals("DEBITO") ) {	  
			  TimeSleep(5000);
			  System.out.println("Entró al if "+MedioPago);	
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
		
		  
		  if(MedioPago.equals("DEBITO") ) {
			  acciones.ClickAmdoc("selectBancoDeb","EcommerceGlobales",10);
	        	acciones.ClickAmdoc("opBancoDeb","EcommerceGlobales",10);
	            TimeSleep(1000);
	            acciones.ClickAmdoc("optionPanel","EcommerceGlobales",10);  
	            TimeSleep(1000); 
		  }
		  else{
			  
			  if(acciones.FindElementoWaitBoo("selectBanco", "EcommerceGlobales", 5)) {	
	        	acciones.ClickAmdoc("selectBanco","EcommerceGlobales",10);
	        	acciones.ClickAmdoc("opBanco","EcommerceGlobales",10);
	            TimeSleep(500);
	            acciones.ClickAmdoc("optionPanel","EcommerceGlobales",10);  
	            TimeSleep(500); 
			  }
		  }

        if(MedioPago.equals("DEBITO") ) {	
        	acciones.ClickAmdoc("txtNumTarjetaDebito","EcommerceGlobales",10);
            acciones.InputText(NumTarjeta,"txtNumTarjetaDebito", "EcommerceGlobales");
            acciones.ClickAmdoc("btnContinuarWebPayDeb", "EcommerceGlobales", 10);                     
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
            acciones.InputText(CodValidacion,"opCvvCredito", "EcommerceGlobales");   //quitar comillas codVal
            
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
    
	public void OptRegion
(String Select, String Plataforma, String Lista, String Region) throws InterruptedException, IOException {
		    acciones.ClickEcom(Select,Plataforma,10);
	        List<WebElement> Reg = acciones.findElementosWait(Lista,Plataforma,10);
	
	        TimeSleep(3000);
	        
	        for (int i = 0; i < Reg.size();i++) {
	        	//System.out.println("Region i:"+i + " -region text:"+Reg.get(i).getText()+" -value:"+Reg.get(i).getAttribute("Value"));
	        	
	        	String RegTrim = Reg.get(i).getText().trim();
	        	//System.out.println("Region: "+RegTrim);
	        	if(RegTrim.equals(Region)) {
	        		String xpathRegion = "//*[@id='selectState']/option["+(i+1)+"]";
	        		//String xpathRegion = "/html/body//div[19]/select/option["+(i+1)+"]";
	        		//String xpathRegion = "/html/body/div[19]/select/option["+Reg.get(i).getAttribute("Value")+"]";
	            	acciones.ClickXpath(xpathRegion);	

	                break;
	            }
	        }	       
	}
	
	public void OptComuna
(String Select, String Plataforma, String Lista, String Comuna) throws InterruptedException, IOException {	
	    acciones.ClickEcom(Select,Plataforma,10);
        List<WebElement> Com = acciones.findElementosWait(Lista,Plataforma,10);
        TimeSleep(2000);
        
        for (int i = 0; i < Com.size();i++) {        	
        	String ComTrim = Com.get(i).getText().trim();
        	if(ComTrim.equals(Comuna)) {
//        		String xpathComuna = "/html/body/div[1]/div[4]/div/div/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/form/div[9]/div[2]/div/div[2]/div/select/option["+(i+1)+"]";
        		String xpathComuna = "//*[@id=\"city\"]/option["+(i+1)+"]";         	
//        		String xpathComuna = "/html/body/div[1]/div[4]/div/div/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/form/div[9]/div[2]/div/div[2]/div/select/option[12]";        		
            	acciones.ClickXpath(xpathComuna);	
                break;
            }
        }  
	}
	
	public void OptRegionHogar
(String Select, String Plataforma, String Lista, String Region) throws InterruptedException, IOException {	
	    acciones.ClickEcom(Select,Plataforma,10);
        List<WebElement> Reg = acciones.findElementosWait(Lista,Plataforma,10);
        
        int x = 1;
        for (int i = 0; i < Reg.size();i++) {
        	
        	//System.out.println("Region: "+Region + " - region pagin :"+Reg.get(i).getText());
        	if(Reg.get(i).getText().equals(Region)) {		
            	String xpathRegion = "//*[@id=\"WC_formularioFactura_AddressEntryForm_FormInput_state_1-menu\"]/li["+x+"]";
//            	String xpathRegion = "/html/body/div[10]/ul/li["+x+"]";
            	acciones.ClickXpath(xpathRegion);	
                break;
            }
        	x = x + 1;
        }      
}
	
	public void OptCalle
(String Opcion, String Plataforma, String Calle) throws InterruptedException {		
        acciones.InputText(Calle,Opcion,Plataforma);
        TimeSleep(3000);
//        WebElement dir = acciones.FindElementoWait(Opcion, Plataforma, 5);
//        TimeSleep(800);
//        dir.sendKeys(Keys.DOWN);
//        TimeSleep(800);
//    	dir.sendKeys(Keys.TAB);

	}
	
	public String DatosSolicitud
(String Opcion, String Plataforma) throws InterruptedException {
		WebElement tagSolicitud;
		String Dato = null;
		Pattern p = Pattern.compile("[0-9]+$");
		String valor = "";		
		TimeSleep(1800);
		
		tagSolicitud = acciones.FindElementoWait(Opcion,Plataforma, 10);
		
	    Matcher m = p.matcher(tagSolicitud.getText());
	    while (m.find()){
	    	Dato = valor+m.group();
	    }
	    return Dato;
	}
	
	public  String acentoEñe
(String palabra) {
	    if (palabra == null) {
	        return null;
	    }
		String ORIGINAL = "ÁáÉéÍíÓóÚúÑñÜü";
		String REPLACEMENT = "AaEeIiOoUuNnUu";
	    char[] valor = palabra.toCharArray();
	    for (int i = 0; i < valor.length; i++) {
	        int pos = ORIGINAL.indexOf(valor[i]);
	        if (pos > -1) {
	            valor[i] = REPLACEMENT.charAt(pos);
	        }
	    }
	    return new String(valor);
	}
	
	public String fechaHora
() {		
	//	DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	//    System.out.println("yyyy/MM/dd HH:mm-> "+dtf4.format(LocalDateTime.now()));
		Date fecha = new Date();
		String Setfecha = "dd-MMM-yyyy hh:mm";  
        SimpleDateFormat objSDF = new SimpleDateFormat(Setfecha, new Locale("es_ES"));
        String valor = objSDF.format(fecha);
        
	    return valor;	    
		}	
	
}