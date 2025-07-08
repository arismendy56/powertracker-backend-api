package com.arismendy.powertracker.util;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Objects;

public class SpreadsheetHelper {

    public static void dataReader(){
        String filePath = Objects.requireNonNull(SpreadsheetHelper.class.getClassLoader().getResource("ArisTo7.19.25.xlsx")).getFile();

        try{
            readSpreadsheet(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error reading spreadsheet: " + e.getMessage());
        }
    }

    private static void readSpreadsheet(String filePath) throws FileNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);

            // ...
            Sheet sheet = workbook.getSheetAt(0); // By name
            // Or: Sheet sheet = workbook.getSheetAt(0); // By index

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    // Process the cell based on its type
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue() + "\t");
                            break;
                        case STRING:
                            System.out.println(cell.getStringCellValue() + "\t");
                            break;
                        // Add cases for other cell types like BOOLEAN, FORMULA, etc.
                        default:
                            System.out.println("\t");
                    }
                }
                System.out.println(); // New line for each row
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
