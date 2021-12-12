package com.epam.esm.service;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificateRequestParameter;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
     * @param certificate the certificate
     * @return the certificate
     */
    //CertificatePatch updatePatch(CertificatePatch certificate);

    /**
     * Delete.
     *
     * @param id the id
     * @throws ResourceValidationException
     */
    void delete(int id) throws ResourceValidationException;
}
