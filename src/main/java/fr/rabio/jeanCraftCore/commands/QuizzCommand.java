package fr.rabio.jeanCraftCore.commands;

import fr.rabio.jeanCraftCore.Main;
import fr.rabio.jeanCraftCore.event.GestionInvetory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuizzCommand implements CommandExecutor{
	
public Main main;

public QuizzCommand(Main main ) {


	this.main = main;
}
  

	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equals("quizz")) {
			if(sender instanceof Player) {
				 Player player = (Player) sender;
				GestionInvetory gst = new GestionInvetory(null, player);
				
				gst.ON_Inventory_Start(player);
				
				return true;
			}


		}
		return false;
		
	}
				

}
