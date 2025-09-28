package kg.geeks.game.players;

public class Magic extends Hero {
    private int boostPerRound = 5;
    private int roundsApplied = 0;

    public Magic(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.BOOST);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (roundsApplied < 4 && this.getHealth() > 0 && boss.getHealth() > 0) {
            for (Hero h : heroes) {
                if (h.getHealth() > 0) {
                    h.setDamage(h.getDamage() + boostPerRound);
                }
            }
            roundsApplied++;
            System.out.println(getName() + " усилил урон всей команды на +" + boostPerRound +
                    " (раундов буста: " + roundsApplied + "/4)");
        }
    }
}