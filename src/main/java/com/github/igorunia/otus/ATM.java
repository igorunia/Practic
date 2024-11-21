package com.github.igorunia.otus;

import java.util.*;

public class ATM {

    final private SortedMap<Integer, Integer> nominal;
    private int balance;

    public ATM() {
        nominal = new TreeMap<>(Collections.reverseOrder());
        nominal.put(500, 10);
        nominal.put(200, 20);
        nominal.put(100, 30);
        nominal.put(50, 50);
        balance = 0;
    }

    public void displayNominal() {
        System.out.println("Доступные номиналы купюр:");
        nominal.forEach((key, value) -> System.out.printf("Купюра: %d - Доступно: %d%n",
                key, value));
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Вы пополнили счет на: %d%n" +
                    "Текущий баланс: %d%n", amount, balance);
        } else {
            System.out.println("Сумма пополнения должна быть положительной.");
        }
    }

    public void withdraw(int amount) {
        if (amount > balance) {
            System.out.println("Недостаточно средств на счете.");
            return;
        }
        int remaining = amount;
        SortedMap<Integer, Integer> toWithdraw = new TreeMap<>(Collections.reverseOrder());

        List<Map.Entry<Integer, Integer>> entries = nominal.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .toList();
        for (Map.Entry entry : entries) {
            int denomination = (int) entry.getKey();
            int count = (int) entry.getValue();
            int needed = remaining / denomination;

            if (needed > 0) {

                int toTake = Math.min(needed, count);
                toWithdraw.put(denomination, toTake);
                remaining -= toTake * denomination;
            }
            if (remaining == 0) break;
        }
        if (remaining > 0) {
            System.out.println("Не удалось выдать запрашиваемую сумму. Проверьте доступные купюры.");
            return;
        }
        toWithdraw.forEach((denomination,
                            count) ->
                nominal.put(denomination,
                        nominal.get(denomination) - count));

        balance -= amount;
        System.out.printf("Вы сняли: %d%n" +
                "Текущий баланс: %d%n", amount, balance);
    }

    public void checkBalance() {
        System.out.printf("Ваш текущий баланс: %d%n", balance);
    }

}
