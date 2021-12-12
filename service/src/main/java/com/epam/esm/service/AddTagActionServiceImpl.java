package com.epam.esm.service;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.TagAction;
import com.epam.esm.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class AddTagActionServiceImpl implements TagActionService {

    private final TagDao tagDao;
    private final CertificateDao certificateDao;

    @Autowired
    public AddTagActionServiceImpl(TagDao tagDao, CertificateDao certificateDao) {
        this.tagDao = tagDao;
        this.certificateDao = certificateDao;
    }

    @Override
    public boolean isApplicable(TagAction tagAction) {
        return tagAction.getType().equals(TagAction.ActionType.ADD);
    }

    @Override
    public void processAction(TagAction tagAction) throws ResourceValidationException {
        int tagId = tagAction.getTagId();
        int certificateId = tagAction.getCertificateId();
        if (tagDao.read(tagId).isEmpty()) {
            throw new ResourceValidationException();
        }
        if (certificateDao.read(certificateId).isEmpty()) {
            throw new ResourceValidationException();
        }
        certificateDao.addTag(tagId, certificateId);
    }
}
