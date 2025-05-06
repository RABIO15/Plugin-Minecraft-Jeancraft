package fr.rabio.jeanCraftCore.Autre;

// Importation des classes nécessaires pour la gestion des fichiers et des configurations
import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration; // Gestion des fichiers de configuration
import org.bukkit.configuration.file.YamlConfiguration; // Permet de manipuler les fichiers YAML

import fr.rabio.jeanCraftCore.Main; // Importation de la classe principale du plugin
import org.bukkit.entity.Player;

// Classe qui gère le chargement et l'accès aux questions et définitions
public class QuestionLoader {

    // Variables pour stocker les configurations des fichiers YAML
    private FileConfiguration config; // Configuration pour le fichier questions.yml


    private FileConfiguration configD; // Configuration pour le fichier questionsDefis.yml

    // Constructeur : Initialise les fichiers et charge leur contenu
    public QuestionLoader(Main main) {
        createQuestionsFile(main); // Crée le fichier questions.yml s'il n'existe pas


        loadQuestionsConfig();



        // Charge le contenu du fichier questions.yml
    }


    public QuestionLoader(Main main,String test) {
        // Crée le fichier questions.yml s'il n'existe pas
        createQuestionsDefisFile(main);
        loadQuestionsDefisConfig();
//en gros ici j'ai fait de la surcharge pour pouvoir crée 2 constructeur différent comme ça je peux appeler
        // le constructeur que je veux



        // Charge le contenu du fichier questions.yml
    }




    // Références aux fichiers contenant les questions et les défis
    private File questionsFile; // Fichier questions.yml
    private File questionsDefisFile; // Fichier questionsDefis.yml

    // Méthode privée pour créer le fichier questions.yml

    /*

    Pour question normal

     */
    private void createQuestionsFile(Main main) {
        questionsFile = new File(main.getDataFolder(), "questions.yml"); // Définit le chemin du fichier
        if (!questionsFile.exists()) { // Vérifie si le fichier n'existe pas
            questionsFile.getParentFile().mkdirs(); // Crée les répertoires parents si nécessaires
            main.saveResource("questions.yml", false); // Copie le fichier par défaut des ressources du plugin
        }
    }
    private void loadQuestionsConfig() {
        config = YamlConfiguration.loadConfiguration(questionsFile); // Charge les données du fichier dans config

    }



    /*

   Pour question Defis

    */
    // Méthode privée pour créer le fichier questionsDefis.yml
    private void createQuestionsDefisFile(Main main) {
        questionsDefisFile = new File(main.getDataFolder(), "questionsDefis.yml"); // Définit le chemin du fichier
        if (!questionsDefisFile.exists()) { // Vérifie si le fichier n'existe pas
            questionsDefisFile.getParentFile().mkdirs(); // Crée les répertoires parents si nécessaires
            main.saveResource("questionsDefis.yml", false); // Copie le fichier par défaut des ressources du plugin
        }
    }

    // Méthode privée pour charger la configuration du fichier questionsDefis.yml
    private void loadQuestionsDefisConfig() {


        configD = YamlConfiguration.loadConfiguration(questionsDefisFile); // Charge les données du fichier dans configD
    }

    // Méthode privée pour charger la configuration du fichier questions.yml






  /*
    Pour les question Defis



     */

