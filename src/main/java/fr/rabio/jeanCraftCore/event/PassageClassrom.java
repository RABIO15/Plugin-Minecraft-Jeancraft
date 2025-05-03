package fr.rabio.jeanCraftCore.event;

import fr.rabio.jeanCraftCore.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class PassageClassrom implements Listener {



    public Main main;

    public PassageClassrom(Main main){

        this.main = main;
    }

   @EventHandler
   public void PlayerMove(PlayerMoveEvent event)  {

       Player player = event.getPlayer();

       Block block = event.getTo().getBlock().getRelative(BlockFace.DOWN);
       Block blocSousJoueur = player.getLocation().subtract(0, 1, 0).getBlock();


       if(block.getType() == Material.GOLD_BLOCK ){  

           player.sendMessage("Salut caca c'est pipi");
            int x = blocSousJoueur.getX();

           int y = blocSousJoueur.getY();

           int z = blocSousJoueur.getZ();

           player.sendMessage("Tu es sur un bloc d'or aux coordonnées : X=" + x + ", Y=" + y + ", Z=" + z);

            if(x == 90){

                player.sendMessage("reussite !!!!!!");

            }else{

                player.sendMessage("ail");

            }


           Random ran = new Random();
           int random_chiffre = ran.nextInt(3);



           File dossier = new File(main.getDataFolder(),"TpCo.yml");

           YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dossier);









           String Tp = "Salle1.";






           Location loci = new Location(player.getWorld(),60,80,31);










           switch(random_chiffre) {
               case 1:
                   player.sendMessage("le chat est pendu");



    ChangeSalle(Tp,player);






                   Location playerLocation = player.getLocation();
                  // if (playerLocation.getBlockX() == 89 && playerLocation.getBlockY() == 120 && playerLocation.getBlockZ() == 21) {
                       player.sendMessage("lHFOZIGHFEIUZEIUFIZHEIFHZE");
                       player.sendMessage("lHFOZIGHFEIUZEIUFIZHEIFHZE"); player.sendMessage("lHFOZIGHFEIUZEIUFIZHEIFHZE"); player.sendMessage("lHFOZIGHFEIUZEIUFIZHEIFHZE");
                       player.sendMessage("lHFOZIGHFEIUZEIUFIZHEIFHZE");





                   player.sendMessage("§4 Vous vous êtes bien téléportez !");

                   //}
                   break;


               case 2:
                   player.sendMessage("le chat est perdu");
                   ChangeSalle("Salle2.",player);
                   break;

               default:

                   player.sendMessage("Salut caca c'est pipi premium ");
                   player.teleport(loci);
                   break;

           }





       }else{

       }


   }
public void ChangeSalle(String salle,Player player){

    File dossier = new File(main.getDataFolder(),"TpCo.yml");

    YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dossier);











    int X1 = configuration.getInt(salle + ".x",0);
    int Y1 = configuration.getInt(salle + ".y",0);
    int Z1 = configuration.getInt(salle + ".z",0);




    Location loc = new Location(player.getWorld(),X1,Y1,Z1);

    player.teleport(loc);
}


}
