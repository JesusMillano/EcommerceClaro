package Core.Selenium;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Key;
import org.testng.SkipException;

import Business.Ecommers;
import Core.Operaciones.Screenshots;
import Core.driver.DriverContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccionesWeb {
    private WebDriver driver;

    public AccionesWeb() {
    }

    private Core.Ini.LeerData DataIni = new Core.Ini.LeerData();
    
    public List<WebElement> FindListElements(String nombre, String plataforma) throws InterruptedException {
        List<WebElement> elem = null;
        driver = InitSelenium.getDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        nombre = nombre.replace("\"", "");
        plataforma = plataforma.replace("\"", "");
        //Leer Xpath de los archivos xml
        Core.Xml.LeerPasos xml = new Core.Xml.LeerPasos();
        List<String> Atributos = xml.getxmlArch(nombre, plataforma);
        if (Atributos == null)
            System.out.println("Objeto de nombre " + nombre + " No ha se ha encontrado");

        String TipoBy = Atributos.get(0);
        String Xpath = Atributos.get(1);


        switch (TipoBy) {
            case "Xpath":
                elem = driver.findElements(By.xpath(Xpath));
                break;
            case "Id":
                elem = driver.findElements(By.id(Xpath));
                break;
            case "LinkText":
                elem = driver.findElements(By.linkText(Xpath));
                break;
        }

        return elem;
    }
    

    public WebElement FindElemento(String nombre, String plataforma) throws InterruptedException {
        WebElement elem = null;
        driver = InitSelenium.getDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        nombre = nombre.replace("\"", "");
        plataforma = plataforma.replace("\"", "");
        //Leer Xpath de los archivos xml
        Core.Xml.LeerPasos xml = new Core.Xml.LeerPasos();
        List<String> Atributos = xml.getxmlArch(nombre, plataforma);
        if (Atributos == null)
            System.out.println("Objeto de nombre " + nombre + " No ha se ha encontrado");

        String TipoBy = Atributos.get(0);
        String Xpath = Atributos.get(1);


        switch (TipoBy) {
            case "Xpath":
                elem = driver.findElement(By.xpath(Xpath));
                break;
            case "Id":
                elem = driver.findElement(By.id(Xpath));
                break;
            case "LinkText":
                elem = driver.findElement(By.linkText(Xpath));
                break;
        }

        return elem;
    }

    
    public void ClickAmdoc(String Nombre, String Plataforma, int timeOut) throws InterruptedException {
        WebElement Elem = FindElementoWait(Nombre, Plataforma, timeOut);
        Elem.click();
    }
    
    
    public void ClickEcom(String Nombre, String Plataforma, int timeOut) throws InterruptedException {
        WebElement Elem = FindElementoWait(Nombre, Plataforma, timeOut);
        Elem.click();
    }
    

    public void AlertAccept(String Plataforma) {
    }
    

    public void InputText(String Value, String Nombre, String Plataforma) throws InterruptedException {
        WebElement Elem = FindElementoWait(Nombre, Plataforma, 30);
        Elem.sendKeys(Value);
        //Elem.sendKeys(Keys.ENTER);
    }


    public void clearText(String Nombre, String Plataforma) throws InterruptedException {
        WebElement Elem = FindElementoWait(Nombre, Plataforma, 30);
        Elem.clear();
    }

    
    public String LeerElemento(String Nombre, String Plataforma) throws InterruptedException {
        WebElement Elem = FindElementoWait(Nombre, Plataforma, 60);
        String Resultado = Elem.getText();

        System.out.println("Lectura: " + Resultado);
        return Resultado;
    }
    

    public void CompararString(String Value, String Nombre, String Plataforma) throws InterruptedException {

        String Resultado = LeerElemento(Nombre, Plataforma);
        int time = 60;
        for (int i = 0; i < time; i++) {

            if (Resultado.contains(Value))
                return;
            Thread.sleep(1000);
        }

        throw new RuntimeException(Resultado + " es distinto a: " + Value);
    }

    
    public List<WebElement> findElementosWait(String nombre, String plataforma, int timeout) throws InterruptedException {
        System.out.println(".. buscando elemento " + nombre);
        List<WebElement> elem = null;
        for (int i = 0; i < timeout; i++) {
            try {
                elem = FindListElements(nombre, plataforma);
            } catch (Exception e) {
                System.out.println(".. buscando elemento " + nombre);
                Thread.sleep(1000);
            }
            if (elem != null) {
                return elem;
            }
        }

        return elem;
    }

    
    public WebElement FindElementoWait(String Nombre, String Plataforma, int TimeOut) throws InterruptedException {
        System.out.println(".. buscando elemento " + Nombre);
        WebElement elem = null;
        for (int i = 0; i < TimeOut; i++) {
            try {
                elem = FindElemento(Nombre, Plataforma);
            } catch (Exception e) {
                System.out.println(".. buscando elemento " + Nombre);
                Thread.sleep(1000);
            }
            if (elem != null) {
                return elem;
            }
        }
        return elem;
    }

    
    public boolean FindElementoWaitBoo(String Nombre, String Plataforma, int TimeOut) throws InterruptedException {
        System.out.println(".. buscando elemento " + Nombre);
        WebElement elem = null;
        for (int i = 0; i < TimeOut; i++) {
            try {
                elem = FindElemento(Nombre, Plataforma);
            } catch (Exception e) {
                System.out.println(".. buscando elemento " + Nombre);
                Thread.sleep(1000);
            }
            if (elem != null) {
                return true;
            }

        }
        return false;
    }

    
    public void AbrirDireccion(String Url) throws InterruptedException {       
    	WebDriver Driver = InitSelenium.getDriver();
        Driver.manage().window().maximize();

        Thread.sleep(500);
        Driver.get(Url);
        Thread.sleep(500);
    }

    
    public void CloseDriver() throws InterruptedException {
        WebDriver Driver = InitSelenium.getDriver();
        	Thread.sleep(2000);
        	Driver.close();
    }

    
    public void Iframe() {
        WebDriver Driver = InitSelenium.getDriver();
        Driver.switchTo().frame(1);
    }

    
    public void IngresarTexto(String nameXML, String plataforma, String Objeto, String texto, Boolean capturar, String comentario) throws IOException, InterruptedException {     
        Thread.sleep(500);
        WebElement Elemento = BuscarElemento("Xpath", Objeto);
       // GenericValidations.existElementReport(nameXML, capturar, plataforma, comentario);
   
        Elemento.clear();
        Elemento.sendKeys(texto);

        // En caso que sea requerido, realizamos una captura del campo de texto con el texto ingresado.
        /*if (Capturar == true){
            // Creamos el objeto para captura de la accion.
            Screenshots Captura = new Screenshots();

            // Realizamos una captura de la accion para registrar en el reporte.
            if(Comentario != "") {
                // Además de la captura, ingresamos un comentario personalizado.
                Captura.CapturaElementoWeb(Elemento, Comentario);
            }

            else {
                // Si no existe un comentario persoinalizado, ingresamos un comentario basico de la acción.
                Captura.CapturaElementoWeb(Elemento, "Ingresamos el texto: " + Texto + " en el campo: " + Objeto);
            }
        }*/

        Thread.sleep(500);
    }


    public List<WebElement> buscarElementos(String tipo, String objeto) {       
        WebDriver driver = DriverContext.getDriver();
        ElementosWeb Elementos = new ElementosWeb();
        String Ruta = Elementos.RutaElemento(objeto);

        List<WebElement> elementos = null;
        String Error = null;

        // Buscamos el elemento a traves del tipo y la ruta indicada.
        try {
            switch (tipo) {
                case "Xpath":
                    elementos = driver.findElements(By.xpath(objeto));
                    break;
                case "PartialLinkText":
                    elementos = driver.findElements(By.partialLinkText(Ruta));
                    break;
                case "Id":
                    elementos = driver.findElements(By.id(Ruta));
                    break;
                case "Class":
                    elementos = driver.findElements(By.className(Ruta));
                    break;
                case "Css":
                    elementos = driver.findElements(By.cssSelector(Ruta));
                    break;
            }
        } catch (Exception Err) {
            // Devolvemos un error de manera simple indicando el objeto y su ruta.
            if (objeto != objeto)
                Error = "Error: El elemento: " + objeto + " con la ruta: " + Ruta + " no fue encontrado!";

                // Devolvemos un error de manera simple en caso que se haya indicado solo la ruta.
            else if (objeto == objeto)
                Error = "Error: El elemento con la ruta: " + objeto + " no fue encontrado!";

                // Devolvemos el error de java completo en caso de no indicar log simple.
            else
                Error = "Error: " + Err;

            // Imprimimos el error en la consola.
            System.err.println(Error);

            // Iniciamos el objeto para capturas.
            Screenshots Captura = new Screenshots();

            // Obtenemos una captura de pantalla completa mas una descripcion del error.
            //	Captura.CapturaElementoWeb(null, "<span style='color: red;'>" + Error + "</span>");

            // Omitimos el resto de la ejecucion.
            throw new SkipException("El caso ha finalizado con error!");
        }

        return elementos;
    }

    
    public WebElement BuscarElemento(String Tipo, String Objeto) throws IOException {
        WebDriver Driver = InitSelenium.getDriver();
        ElementosWeb Elementos = new ElementosWeb();
        String Ruta = Elementos.RutaElemento(Objeto);

        WebElement Elemento = null;
        String Error = null;

        // Buscamos el elemento a traves del tipo y la ruta indicada.
        try {
            switch (Tipo) {
                case "Xpath":
                    Elemento = Driver.findElement(By.xpath(Objeto));
                    break;
                case "PartialLinkText":
                    Elemento = Driver.findElement(By.partialLinkText(Objeto));
                    break;
                case "Id":
                    Elemento = Driver.findElement(By.id(Objeto));
                    break;
                case "Class":
                    Elemento = Driver.findElement(By.className(Objeto));
                    break;
                case "Css":
                    Elemento = Driver.findElement(By.cssSelector(Objeto));
                    break;
            }
        } catch (Exception Err) {
            // Devolvemos un error de manera simple indicando el objeto y su ruta.
            if (Objeto != Ruta)
                Error = "Error: El elemento: " + Objeto + " con la ruta: " + Ruta + " no fue encontrado!";

                // Devolvemos un error de manera simple en caso que se haya indicado solo la ruta.
            else if (Objeto == Ruta)
                Error = "Error: El elemento con la ruta: " + Ruta + " no fue encontrado!";

                // Devolvemos el error de java completo en caso de no indicar log simple.
            else
                Error = "Error: " + Err;

            // Imprimimos el error en la consola.
            System.err.println(Error);

            // Iniciamos el objeto para capturas.
            Screenshots Captura = new Screenshots();

            // Obtenemos una captura de pantalla completa mas una descripcion del error.
            //	Captura.CapturaElementoWeb(null, "<span style='color: red;'>" + Error + "</span>");

            // Omitimos el resto de la ejecucion.
            throw new SkipException("El caso ha finalizado con error!");
        }

        return Elemento;
    }

    
    public void Click(String nameXML, String plataforma, String Objeto, Boolean capturar, String comentario) throws IOException, InterruptedException {
    	//
    	//Eliminar cuando se modifiquen flujos de pre-pos y hogar
    	/////7
        WebElement Elemento = BuscarElemento("Xpath", Objeto);
        //GenericValidations.existElementReport(nameXML,capturar,plataforma,comentario);
       
        Thread.sleep(500);
        Elemento.click();
    }
    
    
    public void Click(String Objeto) throws IOException, InterruptedException {
        WebElement Elemento = BuscarElemento("Xpath", Objeto);

        Thread.sleep(500);
        Elemento.click();
    }
    
    
    public void ClickXpath( String Objeto) throws IOException, InterruptedException {
        WebElement Elemento = BuscarElemento("Xpath", Objeto);
        Thread.sleep(500);
        Elemento.click();
    }

    
    public void Click(WebElement elemento) {
    	elemento.click();
    }


    public void switchToIFrame(String nombre, String plataforma, int timeOut) throws InterruptedException {
        WebElement elemento = FindElementoWait(nombre, plataforma, timeOut);
        InitSelenium.getDriver().switchTo().frame(elemento);
    }

    
    public void cerrarIframe() {
        InitSelenium.getDriver().switchTo().defaultContent();
    }

    
    public void moveScreenToElement(String nombre, String plataforma, int timeOut) throws InterruptedException {
        WebElement elemento = FindElementoWait(nombre, plataforma, timeOut);
        Actions pantalla = new Actions(InitSelenium.getDriver());
        pantalla.moveToElement(elemento);
    }

    
    public void movePointerOverElement(String nombre, String plataforma, int timeOut) throws InterruptedException {
        WebElement elemento = FindElementoWait(nombre, plataforma, timeOut);
        Point coordenadas = elemento.getLocation();
        Actions mouse = new Actions(InitSelenium.getDriver());
        mouse.moveByOffset(coordenadas.getX(), coordenadas.getY());
    }
    

    public void scrollMonitor(int catScroll){
        JavascriptExecutor Scrool = (JavascriptExecutor) driver;
        Scrool.executeScript("window.scrollBy(0,"+catScroll+")", "");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    
    public void BotonDerecho(WebElement elemento) {
    	 WebDriver Driver = InitSelenium.getDriver();
    	 Actions actionProvider = new Actions(Driver);
    	 actionProvider.contextClick(elemento).build().perform();     
    }
    
    
    public boolean EsNumero(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
    
    
    public boolean NumLetra(String cadena) {   	
    	int ValCadena =  cadena.length();
    	
    	if(ValCadena != 0) {

	    	 Pattern pat = Pattern.compile("[^a-zA-Z0-9$]"); 	
		     Matcher mat = pat.matcher(cadena);   
	     
	         if (mat.find()) {
	               return false; 
	         } else {
	             return true;
	         }
    	}else {
    		return false; 
    	}   	
    }
    
    
    public boolean ValidaRut(String rut) {
        int val = rut.length();
        boolean validacion = false;
        
        if(val >= 8) {
		        try {
		            rut =  rut.toUpperCase();
		            rut = rut.replace(".", "");
		            rut = rut.replace("-", "");
		            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
		
		            char dv = rut.charAt(rut.length() - 1);
		
		            int m = 0, s = 1;
		            for (; rutAux != 0; rutAux /= 10) {
		                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
		            }
		            if (dv == (char) (s != 0 ? s + 47 : 75)) {
		                validacion = true;
		            }
		
		        } 
		        catch (java.lang.NumberFormatException e) {
		        } 
		        catch (Exception e) {
		        }
        }else {
        	validacion = false;
        	}
        
        return validacion;
    }
    
    
    public  boolean ValidarMail(String email) {       
    	Pattern pattern = 
    			Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }
     
    
    public void PagoDebito() throws InterruptedException{
    	List<WebElement> metodos = driver.findElements(By.className("payment-options__method-items-option"));
    			System.out.println(metodos);
    			
    			for (WebElement metodosPago : metodos) {
    				System.out.println("Los medios de pago disponibles son: " + metodosPago.getText());
    				if(metodosPago.getText().contains("Débito"))
    					metodosPago.click();  
    			}	   			
    }
    
    
    public void PagoCredito(){
    	List<WebElement> metodos = driver.findElements(By.className("payment-options__method-items-option"));
    			System.out.println(metodos);
    			
    			for (WebElement metodosPago : metodos) {
    				System.out.println("Los medios de pago disponibles son: " + metodosPago.getText());
    				if(metodosPago.getText().contains("Crédito"))
    					metodosPago.click();  				
    			}
    }
    
    
    public void PagoPrepago(){
    	List<WebElement> metodos = driver.findElements(By.className("payment-options__method-items-option"));
    			System.out.println(metodos);
    			
    			for (WebElement metodosPago : metodos) {
    				System.out.println("Los medios de pago disponibles son: " + metodosPago.getText());
    				if(metodosPago.getText().contains("Prepago"))
    					metodosPago.click();  				
    			}
    }
    
       
    public WebElement EsperarElementoVisible(String Nombre, String Plataforma) throws InterruptedException {
            WebElement elemento = FindElemento(Nombre, Plataforma);
     
            // Verificar que el elemento esté presente antes de aplicar la espera de visibilidad
            if (elemento != null) {
                try {
                    System.out.println("Esperando el elemento visible: " + Nombre);
                    WebDriverWait ewait = new WebDriverWait(driver, null);
                    ewait.until(ExpectedConditions.visibilityOf(elemento));
                    System.out.println("Elemento visible: " + Nombre);
                } catch (StaleElementReferenceException e) {
                    System.out.println("El elemento no es más referenciable: " + Nombre);
                } catch (Exception e) {
                    System.out.println("Error durante la espera de visibilidad: " + e.getMessage());
                }
            } else {
                System.out.println("El elemento no fue encontrado: " + Nombre);
            }
            
            return elemento;
        }
        
}