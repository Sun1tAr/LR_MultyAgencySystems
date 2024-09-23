package my_package;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            flag = false;
            Scanner sc = new Scanner(System.in);
            String string = sc.nextLine();

            char[] strChars = string.toCharArray();

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

            if (iOperator == Integer.MAX_VALUE) {
                System.err.println("На вход подано одно число, измените входные данные: " + "\n" + num1);
                flag = true;
            } else {
                String result = "Произошла ошибка - повторите ввод";
                if (NumberIdentifier.isRomanNumber(num1) && NumberIdentifier.isRomanNumber(num2)){
                    RomanCalc rom1 = new RomanCalc(num1);
                    switch (operator){
                        case "+":
                            result = rom1.addition(num2);
                            break;
                        case "/":
                            result = rom1.division(num2);
                            break;
                        case "-":
                            result = rom1.substraction(num2);
                            break;
                        case "*":
                            result = rom1.multiplication(num2);
                            break;
                        default:
                    }

                }else if (NumberIdentifier.isArabicNumber(num1) && NumberIdentifier.isArabicNumber(num2)){
                    ArabicCalc ar1 = new ArabicCalc(num1);
                    switch (operator){
                        case "+":
                            result = ar1.addition(num2);
                            break;
                        case "/":
                            result = ar1.division(num2);
                            break;
                        case "-":
                            result = ar1.substraction(num2);
                            break;
                        case "*":
                            result = ar1.multiplication(num2);
                            break;
                        default:
                    }
                }else{
                    System.err.println("Неверный формат числа");
                }
                System.out.println("Результат: \n" + result);
            }
            if(!flag) {
                System.out.println("Повторить ввод? \n 1 - Да \n 0 - Нет");
                if (sc.nextInt() == 1) flag = true;
            }
        }
    }
}