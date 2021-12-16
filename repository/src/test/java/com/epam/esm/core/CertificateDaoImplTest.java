package com.epam.esm.core;

import com.epam.esm.repository.dao.CertificateDao;
import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.entity.Certificate;
import com.epam.esm.repository.entity.CertificatePatch;
import com.epam.esm.repository.entity.CertificateRequestParameter;
import com.epam.esm.repository.entity.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
@WebAppConfiguration
class CertificateDaoImplTest {

    public static final int ID_FOR_3_CERTIFICATE = 3;
    public static final int NOT_EXISTED_CERTIFICATE_ID = 99999;

    @Autowired
    CertificateDao certificateDao;
    @Autowired
    TagDao tagDao;

    @BeforeEach
    void setUp() {
        Certificate certificate1 = givenExistingCertificate1();
        Certificate certificate2 = givenExistingCertificate2();
        Tag tag1 = givenExistingTag1();
        Tag tag2 = givenExistingTag2();
        certificateDao.createCertificate(certificate1);
        certificateDao.createCertificate(certificate2);
        tagDao.create(tag1);
        tagDao.create(tag2);
        certificateDao.addTag(tag1.getId(), certificate2.getId());
        certificateDao.addTag(tag2.getId(), certificate2.getId());
    }

    @Test
    void createCertificate() {
        Certificate expectedCertificate = givenNewCertificateWithoutId();

        Certificate actualCertificate = certificateDao.createCertificate(expectedCertificate);

        expectedCertificate.setId(ID_FOR_3_CERTIFICATE);
        assertEquals(expectedCertificate, actualCertificate);
    }

    @Test
    void read() {
    }

    @Test
    void readAll() {
        Certificate certificate1 = givenExistingCertificate1();
        Certificate certificate2 = givenExistingCertificate2();
        List<Certificate> expectedList = List.of(certificate1, certificate2);

        List<Certificate> actualList = certificateDao.readAll(new CertificateRequestParameter());
        assertEquals(expectedList, actualList);
    }

    @Test
    void update() {
        Certificate expectedCertificate = new Certificate(1, "new name");

        certificateDao.update(expectedCertificate);

        Certificate actualCertificate = certificateDao.read(expectedCertificate.getId()).get();
        assertEquals(expectedCertificate, actualCertificate);
    }

    @Test
    void readCertificateTags() {
        Certificate certificate = givenExistingCertificate2();
        Tag tag1 = givenExistingTag1();
        Tag tag2 = givenExistingTag2();
        List<Tag> expectedTags = List.of(tag1, tag2);

        List<Tag> actualTags = certificateDao.readCertificateTags(certificate.getId());
        assertEquals(expectedTags, actualTags);
    }

    @Test
    void addTag() {
        Certificate certificate = givenExistingCertificate1();
        Tag tag = givenExistingTag1();
        List<Tag> expectedTags = List.of(tag);

        certificateDao.addTag(tag.getId(), certificate.getId());

        List<Tag> actualTags = certificateDao.readCertificateTags(certificate.getId());
        assertEquals(expectedTags, actualTags);
    }

    @Test
    void removeTag() {
        Certificate certificate = givenExistingCertificate2();
        Tag tag1 = givenExistingTag1();
        Tag tag2 = givenExistingTag2();
        List<Tag> expectedTags = List.of(tag1);

        certificateDao.removeTag(tag2.getId(), certificate.getId());

        List<Tag> actualTags = certificateDao.readCertificateTags(certificate.getId());
        assertEquals(expectedTags, actualTags);
    }

    @Test
    void deleteCertificate() {
        Certificate certificate = givenExistingCertificate1();

        certificateDao.deleteCertificate(certificate.getId());

        Optional<Certificate> actualCertificate = certificateDao.read(certificate.getId());
        assertTrue(actualCertificate.isEmpty());
    }

    @Test
    void deleteBondingTagsByTagId() {
        Certificate certificate = givenExistingCertificate2();
        Tag tag = givenExistingTag1();
        List<Tag> expectedTags = List.of(tag);

        certificateDao.deleteBondingTagsByTagId(certificate.getId());

        List<Tag> actualTags = certificateDao.readCertificateTags(certificate.getId());
        assertEquals(expectedTags, actualTags);
    }

    @Test
    void deleteBondingTagsByCertificateId() {
        Certificate certificate = givenExistingCertificate2();
        List<Tag> expectedTags = Collections.emptyList();

        certificateDao.deleteBondingTagsByCertificateId(certificate.getId());

        List<Tag> actualTags = certificateDao.readCertificateTags(certificate.getId());
        assertEquals(expectedTags, actualTags);
    }

    @Test
    void updatePatch() {
        Certificate expectedCertificate = givenExistingCertificate1();
        expectedCertificate.setName("new name");
        CertificatePatch updateCertificate = new CertificatePatch();
        updateCertificate.setId(1);
        updateCertificate.setName("new name");

        certificateDao.updatePatch(updateCertificate);

        Certificate actualCertificate = certificateDao.read(expectedCertificate.getId()).get();
        assertEquals(expectedCertificate, actualCertificate);
    }

    private static Certificate givenExistingCertificate1() {
        return new Certificate(1, "first certificate", "first description", 1.33, 5,
                LocalDateTime.of(2020, 12, 25, 15, 0, 0),
                LocalDateTime.of(2020, 12, 30, 16, 30, 0));
    }

    private static Certificate givenExistingCertificate2() {
        return new Certificate(2, "second certificate", "second description", 2.33, 10,
                LocalDateTime.of(2020, 12, 25, 15, 0, 0),
                LocalDateTime.of(2021, 1, 5, 14, 0, 0));
    }

    private static Certificate givenNewCertificateWithoutId() {
        return new Certificate("third certificate", "third certificate", 3.33, 15,
                LocalDateTime.of(2021, 1, 5, 14, 0, 0),
                LocalDateTime.of(2021, 1, 5, 14, 0, 0));
    }

    private static Tag givenExistingTag1() {
        return new Tag(1, "first tag");
    }

    private static Tag givenExistingTag2() {
        return new Tag(2, "second tag");
    }
}