package fr.rabio.jeanCraftCore.Autre;


import org.bukkit.entity.Player;

public class ChangeDifficult {
	
	private Difficult difficult;
	
	
	
	
	
	public void setDifficult(Difficult newdifficult) {
		
		difficult = newdifficult;
		
	}
	
	
	public Difficult getDifficult() {
		
		return difficult;
	}
	
	public void Difficult_Actuel(Player player) {
		
		if (difficult == null) {
			player.sendMessage("Aucune difficulté n'est définie.");
			return;
		}
		
		switch(difficult) {
		
		case TRES_FACILE:
			player.sendMessage("Vous avez choisi la difficulter Très Facile");
			break;
		case FACILE:
			player.sendMessage("Vous avez choisi la difficulter Facile");
			break;
		case NORMALE:
			player.sendMessage("Vous avez choisi la difficulter Normal");
			break;
		case DIFFICILE:
			player.sendMessage("Vous avez choisi la difficulter Difficile");
			break;
		case TRES_DIFFICILE:
			player.sendMessage("Vous avez choisi la difficulter Très Difficile");
			break;
		default:
			
			break;
			
		
		}
		
		
		
		
	}
	
	

}
