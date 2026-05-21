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
        System.out.println("Enter cowboys amount: ");
        int cowboysAmount = input.nextInt();

        if (cowboysAmount < 2) {
            input.close();
            throw new IllegalArgumentException("At least 2 cowboys needed!");
        }

        IntStream.range(0, cowboysAmount).forEach(i -> cowboys.add(new Cowboy(i + 1)));
        input.close();
    }

    public static int defineNextShooterIndex(int shooterIndex, int damage) {
        if (cowboys == null || cowboys.size() <= 1 ||
                shooterIndex < 0 || shooterIndex >= cowboys.size() || damage < 1 || damage > 5) {
            return -1;
        }

        int targetIndex = defineNextTargetIndex(shooterIndex);

        // Will the target die from the current shot?
        boolean targetKilled = cowboys.get(targetIndex).healthPoints - damage <= 0;

        if (!targetKilled) {
            // target becomes the next shooter if alive.
            return targetIndex;
        }

        if (targetIndex < shooterIndex) {
            return shooterIndex - 1;
        }
        // Default: if the target dies, the shooter stays the shooter.
        return shooterIndex;
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


    public static void shootCowboy(int shooterIndex, int targetIndex, int damage) {
        if (shooterIndex < 0 || shooterIndex >= cowboys.size()) {
            throw new IllegalArgumentException("Shooter index not in cowboys list!");
        }

        if (targetIndex < 0 || targetIndex >= cowboys.size()) {
            throw new IllegalArgumentException("Target index not in cowboys list!");
        }

        if (shooterIndex == targetIndex) {
            throw new IllegalArgumentException("Shooter can not be his own target!");
         }
        if(damage < 1 || damage > 5){
            throw new IllegalArgumentException("Damage must be between 1 and 5!");
        }

        int shooterID = cowboys.get(shooterIndex).id;
        int targetID = cowboys.get(targetIndex).id;
        int shooterHealthPoints = cowboys.get(shooterIndex).healthPoints;
        // Damage the target.
        cowboys.get(targetIndex).healthPoints -= damage;
        int targetHealthPoints = cowboys.get(targetIndex).healthPoints;
        int targetPrevHealthPoints = targetHealthPoints + damage;
        System.out.println(
                "Shooter ID: " + shooterID +
                        ", Target ID: " + targetID +
                        ", Shooter HP: " + shooterHealthPoints +
                        ", Shooter index: " + shooterIndex +
                        ", Target index: " + targetIndex +
                        ", Target previous HP: " + targetPrevHealthPoints +
                        ", Damage: " + damage +
                        ", Target current HP: " + targetHealthPoints
        );

        // Store info about the shot in the JSON protocol file.
         ShootingStoringFile.saveShootingRound(
                 "shooting-log.json",
                 shooterID,
                 shooterIndex,
                 shooterHealthPoints,
                 targetID,
                 targetIndex,
                 targetPrevHealthPoints,
                 damage,
                 targetHealthPoints);

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
