package org.example;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void docShooting(ArrayList<Cowboy> cowboys) {

    }


    public static void main(String[] args) {
        Cowboy.createCowboyArray();
        System.out.println("Welcome to Cowboy! " + Cowboy.cowboys.size());
        Cowboy.shootCowboy(0,1);


//        String checksum = ChecksumTransformation.calculateSHA256("shooting-log.json");
//
//        System.out.println("SHA-256 checksum: " + checksum);

    }
}