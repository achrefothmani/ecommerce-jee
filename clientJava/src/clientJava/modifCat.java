package clientJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metier.entities.Categorie;
import metier.sessions.IRemote;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class modifCat extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifCat frame = new modifCat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public modifCat() {
		try {
			Context ctx = null;
			String appName="";
			String moduleName="projetEJB";
			//String distinctName="";
			String beanName="Ejb_Imp";
			String remoteInterface=IRemote.class.getName();
			String name="ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface;
			Properties p = new Properties();
			final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
			p.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			ctx = new InitialContext(p);
			final IRemote proxy = (IRemote) ctx.lookup(name);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdCategorie = new JLabel("id Categorie");
		lblIdCategorie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdCategorie.setBounds(74, 68, 93, 19);
		contentPane.add(lblIdCategorie);
		
		textField = new JTextField();
		textField.setBounds(177, 69, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAttributModifier = new JLabel("attribut \u00E0 modifier");
		lblAttributModifier.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAttributModifier.setBounds(35, 109, 121, 19);
		contentPane.add(lblAttributModifier);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"choisissez", "nom ", "description"}));
		comboBox.setBounds(177, 110, 86, 20);
		contentPane.add(comboBox);
		
		JLabel lblNouveauAttribut = new JLabel("nouveau attribut");
		lblNouveauAttribut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNouveauAttribut.setBounds(45, 146, 122, 19);
		contentPane.add(lblNouveauAttribut);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(177, 147, 86, 20);
		contentPane.add(textField_1);
		
		JButton btnModifier = new JButton("modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Categorie c = proxy.getCatID(Long.parseLong(textField.getText()));
				String choix = comboBox.getSelectedItem().toString();
				String value = textField_1.getText();
				switch(choix)
				{
				case "Description":
					c.setDesc(value);
					break;
				case "nom":
					c.setNomCat(value);
					break;
				}
				proxy.updateCategorie(c);
				JOptionPane.showMessageDialog(null, "Categorie modifi�e");
				
			}
		});
		btnModifier.setBounds(78, 194, 89, 23);
		contentPane.add(btnModifier);
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
