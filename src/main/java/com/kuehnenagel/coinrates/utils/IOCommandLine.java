package com.kuehnenagel.coinrates.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class IOCommandLine {

    private static final Logger log = LoggerFactory.getLogger(IOCommandLine.class);


    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void writeOutput(String output) {
        log.info(output);
    }

}
