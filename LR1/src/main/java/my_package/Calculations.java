package my_package;

public abstract class Calculations {

    public abstract String getValue();

    public abstract String addition(String number);

    public abstract String substraction(String number);

    public abstract String multiplication(String number);

    public abstract String division(String number);

    public String convertToRoman(int number){

        int N = number / 10;
        int n = number % 10;

        String t, o;

        t = switch (N) {
            case 1 -> "X";
            case 2 -> "XX";
            case 3 -> "XXX";
            case 4 -> "XL";
            case 5 -> "L";
            case 6 -> "LX";
            case 7 -> "LXX";
            case 8 -> "LXXX";
            case 9 -> "XC";
            case 10 -> "C";
            default -> "";
        };

        o = switch (n) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> "";
        };
        return t + o;
    }

    public int convertToArabic(String number){

        return switch (number) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }
}
