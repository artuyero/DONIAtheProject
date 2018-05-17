package Vista;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import Controlador.ControlLista;
import Excepciones.ErrorCreacionObjeto;
import Model.Objetos.Lista;
import Model.Objetos.ListaNormal;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;

public class ModificarLista extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField entradaNombre;

	private Lista listaSel = null;
	
	/**
	 * Create the panel.
	 * @param ventanaPrincipal 
	 */
	public ModificarLista(VentanaPrincipal ventanaPrincipal) {
		ArrayList<Lista> listas = ventanaPrincipal.getListaSelecccionada();
		if (listas != null && !listas.isEmpty()) {
			listaSel = listas.get(0);
		}
		
		String titulo;
		String aviso;
		if (listaSel == null){
			titulo = "No hay lista seleccionada";
			aviso = "Selecciona primero una lista";
		} else if (listaSel.getId().equals("l0")){
			titulo = "No hay lista seleccionada";
			aviso = "No se puede modificar la biblioteca";
		} else {
			titulo = "A\u00F1adir canciones a la lista " + listaSel.getNombre();
			aviso = "";
		}
		
		setBorder(new TitledBorder(null, titulo, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton botonModificar = new JButton("Guardar nuevo nombre");
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		setLayout(new MigLayout("", "[142.00px,right][168.00px,center][142.00px,fill]", "[76.00px][16px][29px,grow]"));
		
		entradaNombre = new JTextField();
		entradaNombre.setColumns(10);
		entradaNombre.setText("");
		add(entradaNombre, "cell 1 0,growx,aligny center");
		
		entradaNombre.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                		botonModificar.doClick();
                }
			}
		});
		add(lblNewLabel, "cell 0 0,alignx right,aligny center");
				
				JLabel lblNombreInvalido = new JLabel("NOMBRE INVALIDO");
				add(lblNombreInvalido, "cell 1 1,alignx center,aligny top");
				lblNombreInvalido.setVisible(false);
		
				add(botonModificar, "cell 1 2,alignx center,aligny top");
				
				botonModificar.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (listaSel != null && entradaNombre.getText() != null && !entradaNombre.getText().equals("") &&
									VentanaPrincipal.entradaValida(entradaNombre.getText())) {
								listaSel.setNombre(entradaNombre.getText());
								ControlLista controlador = new ControlLista(ventanaPrincipal.getUsuarioActual());
								controlador.crearLista(listaSel);
								lblNombreInvalido.setVisible(false);
							} else {
								lblNombreInvalido.setVisible(true);
							}
						}
					}
				);
				
		if (listaSel == null || listaSel.getId().equals("l0")) botonModificar.setEnabled(false);
		else botonModificar.setEnabled(true);
	}
}
