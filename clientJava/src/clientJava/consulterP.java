package clientJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import metier.sessions.IRemote;
import net.proteanit.sql.DbUtils;
import metier.entities.*;
import javax.swing.JTable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class consulterP extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnmodifier;
	private JButton btnSupprimer;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consulterP frame = new consulterP();
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
	public consulterP() {
		
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
		setBounds(100, 100, 591, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 25, 555, 184);
		contentPane.add(table);
		
		JButton btnAfficher = new JButton("afficher");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Produit> listeP = proxy.listeProduit();
				 for(int i = 0; i <listeP.size(); i++)
			        {
			            table.setModel(new DefaultTableModel(
			                    new Object[][] {
			                            {listeP.get(i).getIdProduit(),listeP.get(i).getDesc(),listeP.get(i).getDesignation(),listeP.get(i).getPrix()}
			                    },
			                    new String[] {
			                        "Id Produit", "Description", "Designation", "Prix"
			                    }
			                ));
			        }
			}
		});
		btnAfficher.setBackground(SystemColor.activeCaption);
		btnAfficher.setBounds(10, 246, 89, 23);
		contentPane.add(btnAfficher);
		
		btnmodifier = new JButton("modifier");
		btnmodifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifP p = new modifP();
				p.setVisible(true);
			}
		});
		btnmodifier.setBackground(SystemColor.activeCaption);
		btnmodifier.setBounds(109, 246, 89, 23);
		contentPane.add(btnmodifier);
		
		btnSupprimer = new JButton("supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long id = Long.parseLong(idField.getText());
				proxy.deleteProduit(id);
				JOptionPane.showMessageDialog(null, "Produit Supprim�e");
			}
		});
		btnSupprimer.setBackground(SystemColor.activeCaption);
		btnSupprimer.setBounds(188, 293, 107, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblIdProduit = new JLabel("ID Produit");
		lblIdProduit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdProduit.setBounds(10, 291, 71, 23);
		contentPane.add(lblIdProduit);
		
		idField = new JTextField();
		idField.setBounds(81, 294, 86, 20);
		contentPane.add(idField);
		idField.setColumns(10);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
}
