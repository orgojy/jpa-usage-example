package com.example.jpausageexample.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

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
}
