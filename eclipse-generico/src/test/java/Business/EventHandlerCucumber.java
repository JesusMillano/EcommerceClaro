package Business;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.apache.poi.util.SystemOutLogger;
//import org.apache.tools.ant.taskdefs.optional.Script;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;


import Core.Cucumber.Runner;
import Core.Selenium.AccionesWeb;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import Script.Ejecuciones.ECOMMERCE;



@SuppressWarnings("unused")
public class EventHandlerCucumber extends Runner {
	private Scenario MiEscenario;
	String [] DataEcommerce  = Business.Ecommers.DataEcommerce;

	@Before()
	public void inicia(Scenario scenario)
	{
		MiEscenario =scenario;

	}

	@After()
	public void Termina(Scenario scenario) throws InterruptedException, IOException {
		generateHTMLReports();
		Thread.sleep(1000);
		quit();

	}

	@Entonces("Capturar Pantralla")
	public void TakeScreenshot() throws AWTException, IOException
	{
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		try {
			
			BufferedImage capture = new Robot().createScreenCapture(screenRect);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( capture, "png", baos );
			baos.flush();
		    byte[] screenshot = baos.toByteArray();
			MiEscenario.embed(screenshot, "image/png");  // Stick it in the report
			baos.close();
       } catch (WebDriverException somePlatformsDontSupportScreenshots) {
       		}
	}
		
