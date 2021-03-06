package Vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import Controlador.ControlGenero;
import Controlador.ControlLista;
import Model.Objetos.Cancion;
import Model.Objetos.Lista;

/**
 * Clase que crea la barra de herramientas de las canciones la cual contiene los botones de añadir canción a la 
 * lista seleccionada, eliminar canción de la lista seleccionada, darle me gusta a la canción actual, y ver
 * letra de la canción actual.
 */
public class ToolBarCanciones extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Crea y añade una barra herramientas con funciones sobre las canciones a la ventana principal.
	 * @param mainWindow Ventana principal a la qeu se añade la barra de herramientas sobre canciones
	 */
	public ToolBarCanciones(VentanaPrincipal mainWindow) {
		
		super();
		setLayout(new GridLayout(1, 4));
		
		JButton aniadirCancion = new JButton();
		aniadirCancion.setToolTipText("Añade una cancion");
		aniadirCancion.setText("Añadir cancion");
		aniadirCancion.setHorizontalTextPosition( SwingConstants.CENTER );
		aniadirCancion.setVerticalTextPosition( SwingConstants.BOTTOM );
		aniadirCancion.setIcon(new ImageIcon(ToolBarCanciones.class.getResource("/image/add.png")));
		aniadirCancion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainWindow.verAniadirCancionALista();
			}
		});
		this.add(aniadirCancion);
		
		
		JButton eliminarCancion = new JButton();
		eliminarCancion.setToolTipText("Borra una cancion de la lista");
		eliminarCancion.setText("Borrar cancion");
		eliminarCancion.setHorizontalTextPosition( SwingConstants.CENTER );
		eliminarCancion.setVerticalTextPosition( SwingConstants.BOTTOM );
		eliminarCancion.setIcon(new ImageIcon(ToolBarCanciones.class.getResource("/image/delete.png")));
		eliminarCancion.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Cancion> selC = mainWindow.getCancionSelecccionada();
				ArrayList<Lista> selL = mainWindow.getListaSelecccionada();
				if (selC != null && !selC.isEmpty() && selL != null && !selL.isEmpty() ) {
					
					for (Cancion c : selC) {
						for (Lista l : selL) {
							ControlLista control = new ControlLista(mainWindow.getUsuarioActual());
							control.eliminarCancion(c, l);
						}
					}
				}
			}
		});		
		this.add(eliminarCancion);
		
		
		JButton meGusta = new JButton();
		meGusta.setToolTipText("Añadir el genero de la cancion a tus gustos");
		meGusta.setText("Me gusta!");
		meGusta.setHorizontalTextPosition( SwingConstants.CENTER );
		meGusta.setVerticalTextPosition( SwingConstants.BOTTOM );
		meGusta.setIcon(new ImageIcon(ToolBarCanciones.class.getResource("/image/hand.png")));
		meGusta.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<Cancion> sel = mainWindow.getCancionSelecccionada();
				if (sel != null && !sel.isEmpty()) {
					
					for (Cancion c : sel) {
						ControlGenero control = new ControlGenero(mainWindow.getUsuarioActual());
						control.Anadir(c.getGenero());
					}
				}
			}
		 });
		this.add(meGusta);
		
		JButton verLetra = new JButton();
		verLetra.setToolTipText("Ver la letra de la cancion");
		verLetra.setText("Ver letra");
		verLetra.setHorizontalTextPosition( SwingConstants.CENTER );
		verLetra.setVerticalTextPosition( SwingConstants.BOTTOM );
		verLetra.setIcon(new ImageIcon(ToolBarCanciones.class.getResource("/image/VerLetra.png")));
		verLetra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				mainWindow.verPanelLetras();
			}
		 });
		this.add(verLetra);
	}
}
