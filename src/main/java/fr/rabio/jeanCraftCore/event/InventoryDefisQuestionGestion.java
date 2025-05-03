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


public class InventoryDefisQuestionGestion implements Listener {



    private final QuestionLoader questionLoader;
    private final Main main;
    public InventoryQuestionPnj pnj;
    private FileConfiguration playerData;
    private File playerDataFile;
    private int Addition_fin;
    private boolean ByPass_NextQuestionIsEmpty;
    private int Q_q; //en gros cette variabe  va permetre de genre faire le premier Q1 sans que le truc sincrémente
    //2 fois la premier fois donc que quan,d je clique la premier fois que ça fasse qpas Q11 mais Q1











    public InventoryDefisQuestionGestion( Main main, InventoryQuestionPnj pnj) {

        this.main = main;

        this.questionLoader = new QuestionLoader(main,"test");

        this.pnj = pnj;


    }

    private final Set<Player> changingInventory = new HashSet<>();

    StringBuilder questionKeyBuilder = new StringBuilder("Q1");  // Commence à "Q1" ou autre selon ta logique actuelle
    // Ajoute un '1' pour obtenir "Q11", "Q111", etc.
    String nextQuestionKey = questionKeyBuilder.toString();
    //String difficulté = "";

    String subject = "";
    int complete = 0;
    //deja toucher



    @EventHandler
    public void onInventoryCancel(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return; // Évite de tenter d'accéder à un item null ou vide
        }

        ItemMeta itemMeta = clickedItem.getItemMeta();
        if (itemMeta == null || !itemMeta.hasDisplayName()) {


            return;


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
            player.sendMessage("NOOOOOOO");
            return; // Pareil, on évite d'aller plus loin si le meta est null
        }

        String itemName = itemMeta.getDisplayName();

//il faut verifier cette fonctionnalité 26/04/25
        List<String> ReponseMatier = questionLoader.getDefisExist();

        //vérifie que si le l'objet a le nom d'un matier et bien redéfinir le subject

        if(ReponseMatier.contains(itemName)) {


            subject = itemName;
            player.sendMessage("§9 HAPPY HAPPY HAPPY");


            ByPass_NextQuestionIsEmpty = true;



//le probléme tu fais que j'arrive pas aller plus que la premier question au inventaire defis vien de la 19/04/25
        }else {
            player.sendMessage("§4 GET OUTTT !!!!!!!" + subject);
            //en grois elle verifi quye si lobject selectionner  a pas le nom de la  matier et bah elle va  la redéfinir si elle vient d'un pnj
            if(!ReponseMatier.contains(subject)) {



                if(ReponseMatier.contains(pnj.getMatier())) {
                    subject = pnj.getMatier();
                    ByPass_NextQuestionIsEmpty = true;
                    player.sendMessage("BILMLMGE  T ");

                }
            }

        }

        //y a peut être un truc la



