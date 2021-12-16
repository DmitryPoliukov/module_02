package com.epam.esm.service;


import com.epam.esm.repository.entity.Tag;
import com.epam.esm.repository.entity.TagAction;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.exception.ResourceValidationException;

import java.util.List;

public interface TagService {

    /**
     * Create tag.
     *
     * @param tag the tag
     * @return the tag
     */
    Tag create(Tag tag);

    /**
     * Read tag.
     *
     * @param id the id
     * @return the tag
     */
    Tag read(int id) throws ResourceNotFoundException;

    /**
     * Read all list.
     *
     * @return the list
     */
    List<Tag> readAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(int id) throws ResourceValidationException;

    /**
     * Process tag action.
     *
     * @throws ResourceValidationException
     * @param action the action
     */
    void processTagAction(TagAction action) throws ResourceValidationException;
}