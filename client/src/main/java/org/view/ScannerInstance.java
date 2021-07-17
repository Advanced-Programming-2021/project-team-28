package org.view;

import java.util.Scanner;

public class ScannerInstance {
    private static ScannerInstance scannerInstance = null;

    private Scanner scanner;
    private ScannerInstance (){
        this.scanner = new Scanner(System.in);
    }

    public static ScannerInstance getInstance(){
        if(scannerInstance == null){
            scannerInstance = new ScannerInstance();
        }

        return scannerInstance;
    }

    public Scanner getScanner(){
        return this.scanner;
    }
}
