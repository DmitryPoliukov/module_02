package com.epam.esm.controller;

import com.epam.esm.repository.dto.TagDto;
import com.epam.esm.repository.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
        Tag tag = tagService.read(id).toEntity();

        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tag> readTags() {
        List<Tag> tagList = new ArrayList<>();
        List<TagDto> tagDtoList = tagService.readAll();
        for (TagDto tagDto : tagDtoList) {
            tagList.add(tagDto.toEntity());
        }
        return tagList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag createTag(@RequestBody @Valid TagDto tagDto) {
        return tagService.create(tagDto).toEntity();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable int id) {
        tagService.delete(id);
    }

}

