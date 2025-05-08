package fr.rabio.jeanCraftCore.manager;

import fr.rabio.jeanCraftCore.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class ScoreBoardManager implements Listener {

    private final Main main;
    private final LuckPerms luckPerms;

    public ScoreBoardManager(Main main, LuckPerms luckPerms) {
        this.main = main;
        this.luckPerms = luckPerms;
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

        int playerOnline = Bukkit.getOnlinePlayers().size();

        // Création du scoreboard
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(
                "rank",
                "dummy",
                "§f§lJeanCraft",
                RenderType.INTEGER
        );
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Récupérer les informations LuckPerms
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        String userFormatted = (user != null && user.getCachedData().getMetaData().getPrefix() != null)
                ? user.getCachedData().getMetaData().getPrefix().replace("&", "§")
                : "§fAucun";

        // Ajouter les lignes au scoreboard
        String date = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date());
        objective.getScore("§7" + date).setScore(8);
        objective.getScore(" ").setScore(7);
        objective.getScore("§fGrade : §r" + userFormatted).setScore(6);
        objective.getScore("§fPseudo : §a" + player.getName()).setScore(5);
        objective.getScore("§fPoints : §a" + playerPoints).setScore(4);
        objective.getScore("   ").setScore(3);
        objective.getScore("§fJoueurs : §a" + playerOnline).setScore(2);
        objective.getScore("  ").setScore(1);
        objective.getScore("§ejeancraft.fr").setScore(0);

        // Appliquer le scoreboard au joueur
        player.setScoreboard(scoreboard);
    }
}
