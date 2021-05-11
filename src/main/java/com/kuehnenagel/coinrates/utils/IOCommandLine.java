package com.kuehnenagel.coinrates.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class IOCommandLine {

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        //TODO
//        sc.close();
        return input;
    }

    public void writeOutput(String output) {
        System.out.println(output);
    }

}
