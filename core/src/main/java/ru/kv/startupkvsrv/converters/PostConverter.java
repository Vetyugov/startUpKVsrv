package ru.kv.startupkvsrv.converters;

import ru.kv.startupkvsrv.dbEntities.main.Post;
import ru.kv.startupkvsrv.dbEntities.locations.Location;
import ru.kv.startupkvsrv.dbEntities.main.PostImage;
import ru.kv.startupkvsrv.dbEntities.main.UserInfo;
import ru.kv.startupkvsrv.publuc.dto.ImageDTO;
import ru.kv.startupkvsrv.publuc.dto.PostNewRq;
import ru.kv.startupkvsrv.publuc.dto.PostRs;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {
    public static PostRs postToResponse(Post post){
        return PostRs.builder()
                .id(post.getId())
                .location(post.getLocation().getName())
                .desc(post.getDesc())
                .images(post.getPostImages().stream().map(PostImage::getImageLink).toList())
                .user(UserInfoConverter.userInfoToResponse(post.getUserInfo()))
                .createTime(post.getCreateTime())
                .build();
    }

    public static Post requestToPost(PostNewRq dto, Location location, UserInfo userInfo, List<ImageDTO> images){
        Post post = Post.builder()
                .location(location)
                .desc(dto.getDesc())
                .userInfo(userInfo)
                .build();
        post.setPostImages(images.stream().map(im -> IntegrationImageConverter.imageDtoToPostImage(im, post)).collect(Collectors.toSet()));
        return post;
    }
}
