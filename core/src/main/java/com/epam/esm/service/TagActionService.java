package com.epam.esm.service;

import com.epam.esm.entity.TagAction;
import com.epam.esm.exception.ResourceValidationException;

public interface TagActionService {

    boolean isApplicable(TagAction tagAction);

    void processAction(TagAction tagAction) throws ResourceValidationException;
}
