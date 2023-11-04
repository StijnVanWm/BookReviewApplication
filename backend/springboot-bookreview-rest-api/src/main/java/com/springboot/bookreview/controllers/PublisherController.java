package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.publisherDtos.PublisherAddDto;
import com.springboot.bookreview.dto.publisherDtos.PublisherDto;
import com.springboot.bookreview.services.AuthorService;
import com.springboot.bookreview.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    //GET: api/publishers
    @GetMapping
    public ResponseEntity<List<PublisherDto>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    //GET: api/publisher/5
    @GetMapping("{id}")
    public ResponseEntity<PublisherDto> getPublisherById(@PathVariable(name = "id") Long publisherId) {
        return ResponseEntity.ok(publisherService.getPublisherById(publisherId));
    }

    //POST: api/publishers
    @PostMapping
    public ResponseEntity<PublisherDto> addPublisher(@RequestBody PublisherAddDto publisherAddDto) {

        PublisherDto publisherFromDb = publisherService.addPublisher(publisherAddDto);

        if(publisherFromDb == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(publisherFromDb, HttpStatus.CREATED);
    }

    //DELETE: api/publishers
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable(name = "id") Long publisherId) {
        publisherService.deletePublisher(publisherId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
