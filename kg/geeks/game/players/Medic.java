package kg.geeks.game.players;

public class Medic extends Hero {
    private final int healPoints;

    public Medic(int health, int damage, String name, int healPoints) {
        super(health, damage, name, SuperAbility.HEAL);
        this.healPoints = healPoints;
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        for (Hero hero : heroes) {
            if(hero.getHealth() > 0 && hero  != this ) {
                hero.setHealth(hero.getHealth() + healPoints);
            }
        }
    }
}
