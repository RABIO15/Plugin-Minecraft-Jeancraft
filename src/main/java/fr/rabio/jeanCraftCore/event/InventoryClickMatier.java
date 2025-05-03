package fr.rabio.jeanCraftCore.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import fr.rabio.jeanCraftCore.Main;

public class InventoryClickMatier implements Listener {
    public Main main;

    public InventoryClickMatier(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        GestionInvetory gestion = new GestionInvetory(main, player);

  
        
        
        
        
        
        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        if (clickedItem.getItemMeta() == null || clickedItem.getItemMeta().getDisplayName() == null) {
            return;
        }

        ItemMeta itemMeta = clickedItem.getItemMeta();
        if (itemMeta == null || !itemMeta.hasDisplayName()) {
            return; // Pareil, on évite d'aller plus loin si le meta est null
        }

        String itemName = itemMeta.getDisplayName();
    
        
        /* Matier selectedMatier = null;

        for (Matier matier : Matier.values()) {
            
                selectedMatier = matier;
                break;
            
        }
        */
        switch(itemName) {
        case "Français":
        	 gestion.On_Inventory_Difficult(player);
        	break;
        case "Math":
       	 gestion.On_Inventory_Difficult(player);

       	break;

           case "Sport":
                gestion.On_Inventory_Difficult(player);

                break;
            case "Langue":
                gestion.On_Inventory_Difficult(player);

                break;
            case "Svt":
                gestion.On_Inventory_Difficult(player);

                break;
            case "Histoire":
                gestion.On_Inventory_Difficult(player);

                break;
            case "Physique-Chimie":
                gestion.On_Inventory_Difficult(player);

                break;
            case "Economie":
                gestion.On_Inventory_Difficult(player);

                break;
            case "Pse":
                gestion.On_Inventory_Difficult(player);

                break;

        	
        default:
        	
        	break;
        }

       
    }
}
