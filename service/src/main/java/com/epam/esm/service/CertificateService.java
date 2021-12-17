package com.epam.esm.service;


import com.epam.esm.repository.entity.Certificate;
import com.epam.esm.repository.entity.CertificatePatch;
import com.epam.esm.repository.entity.CertificateRequestParameter;

import java.util.List;

public interface CertificateService {

    /**
     * Create certificate.
     *
     * @param certificate the certificate
     * @return the certificate
     */
    Certificate create(Certificate certificate);

    /**
     * Read all list.
     *
     * @param parameter the parameter
     * @return the list
     */
    List<Certificate> readAll(CertificateRequestParameter parameter);

    /**
     * Read certificate.
     *
     * @param id the id
     * @return the certificate
     */
    Certificate read(int id);

    /**
     * Update put certificate.
     *
     * @param certificate the certificate
     * @return the certificate
     */
    Certificate updatePut(Certificate certificate);

    /**
     * Update patch certificate.
     *
     * @param certificate
     * @return
     */
    CertificatePatch updatePatch(CertificatePatch certificate);

    /**
     * Delete certificate.
     *
     * @param id
     */
    void delete(int id);
}
