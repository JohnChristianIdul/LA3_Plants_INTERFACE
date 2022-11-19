import java.time.Instant;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Game Mode: ");
        String mode = sc.nextLine();

        String input;
        do {
            System.out.print("Add a plant: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Wall Nut":
                    plants.add(new Plant.WallNut());
                    break;
                case "Sun-shroom":
                    if(mode.equals("Night") || mode.equals("Fog"))
                        plants.add(new Mushroom.SunShroom(true));
                    else
                        plants.add(new Mushroom.SunShroom(false));
                    break;
                // add more plants here
                case "Puff-shroom":
                    if(mode.equals("Night") || mode.equals("Fog"))
                        plants.add(new Mushroom.PuffShroom(true));
                    else
                        plants.add(new Mushroom.PuffShroom(false));
                    break;
                case "Doom-shroom":
                    if(mode.equals("Night") || mode.equals("Fog"))
                        plants.add(new Mushroom.DoomShroom(true));
                    else
                        plants.add(new Mushroom.DoomShroom(false));
                    break;
                case "Sunflower":
                    plants.add(new Plant.Sunflower());
                    break;
                case "Twin Sunflower":
                    int i = 0;
                    for(Plant p : plants){
                        if(p instanceof Plant.Sunflower){
                            plants.set(i, new Plant.TwinSunflower());
                            break;
                        }
                        i++;
                        break;
                    }
                    break;
                case "Peashooter":
                    plants.add(new Plant.Peashooter());
                    break;
                case "Squash":
                    plants.add(new Plant.Squash());
                    break;
                case "Jalapeno":
                    plants.add(new Plant.Jalapeno());
                    break;
                case "Coffee Bean":
                    for(Plant a : plants){
                        if(a instanceof Mushroom){
                            Mushroom m = (Mushroom) a;
                            if(!m.isAwake()){
                                m.awaken(new Plant.CoffeeBean());
                                break;
                            }
                        }
                    }
                    break;
                case "Lily Pad":
                    plants.add(new Plant.LilyPad());
                    break;
                case "Cattail":
                    int c = 0;
                    for(Plant p : plants){
                        if(p instanceof Plant.LilyPad){
//                            int pos = plants.indexOf(p);
//                            plants.remove(pos);
//                            plants.add(pos, new Plant.Cattail());
                            plants.set(c, new Plant.Cattail());
                            break;
                        }
                        c++;
                    }
                    break;
                default:
                    System.out.println(input + " is not a plant");
            }
        } while (!input.equals("DONE"));

        do {
            System.out.print("Do something: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Produce Sun":
                    int sum = 0;
                    int count = 0;
                    for(Plant p : plants){
                        if(p instanceof SunProducer){
                            SunProducer sun = (SunProducer) p;
                            sum+=sun.produce_sun();
                            count++;
                        }
                    }
                    if(count != 0){
                        System.out.println(count + " sun producers gather " + sum + " suns");
                    } else {
                        System.out.println("You have no sun producers");
                    }
                    break;
                case "Attack":
                    // add implementation here
                    int total = 0;
                    int c = 0;
                    for(Plant p : plants){
                        if(p instanceof Attacker){
                            Attacker s = (Attacker) p;
                            if(p.isAlive()) {
                                total += s.attack();
                                c++;
                            }
                        }
                    }
                    if(c !=0) {
                        System.out.println(c + " attackers dealing " + total + " damage");
                    } else {
                        System.out.println("You have no attackers");
                    }
                    break;
                // add more cases here
                case "Instant Kill Status":
                    int co = 0;
                    for(Plant p : plants) {
                        if(p.isAlive()) {
                            if (p instanceof InstantKiller) {
                                InstantKiller instant = (InstantKiller) p;
                                instant.killType();
                                co++;
                            }
                        }
                    }
                    if(co == 0) {
                        System.out.println("You have no plants which can kill instantly");
                    }
                    break;
                case "Attacker Status":
                    int ct = 0;
                    for(Plant p : plants) {
                        if(p instanceof Attacker){
                            if(p.isAlive()) {
                                Attacker attack = (Attacker) p;
                                attack.rangeType();
                                ct++;
                            }
                        }
                    }
                    if(ct == 0) {
                        System.out.println("You have no attackers");
                    }
                    break;
                case "Sort by HP":
                    Collections.sort(plants, new Plant.sortByHP());
                    plants.toString();
                    break;
                case "Sort by Name":
                    Collections.sort(plants,new Plant.sortByname());
                    plants.toString();
                    break;
                case "Sort by Sun Cost":
                    Collections.sort(plants, new Plant.sortBysun_cost());
                    plants.toString();
                    break;
                default:
                    System.out.println("Unknown action: " + input);
            }
        } while (!input.equals("DONE"));
    }
}