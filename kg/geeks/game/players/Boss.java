package kg.geeks.game.players;

import kg.geeks.game.logic.RPG_Game;

public class Boss extends GameEntity {
    private SuperAbility defence;

    public Boss(int health, int damage, String name) {
        super(health, damage, name);
    }

    public void chooseDefence() {
        SuperAbility[] variants = SuperAbility.values();
        int randomIndex = RPG_Game.random.nextInt(variants.length);
        defence = variants[randomIndex];
    }

    public SuperAbility getDefence() {
        return defence;
    }

    public void attack(Hero[] heroes) {
        for (Hero hero : heroes) {
            if (hero.getHealth() <= 0) continue;

            if (hero instanceof Avrora avrora && avrora.isInvisible()) {
                avrora.addIncomingFromBoss(this.getDamage());
                continue;
            }

            if ((hero instanceof Berserk b) && this.defence != hero.getAbility()) {
                int blocked = RPG_Game.random.nextInt(1, 3) * 5; // 5 или 10
                b.setBlockedDamage(blocked);
                hero.setHealth(hero.getHealth() - (this.getDamage() - blocked));
            } else {
                    hero.setHealth(hero.getHealth() - this.getDamage());
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " defence: " + this.defence;
    }
}