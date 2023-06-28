package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App
{


    public static void main( String[] args ) throws Exception {
//        String ExcelfilesDir = "./src/test/resources/";
//        File excels = new File(ExcelfilesDir);
//
//        File[] files ;
//        FilenameFilter filenameFilter= new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.startsWith("ExcelFile.xl");
//            }
//        };
//        files = excels.listFiles(filenameFilter);
//        if(files.length<1){
//            throw new Exception("File Missing");
//        }else {
//            for (File tempfile:files) {
//                if(tempfile.exists()){
//                    excels = new File(tempfile.getAbsolutePath());
//                }
//
//            }
//        }
//        XSSFHandler excelpoi  = new XSSFHandler(excels.getAbsoluteFile().toString(),"ExcelFile");

        String path = "./src/test/resources/ExcelFile.xlsx";
        FileInputStream file = new FileInputStream(new File(path));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = null;

        String sheetName ="Sheet1";
        try {
            sheet = workbook.getSheet(sheetName);
            if(sheet== null){
                throw new Exception("Sheet Doesn't Exist");
            }
            ArrayList<String> columnName = new ArrayList<String>();
            int maxSize=0;

            LinkedHashMap<String, ArrayList<String>> rowColumnMap = new  LinkedHashMap<String,ArrayList<String>>();

            for (Cell tempRow :   sheet.getRow(0)) {
                    columnName.add(tempRow.toString());
                if(maxSize<tempRow.toString().length()){
                    maxSize=tempRow.toString().length();
                }

            }



                for(int j=0;j<columnName.size();j++){
                    ArrayList<String> col = new ArrayList<String>();
                    for(int i=0;i <sheet.getLastRowNum();i++){

                    if(sheet.getRow(i+1)!=null){
                        Cell data = sheet.getRow(i+1).getCell(j);
                        if(data!=null){
                            col.add(data.toString());
                            if(maxSize<data.toString().length()){
                                maxSize=data.toString().length();
                            }

                            rowColumnMap.put(columnName.get(j),col);
                        }else {
                            col.add(null);

                            rowColumnMap.put(columnName.get(j),col);
                        }
                    }else {
                        col.add(null);

                        rowColumnMap.put(columnName.get(j),col);
                    }
                }
            }

            //printData int rowNum

                int rowNum =1;
                String output="";

            for (String key:rowColumnMap.keySet()) {
                if(key.length() != maxSize){

                    int diff = maxSize-key.length();
                    System.out.print(key +String.format("%0" + diff + "d",0).replace("0", " "));
                }else {
                    System.out.print(key);
                }
            }
                System.out.println();
            for (String key:rowColumnMap.keySet()) {

                if(rowColumnMap.get(key).size() != maxSize){
                    int diff = maxSize-rowColumnMap.get(key).get(0).length();
                    System.out.print(rowColumnMap.get(key).get(rowNum-1) +String.format("%0" + diff + "d",0).replace("0", " "));
                }else {
                    System.out.print(rowColumnMap.get(key).get(rowNum-1) );
                }
            }
            System.out.println();
        }catch (Exception e){
            throw e;
        }
    }
}
