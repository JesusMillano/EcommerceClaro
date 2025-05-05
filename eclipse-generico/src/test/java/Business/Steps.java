package Business;

import cucumber.api.java.es.Dado;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;



public class Steps{
    	
	public static WebDriver Driver = null;


    @Dado("Se da inicio Ecommerce")
	public void InicioEcommerce() throws InterruptedException, IOException, UnsupportedFlavorException, FindFailed, AWTException{
    	System.out.println("hi");
    	Business.EventHandlerCucumber Event = new Business.EventHandlerCucumber();
    	Event.RunTestExcel();
    	
	}
}