package tucildua;

public class BigNumLib {
    //konstruktor
    public BigNumLib(){
        this.count = 0;
        this.product = 0;
    }
    //setter
    public void setCount(int c){
        this.count = c;
    }
    public void setProduct(int c){
        this.product = c;
    }
    //getter
    public int getCount(){
        return this.count;
    }
    public int getProduct(){
        return this.product;
    }
    
    // mengembalikan true jika string merupakan bilangan negatif dan false bila positif atau nol
    public static boolean isMinus(String s){
        return (s.charAt(0)=='-');
    }
    
    //mengembalikan true string yang pertama lebih kecil dari string yang kedua dan false jika sebaliknya atau sama
    public static boolean smallerthan(String s1, String s2){
        for(int i = 0; i < s1.length()-1; i++){
            if(s2.charAt(i) > s1.charAt(i)){
                return true;
            } else if (s2.charAt(i) < s1.charAt(i)){
                return false;
            }
        }
        return false;
    }
    
    //menjumlahkan dua buah bilangan
    public String plus(String s1, String s2){
        String hsl = "";
        if (!isMinus(s1)&&!isMinus(s2)){ //kedua bilangan positif
            String s[] = addZero(s1,s2);
            boolean carry = false;
            for (int i=s[0].length()-1;i>=0;i--){
                int temp = (s[0].charAt(i) - '0') + (s[1].charAt(i) - '0');
                this.count++;
                if (carry) {
                    temp += 1;
                    this.count++;
                }
                if (temp > 9){
                    carry = true;
                    temp %= 10;
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
    public String minus(String s1, String s2){
        String hsl = "";
        if (!isMinus(s1)&&!isMinus(s2)){ //kedua bilangan positif
            String min = "";
            String s[] = addZero(s1,s2);
            if (smallerthan(s[0],s[1])){
                String temp = s[0];
                s[0] = s[1];
                s[1] = temp;
                min = "-";
            }
            boolean mincarry = false;
            for (int i=s[0].length()-1;i>=0;i--){
                int temp = (s[0].charAt(i) - '0') - (s[1].charAt(i) - '0');
                this.count++;
                if (mincarry) {
                    temp -= 1;
                    this.count++;
                }
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
        } else if (isMinus(s1)&&!isMinus(s2)){ //bilangan pertama negatif, bilangan kedua positif
            hsl = '-' + plus(s1.substring(1),s2);
        } else { //kedua bilangan negatif
            hsl = minus(s2.substring(1),s1.substring(1));
        }
        return hsl;
    }
    
    //mengalikan bilangan pertama dengan bilangan kedua
    public String multiply(String s1, String s2){
        if (!isMinus(s1)&&!isMinus(s2)){ //kedua bilangan positif
            String s[] = addZero(s1,s2);
            return karatsuba(s[0],s[1]);
        } else if (!isMinus(s1)&&isMinus(s2)){ //bilangan pertama positif, bilangan kedua negatif
            String s[] = addZero(s1,s2.substring(1));
            return '-' + karatsuba(s[0],s[1]);
        } else if (isMinus(s1)&&!isMinus(s2)){ //bilangan pertama negatif, bilangan kedua positif
            String s[] = addZero(s1.substring(1),s2);
            return '-' + karatsuba(s[0],s[1]);
        }
        //kedua bilangan negatif
        String s[] = addZero(s1.substring(1),s2.substring(1));
        return karatsuba(s[0],s[1]);
    }
    
    //menggunakan algoritma karatsuba
    public String karatsuba(String s1, String s2){
        if ((s1.length()==1)||(s2.length()==1)){
            this.product++;
            return (Integer.parseInt(s1)*Integer.parseInt(s2))+"";
        }
        // calculate the number size
        int m = Math.max(s1.length(), s1.length());
        int m2 = m/2;
        // split the digit in the middle
        String a = s1.substring(0, m2);
        String b = s1.substring(m2, s1.length());
        String c = s2.substring(0, m2);
        String d = s2.substring(m2, s2.length());
        //recursive function
        String p = multiply(a, c);
        String q = multiply(b, d);
        String r = multiply(plus(a,b),plus(c,d));
        //compute power function
        String powp = p;
        for (int i=0;i<b.length()+d.length();i++){
            powp += '0';
        }
        String rpq = minus(minus(r,p),q);
        for (int i=0;i<Math.max(b.length(),d.length());i++){
            rpq += '0';
        }
        
        return plus(plus(powp,rpq),q);
    }
    
    //menambahkan angka nol pada angka yang memiliki jumlah digit lebih sedikit hingga jumlah digitnya sama
    public String[] addZero(String s1, String s2){
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
        String s1 = "31313131";//"7654729850328997631007285998163550104";
        String s2 = "43434343";//"5980139243970186632651869926335829102";
        System.out.println(multiply(s1, s2));
    }*/
    private int count;
    private int product;
}
