package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class XSSFHandler {
    XSSFWorkbook workbook ;
    XSSFSheet sheet;
    FileInputStream file;

    ArrayList<String> columnName = new ArrayList<String>();

    HashMap<String, ArrayList<String>> rowColumnMap = new  HashMap<String,ArrayList<String>>();


    XSSFHandler(String filepath, String sheet) throws Exception {

        this.file = new FileInputStream(new File(filepath));
        this.workbook = new XSSFWorkbook(file);
        this.sheet=workbook.getSheet(sheet);
        if(sheet== null){
            throw new Exception("Sheet Doesn't Exist");
        }

    }
    public void columnNameResder(){

        for (Cell tempRow :   sheet.getRow(0)) {
            columnName.add(tempRow.toString());

        }
    }

    public void fetchData() throws Exception {
        try {

            columnNameResder();

            for(int i=0;i <sheet.getLastRowNum();i++){
                ArrayList<String> col = new ArrayList<String>();
                for(int j=1;j<columnName.size();j++){
                    if(sheet.getRow(j)!=null){
                        Cell data = sheet.getRow(j).getCell(i);
                        if(data!=null){
                            col.add(data.toString());
                            rowColumnMap.put(columnName.get(i),col);
                        }
                    }
                }
            }






        }catch (Exception e){
            throw e;
        }
    }







}
