package com.epam.esm.service;


import com.epam.esm.repository.dto.CertificateDto;
import com.epam.esm.repository.entity.CertificatePatch;

import java.util.List;

public interface CertificateService {

    /**
     * Create certificate.
     *
     * @param certificate the certificate
     * @return the certificate
     */
    CertificateDto create(CertificateDto certificate);

    /**
     * Read all list.
     *
     * @return the list
     */
    List<CertificateDto> readAll();

    /**
     * Read certificate.
     *
     * @param id the id
     * @return the certificate
     */
    CertificateDto read(int id);

    /**
     * Update put certificate.
     *
     * @param certificate the certificate
     * @return the certificate
     */
    CertificateDto updatePut(CertificateDto certificate);





    /**
     * Delete certificate.
     *
     * @param id
     */
    void delete(int id);
}
