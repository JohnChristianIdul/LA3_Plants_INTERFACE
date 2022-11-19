import java.util.Comparator;

public abstract class Plant {
    public static final int INFINITE = Integer.MAX_VALUE;

    String name;
    int hp;
    int sun_cost;

    public Plant(String name, int sun_cost) {
        this.name = name;
        this.hp = 6;
        this.sun_cost = sun_cost;
    }

    public Plant(String name, int hp, int sun_cost) {
        this.name = name;
        this.hp = hp;
        this.sun_cost = sun_cost;
    }

    public boolean isAlive() {
        if(hp == 0){
            return false;
        }
        return true;
    }

    public String die() {
        hp = 0;
        return name + " dies";
    }

    @Override
    public String toString() {
        // TODO implementation
        if(hp == INFINITE){
            System.out.println(name +" (∞)" + " - cost: " + sun_cost);
        } else {
            System.out.println(name +" ("+hp+")" + " - cost: " + sun_cost);
        }
        return super.toString();
    }

    // Add Plant subclasses here, and
    // Hint: You can also add Comparator inner classes here
    // WallNut and CoffeeBean given for free
    public static class sortByHP implements Comparator<Plant>{

        public int compare(Plant a, Plant b){
            sortByname nsort = new sortByname();
            if(a.hp < b.hp) {
                return 1;
            }
            if(a.hp == b.hp) {
                return nsort.compare(a,b);
            }
            return -1;
        }
    }

    public static class sortByname implements Comparator<Plant>{

        public int compare(Plant a, Plant b){
            int res = a.name.compareTo(b.name);
            return res;
        }
    }

    public static class sortBysun_cost implements Comparator<Plant>{
        sortByname n1sort = new sortByname();
        public int compare(Plant a, Plant b){
            if(a.sun_cost < b.sun_cost) {
                return 1;
            }
            if(a.sun_cost == b.sun_cost) {
                return n1sort.compare(a,b);
            }
            return -1;
        }
    }
    public static class WallNut extends Plant{
        public WallNut() {
            super("Wall Nut", 25, 50);
        }
    }

    public static class CoffeeBean extends Plant{
        public CoffeeBean() {
            super("Coffee Bean", INFINITE, 75);
        }
    }

    public static class Sunflower extends Plant implements SunProducer, Upgradable{

        public Sunflower() {
            super("Sunflower", 6,50);
        }

        @Override
        public int produce_sun() {
            System.out.println(name + " produces 25 suns");
            return 25;
        }

        @Override
        public PlantUpgrade upgrade() {
            return new PlantUpgrade() {
                @Override
                public int concurrentSunCost() {
                    return 50;
                }
            };
        }
    }

    public static class TwinSunflower extends Plant implements SunProducer, Upgradable{
        public TwinSunflower() {
            super("Twin Sunflower",6, 250);
        }

        @Override
        public int produce_sun() {
            System.out.println(name + " produces 50 suns");
            return 50;
        }

        @Override
        public PlantUpgrade upgrade() {
            return new PlantUpgrade() {
                @Override
                public int concurrentSunCost() {
                    return 50;
                }
            };
        }
    }

    public static class Peashooter extends Plant implements Attacker {
        public Peashooter() {
            super("Peashooter", 6,100);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public int rangeType() {
            System.out.println(name + " can attack on a single line");
            return single_line;
        }
    }

    public static class Squash extends Plant implements InstantKiller, Attacker{
        public Squash() {
            super("Squash", INFINITE,50);

        }

        @Override
        public String die() {
            return super.die() + " while squashing zombies";
        }

        @Override
        public int attack() {
            if(isAlive()) {
                System.out.println(name + " attacks");
                System.out.println(die());
                return 3;
            }
            return 0;
        }

        @Override
        public int rangeType() {
            System.out.println(name + " can attack only when enemy is nearby");
            return limited_range;
        }

        @Override
        public int killType() {
            System.out.println(name + " can kill on contact");
            return closeContact;
        }
    }

    public static class Jalapeno extends Plant implements InstantKiller, Attacker{
        public Jalapeno() {
            super("Jalapeno", INFINITE,125);
        }

        @Override
        public String die() {
            return super.die() + " while exploding";
        }

        @Override
        public int attack() {
            if(isAlive()) {
                System.out.println(name + " attacks");
                System.out.println(die());
                return 5;
            }
            return 0;
        }

        @Override
        public int rangeType() {
            System.out.println(name + " can attack on a single line");
            return single_line;
        }

        @Override
        public int killType() {
            System.out.println(name + " can kill instantly");
            return instantly;
        }
    }

    public static class LilyPad extends Plant{
        public LilyPad() {
            super("Lily Pad", 6,25);

        }
    }

    public static class Cattail extends Plant implements Attacker, Upgradable{
        public Cattail() {
            super("Cattail", 6, 225);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public int rangeType() {
            System.out.println(name + " can attack any enemies from anywhere");
            return free_range;
        }

        @Override
        public PlantUpgrade upgrade() {
            return new PlantUpgrade() {
                @Override
                public int concurrentSunCost() {
                    return 25;
                }
            };
        }
    }

}