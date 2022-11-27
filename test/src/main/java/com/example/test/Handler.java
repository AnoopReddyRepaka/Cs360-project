package com.example.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public final class Handler {

    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Pizza> pizzas = new ArrayList<>();
    static int currentSTDIndex = -1;

    public Handler() {
        loadStudents();
        loadPizzas();
    }

    private void loadStudents() {

        try {
            Scanner sc = new Scanner(new File("C:\\Users\\anoop\\Downloads\\test\\test\\src\\main\\java\\com\\example\\test\\students.txt"));
            while (sc.hasNext()) {
                students.add(new Student(sc.nextLine()));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    private void loadPizzas() {
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\anoop\\Downloads\\test\\test\\src\\main\\java\\com\\example\\test\\pizzas.txt"));
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(";,;");
                pizzas.add(new Pizza(line[0], line[1], line[2], line[3],
                        line[4], line[5]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void savePizzas() {

        try {
            FileWriter fw = new FileWriter(new File("C:\\Users\\anoop\\Downloads\\test\\test\\src\\main\\java\\com\\example\\test\\" +
                    "pizzas.txt"));
            for (Pizza pizza : pizzas) {
                fw.write(pizza.getOrderId() + ";,;" + pizza.getStudentID() + ";,;"
                        + pizza.getType() + ";,;" + pizza.getToopings() + ";,;"
                        + pizza.getPickupTime() + ";,;" + pizza.getStatus() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
