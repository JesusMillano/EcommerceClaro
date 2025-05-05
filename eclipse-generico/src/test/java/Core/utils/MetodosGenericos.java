package Core.utils;

import Core.driver.DriverContext;
import Core.reporter.EstadoPrueba;
import Core.reporter.PdfBciReports;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MetodosGenericos {
    /**************************************************************************************************
     * @author: Alcántara Tower
     * @see: ControlledActions#exists();
     **************************************************************************************************/
    @Deprecated
    public static boolean visualizarObjeto(WebElement objeto, Duration segundos){
        try{
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), segundos);
            wait.until(ExpectedConditions.visibilityOf(objeto));
            System.out.println("Objeto Encontrado: ["+objeto+"].");
            return true;
        }catch(Exception e){
            System.out.println("No fue posible reconocer el objeto: ["+objeto+"].");
            return false;
        }
    }

    /*************************************************************************************************
     * @author: Alcántara Tower
     *El metodo "emitirReporte", genera reportes en base a los parametros que necesita, estadoObjeto
     * es un valor boleano que indica si el objeto esta o no en la vista, objeto es el nombre del objeto
     * parta identificarlo en reporte y vista para indentificar la vista en la que estamos.
     ************************************************************************************************/
    @Deprecated
    public static void reporteObjetoDesplegado(boolean estadoObjeto, String objeto, String vista, boolean fatal){
        if (estadoObjeto == true){
            PdfBciReports.addReport("Elemento encontrado: "+objeto,"El objeto:"+objeto+", se visualiza correctamente en la vista de "+vista+".", EstadoPrueba.PASSED, fatal);
        }else{
            PdfBciReports.addReport("Elemento no encontrado: "+objeto,"El objeto:"+objeto+", no se visualiza  en la vista de "+vista+".", EstadoPrueba.FAILED, fatal);
        }
    }
}