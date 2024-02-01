package com.sample.files;

public class Details {
    private static String username;
    private static String pass;
    private static String email;

    public Details(String name, String password, String emailid){
        username=name;
        System.out.println("username:"+name);
        pass=password;
        System.out.println("pass:"+password);
        email=emailid;
        System.out.println("email:"+emailid);
    }

    public Details(){
    }

    public static String getUserName(){
        System.out.println("username2:"+username);
        return username;
    }
    public static String getPass(){
        return pass;
    }
    public static String getEmail(){
        return email;
    }

    public void userCredentials(){
        System.out.println("userCredentials");
        Details details=new Details("michael","Michael@","michaelreethi@gmail.com");
    }

}
