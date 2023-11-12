package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.publisherDtos.PublisherAddDto;
import com.springboot.bookreview.dto.publisherDtos.PublisherDto;
import com.springboot.bookreview.entities.Publisher;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.PublisherRepository;
import com.springboot.bookreview.services.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherDto> getAllPublishers() {
        return publisherRepository
                .findAll()
                .stream()
                .map(publisher -> mapEntityToDto(publisher))
                .collect(Collectors.toList());
    }

    @Override
    public PublisherDto getPublisherById(Long publisherId) {

        Publisher publisherFromDb = publisherRepository.findById(publisherId).orElseThrow(()
                -> new ResourceNotFoundException("Publisher", "id", publisherId));

        return mapEntityToDto(publisherFromDb);
    }

    @Override
    public PublisherDto addPublisher(PublisherAddDto publisherAddDto) {

        Publisher publisherToAdd = mapAddDtoToEntity(publisherAddDto);
        Publisher publisherFromDb = publisherRepository.save(publisherToAdd);

        return mapEntityToDto(publisherFromDb);

    }

    @Override
    public void deletePublisher(Long publisherId) {

        Publisher publisherFromDb = publisherRepository.findById(publisherId).orElseThrow(()
                -> new ResourceNotFoundException("Publisher", "id", publisherId));

        publisherRepository.delete(publisherFromDb);

    }

    public static PublisherDto mapEntityToDto(Publisher publisher) {

        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setId(publisher.getId());
        publisherDto.setName(publisher.getName());

        return publisherDto;
    }


    private Publisher mapAddDtoToEntity(PublisherAddDto publisherAddDto) {

        return new Publisher(
                publisherAddDto.getName()
        );
    }
}
