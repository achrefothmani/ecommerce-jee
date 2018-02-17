package metier.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Panier implements Serializable {

	HashMap<Integer, Produit> item;
	
	public Panier(Map<Long, Ligne_Commande> items) {
		
		//this.items = items;
		item=new  HashMap<Integer, Produit>();
	}
	

	public Panier() {
		item=new  HashMap<Integer, Produit>();
	}


	public HashMap<Integer, Produit> getTemporalItems(){
        return item;
    }
	
	  public void addToPanier(Produit produit, Integer qte){
	        item.put(qte, produit);
	    }
	
	private Map<Long,Ligne_Commande> items = new HashMap<Long,Ligne_Commande>();
	
	public void addItem(Produit p,int qte)
	{
		Ligne_Commande lc = items.get(p.getIdProduit());
		if(lc==null)
		{
			Ligne_Commande art=new Ligne_Commande();
			art.setL_produit(p);
			art.setQte(qte);
			art.setPrix(p.getPrix());
			items.put(p.getIdProduit(),art);		
		}
		else
		{
			lc.setQte(lc.getQte()+qte);
		}
	}
	
	public Collection<Ligne_Commande> getItem()
	{
		return items.values();
	}
	
	public int getSize()
	{
		return items.size();
	}
	
	public double getTotal()
	{
		double total=0;
		for(Ligne_Commande lc:items.values())
		{
			total+=lc.getPrix()*lc.getQte();
		}
		return total;
	}
	
	public void deleteItem(Produit p )
	{
		item.clear();
	}
}
