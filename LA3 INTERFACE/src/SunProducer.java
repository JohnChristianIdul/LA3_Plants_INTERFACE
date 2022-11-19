interface SunProducer {
    int produce_sun();
}

interface PlantUpgrade {
    int concurrentSunCost();
}

interface Attacker {
    int single_line = 1;
    int AOE = 2;
    int limited_range = 3;
    int  free_range = 4;

    int attack();
    int rangeType();
}

interface InstantKiller {
    int instantly = 1;
    int closeContact = 2;
    int killType();
}

interface Upgradable {
    PlantUpgrade upgrade();
}
