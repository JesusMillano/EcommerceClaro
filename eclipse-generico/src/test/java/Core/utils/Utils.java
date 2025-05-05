package Core.utils;

import Core.reporter.EstadoPrueba;
import Core.reporter.PdfBciReports;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;

public class Utils {
    /*
    PARA ÉSTA CLASE CORRESPONDEN LAS SIGUIENTES REGLAS CUANDO SEA NECESARIO CREAR ALGÚN MÉTODOS:
         1 - MANTENER UNA ESTRUCTURA DE COMENTARIOS SEGÚN CORRESPONDA
                + @author: [Torre correspondiente] - [usuario del editor]
                + @@param: parametroDeEntrada describir funcionalidad de la variable declarada (agregar tantos @param como datos de entrada tenga el metodo)
                + @return: Descripción del valor a retornar y su aplicación
                + * Comentario descriptivo de ser necesario (opcional)
         2 - LOS METODOS NO DEBEN MANIPULAR *WEBELEMENTS*
         3 - DEBEN DECLARARSE COMO MÉTODOS *STATIC*
    */

    /**************************************************************************************************
     * @author: Holanda Tower - exftoce
     * @param fecha del tipo Date
     * @return: retorna la fecha convertida en un String
     **************************************************************************************************/
    public static String dateToString(Date fecha){
        return new String();
    }


    /**************************************************************************************************
     * @author: Holanda Tower - exftoce
     * @param dia valor númerico
     * @param mes valor númerico o nombre del mes que desea convertir
     * @param anio valor númerico del año
     * @return: retorna una fecha del tipo date con los parametros ingresados
     **************************************************************************************************/
    public static Date stringToDate(String dia, String mes, String anio){
        return new Date();
    }


    /**************************************************************************************************
     * @author: Holanda Tower - exftoce
     * @param fecha con formato completo (DD-MM-AAAA)
     * @return: retorna una fecha del tipo date con los parametros ingresados
     **************************************************************************************************/
    public static Date stringToDate(String fecha){
        return new Date();
    }

    /**************************************************************************************************
     * @author: Holanda Tower - exgmart
     * @return: retorna la fecha de hoy convertida en un String
     **************************************************************************************************/
    public static String dateNow(){
        return new String();
    }


    /**************************************************************************************************
     * @author: Holanda Tower - exgmart
     * @param segundo Elemento visible dentro de la página
     * Realiza una pausa de tantos segundos sean ingresados al metodo
     **************************************************************************************************/
    public static void pausa(long segundo){
        try{
            Thread.sleep(segundo*1000);
        }catch(InterruptedException iexc){
            System.out.println("Error en el tiempo de espera: [" + iexc.getMessage() + "]");
        }
    }


    /**************************************************************************************************
     * @author: Alcántara Tower
     * @param rut correspondiente al dato de un registro en la bd tandem
     * @return: retorna el rut ingresado con el formato valida para realizar una consulta en tandem
     **************************************************************************************************/
    public static String formatearRutTandem(String rut){
        String rutFormateado;
        rutFormateado = rut.replace(".","");
        rutFormateado = rutFormateado.substring(0, rutFormateado.length() - 2);
        if (rutFormateado.length() == 9){
            rutFormateado = "0"+rutFormateado;
        }
        if (rutFormateado.length() == 8){
            rutFormateado = "00"+rutFormateado;
        }
        if (rutFormateado.length() == 7){
            rutFormateado = "000"+rutFormateado;
        }
        if(rutFormateado.length() == 6){
            rutFormateado = "0000"+rutFormateado;
        }
        return rutFormateado;
    }


    /**************************************************************************************************
     * @autor: Alcántara Tower
     * @param cadena Valida si es factible convertir el String en un entero
     * @return Valor booleano que indica si la cadena ingresada al metodo se puede convertir en un valor númerico
     **************************************************************************************************/
    public static boolean isNumeric(String cadena) {
        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) { resultado = false; }

