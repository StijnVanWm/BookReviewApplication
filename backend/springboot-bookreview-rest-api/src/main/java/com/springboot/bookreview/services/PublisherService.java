package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.publisherDtos.PublisherAddDto;
import com.springboot.bookreview.dto.publisherDtos.PublisherDto;

import java.util.List;

public interface PublisherService {

    List<PublisherDto> getAllPublishers();
    PublisherDto getPublisherById(Long publisherId);
    PublisherDto addPublisher(PublisherAddDto publisherAddDto);
    void deletePublisher(Long publisherId);
}
