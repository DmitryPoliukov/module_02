package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