    if(ByPass_NextQuestionIsEmpty) {
    player.sendMessage("§1 bypassnextquestionisemty est sur trueeeeee");
    //y a un truc avec le subject qui mal redéfini après faut faire des tester 24/04/25
    player.sendMessage("Le subject et " + subject);

    List<Map.Entry<String, List<String>>> nextQuestions = questionLoader.getQuestionsDefis(subject,"Q1",player);

    if (!nextQuestions.isEmpty()) {


        player.sendMessage("Bravo 1 ");

        if (!hasCompletedQuiz(player, subject)) {

            complete = 1;
            player.sendMessage("Le subject et " + subject);


            Map.Entry<String, List<String>> nextQuestion = nextQuestions.get(0); // Récupère la première question


           //y a     peut etre un truc 01/05/25 verifier avec lautre classe
            CreateInventoryDifficultMatier df = new CreateInventoryDifficultMatier(main, player); //on instancie la classe
                     player.sendMessage(nextQuestion.getKey() + "Bravo 222222..." + nextQuestion.getValue() );

            df.createInventory(player, "Question", nextQuestion.getKey(), nextQuestion.getValue());
            player.sendMessage("Le subject et " + subject);

            ByPass_NextQuestionIsEmpty = false;


        } else {
            GestionInvetory gstinv = new GestionInvetory(main, player);


            gstinv.On_Inventory_Difficult(player);
            player.closeInventory();
            player.sendMessage("Vous avez déjà fait ce quizz ");
            player.sendMessage("Le subject et " + subject);
            ByPass_NextQuestionIsEmpty = false;

        }
        //player.sendMessage("§2 le truc s'appel attention :" + difficulté + "Notre sujet est" + subject);
    } else {
        player.sendMessage("Vous avez terminé toutes les questions !AAAAAAAAAAAAA");

        GestionInvetory gstinv = new GestionInvetory(main, player);
        ByPass_NextQuestionIsEmpty = false;


        player.closeInventory();


        player.sendMessage("Vous avez terminé toutes les questions !AAAAAAAAAAAAA");


    }

}else{

    player.sendMessage("La valeur BypasseNextquestionIsEmpty et sur false");
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

        List<String> correctreponse = questionLoader.getCorrectAnswersDefis(); //nextQuestionKey


        List<Map.Entry<String, List<String>>> nextQuestions = questionLoader.getQuestionsDefis(subject,nextQuestionKey,player);

        File file = new File(main.getDataFolder(), "point.yml");

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String key = "players." + player.getUniqueId();

        int point = config.getInt(key + ".point",0);

        player.sendMessage("---///----Le subject et " + subject);

        if(correctreponse.contains(itemName) ) {
            //si lobjet contien la bonne réponse et bien f  fffaire en sorte de ajouter 1 point et  apperler la fonction vérifier
            changingInventory.remove(player);

            player.sendMessage("Ceci est la Bonne réponse !" + point);

            Addition_fin += 1;


            vérification(player);



        }else {
            if(complete == 1){
                player.sendMessage("§6 Aucun reponse bonne de détecter" );

                vérification(player);
            }



            /*
            if( ByPass_NextQuestionIsEmpty) {
                vérification(player);
                player.sendMessage("pas Bravo 1 ");
                //rien
                }
                c'est un test je envleve temps sinon si ça marche toujours pas tester

                 List<String> ReponseDifficult = questionLoader.getDifficultExist();

        	  changingInventory.remove(player);


        	if(ReponseDifficult.contains(difficulté)) {


        	    if(!ReponseDifficult.contains(itemName)) {


        			player.sendMessage("Mauvaise réponse !");


        		vérification(player);
        	    }


        	    22/04/25
                */


        }


        event.setCancelled(false);
    }


