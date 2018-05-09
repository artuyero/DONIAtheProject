package Model.Cancion;

import Excepciones.ErrorConsulta;
import Excepciones.ErrorCreacionObjeto;
import Model.Objetos.*;

public class FachadaCancion implements InterfazFachadaCancion {
    
	public InterfazSASCancion interfazSASCancion;

    public void creaCancion(Cancion cancion) throws ErrorCreacionObjeto {
    		interfazSASCancion.creaCancion(cancion);
	}
	
	public void eliminaCancion(Cancion cancion) {
		interfazSASCancion.eliminaCancion(cancion);
	}
	
    public Video consultaVideo(String cancion) throws ErrorConsulta {
    		return interfazSASCancion.consultaVideo(cancion);
    }

    public Letra consultaLetra(String cancion) throws ErrorConsulta {
    		return interfazSASCancion.consultaLetra(cancion);
    }

    public Cancion consultaCancion(String cancion) throws ErrorConsulta {
    		return interfazSASCancion.consultaCancion(cancion);
    }

    public String descargaVideo(String cancion) throws ErrorConsulta {
    		return interfazSASCancion.descargaVideo(cancion);
    }
}
