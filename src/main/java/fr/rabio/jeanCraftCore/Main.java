package fr.rabio.jeanCraftCore;

import fr.rabio.jeanCraftCore.commands.Classement;
import fr.rabio.jeanCraftCore.commands.QuizzCommand;
import fr.rabio.jeanCraftCore.event.*;
import fr.rabio.jeanCraftCore.manager.ScoreBoardManager;
import fr.rabio.jeanCraftCore.Autre.ChangeDifficult;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private InventoryQuestionPnj inventoryPnj;
    private FileConfiguration playerData;
    private File playerDataFile;

    @Override
    public void onEnable() {
        // Initialisation de LuckPerms si disponible
        LuckPerms luckPermsApi = setupLuckPerms();
        // Initialisation du gestionnaire de tableaux de score
        ScoreBoardManager scoreBoardManager = new ScoreBoardManager(this, luckPermsApi);
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                scoreBoardManager.createScoreboard(player);
            }

        }, 0L, 20L);

        // Initialisation des fonctionnalités
        ChangeDifficult changeDifficult = new ChangeDifficult();
        this.inventoryPnj = new InventoryQuestionPnj(this, "");

        // Enregistrement des événements
        registerEvents(changeDifficult);

        // Enregistrement des commandes
        getCommand("quizz").setExecutor(new QuizzCommand(this));
        getCommand("classement").setExecutor(new Classement(this));

        // Chargement des données des joueurs
        loadPlayerData();
    }

    @Override
    public void onDisable() {
        // Actions lors de la désactivation du plugin si nécessaire
    }

    private LuckPerms setupLuckPerms() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        return provider != null ? provider.getProvider() : null;
    }

    private void registerEvents(ChangeDifficult changeDifficult) {
        getServer().getPluginManager().registerEvents(new InventoryClickMatier(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickDifficultAndQuestion(changeDifficult, this, this.inventoryPnj), this);
        getServer().getPluginManager().registerEvents(new ClickItemStart(this), this);
        getServer().getPluginManager().registerEvents(this.inventoryPnj, this);
        getServer().getPluginManager().registerEvents(new PassageClassrom(this), this);
        getServer().getPluginManager().registerEvents(new InventoryDefisQuestionGestion(this, this.inventoryPnj), this);
    }

    private void loadPlayerData() {
        playerDataFile = new File(getDataFolder(), "player_data.yml");
        if (!playerDataFile.exists()) {
            saveResource("player_data.yml", false);
        }
        playerData = YamlConfiguration.loadConfiguration(playerDataFile);
    }
}
