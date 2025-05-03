package fr.rabio.jeanCraftCore.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.rabio.jeanCraftCore.Main;

public class GestionInvetory {
	
public Main main;
public Player player;


public GestionInvetory(Main main,Player player) {
	this.main = main;
	this.player = player;
}


    

//Cette fonction ouvre un invetaire ou on peut choisir ce que on souahite faire si on veut quizz defis ou quizz court ou cultur général.
public void ON_Inventory_Start(Player player) {
	

	 Inventory inventaire = Bukkit.createInventory(null, 27, ChatColor.BLUE +"Jeux");
	    
	    
	   
	    
	    ItemStack objet_deco1 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
	    ItemMeta objet_deco_meta1 = objet_deco1.getItemMeta();
	    objet_deco_meta1.setDisplayName(" ");

	    objet_deco1.setItemMeta(objet_deco_meta1);
	    
	    ItemStack objet_deco2 = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
	    ItemMeta objet_deco_meta2 = objet_deco2.getItemMeta();
	    objet_deco_meta2.setDisplayName(" ");  

	    objet_deco2.setItemMeta(objet_deco_meta2);
	    ItemStack objet_deco3 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
	    ItemMeta objet_deco_meta3 = objet_deco3.getItemMeta();
	    objet_deco_meta3.setDisplayName(" ");

	  
	    objet_deco3.setItemMeta(objet_deco_meta3);
	    
	    
	    
	    
	    
	    ItemStack objet_jeux1 = new ItemStack(Material.APPLE);
	    ItemMeta objet_jeux_meta1 = objet_jeux1.getItemMeta();
	    objet_jeux_meta1.setDisplayName(ChatColor.GOLD +"Questions-Cours");
	    
	    
	    objet_jeux1.setItemMeta(objet_jeux_meta1);

	    ItemStack objet_jeux2 = new ItemStack(Material.BEACON);
	    ItemMeta objet_jeux_meta2 = objet_jeux2.getItemMeta();
	    objet_jeux_meta2.setDisplayName(ChatColor.AQUA +"Defis");

	    objet_jeux2.setItemMeta(objet_jeux_meta2);

	    ItemStack objet_jeux3 = new ItemStack(Material.ENDER_EYE);
	    ItemMeta objet_jeux_meta3 = objet_jeux3.getItemMeta();
	    objet_jeux_meta3.setDisplayName(ChatColor.LIGHT_PURPLE + "Question-Culture-Général");  
	    objet_jeux3.setItemMeta(objet_jeux_meta3);
	    
	    ItemStack objet_jeux4 = new ItemStack(Material.TNT);
	    ItemMeta objet_jeux_meta4 = objet_jeux4.getItemMeta();
	    objet_jeux_meta4.setDisplayName("Spawn");

	    objet_jeux4.setItemMeta(objet_jeux_meta4);
	    
	    ItemStack objet_retour = new ItemStack(Material.ARROW);
	    ItemMeta objet_retourmeta = objet_retour.getItemMeta();
	    objet_retourmeta.setDisplayName(ChatColor.RED + "Retour");

	    objet_retour.setItemMeta(objet_retourmeta);


	    inventaire.setItem(0, objet_deco1);
	    inventaire.setItem(2, objet_deco1);
	    inventaire.setItem(4, objet_deco1);
	    inventaire.setItem(6, objet_deco1);
	    inventaire.setItem(8, objet_deco1);
	    inventaire.setItem(18, objet_deco1);
	    inventaire.setItem(20, objet_deco1);
	    
	    inventaire.setItem(24, objet_deco1);
	    inventaire.setItem(26, objet_deco1);
	    inventaire.setItem(1, objet_deco2);
	    inventaire.setItem(7, objet_deco2);
	    inventaire.setItem(9, objet_deco2);
	    inventaire.setItem(17, objet_deco2);
	    inventaire.setItem(25, objet_deco2);
	    inventaire.setItem(3, objet_deco3);
	    inventaire.setItem(5, objet_deco3);
	    inventaire.setItem(10, objet_deco3);
	    inventaire.setItem(16, objet_deco3);
	    inventaire.setItem(21, objet_deco3);
	    inventaire.setItem(23, objet_deco3);
	    inventaire.setItem(19, objet_deco2);
	    inventaire.setItem(16, objet_deco3);
	    inventaire.setItem(15, objet_deco2);
	  
	    
	    
	    
	    
	    
	    inventaire.setItem(12, objet_jeux1);
	    inventaire.setItem(13, objet_jeux2);	   
	    inventaire.setItem(11, objet_jeux3);
	    inventaire.setItem(14, objet_jeux4);
	    
	    
	    
	    inventaire.setItem(22, objet_retour);
	    
	  player.openInventory(inventaire);

	
	
	
	
	
	
}
//Cette inventaire ouvre  le choix des matier pour le quizz
public void InventoryGame(Player player) {
	
	
	Inventory inventaire = Bukkit.createInventory(null, 9,ChatColor.ITALIC +"Choix de Matier");

	
	 ItemStack Matierechoix = new ItemStack(Material.BLUE_WOOL);
       ItemMeta Matierchoix_meta = Matierechoix.getItemMeta();
       Matierchoix_meta.setDisplayName("Français");
       Matierechoix.setItemMeta(Matierchoix_meta);
       inventaire.setItem(0, Matierechoix);
     
     
      
         
       ItemStack Matierechoix2 = new ItemStack(Material.BLACK_WOOL);
       ItemMeta Matierchoix_meta2 = Matierechoix2.getItemMeta();
       Matierchoix_meta2.setDisplayName("Sport");
       Matierechoix2.setItemMeta(Matierchoix_meta2);
       inventaire.setItem(1, Matierechoix2);
       
       
       
       ItemStack Matierechoix3 = new ItemStack(Material.RED_WOOL);
       ItemMeta Matierchoix_meta3 = Matierechoix3.getItemMeta();
       Matierchoix_meta3.setDisplayName("Math");
       Matierechoix3.setItemMeta(Matierchoix_meta3);
       inventaire.setItem(2, Matierechoix3);
       
       
       
       ItemStack Matierechoix4 = new ItemStack(Material.GREEN_WOOL);
       ItemMeta Matierchoix_meta4 = Matierechoix4.getItemMeta();
       Matierchoix_meta4.setDisplayName("Langue");
       Matierechoix4.setItemMeta(Matierchoix_meta4);
       inventaire.setItem(3, Matierechoix4);
       
       
       ItemStack Matierechoix5 = new ItemStack(Material.GRAY_WOOL);
       ItemMeta Matierchoix_meta5 = Matierechoix5.getItemMeta();
       Matierchoix_meta5.setDisplayName("Svt");
       Matierechoix5.setItemMeta(Matierchoix_meta5);
       inventaire.setItem(4, Matierechoix5);
         
       
       ItemStack Matierechoix6 = new ItemStack(Material.MAGENTA_WOOL);
       ItemMeta Matierchoix_meta6 = Matierechoix6.getItemMeta();
       Matierchoix_meta6.setDisplayName("Pse");
       Matierechoix6.setItemMeta(Matierchoix_meta6);
       inventaire.setItem(5, Matierechoix6);
       
       
       
       ItemStack Matierechoix7 = new ItemStack(Material.LIME_WOOL);
       ItemMeta Matierchoix_meta7 = Matierechoix7.getItemMeta();
       Matierchoix_meta7.setDisplayName("Economie");
       Matierechoix7.setItemMeta(Matierchoix_meta7);
       inventaire.setItem(6, Matierechoix7);
       
       
       
       ItemStack Matierechoix8 = new ItemStack(Material.YELLOW_WOOL);
       ItemMeta Matierchoix_meta8 = Matierechoix8.getItemMeta();
       Matierchoix_meta8.setDisplayName("Histoire");
       Matierechoix8.setItemMeta(Matierchoix_meta8);
       inventaire.setItem(7, Matierechoix8);
       
       ItemStack Matierechoix9 = new ItemStack(Material.CYAN_WOOL);
       ItemMeta Matierchoix_meta9 = Matierechoix9.getItemMeta();
       Matierchoix_meta9.setDisplayName("Physique-chimie");
       Matierechoix9.setItemMeta(Matierchoix_meta9);
       inventaire.setItem(8, Matierechoix9);
       
       player.openInventory(inventaire);  
}

//Cette fonction ouvre l'inventaire pour les defis question
public void On_Defis_Inventory() {
 Inventory inventaire = Bukkit.createInventory(null, 36,ChatColor.AQUA+ "Defis");
     
 ItemStack objet_jeux1 = new ItemStack(Material.GOLD_NUGGET);
 ItemMeta objet_jeux_meta1 = objet_jeux1.getItemMeta();
 objet_jeux_meta1.setDisplayName("Defis1");
 objet_jeux1.setItemMeta(objet_jeux_meta1);
 inventaire.setItem(0, objet_jeux1);
 
 ItemStack objet_jeux11 = new ItemStack(Material.GOLD_NUGGET);
 ItemMeta objet_jeux_meta11 = objet_jeux11.getItemMeta();
 objet_jeux_meta11.setDisplayName("Défis#2");
 objet_jeux11.setItemMeta(objet_jeux_meta11);
 inventaire.setItem(1, objet_jeux11);

 ItemStack objet_jeux111 = new ItemStack(Material.GOLD_NUGGET);
 ItemMeta objet_jeux_meta111 = objet_jeux111.getItemMeta();
 objet_jeux_meta111.setDisplayName("Défis#3");
 objet_jeux111.setItemMeta(objet_jeux_meta111);
 
 
 inventaire.setItem(2, objet_jeux111);

 
 
 ItemStack objet_jeux12 = new ItemStack(Material.GOLD_NUGGET);
 ItemMeta objet_jeux_meta12 = objet_jeux12.getItemMeta();
 objet_jeux_meta12.setDisplayName("Défis#4");
 objet_jeux12.setItemMeta(objet_jeux_meta12);
 inventaire.setItem(3, objet_jeux12);
 
 ItemStack objet_jeux51 = new ItemStack(Material.GOLD_NUGGET);
 ItemMeta objet_jeux_meta51 = objet_jeux51.getItemMeta();
 objet_jeux_meta51.setDisplayName("Défis#5");
 objet_jeux51.setItemMeta(objet_jeux_meta51);
 inventaire.setItem(4, objet_jeux51);
        
        
        player.openInventory(inventaire);
	
}
//Fonction qui ouvre l'inventaire du joueurs pour le quizz culture général
public void On_Culture_Inventory() {
	 Inventory inventaire = Bukkit.createInventory(null, 27,ChatColor.AQUA+ "Culturegénérale");
	     
	
	 
	 ItemStack objet_jeux51 = new ItemStack(Material.TERRACOTTA);
	 ItemMeta objet_jeux_meta51 = objet_jeux51.getItemMeta();
	 objet_jeux_meta51.setDisplayName("Question n1");
	 objet_jeux51.setItemMeta(objet_jeux_meta51);
	 inventaire.setItem(4, objet_jeux51);
	        
	        
	        player.openInventory(inventaire);
		
	}




//permet de ouvrir l'inventaire qui permet de choisir la difficulter pour le joueur
public void On_Inventory_Difficult(Player player) {
	
	
	 Inventory inventaire = Bukkit.createInventory(null, 9,"Difficulté");
     
     ItemStack objet_decotooo = new ItemStack(Material.BEDROCK);
        inventaire.setItem(0, objet_decotooo);
        
        ItemStack objet_decotoooo = new ItemStack(Material.BEDROCK);
        inventaire.setItem(1, objet_decotoooo);
        
        ItemStack objet_jeux1 = new ItemStack(Material.BLUE_ICE);
        ItemMeta objet_jeux_meta1 = objet_jeux1.getItemMeta();
        objet_jeux_meta1.setDisplayName("Très Facile");
        objet_jeux1.setItemMeta(objet_jeux_meta1);
        inventaire.setItem(2, objet_jeux1);
        
        ItemStack objet_jeux3 = new ItemStack(Material.LIGHT_BLUE_TERRACOTTA);
        ItemMeta objet_jeux_meta3 = objet_jeux3.getItemMeta();
        objet_jeux_meta3.setDisplayName("Facile");
        objet_jeux3.setItemMeta(objet_jeux_meta3);
        inventaire.setItem(3, objet_jeux3);
        
        ItemStack objet_jeux4 = new ItemStack(Material.SEA_LANTERN);
        ItemMeta objet_jeux_meta4 = objet_jeux4.getItemMeta();
        objet_jeux_meta4.setDisplayName("Normal");
        objet_jeux4.setItemMeta(objet_jeux_meta4);
        inventaire.setItem(4, objet_jeux4);
     
        
        ItemStack objet_jeux5 = new ItemStack(Material.ORANGE_TERRACOTTA);
        ItemMeta objet_jeux_meta5 = objet_jeux5.getItemMeta();
        objet_jeux_meta5.setDisplayName("Difficile");
        objet_jeux5.setItemMeta(objet_jeux_meta5);
        inventaire.setItem(5, objet_jeux5);
        
        
        ItemStack objet_jeux2 = new ItemStack(Material.NETHER_WART_BLOCK);
        ItemMeta objet_jeux_meta2 = objet_jeux2.getItemMeta();
        objet_jeux_meta2.setDisplayName("Très Difficile");
        objet_jeux2.setItemMeta(objet_jeux_meta2);
        inventaire.setItem(6, objet_jeux2);
        
        ItemStack objet_decoto = new ItemStack(Material.BEDROCK);
        inventaire.setItem(7, objet_decoto);
        ItemStack objet_decotoo = new ItemStack(Material.BEDROCK);
        inventaire.setItem(8, objet_decotoo);
        
        player.openInventory(inventaire);
}







}









