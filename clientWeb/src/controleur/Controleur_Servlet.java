package controleur;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.entities.Commande;
import metier.entities.Ligne_Commande;
import metier.entities.Panier;
import metier.entities.Produit;
import metier.sessions.ILocal;

/**
 * Servlet implementation class Controleur_Servlet
 */
@WebServlet(name="Controleur_Produit", urlPatterns= {"/produit"})
public class Controleur_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ILocal metier;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controleur_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("vue_Produit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String critere= request.getParameter("critere");
		if(action.equals("Tous"))
		{
			List<Produit> p = metier.listeProduit();
			request.setAttribute("Produit", p);	
		}	
		
		else
		{
			switch(critere)
			{
			case "id":
				Long idProduit=Long.parseLong(request.getParameter("code"));
				request.setAttribute("code", idProduit);
				Produit p = metier.consulterProduit(idProduit);
				request.setAttribute("pr", p);
			break;
			case "motCle":
				String mot = request.getParameter("code");
				List<Produit> p2 = metier.consulterProduitCle(mot);
				request.setAttribute("Produit", p2);
			break;
			case "Technologie":
				request.setAttribute("code",critere);
				String cat = request.getParameter("critere");
				List<Produit> p3 = metier.rechercherParCat(cat);
				request.setAttribute("Produit", p3);	
				break;
			case "Smartphone":
				request.setAttribute("code",critere);
				String cat2 = request.getParameter("critere");
				List<Produit> p4 = metier.rechercherParCat(cat2);
				request.setAttribute("Produit", p4);	
				break;
			}
		}
		
		if(request.getParameter("ajouter")!=null)
		{
		Long idP = Long.parseLong(request.getParameter("id"));
		request.setAttribute("code",idP);
		/*Produit prd = metier.consulterProduit(idP);
		int qte = 1;
		Commande cmd = new Commande();
		Ligne_Commande lcd = new Ligne_Commande(prd.getIdProduit(),cmd.getIdCmd(),qte,prd.getPrix());
		Panier pn = new Panier();		
		pn.addItem(prd, qte);
		request.setAttribute("pp", metier.consulterProduit(idP));*/
		
		}
		
	
		doGet(request, response);
	}

}
