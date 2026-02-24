package view;

import dao.impl.PartDaoImpl;
import model.Part;

import java.util.Scanner;

public class ConsoleMenu {

    public static void start() {
        Scanner sc = new Scanner(System.in);
        PartDaoImpl dao = new PartDaoImpl();

        while (true) {
            System.out.println("1. Добавить запчасть");
            System.out.println("2. Показать все");
            System.out.println("0. Выход");

            int choice = sc.nextInt();

            if (choice == 1) {
                sc.nextLine();
                System.out.print("Название: ");
                String name = sc.nextLine();
                System.out.print("Количество: ");
                int qty = sc.nextInt();
                System.out.print("Цена: ");
                double price = sc.nextDouble();

                dao.save(new Part(0, name, qty, price));
            }

            if (choice == 2) {
                dao.findAll().forEach(p ->
                        System.out.println(p.getId() + " " + p.getName()));
            }

            if (choice == 0) break;
        }
    }
}
