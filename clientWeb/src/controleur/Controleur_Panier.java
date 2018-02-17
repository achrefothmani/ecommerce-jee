package controleur;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.entities.Commande;
import metier.entities.Ligne_Commande;
import metier.entities.Panier;
import metier.entities.Produit;
import metier.entities.Cart;
import metier.sessions.ILocal;

/**
 * Servlet implementation class Controleur_Panier
 */
@WebServlet(name="Controleur_Panier", urlPatterns="/panier")
public class Controleur_Panier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	private ILocal metier;
	
	Panier panier;
	List<Produit> items;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controleur_Panier() {
        super();
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String action = request.getParameter("action");
		HttpSession session = request.getSession();
		panier = (Panier) session.getAttribute("panier");
		if(panier == null){
	         panier = new Panier();
	         session.setAttribute("panier", panier);
	       }
		if(action.equals("ajouter"))
		{
		Long id = Long.parseLong(request.getParameter("id"));
		Produit prd = metier.consulterProduit(id);
		int qte = 1;
		
		//metier.getPanier().addItem(prd, qte);
		
		metier.getPanier().addToPanier(prd, qte);
		
		session.setAttribute("panier", metier.getPanier());
		}*/
		
		 String action = request.getParameter("action");
		 HttpSession session = request.getSession();
	        Panier panier = new Panier();
	        panier = (Panier) session.getAttribute("panierP");
	        if(panier == null){
	          panier = new Panier();
	          session.setAttribute("panierP", panier);
	        }
	        if(action.equals("ajouter"))
			{
	        Long id = Long.parseLong(request.getParameter("id"));
	    	Produit prd = metier.consulterProduit(id);
	    	int qte = 1;
	    	
	    	panier.addToPanier(prd, qte);
	    	
	        session.setAttribute("panierP", panier);
			}
	        
	        if(action.equals("supprimer"))
	        {
	        	Long id = Long.parseLong(request.getParameter("id"));
		    	Produit prd = metier.consulterProduit(id);
	        	panier.deleteItem(prd);
	        	
	        }
		
		
		
		
		request.getRequestDispatcher("vuePanier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.sendRedirect("vuePanier.jsp");
		
		doGet(request, response);
	}

}
