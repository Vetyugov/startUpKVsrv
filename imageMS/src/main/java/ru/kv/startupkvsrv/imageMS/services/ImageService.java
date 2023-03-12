package ru.kv.startupkvsrv.imageMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kv.startupkvsrv.imageMS.converters.ImageConverter;
import ru.kv.startupkvsrv.imageMS.dbEntities.DbImage;
import ru.kv.startupkvsrv.imageMS.repositories.ImageRepository;
import ru.kv.startupkvsrv.publuc.dto.ImageDTO;
import ru.kv.startupkvsrv.publuc.dto.ImageNewRq;
import ru.kv.startupkvsrv.publuc.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageDTO findImageById(Long imageId) throws NotFoundException {
        return ImageConverter.imageToDTO(imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image with id " + imageId+" not found"))
        );
    }

    public ImageDTO saveImage(ImageNewRq newImage){
        DbImage saved = imageRepository.save(ImageConverter.rqToDbImage(newImage));
        return ImageConverter.imageToDTO(saved);
    }

    public void deleteImage(Long imageId) throws NotFoundException {
        DbImage toDelete = imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image with id " + imageId+" not found"));
        imageRepository.delete(toDelete);
    }
}
