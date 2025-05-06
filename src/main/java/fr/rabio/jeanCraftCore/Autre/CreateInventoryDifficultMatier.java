package fr.rabio.jeanCraftCore.Autre;

// Importation des classes nécessaires pour la gestion des inventaires et des joueurs
import java.util.List;

import org.bukkit.Bukkit; // Permet d'accéder aux fonctionnalités principales de Bukkit
import org.bukkit.Material; // Définit les matériaux pour les objets (comme le papier)
import org.bukkit.entity.Player; // Représente un joueur dans Minecraft
import org.bukkit.inventory.Inventory; // Représente un inventaire (conteneur d'objets)
import org.bukkit.inventory.ItemStack; // Représente un objet dans un inventaire
import org.bukkit.inventory.meta.ItemMeta; // Permet de définir des métadonnées pour un objet (comme un nom ou une description)

import fr.rabio.jeanCraftCore.Main; // Importation de la classe principale du plugin

// Classe pour créer un inventaire personnalisé pour un joueur
public class CreateInventoryDifficultMatier {
    private Main main; // Référence à l'instance principale du plugin
    private Player player; // Référence au joueur pour lequel l'inventaire est créé

    // Constructeur : initialise l'objet avec le plugin principal et un joueur
    public CreateInventoryDifficultMatier(Main main, Player player) {
        this.main = main; // Stocke l'instance principale du plugin
        this.player = player; // Stocke le joueur
    }

    /**
     * Méthode pour créer un inventaire personnalisé avec une question et des réponses.
     * @param player Le joueur pour lequel l'inventaire est créé
     * @param inventoryName Le nom de l'inventaire (non utilisé ici)
     * @param question La question affichée comme titre de l'inventaire
     * @param answers La liste des réponses possibles à afficher sous forme d'objets
     */
    public void createInventory(Player player, String inventoryName, String question, List<String> answers) {
        // Exemple des paramètres :
        // player = un joueur connecté
        // inventoryName = "Quiz"
        // question = "Quelle est la capitale de la France ?"
        // answers = ["Paris", "Lyon", "Marseille"]

        // Création d'un inventaire de 9 emplacements avec pour titre la question
        Inventory inventory = Bukkit.createInventory(player, 9, question); //j'ai changer le null par player
        // Remarque : L'inventaire est limité à 9 emplacements ici (1 rangée). Vous pouvez ajuster à 18, 27, etc., si nécessaire.

        // Boucle pour parcourir la liste des réponses et les ajouter à l'inventaire
        for (int i = 0; i < answers.size() && i < 9; i++) {
            String answer = answers.get(i); // Récupère la réponse actuelle
            // Exemple : Si answers = ["Paris", "Lyon", "Marseille"], alors answer sera "Paris" au premier passage

            // Création d'un objet de type papier pour représenter une réponse
            ItemStack answerItem = new ItemStack(Material.PAPER); // Chaque réponse sera affichée comme un papier
            ItemMeta answerMeta = answerItem.getItemMeta(); // Permet d'ajouter des métadonnées à l'objet
            answerMeta.setDisplayName(answer); // Définit le nom affiché de l'objet (ex. "Paris")
            answerItem.setItemMeta(answerMeta); // Applique les métadonnées à l'objet

            // Place l'objet dans l'inventaire à l'indice correspondant (de 0 à 8)
            inventory.setItem(i, answerItem);
        }

        // Ouvre l'inventaire personnalisé pour le joueur
        player.openInventory(inventory);
        player.sendMessage("§b L'ouverturee de l'inventaire c'est bien effectuer avec succée ");


        // Une fois appelé, le joueur verra l'inventaire avec les options.
        // Exemple : Si question = "Quelle est la capitale de la France ?" et answers = ["Paris", "Lyon", "Marseille"],
        // le joueur verra un inventaire avec 3 papiers intitulés "Paris", "Lyon" et "Marseille".
    }
}






