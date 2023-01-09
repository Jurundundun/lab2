package com.example.lab2.rating;

import com.example.lab2.entity.OrderEntity;
import com.example.lab2.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    private OrderRepo orderRepo;

    @BeforeEach
    public void cleanUp() {
        orderRepo.deleteAll();
    }

    @Test
    public void createAndRetrieveOrder(){
        OrderEntity order = new OrderEntity(2, new Date(2003,Calendar.MAY,13), "доставлен",new ArrayList<>());
        orderRepo.save(order);

        List<OrderEntity> listOfOrders = orderRepo.findAll();
        assertEquals(1, listOfOrders.size());
        assertEquals(2, listOfOrders.stream().findFirst().get().getId());
        assertEquals(new Date(2003,Calendar.MAY,13), listOfOrders.stream().findFirst().get().getDate() );
    }
}
