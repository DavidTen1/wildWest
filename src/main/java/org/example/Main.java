package org.example;
import static org.example.Cowboy.cowboys;
import static org.example.Cowboy.random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void documentShooting() {
        Cowboy.createCowboyArray();

        int shooterIndex = random.nextInt(0, cowboys.size());

        while (cowboys.size() > 1) {
            int damage = random.nextInt(1, 6);

            int targetIndex = Cowboy.defineNextTargetIndex(shooterIndex);

            int nextShooterIndex = Cowboy.defineNextShooterIndex(shooterIndex, damage);

            Cowboy.shootCowboy(shooterIndex, targetIndex, damage);

            shooterIndex = nextShooterIndex;
        }

        System.out.println("Winner: Cowboy " + cowboys.getFirst().id);
    }


    public static void main(String[] args) {
        documentShooting();

        String checksum = ChecksumTransformation.calculateSHA256("shooting-log.json");
        System.out.println("SHA-256 checksum: " + checksum);

    }
}