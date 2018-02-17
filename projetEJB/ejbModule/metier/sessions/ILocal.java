package metier.sessions;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Cart;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Panier;
import metier.entities.Produit;
import metier.entities.Role;
import metier.entities.User;

@Local
public interface ILocal {
	
	public List<Produit> listeProduit();
	public Produit consulterProduit(Long idProduit);
	public List<Produit> consulterProduitCle(String cle );
	public Commande enregistrerCommandes(Panier p, Client c);
	public List<Produit> rechercherParCat(String cat);
	public Panier getPanier();
	public void setPanier(Panier p);
	public Cart getCart();
	public void setCart(Cart c);
	public void addUser(User user);
	public Role getRole(Long id);

}