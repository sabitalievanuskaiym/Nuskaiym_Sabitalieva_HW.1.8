package kg.geeks.game.players;

import java.util.Random;

public class Antman extends Hero {
    private final Random rnd = new Random();
    private boolean mutated = false;
    private int savedDamage;
    private int size = 1;

    public Antman(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.RESIZE);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (getHealth() <= 0 || boss.getHealth() <= 0) return;

        if (mutated) {
            setDamage(savedDamage);
            mutated = false;
            size = 1;
        }

        boolean grow = rnd.nextBoolean();

        savedDamage = getDamage();
        if (grow) {
            setDamage((int) Math.round(getDamage() * 1.5));
            size = 2;
            System.out.println(getName() + " вырос: урон временно x1.5");
        } else {
            setDamage(Math.max(1, (int) Math.round(getDamage() * 0.5)));
            size = 0;
            System.out.println(getName() + " уменьшился: урон временно x0.5");
        }
        mutated = true;
    }
}