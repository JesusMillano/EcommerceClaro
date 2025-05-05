package Core.Data;

import java.util.concurrent.ThreadLocalRandom;

public class GeneradorRut {
	
	public String Rut;
	
	
    public String getRut() {
		return Rut;
	}

	public void setRut(String rut) {
		Rut = rut;
	}

	
	 private char GetDigitoRut(int rut) {
	        int m = 0, s = 1;
	        for (; rut != 0; rut /= 10) {
	            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
	        }
	        char Digito = (char) (s != 0 ? s + 47 : 75);
	        
	        return Digito;
	    }
	    
	    public String GetRutRandom()
	    {
	    	int NumRut = ThreadLocalRandom.current().nextInt(30000000, 49000000);    	
	    	char Digito= GetDigitoRut(NumRut);
	    	String Rut=Integer.toString(NumRut)+Digito;
	    	
	    	return Rut;
	    }

}
