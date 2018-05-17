package Vista;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Model.Objetos.Cancion;

import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;

public class Enlaces_panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Enlaces_panel() {
		setLayout(new MigLayout("", "[][405.00,center]", "[20px][][22px][20px][22px][25px][][][][][][]"));
		
		JLabel lblEnlaceVerVideo = new JLabel("Ver video");
		lblEnlaceVerVideo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
		add(lblEnlaceVerVideo, "cell 1 1,alignx center,aligny top");
		
		textField = new JTextField();
		textField.setColumns(10);
		add(textField, "cell 1 2,growx,aligny top");
		
		JLabel lblNewLabel = new JLabel("Enlace descarga");
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
		add(lblNewLabel, "cell 1 4,alignx center,aligny top");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		add(textField_1, "cell 1 5,growx,aligny top");
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		add(btnVolver, "cell 1 9,alignx center,aligny top");
		
		setVisible(true);

	}
	
	public void setDatos(Cancion cancion) {
		
		this.textField.setText(cancion.getVideo().getEnlace());
		this.textField_1.setText(cancion.getVideo().getEnlaceDescarga());
	}

	public void vaciarCampos() {
		this.textField.setText("");
		this.textField_1.setText("");		
	}
}
