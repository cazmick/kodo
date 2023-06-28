package org.example;

import java.util.Arrays;
import java.util.List;

public class DataFetcher {
    String query;
    DataFetcher(String query){
        this.query = query.toLowerCase();
    }
    public String ActionType(){
        boolean support=false;
        if(query.startsWith("use") ){
            support=true;
            return "schemaSelector";

        }
        else if(query.startsWith("Select") ){
            support=true;
            return "select";
        }else if(query.startsWith("Insert") ){
            support=true;
            return "insert";
        }else if(query.startsWith("Update") ){
            support=true;
            return "insert";
        }
        else if(query.startsWith("Delete") ){
            support=true;
            return "insert";
        }else{
            support=false;
            return "UnsupportActionType";
        }


    }

    public String colums(){
        //Select * from worksheetname
        String subs="";
        if(this.ActionType().equals("select") ){
            subs = query.substring(query.lastIndexOf("select "),query.indexOf("from"));
            //Select // * //from //worksheetname
            if(subs.equals("*")){
                return "all";
            }
        }else{
            return subs;
        }
        return subs;
    }

}
