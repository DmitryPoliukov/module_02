package com.epam.esm.service;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.exception.ResourceValidationException;

import java.util.List;

public interface CertificateService {
    Certificate create(Certificate certificate);


    //List<Certificate> readAll(CertificatesRequest parameter);


    Certificate read(int id) throws ResourceNotFoundException;

    Certificate updatePut(Certificate certificate) throws ResourceValidationException;

    //CertificatePatch updatePatch(CertificatePatch certificate);


    void delete(int id) throws ResourceValidationException;
}
