package kg.geeks.game.logic;

import kg.geeks.game.players.*;
import java.util.Random;

public class RPG_Game {

    public static Random random = new Random();
    private static int roundNumber;

    public static void startGame() {
        Boss boss = new Boss(1000, 50, "Dark Lord");

        Warrior w1 = new Warrior(280, 15, "Saifullo");
        Warrior w2 = new Warrior(270, 20, "Vlad");
        Magic magic = new Magic(290, 10, "Pedri");
        Medic doc = new Medic(250, 5, "Albus", 15);
        Medic assistant = new Medic(300, 5, "Sirius", 5);
        Berserk berserk = new Berserk(260, 15, "Assassin");
        Avrora avrora = new Avrora(260, 15, "Avrora");
        Antman antman = new Antman(250, 12, "Antman");
        Hacker hacker = new Hacker(240, 10, "Hacker");

        Hero[] heroes = {
                w1, doc, magic, berserk, w2, assistant,
                avrora, antman, hacker
        };

        printStatistics(boss, heroes);
        while (!isGameOver(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("ROUND: " + roundNumber + " -------------");
        System.out.println(boss);
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }

    private static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence();
        boss.attack(heroes);
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0 && boss.getHealth() > 0
                    && hero.getAbility() != boss.getDefence()) {
                hero.attack(boss);
                hero.applySuperPower(boss, heroes);
            }
        }
        printStatistics(boss, heroes);
    }

    private static boolean isGameOver(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }
}