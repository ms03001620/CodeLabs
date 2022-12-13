import java.util.Scanner;

public class HelloWorld {
   // public static void main(String[] args) {
   //     System.out.println("hello world!!!");
   // }

    public static String stringsXOR(String s, String t) {
        String res = new String("");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == t.charAt(i)){
                sb.append("0");
            }
            else{
                sb.append("1");
            }
        }
        res = sb.toString();
        return res;
    }

    public static void main(String[] args) {

        String s, t;
        Scanner in = new Scanner(System.in);
        s = in.nextLine();
        t = in.nextLine();
        System.out.println(stringsXOR(s, t));

    }

}
