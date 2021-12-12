package com.epam.esm.dao;

import com.epam.esm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CertificateDaoImpl implements CertificateDao {

    private final JdbcTemplate jdbcTemplate;
    private final SqlHandler sqlHandler;

    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate, SqlHandler sqlHandler) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlHandler = sqlHandler;
    }

    private static final String SQL_CREATE_CERTIFICATE = "INSERT INTO gift_certificate (name, description, " +
            "price, duration, create_date, last_update_date) VALUES (?,?,?,?,?,?)";

    private static final String SQL_READ_CERTIFICATE = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date from gift_certificate WHERE id = ?";

    private static final String SQL_UPDATE = "UPDATE gift_certificate SET name = ?, description = ?, price = ?, duration = ?," +
            " create_date = ?, last_update_date = ? WHERE id = ?";

    private static final String SQL_DELETE_CERTIFICATE = "DELETE FROM gift_certificate WHERE id = ?";

    private static final String SQL_ADD_TAG = "INSERT INTO gift_certificate_m2m_tag (tag_id, gift_certificate_id) " +
            "VALUES (?, ?)";

    private static final String SQL_REMOVE_TAG = "DELETE FROM gift_certificate_m2m_tag WHERE " +
            "gift_certificate_id = ? AND tag_id = ?";

    private static final String SQL_READ_BONDING_TAGS = "SELECT t.id, name FROM tag t JOIN gift_certificate_m2m_tag m2m ON t.id=m2m.tag_id WHERE gift_certificate_id = ?";

    private static final String SQL_DELETE_BONDING_TAGS_BY_TAG_ID = "DELETE FROM gift_certificate_m2m_tag WHERE tag_id = ?";

    private static final String SQL_DELETE_BONDING_TAGS_BY_CERTIFICATE_ID =
            "DELETE FROM gift_certificate_m2m_tag WHERE gift_certificate_id = ?";

    @Override
    public Certificate createCertificate(Certificate certificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_CREATE_CERTIFICATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, certificate.getName());
            ps.setString(2, certificate.getDescription());
            ps.setDouble(3, certificate.getPrice());
            ps.setInt(4, certificate.getDuration());
            ps.setObject(5, certificate.getCreateDate());
            ps.setObject(6, certificate.getLastUpdateDate());
            return ps;
        }, keyHolder);
        certificate.setId(keyHolder.getKey().intValue());

        return certificate;
    }

    @Override
    public Optional<Certificate> read(int certificateId) {
        return jdbcTemplate
                .queryForStream(SQL_READ_CERTIFICATE, new BeanPropertyRowMapper<>(Certificate.class), certificateId)
                .findAny();
    }


    @Override
    public List<Certificate> readAll(CertificateRequestParameter parameter) {
        SqlData sqlData = sqlHandler.generateSqlDataForReadAllRequest(parameter);
        return jdbcTemplate.query(
                sqlData.getRequest(), new BeanPropertyRowMapper<>(Certificate.class), sqlData.getArgs().toArray());
    }




    @Override
    public int update(Certificate certificate) {
        return jdbcTemplate.update(
                SQL_UPDATE,
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getDuration(),
                certificate.getCreateDate(),
                certificate.getLastUpdateDate(),
                certificate.getId());
    }

    @Override
    public List<Tag> readCertificateTags(int certificateId) {
        return jdbcTemplate.query(SQL_READ_BONDING_TAGS, new BeanPropertyRowMapper<>(Tag.class), certificateId);
    }

    @Override
    public void addTag(int tagId, int certificateId) {
        jdbcTemplate.update(SQL_ADD_TAG, tagId, certificateId);

    }

    @Override
    public int removeTag(int tagId, int certificateId) {
        return jdbcTemplate.update(SQL_REMOVE_TAG, tagId, certificateId);
    }

    @Override
    public int deleteCertificate(int certificateId) {
        return jdbcTemplate.update(SQL_DELETE_CERTIFICATE, certificateId);
    }

    @Override
    public int deleteBondingTagsByTagId(int tagId) {
        return jdbcTemplate.update(SQL_DELETE_BONDING_TAGS_BY_TAG_ID, tagId);
    }

    @Override
    public int deleteBondingTagsByCertificateId(int certificateId) {
        return jdbcTemplate.update(SQL_DELETE_BONDING_TAGS_BY_CERTIFICATE_ID, certificateId);
    }

    @Override
    public int updatePatch(CertificatePatch certificate) {
        SqlData sqlData = sqlHandler.generateSqlDataForUpdateRequest(certificate);
        return jdbcTemplate.update(sqlData.getRequest(), sqlData.getArgs().toArray());
    }

}
