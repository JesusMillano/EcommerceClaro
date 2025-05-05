package Core.utils;


import Core.reporter.EstadoPrueba;
import Core.reporter.PdfBciReports;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    /**************************************************************************************************
     * @author: Alcántara Tower
     * @param nameFile nombre del archivo property que se leerá
     * @return un objeto del tipo Properties
     **************************************************************************************************/
    public static Properties readFromConfig(String nameFile){
        String propFileName=nameFile;
        Properties properties=new Properties();
        InputStream inputStream= ReadProperties.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputStream!=null){
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                PdfBciReports.addReport("Read Properties","No se pudo encontrar el archivo properties "+propFileName, EstadoPrueba.FAILED, true);
            }
        }else{
            PdfBciReports.addReport("Read Properties","No se pudo encontrar el archivo properties "+propFileName, EstadoPrueba.FAILED, true);
        }
        return properties;
    }
}