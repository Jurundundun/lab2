package com.example.lab2.service;

import com.example.lab2.entity.ProviderEntity;
import com.example.lab2.repo.ProviderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderService implements EntityServiceInterface<ProviderEntity>{
    private final ProviderRepo providerRepo;
    @Override
    public Iterable<ProviderEntity> getAll() {

        return providerRepo.findAll();
    }

    @Override
    public ProviderEntity getById(Integer id) {
        try {
            return providerRepo.findById(id).get();
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Данный поставщик не найден");
        }
    }

    @Override
    public String save(ProviderEntity entity) {
        String response;
        if(providerRepo.existsById(entity.getId())){
            response = "поставщик с данным айди переписан на нового";
        }else {
            response = "поставщик сохранен";
        }
        providerRepo.save(entity);
        return response;
    }

    @Override
    public String deleteById(Integer id) {
        if(providerRepo.existsById(id)){
            providerRepo.deleteById(id);
            return "элемент удален";
        }else {
            return "элемент не найден";
        }
    }
}
