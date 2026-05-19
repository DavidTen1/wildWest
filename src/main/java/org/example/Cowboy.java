package org.example;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Random;

public class Cowboy {
    public final int index;
    public int healthPoints;
    public Cowboy(int index){
        this.healthPoints = 10;
        this.index = index;
    }

    public static ArrayList<Cowboy> createCowboyArray(int cowboyAmount){
        ArrayList<Cowboy> cowboys = new ArrayList<>();
        IntStream.range(0, cowboyAmount).forEach(i -> cowboys.add(new Cowboy(i)));

    return  cowboys;
    }

    public static int[] shootCowboy(int shooterIndex, ArrayList<Cowboy> cowboys) {
        if (cowboys.size() <= 1) {
            return new int[] { 0 };
        }

        Random random = new Random();
        int damage = random.nextInt(1, 6);

        Cowboy shooter = cowboys.get(shooterIndex);

        int targetIndex = (shooter.healthPoints % 2 != 0) ? (shooterIndex == 0 ? cowboys.size() - 1 : shooterIndex - 1)
                : (shooterIndex == cowboys.size() - 1 ? 0 : shooterIndex + 1);

        Cowboy target = cowboys.get(targetIndex);
        int targetPrevHealth = target.healthPoints;
        target.healthPoints -= damage;

        int[] shooterInfo = new int[3];
        shooterInfo[0] = shooterIndex;
        shooterInfo[1] = targetIndex;
        shooterInfo[2] = damage;

        if (target.healthPoints <= 0) {
            cowboys.remove(targetIndex);
        }

        ShootingStoringFile.saveShootingRound(
                "shooting-log.json",
                shooterIndex,
                targetIndex,
                targetPrevHealth,
                damage,
                target.healthPoints
        );

        return shooterInfo;
    }

}
