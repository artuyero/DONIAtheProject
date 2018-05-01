package Controlador.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import Excepciones.ErrorCreacionObjeto;
import Modelo.Objetos.*;

public class SASDAO implements InterfazSASDAO {
	
	private static final String JDBC = "org.mariadb.jdbc.Driver";
	private static final String DBsys = "mariadb";
	private static final String DBhost = "127.0.0.1";
	private static final String DBnom = "donia";
	private static String DBserver = "jdbc:mariadb://127.0.0.1/donia";
	private Connection DBconn = null;
	//acceso
	private static final String USER = "usr";
    private static final String PASS = "usr";
    
    public SASDAO() {
    		SASDAO.DBserver = "jdbc:" + SASDAO.DBsys + "://" + DBhost + "/" + SASDAO.DBnom;
    		try {
				this.initDB();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    /**
     * Inicializa la estructura de la base de datos. Para la primera ejecucion.
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    private void initDB() throws ClassNotFoundException, SQLException {
    		//de algun modo deberi crear una mariaDB en localhost que sa llame donia con user = usr pass = usr
    		this.conectaDB();
    		//tests
        Statement stmt = this.DBconn.createStatement();
        stmt.executeUpdate(DBstruct.getCancion());
    }
    
    /**
     * Inicializa conexion con maria
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    private void conectaDB() throws SQLException, ClassNotFoundException {
        //driver
        Class.forName(SASDAO.JDBC);
        //conectame
        System.out.println("Conectando a MariaDB...");
        this.DBconn = DriverManager.getConnection(DBserver, USER, PASS);
        System.out.println("Conectao!");
    }
    
    public void cierraDB() throws SQLException {
    		this.DBconn.close();
    }
    
    /**
     * Chack de si esta conectado a la DB
     * @return
     * @throws SQLException 
     */
    private boolean conectado() throws SQLException {
    		return this.DBconn != null && !this.DBconn.isClosed();
    }
    
    public Cancion getCancionDB(String idCancion) {
    		try {
			if (this.conectado() && idCancion != null) {
				Cancion cancion = null;
			    PreparedStatement stat = this.DBconn.prepareStatement("select * from cancion where cancion = " + idCancion + ";");
			    ResultSet datosCancion = stat.executeQuery();
				if(datosCancion.next()) {
					//si ha leido bien lo que queriamos
					String id = datosCancion.getString("cancion");
					String titulo = datosCancion.getString("titulo");
					String autor = datosCancion.getString("autor");
					String album = datosCancion.getString("album");
					int duracion = datosCancion.getInt("duracion");
					String idLetra = datosCancion.getString("letra");
					String idVideo = datosCancion.getString("video");
					String idGenero = datosCancion.getString("genero");
					
					Letra letra = null;
					if (idLetra != null)
					letra = this.getLetraDB(idLetra);
					
					Video video = null;
					if (idVideo != null)
					video = this.getVideoDB(idVideo);
					
					Genero genero = null;
					if (idGenero != null)
					genero = this.getGeneroDB(idGenero);
					
					cancion = new Cancion(id, titulo, autor, album, duracion, letra, video, genero);
				}
				return cancion;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorCreacionObjeto e) {
			e.printStackTrace();
		}
		return null;
    }

    private Video getVideoDB(String idVideo) {
    		try {
			if (this.conectado() && idVideo != null) {
				Video video = null;
			    PreparedStatement stat = this.DBconn.prepareStatement("select * from video where video = " + idVideo + ";");
			    ResultSet datosVideo = stat.executeQuery();
				if(datosVideo.next()) {
					//si ha leido bien lo que queriamos
					String id = datosVideo.getString("cancion");
					String enlace = datosVideo.getString("enlace");
					String enlaceDescarga = datosVideo.getString("enlaceDescarga");
					
					video = new Video(id, enlace, enlaceDescarga);
				}
				return video;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorCreacionObjeto e) {
			
		}
		return null;
	}

	private Letra getLetraDB(String idLetra) {
		try {
			if (this.conectado() && idLetra != null) {
				Letra letra = null;
			    PreparedStatement stat = this.DBconn.prepareStatement("select * from letra where letra = " + idLetra + ";");
			    ResultSet datosLetra = stat.executeQuery();
				if(datosLetra.next()) {
					//si ha leido bien lo que queriamos
					String id = datosLetra.getString("letra");
					String texto = datosLetra.getString("texto");
					
					letra = new Letra(id, texto);
				}
				return letra;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorCreacionObjeto e) {
			
		}
		return null;
	}

	public Lista getListaDB(String idLista) {
		try {
			if (this.conectado() && idLista != null) {
				Lista lista = null;
			    PreparedStatement stat = this.DBconn.prepareStatement("select * from cancion where cancion = " + idLista + ";");
			    ResultSet datosLista = stat.executeQuery();
				if(datosLista.next()) {
					//si ha leido bien lo que queriamos
					String id = datosLista.getString("lista");
					String titulo = datosLista.getString("nombre");
					
					/*
					 * 
					 * MIRAR SI ESTA BIEN LO SIGUIENTE
					 * 
					 */
					
					List<Cancion> canciones = null;
					
					String idCancion = datosLista.getString("cancion");;
					while (idCancion != null) {
						canciones.add(this.getCancionDB(idCancion));
						idCancion = datosLista.getString("cancion");
					}
					
					lista = new ListaNormal(id, titulo, canciones);
				}
				return lista;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorCreacionObjeto e) {
			e.printStackTrace();
		}
		return null;  
	}

    public Genero getGeneroDB(String idGenero) {
    	try {
			if (this.conectado() && idGenero != null) {
				Genero genero = null;
			    PreparedStatement stat = this.DBconn.prepareStatement("select * from cancion where cancion = " + idGenero + ";");
			    ResultSet datosGenero = stat.executeQuery();
				if(datosGenero.next()) {
					//si ha leido bien lo que queriamos
					String id = datosGenero.getString("genero");
					String nombre = datosGenero.getString("nombre");
					
					genero = new Genero(id, nombre);
				}
				return genero;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorCreacionObjeto e) {
			e.printStackTrace();
		}
		return null;
    }

    public Usuario getUsuarioDB(String idUsuario, String clave) {
    	try {
			if (this.conectado() && idUsuario != null) {
				Usuario usuario = null;
			    PreparedStatement stat = this.DBconn.prepareStatement("select * from cancion where cancion = " + idUsuario + ";");
			    ResultSet datosUsuario = stat.executeQuery();
				if(datosUsuario.next()) {
					//si ha leido bien lo que queriamos
					String id = datosUsuario.getString("usuario");
					String titulo = datosUsuario.getString("nombre");
					String autor = datosUsuario.getString("clave");
					
					/*
					 * 
					 * MIRAR SI ESTA BIEN LO SIGUIENTE
					 * 
					 */
					
					List<Genero> generos = null;
					
					String idGenero = datosUsuario.getString("genero");;
					while (idGenero != null) {
						generos.add(this.getGeneroDB(idGenero));
						idGenero = datosUsuario.getString("cancion");
					}
					
					usuario = new Usuario(id, titulo, autor, null);
				}
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorCreacionObjeto e) {
			e.printStackTrace();
		}
		return null;   
	}

    public void setCancion(Cancion cancion) {
    }

    public void setUsuario(Usuario usuario) {
    }

    public void setGenero(Genero genero) {
    }

    public void setLista(Lista lista) {
    }
}
