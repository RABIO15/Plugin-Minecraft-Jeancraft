package fr.rabio.jeanCraftCore.event;

import java.util.List;

import fr.rabio.jeanCraftCore.Autre.QuestionLoader;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.rabio.jeanCraftCore.Main;


public class InventoryQuestionPnj implements Listener {
	
  
    private final QuestionLoader questionLoader;
    private final Main main;
    public String matier;
   public  String subject;
    
	 public InventoryQuestionPnj( Main main,String subject) {
	     
	        this.main = main;
	        this.questionLoader = new QuestionLoader(main);
	        this.subject = subject; 
	      
	        
	 }
	 
	 StringBuilder questionKeyBuilder = new StringBuilder("Q1");  
	   
	    String nextQuestionKey = questionKeyBuilder.toString();
	    String difficulté = "";
	   

	 @EventHandler
	    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

	       
	        Player player = event.getPlayer();    

	         
	        GestionInvetory gest = new GestionInvetory(main, player);
	        if(event.getRightClicked().getType() == EntityType.VILLAGER) {
	        	
	        	Villager villager = (Villager) event.getRightClicked();
	        	String itemName = villager.getName();
	        	
	        	  
	            List<String> ReponseMatier = questionLoader.getMatierExist();

	            if(villager.getProfession() == Villager.Profession.NONE) {
	            	
	            	
        			player.sendMessage("bravo");
        			
        			SetMatier("");
        			subject = "";
        			
        			
        		
	            if(ReponseMatier.contains(itemName)) {


	            	gest.On_Inventory_Difficult(player);
	            	subject = itemName;
	            	SetMatier(itemName);

					//je utilise le setMatier pour pouvoirs le reutilliser plus tard dans la classe click difficult and question
	            	player.sendMessage("§4 Message de debugage getter subject :" + getMatier());
	        
	            	
	            	 
	            }
	            
	            }
	        	
	        	
	        }
	 }
	 
	 
	 public String getMatier() {
		 System.out.print("Message de DEBUGGAGE  pour la classe pnj ligne 72 pour retourner la matier: " + subject);
		 
		return subject;
		 
	 }
public String  SetMatier(String subject) {
	this.subject = subject;
	 System.out.print("Message de DEBUGGAGE  pour la classe pnj ligne 72 pour retourner la matier: " + subject);
	
	return subject;
	
}
	 
	 
	 
}


