package DAO;

import java.util.ArrayList;

import Excepciones.ErrorAutenticacion;
import Excepciones.ErrorConsulta;
import Excepciones.ErrorCreacionObjeto;
import Excepciones.ErrorEliminacion;
import Excepciones.ErrorGuardado;
import Model.Objetos.*;

public interface InterfazSASDAO {
	// GET
	/**
	 * Consulta la base de datos y crea el genero solicitado
	 * @param idGenero id del genero solicitado
	 * @return genero solicitado, null si no existe
	 * @throws ErrorConsulta si no se encuentra el genero
	 * @throws ErrorCreacionObjeto si no se puede devolver
	 */
	public Genero getGeneroDB(String idGenero) throws ErrorConsulta, ErrorCreacionObjeto;
	/**
	 * Consulta la base de datos y crea la cancion solicitada
	 * @param idCancion id de la cancion solicitada
	 * @return cancion solicitada, null si no existe
	 * @throws ErrorConsulta si no se ha padido encontrar
	 * @throws ErrorCreacionObjeto si no se puede devolver 
	 */
	public Cancion getCancionDB(String idCancion) throws ErrorCreacionObjeto, ErrorConsulta;
	/**
	 * Consulta la base de datos y obtiene la lista de canciones de un genero musical
	 * @param idGenero id del genero del cual se desea obtener las canciones
	 * @return Lista de canciones, null si no existe el genero o no hay canciones
	 * @throws ErrorConsulta si no se ha padido encontrar 
	 * @throws ErrorCreacionObjeto si no se puede devolver 
	 */
	public ArrayList<Cancion> getCancionesGeneroDB(String idGenero) throws ErrorConsulta, ErrorCreacionObjeto;
	/**
	 * Consulta la base de datos y crea la lista solicitado
	 * @param idLista id de la lista solicitada
	 * @return lista solicitada, null si no existe
	 * @throws ErrorConsulta si no se ha padido encontrar 
	 * @throws ErrorCreacionObjeto si no se puede devolver 
	 */
	public Lista getListaDB(String idLista) throws ErrorConsulta, ErrorCreacionObjeto;
	/**
	 * Devuelve todas las listas del usuario
	 * @param idUsuario la id del usuario del que se quieren obtener las listas
	 * @param clave la clave del usuario
	 * @return una lista conteniendo las listas del usuario
	 * @throws ErrorCreacionObjeto si no se puede devolver 
	 * @throws ErrorConsulta si no se ha padido encontrar 
	 * @throws ErrorAutenticacion si la clave no coincide con la esperada, o el usuario no existe 
	 */
	public ArrayList<Lista> getListasDB(String idUsuario, String clave) throws ErrorAutenticacion, ErrorConsulta, ErrorCreacionObjeto;
	/**
	 * Obtiene el usuario solicitado de la DB
	 * @param idUsuario id de usuario
	 * @param clave contraseña
	 * @return usuario solicitado
	 * @throws ErrorAutenticacion si la clave no coincide con la esperada, o el usuario no existe
	 * @throws ErrorConsulta si no se ha padido encontrar 
	 * @throws ErrorCreacionObjeto si no se puede devolver 
	 */
	public Usuario getUsuarioDB(String idUsuario, String clave) throws ErrorAutenticacion, ErrorConsulta, ErrorCreacionObjeto;

	// SET
	/**
	 * Guarda la cancion en la DB, si ya existe actualiza
	 * @param cancion cancion a guardar
	 * @throws ErrorGuardado si no se ha podido guerdar
	 */
	public void setCancion(Cancion cancion) throws ErrorGuardado, ErrorCreacionObjeto;
	/**
	 * Guarda la genero en la DB y lo asocia a un usuario, si ya existe actualiza
	 * @param genero genero a guardar
	 * @param usuario usuario asociado
	 * @throws ErrorGuardado si no se ha podido guerdar 
	 * @throws ErrorAutenticacion si la clave no coincide con la esperada, o el usuario no existe 
	 */
	public void setGenero(Genero genero, Usuario usuario) throws ErrorAutenticacion, ErrorGuardado;
	/**
	 * Registra usuario o lo actualiza si ya existe
	 * @param usuario usuario a guardar
	 * @throws ErrorAutenticacion si la clave no coincide con la almacenada
	 * @throws ErrorGuardado si no se ha podido guerdar 
	 */
	public void setUsuario(Usuario usuario) throws ErrorAutenticacion, ErrorGuardado;
	/**
	 * Guarda la lista en la DB y la asocia a un usuario, si ya existe actualiza
	 * @param lista lista a guardar
	 * @param usuario usuario al que pertenece la lista
	 * @throws ErrorAutenticacion si la clave no coincide con la esperada, o el usuario no existe 
	 * @throws ErrorGuardado si no se ha podido guerdar 
	 */
	public void setLista(Lista lista, Usuario usuario) throws ErrorAutenticacion, ErrorGuardado;
	/**
	 * Guarda la lista auto en la DB y la asocia a un genero y a un usuario, si ya existe actualiza
	 * @param lista lista a guardar
	 * @param genero genero de la lista
	 * @param usuario usuario al que pertenece la lista
	 * @throws ErrorAutenticacion si la clave no coincide con la esperada, o el usuario no existe
	 * @throws ErrorGuardado si no se ha podido guerdar 
	 */
	public void setListaAuto(ListaAuto lista, Usuario usuario) throws ErrorAutenticacion, ErrorGuardado;
	
	// ELIMINAR
	/**
	 * Elimina una lista de la DB
	 * @param lista lista a eliminar
	 */
	public void eliminarLista(Lista lista) throws ErrorEliminacion;
	/**
	 * Elimina una cancion de la DB
	 * @param cancion cancion a eliminar
	 * @throws ErrorEliminacion si no se ha podido eliminar
	 */
	public void eliminarCancion(Cancion cancion) throws ErrorEliminacion;
	/**
	 * Elimina la relacion entre un genero y un usuario de la DB
	 * @param genero genero a eliminar para el usuario
	 * @param usuario usuario del genero
	 * @throws ErrorEliminacion si no se ha podido eliminar
	 */
	public void eliminarGenero(Genero genero, Usuario usuario) throws ErrorEliminacion, ErrorAutenticacion;
	/**
	 * Elimina un usuario (asi como sus listas y generos) de la DB
	 * @param usuario usuario a eliminar
	 * @throws ErrorAutenticacion si la clave no coincide con la esperada, o el usuario no existe 
	 * @throws ErrorEliminacion si no se ha podido eliminar 
	 */
	public void eliminarUsuario(Usuario usuario) throws ErrorEliminacion, ErrorAutenticacion;
}