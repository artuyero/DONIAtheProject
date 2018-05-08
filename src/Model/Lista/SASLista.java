package Model.Lista;

import java.util.ArrayList;
import Controlador.Controlador;
import Controlador.DAO.InterfazDAOFachada;
import Excepciones.ErrorAutenticacion;
import Excepciones.ErrorConsulta;
import Excepciones.ErrorCreacionObjeto;
import Model.Lista.InterfazSASLista;
import Model.Objetos.*;

/**
 * Clase SASLista
 *
 */
public class SASLista implements InterfazSASLista {
	
	private InterfazDAOFachada dao;
	//hay un objeto dao, solo que no se donde deberiamos ponerlo
	private Controlador controlador;
	//tambien necesitamos en algunas clases (como esta) saber cual es el usuario con el que estamos trabajando ahora
	
	/**
	 * Devuelve la lista solicitada
	 * 
	 * TODO: quizas la lista deberia ser un string con la id
	 * 
	 * @param id id de la lista a consultar
	 * @return la lista buscada, null si no existe
	 * @throws ErrorConsulta 
	 */
	@Override
    public Lista consulta(String id) throws ErrorConsulta {
		Lista lista = dao.getListaDB(id);
		if (lista == null)
			throw new ErrorConsulta("Error al consultar lista");
		else
			return lista;
    }
    
	
    /**
     * borra una lista existente (obvio)
     * @param lista lista a borrar
     */
    @Override
    public void borrar(Lista lista, Usuario usuario) {
    	dao.eliminarLista(lista, usuario);
    }
    
    /**
     * modifica una lista existente
     * @param nombre el nuevo nombre
     * @param lista lista a modificar
     */
    @Override
    public void modificar(String nombre, Lista lista, Usuario usuario) throws ErrorAutenticacion{
    		lista.setNombre(nombre);
    		dao.setLista(lista, usuario);
    }

    /**
     * Crea una lista vacia (las canciones se anaden aparte) y la guarda en la DB
     * @param nombre nombre de la lista
     * @throws ErrorCreacionObjeto 
     */
    @Override
    public void crearLista(String nombre, Usuario usuario) throws ErrorAutenticacion, ErrorCreacionObjeto {
    		ListaNormal lista = new ListaNormal(/*l aputa id*/"a", nombre);
    		if (lista == null)
    			throw new ErrorCreacionObjeto("Error al crear la lista");
    		else
    			dao.setLista(lista, usuario);
    }

    /**
     * Crea una listaAuto con sus temas correspondientes y la guarda en la DB
     * @param nombre nombre de la lista
     * @param genero el genero del cual seleccionar las canciones
     * @throws ErrorCreacionObjeto 
     */
    @Override
    public void crearListaAuto(String nombre, Genero genero, Usuario usuario, int duracionMax) throws ErrorAutenticacion, ErrorCreacionObjeto {
    		ListaAuto lista = new ListaAuto("a"/*TODO*/, nombre, genero);
    		if (lista == null)
    			throw new ErrorCreacionObjeto("Error al crear lista auto");
    		else {
	    		//obtener lista de canciones con el genero que nos interesa
    			int duracion = 0;
	    		int i = 0;
    			ArrayList<Cancion> canciones = dao.getCancionesGeneroDB(genero.getId());
	    		Cancion cancion = canciones.get(i);
	    		duracion += cancion.getDuracion();
	    		while (duracion < duracionMax && i < canciones.size()) {
	    			this.anadirCancion(cancion, lista, usuario);
		    		cancion = canciones.get(i);
		    		duracion += cancion.getDuracion();
		    		i++;
	    		}
	    		dao.setListaAuto(lista, genero, usuario);
	    		if (i == canciones.size())
	    			throw new ErrorCreacionObjeto("No hay suficiente canciones para llegar a la duraci�n deseada");
    		}
    }

    /**
     * Guarda en la DB la pertenencia de una cancion a una lista, vamos que mete
     * la cancion en la lista
     * @param cancion cancion a anadir
     * @param lista lista objetivo
     * @throws ErrorAutenticacion 
     */
    @Override
    public void anadirCancion(Cancion cancion, Lista lista, Usuario usuario) throws ErrorAutenticacion {
    		if (dao.getCancionDB(cancion.getId()).equals(cancion)) {
    			//comprueba que vamos a anadir una cancion valida
    			lista.anadirCancion(cancion);
    			dao.setLista(lista, usuario);
    		}
    }
    
    /**
     * Elimina de la DB la pertenencia de una cancion a una lista
     * @param cancion cancion a eliminar
     * @param lista lista objetivo
     * @throws ErrorAutenticacion 
     */
    @Override
    public void eliminarCancion(Cancion cancion, Lista lista, Usuario usuario) throws ErrorAutenticacion {
    		if (dao.getCancionDB(cancion.getId()).equals(cancion)) {
    			lista.eliminarCancion(cancion);
    			dao.setLista(lista, usuario);
    		}
    }
}
