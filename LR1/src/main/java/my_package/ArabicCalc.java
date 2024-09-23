package my_package;

public class ArabicCalc extends Calculations{

    final int value;

    public ArabicCalc(String value){
        this.value = Integer.parseInt (value);
    }

    @Override
    public String getValue() {
        return "" + this.value;
    }

    @Override
    public String addition(String number) {
        int intNum = Integer.parseInt(number);
        int result = this.value + intNum;
        return "" + result;
    }

    @Override
    public String substraction(String number) {
        int intNum = Integer.parseInt(number);
        int result = this.value - intNum;
        return "" + result;
    }

    @Override
    public String multiplication(String number) {
        int intNum = Integer.parseInt(number);
        int result = this.value * intNum;
        return "" + result;
    }

    @Override
    public String division(String number) {
        int intNum = Integer.parseInt(number);
        int result = this.value / intNum;
        return "" + result;
    }
}
