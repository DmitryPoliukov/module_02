package com.epam.esm.service.impl;


import com.epam.esm.repository.dao.CertificateDao;
import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.entity.Certificate;
import com.epam.esm.repository.entity.CertificatePatch;
import com.epam.esm.repository.entity.CertificateRequestParameter;
import com.epam.esm.repository.entity.Tag;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.exception.ResourceValidationException;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    public static final int ONE_UPDATED_ROW = 1;
    private final CertificateDao certificateDao;
    private final TagDao tagDao;

    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao, TagDao tagDao) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
    }

    @Override
    public Certificate create(Certificate certificate) {
        LocalDateTime timeNow = LocalDateTime.now();
        certificate.setCreateDate(timeNow);
        certificate.setLastUpdateDate(timeNow);
        Certificate createdCertificate = certificateDao.createCertificate(certificate);
        createdCertificate.setTags(certificate.getTags());
        addTagsToDb(createdCertificate);
        return createdCertificate;
    }


    @Override
    public List<Certificate> readAll(CertificateRequestParameter parameter) {
        List<Certificate> certificates = certificateDao.readAll(parameter);
        for (Certificate certificate : certificates) {
            certificate.setTags(certificateDao.readCertificateTags(certificate.getId()));
        }
        return certificates;
    }

    @Override
    public Certificate read(int id) {
        Optional<Certificate> certificate = certificateDao.read(id);
        certificate.ifPresent(
                actualCertificate -> actualCertificate.setTags(certificateDao.readCertificateTags(id)));
        return certificate.orElseThrow(ResourceNotFoundException.notFoundWithCertificateId(id));
    }

    @Override
    public Certificate updatePut(Certificate certificate) {
        LocalDateTime timeNow = LocalDateTime.now();
        certificate.setCreateDate(timeNow);
        certificate.setLastUpdateDate(timeNow);
        int numberOfUpdatedRows = certificateDao.update(certificate);
        if (numberOfUpdatedRows != ONE_UPDATED_ROW) {
            throw ResourceValidationException.validationWithCertificateId(certificate.getId()).get();
        }
        certificateDao.deleteBondingTagsByCertificateId(certificate.getId());
        addTagsToDb(certificate);
        return certificate;
    }

    @Override
    public CertificatePatch updatePatch(CertificatePatch certificate) {
        LocalDateTime timeNow = LocalDateTime.now();
        certificate.setLastUpdateDate(timeNow);
        int numberOfUpdatedRows = certificateDao.updatePatch(certificate);
        if (numberOfUpdatedRows != ONE_UPDATED_ROW) {
            throw ResourceValidationException.validationWithCertificateId(certificate.getId()).get();
        }
        return certificate;
    }

    @Override
    public void delete(int id){
        certificateDao.deleteBondingTagsByCertificateId(id);
        int numberOfUpdatedRows = certificateDao.deleteCertificate(id);
        if (numberOfUpdatedRows != ONE_UPDATED_ROW) {
            throw ResourceValidationException.validationWithCertificateId(id).get();
        }
    }


    void addTagsToDb(Certificate certificate) {
        List<Tag> tags = certificate.getTags();
        if (tags != null) {
            for (Tag tag : tags) {
                Optional<Tag> existedTag = tagDao.read(tag.getName());
                int tagId = existedTag.map(Tag::getId).orElseGet(() -> tagDao.create(tag).getId());
                certificateDao.addTag(tagId, certificate.getId());
            }
        }
    }
}
