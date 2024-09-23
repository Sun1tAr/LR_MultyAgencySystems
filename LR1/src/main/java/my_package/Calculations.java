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

        switch (N){
            case 1: t = "X";
            case 2: t = "XX";
            case 3: t = "XXX";
            case 4: t = "XL";
            case 5: t = "L";
            case 6: t = "LX";
            case 7: t = "LXX";
            case 8: t = "LXXX";
            case 9: t = "XC";
            case 10: t = "C";
            default: t = "";
        }

        switch (n){
            case 1: o = "I";
            case 2: o = "II";
            case 3: o = "III";
            case 4: o = "IV";
            case 5: o = "V";
            case 6: o = "VI";
            case 7: o = "VII";
            case 8: o = "VIII";
            case 9: o = "IX";
            case 10: o = "X";
            default: o = "";
        }
        return t + o;
    }

    public int convertToArabic(String number){
        int o;

        switch (number){
            case "I" : o = 1;
            case "II" : o = 2;
            case "III" : o = 3;
            case "IV" : o = 4;
            case "V" : o = 5;
            case "VI" : o = 6;
            case "VII" : o = 7;
            case "VIII" : o = 8;
            case "IX" : o = 9;
            case "X" : o = 10;
            default: o = 0;
        }
        return o;
    }
}
