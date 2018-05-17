package Vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import Controlador.ControlUsuario;
import Excepciones.ErrorCreacionObjeto;
import Model.Objetos.Usuario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Account_panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private VentanaPrincipal view;
	private JLabel exito ;
	/**
	 * Create the panel.
	 */

	public Account_panel(VentanaPrincipal view) {
		this.view = view;
		setBorder(new TitledBorder(null, "PERFIL", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new MigLayout("", "[445.00,grow]", "[164.00][206.00][84.00][]"));


		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, "cell 0 0,grow");
		panel_5.setLayout(new MigLayout("", "[99px][111px,grow][163px][148px,grow][116px]", "[][25px,grow][][][][][][][][][][][][]"));
		
		JPanel panel = new JPanel();
		panel_5.add(panel, "cell 1 0 3 1,grow");
		panel.setPreferredSize(new Dimension(200, 200));
		panel.setLayout(null);

		JPanel panel_6 = new JPanel();;
		

		PanelImagen panelimg = null;
		panelimg = new PanelImagen("src/icons/perfilLogo.png");
		panelimg.setBounds(160, 0, 131,131);
		panel.add(panelimg);
		
		JButton btnEditarPerfil = new JButton("Editar perfil");
		btnEditarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField.getText();
				String nombre = textField_1.getText();
				String clave = String.valueOf(passwordField.getPassword());
				if (VentanaPrincipal.entradaValida(id) && VentanaPrincipal.entradaValida(nombre)
					&& VentanaPrincipal.entradaValida(clave)) {
					ControlUsuario ctrlUsuario = new ControlUsuario(view.getUsuarioActual());
					Usuario usuario;
					try {
						usuario = new Usuario(id, nombre, clave);
						ctrlUsuario.modificar(usuario);
						exito.setText("Datos cambiados con exito");
					} catch (ErrorCreacionObjeto e) {
						exito.setText("Error, imposible cambiar los datos");
					}
				}
				else {
					
				}
			}
		});
		
				

		panel_5.add(btnEditarPerfil, "cell 3 4,growx,aligny top");
	
		JLabel lblPrimeroEditaLos = new JLabel("Primero edita los campos de");
		panel_5.add(lblPrimeroEditaLos, "cell 1 7,alignx left,aligny center");
		JLabel lblDebajoYDespus = new JLabel("debajo y después pulsa el");
		panel_5.add(lblDebajoYDespus, "cell 1 8,alignx left,aligny center");
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		panel_5.add(btnCerrarSesin, "cell 3 8,alignx left,aligny top");
		
				btnCerrarSesin.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						view.setUsuarioActual(null);
						view.setVisible(false);
						Login lgn = null;
						try {
							lgn = new Login(view);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						lgn.setVisible(true);
					}
				});
		JLabel lblBotneditarPerfil = new JLabel("botón \"Editar perfil\".");
		panel_5.add(lblBotneditarPerfil, "cell 1 10,alignx left,aligny center");
		
		JButton btnBorrarCuenta = new JButton("Borrar cuenta");
		btnBorrarCuenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ControlUsuario contr = new ControlUsuario(view.getUsuarioActual());
				int a = JOptionPane.showOptionDialog(new JFrame(), "¿Seguro que quieres borrar el usuario?", "BORRAR USUARIO",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				if (a == 0) {
					if (view.getUsuarioActual().getId().equals("u0")) {
						JOptionPane.showMessageDialog(new JFrame(), "No se puede borrar el usuario ADMINISTRADOR", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else {
						contr.borrar(view.getUsuarioActual());
						view.setVisible(false);
						Login lgn = null;
						try {
							lgn = new Login(view);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						lgn.setVisible(true);
					}
				}
			}
		});
		panel_5.add(btnBorrarCuenta, "cell 3 11");
		
		JSeparator separator = new JSeparator();
		panel_2.add(separator, "cell 0 1");
		
		
		panel_2.add(panel_6, "cell 0 2,grow");
		panel_6.setLayout(new MigLayout("", "[49px,grow][80px][][116px][][][116px][6px]", "[22px][][25.00,center][][21.00][][][]"));
		
		
			
		JLabel lblId = new JLabel("ID");
		panel_6.add(lblId, "cell 2 1,alignx left,aligny center");
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		panel_6.add(textField, "cell 5 1,alignx left,aligny top");
		
		JSeparator separator_1 = new JSeparator();
		panel_6.add(separator_1, "cell 1 2 6 1");
		JLabel lblNombre = new JLabel("NOMBRE");
		panel_6.add(lblNombre, "cell 2 3,alignx left,aligny center");
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_6.add(textField_1, "cell 5 3,alignx left,aligny top");
		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		panel_6.add(lblContrasea, "cell 2 5,alignx left,aligny center");
		
		passwordField = new JPasswordField();

		panel_6.add(passwordField, "cell 5 5,growx,aligny top");
		exito = new JLabel("");
		panel_6.add(exito, "cell 3 7");
		
		


	}
	
	public void setDatosUsuario(Usuario user) {
		
		this.passwordField.setText(user.getClave());
		this.textField.setText(user.getId());
		this.textField_1.setText(user.getNombre());
	}
}
