package kg.geeks.game.players;

public class Avrora extends Hero {
    private boolean usedOnce = false;
    private int invisibleRoundsLeft = 0;
    private int storedDamage = 0;

    public Avrora(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.INVISIBILITY);
    }

    public boolean isInvisible() {
        return invisibleRoundsLeft > 0;
    }

    public void addIncomingFromBoss(int bossDamage) {
        if (isInvisible()) {
            storedDamage += bossDamage;
        }
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (getHealth() <= 0 || boss.getHealth() <= 0) return;

        if (isInvisible()) {
            invisibleRoundsLeft--;
            if (!isInvisible() && storedDamage > 0 && boss.getHealth() > 0) {
                boss.setHealth(boss.getHealth() - storedDamage);
                System.out.println(getName() + " вернула боссу накопленный урон: " + storedDamage);
                if (boss.getHealth() < 0) boss.setHealth(0);
                storedDamage = 0;
            }
            return;
        }

        if (!usedOnce) {
            usedOnce = true;
            invisibleRoundsLeft = 2;
            System.out.println(getName() + " исчезла на 2 раунда!");
        }
    }
}