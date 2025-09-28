package kg.geeks.game.players;

import java.util.Random;

public class Hacker extends Hero {
    private final Random rnd = new Random();
    private int cooldown = 0;
    private int stealAmount = 30;

    public Hacker(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.HACK);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (getHealth() <= 0 || boss.getHealth() <= 0) return;

        if (cooldown == 0) {
            int idx;
            do {
                idx = rnd.nextInt(heroes.length);
            } while (heroes[idx].getHealth() <= 0);

            int taken = Math.min(stealAmount, boss.getHealth());
            boss.setHealth(boss.getHealth() - taken);
            heroes[idx].setHealth(heroes[idx].getHealth() + taken);

            System.out.println(getName() + " украл у босса " + taken +
                    " HP и отдал герою " + heroes[idx].getName());

            cooldown = 1;
        } else {
            cooldown = 0;
        }
    }
}