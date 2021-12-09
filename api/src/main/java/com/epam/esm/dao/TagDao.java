package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao {

    Tag create(Tag tag);

    Optional<Tag> read(int id);

    List<Tag> readAll();

    int delete(int id);

    Optional<Tag> read(String name);




}
