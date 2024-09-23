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
            case 1:
                t = "X";
                break;
            case 2:
                t = "XX";
                break;
            case 3:
                t = "XXX";
                break;
            case 4:
                t = "XL";
                break;
            case 5:
                t = "L";
                break;
            case 6:
                t = "LX";
                break;
            case 7:
                t = "LXX";
                break;
            case 8:
                t = "LXXX";
                break;
            case 9:
                t = "XC";
                break;
            case 10:
                t = "C";
                break;
            default: t = "";
        }

        switch (n){
            case 1:
                o = "I";
                break;
            case 2:
                o = "II";
                break;
            case 3:
                o = "III";
                break;
            case 4:
                o = "IV";
                break;
            case 5:
                o = "V";
                break;
            case 6:
                o = "VI";
                break;
            case 7:
                o = "VII";
                break;
            case 8:
                o = "VIII";
                break;
            case 9:
                o = "IX";
                break;
            case 10:
                o = "X";
                break;
            default:
                o = "";
        }
        return t + o;
    }

    public int convertToArabic(String number){
        int o;

        switch (number){
            case "I" :
                o = 1;
                break;
            case "II" :
                o = 2;
                break;
            case "III" :
                o = 3;
                break;
            case "IV" :
                o = 4;
                break;
            case "V" :
                o = 5;
                break;
            case "VI" :
                o = 6;
                break;
            case "VII" :
                o = 7;
                break;
            case "VIII" :
                o = 8;
                break;
            case "IX" :
                o = 9;
                break;
            case "X" :
                o = 10;
                break;
            default:
                o = 0;
                break;
        }
        return o;
    }
}
