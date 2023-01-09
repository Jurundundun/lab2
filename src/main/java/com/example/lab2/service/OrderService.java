package com.example.lab2.service;

import com.example.lab2.entity.OrderEntity;
import com.example.lab2.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService implements EntityServiceInterface<OrderEntity>{
    private final OrderRepo orderRepo;
    @Override
    public Iterable<OrderEntity> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public OrderEntity getById(Integer id) {
        try {
            return orderRepo.findById(id).get();
        }catch (NoSuchElementException e){
            throw new RuntimeException("Данный заказ не найден ");
        }
    }

    @Override
    public String save(OrderEntity entity) {
        String response;
        if(orderRepo.existsById(entity.getId())){
            response = "заказ с данным айди переписан на нового";
        }else {
            response = "заказ сохранен";
        }
        orderRepo.save(entity);
        return response;

    }

    @Override
    public String deleteById(Integer id) {
        System.out.println(orderRepo.existsById(id));
        if(orderRepo.existsById(id)){
            orderRepo.deleteById(id);
            return "элемент удален";
        }else {
            return "элемент не найден";
        }
    }
    public List<OrderEntity> ordersByCondition(){
        return orderRepo.findAll().stream()
                .filter(order ->
                        order.getDate().getMonth().getValue() <= 6
                        && order.getWeightOfOrder() < 30)
                .toList();
    }
}
