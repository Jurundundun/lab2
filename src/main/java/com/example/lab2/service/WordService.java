package com.example.lab2.service;

import com.example.lab2.entity.OrderEntity;
import com.example.lab2.repo.OrderRepo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WordService implements WordServiceInterface{
    private XWPFDocument document;
    private final OrderRepo orderRepo;
    private void createTitle() {
        XWPFParagraph par = document.createParagraph();
        par.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = par.createRun();
        run.setText("Заказы в первой половине года и массой не превышающие 30кг");
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setFontSize(20);
    }
    private void createHeaderCell(XWPFTableRow row, int pos, String text) {
        XWPFParagraph par;
        XWPFRun run;
        XWPFTableCell cell;
        cell = row.getCell(pos);
        par = cell.addParagraph();
        par.setAlignment(ParagraphAlignment.CENTER);
        par.setVerticalAlignment(TextAlignment.BOTTOM);
        run = par.createRun();
        run.setText(text);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setFontSize(14);
    }
    private void createTable() {
        createTitle();
        XWPFTable table = document.createTable(1,4);
        table.setWidth("100%");
        XWPFTableRow tableRow = table.getRow(0);
        createHeaderCell(tableRow, 0, "id");
        createHeaderCell(tableRow, 1, "data");
        createHeaderCell(tableRow, 2, "status");
        createHeaderCell(tableRow, 3, "weight");
        for(OrderEntity order: orderRepo.findAll()) {
            if (order.getWeightOfOrder() < 30 && order.getDate().getMonth().getValue() <= 6 ) {
                tableRow = table.createRow();
                tableRow.getCell(0).setText(String.valueOf(order.getId()));
                tableRow.getCell(1).setText(String.valueOf(order.getDate().getYear()));
                tableRow.getCell(2).setText(order.getStatus());
                tableRow.getCell(3).setText(String.valueOf(order.getWeightOfOrder()));
            }
        }
        table.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "000000");
        table.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "000000");
        table.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "000000");
        table.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "000000");
        table.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "000000");
        table.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "000000");
    }
    @Override
    public String downloadToWord(HttpServletResponse response)  {
        document = new XWPFDocument();
        createTable();
        File report = new File("tables.docx");
        try (FileOutputStream file = new FileOutputStream(report)) {
            document.write(file);
            document.close();
            return "OK";
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при записи файла на диск!";
        }
    }
}
