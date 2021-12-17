package com.epam.esm.service.impl;


import com.epam.esm.repository.dao.CertificateDao;
import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.entity.Tag;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.ResourceValidationException;
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

    @Autowired
    public TagServiceImpl(TagDao tagDao, CertificateDao certificateDao) {
        this.tagDao = tagDao;
        this.certificateDao = certificateDao;
    }




    @Override
    public Tag create(Tag inputTag) {
        Optional<Tag> existingTag = tagDao.read(inputTag.getName());
        return existingTag.orElseGet(() -> tagDao.create(inputTag));
    }

    @Override
    public Tag read(int id) {
        Optional<Tag> tag = tagDao.read(id);

        return tag.orElseThrow(ResourceNotFoundException.notFoundWithTagId(id));
    }

    @Override
    public List<Tag> readAll() {
        return tagDao.readAll();
    }

    @Transactional
    @Override
    public void delete(int id) {
        certificateDao.deleteBondingTagsByTagId(id);
        int numberOfUpdatedRows = tagDao.delete(id);
        if (numberOfUpdatedRows != ONE_UPDATED_ROW) {
            throw ResourceValidationException.validationWithTagId(id).get();
        }

    }

}
