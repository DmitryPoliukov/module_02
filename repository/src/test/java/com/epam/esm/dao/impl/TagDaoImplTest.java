package com.epam.esm.dao.impl;

import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class TagDaoImplTest {

    public static final int ID_FOR_3_TAG = 3;
    public static final int NOT_EXISTED_TAG_ID = 99999;

    @Autowired
    TagDao tagDao;


    @Test
    void create() {

        Tag expectedTag = givenNewTagWithoutId();

        Tag actualTag = tagDao.create(expectedTag);

        expectedTag.setId(ID_FOR_3_TAG);
        assertEquals(expectedTag, actualTag);
    }
/*
    @ParameterizedTest
    @MethodSource("readDataProvider")
    void read(int actualId, Optional<Tag> expectedTag) {
        Optional<Tag> actualTag = tagDao.read(actualId);
        assertEquals(expectedTag, actualTag);
    }

    static Stream<Arguments> readDataProvider() {
        return Stream.of(
                arguments(givenExistingTag1().getId(), Optional.of(givenExistingTag1())),
                arguments(NOT_EXISTED_TAG_ID, Optional.empty()));
    }

 */


/*
    @Test
    void readAll() {

        Tag tag2 = tagDao.read(2).get();
        Tag tag3 = tagDao.read(3).get();
        Tag tag4 = tagDao.read(4).get();
        List<Tag> expectedList = List.of(tag2, tag3, tag4);

        List<Tag> actualList = tagDao.readAll();
        assertEquals(expectedList, actualList);
    }

 */



    @ParameterizedTest
    @MethodSource("readTagByNameDataProvider")
    void readTest(Tag wantedTag, Optional<Tag> expectedTag) {
        Optional<Tag> actualTag = tagDao.read(wantedTag.getName());
        assertEquals(expectedTag, actualTag);
    }

    Stream<Arguments> readTagByNameDataProvider() {
        Tag tag1 = tagDao.read(1).get();
        Tag tag2 = tagDao.read(2).get();
        return Stream.of(
                arguments(tag1, Optional.of(tag1)),
                arguments(givenNewTagWithoutId(), Optional.empty()));
    }


/*
    @Test
    void delete() {
        Tag tag1 = tagDao.read(1).get();

        tagDao.delete(tag1.getId());

        assertTrue(tagDao.read(tag1.getId()).isEmpty());
        tagDao.create(tag1);
    }

 */

    private static Tag givenNewTagWithoutId() {
        return new Tag("third tag");
    }


}