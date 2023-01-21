package com.example.lab2.service;

import com.example.lab2.entity.PositionEntity;
import com.example.lab2.model.PositionModel;
import com.example.lab2.repo.PositionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PositionService implements EntityServiceInterface<PositionModel>{
    private final PositionRepo positionRepo;
    @Override
    public Iterable<PositionModel> getAll() {;
        return positionRepo.findAll().stream().map(PositionModel::positionToModel).toList();
    }

    @Override
    public PositionModel getById(Integer id) {

        try {
            return PositionModel.positionToModel(positionRepo.findById(id).get());
        }catch (EmptyResultDataAccessException | NoSuchElementException e){
            throw new RuntimeException("Данная позиция не найдена не найден");
        }
    }


    public String saveEntity(PositionEntity entity) {
        String response;
        if(positionRepo.existsById(entity.getId())){
            response = "позиция заказа с данным айди переписана на новую";
        }else {
            response = "позиция заказа сохранен";
        }
        positionRepo.save(entity);
        return response;
    }
    public String deleteEntity(PositionEntity entity) {
        if(positionRepo.existsById(entity.getId())){
            positionRepo.delete(entity);
            return "элемент удален";
        }else {
            return "элемент не найден";
        }
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

    //Неиспользуемые методы

    @Override
    public String save(PositionModel entity) {
        return null;
    }

    @Override
    public String delete(PositionModel entity) {
        return null;
    }
}
