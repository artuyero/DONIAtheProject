package Controlador;

import Excepciones.ErrorAutenticacion;
import Excepciones.ErrorConsulta;
import Excepciones.ErrorCreacionObjeto;
import Model.Lista.InterfazListaFachada;
import Model.Objetos.Cancion;
import Model.Objetos.Genero;
import Model.Objetos.Lista;
import Model.Objetos.Usuario;
import Vista.VentanaPrincipal;

public class ControlLista {

	//el controlador necesita el usuario actual para acceder a solo a sus listas
	Usuario usuarioActual = null;
	InterfazListaFachada fLista;
	VentanaPrincipal ventanaPrincipal;

	public ControlLista(VentanaPrincipal ventanaPrincipal, InterfazListaFachada fLista,
			Usuario usuarioActual) {
		this.setfLista(fLista);
		this.setUsuarioActual(usuarioActual);
		this.setVentanaPrincipal(ventanaPrincipal);
	}

	private void setfLista(InterfazListaFachada fLista) {
		this.fLista = fLista;
	}

	private void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	
	public void crearLista(String nombre) {
	    	try {
	    		fLista.crearLista(nombre, usuarioActual);
	    	} catch (ErrorAutenticacion | ErrorCreacionObjeto e) {
	    		ventanaPrincipal.muestraError(e);
	    	}
    }

    public void crearListaAuto(String nombre, Genero genero, int duracion) {
    		try {
    			fLista.crearListaAuto(nombre, genero, usuarioActual, duracion);
		} catch (ErrorAutenticacion | ErrorCreacionObjeto e) {
			ventanaPrincipal.muestraError(e);
		}
    }

	public Lista consulta(String idLista) {
    		try {
				return fLista.consulta(idLista);
			} catch (ErrorConsulta e) {
				ventanaPrincipal.muestraError(e);
			}
    		return null;
	}

	public void borrar(Lista lista) {
		fLista.borrar(lista, usuarioActual);
	}

	public void modificar(String nombre, Lista lista) {
		try {
			fLista.modificar(nombre, lista, usuarioActual);
		} catch (ErrorAutenticacion e) {
			ventanaPrincipal.muestraError(e);
		}		
	}

	public void anadirCancion(Cancion cancion, Lista lista) {
		try {
			fLista.anadirCancion(cancion, lista, usuarioActual);
		} catch (ErrorAutenticacion e) {
			ventanaPrincipal.muestraError(e);
		}
	}

	public void eliminarCancion(Cancion cancion, Lista lista) {
		try {
			fLista.eliminarCancion(cancion, lista, usuarioActual);
		} catch (ErrorAutenticacion e) {
			ventanaPrincipal.muestraError(e);
		}
	}
}
