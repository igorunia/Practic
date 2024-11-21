package com.github.igorunia.otus;

import java.util.Scanner;

public class ScreenATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();
        while (true) {
            System.out.println("Добро пожаловать в банкомат!");
            System.out.println("1. Проверить баланс.");
            System.out.println("2. Пополнить счет.");
            System.out.println("3. Снять деньги.");
            System.out.println("4. Показать доступные купюры.");
            System.out.println("0. Выход.");
            System.out.print("Выбирите действие: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Введите сумму для пополнения: ");
                    int depositAmount = scanner.nextInt();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Введите сумму для снятия: ");
                    int withdrawAmount = scanner.nextInt();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    atm.displayNominal();
                    break;
                case 0:
                    System.out.println("Спасибо за использование банкомата!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Не верный выбор. Попробуйте снова");

            }
        }
    }
}
