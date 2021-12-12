package com.epam.esm.service;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificatePatch;
import com.epam.esm.entity.CertificateRequestParameter;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.exception.ResourceValidationException;
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
    public Certificate read(int id) throws ResourceNotFoundException {
        Optional<Certificate> certificate = certificateDao.read(id);
        certificate.ifPresent(
                actualCertificate -> actualCertificate.setTags(certificateDao.readCertificateTags(id)));
        return certificate.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Certificate updatePut(Certificate certificate) throws ResourceValidationException {
        LocalDateTime timeNow = LocalDateTime.now();
        certificate.setCreateDate(timeNow);
        certificate.setLastUpdateDate(timeNow);
        int numberOfUpdatedRows = certificateDao.update(certificate);
        if (numberOfUpdatedRows != ONE_UPDATED_ROW) {
            throw new ResourceValidationException();
        }
        certificateDao.deleteBondingTagsByCertificateId(certificate.getId());
        addTagsToDb(certificate);
        return certificate;
    }

    @Override
    public CertificatePatch updatePatch(CertificatePatch certificate) throws ResourceValidationException {
        LocalDateTime timeNow = LocalDateTime.now();
        certificate.setLastUpdateDate(timeNow);
        int numberOfUpdatedRows = certificateDao.updatePatch(certificate);
        if (numberOfUpdatedRows != ONE_UPDATED_ROW) {
            throw new ResourceValidationException();
        }
        return certificate;
    }

    @Override
    public void delete(int id) throws ResourceValidationException {
        certificateDao.deleteBondingTagsByCertificateId(id);
        int numberOfUpdatedRows = certificateDao.deleteCertificate(id);
        if (numberOfUpdatedRows != ONE_UPDATED_ROW) {
            throw new ResourceValidationException();
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
