package com.epam.esm.controller;

import com.epam.esm.repository.entity.Tag;
import com.epam.esm.repository.entity.TagAction;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.exception.ResourceValidationException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> readTag(@PathVariable int id) {
        Tag tag = null;
        try {
            tag = tagService.read(id);
        } catch (ResourceNotFoundException e) {
            //log
        }
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tag> readTags() {
        return tagService.readAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag createTag(@RequestBody @Valid Tag tag) {
        return tagService.create(tag);
    }

    @PostMapping("/action")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> processTagAction(@RequestBody TagAction action) {
        try {
            tagService.processTagAction(action);
        } catch (ResourceValidationException e) {
            //log
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable int id) {
        try {
            tagService.delete(id);
        } catch (ResourceValidationException e) {
            //log
        }
    }

}

