package alisa;

public class JavaTest {
    public static void main(String args[]){
      double[] mylist = {1.9, 2.9, 3.4, 3.5};
      double max = mylist[0];
      for(int i =1;i<mylist.length;i++){
          if(mylist[i]>max){
              max=mylist[i];
          }
      }
        System.out.print("Max is :"+ max);
    }
}
