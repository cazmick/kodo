package org.example;

public class Cheke {
    public App app = new App();

    public static void main(String[] args){
        String query = "select Demo from Sheet1 where column = Hello";

        String[] aplitter  = query.split(" ");
        if(aplitter[1].equals("*")){
            System.out.println("Print All");

        }



    }
}
