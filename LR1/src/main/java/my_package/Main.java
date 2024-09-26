package my_package;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        boolean flag = true;
        while (true) {

            Scanner sc = new Scanner(System.in);
            String string = sc.nextLine();

            char[] strChars = string.toCharArray();
            String result = "";
            String num1 = "";
            String num2 = "";
            String operator = "";
            int iOperator = Integer.MAX_VALUE;

            for (int i = 0; i < strChars.length; i++) {

                if (strChars[i] == '+' || strChars[i] == '-' || strChars[i] == '*' || strChars[i] == '/') {
                    iOperator = i;
                    operator += strChars[i];
                } else if (i < iOperator) {
                    num1 += strChars[i];
                } else num2 += strChars[i];
            }

            if (iOperator == Integer.MAX_VALUE || num1.isEmpty() || num2.isEmpty()) {
                System.err.println("На вход подано одно число, измените входные данные: ");
                continue;
            } else {
                if (NumberIdentifier.isRomanNumber(num1) && NumberIdentifier.isRomanNumber(num2)) {
                    RomanCalc rom1 = new RomanCalc(num1);
                    result = switch (operator) {
                        case "+" -> rom1.addition(num2);
                        case "/" -> rom1.division(num2);
                        case "-" -> rom1.substraction(num2);
                        case "*" -> rom1.multiplication(num2);
                        default -> throw new IllegalArgumentException("Ошибка");
                    };

                } else if (NumberIdentifier.isArabicNumber(num1) && NumberIdentifier.isArabicNumber(num2)) {
                    ArabicCalc ar1 = new ArabicCalc(num1);
                    result = switch (operator) {
                        case "+" -> ar1.addition(num2);
                        case "/" -> ar1.division(num2);
                        case "-" -> ar1.substraction(num2);
                        case "*" -> ar1.multiplication(num2);
                        default -> throw new IllegalArgumentException("Ошибка");
                    };
                } else {
                    System.err.println("Неверный формат числа");
                    continue;
                }
            }
            System.out.println("Результат: \n" + result);
            System.out.println("Повторить ввод? \n 1 - Да \n 0 - Нет");
            String newIn = sc.nextLine();
            if (isInt(newIn)) {
                if (Integer.parseInt(newIn) == 0) break;
            } else {
                System.err.println("Введённое значение нераспознано - программа остановлена!");
                break;
            }

        }
    }

    public static boolean isInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}