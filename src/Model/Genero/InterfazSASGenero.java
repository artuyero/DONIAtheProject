package Model.Genero;

import Excepciones.ErrorAutenticacion;
import Excepciones.ErrorConsulta;
import Excepciones.ErrorCreacionObjeto;
import Excepciones.ErrorEliminacion;
import Excepciones.ErrorGuardado;
import Model.Objetos.Genero;
import Model.Objetos.Usuario;

/**
 * Interfaz del servicio de aplicación de género.s
 * @author Raúl
 *
 */
public interface InterfazSASGenero {
	
	/**
	 * Añade un género a la DB.
	 * @param genero Género a añadir.
	 * @param usuario Usuario en el que se quiere añadir el género.
	 * @throws ErrorAutenticacion Si el usuario no existe.
	 * @throws ErrorGuardado Si no se ha podido guardar la información.
	 */
    void Anadir(Genero genero, Usuario usuario) throws ErrorAutenticacion, ErrorGuardado;
    
    /**
     * Elimina un género de la DB.
     * @param genero género a eliminar.
     * @param usuario usuario que quiere eliminar el género.
     * @throws ErrorEliminacion Si no se ha podido eliminar.
     * @throws ErrorAutenticacion Si el usuario no existe.
     */
    void Eliminar(Genero genero, Usuario usuario) throws ErrorEliminacion, ErrorAutenticacion;
    
    /**
     * Obtiene un género de la DB.
     * @param idGenero id del género.
     * @return el género buscado, null si no existe.
     * @throws ErrorConsulta Si se ha producido un error al buscar el género.
     * @throws ErrorCreacionObjeto Si se ha producido un error al crear el objeto.
     */
    Genero Consultar(String idGenero) throws ErrorConsulta, ErrorCreacionObjeto;
}
