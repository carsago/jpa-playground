package com.example.jpastudy.support;

import com.example.jpastudy.domain.Employee;
import com.example.jpastudy.domain.Product;
import com.example.jpastudy.domain.Store;
import com.example.jpastudy.persistence.StoreRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public void init(Integer tryCount) {
        List<Store> stores = new ArrayList<>();
        for (Integer i = 0; i < tryCount; i++) {
            Store store = new Store("a" + i);
            store.addProducts(new Product("양념통닭" + i, 100));
            store.addProducts(new Product("고추바사삭" + i, 100));
            store.hireEmployee(new Employee("장병규" + i));
            store.hireEmployee(new Employee("오타니" + i));
            stores.add(store);
        }
        storeRepository.saveAll(stores);
    }
}
