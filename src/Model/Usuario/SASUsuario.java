import java.util.ArrayList;

import Controlador.DAO.InterfazDAOFachada;
import Excepciones.ErrorAutenticacion;
import Excepciones.ErrorCreacionObjeto;
import Model.Objetos.Genero;
import Model.Objetos.Usuario;

public class SASUsuario implements InterfazSASUsuario {
	
	private InterfazDAOFachada dao;
	//hay un objeto dao, solo que no se donde deberiamos ponerlo
	
	/**
	 * Verifica los datos de inicio de sesion y genera el usuario seleccionado
	 * @param id id de usuario
	 * @param pass contrasena
	 * @return 
	 * @throws ErrorDeAutenticacion si se ha producido un error al validar los datos del usuario (la contrasena esta mal)
	 */
    public Usuario ingreso(String id, String pass) throws ErrorAutenticacion {
    		Usuario buscado = dao.getUsuarioDB(id, pass);
    		if (buscado != null)
    			//llamada a controlador ajustando el nuevo usuario seleccionado y lanzondo la  ventana principal
    			return buscado;
    		else
    			throw new ErrorAutenticacion();
    }

    /**
     * Elimina el usuario de la DB
     * @param Usuario usuario a eliminar
     * @throws ErrorDeAutenticacion si se ha producido un error al validar los datos del usuario
     */
    public void borrar(Usuario Usuario) throws ErrorAutenticacion {
    		dao.borrarUsuario(Usuario);
    }

    /**
     * Genera el usuario con las modificaciones y lo guarda si es posible en la DB
     * @param usuario usuario a modificar
     * @param nombre nuevo nombre
     * @param pass nuevo pass
     * @throws ErrorCreacionObjeto si los nombre o pass intruducidos no son validos
     * @throws ErrorDeAutenticacion si se ha producido un error al validar los datos del usuario (la contrasena esta mal)
     */
    public void modificar(Usuario usuario, String nombre, String pass) throws ErrorCreacionObjeto, ErrorAutenticacion {
    		Usuario nuevo = new Usuario(usuario.getId(), nombre, pass, usuario.getGustos());
    		dao.setUsuario(nuevo);
    }

    /**
     * Finaliza la sesion del usuario actual y sale de la ventana principal
     * @param usuario
     */
    public void salir(Usuario usuario) {
    		//llamada al controlador (el cual no se si existe o cada sas hace de controlador)
    		//	en la cual establece el usuario actual a nulo y nos lleva a la pantalla de inicio
    }
    
    /**
     * Crea un usuario y lo guarda en la DB, siempre que no se repita la id elegida
     * @param id el id de usuario
     * @param nombre nombre del usuario
     * @param pass contrasena
     */
	public void registro(String id, String nombre, String pass) {
		Usuario usuario = new Usuario(id, nombre, pass);
		dao.setUsuario(usuario);
	}
}