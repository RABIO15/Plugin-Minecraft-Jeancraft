package fr.rabio.jeanCraftCore.Autre;

public enum Difficult {

	TRES_FACILE("Très Facile",1),
	FACILE("Facile",2),
	NORMALE("Normal",3),
	DIFFICILE("Difficile",4),
	TRES_DIFFICILE("Très Difficile",5);
	
	public String name;
	public int values;
	
	private Difficult(String name,int values) {
	this.name = name;	
	this.values = values;
	}
	
	
	
	public String namee() {
		return this.name;
	}
	
	  
	
}