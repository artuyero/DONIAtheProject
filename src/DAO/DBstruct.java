package DAO;

/**
 * Clase DBstruct, proporciona las serntencias SQL para la creacion de tablas, insercion,
 * eliminacion y actualizacion en maria
 */
public abstract class DBstruct {
	
	// IDs ENTIDADES UNICAS OBLIGATORIAS
	private static String idUsuarioAdmin = "u0";
	private static String idBiblioteca = "l0";
	
	// SENTENCIAS CREACION TABLAS
	private static String initTodo = "DROP TABLE 'rusuariogenero';\n" + 
			"DROP TABLE 'rlistacancion';\n" + 
			"DROP TABLE 'cancion';\n" + 
			"DROP TABLE 'video';\n" + 
			"DROP TABLE 'letra';\n" + 
			"DROP TABLE 'biblioteca';\n" + 
			"DROP TABLE 'listaauto';\n" + 
			"DROP TABLE 'genero';\n" + 
			"DROP TABLE 'listanormal';\n" + 
			"DROP TABLE 'lista';\n" + 
			"DROP TABLE 'usuarioadmin';\n" + 
			"DROP TABLE 'usuario';\n" + 
			"\n" + 
			"CREATE TABLE 'usuario' (\n" + 
			"  'usuario' varchar(100) NOT NULL,\n" + 
			"  'nombre' varchar(100) DEFAULT NULL,\n" + 
			"  'clave' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('usuario')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'usuarioadmin' (\n" + 
			"  'usuario' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('usuario'),\n" + 
			"  CONSTRAINT 'usuarioadmin_usuario_FK' FOREIGN KEY ('usuario') REFERENCES 'usuario' ('usuario') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'lista' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  'nombre' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'listanormal' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  'usuario' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista'),\n" + 
			"  KEY 'listanormal_usuario_FK' ('usuario'),\n" + 
			"  CONSTRAINT 'listanormal_lista_FK' FOREIGN KEY ('lista') REFERENCES 'lista' ('lista') ON DELETE CASCADE ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'listanormal_usuario_FK' FOREIGN KEY ('usuario') REFERENCES 'usuario' ('usuario') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'genero' (\n" + 
			"  'genero' varchar(100) NOT NULL,\n" + 
			"  'nombre' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('genero')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'listaauto' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  'genero' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista'),\n" + 
			"  KEY 'listaauto_genero_FK' ('genero'),\n" + 
			"  CONSTRAINT 'listaauto_genero_FK' FOREIGN KEY ('genero') REFERENCES 'genero' ('genero'),\n" + 
			"  CONSTRAINT 'listaauto_listanormal_FK' FOREIGN KEY ('lista') REFERENCES 'listanormal' ('lista') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'biblioteca' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista'),\n" + 
			"  CONSTRAINT 'biblioteca_lista_FK' FOREIGN KEY ('lista') REFERENCES 'lista' ('lista')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'letra' (\n" + 
			"  'texto' text NOT NULL,\n" + 
			"  'letra' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('letra')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'video' (\n" + 
			"  'enlace' varchar(100) NOT NULL,\n" + 
			"  'enlaceDescarga' varchar(100) DEFAULT NULL,\n" + 
			"  'video' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('video')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'cancion' (\n" + 
			"  'cancion' varchar(100) NOT NULL,\n" + 
			"  'titulo' varchar(100) NOT NULL,\n" + 
			"  'autor' varchar(100) DEFAULT NULL,\n" + 
			"  'duracion' int(10) unsigned DEFAULT NULL,\n" + 
			"  'album' varchar(100) DEFAULT NULL,\n" + 
			"  'genero' varchar(100) DEFAULT NULL,\n" + 
			"  'video' varchar(100) DEFAULT NULL,\n" + 
			"  'letra' varchar(100) DEFAULT NULL,\n" + 
			"  PRIMARY KEY ('cancion'),\n" + 
			"  KEY 'cancion_letra_FK' ('letra'),\n" + 
			"  KEY 'cancion_video_FK' ('video'),\n" + 
			"  KEY 'cancion_genero_FK' ('genero'),\n" + 
			"  CONSTRAINT 'cancion_genero_FK' FOREIGN KEY ('genero') REFERENCES 'genero' ('genero') ON DELETE SET NULL ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'cancion_letra_FK' FOREIGN KEY ('letra') REFERENCES 'letra' ('letra') ON DELETE SET NULL ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'cancion_video_FK' FOREIGN KEY ('video') REFERENCES 'video' ('video') ON DELETE SET NULL ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'rlistacancion' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  'cancion' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista','cancion'),\n" + 
			"  KEY 'rlistacancion_cancion_FK' ('cancion'),\n" + 
			"  CONSTRAINT 'rlistacancion_cancion_FK' FOREIGN KEY ('cancion') REFERENCES 'cancion' ('cancion') ON DELETE CASCADE ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'rlistacancion_lista_FK' FOREIGN KEY ('lista') REFERENCES 'lista' ('lista') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
			"\n" + 
			"CREATE TABLE 'rusuariogenero' (\n" + 
			"  'usuario' varchar(100) NOT NULL,\n" + 
			"  'genero' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('usuario','genero'),\n" + 
			"  KEY 'rusuariogenero_genero_FK' ('genero'),\n" + 
			"  CONSTRAINT 'rusuariogenero_genero_FK' FOREIGN KEY ('genero') REFERENCES 'genero' ('genero') ON DELETE CASCADE ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'rusuariogenero_usuario_FK' FOREIGN KEY ('usuario') REFERENCES 'usuario' ('usuario') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	private static String cancion = "CREATE TABLE 'cancion' (\n" + 
			"  'cancion' varchar(100) NOT NULL,\n" + 
			"  'titulo' varchar(100) NOT NULL,\n" + 
			"  'autor' varchar(100) DEFAULT NULL,\n" + 
			"  'duracion' int(10) unsigned DEFAULT NULL,\n" + 
			"  'album' varchar(100) DEFAULT NULL,\n" + 
			"  'genero' varchar(100) DEFAULT NULL,\n" + 
			"  'video' varchar(100) DEFAULT NULL,\n" + 
			"  'letra' varchar(100) DEFAULT NULL,\n" + 
			"  PRIMARY KEY ('cancion'),\n" + 
			"  KEY 'cancion_letra_FK' ('letra'),\n" + 
			"  KEY 'cancion_video_FK' ('video'),\n" + 
			"  KEY 'cancion_genero_FK' ('genero'),\n" + 
			"  CONSTRAINT 'cancion_genero_FK' FOREIGN KEY ('genero') REFERENCES 'genero' ('genero') ON DELETE SET NULL ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'cancion_letra_FK' FOREIGN KEY ('letra') REFERENCES 'letra' ('letra') ON DELETE SET NULL ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'cancion_video_FK' FOREIGN KEY ('video') REFERENCES 'video' ('video') ON DELETE SET NULL ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";

	private static String video = "CREATE TABLE 'video' (\n" + 
			"  'enlace' varchar(100) NOT NULL,\n" + 
			"  'enlaceDescarga' varchar(100) DEFAULT NULL,\n" + 
			"  'video' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('video')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n;";
	private static String letra = "CREATE TABLE 'letra' (\n" + 
			"  'texto' text NOT NULL,\n" + 
			"  'letra' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('letra')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String genero = "CREATE TABLE 'genero' (\n" + 
			"  'genero' varchar(100) NOT NULL,\n" + 
			"  'nombre' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('genero')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String lista = "CREATE TABLE 'lista' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  'nombre' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String rlistacancion = "CREATE TABLE 'rlistacancion' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  'cancion' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista','cancion'),\n" + 
			"  KEY 'rlistacancion_cancion_FK' ('cancion'),\n" + 
			"  CONSTRAINT 'rlistacancion_cancion_FK' FOREIGN KEY ('cancion') REFERENCES 'cancion' ('cancion') ON DELETE CASCADE ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'rlistacancion_lista_FK' FOREIGN KEY ('lista') REFERENCES 'lista' ('lista') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String listaauto = "CREATE TABLE 'listaauto' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  'genero' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista'),\n" + 
			"  KEY 'listaauto_genero_FK' ('genero'),\n" + 
			"  CONSTRAINT 'listaauto_genero_FK' FOREIGN KEY ('genero') REFERENCES 'genero' ('genero'),\n" + 
			"  CONSTRAINT 'listaauto_listanormal_FK' FOREIGN KEY ('lista') REFERENCES 'listanormal' ('lista') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String listanormal = "CREATE TABLE 'listanormal' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  'usuario' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista'),\n" + 
			"  KEY 'listanormal_usuario_FK' ('usuario'),\n" + 
			"  CONSTRAINT 'listanormal_lista_FK' FOREIGN KEY ('lista') REFERENCES 'lista' ('lista') ON DELETE CASCADE ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'listanormal_usuario_FK' FOREIGN KEY ('usuario') REFERENCES 'usuario' ('usuario') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String biblioteca = "CREATE TABLE 'biblioteca' (\n" + 
			"  'lista' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('lista'),\n" + 
			"  CONSTRAINT 'biblioteca_lista_FK' FOREIGN KEY ('lista') REFERENCES 'lista' ('lista')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String usuario = "CREATE TABLE 'usuario' (\n" + 
			"  'usuario' varchar(100) NOT NULL,\n" + 
			"  'nombre' varchar(100) DEFAULT NULL,\n" + 
			"  'clave' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('usuario')\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String rusuariogenero = "CREATE TABLE 'rusuariogenero' (\n" + 
			"  'usuario' varchar(100) NOT NULL,\n" + 
			"  'genero' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('usuario','genero'),\n" + 
			"  KEY 'rusuariogenero_genero_FK' ('genero'),\n" + 
			"  CONSTRAINT 'rusuariogenero_genero_FK' FOREIGN KEY ('genero') REFERENCES 'genero' ('genero') ON DELETE CASCADE ON UPDATE CASCADE,\n" + 
			"  CONSTRAINT 'rusuariogenero_usuario_FK' FOREIGN KEY ('usuario') REFERENCES 'usuario' ('usuario') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	private static String usuarioadmin = "CREATE TABLE 'usuarioadmin' (\n" + 
			"  'usuario' varchar(100) NOT NULL,\n" + 
			"  PRIMARY KEY ('usuario'),\n" + 
			"  CONSTRAINT 'usuarioadmin_usuario_FK' FOREIGN KEY ('usuario') REFERENCES 'usuario' ('usuario') ON DELETE CASCADE ON UPDATE CASCADE\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 \n";
	
	// IDs ENTIDADES UNICAS OBLIGATORIAS
	/**
	 * Devuelve la id de la biblioteca.
	 * @return Id de la biblioteca.
	 */
	public static String getIdBiblioteca() {
		return idBiblioteca;
	}
	/**
	 * Devuelve la id del administrador.
	 * @return Id del administrador.
	 */
	public static String getIdUsuarioAdmin() {
		return idUsuarioAdmin;
	}
	
	// SENTENCIAS CREACION TABLAS
	/**
	 * Devuelve todas las sentencias para la creacion de todas las tablas.
	 * @return La sentencia.
	 */
	public static String getInitTodo() {
		return initTodo;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla cancion.
	 * @return La sentencia.
	 */
	public static String getCancion() {
		return cancion;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla video.
	 * @return La sentencia.
	 */
	public static String getVideo() {
		return video;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla letra.
	 * @return La sentencia.
	 */
	public static String getLetra() {
		return letra;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla genero.
	 * @return La sentencia.
	 */
	public static String getGenero() {
		return genero;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla lista.
	 * @return La sentencia.
	 */
	public static String getLista() {
		return lista;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla rlistacancion.
	 * @return La sentencia.
	 */
	public static String getRlistacancion() {
		return rlistacancion;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla listaauto.
	 * @return La sentencia.
	 */
	public static String getListaauto() {
		return listaauto;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla listanormal.
	 * @return La sentencia.
	 */
	public static String getListanormal() {
		return listanormal;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla biblioteca.
	 * @return La sentencia.
	 */
	public static String getBiblioteca() {
		return biblioteca;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla usuario.
	 * @return La sentencia.
	 */
	public static String getUsuario() {
		return usuario;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla rusuariogenero.
	 * @return La sentencia.
	 */
	public static String getRusuariogenero() {
		return rusuariogenero;
	}
	/**
	 * Devuelve la sentencia para la creacion de la tabla usuarioadmin.
	 * @return La sentencia.
	 */
	public static String getUsuarioadmin() {
		return usuarioadmin;
	}
	
	//SENTENCIAS DE INSERCION
	/**
	 * Genera una sentencia para isertar datos en la tabla cancion.
	 * @param id Id de la canción.
	 * @param titulo Título de la canción. 
	 * @param autor Autor de la canción.
	 * @param duracion Duración de la canción.
	 * @param album álbum de la canción.
	 * @param genero Id del género de la canción.
	 * @param video Id del vídeo de la canción.
	 * @param letra Id de la letra de la canción.
	 * @return La sentencia.
	 */
	public static String insertCancion(String id, String titulo, String autor, double duracion, String album,
			String genero, String video, String letra) {
		return 	"INSERT INTO donia.cancion\n" +
				"(cancion, titulo, autor, duracion, album, genero, video, letra)\n" +
				"VALUES('" + id + "', " + "'" + titulo + "', " + "'" + autor + "', " + "'" + duracion + "', " +
				"'" + album + "', " + "'" + genero + "', " + "'" + video + "', " + "'" + letra + "');\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla cancion.
	 * @param id Id de la letra.
	 * @param texto La letra.
	 * @return La sentencia.
	 */
	public static String insertaLetra(String id, String texto) {
		return "INSERT INTO donia.letra\n" + 
				"(texto, letra)\n" + 
				"VALUES('"+ texto +"', '"+ id +"');\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla cancion.
	 * @param id Id del video.
	 * @param enlace Enlace al video.
	 * @param descarga Enlace a la descarga del video.
	 * @return La sentencia.
	 */
	public static String insertaVideo(String id, String enlace, String descarga) {
		return "INSERT INTO donia.video\n" + 
				"(enlace, enlaceDescarga, video)\n" + 
				"VALUES('"+ enlace +"', '"+ descarga +"', '"+ id +"');\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla usuario.
	 * @param id Id del usuario.
	 * @param nombre Nombre del usuario.
	 * @param clave Clave del usuario.
	 * @return La sentencia.
	 */
	public static String insertUsuario(String id, String nombre, String clave) {
		return "INSERT INTO donia.usuario\n" +
				"(usuario, nombre, clave)\n" +
				"VALUES('" + id + "', '" + nombre + "', '"  + clave + "')\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla genero.
	 * @param id Id del género.
	 * @return La sentencia.
	 */
	public static String insertGenero(String id) {
		return "INSERT INTO donia.genero\n" +
				"(genero)\n" +
				"VALUES('" + id + "');\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla lista.
	 * @param id Id de la lista.
	 * @param nombre Nombre de la lista.
	 * @return La sentencia.
	 */
	public static String insertLista(String id, String nombre) {
		return "INSERT INTO donia.lista\n" +
				"(lista, nombre)\n" +
				"VALUES('" + id + "', " + "'" + nombre + "');\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla listanormal.
	 * @param lista Id de la lista.
	 * @param usuario Id del usuario.
	 * @return La sentencia.
	 */
	public static String insertListaNormal(String lista, String usuario) {
		return "INSERT INTO donia.listanormal\n" +
		"(lista, usuario)\n" +
		"VALUES('" + lista + "', " + "'" + usuario + "');\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla listaauto.
	 * @param lista Id de la lista.
	 * @param genero Id del genero.
	 * @return La sentencia.
	 */
	public static String insertListaAuto(String lista, String genero) {
		return "INSERT INTO donia.listaauto\n" +
				"(lista, genero)\n" +
				"VALUES('" + lista + "', " + "'" + genero + "');\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla rlistacancion.
	 * @param lista Id de la lista.
	 * @param cancion Id de la canción.
	 * @return La sentencia.
	 */
	public static String insertRlistaCancion(String lista, String cancion) {
		return 	"INSERT INTO donia.rlistacancion\n" +
				"(lista, cancion) \n" +
				"VALUES('" + lista + "', " + "'" + cancion + "');\n";
	}
	/**
	 * Genera una sentencia para isertar datos en la tabla rgenerousuario.
	 * @param genero Id del genero.
	 * @param usuario Id del usuario.
	 * @return La sentencia.
	 */
	public static String insertRgeneroUsuario(String genero, String usuario) {
		return 	"INSERT INTO donia.rusuariogenero\n" +
				"(genero, usuario)\n" +
				"VALUES('" + genero + "', " + "'" + usuario + "');\n";
	}
	
	//SENTENCIAS DE ACTUALIZACION
	/**
	 * Genera una sentencia para actualizar datos en la tabla cancion.
	 * @param id Id de la canción.
	 * @param titulo Título de la canción. 
	 * @param autor Autor de la canción.
	 * @param duracion Duración de la canción.
	 * @param album álbum de la canción.
	 * @param genero Id del género de la canción.
	 * @param video Id del vídeo de la canción.
	 * @param letra Id de la letra de la canción.
	 * @return La sentencia.
	 */
	public static String updateCancion(String id, String titulo, String autor, double duracion, String album,
			String genero, String video, String letra) {
		return 	"UPDATE donia.cancion \n" +
				"SET titulo='" + titulo + "', autor='" + autor + "', duracion=" + duracion + 
				", album='" + album + "', genero='" + genero + "', video='" + video + "', letra='" + letra + "' \n" +
				"WHERE cancion='" + id + "'";
	}
	/**
	 * Genera una sentencia para actualizar datos en la tabla usuario.
	 * @param id Id del usuario.
	 * @param nombre Nombre del usuario.
	 * @param clave Clave del usuario.
	 * @return La sentencia.
	 */
	public static String updateUsuario(String id, String nombre, String clave) {
		return 	"UPDATE donia.usuario\n" +
				"SET nombre='" + nombre + "', clave='" + clave + "'\n" +
				"WHERE usuario='" + id + "';\n";
	}
	/**
	 * Genera una sentencia para actualizar datos en la tabla genero.
	 * @param id Id del género.
	 * @param nombre Id del género.
	 * @return La sentencia.
	 */
	public static String updateGenero(String id, String nombre) {
		return 	"UPDATE donia.genero\n" +
				"SET nombre='" + nombre + "' \n" +
				"WHERE genero='" + id + "'";
	}
	/**
	 * Genera una sentencia para actualizar datos en la tabla lista.
	 * @param id Id de la lista.
	 * @param nombre Nombre de la lista.
	 * @return La sentencia.
	 */
	public static String updateLista(String id, String nombre) {
		return "UPDATE donia.lista \n" +
				"SET nombre='" + nombre + "' \n" +
				"WHERE lista='" + id + "'";
	}
	/**
	 * Genera una sentencia para actualizar datos en la tabla listaauto.
	 * @param idLista Id de la lista.
	 * @param idGenero Id del género.
	 * @return La sentencia.
	 */
	public static String updateGeneroLista(String idLista, String idGenero) {
		return "UPDATE donia.listaauto\n" + 
				"SET genero='"+ idGenero +"' " + 
				"WHERE lista='" + idLista + "'";
	}
	
	// SENTENCIAS DE ELIMINACION
	/**
	 * Genera una sentencia para eliminar datos de la tabla lista.
	 * @param idLista Id de la lista.
	 * @return La sentencia.
	 */
	public static String deleteLista(String idLista) {
		return "DELETE FROM donia.lista \n" + 
				"WHERE lista='" + idLista + "'";
	}
	/**
	 * Genera una sentencia para eliminar datos de la tabla cancion.
	 * @param idCancion Id de la canción.
	 * @return La sentencia.
	 */
	public static String deleteCancion(String idCancion) {
		return "DELETE FROM donia.cancion \n" + 
				"WHERE cancion='" + idCancion + "'";
	}
	/**
	 * Genera una sentencia para eliminar datos de la tabla genero.
	 * @param idGenero Id del género.
	 * @return La sentencia.
	 */
	public static String deleteGenero(String idGenero) {
		return "DELETE FROM donia.genero \n" + 
				"WHERE genero='" + idGenero + "'";
	}
	/**
	 * Genera una sentencia para eliminar datos de la tabla usuario.
	 * @param idUsuario Id del usuario.
	 * @return La sentencia.
	 */
	public static String deleteUsuario(String idUsuario) {
		return "DELETE FROM donia.usuario \n" + 
				"WHERE usuario='" + idUsuario + "'";
	}
	/**
	 * Genera una sentencia para eliminar las canciones de una lista (en la tabla rlistacancion).
	 * @param idLista Id de la lista.
	 * @return La sentencia.
	 */
	public static String deleteRlistaCancion(String idLista) {
		return "DELETE FROM donia.rlistacancion \n" + 
				"WHERE lista='" + idLista + "'";
	}
	/**
	 * Genera una sentencia para eliminar datos de la tabla rgenerousuario.
	 * @param idUsuario Id del usuario.
	 * @param idGenero Id del género.
	 * @return La sentencia.
	 */
	public static String deleteRgeneroUsuario(String idUsuario, String idGenero) {
		return "DELETE FROM donia.rusuariogenero\n" + 
				"WHERE usuario='" + idUsuario + "' AND genero='" + idGenero + "';";
	}
	/**
	 * Genera una sentencia para eliminar multiples datos de la tabla rgenerousuario.
	 * @param idUsuario Id del usuario.
	 * @return La sentencia.
	 */
	public static String deleteRgenerosUsuario(String idUsuario) {
		return "DELETE FROM donia.rusuariogenero \n" + 
				"WHERE usuario='" + idUsuario + "'";
	}
}
