package com.epam.esm.service.impl;


import com.epam.esm.repository.dao.CertificateDao;
import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.entity.Tag;
import com.epam.esm.repository.entity.TagAction;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.exception.ResourceValidationException;
import com.epam.esm.service.TagActionService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    public static final int ONE_UPDATED_ROW = 1;

    private final TagDao tagDao;
    private final CertificateDao certificateDao;
    private final List<TagActionService> tagActionServices;

    @Autowired
    public TagServiceImpl(TagDao tagDao, CertificateDao certificateDao, List<TagActionService> tagActionServices) {
        this.tagDao = tagDao;
        this.certificateDao = certificateDao;
        this.tagActionServices = tagActionServices;
    }

    @Override
    public Tag create(Tag inputTag) {
        Optional<Tag> existingTag = tagDao.read(inputTag.getName());
        return existingTag.orElseGet(() -> tagDao.create(inputTag));
    }

    @Override
    public Tag read(int id) throws ResourceNotFoundException {
        Optional<Tag> tag = tagDao.read(id);

        return tag.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Tag> readAll() {
        return tagDao.readAll();
    }

    @Transactional
    @Override
    public void delete(int id) throws ResourceValidationException {
        certificateDao.deleteBondingTagsByTagId(id);
        int numberOfUpdatedRows = tagDao.delete(id);
        if (numberOfUpdatedRows != ONE_UPDATED_ROW) {
            throw new ResourceValidationException();
        }

    }


    @Override
    public void processTagAction(TagAction action) throws ResourceValidationException {
        tagActionServices.stream()
                .filter(service -> service.isApplicable(action))
                .findFirst()
                .orElseThrow(
                        () ->
                                new IllegalArgumentException("There is no valid service to deal with given action"))
                .processAction(action);

    }
}
