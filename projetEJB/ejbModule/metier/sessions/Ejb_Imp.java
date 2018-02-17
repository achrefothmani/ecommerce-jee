package metier.sessions;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Cart;
import metier.entities.Categorie;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Panier;
import metier.entities.Produit;
import metier.entities.Promotion;
import metier.entities.Role;
import metier.entities.User;

@Stateless
public class Ejb_Imp implements ILocal, IRemote {

	@PersistenceContext
	private EntityManager em;
	
	
	private Panier panier;
	
	private Cart cart;
	
	@Override
	public Panier getPanier() {
		return panier;
	}
	
	@PostConstruct
	@Override
	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	
	@Override
	public Cart getCart() {
		return cart;
	}

	@PostConstruct
	@Override
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
	@Override
	public void addProduit(Produit prd)
	{
		em.persist(prd);
	}
	
	@Override
	public void addPromotion(Promotion prm)
	{
		em.persist(prm);
	}

	@Override
	public void addCategorie(Categorie cat) {
		em.persist(cat);
	}

	@Override
	public void addUser(User user) {
		em.persist(user);
		
	}

	@Override
	public void updateProduit(Produit prd) {
		em.merge(prd);
	}

	@Override
	public void updatePromotion(Promotion prm) {
		em.merge(prm);
	}

	@Override
	public void updateCategorie(Categorie cat) {
		em.merge(cat);
	}

	@Override
	public void deleteProduit(Long idProduit) {
		Produit p = em.find(Produit.class , idProduit);
		em.remove(p);
	}

	@Override
	public void deletePromotion(Long idProm) {
		Promotion prm = em.find(Promotion.class , idProm);
		em.remove(prm);
	}

	@Override
	public void deleteCategorie(Long idCat) {
		Categorie c = em.find(Categorie.class , idCat);
		em.remove(c);
	}

	@Override
	public void deleteUser(Long idUser) {
		User u = em.find(User.class , idUser);
		em.remove(u);
	}


	@Override
	public List<Produit> listeProduit() {
		Query query = em.createQuery("SELECT p FROM Produit p");
		@SuppressWarnings("unchecked")
		List<Produit> listePrd = (List<Produit>)query.getResultList();
		return listePrd;
	}

	
	@Override
	public Produit consulterProduit(Long idProduit) {
		Produit p = em.find(Produit.class , idProduit);
		if (p==null) throw new RuntimeException("Produit Introuvable");
		return p;
	}
	
	
	
	

	@Override
	public Commande enregistrerCommandes(Panier p, Client c) {
		em.persist(c);
		Commande cmd = new Commande();
		cmd.setClt(c);
		cmd.setLigne_commande(p.getItem());
		em.persist(cmd);
		return cmd ; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> consulterProduitCle(String cle) {
		Query query = em.createQuery("SELECT p FROM Produit p WHERE designation LIKE :x OR description LIKE:x");
		query.setParameter("x","%"+cle+"%");
		return (List<Produit>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> rechercherParCat(String cat) {
		Query query = em.createQuery("SELECT c FROM Categorie c WHERE nomCategorie LIKE:cat");
		query.setParameter("cat","%"+cat+"%");
		Categorie c = (Categorie) query.getSingleResult();
		Long id = c.getIdCat();
		Query query2 = em.createQuery("SELECT p FROM Produit p WHERE idCat=:x");
		query2.setParameter("x",id);
		return (List<Produit>) query2.getResultList();
		
	}

	@Override
	public User getUser(String login, String password) {
		Query query = em.createQuery("SELECT u FROM User u WHERE login=:x1 AND pass=:x2");
		query.setParameter("x1",login);
		query.setParameter("x2",password);
		User c = (User) query.getSingleResult();
		return c;
	}

	@Override
	public Categorie getCategorie(String cat) {
		Query query = em.createQuery("SELECT c FROM Categorie c WHERE nomCat=:x");
		query.setParameter("x",cat);
		Categorie c = (Categorie) query.getSingleResult();
		return c;
	}

	@Override
	public List<Categorie> listeCategorie() {
		Query query = em.createQuery("SELECT cat FROM Categorie cat");
		@SuppressWarnings("unchecked")
		List<Categorie> listeCat = (List<Categorie>)query.getResultList();
		return listeCat;
	}

	@Override
	public Role getRole(Long id) {
		Role r = em.find(Role.class , id);
		if (r==null) throw new RuntimeException("Role Not Found");
		return r;
	}

	@Override
	public Categorie getCatID(Long id) {
		Categorie cat = em.find(Categorie.class , id);
		if (cat==null) throw new RuntimeException("Categorie Introuvable");
		return cat;
	}

	

	
	

	
	
	
}
