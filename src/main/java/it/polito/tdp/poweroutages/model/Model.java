package it.polito.tdp.poweroutages.model;


import java.util.LinkedList;
import java.util.List;


import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<PowerOutages> migliore;
	private double utentiMigliore;
	private List<PowerOutages> eventi;
	
	public Model() {
		podao = new PowerOutageDAO();
		
	}
	
	
	
	public double getUtentiMigliore() {
		return utentiMigliore;
	}



	public void TuttiGliEventi(Nerc n)
	{
		eventi = new LinkedList<PowerOutages>(podao.getAllEventi(n));
	}
	
	public List<PowerOutages> calcolaEventi(int X, long Y)
	{
		migliore = new LinkedList<PowerOutages>();
		utentiMigliore = 0;
		
		LinkedList<PowerOutages> parziale = new LinkedList<PowerOutages>();
		cerca(parziale,X,Y);
		return migliore;	
	}

	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	
	private void cerca(List<PowerOutages>parziale, int X, long Y)
	{
		//casi terminali
		long sommaOre = somma(parziale);
		
		if(sommaOre > Y || !range(parziale, X))
		{
			return;
		}
		
		int numUtenti = calcolaUtenti(parziale);
		
		if(numUtenti>utentiMigliore)
		{
			migliore = new LinkedList<PowerOutages>(parziale);
			utentiMigliore = numUtenti;
		}
		
			
		
		for(int i = 0; i<eventi.size(); i++)
		{
			if(!parziale.contains(eventi.get(i)))
			{
				parziale.add(eventi.get(i));
				cerca(parziale, X, Y);
				parziale.remove(eventi.get(i));
			}
			else
			{
				return;
			}
			
		}
	
		
	}
	
	public long ore_migliore (List<PowerOutages> migliore) {
		
		long s = 0;
		
		for(PowerOutages p:migliore)
		{
			s+=p.getDurata_in_ore();
		}
		
		return s;
		
	}


	private int calcolaUtenti(List<PowerOutages> parziale) {
		
		int utenti=0;
		
		for(PowerOutages p:parziale)
		{
			utenti+=p.getAffectedPeople();
		}
		
		return utenti;
		
	}

	private boolean range(List<PowerOutages> parziale, int x) {
		
		if(parziale.size() == 0)
		{
			return true;
		}
		
		if((parziale.get(parziale.size()-1).getYear() - parziale.get(0).getYear()) <= x)
		{
			return true;
		}
		
		return false;
	}

	private long somma(List<PowerOutages> parziale) {
		long s = 0;
		
		for(PowerOutages p:parziale)
		{
			s+=p.getDurata_in_ore();
		}
		
		return s;
	}

}
