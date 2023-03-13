import java.util.ArrayList;
import java.util.List;

public class Converterr {

    long intIP = 0;
    String strIP = "";

    public Converterr(String str){
        strIP = str;
    }
    public Converterr(long int_ip){
        intIP = int_ip;

    }

    private String int_to_str(long int_ip) {
        String str = "";
        String buf_str = Long.toString(int_ip, 16); // Перевод в 16-тиричную систему
        int num = buf_str.length();
        String buf = "";
        if (num < 8) { // Создаём добавочные нули (для целостности)
            for (int i = 0; i < 8-num; i++) {
                buf += "0";
            }
        }
        String buf2 = buf + buf_str; // Складываем нули и преобразованную в 16-тиричную систему часть

        String buf_str3 = "";
        for (int i = 0; i < 8; i+=2) { // Преобразуем в нужный нам вид 0.0.0.0
            buf_str3 += Long.parseLong((Character.toString(buf2.charAt(i)) + Character.toString(buf2.charAt(i+1))), 16);
            if(i!=6) {
                buf_str3 += ".";
            }
        }
        System.out.println(buf_str3);
        return str;
    }

    private Long str_to_int(String str_ip) {
        long intp = 0;
        String str_bf = "";
        String[] str_buf = str_ip.split("\\."); // Разделяем на части IP

        for(int i = 0; i < str_buf.length; i++) { // Трансформируем в шестнадцатиричную систему при правилах, 0 = 00, одно число
            // например а = 0а, два числа и останется двумя числами аа = аа
            if (!str_buf[i].equals("0")){
                if (Long.toString(Integer.parseInt(str_buf[i]), 16).length()==2)
                    str_bf += Long.toString(Integer.parseInt(str_buf[i]), 16);

                else {
                    str_bf += "0";
                    str_bf += Long.toString(Integer.parseInt(str_buf[i]), 16);
                }

            } else {
                str_bf += "00";
            }
        }

        System.out.println(Long.parseLong(str_bf, 16)); // преобразование в инт
        return intp;
    }
    public void conv(){
        int_to_str(intIP);
    }
    public void conv(long intIP){
        int_to_str(intIP);
    }
    public void conv2(){
        str_to_int(strIP);
    }
    public void conv2(String strIP){
        str_to_int(strIP);
    }
}
