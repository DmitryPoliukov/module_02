package com.epam.esm.repository.dto;

import com.epam.esm.repository.entity.Certificate;
import com.epam.esm.repository.entity.Tag;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class CertificateDto {

    private Integer id;

    @Size(min = 1,max = 45)
    private String name;

    @Size(min = 1,max = 45)
    private String description;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    private Integer duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private List<Tag> tags;
/*
    StudentDto dto = modelMapper.map(entity, StudentDto.class);
    Student entity = modelMapper.map(dto, Student.class);


    public CertificateDto(Certificate entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.duration = entity.getDuration();
        this.createDate = entity.getCreateDate();
        this.lastUpdateDate = entity.getLastUpdateDate();
        this.tags = entity.getTags();
    }

    public Certificate toEntity() {
        Certificate entity = new Certificate();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setPrice(this.price);
        entity.setDuration(this.duration);
        entity.set
        return entity;
    }

 */
}
