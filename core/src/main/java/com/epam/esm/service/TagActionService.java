package com.epam.esm.service;

import com.epam.esm.entity.TagAction;
import com.epam.esm.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

@Service
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
