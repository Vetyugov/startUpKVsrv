package ru.kv.startupkvsrv.converters;

import ru.kv.startupkvsrv.dbEntities.main.Post;
import ru.kv.startupkvsrv.dbEntities.locations.Location;
import ru.kv.startupkvsrv.dbEntities.main.UserInfo;
import ru.kv.startupkvsrv.dto.PostNewRq;
import ru.kv.startupkvsrv.dto.PostRs;

import java.util.stream.Collectors;

public class PostConverter {
    public static PostRs postToResponse(Post post){
        return PostRs.builder()
                .id(post.getId())
                .location(post.getLocation().getName())
                .desc(post.getDesc())
                .images(post.getImages().stream().map(ImageConverter::imageToDTO).toList())
                .user(UserInfoConverter.userInfoToResponse(post.getUserInfo()))
                .createTime(post.getCreateTime())
                .build();
    }

    public static Post requestToPost(PostNewRq dto, Location location, UserInfo userInfo){
        return Post.builder()
                .location(location)
                .desc(dto.getDesc())
                .images(dto.getImages().stream().map(ImageConverter::dtoToImage).collect(Collectors.toSet()))
                .userInfo(userInfo)
                .build();
    }
}
