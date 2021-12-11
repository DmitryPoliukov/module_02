package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagAction {

    public enum ActionType {
        @JsonProperty(value = "add")
        ADD,
        @JsonProperty(value = "remove")
        REMOVE
    }

    private ActionType type;
    private int certificateId;
    private int tagId;

    public TagAction() {}

    public TagAction(ActionType type, int certificateId, int tagId) {
        this.type = type;
        this.certificateId = certificateId;
        this.tagId = tagId;
    }

    public ActionType getType() {
        return type;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public int getTagId() {
        return tagId;
    }

}