    public List<Map.Entry<String, List<String>>> getQuestionsDefis(String subject, String questionId, Player player) {


        List<Map.Entry<String, List<String>>> questionsD = new ArrayList<>(); // Liste pour stocker les questions et réponses
        String path = subject + "." + questionId; // Chemin dans la configuration pour accéder aux questions
        List<Map<?, ?>> configDQuestions = configD.getMapList(path); // Récupère la liste des questions

/*

        player.sendMessage("§4____INFORMATION COMPLAIMENTAIRE_____");
        player.sendMessage("                                                " );
        player.sendMessage("                                                " );
        player.sendMessage("§9___SUBJECT____ : " + subject);
        player.sendMessage("                                                " );
        player.sendMessage("§9____QuestionID____: " + questionId);
        player.sendMessage("                                                " );
        player.sendMessage("§9___  Question D___ : " + questionsD);
        player.sendMessage("                                                " );
        player.sendMessage("§9___ConfigDquestion___ : " + configDQuestions);
        player.sendMessage("                                                " );
        player.sendMessage("§9_____Path____ : " + path);




 player.sendMessage("§6____appel de la premier fonction getquestiondefis____");



   */

        if (configDQuestions.isEmpty()) { // Si aucusssssssne question n'est trouvée, retourne une liste vide
            player.sendMessage("§4_____ ECHEC" + questionsD);

            return questionsD;
        }else{
            player.sendMessage(" Tout va bien " + questionsD);

        }

        // Parcours des questions trouvées dans la configuration
        for (Map<?, ?> questionDMap : configDQuestions) {
            try {
                String questionDD = (String) questionDMap.get("question"); // Récupère la question
                List<String> answers = (List<String>) questionDMap.get("answers"); // Récupère les réponses associées
                questionsD.add(new AbstractMap.SimpleEntry<>(questionDD, answers));
                player.sendMessage("§6________Ajout list");
                // Ajoute la question et les réponses à la liste
            } catch (Exception e) {
                player.sendMessage("___Erreur lors du chargement des questions.___"); // En cas d'erreur, log un message
            }
        }

        player.sendMessage("§12________Il retourne a nouveaux "+ questionsD + "_______");

        return questionsD; // Retourne la liste des questions et réponses
    }



    /*
    Pour les question en normal


     */

    public List<Map.Entry<String, List<String>>> getQuestions(String subject, String difficulty, String questionId) {
        List<Map.Entry<String, List<String>>> questions = new ArrayList<>(); // Liste pour stocker les questions et réponses
        String path = subject + "." + difficulty + "." + questionId; // Chemin dans la configuration pour accéder aux questions
        List<Map<?, ?>> configQuestions = config.getMapList(path); // Récupère la liste des questions

        if (configQuestions.isEmpty()) { // Si aucune question n'est trouvée, retourne une liste vide
            return questions;
        }

        // Parcours des questions trouvées dans la configuration
        for (Map<?, ?> questionMap : configQuestions) {
            try {
                String question = (String) questionMap.get("question"); // Récupère la question
                List<String> answers = (List<String>) questionMap.get("answers"); // Récupère les réponses associées
                questions.add(new AbstractMap.SimpleEntry<>(question, answers)); // Ajoute la question et les réponses à la liste
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement des questions."); // En cas d'erreur, log un message
            }
        }

        return questions; // Retourne la liste des questions et réponses
    }


    /*

   Pour question normal


    */



    // Récupère les réponses correctes des questions
    public List<String> getCorrectAnswers() {
        return config.getStringList("REPONSE.question"); // Récupère la liste des réponses correctes dans la configuration
    }

    // Récupère les réponses correctes des défis


    // Récupère les matières disponibles dans la configuration
    public List<String> getMatierExist() {
        return config.getStringList("MATIERSCOLAIRE.scolaire"); // Récupère la liste des matières scolaires
    }

    // Récupère les défis disponibles dans la configuration

   /*

   Pour question Defis


    */
    public List<String> getDifficultExist() {
        return config.getStringList("DIF.difficult"); // Récupère la liste des difficultés des questions
    }



    public List<String> getCorrectAnswersDefis() {
        return configD.getStringList("REPONSEDEFIS.question"); // Récupère la liste des réponses correctes pour les défis
    }


    public List<String> getDefisExist() {



        return configD.getStringList("MATIERSCOLAIRE.scolaire"); // Récupère la liste des défis
    }


    // Récupère les niveaux de difficulté des défis
   /* public List<String> getDifficultDefisExist() {
        return configD.getStringList("DIFDEFIS.difficult"); // Récupère la liste des difficultés des défis
    }
    */

}
