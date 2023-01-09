package com.example.lab2.service;

import com.example.lab2.entity.OrderEntity;
import com.example.lab2.entity.PositionEntity;
import com.example.lab2.entity.ProviderEntity;
import com.example.lab2.repo.OrderRepo;
import com.example.lab2.repo.PositionRepo;
import com.example.lab2.repo.ProviderRepo;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UploadService implements ExcelServiceInterface {
    private XSSFWorkbook myExcelBook;
    private final OrderRepo orderRepo;
    private final PositionRepo positionRepo;
    private final ProviderRepo providerRepo;
    private void parseOrdersFromExcel() {

        XSSFSheet sheet = myExcelBook.getSheet("Order");
        OrderEntity order;
        for (Row row : sheet) {
            order = new OrderEntity();

            try {
                order.setId((int)row.getCell(0).getNumericCellValue());
                order.setDate(row.getCell(1).getLocalDateTimeCellValue().toLocalDate());
                order.setStatus(row.getCell(2).getStringCellValue());
                orderRepo.save(order);
            }catch (NullPointerException e){
                System.out.println("Empty cell in the table");
                e.printStackTrace();
            }
        }
    }
    private void parsePositionsFromExcel() {

        XSSFSheet sheet = myExcelBook.getSheet("OrderPosition");
        PositionEntity orderPosition;
        for (Row row : sheet) {
            orderPosition = new PositionEntity();
            try {
                orderPosition.setId((int) row.getCell(0).getNumericCellValue());
                orderPosition.setWeight((int) row.getCell(1).getNumericCellValue());
                orderPosition.setProvider(providerRepo.findById((int)row.getCell(2).getNumericCellValue()).get());
                orderPosition.setOrder(orderRepo.findById((int)row.getCell(3).getNumericCellValue()).get());
                positionRepo.save(orderPosition);
            }catch (NullPointerException e){
                System.out.println("Empty cell in the table");
                e.printStackTrace();
            }
        }
    }
    private void parseProvidersFromExcel() {

        XSSFSheet sheet = myExcelBook.getSheet("Provider");
        ProviderEntity provider;
        for (Row row : sheet) {
            provider = new ProviderEntity();
            try {
                provider.setId((int)row.getCell(0).getNumericCellValue());
                provider.setAddress(row.getCell(1).getStringCellValue());
                provider.setPhoneNumber(row.getCell(2).getStringCellValue());
                provider.setProductName(row.getCell(3).getStringCellValue());
                providerRepo.save(provider);
            }catch (NullPointerException e){
                System.out.println("Empty cell in the table");
                e.printStackTrace();
            }

        }
    }
    @Override
    public String uploadFromExcel(MultipartFile file) {
        try {
            myExcelBook = new XSSFWorkbook(file.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
            return "Ошибка при чтении с файла";
        }
        parseProvidersFromExcel();
        parseOrdersFromExcel();
        parsePositionsFromExcel();
        return "OK";
    }
}
