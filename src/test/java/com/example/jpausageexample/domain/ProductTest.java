package com.example.jpausageexample.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTestWithoutTransaction() {
        // given
        String name = "Apple";
        BigDecimal price = BigDecimal.valueOf(1_000);
        // when
        Product product = new Product(name, price);
        // then
        assertThat(product).isNotNull();
        assertThat(product.getId()).isNull();
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getCreatedAt()).isNotNull();

        // when
        product = productRepository.save(product);
        // then
        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getCreatedAt()).isNotNull();
    }

    @Test
    void deleteEntity() {
        // given
        Product product1 = new Product("Apple", BigDecimal.valueOf(1_000));
        Product product2 = new Product("Banana", BigDecimal.valueOf(2_000));
        productRepository.saveAll(Arrays.asList(product1, product2));

        // when
        productRepository.delete(product1);
        List<Product> findProducts = productRepository.findAll();

        // then
        assertThat(findProducts).hasSize(1);
        assertThat(findProducts.get(0)).isEqualTo(product2);
    }
}
