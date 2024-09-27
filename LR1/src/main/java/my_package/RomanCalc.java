package my_package;

public class RomanCalc extends Calculations{
    private final int value;

    public RomanCalc(String value){
        this.value = super.convertToArabic(value);
    }

    public String getArabicVal(){
        return "" + this.value;
    }

    @Override
    public String getValue() {
        return super.convertToRoman(this.value);
    }

    @Override
    public String addition(String number) {
        int intNum = super.convertToArabic(number);
        int result = this.value + intNum;
        return super.convertToRoman(result);
    }

    @Override
    public String substraction(String number) {
        int intNum = super.convertToArabic(number);
        int result = this.value - intNum;
        if (result < 0) return "Неположительный результат";
        else return super.convertToRoman(result);
    }

    @Override
    public String multiplication(String number) {
        int intNum = super.convertToArabic(number);
        int result = this.value * intNum;
        return super.convertToRoman(result);
    }

    @Override
    public String division(String number) {
        int intNum = super.convertToArabic(number);
        int result = this.value / intNum;
        return super.convertToRoman(result);
    }
}