    public String vérification(Player player) {
        File file = new File(main.getDataFolder(), "point.yml");

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String key = "players." + player.getUniqueId();

        int point = config.getInt(key + ".point",0);

        List<Map.Entry<String, List<String>>> nextQuestions = questionLoader.getQuestionsDefis(subject,  nextQuestionKey,player);

        player.sendMessage("Verification original " + nextQuestionKey);
        player.sendMessage("////Le subject et " + subject);


        if (!nextQuestions.isEmpty()) {

            player.sendMessage("Premier verification reussi ");
            if(Q_q == 1) {
                player.sendMessage("Un ajout à été effectuer");
                questionKeyBuilder.append('1');
            }  

            player.sendMessage("////Le subject et " + subject);
            nextQuestionKey = questionKeyBuilder.toString();
            List<Map.Entry<String, List<String>>> nextQuestionss = questionLoader.getQuestionsDefis(subject,nextQuestionKey,player);
            player.sendMessage("-------------mmm-------------" + nextQuestionKey);


            if (!nextQuestionss.isEmpty()) {
                player.sendMessage("////Le subject et " + subject);
                player.sendMessage("Double verification reussi ahahahahah ");

                Map.Entry<String, List<String>> nextQuestion = nextQuestionss.get(0);
                // Récupère la première question
                CreateInventoryDifficultMatier df = new CreateInventoryDifficultMatier(main, player);

                player.sendMessage("3 verfi" + nextQuestion.getKey() +"et  ....."+ nextQuestion.getValue());

                df.createInventory(player, "Question", nextQuestion.getKey(), nextQuestion.getValue());
                player.sendMessage("H" + nextQuestionKey);
                player.sendMessage("3 verfi" + nextQuestion.getKey() +"et  ....."+ nextQuestion.getValue());
                changingInventory.add(player);
                complete = 1;
                Q_q = 1;

            }else {

                player.sendMessage("Il ya eu un certain soucis  " + point);

                questionKeyBuilder = new StringBuilder("Q1");
                nextQuestionKey = questionKeyBuilder.toString();

                File fileC = new File(main.getDataFolder(), "classement.yml");

                YamlConfiguration configC = YamlConfiguration.loadConfiguration(fileC);
                String keyC = "players." + player.getUniqueId();

                // ne comprend pas son utiliter je pense que c'est pour le classement mais je trouve rien int pointC = configC.getInt(keyC + ".point",0);

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
                player.sendMessage("////Le subject et " + subject);
                GestionInvetory gstinv = new GestionInvetory(main, player);


                gstinv.On_Inventory_Difficult(player);

                player.closeInventory();
                changingInventory.remove(player);
                BlockQuestion(player,subject);




            }

        }else {

    if(complete == 1) {
        reset(player);
        changingInventory.remove(player);
        BlockQuestion(player, subject);
        player.sendMessage("Bravo  tout et terminer renitialisation des quizz ");
        player.sendMessage("////Le subject et " + subject);

    }else{
        player.sendMessage("toujours non 2 ");
        player.sendMessage("////Le subject et " + subject);
    }

        }



        return nextQuestionKey;
    }


    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Code à exécuter lorsque le joueur ferme l'inventaire
        Player player = (Player) event.getPlayer();

        if(changingInventory.contains(player)) {
            player.sendMessage("aie aie");
                //reset(player); //21/04/25 y a un soucis ici quand on change de inventaire le cette fonction s'active ce qui fait voila ça reset et ça fair BUGG
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Code à exécuter lorsque le joueur se déconnecte
        Player player = event.getPlayer();
        player.sendMessage("aie aiez aiezzzzzzz");
        reset(player);

    }

  


    public void reset(Player player) {
        questionKeyBuilder = new StringBuilder("Q1");
        nextQuestionKey = questionKeyBuilder.toString();

        subject = "";
        pnj.SetMatier("");
        complete = 0;
        ByPass_NextQuestionIsEmpty = false;
        Addition_fin = 0;
        Q_q = 0;
        player.sendMessage("Le reset c'est bien effectuer");
// il faudra mettre un message de debug ici pour voir


    }


    public void loadPlayerData() {
        playerDataFile = new File(main.getDataFolder(), "player_data_defis.yml");


        if (!playerDataFile.exists()) {

            main.saveResource("player_data_defis.yml", false);
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
    public boolean hasCompletedQuiz(Player player, String subject) {
        ensurePlayerExists(player); // Vérifie que le joueur est bien enregistré
        String path = "players." + player.getUniqueId() + "." + subject ;

        return playerData.getBoolean(path, false);
    }

    // Marquer un quizz comme complété
    public void markQuizCompleted(Player player, String subject) {
        ensurePlayerExists(player); // Vérifie que le joueur est bien enregistré
        String path = "players." + player.getUniqueId() + "." + subject;
        playerData.set(path, true);
        savePlayerData();


    }

    public void BlockQuestion(Player player,String sujet){

        //loadPlayerData();
        ensurePlayerExists(player);
        markQuizCompleted(player,sujet);
        ByPass_NextQuestionIsEmpty = false;
        savePlayerData();
        player.sendMessage("BLOCAGE BLOCAGE");

    }


}


