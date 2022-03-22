package com.example.springexample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class JpaTest {
    @Autowired
    private ItemRepository itemRepository;

    //test by manually adding elements
    @Test
    void createItemTest() {
        Item created = itemRepository.save(new Item("test", "testTEST"));

        assertTrue(created != null);
    }

    //test if proper data being saved
    @Test
    void whenCreatedIsThereTest() {
        itemRepository.save(new Item("test","testTEST"));
        assertThat(itemRepository.findByName("test")).isNotNull();
    }

    //test by populating database using SQL
    @Test
    @Sql("init_two_items.sql")
    void readingSqlTest() {
        assertEquals(2, itemRepository.count());
    }
}
