import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Factorial {
    int n = 0;
    public Factorial(int ni){
        n = ni;
    }

    // Different variants
    // 1/n! * (1! + 2! + 3! + ... + n!)
    // 1!\n! + 2!\n! + 3!\n! + ... + 1

    private void fact1(int n) {
        int round = 15;
        BigDecimal  f = new BigDecimal (1);
        for (int i = 0; i < n-1; i++) {
            f = f.add(factCalc(i+1).divide(factCalc(n), round, RoundingMode.HALF_EVEN));
            System.out.println(f);
        }
        System.out.println(f.setScale(6, RoundingMode.DOWN));
    }
    private BigDecimal factCalc(int n) {
        BigDecimal  fact = new BigDecimal (1);
        for (int i = 0; i < n; i++) {
            fact = fact.multiply(new BigDecimal(String.valueOf(i+1)));
        }
        return  fact;
    }
    //Solving
    public void calculate(int n){
        fact1(n);
    }
    public void calculate(){
        fact1(n);
    }
}
