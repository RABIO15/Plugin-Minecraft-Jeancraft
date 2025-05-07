package fr.rabio.jeanCraftCore;


import fr.rabio.jeanCraftCore.commands.Classement;
import fr.rabio.jeanCraftCore.event.*;
import fr.rabio.jeanCraftCore.manager.ScoreBoardManager;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
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
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        LuckPerms api = null;
        if (provider != null) {
            api = provider.getProvider();

        }
        ScoreBoardManager scoreBoardManager = new ScoreBoardManager(this, api);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                scoreBoardManager.createScoreboard(player);
            }
        }, 0L, 100L);

        //la classe principal
        ChangeDifficult changeDifficult = new ChangeDifficult();
        this.inventorypnj = new InventoryQuestionPnj(this, "");

        getServer().getPluginManager().registerEvents(new InventoryClickMatier(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickDifficultAndQuestion(changeDifficult, this, this.inventorypnj), this);
        getServer().getPluginManager().registerEvents(new ClickItemStart(this), this);
        getServer().getPluginManager().registerEvents(this.inventorypnj, this);
        getServer().getPluginManager().registerEvents(new PassageClassrom(this), this);
        getServer().getPluginManager().registerEvents(new InventoryDefisQuestionGestion(this, this.inventorypnj), this);
        // PluginCommand quizzCommand = Objects.requireNonNull(getCommand("quizz"), "La commande 'quizz' n'est pas définie dans plugin.yml !");
        //quizzCommand.setExecutor(new QuizzCommand(this));
        getCommand("quizz").setExecutor(new QuizzCommand(this));
        getCommand("classement").setExecutor(new Classement(this));
        loadPlayerData();
    }

    @Override
    public void onDisable() {}

    public void loadPlayerData() {
        playerDataFile = new File(getDataFolder(), "player_data.yml");
        if (!playerDataFile.exists()) {

            saveResource("player_data.yml", false);
        }
        playerData = YamlConfiguration.loadConfiguration(playerDataFile);
    }
}