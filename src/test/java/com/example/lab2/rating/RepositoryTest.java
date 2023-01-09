package com.example.lab2.rating;

import com.example.lab2.entity.ProviderEntity;
import com.example.lab2.repo.ProviderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    private ProviderRepo providerRepo;

    @BeforeEach
    public void cleanUp() {
        providerRepo.deleteAll();
    }

    @Test
    public void createAndRetrieveProvider(){
        ProviderEntity provider = new ProviderEntity(2,
                "address",
                "+7916891416",
                "productName");
        providerRepo.save(provider);

        List<ProviderEntity> providerEntitys = providerRepo.findAll();
        assertEquals(1, providerEntitys.size());
        assertEquals(2, providerEntitys.stream().findFirst().get().getId());
        assertEquals("+7916891416", providerEntitys.stream().findFirst().get().getPhoneNumber());
    }
}