        return resultado;
    }


    /**************************************************************************************************
     * @author: Alcántara Tower
     * @param datosBD
     * @param heightImagen
     * @return
     **************************************************************************************************/
    public static byte[] getCaptura(String datosBD, int heightImagen) throws Exception {

        HashMap<RenderingHints.Key, Object> renderingProperties = new HashMap<>();

        //String screenText = StringUtils.join(s.readScreen(), "\n");
        renderingProperties.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingProperties.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        renderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        Font font = new Font("Consolas", Font.PLAIN, 12);
        FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);

        BufferedImage bufferedImage = new BufferedImage(600, heightImagen, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setRenderingHints(renderingProperties);
        graphics2D.setBackground(Color.black);
        graphics2D.setColor(Color.white);
        graphics2D.setFont(font);
        graphics2D.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        TextLayout textLayout = new TextLayout(datosBD, font, fontRenderContext);

        Double cont= 0.0;
        for (String line : datosBD.split(",")) {
            graphics2D.drawString(line, 15, (int) (15 + cont * textLayout.getBounds().getHeight()));
            cont = cont + 1.5;
        }

        graphics2D.dispose();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", out);
        return out.toByteArray();
    }



    @Deprecated
    @Step("Validación de texto")
    public static boolean validarTexto(String textoApp,String textoAValidar){
        if (textoApp.trim().equals(textoAValidar.trim())){
            PdfBciReports.addReport("Validación de texto","Texto:"+textoApp+" validado segun diseño y es correcto", EstadoPrueba.PASSED, false);
            return true;
        }else{
            PdfBciReports.addReport("Validación de texto","Texto desplegado en app:"+textoApp+" validado segun diseño y no es correcto, deberia desplegar:"+textoAValidar, EstadoPrueba.FAILED, false);
            return false;
        }
    }

    /*
        ESTE METODO ES EL MISMO QUE EL DE ARRIBA SOLO QUE TRABAJA EL OBJETO COMPLETO PARA SU VALIDACION QUE SEGÚN
        LO TRABAJADO DEBERIA SER EL IDEAL
     */
    /*@Deprecated
    @Step("Validación de objeto y texto")
    public static void reporteValidacionTextos(WebElement objeto, String textoEsperado){
        try{
            boolean existeObjeto = ControlledActions.exists();
            if(existeObjeto==true){
                if (objeto.getText().equals(textoEsperado)){
                    PdfBciReports.addReport("Validación de objeto y texto","Se visualiza el texto esperado:'"+textoEsperado+"'.", EstadoPrueba.PASSED, false);
                }else{
                    PdfBciReports.addReport("Validación de objeto y texto","Se visualiza el texto esperado:'"+textoEsperado+"'.", EstadoPrueba.FAILED, false);
                }
            }else{
                PdfBciReports.addReport("Validación de objeto y texto","No se visualiza el texto:‘"+textoEsperado+"’ en la vista desplegada.", EstadoPrueba.FAILED, false);
            }
        }catch(Exception e){
            System.out.println("No se  logra ejecutar funcion reporteValidacionTextos, motivo:"+e.getMessage());
            PdfBciReports.addReport("Validación de objeto y texto","Error en el metodo ‘reporteValidacionTextos’, motivo:"+e.getMessage(), EstadoPrueba.FAILED, false);
        }
    }*/

    //no lleva step por que no reporta
    @Deprecated
    public static boolean validarTextoSinReporte(String textoApp,String textoAValidar){
        if (textoApp.trim().equals(textoAValidar.trim())){
            return true;
        }else{
            return false;
        }
    }

    //no lleva step por que no reporta
    @Deprecated
    public static boolean isEnabled(WebElement element) throws NoSuchElementException {
        System.out.println("Esta el elemento habilitado?:"+element.getAttribute("enabled"));
        return element.getAttribute("enabled").trim().equals("true");
    }
}