package tucildua;

public class BigNumLib {     
    // mengembalikan true jika string merupakan bilangan negatif dan false bila positif atau nol
    public static boolean isMinus(String s){
        return (s.charAt(0)=='-');
    }
    
    //mengembalikan true string yang pertama lebih kecil dari string yang kedua dan false jika sebaliknya atau sama
    public static boolean smallerthan(String s1, String s2){
        for(int i = 0; i < s1.length(); i++){
            if(s2.charAt(i) > s1.charAt(i)){
                return true;
            } else if (s2.charAt(i) < s1.charAt(i)){
                return false;
            }
        }
        return false;
    }
    
    //menjumlahkan dua buah bilangan
    public static String plus(String s1, String s2){
        String hsl = "";
        if (!isMinus(s1)&&!isMinus(s2)){ //kedua bilangan positif
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
            hsl = hsl.replaceFirst("^0+(?!$)", "");
        } else if (isMinus(s1)&&isMinus(s2)){ //kedua bilangan negatif
            hsl = '-' + plus(s1.substring(1),s2.substring(1));
        } else if (isMinus(s1)&&!isMinus(s2)){ //bilangan pertama negatif, bilangan kedua positif
            hsl = minus(s2, s1.substring(1));
        } else { //bilangan pertama positif, bilangan kedua negatif
            hsl = minus(s1, s2.substring(1));
        }
        return hsl;
    }
    
    //mengurangkan bilangan pertama dengan bilangan kedua
    public static String minus(String s1, String s2){
        String hsl = "";
        if (!isMinus(s1)&&!isMinus(s2)){ //kedua bilangan positif
            String min = "";
            if (smallerthan(s1,s2)){
                String temp = s1;
                s1 = s2;
                s2 = temp;
                min = "-";
            }
            boolean mincarry = false;
            String s[] = addZero(s1,s2);
            for (int i=s[0].length()-1;i>=0;i--){
                int temp = (s[0].charAt(i) - '0') - (s[1].charAt(i) - '0');
                if (mincarry) temp -= 1;
                if (temp < 0){
                    mincarry = true;
                    temp += 10;
                } else 
                    mincarry = false;  
                hsl = temp + hsl;
            }
            hsl = min + hsl.replaceFirst("^0+(?!$)", "");
        } else if (!isMinus(s1)&&isMinus(s2)){ //bilangan pertama positif, bilangan kedua negatif
            hsl = plus(s1,s2.substring(1));
        } else if (isMinus(s1)&&!isMinus(s2)){  //bilangan pertama negatif, bilangan kedua positif
            hsl = '-' + plus(s1.substring(1),s2);
        } else { //kedua bilangan negatif
            hsl = minus(s2.substring(1),s1.substring(1));
        }
        return hsl;
    }
    
    //menambahkan angka nol pada angka yang memiliki jumlah digit lebih sedikit hingga jumlah digitnya sama
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
