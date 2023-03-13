
public class Main {
    public static void main(String[] args) {
        Converterr cnv = new Converterr( 2149583360L);
        cnv.conv();
        cnv.conv2("128.32.10.0");
        System.out.println("Second");
        cnv.conv(255L);
        cnv.conv2("0.0.0.255");

    }
}