package org.example;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void docShooting(ArrayList<Cowboy> cowboys) {
     int shooterIndex = Cowboy.random.nextInt(cowboys.size());
     int targetIndex = Cowboy.defineTargetIndex(shooterIndex,cowboys);
     while (cowboys.size() > 1) {
         Cowboy.shootCowboy(shooterIndex,targetIndex, cowboys);
         System.out.println(shooterIndex);
     }

    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        int cowboysAmount = input.nextInt();
//        input.close();
//        ArrayList<Cowboy> cowboys = Cowboy.createCowboyArray(cowboysAmount);
//        docShooting(cowboys);
//        String checksum = ChecksumTransformation.calculateSHA256("shooting-log.json");
//
//        System.out.println("SHA-256 checksum: " + checksum);

    }
}