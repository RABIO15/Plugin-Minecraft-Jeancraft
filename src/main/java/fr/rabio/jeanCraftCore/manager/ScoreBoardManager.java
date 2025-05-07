package fr.rabio.jeanCraftCore.manager;

import fr.rabio.jeanCraftCore.Main;
<<<<<<< HEAD
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
=======
>>>>>>> 404a238a1b808995c42390481064550b9fe35201
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;

import java.io.File;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ScoreBoardManager implements Listener {
    public Main main;
    public LuckPerms luckPerms;
    public ScoreBoardManager(Main main, LuckPerms luckperms) {
        this.main = main;
        this.luckPerms = luckperms;
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoardManager implements Listener {
    public Main main;

    public ScoreBoardManager(Main main) {
        this.main = main;
>>>>>>> 404a238a1b808995c42390481064550b9fe35201
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
<<<<<<< HEAD
        Objective objective = scoreboard.registerNewObjective("rank", Criteria.DUMMY, "§f§lJeanCraft");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        // Ajouter le nom et le classement du joueur
        String date = new SimpleDateFormat("dd/MM/yy").format(new Date());
        Score dateline = objective.getScore("§7" + date);
        dateline.setScore(8);
        Score Emptyline1 = objective.getScore(" ");
        Emptyline1.setScore(7);
        String Userformated = user.getCachedData().getMetaData().getPrefix().replace("&", "§");
        Score gradeline = objective.getScore("§fGrade : §r" + Userformated);
        gradeline.setScore(6);
        Score nameLine = objective.getScore("§fPseudo : §a" + player.getName());
        nameLine.setScore(5);
        Score PointLine = objective.getScore("§fPoints : §a" + playerPoints);
        PointLine.setScore(4);
        Score Emptyline3 = objective.getScore("   ");
        Emptyline3.setScore(3);
        Score playerLine = objective.getScore("§fJoueurs : §a" + player_online);
        playerLine.setScore(2);
        Score Emptyline2 = objective.getScore("  ");
        Emptyline2.setScore(1);
        Score ipline = objective.getScore("§ejeancraft.fr");
        ipline.setScore(0);





=======
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
>>>>>>> 404a238a1b808995c42390481064550b9fe35201

        // Appliquer le scoreboard au joueur
        player.setScoreboard(scoreboard);
    }
}
