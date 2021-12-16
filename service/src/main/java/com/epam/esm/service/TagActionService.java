package com.epam.esm.service;


import com.epam.esm.core.dao.entity.TagAction;
import com.epam.esm.service.exception.ResourceValidationException;

public interface TagActionService {

    /**
     * Is applicable boolean.
     *
     * @param tagAction the tag action
     * @return the boolean
     */
    boolean isApplicable(TagAction tagAction);

    /**
     * Process action.
     *
     * @param tagAction the tag action
     * @throws ResourceValidationException
     */
    void processAction(TagAction tagAction) throws ResourceValidationException;
}
