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
        IntStream.range(0, cowboysAmount).forEach(i -> cowboys.add(new Cowboy(i)));
        input.close();
    }

    public static int defineNextTargetIndex(int shooterIndex, ArrayList<Cowboy> cowboys) {

        if (cowboys == null || cowboys.size() <= 1 || shooterIndex < 0 || shooterIndex >= cowboys.size()) {
            return -1;
        }

        if (cowboys.get(shooterIndex).healthPoints % 2 == 0) {
            // even HP -> shoot right
            return (shooterIndex + 1) % cowboys.size();
        } else {
            // odd HP -> shoot left
            return (shooterIndex - 1 + cowboys.size()) % cowboys.size();
        }
    }

    public static void shootCowboy(int shooterIndex, int targetIndex) {
        int damage = random.nextInt(1, 6);
        int shooterID = cowboys.get(shooterIndex).id;
        int targetID = cowboys.get(targetIndex).id;
        cowboys.get(targetIndex).healthPoints -= damage;
        int targetHealthPoints = cowboys.get(targetIndex).healthPoints;

        System.out.println("shooter ID " + shooterID +   " tar ID "  + targetID  +  " shooter index " + shooterIndex + " tar index " + targetIndex + " tar health " + targetHealthPoints);

         removeDeadCowboy(targetIndex);
    }

    public static void removeDeadCowboy(int cowboyIndex) {
        if (cowboys.get(cowboyIndex).healthPoints <= 0) {
            System.out.println("cowboy " + cowboys.get(cowboyIndex).id+ " killed");
            cowboys.remove(cowboyIndex);

        }
    }

}
