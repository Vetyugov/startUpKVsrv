package ru.kv.startupkvsrv.imageMS.converters;

import ru.kv.startupkvsrv.imageMS.dbEntities.DbImage;
import ru.kv.startupkvsrv.publuc.dto.ImageDTO;
import ru.kv.startupkvsrv.publuc.dto.ImageNewRq;

public class ImageConverter {
    public static ImageDTO imageToDTO(DbImage dbImage){
        return ImageDTO.builder()
                .imageId(dbImage.getId())
                .image(dbImage.getImage())
                .build();
    }

    public static DbImage dtoToDbImage(ImageDTO dto){
        return DbImage.builder()
                .id(dto.getImageId())
                .image(dto.getImage())
                .build();
    }

    public static DbImage rqToDbImage(ImageNewRq rq){
        return DbImage.builder()
                .image(rq.getImage())
                .build();
    }

}
