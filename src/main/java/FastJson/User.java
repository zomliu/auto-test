package FastJson;

public class User {
    private String username;
    private  int age;
    public int getAge(){
        return  age;
    }
    public String getUsername(){
        return  username;
    }
    public void  setAge(int age){
        this.age = age;
    }
    public void  setUsername(String username){
        this.username = username;
    }
    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }


}
