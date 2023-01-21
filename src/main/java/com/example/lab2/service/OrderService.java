package com.example.lab2.service;

import com.example.lab2.entity.OrderEntity;
import com.example.lab2.repo.OrderRepo;
import com.example.lab2.repo.PositionRepo;
import lombok.RequiredArgsConstructor;
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
    public String delete(OrderEntity entity) {
        if(orderRepo.existsById(entity.getId())){
            orderRepo.delete(entity);
            return "элемент удален";
        }else {
            return "элемент не найден";
        }
    }
    @Override
    public String deleteById(Integer id) {
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
