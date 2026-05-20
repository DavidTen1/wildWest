package org.example;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Random;

public class Cowboy {
    public final int id;
    public int healthPoints;
    public Cowboy(int id){
        this.healthPoints = 10;
        this.id = id;
    }

    public static ArrayList<Cowboy> createCowboyArray(int cowboyAmount){
        ArrayList<Cowboy> cowboys = new ArrayList<>();
        IntStream.range(0, cowboyAmount).forEach(i -> cowboys.add(new Cowboy(i)));

    return  cowboys;
    }

    public static void shootCowboy(int shooterIndex, ArrayList<Cowboy> cowboys) {
        if (cowboys.size() <= 1) {
            return;
        }

        Random random = new Random();
        int damage = random.nextInt(1, 6);
        Cowboy shooter = cowboys.get(shooterIndex);

        int targetIndex = (shooter.healthPoints % 2 != 0) ? (shooterIndex == 0 ? cowboys.size() - 1 : shooterIndex - 1)
                : (shooterIndex == cowboys.size() - 1 ? 0 : shooterIndex + 1);

        Cowboy target = cowboys.get(targetIndex);
        int targetPrevHealth = target.healthPoints;
        target.healthPoints -= damage;



        if (target.healthPoints <= 0) {
            cowboys.remove(targetIndex);
        }

        ShootingStoringFile.saveShootingRound(
                "shooting-log.json",
                shooter.id,
                shooterIndex,
                target.id,
                targetIndex,
                targetPrevHealth,
                damage,
                target.healthPoints
        );

    }

}
