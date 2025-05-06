package fr.rabio.jeanCraftCore.event;

import java.io.File;
import java.io.IOException;
import java.util.*;

import fr.rabio.jeanCraftCore.Autre.ChangeDifficult;
import fr.rabio.jeanCraftCore.Autre.CreateInventoryDifficultMatier;
import fr.rabio.jeanCraftCore.Autre.QuestionLoader;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import fr.rabio.jeanCraftCore.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;


public class InventoryClickDifficultAndQuestion implements Listener {
    private final ChangeDifficult changeDifficult;
    private final QuestionLoader questionLoader;
    private final Main main;
    public InventoryQuestionPnj pnj;
    private FileConfiguration playerData;
    private File playerDataFile;
    private int Addition_fin;

    //cette variable permet récupérer les point des joueur pour pouvoirsd les addition a la fin
    //cela evite au personne de commencer puis de quitter tout en récupéreant les point car il devront attendre seulementy la fin
    //pour   pouvoir récupérer leur point


    public InventoryClickDifficultAndQuestion(ChangeDifficult changeDifficult, Main main,InventoryQuestionPnj pnj) {
        this.changeDifficult = changeDifficult;
        this.main = main;
        this.questionLoader = new QuestionLoader(main);
        this.pnj = pnj;
        
        
    }
    
     private final Set<Player> changingInventory = new HashSet<>();

    //cette liste sert a styoker les joueur quand il repond a une question pour eviter qui  si il quitte
    //ou arrive quoi que sois tous soi réanistaliser pour permettre de pas avoir des problémes avec des donné qui son restante


    StringBuilder questionKeyBuilder = new StringBuilder("Q1");  // Commence à "Q1" ou autre selon ta logique actuelle
    // Ajoute un '1' pour obtenir "Q11", "Q111", etc.
    String nextQuestionKey = questionKeyBuilder.toString();
    String difficulté = "";
  
    String subject = "";
    int complete = 0;
    @EventHandler
    public void onInventoryCancel(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return; // Évite de tenter d'accéder à un item null ou vide
        }

        ItemMeta itemMeta = clickedItem.getItemMeta();
        if (itemMeta == null || !itemMeta.hasDisplayName()) {
            return; // Pareil, on évite d'aller plus loin si le meta est null
        }



        if (event.getView().getTitle().equals(ChatColor.AQUA +"Defis")) { // Vérifie que c'est l'inventaire du QCM
            event.setCancelled(true);

            player.sendMessage("test de la debug 1");
        }else{

            if (event.getView().getTitle().equals(ChatColor.GOLD +"Questions-Cours")) { // Vérifie que c'est l'inventaire du QCM
                event.setCancelled(true);

                player.sendMessage("test de la debug 2");

            }




            if (event.getView().getTitle().equals("Difficulté")) { // Vérifie que c'est l'inventaire du QCM
                event.setCancelled(true);



            }


        }



    }
    @EventHandler
    public void onInventoryDifficultClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();


        Player player = (Player) event.getWhoClicked();
  
        loadPlayerData();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        ItemMeta itemMeta = clickedItem.getItemMeta();

        if (itemMeta == null || !itemMeta.hasDisplayName()) {
            return; // Pareil, on évite d'aller plus loin si le meta est null
        }

        String itemName = itemMeta.getDisplayName();
        
        
        List<String> ReponseMatier = questionLoader.getMatierExist();


        
        //vérifie que si le l'objet a le nom d'un matier et bien redéfinir le subject
        if(ReponseMatier.contains(itemName)) {
        	 subject = itemName;

             player.sendMessage("§a La matier est existante !!! " + subject );
        	
        	 
        	 
        	 

        	 
        }else {
            //en grois elle verifi quye si lobject selectionner  a pas le nom de la  matier et bah elle va  la redéfinir si elle vient d'un pnj
        	  if(!ReponseMatier.contains(subject)) {
                  player.sendMessage("§4 La matier est pas existantoooo" + subject );
        	
        	if(ReponseMatier.contains(pnj.getMatier())) {
        		subject = pnj.getMatier();
        	}
        	  }
        	
        }
        

        
            // Incrémentation de la clé de la question
        List<String> ReponseDifficult = questionLoader.getDifficultExist();
        
        if(ReponseDifficult.contains(itemName)) { //en gros il vérifie que si le nom du truc sélectionner fait partie des
            //difficulté alors faire ça
        	
        	difficulté = itemName;
            player.sendMessage("La Difficulter à eté chosir" + difficulté );

            List<Map.Entry<String, List<String>>> nextQuestions = questionLoader.getQuestions(subject, difficulté, "Q1");


            if (!nextQuestions.isEmpty()) {
                player.sendMessage("tout va bien le nextquestion du premier inventaire et pas vide ");

                if(!hasCompletedQuiz(player,subject,difficulté)) {
                    complete = 1;

                    Map.Entry<String, List<String>> nextQuestion = nextQuestions.get(0); // Récupère la première question
                    CreateInventoryDifficultMatier df = new CreateInventoryDifficultMatier(main, player);


                    df.createInventory(player, "Question", nextQuestion.getKey(), nextQuestion.getValue());
                    player.sendMessage("la création ud inventaire 1er" + player + nextQuestion.getKey() +"et aussi"+ nextQuestion.getValue());

                }else{
                    GestionInvetory gstinv = new GestionInvetory(main, player);


                    gstinv.On_Inventory_Difficult(player);
                    player.closeInventory();
                    player.sendMessage("Vous avez déjà fait ce quizz ");

   
                }
                //player.sendMessage("§2 le truc s'appel attention :" + difficulté + "Notre sujet est" + subject);
       }else {

                GestionInvetory gstinv = new GestionInvetory(main, player);


                gstinv.On_Inventory_Difficult(player);
                player.closeInventory();
           player.sendMessage("Vous avez terminé toutes les questions !");
       }
            
        }
        
       
}


