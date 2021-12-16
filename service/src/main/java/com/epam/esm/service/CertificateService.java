package com.epam.esm.service;


import com.epam.esm.repository.entity.Certificate;
import com.epam.esm.repository.entity.CertificatePatch;
import com.epam.esm.repository.entity.CertificateRequestParameter;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.exception.ResourceValidationException;

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
     * @throws ResourceNotFoundException
     * @return the certificate
     */
    Certificate read(int id) throws ResourceNotFoundException;

    /**
     * Update put certificate.
     *
     * @param certificate the certificate
     * @throws ResourceValidationException
     * @return the certificate
     */
    Certificate updatePut(Certificate certificate) throws ResourceValidationException;

    /**
     * Update patch certificate.
     *
     * @param certificate
     * @return
     * @throws ResourceValidationException
     */
    CertificatePatch updatePatch(CertificatePatch certificate) throws ResourceValidationException;

    /**
     * Delete certificate.
     *
     * @param id
     * @throws ResourceValidationException
     */
    void delete(int id) throws ResourceValidationException;
}
