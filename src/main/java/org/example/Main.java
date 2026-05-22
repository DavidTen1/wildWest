package org.example;
import java.util.Scanner;

import static org.example.Cowboy.cowboys;
import static org.example.Cowboy.random;

public class Main {

    public static void documentShooting(int cowboysAmount) {
        Cowboy.createCowboyArray(cowboysAmount);

        int shooterIndex = random.nextInt(0, cowboys.size());

        // Continue the shootout until only one cowboy remains.
        while (cowboys.size() > 1) {

            int damage = random.nextInt(1, 6);

            int targetIndex = Cowboy.defineNextTargetIndex(shooterIndex);

            int nextShooterIndex = Cowboy.defineNextShooterIndex(shooterIndex, damage);

            // The attacker shoots the target, causing 1-5 health points as damage.
            Cowboy.shootCowboy(shooterIndex, targetIndex, damage);

            // the next shooter will either be the current shooter or his target if still alive
            // Beware: If the target is killed, it is removed and the list indexes may change.
            shooterIndex = nextShooterIndex;
        }

        System.out.println("Winner: Cowboy " + cowboys.get(0).id);
    }


    public static void main(String[] args) {
        String shootingsFile = "shooting-log.json";
        String checksumFile = "shooting-log.sha512";

        String checksumStatusString = ChecksumTransformation.checkChecksum(shootingsFile, checksumFile) ?
                "Valid. Protocol unchanged." : "Invalid or not found.";
        System.out.println("Checksum status: " + checksumStatusString);

        Scanner input = new Scanner(System.in);
        System.out.println("Enter cowboys amount:");
        int cowboysAmount = input.nextInt();
        input.close();


        ShootingStoringFile.resetFile(shootingsFile);
        ShootingStoringFile.resetFile(checksumFile);
        documentShooting(cowboysAmount);

        // Calculate and print the checksum of the completed protocol file.
        String checksum = ChecksumTransformation.calculateSHA512(shootingsFile);
        System.out.println("SHA-512 checksum: " + checksum);
        ChecksumTransformation.saveChecksum(checksum, checksumFile);
    }
}