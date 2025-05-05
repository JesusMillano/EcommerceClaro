package Core.Selenium;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import Core.Operaciones.OpcionesEjecucion;


public class InitSelenium {
    private static ThreadLocal<WebDriver> Driver = new ThreadLocal<WebDriver>();
    
    private static String oldHandle;
	private static String newHandle;
	
	public static String getNewHandle() {
		return newHandle;
	}
    
	public static void setNewHandle(String newHandle) {
		InitSelenium.newHandle = newHandle;
	}
	
	public static String getOldHandle() {
		return oldHandle;
	}
    
	public static void setOldHandle(String oldHandle) {
		InitSelenium.oldHandle = oldHandle;
	}
      
    public InitSelenium(){

    }
    
    /**
    *
    * Devolvemos el driver solicitado para selenium.
    * @return Selenium webdriver. 
    */        
    public static WebDriver getDriver(){
    	return Driver.get();
    }
    
    /**
    *
    * Iniciamos la ejecucion de las pruebas y la navegacion. 
    */        
    public void IniciarSelenium() throws IOException {
    	try
    	{
        // Obtenemos el nombre del sistema operativo actual.
        String SistemaOperativo = System.getProperty("os.name");
        System.out.println("Sistema Operativo detectado: " + SistemaOperativo);
        
        // Seleccionamos el chrome driver para windows.
        if(SistemaOperativo.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//windows//Selenium//chromedriver.exe");

            // Seleccionamos el chrome driver para linux.
        }else if(SistemaOperativo.contains("Linux"))
        	System.setProperty("webdriver.chrome.driver","src//test//java//Webdriver//chromedriver");
        
        // Establecemos los argumentos de ejecucion para el chrome dirver
        ChromeOptions Opciones = new ChromeOptions();
        Opciones.addArguments("ignore-certificate-errors");
//      Opciones.addArguments("--incognito");
        
        // Agregamos la opcion de una ejecucion web en chrome con navegador oculto.
        if(OpcionesEjecucion.ChromeHeadless == true) {
            Opciones.addArguments("--headless");
        }
        // Establecemos la dimension de la ventana del navegador en ejecucion.
        Opciones.addArguments("--start-maximized");
       // Opciones.addArguments("--window-size=1360,768");
        Opciones.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        
       
        // Iniciamos el driver para la navegacion.
        Driver.set(new ChromeDriver(Opciones));
    	}
    	catch(Exception e)
    	{
    		System.out.println("Este es el error "+e);
    	}
       

   }
    
    /**
    *
    * Finalizamos la navegacion y la ejecucion.
    */    
    public void FinalizarSelenium() throws InterruptedException{
        // Luego de una breve pausa de 2 segundos, finalizamos la sesion en el navegador.
        Thread.sleep(2000);
        Driver.get().quit();
    }
}
