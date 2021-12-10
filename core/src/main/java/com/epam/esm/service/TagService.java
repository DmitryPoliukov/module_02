package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.TagAction;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.exception.ResourceValidationException;

import java.util.List;

public interface TagService {
    Tag create(Tag tag);

    Tag read(int id) throws ResourceNotFoundException;

    List<Tag> readAll();

    void delete(int id) throws ResourceValidationException;

    void processTagAction(TagAction action) throws ResourceValidationException;
}
