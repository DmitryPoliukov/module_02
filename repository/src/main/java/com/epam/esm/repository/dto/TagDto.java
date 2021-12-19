package com.epam.esm.repository.dto;

import com.epam.esm.repository.entity.Tag;

import javax.validation.constraints.Size;

public class TagDto {

    private int id;

    @Size(min=1,max = 45)
    private String name;

    public TagDto(Tag entityTag) {
        this.id = entityTag.getId();
        this.name = entityTag.getName();
    }

    public Tag toEntity() {
        Tag entityTag = new Tag();
        entityTag.setId(this.id);
        entityTag.setName(this.name);
        return entityTag;
    }

    public TagDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
