package clientJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import metier.entities.Categorie;
import metier.entities.Produit;
import metier.sessions.IRemote;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;

public class ProduitFrame extends JFrame {

	private JPanel contentPane;
	private JTextField desF;
	private JTextField prixF;
	private JTextField qteF;
	private JLabel image;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProduitFrame frame = new ProduitFrame();
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
	@SuppressWarnings("unused")
	public ProduitFrame() {
		
		
		
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
		setBounds(100, 100, 595, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel desFieldla = new JLabel("Designation");
		desFieldla.setFont(new Font("Tahoma", Font.PLAIN, 15));
		desFieldla.setBounds(38, 51, 86, 24);
		contentPane.add(desFieldla);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(38, 86, 86, 24);
		contentPane.add(lblDescription);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrix.setBounds(38, 158, 80, 24);
		contentPane.add(lblPrix);
		
		desF = new JTextField();
		desF.setBounds(122, 55, 155, 20);
		contentPane.add(desF);
		desF.setColumns(10);
		
		final JTextArea descField = new JTextArea();
		descField.setBounds(122, 88, 155, 59);
		contentPane.add(descField);
		
		prixF = new JTextField();
		prixF.setBounds(122, 162, 86, 20);
		contentPane.add(prixF);
		prixF.setColumns(10);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9");
		lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantit.setBounds(38, 193, 86, 24);
		contentPane.add(lblQuantit);
		
		qteF = new JTextField();
		qteF.setColumns(10);
		qteF.setBounds(122, 197, 86, 20);
		contentPane.add(qteF);
		
		JLabel lblSelectionne = new JLabel("Selectionne");
		lblSelectionne.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelectionne.setBounds(38, 224, 86, 24);
		contentPane.add(lblSelectionne);
		
		
		
		final JRadioButton rdo = new JRadioButton("oui");
		rdo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(rdo.isSelected())
					rdn.setSelected(false);*/
				
			}
		});
		rdo.setBounds(122, 224, 46, 23);
		contentPane.add(rdo);
		
		final JRadioButton rdn = new JRadioButton("non");
		rdn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdn.isSelected())
					rdo.setSelected(false);
			}
		});
		rdn.setBounds(170, 224, 46, 23);
		contentPane.add(rdn);
		
		

		
		JButton ajoutF = new JButton("Ajouter");
		ajoutF.setBackground(SystemColor.activeCaption);
		ajoutF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String des = desF.getText();
				String desc= descField.getText();
				double prix = Double.parseDouble(prixF.getText());
				int qte = Integer.parseInt(qteF.getText());
				boolean select ;		
			    if(rdo.isSelected())
			    {
			    	select = true;
			    	rdn.setSelected(false);
			    }
			    else 
			    {
			    	select = false;
			    	rdo.setSelected(false);
			    }
			    
			    
			    BufferedImage ime = getBufferedImage(iconToImage(image.getIcon())); 
				ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
				try {
					ImageIO.write(ime, "jpg", baos);
				} catch (IOException e11) {
					// TODO Auto-generated catch block
					e11.printStackTrace();
				} byte[] res=baos.toByteArray(); 
				Encoder a=Base64.getEncoder();
				String encodedImage = a.encodeToString(baos.toByteArray());
	    
			    Produit p = new Produit(des,desc,prix,qte,select,encodedImage);
			    
			    proxy.addProduit(p);
			    
			    JOptionPane.showMessageDialog(null, "Produit inser�");
		
			}
		});
		ajoutF.setBounds(38, 305, 89, 23);
		contentPane.add(ajoutF);
		
		JButton effacerF = new JButton("Effacer");
		effacerF.setBackground(SystemColor.activeCaption);
		effacerF.setBounds(188, 305, 89, 23);
		contentPane.add(effacerF);
		
		JButton btnAddImg = new JButton("Ajouter une Image");
		btnAddImg.setBackground(SystemColor.activeCaption);
		btnAddImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame x=new JFrame();
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG & GIF Images", "jpg", "gif","png");
				fc.setFileFilter(filter);
				int o=fc.showOpenDialog(x);
				  if(o == JFileChooser.APPROVE_OPTION) {
					  ImageIcon i=new ImageIcon(fc.getSelectedFile().getPath());
					  BufferedImage a=new BufferedImage(110, 110,  BufferedImage.TYPE_INT_RGB);
					  a.createGraphics().drawImage(i.getImage(),0,0,110,110,null);
					  ImageIcon i2=new ImageIcon(a);
					  image.setIcon(i2);
				
			}
		}});
		btnAddImg.setBounds(339, 54, 155, 23);
		contentPane.add(btnAddImg);
		
		image = new JLabel("");
		image.setBounds(339, 93, 155, 137);
		contentPane.add(image);
		
		JLabel lblAjouterUnProduit = new JLabel("Ajouter un produit");
		lblAjouterUnProduit.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAjouterUnProduit.setBounds(38, 11, 169, 29);
		contentPane.add(lblAjouterUnProduit);
		
		JLabel lblNewLabel = new JLabel("Categorie");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(38, 259, 80, 24);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Technologie"}));
		comboBox.setBounds(122, 259, 107, 20);
		contentPane.add(comboBox);
		
		JButton btnConsulter = new JButton("consulter");
		btnConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consulterP cc = new consulterP();
						cc.setVisible(true);
			}
		});
		btnConsulter.setBackground(SystemColor.activeCaption);
		btnConsulter.setBounds(339, 305, 89, 23);
		contentPane.add(btnConsulter);
		
				
	
		
		
		
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static BufferedImage getBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	       return (BufferedImage) img;
	    }

	    BufferedImage bimage = new BufferedImage(img.getWidth(null), 
	                    img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	static Image iconToImage(Icon icon) {
		   if (icon instanceof ImageIcon) {
		      return ((ImageIcon)icon).getImage();
		   } 
		   else {
		      int w = icon.getIconWidth();
		      int h = icon.getIconHeight();
		      GraphicsEnvironment ge = 
		        GraphicsEnvironment.getLocalGraphicsEnvironment();
		      GraphicsDevice gd = ge.getDefaultScreenDevice();
		      GraphicsConfiguration gc = gd.getDefaultConfiguration();
		      BufferedImage image = gc.createCompatibleImage(w, h);
		      Graphics2D g = image.createGraphics();
		      icon.paintIcon(null, g, 0, 0);
		      g.dispose();
		      return image;
		   }
		 }
}
