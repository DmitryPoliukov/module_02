package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface CertificateDao {

    Certificate createCertificate(Certificate certificate);

    Optional<Certificate> read(int certificateId);

    List<Certificate> readAll();

    int update(Certificate certificate);

    List<Certificate> readByTag(String tagName);

    List<Tag> readCertificateTags(int certificateId);

    void addTag(int tagId, int certificateId);

    int removeTag(int tagId, int certificateId);

    int deleteCertificate(int certificateId);

    int deleteBondingTagsByTagId(int tagId);

    int deleteBondingTagsByCertificateId(int certificateId);

    //int updatePatch(CertificatePatch certificate)










}
