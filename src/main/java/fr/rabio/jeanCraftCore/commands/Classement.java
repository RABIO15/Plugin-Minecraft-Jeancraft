package fr.rabio.jeanCraftCore.commands;

import fr.rabio.jeanCraftCore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classement implements CommandExecutor {
    public Main main;


    public Classement(Main main) {
        this.main = main;


    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if (label.equals("classement")) {

            if (sender instanceof Player) {


                Player player = (Player) sender;


                File fileC = new File(main.getDataFolder(), "classement.yml");

                YamlConfiguration configC = YamlConfiguration.loadConfiguration(fileC);


/*
                File fileC = new File(main.getDataFolder(), "classement.yml");

                YamlConfiguration configC = YamlConfiguration.loadConfiguration(fileC);
                String keyC = "players." + player.getUniqueId();

                int pointC = configC.getInt(keyC + ".point",0);



 */
                String keyC = "players." + player.getUniqueId();

                int pointC = configC.getInt(keyC + ".point", 0);


                HashMap<String, Integer> playerPoints = new HashMap<>();
                //boucle for qui prend euh les  poitn de joeur et qui fait un classement
                for (String key : configC.getConfigurationSection("players").getKeys(false)) {
                    int points = configC.getInt("players." + key + ".point", 0);


                    playerPoints.put(key, points);
                    player.sendMessage("chat test 2");
                }

                List<Map.Entry<String, Integer>> sortedPlayers = new ArrayList<>(playerPoints.entrySet());
                sortedPlayers.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Tri décroissant

                // Afficher le classement
                player.sendMessage("§6§l--- CLASSEMENT DES JOUEURS ---");
                int rank = 1;


                for (Map.Entry<String, Integer> entry : sortedPlayers) {
                    //player.sendMessage("§e#" + rank + " §a" + entry.getKey() + " §7- §b" + keyC + " points");
                    player.sendMessage("§e#" + rank + " §a" + entry.getKey() + " §7- §b" + entry.getValue() + " points");
                }


            }
        }
        return true;
    }

}