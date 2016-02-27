package tucildua;

public class BigNumLib {     
    public static boolean isMinus(String s){
        return (s.charAt(0)=='-');
    }
    
    public static String plus(String s1, String s2){
        String hsl = "";
        if (!isMinus(s1)&&!isMinus(s2)){
            String s[] = addZero(s1,s2);
            boolean carry = false;
            for (int i=s[0].length()-1;i>=0;i--){
                int temp = (s[0].charAt(i) - '0') + (s[1].charAt(i) - '0');
                if (carry) temp += 1;
                if (temp > 9){
                    carry = true;
                    temp -= 10;
                } else 
                    carry = false;    
                hsl = temp + hsl;
            }
            if (carry) hsl = 1 + hsl;
        } else if (isMinus(s1)&&isMinus(s2)){
            hsl = '-' + plus(s1.substring(1),s2.substring(1));
        }
        return hsl;
    }
    
    public static String minus(String s1, String s2){
        String hsl = "";
        if (!isMinus(s1)&&isMinus(s2)){
            hsl = plus(s1,s2.substring(1));
        } else if (isMinus(s1)&&!isMinus(s2)){
            hsl = '-' + plus(s1.substring(1),s2);
        }
        return hsl;
    }
    
    public static String[] addZero(String s1, String s2){
        if (s1.length()>s2.length()){
            int limit = s1.length()-s2.length();
            for (int i=0;i<limit;i++){
                s2 = "0" + s2;
            }
        } else if (s1.length()<s2.length()){
            int limit = s2.length()-s1.length();
            for (int i=0;i<limit;i++){
                s1 = "0" + s1;
           }
        } 
        return new String[]{s1,s2};
    }
    /*
    public static void main(String args[]){
        String s1 = "7654729850328997631007285998163550104";
        String s2 = "5980139243970186632651869926335829102";
        System.out.println(plus(s1, s2));
    }*/
}
