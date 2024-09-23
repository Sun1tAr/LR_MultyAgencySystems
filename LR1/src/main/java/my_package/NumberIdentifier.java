package my_package;

public class NumberIdentifier {

    public static boolean isArabicNumber(String number){
        char[] key = {'0','1','2','3','4','5','6','7','8','9'};
        char[] num = number.toCharArray();
        int count = 0;

        for (int x : key){
            for (char y : num){
                if (x == y) count += 1;
            }
        }
        return (count == number.length());
    }

    public static boolean isRomanNumber(String number){
        char[] key = {'I','V','X'};
        char[] num = number.toCharArray();
        int count = 0;

        for (int x : key){
            for (char y : num){
                if (x == y) count += 1;
            }
        }
        return (count == number.length());
    }


}
