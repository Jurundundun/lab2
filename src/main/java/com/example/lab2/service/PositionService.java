package com.example.lab2.service;

import com.example.lab2.entity.PositionEntity;
import com.example.lab2.repo.PositionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionService implements EntityServiceInterface<PositionEntity>{
    private final PositionRepo positionRepo;
    @Override
    public Iterable<PositionEntity> getAll() {
        return positionRepo.findAll();
    }

    @Override
    public PositionEntity getById(Integer id) {

        try {
            return positionRepo.findById(id).get();
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Данная позиция не найдена не найден");
        }
    }

    @Override
    public String save(PositionEntity entity) {
        String response;
        if(positionRepo.existsById(entity.getId())){
            response = "позиция заказа с данным айди переписана на новую";
        }else {
            response = "позиция заказа сохранен";
        }
        positionRepo.save(entity);
        return response;
    }

    @Override
    public String deleteById(Integer id) {
        if(positionRepo.existsById(id)){
            positionRepo.deleteById(id);
            return "элемент удален";
        }else {
            return "элемент не найден";
        }
    }
}
