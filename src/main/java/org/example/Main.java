package org.example;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cowboyAmount = input.nextInt();
        input.close();
        ArrayList<Cowboy> cowboys = Cowboy.createCowboyArray(cowboyAmount);

    }
}