package ru.kv.startupkvsrv.converters;

import ru.kv.startupkvsrv.dbEntities.main.Post;
import ru.kv.startupkvsrv.dbEntities.main.PostImage;
import ru.kv.startupkvsrv.publuc.dto.ImageDTO;

public class IntegrationImageConverter {
    public static PostImage imageDtoToPostImage(ImageDTO imageDTO, Post post){
        return PostImage.builder()
                .post(post)
                .imageLink(imageDTO.getImageId())
                .build();
    }
}
