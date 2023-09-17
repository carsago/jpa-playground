package com.example.jpastudy;

import com.example.jpastudy.domain.Employee;
import com.example.jpastudy.domain.Product;
import com.example.jpastudy.domain.Store;
import com.example.jpastudy.persistence.ProductRepository;
import com.example.jpastudy.persistence.StoreRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/insert")
public class InsertController {

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    @GetMapping("/{count}")
    public ResponseEntity<Void> create(@PathVariable Integer count) {
        for (Integer i = 0; i < count; i++) {
            Store store = new Store("가게" + atomicInteger.getAndIncrement());
            store.addProducts(new Product("양념통닭" + atomicInteger.getAndIncrement(), 100));
            store.addProducts(new Product("고추바사삭" + atomicInteger.getAndIncrement(), 20));
            store.hireEmployee(new Employee("장병규" + atomicInteger.getAndIncrement()));
            store.hireEmployee(new Employee("오타니" + atomicInteger.getAndIncrement()));
            storeRepository.save(store);
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @GetMapping("/products")
    public ResponseEntity<Void> product() {
        List<Product> products = new ArrayList<>();
        List<Integer> addQuantities = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            products.add(productRepository.save(new Product("상품", 100)));
            addQuantities.add(i);
        }

        for (int i = 0; i < products.size(); i++) {
            products.get(i).addQuantity(addQuantities.get(i));
        }
        return ResponseEntity.ok().build();
    }


    static AtomicInteger atomicInteger = new AtomicInteger();
}
