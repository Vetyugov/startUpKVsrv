package ru.kv.startupkvsrv.converters;

import ru.kv.startupkvsrv.dbEntities.main.Image;
import ru.kv.startupkvsrv.dto.ImageDTO;

public class ImageConverter {
    public static ImageDTO imageToDTO(Image image){
        return ImageDTO.builder()
                .imageId(image.getId())
                .image(image.getImage())
                .build();
    }

    public static Image dtoToImage(ImageDTO dto){
        return Image.builder()
                .id(dto.getImageId())
                .image(dto.getImage())
                .build();
    }
}