	public void RunTestExcel() throws AWTException
	{	
        Core.ScreenShot.ScreenShot Screen = new Core.ScreenShot.ScreenShot();         
        Ecommers ecommerce= new Ecommers();        
		Core.Excel.AccionesExcel Excel = new Core.Excel.AccionesExcel();
		Date fecha = new Date();
        String Setfecha = "dd-MMM-yyyy hh:mm:ss";  
        SimpleDateFormat objSDF = new SimpleDateFormat(Setfecha, new Locale("es_ES"));
        String NombreExcel = objSDF.format(fecha);
        NombreExcel ="ReporteEcom "+NombreExcel.replace(":", "."); 
        
        if(!Excel.ExisteExcel(NombreExcel))
		{
        	//String [] CabeceraEcom= {"Id","Flujo","Caso de prueba","Observaciones","Valor","Estado" };
        	String [] CabeceraEcom= {"Id", "Flujo", "TipoFlujo", "CasoDePrueba", "Fecha", "Nombre", "Apellido", "Rut", "Telefono", "Orden", "SessionId","TipoProducto", "TipoEnvio", "TipoPago", "Estado"};
        	Excel.CrearNuevoExcel(NombreExcel, "Resultado", CabeceraEcom);
		}
	  
		try
		{
			ECOMMERCE EjecEcom = new ECOMMERCE();
			
			ArrayList<ObjExcel> objExcelList =Excel.CountRow("Datos");
			 
			int SizeList=objExcelList.size();
			for(int i=1;i<objExcelList.size();i++)
			{
				try
				{
					 Hashtable<String, String> Data = 
			                 new Hashtable<String, String>(); 
					 
					 if(objExcelList.get(i).getIdEjecucion()!=null)
						 	 Data.put("IdEjecucion",   		objExcelList.get(i).getIdEjecucion());	
					 
					 if(objExcelList.get(i).getID()!=null)
							 Data.put("ID",   				objExcelList.get(i).getID());
						
					 if(objExcelList.get(i).getFlujo()!=null)
							 Data.put("Flujo",   			objExcelList.get(i).getFlujo());
						
					 if(objExcelList.get(i).getTipoFlujo()!=null)
							 Data.put("TipoFlujo",   		objExcelList.get(i).getTipoFlujo());
						
					 if(objExcelList.get(i).getCasoDePrueba()!=null)
							 Data.put("CasoDePrueba",   	objExcelList.get(i).getCasoDePrueba()); 
						
					 if(objExcelList.get(i).getRut()!=null)
							 Data.put("Rut",   				objExcelList.get(i).getRut());
						
					 if(objExcelList.get(i).getNombre()!=null)
							 Data.put("Nombre", 			objExcelList.get(i).getNombre());
						
					 if(objExcelList.get(i).getApellidos()!=null)
							 Data.put("Apellidos",   		objExcelList.get(i).getApellidos());
						
					 if(objExcelList.get(i).getCorreo()!=null)
							 Data.put("Correo", 			objExcelList.get(i).getCorreo());
						
					 if(objExcelList.get(i).getTelefono()!=null)
							 Data.put("Telefono",   		objExcelList.get(i).getTelefono());
						
					 if(objExcelList.get(i).getCheck()!=null)
							 Data.put("Check",   			objExcelList.get(i).getCheck());
						
					 if(objExcelList.get(i).getDia()!=null)
							 Data.put("Dia", 				objExcelList.get(i).getDia());
						
					 if(objExcelList.get(i).getMes()!=null)
							 Data.put("Mes",   				objExcelList.get(i).getMes());	 
						
					 if(objExcelList.get(i).getAnio()!=null)
							 Data.put("Anio", 				objExcelList.get(i).getAnio());					
						
					 if(objExcelList.get(i).getFonoDatPersonal()!=null)
							 Data.put("FonoDatPersonal", 	objExcelList.get(i).getFonoDatPersonal());
						
					 if(objExcelList.get(i).getCorreoDatPersonal()!=null)
							 Data.put("CorreoDatPersonal", 	objExcelList.get(i).getCorreoDatPersonal());
						
					 if(objExcelList.get(i).getRegion()!=null)
							 Data.put("Region",   			objExcelList.get(i).getRegion());	 
						
					 if(objExcelList.get(i).getComuna()!=null)
							 Data.put("Comuna", 			objExcelList.get(i).getComuna()); 
						
					 if(objExcelList.get(i).getCalle()!=null)
							 Data.put("Calle", 				objExcelList.get(i).getCalle());
						
					 if(objExcelList.get(i).getNumeracion()!=null)
							 Data.put("Numeracion", 		objExcelList.get(i).getNumeracion());
						
					 if(objExcelList.get(i).getTipoVivienda()!=null)
							 Data.put("TipoVivienda", 		objExcelList.get(i).getTipoVivienda());
						
					 if(objExcelList.get(i).getNumeroInt()!=null)
							 Data.put("NumeroInt", 			objExcelList.get(i).getNumeroInt());
						
					 if(objExcelList.get(i).getNombrePlan()!=null)
							 Data.put("NombrePlan", 		objExcelList.get(i).getNombrePlan());
						
					 if(objExcelList.get(i).getEquipo()!=null)
							 Data.put("Equipo", 			objExcelList.get(i).getEquipo());
						
					 if(objExcelList.get(i).getColor()!=null)
							 Data.put("Color", 				objExcelList.get(i).getColor());
						
					 if(objExcelList.get(i).getSkuEquipo()!=null)
							 Data.put("SkuEquipo", 			objExcelList.get(i).getSkuEquipo());
						
					 if(objExcelList.get(i).getQuienRecibe()!=null)
							 Data.put("QuienRecibe", 		objExcelList.get(i).getQuienRecibe());						
						
					 if(objExcelList.get(i).getRutOtraPers()!=null)
							 Data.put("RutOtraPers", 		objExcelList.get(i).getRutOtraPers());					
						
					 if(objExcelList.get(i).getNomOtraPers()!=null)
							 Data.put("NomOtraPers", 		objExcelList.get(i).getNomOtraPers());						
						
					 if(objExcelList.get(i).getApellOtraPers()!=null)
							 Data.put("ApellOtraPers", 		objExcelList.get(i).getApellOtraPers());						
						
					 if(objExcelList.get(i).getFonoOtraPers()!=null)
							 Data.put("FonoOtraPers", 		objExcelList.get(i).getFonoOtraPers());						
						
					 if(objExcelList.get(i).getPagoTarjeta()!=null)
							 Data.put("PagoTarjeta", 		objExcelList.get(i).getPagoTarjeta());						
						
					 if(objExcelList.get(i).getTipoEnvio()!=null)
							 Data.put("TipoEnvio", 			objExcelList.get(i).getTipoEnvio());
					
					System.out.println("Ejecutando caso "+objExcelList.get(i).getTipoFlujo());
					System.out.println("Caso "+ i +" De.... "+ (SizeList-1));
					
					try
					{
						ecommerce.DataRowExcel("Id", objExcelList.get(i).getID());
						switch (objExcelList.get(i).getTipoFlujo()){
						
					
						case "Prepago":
							EjecEcom.Prepago(Data);
							break;
							
						case "Linea Nueva":
							EjecEcom.LineaNueva(Data);
							break;
							
						case "Linea Nueva Equipo":
							EjecEcom.LineaNuevaEquipo(Data);
							break;
													
						case "Portabilidad Prepago":
							EjecEcom.Portabilidad(Data);
							break;
							
						case "Portabilidad Postpago":
							EjecEcom.Portabilidad(Data);
							break;
							
						case "Portabilidad Prepago Equipo":
							EjecEcom.PortabilidadEquipo(Data);
							break;
							
						case "Portabilidad Postpago Equipo":
							EjecEcom.PortabilidadEquipo(Data);
							break;						
							
						case "Migracion":
							EjecEcom.Migracion(Data);
							break;								
													
						case "Hogar":
							EjecEcom.Hogar(Data);
							break;		
							
						case "Hogar SVA":
							EjecEcom.HogarSVA(Data);
							break;	

						case "Recambio Tarjeta":
							EjecEcom.Recambio(Data);
							break;
							
						case "Linea Nueva EP":
							EjecEcom.LineaNuevaEP(Data);
							break;
							
//						case "PortabilidadEquipo":
//							EjecEcom.Postpago(Data);
//							break;

								
						}
					}
					catch(Exception e)
					{}
					finally
					{
						Screen.Capturar("", objExcelList.get(i).getID());
						Core.Excel.AccionesExcel excel = new Core.Excel.AccionesExcel();
						System.out.println(DataEcommerce[0]);
						System.out.println(NombreExcel);
						excel.AgregarLineaExcel(NombreExcel, DataEcommerce);
						//aqui
						
					}
					 if((SizeList-1) != i) {
						 Cerrar();
					 }
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
					TakeScreenshot();
				}
			}
		}
		catch(Exception e)
		{}		
	}
		
}