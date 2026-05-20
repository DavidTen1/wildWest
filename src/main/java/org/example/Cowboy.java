package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Random;

public class Cowboy {
    public final int id;
    public int healthPoints;
    public static Random random = new Random();
    public static ArrayList<Cowboy> cowboys = new ArrayList<>();
    public Cowboy(int id) {
        this.healthPoints = 10;
        this.id = id;
    }

    public static void createCowboyArray() {
        Scanner input = new Scanner(System.in);
        int cowboysAmount = input.nextInt();
        IntStream.range(0, cowboysAmount).forEach(i -> cowboys.add(new Cowboy(i + 1)));
        input.close();
    }


    public static int defineNextTargetIndex(int shooterIndex) {
        if (cowboys == null || cowboys.size() <= 1) {
            throw new IllegalArgumentException("You needed at least 2 cowboys!");
        }

        if (shooterIndex < 0 || shooterIndex >= cowboys.size()) {
            throw new IllegalArgumentException("Index not in cowboys list!");
        }

        Cowboy shooter = cowboys.get(shooterIndex);

        if (shooter.healthPoints % 2 == 0) {
            return (shooterIndex + 1) % cowboys.size();
        } else {
            return (shooterIndex - 1 + cowboys.size()) % cowboys.size();
        }
    }

    public static void shootCowboy(int shooterIndex, int targetIndex) {
        if (shooterIndex < 0 || shooterIndex >= cowboys.size()) {
            throw new IllegalArgumentException("Shooter index not in cowboys list!");
        }

        if (targetIndex < 0 || targetIndex >= cowboys.size()) {
            throw new IllegalArgumentException("Target index not in cowboys list!");
        }

        if (shooterIndex == targetIndex) {
            throw new IllegalArgumentException("Shooter can not be his own target!");
         }

        int damage = random.nextInt(1, 6);
        int shooterID = cowboys.get(shooterIndex).id;
        int targetID = cowboys.get(targetIndex).id;
        cowboys.get(targetIndex).healthPoints -= damage;
        int targetHealthPoints = cowboys.get(targetIndex).healthPoints;

        System.out.println("shooter ID " + shooterID +   " tar ID "  + targetID  +  " shooter index " + shooterIndex + " tar index " + targetIndex + " tar health " + targetHealthPoints);

         ShootingStoringFile.saveShootingRound("shootingLog.json",shooterID,shooterIndex,targetID,targetIndex,damage,targetHealthPoints);

        removeDeadCowboy(targetIndex);

    }

    public static void removeDeadCowboy(int cowboyIndex) {
        if (cowboyIndex >= 0 && cowboyIndex < cowboys.size()) {
            if (cowboys.get(cowboyIndex).healthPoints <= 0) {
                System.out.println("cowboy " + cowboys.get(cowboyIndex).id+ " killed");
                cowboys.remove(cowboyIndex);
            }
        }
    }

}
