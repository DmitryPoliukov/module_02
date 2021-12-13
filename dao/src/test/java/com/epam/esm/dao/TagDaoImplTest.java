package com.epam.esm.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class TagDaoImplTest {

    @Test
    void create() {
    }

    @Test
    void read() {
    }

    @Test
    void readAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void testRead() {
    }
}