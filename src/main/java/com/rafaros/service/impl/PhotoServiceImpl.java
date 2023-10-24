package com.rafaros.service.impl;

import com.rafaros.domain.Photo;
import com.rafaros.repository.PhotoRepository;
import com.rafaros.service.PhotoService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Photo}.
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private final Logger log = LoggerFactory.getLogger(PhotoServiceImpl.class);

    private final PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo save(Photo photo) {
        log.debug("Request to save Photo : {}", photo);
        return photoRepository.save(photo);
    }

    @Override
    public Photo update(Photo photo) {
        log.debug("Request to update Photo : {}", photo);
        return photoRepository.save(photo);
    }

    @Override
    public Optional<Photo> partialUpdate(Photo photo) {
        log.debug("Request to partially update Photo : {}", photo);

        return photoRepository
            .findById(photo.getId())
            .map(existingPhoto -> {
                if (photo.getTitle() != null) {
                    existingPhoto.setTitle(photo.getTitle());
                }
                if (photo.getLinkToPhotoFile() != null) {
                    existingPhoto.setLinkToPhotoFile(photo.getLinkToPhotoFile());
                }
                if (photo.getDescription() != null) {
                    existingPhoto.setDescription(photo.getDescription());
                }
                if (photo.getAuthor() != null) {
                    existingPhoto.setAuthor(photo.getAuthor());
                }
                if (photo.getOwner() != null) {
                    existingPhoto.setOwner(photo.getOwner());
                }
                if (photo.getHeight() != null) {
                    existingPhoto.setHeight(photo.getHeight());
                }
                if (photo.getWidth() != null) {
                    existingPhoto.setWidth(photo.getWidth());
                }
                if (photo.getTaken() != null) {
                    existingPhoto.setTaken(photo.getTaken());
                }
                if (photo.getUploaded() != null) {
                    existingPhoto.setUploaded(photo.getUploaded());
                }

                return existingPhoto;
            })
            .map(photoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Photo> findAll(Pageable pageable) {
        log.debug("Request to get all Photos");
        return photoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Photo> findOne(Long id) {
        log.debug("Request to get Photo : {}", id);
        return photoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Photo : {}", id);
        photoRepository.deleteById(id);
    }
}
