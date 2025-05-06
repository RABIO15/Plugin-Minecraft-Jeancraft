package fr.rabio.jeanCraftCore.manager;

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

public class ScoreBoardManager implements Listener {
    public Main main;

    public ScoreBoardManager(Main main) {
        this.main = main;
    }
    public void createScoreboard(Player player) {
        File fileC = new File(main.getDataFolder(), "classement.yml");
        if (!fileC.exists()) {
            player.sendMessage("§cLe fichier classement.yml est introuvable !");
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
        Objective objective = scoreboard.registerNewObjective("rank", Criteria.DUMMY, "§d§lJeanCraft");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Ajouter le nom et le classement du joueur
        Score nameLine = objective.getScore("§6§lName : §e" + player.getName());
        nameLine.setScore(5);
        Score rankLine = objective.getScore("§6§lGrade : §r" + rank);
        rankLine.setScore(4);

        Score playerLine = objective.getScore("§6§lStudents : §e" + player_online + "§6/§e" + Bukkit.getMaxPlayers());
        playerLine.setScore(3);

        Score PointLine = objective.getScore("§6§lPoints :§e" + playerPoints);
        PointLine.setScore(1);

        Score ip = objective.getScore("§cplay.jeancraft.fr");

        // Appliquer le scoreboard au joueur
        player.setScoreboard(scoreboard);
    }
}
