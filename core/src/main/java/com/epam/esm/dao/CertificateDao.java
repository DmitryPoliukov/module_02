package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificatePatch;
import com.epam.esm.entity.CertificateRequestParameter;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateDao {

    /**
     * Create certificate.
     *
     * @param certificate the certificate
     * @return the certificate
     */
    Certificate createCertificate(Certificate certificate);

    /**
     * Read optional.
     *
     * @param certificateId the certificateId
     * @return the optional
     */
    Optional<Certificate> read(int certificateId);

    /**
     * Read all list.
     *
     * @param parameter the requests parameters for certificate
     * @return the list
     */
    List<Certificate> readAll(CertificateRequestParameter parameter);

    /**
     * Update int.
     *
     * @param certificate the certificate
     * @return the int
     */
    int update(Certificate certificate);

    /**
     * Read certificate tags list.
     *
     * @param certificateId the certificate id
     * @return the list
     */
    List<Tag> readCertificateTags(int certificateId);

    /**
     * Add tag.
     *
     * @param tagId the tag id
     * @param certificateId the certificate id
     */
    void addTag(int tagId, int certificateId);

    /**
     * Remove tag int.
     *
     * @param tagId the tag id
     * @param certificateId the certificate id
     * @return the int
     */
    int removeTag(int tagId, int certificateId);

    /**
     * Delete certificate by certificate id.
     *
     * @param certificateId the certificate id
     * @return the int
     */
    int deleteCertificate(int certificateId);

    /**
     * Delete certificate tags by tag id.
     *
     * @param tagId the tag id
     */
    int deleteBondingTagsByTagId(int tagId);

    /**
     * Delete certificate tags by certificate id.
     *
     * @param certificateId the certificate id
     */
    int deleteBondingTagsByCertificateId(int certificateId);

    /**
     * Update patch int.
     *
     * @param certificate the certificate
     * @return the int
     */
    int updatePatch(CertificatePatch certificate);










}
