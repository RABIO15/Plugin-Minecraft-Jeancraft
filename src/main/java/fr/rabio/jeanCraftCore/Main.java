package fr.rabio.jeanCraftCore;


import fr.rabio.jeanCraftCore.Autre.Scorboard;
import fr.rabio.jeanCraftCore.commands.Classement;
import fr.rabio.jeanCraftCore.event.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.rabio.jeanCraftCore.Autre.ChangeDifficult;

import fr.rabio.jeanCraftCore.commands.QuizzCommand;

import java.io.File;


public class Main extends JavaPlugin {

    private InventoryQuestionPnj inventorypnj;
    private FileConfiguration playerData;
    private File playerDataFile;
    private Player player;
    @Override
    public void onEnable() {



        Scorboard scoreboard = new Scorboard(this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                scoreboard.createScoreboard(player);
            }
        }, 0L, 100L);



        //la classe principal
        ChangeDifficult changeDifficult = new ChangeDifficult();
        this.inventorypnj = new InventoryQuestionPnj(this,"");
        
        getServer().getPluginManager().registerEvents(new InventoryClickMatier(this), this);
        
      
        getServer().getPluginManager().registerEvents(new InventoryClickDifficultAndQuestion(changeDifficult, this,this.inventorypnj), this);  
        
        getServer().getPluginManager().registerEvents(new ClickItemStart(this), this);
        getServer().getPluginManager().registerEvents(this.inventorypnj, this);
        getServer().getPluginManager().registerEvents(new PassageClassrom(this),this);

        getServer().getPluginManager().registerEvents(new InventoryDefisQuestionGestion(this,this.inventorypnj),this);

       // PluginCommand quizzCommand = Objects.requireNonNull(getCommand("quizz"), "La commande 'quizz' n'est pas définie dans plugin.yml !");
        //quizzCommand.setExecutor(new QuizzCommand(this));
   




        getCommand("quizz").setExecutor(new QuizzCommand(this));

        getCommand("classement").setExecutor(new Classement(this));


loadPlayerData();

    /*


       Pour la prochaine fois bug avec la commande quizz qui ne veut pas marcher donc ce que j'ai fait c'est bien verifi
       le plugin yml j'ai modifie le paper mais toujoiurs rien la je vais crée une commande caca pour voir si  elle marche
       mais de ce que je vois meme sans rien il est en jaune donc a mon avis cela ne vien pas de la command quizz directement
                ou des autre commandes mais de autre chose d'autre 29/01/2025
                
                */
       
        
         
       /*
       Alors ce que je fais c'est mofifier la version qui etait end e 1.20.4 dans le build grandle en 1.21
       aussii dans la classe main j'ai fait en sorte de faire autrement pour charger le commande
       j'ai regarder si c'est pas du dossier ressource mais non j'ai bien verifier le paper plugin et le plugin yml
       mais rien de nouveau truc a faire poour la  prochaine fois alors tester voir si cela marche ou pas sinon voir en detrail
       la  classe commande qui fait chier ou regarder sur internet sinon trafic dans project structure eet ou crée un nouvel commande pour mieu commprendre
       le soucis 01/02/2025





        */
       
        
        
    }

    @Override
    public void onDisable() {

    
    	
    	
    	
    	
    	
    	
}


    public void loadPlayerData() {
        playerDataFile = new File(getDataFolder(), "player_data.yml");
        if (!playerDataFile.exists()) {

            saveResource("player_data.yml", false);
        }
        playerData = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  

}
  