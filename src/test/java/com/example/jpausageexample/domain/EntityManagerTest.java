package com.example.jpausageexample.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EntityManagerTest {
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void persistTestWithEntityManager() {
        // given
        Product product = new Product("Apple", BigDecimal.valueOf(1_000));
        product = entityManager.persist(product);

        // when
        Product savedProduct = entityManager.find(Product.class, product.getId());

        // then
        assertThat(savedProduct).isEqualTo(product);
    }
}
