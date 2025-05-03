package fr.rabio.jeanCraftCore.Autre;

import fr.rabio.jeanCraftCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scorboard  implements Listener {

    public Main main;

    public Scorboard(Main main){
        this.main = main;

    }






    public void createScoreboard(Player player) {
        File fileC = new File(main.getDataFolder(), "classement.yml");
        if (!fileC.exists()) {
            player.sendMessage(ChatColor.RED + "Le fichier classement.yml est introuvable !");
            return;
        }

        // Charger le fichier YAML
        YamlConfiguration configC = YamlConfiguration.loadConfiguration(fileC);
        String playerKey = "players." + player.getUniqueId();
        int playerPoints = configC.getInt(playerKey + ".point", 0);

        // Récupérer tous les scores
        HashMap<String, Integer> scores = new HashMap<>();
        if (configC.contains("players")) {
            for (String key : configC.getConfigurationSection("players").getKeys(false)) {
                int points = configC.getInt("players." + key + ".point", 0);
                scores.put(key, points);
            }
        }

        // Trier les joueurs par score
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Trouver le classement du joueur
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedScores) {
            if (entry.getKey().equals(player.getUniqueId().toString())) {
                break;
            }
            rank++;
        }
int player_online = Bukkit.getOnlinePlayers().size();
        // Création du scoreboard
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("rank", Criteria.DUMMY, ChatColor.GOLD + "___________");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Ajjjjouter le nom et le classement du joueur



        Score serverLine = objective.getScore(ChatColor.DARK_PURPLE + "Jean craft : " );
        serverLine.setScore(5);



        Score nameLine = objective.getScore(ChatColor.AQUA + "Joueur : " + ChatColor.GREEN + player.getName());
        nameLine.setScore(4);


        Score playerLine = objective.getScore(ChatColor.GREEN + "Nombre de joueur (" + ChatColor.WHITE + player_online + ChatColor.GREEN +"/30)");
        playerLine.setScore(3);


        Score rankLine = objective.getScore(ChatColor.YELLOW + "Rank : #" + rank);
        rankLine.setScore(2);

        Score PointLine = objective.getScore(ChatColor.DARK_BLUE + "Points :" + playerPoints);
        PointLine.setScore(1);

        // Appliquer le scoreboard au joueur
        player.setScoreboard(scoreboard);
    }
}


