
public class Encryption {
    public String encrypt(String s)
   {
       String str=new String(s);
       char ch[]=str.toCharArray();
       int l=str.length();
       int count=1;
       for(int i=0;i<l;i++)
       {
           if(count>26)
               count=1;
           ch[i]=(char)((int)ch[i]+count);
           count++;
       }
       String sa=new String(ch);
       System.out.println(sa);
       return sa;
   }
    public String decrypt(String s)
    {
        String str=new String(s);
       char ch[]=str.toCharArray();
       int l=str.length();
       int count=1;
       for(int i=0;i<l-1;i++)
       {
           if(count>26)
               count=1;
           ch[i]=(char)((int)ch[i]-count);
           count++;
       }
       String sa=new String(ch);
       System.out.println(sa);
       return sa;
    }
}
