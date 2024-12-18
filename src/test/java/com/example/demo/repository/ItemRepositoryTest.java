package com.example.demo.repository;

import com.example.demo.config.TestConfig;
import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

/**
 * item 의 status 가 지정되지 않아 null 일때,
 * 저장이되는지 검사, 이때 주어진 기본값은 "PENDING"입니다.
 */
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import(TestConfig.class)
class ItemRepositoryTest{

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("아이템 상태 NOT NULL 확인")
    @Transactional
    void itemStatusIsNull(){
        // Given
        User owner = new User("user", "owner@a.com", "owner", "0000");
        User manager = new User("user", "manager@a.com", "manager", "0000");
        owner = userRepository.save(owner);
        manager = userRepository.save(manager);
        Item item = new Item("testItem", "item description", manager, owner);

        // When
        Item newItem = itemRepository.saveAndFlush(item);
        // Then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            itemRepository.updateStatusNull(newItem.getId());
        });
    }
}