//cette fonction permet de vérifier la réponse si elle est bonne ou pas

    
    @EventHandler
    public void onInventoryQuestionVerifClick(InventoryClickEvent event) {
    	
    	ItemStack clickedItem = event.getCurrentItem();


        Player player = (Player) event.getWhoClicked();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }


        ItemMeta itemMeta = clickedItem.getItemMeta();


        if (itemMeta == null || !itemMeta.hasDisplayName()) {
            return; // Pareil, on évite d'aller plus loin si le meta est null
        }

        String itemName = itemMeta.getDisplayName();
        
        List<String> correctreponse = questionLoader.getCorrectAnswers();
        List<Map.Entry<String, List<String>>> nextQuestions = questionLoader.getQuestions(subject, difficulté, nextQuestionKey);
        
        File file = new File(main.getDataFolder(), "point.yml");

    	YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    	String key = "players." + player.getUniqueId();

    	int point = config.getInt(key + ".point",0);
        
        if(correctreponse.contains(itemName) ) {
        //si lobjet contien la bonne réponse et bien faire en sorte de ajouter 1 point et  apperler la fonction vérifier
        	changingInventory.remove(player);
        	
        	player.sendMessage("Bonne réponse !" + point);
        	
        	Addition_fin += 1;

        	
             vérification(player);
     
        		
        	  
        }else {
        	  List<String> ReponseDifficult = questionLoader.getDifficultExist();
        	  
        	  changingInventory.remove(player);


        	if(ReponseDifficult.contains(difficulté)) {
        		
        		
        	    if(!ReponseDifficult.contains(itemName)) {


        			player.sendMessage("Mauvaise réponse !");


        		vérification(player);
        	    }
        		
        	}
        	
        }
        
      
        event.setCancelled(false);
    }
    
    
    public String vérification(Player player) {
    	File file = new File(main.getDataFolder(), "point.yml");

    	YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    	String key = "players." + player.getUniqueId();

    	int point = config.getInt(key + ".point",0);
    	   List<Map.Entry<String, List<String>>> nextQuestions = questionLoader.getQuestions(subject, difficulté, nextQuestionKey);
    	   
    	//en gros le double if evite un debordement par exemple si je veux la question 2 bah si elle y pas bah il va pas me l'afficher
        // mais si
        //questionKeyBuilder.append('1'); et bien ça pourrait regler mais je ne les pas mis car je crois ç_a marchait ou ça incrémentait pas bien
                // ce qui provoqsuer cdes soucis dou pourquoi j'ai fait comme ça


        if (!nextQuestions.isEmpty()) {
			player.sendMessage("virus 1");
    		
		
			 questionKeyBuilder.append('1');
			
         	  nextQuestionKey = questionKeyBuilder.toString();
         	 List<Map.Entry<String, List<String>>> nextQuestionss = questionLoader.getQuestions(subject,difficulté, nextQuestionKey);
         	 player.sendMessage("youpppiiiii" + nextQuestionKey);
         	
       
		 if (!nextQuestionss.isEmpty()) {

             player.sendMessage("virus 2");

                complete = 1;
           Map.Entry<String, List<String>> nextQuestion = nextQuestionss.get(0); // Récupère la première question
           CreateInventoryDifficultMatier df = new CreateInventoryDifficultMatier(main, player);
           df.createInventory(player, "Question", nextQuestion.getKey(), nextQuestion.getValue());
           player.sendMessage("H" + nextQuestionKey);
           changingInventory.add(player);
          
		
		 }else {
		     
		      
		      questionKeyBuilder = new StringBuilder("Q1");
			  nextQuestionKey = questionKeyBuilder.toString();

             File fileC = new File(main.getDataFolder(), "classement.yml");

             YamlConfiguration configC = YamlConfiguration.loadConfiguration(fileC);
             String keyC = "players." + player.getUniqueId();

             int pointC = configC.getInt(keyC + ".point",0);

             point +=  Addition_fin;
             config.set(key + ".point",point);
             configC.set(keyC + ".point",point);

             try {
                 config.save(file);
                 configC.save(fileC);
             } catch (IOException e) {

                 e.printStackTrace();
             }
			  
			  player.sendMessage(ChatColor.DARK_AQUA  + "Vous avez terminer toute les questions de ce niveau !");
			  player.sendMessage("Vos point actuel sont " + point);

             GestionInvetory gstinv = new GestionInvetory(main, player);


             gstinv.On_Inventory_Difficult(player);

			  player.closeInventory();
			  changingInventory.remove(player);
             BlockQuestion(player,subject,difficulté);


			 
       
		 }
		 
		}else {

            if(complete == 1) {
                player.sendMessage("The End");

                reset(player);
                changingInventory.remove(player);
                BlockQuestion(player, subject, difficulté);
            }else{
                player.sendMessage("toujours non 1 ");
            }
           
		     
		}
    	player.sendMessage("NONn ça marche pas return nexquestion key pour inventoruclickdifficult and question");
    	return nextQuestionKey;
    }
    
    
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Code à exécuter lorsque le joueur ferme l'inventaire
    	Player player = (Player) event.getPlayer();
    	
    	if(changingInventory.contains(player)) {

    	reset(player);
    	}
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Code à exécuter lorsque le joueur se déconnecte
    	Player player = event.getPlayer();
    	reset(player);
      
    }
    
    
    
    
    public void reset(Player player) {
    	  questionKeyBuilder = new StringBuilder("Q1");
		  nextQuestionKey = questionKeyBuilder.toString();
		  difficulté = "";
		   subject = "";
		   pnj.SetMatier("");
		   complete = 0;
           Addition_fin = 0;
        player.sendMessage("BUAHHHHHH 1 ");
        player.sendMessage("§4 Le reset c'est bien effectuer");
        player.sendMessage("§4 Le reset c'est bien effectuer");
        player.sendMessage("§4 Le reset c'est bien effectuer");
        player.sendMessage("§4 Le reset c'est bien effectuer");
        player.sendMessage("§4 Le reset c'est bien effectuer");
        player.sendMessage("§4 Le reset c'est bien effectuer");
        player.sendMessage("§4 Le reset c'est bien effectuer");
        player.sendMessage("§4 Le reset c'est bien effectuer");
        player.sendMessage("§4 Le reset c'est bien effectuer");




    }


    public void loadPlayerData() {
        playerDataFile = new File(main.getDataFolder(), "player_data.yml");
        if (!playerDataFile.exists()) {

            main.saveResource("player_data.yml", false);
        }
        playerData = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    // Sauvegarder le fichier YAML
    public void savePlayerData() {
        try {
            playerData.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Vérifie et ajoute le joueur s'il n'existe pas
    public void ensurePlayerExists(Player player) {
        String path = "players." + player.getUniqueId();
        if (!playerData.isConfigurationSection(path)) {
            playerData.createSection(path); // CRÉE UNE SECTION YAML PROPRE
            savePlayerData();
        }
    }

    // Vérifie si le joueur a complété un quizz
    public boolean hasCompletedQuiz(Player player, String subject, String difficulty) {
        ensurePlayerExists(player); // Vérifie que le joueur est bien enregistré
        String path = "players." + player.getUniqueId() + "." + subject + "." + difficulty;
        player.sendMessage("BUAHHHHHH 11 ");
        return playerData.getBoolean(path, false);
    }

    // Marquer un quizz comme complété
    public void markQuizCompleted(Player player, String subject, String difficulty) {
        ensurePlayerExists(player); // Vérifie que le joueur est bien enregistré
        String path = "players." + player.getUniqueId() + "." + subject + "." + difficulty;

        playerData.set(path, true);
        savePlayerData();
        player.sendMessage("BUAHHHHHH 111 ");
    }

    public void BlockQuestion(Player player,String sujet,String difficulté){

        //loadPlayerData();
        ensurePlayerExists(player);
        markQuizCompleted(player,subject,difficulté);
        savePlayerData();
        player.sendMessage("BUAHHHHHH 1111 ");

    }





    
}