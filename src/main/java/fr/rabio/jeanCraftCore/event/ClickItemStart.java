package fr.rabio.jeanCraftCore.event;

// Importation des classes nécessaires pour gérer les événements dans Bukkit
import org.bukkit.ChatColor; // Permet de colorer les textes dans le chat
import org.bukkit.Location;
import org.bukkit.Material; // Définit les matériaux (objets) manipulés dans Minecraft
import org.bukkit.World;
import org.bukkit.entity.Player; // Représente un joueur dans Minecraft
import org.bukkit.event.*; // Fournit les interfaces et classes pour les événements
import org.bukkit.event.block.Action; // Enum pour représenter les actions des joueurs (clic droit, clic gauche, etc.)
import org.bukkit.event.inventory.InventoryClickEvent; // Événement déclenché lorsqu'un joueur interagit avec un inventaire
import org.bukkit.event.player.PlayerInteractEvent; // Événement déclenché lorsqu'un joueur interagit (clic droit/gauche)
import org.bukkit.inventory.ItemStack; // Représente un objet dans l'inventaire d'un joueur

import fr.rabio.jeanCraftCore.Main; // Importation de la classe principale du plugin
import org.bukkit.inventory.meta.ItemMeta;

// Classe qui gère les interactions des joueurs avec les objets et les inventaires
public class ClickItemStart implements Listener {

	private Main main;

	private int testchat;
	// Référence à l'instance principale du plugin

	// Constructeur pour initialiser la classe avec une instance de Main
	public ClickItemStart(Main main) {
		this.main = main;
	}

	/**
	 * Méthode pour gérer l'interaction du joueur avec un objet spécifique.
	 * L'événement est déclenché lorsqu'un joueur effectue une action (clic droit/gauche).
	 */
	@EventHandler // Annotation indiquant que cette méthode écoute un événement
	public void ClickItemChoice_Game(PlayerInteractEvent event) {
		// Récupère le joueur qui a déclenché l'événement
		Player player = event.getPlayer();
	testchat = 1;
		// Récupère l'action effectuée par le joueur (clic droit dans l'air ou sur un bloc)
		Action action = event.getAction();

		if (event.getAction() == null) {


			player.sendMessage("TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
			return; // Pareil, on évite d'aller plus loin si le meta est null
		}

		// Instance de GestionInvetory pour gérer les inventaires personnalisés
		GestionInvetory gstinv = new GestionInvetory(main, player);

		// Vérifie si le joueur a effectué un clic droit (dans l'air ou sur un bloc)
		if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			// Récupère l'objet tenu en main par le joueur
			ItemStack itemEnMain = player.getInventory().getItemInMainHand();

			if (itemEnMain == null || itemEnMain.getType().isAir()) {
				return; // Aucun item en main, on arrête ici
			}

			// Vérifie si l'item a un ItemMeta et un display name


			ItemMeta meta = itemEnMain.getItemMeta();
			if (meta == null || !meta.hasDisplayName()) {
				return;
			}


			// Vérifie si l'objet en main est une SLIME_BALL et si son nom est "jeux"
			if (itemEnMain.getType() == Material.SLIME_BALL && itemEnMain.getItemMeta().getDisplayName().equals("jeux")) {
				gstinv.ON_Inventory_Start(player);
				// Ouvre un inventaire spécifique pour permettre au joueur d'accéder aux quiz
			}
		}
	}

	/**
	 * Méthode pour gérer les clics dans un inventaire personnalisé.
	 * L'événement est déclenché lorsqu'un joueur clique sur un élément d'inventaire.
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		// Récupère l'objet sur lequel le joueur a cliqué
		ItemStack clickedItem = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();

		if (clickedItem == null || clickedItem.getType().isAir()) {
			return;
		}

		ItemMeta itemMeta = clickedItem.getItemMeta();

		if (itemMeta != null || itemMeta.hasDisplayName()) {

			event.setCancelled(true);


			 // Pareil, on évite d'aller plus loin si le meta est null
		}



		// Récupère le joueur qui a cliqué

		// Instance de GestionInvetory pour gérer les inventaires
		GestionInvetory gstinv = new GestionInvetory(main, player);

		// Vérifie si le clic était sur un emplacement vide ou si l'objet est invalide


		// Vérifie si l'objet cliqué est une pomme avec le nom "Questions-Cours"
		if (clickedItem.getType() == Material.APPLE && clickedItem.getItemMeta().getDisplayName().equals((ChatColor.GOLD + "Questions-Cours"))) {
			gstinv.InventoryGame(player); // Ouvre l'inventaire des questions de cours

			event.setCancelled(true);

		}

		// Vérifie si l'objet cliqué est une flèche avec le nom "Retour"
		if (clickedItem.getType() == Material.ARROW && clickedItem.getItemMeta().getDisplayName().equals((ChatColor.RED + "Retour"))) {

/*



Je ouvre un inventaire puios le referme imédiatement pour que la personne ne puisse pas prendre de item dans cette
inventaire parce que si sinon je devrai mettre event.setCancelled(true); mais si je fait ça on pourrait plus rien faire
dans l'inventaire normal donc je fait ça pour garder cette redondance
 */
			gstinv.InventoryGame(player);
			player.closeInventory(); // Ferme l'inventaire du joueur
			player.sendMessage("§4 Votre inventaire à été fermer"); // Envoie un message de confirmation
			event.setCancelled(true);



		}

		// Vérifie si l'objet cliqué est un beacon avec le nom "Defis"
		if (clickedItem.getType() == Material.BEACON && clickedItem.getItemMeta().getDisplayName().equals((ChatColor.AQUA + "Defis"))) {
			gstinv.On_Defis_Inventory(); // Ouvre l'inventaire des défis
			player.sendMessage("§9 Ouverture de l'inventaire defis"); // Message de confirmation
			event.setCancelled(true);

		}



		if (clickedItem.getType() == Material.TNT && clickedItem.getItemMeta().getDisplayName().equals(("Spawn"))) {
			gstinv.On_Defis_Inventory(); // Ouvre l'inventaire des défis
			player.sendMessage("§7 Téléportation  au Spawn ! ");
			World w = player.getWorld();
			Location loc = new Location(w,90,70,90);
			player.teleport(loc);




			event.setCancelled(true);

		}



		// Vérifie si l'objet cliqué est un Ender Eye avec le nom "Question-Culture-Général"
		if (clickedItem.getType() == Material.ENDER_EYE && clickedItem.getItemMeta().getDisplayName().equals((ChatColor.LIGHT_PURPLE + "Question-Culture-Général"))) {
			gstinv.On_Culture_Inventory(); // Ouvre l'inventaire des questions de culture générale
			player.sendMessage("§9 Ouverture de l'inventaire culture général"); // Message de confirmation

			event.setCancelled(true);


		}

		// Annule l'événement pour évitfer tout comportement par défaut
		event.setCancelled(false);
	}
}
