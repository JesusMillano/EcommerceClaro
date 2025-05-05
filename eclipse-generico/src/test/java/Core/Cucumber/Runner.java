package Core.Cucumber;

import Core.Selenium.AccionesWeb;
import Core.Selenium.InitSelenium;
import Core.Xml.LeerPasos;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(strict = true, monochrome = true, features = "./src/test/Java/Script/", glue = "Business",plugin = {
		"html:target/cucumber-html-report",
		"json:target/cucumber.json",
		"pretty:target/cucumber-pretty.txt",
		"usage:target/cucumber-usage.json",
		"junit:target/cucumber-results.xml",
		"progress:target/cucumber-progress.txt"}, tags = {})
public class Runner extends AbstractTestNGCucumberTests {

	  public static WebDriver Driver = null;
	    private AccionesWeb Acciones = new AccionesWeb();
		
		public void AbrirPlataforma(String Nombre) throws IOException{
			
			LeerPasos xml = new LeerPasos();
			List <String> Atributos = xml.getxmlArch("Plataforma",Nombre);
			
		   if (Atributos == null)
				System.out.println("Objeto Name no encontrado" + Nombre);		
		   
			String TipoBy =  Atributos.get(0);
			String Xpath  =  Atributos.get(1);
			
			if(Xpath.equalsIgnoreCase("Web"))
				AbrirPlataformaWeb(Nombre);
			
		}
		
		public void AbrirPlataformaWeb(String Nombre) throws IOException{
			
			LeerPasos xml = new LeerPasos();
			List <String> Atributos = xml.getxmlArch("Url",Nombre);
				
		   if (Atributos == null)
				System.out.println("Objeto Name no encontrado Url");		
		   
			String TipoBy =  Atributos.get(0);
			String RutaUrl  =  Atributos.get(1);
			
			// Iniciamos las dependencias de selenium.
			InitSelenium Selenium = new InitSelenium();
	    	Selenium.IniciarSelenium();
	    	
	        // Iniciamos el driver para la navegacion.
	        Driver = InitSelenium.getDriver();
	        
	        // Abrimos pagina de pruebas.
	        Driver.get(RutaUrl);
	        
	        int a=0;
	        a=1+1;
		}	
			
		@BeforeSuite(alwaysRun = true)
		public void setUp() throws Exception 
		{
			
		}
		
		@AfterClass(alwaysRun = true)
		public void takeScreenshot()  throws Exception {
			
		}

		
		@AfterMethod(alwaysRun = true)
		public void tearDown(ITestResult result) throws IOException {
		}
		
		@AfterSuite(alwaysRun=true)
		public void generateHTMLReports() {
			ReportHelper.generateCucumberReport();
		}

		@AfterSuite(alwaysRun = true)
		public void quit() throws IOException, InterruptedException {
			InitSelenium.getDriver().close();
		}
		
		public void Cerrar() throws IOException, InterruptedException {
			InitSelenium.getDriver().close();
		}
		
		
}