package com.example.lab2.model;

import com.example.lab2.entity.PositionEntity;
import com.example.lab2.service.OrderService;
import com.example.lab2.service.ProviderService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PositionModel {
    private int id;
    private Double weight;
    private int providerId;
    private int orderId;

    public static PositionModel positionToModel(PositionEntity position){
        PositionModel positionModel = new PositionModel();
        positionModel.setId(position.getId());
        positionModel.setWeight(position.getWeight());
        positionModel.setProviderId(position.getProvider().getId());
        positionModel.setOrderId(position.getOrder().getId());
        return positionModel;
    }
    public static PositionEntity modelToPosition(PositionModel model, ProviderService providerService, OrderService orderService){
        PositionEntity position = new PositionEntity();
        position.setId(model.getId());
        position.setWeight(model.getWeight());
        position.setProvider(providerService.getById(model.getProviderId()));
        position.setOrder(orderService.getById(model.getOrderId()));
        return position;
    }
}
