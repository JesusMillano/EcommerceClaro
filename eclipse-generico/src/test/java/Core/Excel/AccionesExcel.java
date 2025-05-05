package Core.Excel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Business.ObjExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import Core.Selenium.InitSelenium;

public class AccionesExcel {
	public boolean ExisteExcel(String NombreArchivo)
	{
		
	    File RutaRaiz = new File("Reportes//ReportesExcel"+ "//" + NombreArchivo + ".xlsx");
        File CarpetaRaiz = (RutaRaiz);
        if (CarpetaRaiz.exists())
        	return true;
        return false;
	}
	
    public void CrearNuevoExcel(String NombreArchivo, String NombreHoja, String[] Datos) {
    	// Iniciamos la creacion de un archivo excel.
    	System.out.println("Iniciamos la creacion del archivo: " + NombreArchivo);
    	
    	// Obtenemos la ruta principal de la carpeta del usuario de windows.
    	String CarpetaUsuario = System.getProperty("user.home");
    	
    	try {
	        // Definimos la ruta para ambas carpetas, raiz y la de capturas.
	        //String RutaRaiz = CarpetaUsuario + "//reportes//Excel";
    		//String RutaRaiz = "//reportes//Excel";
	        //String RutaExcel = RutaRaiz + "//" + NombreArchivo + ".xlsx";
	        
	        File RutaRaiz = new File("Reportes//ReportesExcel");//File RutaRaiz = new File("ReportesExcel");
	        String RutaExcel = RutaRaiz + "//" + NombreArchivo + ".xlsx";
	        
	        // Creamos la carpeta raiz si esta no existe.
	        File CarpetaRaiz = (RutaRaiz);
	        if (!CarpetaRaiz.exists())
	       	CarpetaRaiz.mkdir();
	        	        
	        // Creamos un nuevo libro con una nueva hoja con los nombres indicados.
	        XSSFWorkbook LibroExcel = new XSSFWorkbook();
	        XSSFSheet HojaExcel = LibroExcel.createSheet(NombreHoja);
	        
	        // Creamos la cabecera e iniciamos un correlativo para la columna.
	        Row FilaExcel = HojaExcel.createRow(0);
	        int NumColumna = 0;
	
	        // LLenamos la primera columna con el correlativo siguiente a la fila anterior. 
	        Cell CeldaExcel = FilaExcel.createCell(NumColumna);
	        CeldaExcel.setCellValue(0);
	        
	        // Configuramos estilo para color de fondo negro.
	        CellStyle EstiloCelda = LibroExcel.createCellStyle(); 
	        EstiloCelda.setFillForegroundColor(IndexedColors.BLACK.getIndex()); 
	        EstiloCelda.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        // Configuramos estilo para color de fuente.
	        Font Fuente = LibroExcel.createFont();
	        Fuente.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
	        EstiloCelda.setFont(Fuente);
	        
	        // Inicamos el llenado de cada columna en la cabecera.
	        for (String Dato : Datos) {
	        	CeldaExcel = FilaExcel.createCell(NumColumna++);
	        	CeldaExcel.setCellValue(Dato);
	        	CeldaExcel.setCellStyle(EstiloCelda);
	        }
	        
	        // Creamos un archivo de salida para guardar los cambios.
	        FileOutputStream ArchivoSalida = new FileOutputStream(RutaExcel);
	        LibroExcel.write(ArchivoSalida);
	        
	        // Cerramos el archivo de salida y el libro excel.
	        ArchivoSalida.close();
	        LibroExcel.close();
	                
	        // Finalizamos la actualizacion del archivo excel.
	    	System.out.println("Creacion del archivo: " + NombreArchivo + ", finalizado de manera exitosa!");
    	}
    
    	// En caso de algun problema con la actualizacion del excel, arrojamos un error.
        catch (IOException | EncryptedDocumentException ex) {
        	System.err.println("Hubo un error al crear el archivo: " + NombreArchivo);
        	ex.printStackTrace();
        }

    }
    
