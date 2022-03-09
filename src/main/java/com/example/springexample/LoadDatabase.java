package com.example.springexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository itemRepository) {
        return args -> {
            itemRepository.save(new Item("Ifrst", "being first"));
            itemRepository.save(new Item("First", "being actually first"));

            //generate up to 9 random records in database
            int limit = new Random().nextInt(10);
            for (int i = 0; i < limit; i++) {
                itemRepository.save(new Item(generateString(3, 7), generateString(10, 20)));
            }

            itemRepository.findAll().forEach(item -> log.info("Preloaded " + item));
        };
    }

    private String generateString(int min, int max) {
        return new Random().ints(97, 123)
                .limit(ThreadLocalRandom.current().nextLong(min, max))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
