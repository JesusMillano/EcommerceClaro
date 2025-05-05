package Core.utils;

import Core.Selenium.InitSelenium;
import Core.driver.DriverContext;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ControlledActions {

    private static WebDriver driver;
    /*
    PARA ÉSTA CLASE CORRESPONDEN LAS SIGUIENTES REGLAS CUANDO SEA NECESARIO CREAR ALGÚN MÉTODOS:
         1 - MANTENER UNA ESTRUCTURA DE COMENTARIOS SEGÚN CORRESPONDA
                + @author: [Torre correspondiente] - [usuario del editor]
                + @@param: parametroDeEntrada describir funcionalidad de la variable declarada (agregar tantos @param como datos de entrada tenga el metodo)
                + @return: Descripción del valor a retornar y su aplicación
                + * Comentario descriptivo de ser necesario (opcional)
         2 - LOS METODOS DEBEN MANIPULAR ALGÚN *WEBELEMENT*
         3 - DEBEN DECLARARSE COMO MÉTODOS *STATIC*
         4 - LOS METODOS NO DEBEN TENER NINGÚN TIPO DE REPORTE.
         5 - PUEDEN TENER MENSAJES POR CONSOLA
    */

    /**************************************************************************************************
     * @author: Alcántara Tower
     * @param nameXml tag "name" perteneciente al XML
     * @param i Tiempo de espera para detener el driver
     * @return: retorna un valor booleano en caso de que el elemento exista
    **************************************************************************************************/
    public static boolean exists(String nameXml,String plataforma , Duration i){
        try{
            WebElement elem = null;
            driver = InitSelenium.getDriver();
            WebDriverWait wait = new WebDriverWait(InitSelenium.getDriver(), i);
            nameXml = nameXml.replace("\"", "");
            plataforma = plataforma.replace("\"", "");

            //Lectura de XML
            Core.Xml.LeerPasos xml = new Core.Xml.LeerPasos();
            List<String> atributos = xml.getxmlArch(nameXml,plataforma);
            if (atributos == null){
                System.out.println("No fue posible reconocer el objeto...");
                return false;
            }else{
                String xpath  =  atributos.get(1);
                elem = driver.findElement(By.xpath(xpath));
                wait.until(ExpectedConditions.visibilityOf(elem));
                System.out.println("Objeto Encontrado: ["+elem+"].");
                return true;
            }

        }catch(Exception e){
            System.out.println("No fue posible reconocer el objeto...");
            return false;
        }
    }


    /**************************************************************************************************
     * @author: Felipe Toro
     * @param objeto Elemento visible dentro de la página
     * @return: retorna un valor booleano en caso de que el elemento exista
    **************************************************************************************************/
    public static boolean isDisplayedElement(WebElement objeto){
        try{
            if(objeto.isDisplayed()){
                System.out.println("El elemento es visible --> [" + objeto + "]");
                return true;
            }else {
                System.out.println("El elemento no es visible, pero existe dentro de la página.");
                return false;
            }
        }catch(Exception e){
            System.out.println("No fue posible saber si el elemento está presente. " + e.getMessage());
            return false;
        }
    }


    /**************************************************************************************************
     * @author: Alcántara Tower
     * @param objeto Elemento visible dentro de la página
     * Realiza ciclos de espera hasta que el elemento se presente en pantalla
     **************************************************************************************************/
    /*public static void loading(WebElement objeto, int tiempo){
        try{
            long tiempoStart = System.currentTimeMillis();
            long tiempoEnd = tiempoStart + 60*1000; // 60 seconds * 1000 ms/sec

            do {
                if (System.currentTimeMillis() >= tiempoEnd){
                    System.out.println("El tiempo de espera supera el tiempo limite.");
                }
                Duration.ofMillis(100);
            }while (exists(objeto, tiempo));
        }catch (NoSuchElementException ex){
            System.out.println("[ControlledActions] Error: No se pudo encontrar elemento "+ex.getMessage());
        }
    }*/


    /**************************************************************************************************
     * @author: Alcántara Tower
     * @param objeto Elemento visible dentro de la página
     * Realiza ciclos de espera hasta que el elemento desaparezca de la pantalla
     **************************************************************************************************/
    /*public static void loadingNegative(WebElement objeto, int tiempo){
        try{
            long tiempoStart = System.currentTimeMillis();
            long tiempoEnd = tiempoStart + 60*1000; // 60 seconds * 1000 ms/sec

            do {
                if (System.currentTimeMillis() >= tiempoEnd){
                    System.out.println("El tiempo de espera supera el tiempo limite.");
                }
                Duration.ofMillis(100);
            }while (!exists(objeto, tiempo));

        }catch (NoSuchElementException ex){
            System.out.println("[ControlledActions] Error: No se pudo encontrar elemento "+ex.getMessage());
        }
    }*/


    /**************************************************************************************************
     * @author: Holanda Tower - exgmart
     * @param objeto Elemento donde será arrastrado el cursor del mouse
    **************************************************************************************************/
    public static void movePointerOverElement(WebElement objeto){
        Point coordenadas = objeto.getLocation();
        Actions mouse = new Actions(DriverContext.getDriver());
        mouse.moveByOffset(coordenadas.getX(), coordenadas.getY());
    }


    /**************************************************************************************************
     * @author: Holanda Tower - exgmart
     * @param objeto que se encuentra fuera de la pantalla visible del navegador
    **************************************************************************************************/
    public static void moveScreenToElement(WebElement objeto){
        Actions pantalla = new Actions(DriverContext.getDriver());
        pantalla.moveToElement(objeto);
    }


    /**************************************************************************************************
     * @author: Holanda Tower - exgmart
     * @param iframe Frame al que se realizará el cambio
     * Recordar volver al frame principal para continuar con la prueba
     **************************************************************************************************/
    public static void switchToIFrame(WebElement iframe){
        DriverContext.getDriver().switchTo().frame(iframe);
    }
}