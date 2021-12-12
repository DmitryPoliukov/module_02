package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificatePatch;
import com.epam.esm.entity.CertificateRequestParameter;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.exception.ResourceValidationException;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/certificates")

public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> readCertificate(@PathVariable int id) {
        Certificate certificate = null;
        try {
            certificate = certificateService.read(id);
        } catch (ResourceNotFoundException e) {
            //log
        }
        return ResponseEntity.status(HttpStatus.OK).body(certificate);
    }

    @GetMapping
    public List<Certificate> readCertificates(CertificateRequestParameter parameter) {
        return certificateService.readAll(parameter);
    }

    @PostMapping
    public ResponseEntity<Certificate> createCertificate(
            @Valid @RequestBody Certificate certificate) {

        Certificate createdCertificate = certificateService.create(certificate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCertificate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> updateCertificatePut(
            @PathVariable int id, @Valid @RequestBody Certificate certificate) throws ResourceValidationException {
        certificate.setId(id);
        Certificate updatedCertificate = certificateService.updatePut(certificate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCertificate);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CertificatePatch> updateCertificatePatch(
            @PathVariable int id, @Valid @RequestBody CertificatePatch certificate) throws ResourceValidationException {
        certificate.setId(id);
        CertificatePatch updatedCertificate = certificateService.updatePatch(certificate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCertificate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCertificate(@PathVariable int id) throws ResourceValidationException {
        certificateService.delete(id);
    }
}
