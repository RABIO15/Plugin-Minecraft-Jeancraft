package fr.rabio.jeanCraftCore.Autre;

import org.bukkit.Material;

enum Matier {
	Math(1, "vous avez choisi les math"),
    FRANCAIS(2, "vous avez choisi le Français"),
    SPORT(3, "vous avez choisi le Sport"),
    ANGLAIS(4, "vous avez choisi l'Anglais"),
    SVT(5, "vous avez choisi la svt"),
    PREVENTION_SANTE(6, "vous avez choisi la prevention santé"),
    ECONOMIE(7, "vous avez choisi l'economie"),
    HISTOIRE(8, "vous avez choisi l'histoire"),
    PSYQUE_CHIMIE(9, "vous avez choisi la psyque chimie");

    private final int id;
    private final String message;

    Matier(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public static Matier fromMaterial(Material material) {
        switch (material) {
        case RED_WOOL:
        	
            return Math;
        
            case BLUE_WOOL:
            	String message2 = FRANCAIS.message;
            	
            	
                return FRANCAIS;
                
               
                
           
            case BLACK_WOOL:
                return SPORT;
            case GREEN_WOOL:
            
                return ANGLAIS;
            case GRAY_WOOL:
            	
                return SVT;
            case MAGENTA_WOOL:
                return PREVENTION_SANTE;
            case LIME_WOOL:
                return ECONOMIE;
            case YELLOW_WOOL:
                return HISTOIRE;
            case CYAN_WOOL:
                return PSYQUE_CHIMIE;
            default:
                return null;
        }
    }
}