    public void AgregarLineaExcel(String ArchivoExcel, String Datos[]) throws InterruptedException{
    	// Iniciamos actualizacion de archivo excel.
    	System.out.println("Iniciamos actualizacion del archivo: " + ArchivoExcel + ".xlsx");
    	
    	// Obtenemos la ruta principal de la carpeta del usuario de windows.
    	String CarpetaUsuario = System.getProperty("user.home");
        
        try {
        	// Abrimos el archivo excel indicado desde la carpeta de usuario.
        	//String RutaRaiz = CarpetaUsuario + "//reportes//Excel//" + ArchivoExcel + ".xlsx";
        	
        	File RutaRaiz = new File("Reportes/ReportesExcel/"+ArchivoExcel + ".xlsx");
        	FileInputStream ArchivoEntrada = new FileInputStream(RutaRaiz);
            
            // Abrimos el libro y nos posicionamos en la primera hoja de trabajo.
            Workbook LibroExcel = WorkbookFactory.create(ArchivoEntrada);
            Sheet HojaExcel = LibroExcel.getSheetAt(0);
 
            // Obtenemos el numero de la ultima fila con datos.
            int NumFilaActual = HojaExcel.getLastRowNum();
            int NumNuevaFila = NumFilaActual + 1;
            
            // Creamos una fila e iniciamos un correlativo para la columna.
            Row FilaExcel = HojaExcel.createRow(NumNuevaFila);
            int NumColumna = 0;
 
            // LLenamos la primera columna con el correlativo siguiente a la fila anterior. 
            Cell CeldaExcel = FilaExcel.createCell(NumColumna);
            CeldaExcel.setCellValue(NumNuevaFila);
            
            // Inicamos el llenado de cada columna en la fila.
            for (String Dato : Datos) {
            	CeldaExcel = FilaExcel.createCell(NumColumna++);
            	CeldaExcel.setCellValue(Dato);
            }
 
            // Cerramos el archivo de entrada.
            ArchivoEntrada.close();
 
            // Creamos un archivo de salida para guardar los cambios.
            FileOutputStream ArchivoSalida = new FileOutputStream(RutaRaiz);
            LibroExcel.write(ArchivoSalida);
            
            // Cerramos el libro y el archivo de salida.
            LibroExcel.close();
            ArchivoSalida.close();
            
            // Finalizamos la actualizacion del archivo excel.
        	System.out.println("Actualizacion del archivo: " + ArchivoExcel + ".xlsx, finalizada de manera exitosa!");
        	
        } 
        
        // En caso de algun problema con la actualizacion del excel, arrojamos un error.
        catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
        	System.err.println("Hubo un error al actualizar el archivo: " + ArchivoExcel + ".xlsx");
        	ex.printStackTrace();
        }
    }
    
    public ArrayList<ObjExcel> CountRow(String ArchivoExcel)
    {
    	  int Cant=0;
    	  int NumCol;
    	  ArrayList<ObjExcel> objExcelList  = new ArrayList<>();
    	  try
          {
    		  DataFormatter dataFormatter = new DataFormatter();
              FileInputStream file = new FileInputStream(new File("Input/"+ArchivoExcel + ".xlsx"));
   
              //Create Workbook instance holding reference to .xlsx file
              XSSFWorkbook workbook = new XSSFWorkbook(file);
   
              //Get first/desired sheet from the workbook
              XSSFSheet sheet = workbook.getSheetAt(0);
              
   
              //Iterate through each rows one by one
              Iterator<Row> rowIterator = sheet.iterator();
              while (rowIterator.hasNext()) 
              {
            	  ObjExcel objExcel = new ObjExcel();
            	  Cant++;
            	  Row row = rowIterator.next();
                  //For each row, iterate through all the columns
                  Iterator<Cell> cellIterator = row.cellIterator();
                   
                  NumCol=0;
                  while (cellIterator.hasNext()) 
                  {   
                	  NumCol++;
                	  Cell cell = cellIterator.next();
                	  String cellStringValue    = dataFormatter.formatCellValue(cell);                	  
                	  switch (NumCol)
                	  {
                	  	  case 1:  objExcel.setIdEjecucion(cellStringValue);		break;  
                	  	  case 2:  objExcel.setID(cellStringValue);					break;
	                	  case 3:  objExcel.setFlujo(cell.getStringCellValue());    break;
	                	  case 4:  objExcel.setTipoFlujo(cellStringValue);          break;
	                	  case 5:  objExcel.setCasoDePrueba(cellStringValue);       break;
	                	  case 6:  objExcel.setRut(cellStringValue);                break;
	                	  case 7:  objExcel.setNombre(cellStringValue);             break;
	                	  case 8:  objExcel.setApellidos(cellStringValue);          break;
	                	  case 9:  objExcel.setCorreo(cellStringValue);             break;
	                	  case 10:  objExcel.setTelefono(cellStringValue);          break;
	                	  case 11: objExcel.setCheck(cellStringValue);              break;
	                	  case 12: objExcel.setDia(cellStringValue);                break;
	                	  case 13: objExcel.setMes(cellStringValue);                break;
	                	  case 14: objExcel.setAnio(cellStringValue);               break;
	                	  case 15: objExcel.setFonoDatPersonal(cellStringValue);    break;
	                	  case 16: objExcel.setCorreoDatPersonal(cellStringValue);  break;
	                	  case 17: objExcel.setRegion(cellStringValue);             break;
	                	  case 18: objExcel.setComuna(cellStringValue);             break;
	                	  case 19: objExcel.setCalle(cellStringValue);              break;
	                	  case 20: objExcel.setNumeracion(cellStringValue);         break;
	                	  case 21: objExcel.setTipoVivienda(cellStringValue);       break;
	                	  case 22: objExcel.setNumeroInt(cellStringValue);          break;
	                	  case 23: objExcel.setNombrePlan(cellStringValue);			break;
	                	  case 24: objExcel.setEquipo(cellStringValue);				break;
	                	  case 25: objExcel.setColor(cellStringValue);				break;
	                	  case 26: objExcel.setSkuEquipo(cellStringValue);          break;
	                	  case 27: objExcel.setQuienRecibe(cellStringValue);        break;
	                	  case 28: objExcel.setRutOtraPers(cellStringValue);        break;
	                	  case 29: objExcel.setNomOtraPers(cellStringValue);        break;
	                	  case 30: objExcel.setApellOtraPers(cellStringValue);      break;
	                	  case 31: objExcel.setFonoOtraPers(cellStringValue);  		break;   
	                	  case 32: objExcel.setPagoTarjeta(cellStringValue);  		break; 
	                	  case 33: objExcel.setTipoEnvio(cellStringValue);  		break; 
                	  }
                  }
                  
                  objExcelList.add(objExcel);
              }
              file.close();
          } 
          catch (Exception e) 
          {
              e.printStackTrace();
          }
    	  return objExcelList;
    }  
}