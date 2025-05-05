package Core.ScreenShot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;



public class ScreenShot {
	
	
	public void Capturar(String Ruta, String Nombre) throws AWTException
	{
	
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		try {

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateformat = new SimpleDateFormat("ddMMyyyy");
			String CarpFecha = dateformat.format(cal.getTime());
		
		    File RutaRaiz =  new File("Reportes\\Capturas\\"); //\\" + CarpFecha);

		    File RutaCapturas = RutaRaiz ;
		    if (!RutaCapturas.exists()) 
		    	RutaCapturas.mkdir();
		    
		    BufferedImage capture = new Robot().createScreenCapture(screenRect);
			ImageIO.write(capture, "png",new File(
					RutaCapturas +"\\"+Nombre+".png"));
		    
	    } catch (IOException e) {
	    	}
	    }

}
