import com.github.igorunia.otus.ATM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMTest {
    private ATM atm;

    @BeforeEach
    public void setUp() {
        atm = new ATM();
    }

    @Test
    public void testDisplayNominal() {
        atm.displayNominal();

        String expectedOutput = """
                Доступные номиналы купюр:
                Купюра: 500 - Доступно: 10
                Купюра: 200 - Доступно: 20
                Купюра: 100 - Доступно: 30
                Купюра: 50 - Доступно: 50
                """;
        assertEquals(expectedOutput, """
                Доступные номиналы купюр:
                Купюра: 500 - Доступно: 10
                Купюра: 200 - Доступно: 20
                Купюра: 100 - Доступно: 30
                Купюра: 50 - Доступно: 50
                """);
    }

    @Test
    public void testDeposit() {
        atm.deposit(1000);
        String expectedOutput = """
                Вы пополнили счет на: 1000
                Текущий баланс: 1000
                """;

        assertEquals(expectedOutput, """
                Вы пополнили счет на: 1000
                Текущий баланс: 1000
                """);
    }

    @Test
    public void testWithdrawSuccess() {
        atm.deposit(1000);
        atm.withdraw(600);

        String expectedOutput = """
                Вы пополнили счет на: 1000
                Текущий баланс: 1000
                Вы сняли: 600
                Текущий баланс: 400
                """;

        assertEquals(expectedOutput, """
                Вы пополнили счет на: 1000
                Текущий баланс: 1000
                Вы сняли: 600
                Текущий баланс: 400
                """);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        atm.withdraw(100);

        String expectedOutput = "Недостаточно средств на счете.\n";
        assertEquals(expectedOutput, "Недостаточно средств на счете.\n");
    }

    @Test
    public void testWithdrawNotEnoughBills() {
        atm.deposit(1000);
        atm.withdraw(1200);

        String expectedOutput = """
                Вы пополнили счет на: 1000
                Текущий баланс: 1000
                Не удалось выдать запрашиваемую сумму. Проверьте доступные купюры.
                """;

        assertEquals(expectedOutput, """
                Вы пополнили счет на: 1000
                Текущий баланс: 1000
                Не удалось выдать запрашиваемую сумму. Проверьте доступные купюры.
                """);
    }

    @Test
    public void testCheckBalance() {
        atm.deposit(500);
        atm.checkBalance();

        String expectedOutput = """
                Вы пополнили счет на: 500
                Текущий баланс: 500
                Ваш текущий баланс: 500
                """;

        assertEquals(expectedOutput, """
                Вы пополнили счет на: 500
                Текущий баланс: 500
                Ваш текущий баланс: 500
                """);
    }
}