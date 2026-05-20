package org.example;
import static org.example.Cowboy.random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void docShooting() {

    }


    public static void main(String[] args) {
    Cowboy.createCowboyArray();
    Cowboy.shootCowboy(0,1 , random.nextInt(6));
     System.out.println("next target " + Cowboy.defineNextTargetIndex(0));

//        String checksum = ChecksumTransformation.calculateSHA256("shooting-log.json");
//
//        System.out.println("SHA-256 checksum: " + checksum);

    }
}