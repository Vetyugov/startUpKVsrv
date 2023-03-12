package ru.kv.startupkvsrv.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kv.startupkvsrv.converters.PostConverter;
import ru.kv.startupkvsrv.dbEntities.locations.Location;
import ru.kv.startupkvsrv.dbEntities.main.Post;
import ru.kv.startupkvsrv.dbEntities.main.UserInfo;
import ru.kv.startupkvsrv.integrations.ImageServiceIntegration;
import ru.kv.startupkvsrv.publuc.dto.ImageDTO;
import ru.kv.startupkvsrv.publuc.dto.PostNewRq;
import ru.kv.startupkvsrv.publuc.dto.PostRs;
import ru.kv.startupkvsrv.publuc.exceptions.NotFoundException;
import ru.kv.startupkvsrv.repositories.LocationRepository;
import ru.kv.startupkvsrv.repositories.PostRepository;
import ru.kv.startupkvsrv.repositories.UserInfoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final LocationRepository locationRepository;
    private final UserInfoRepository userInfoRepository;

    private final ImageServiceIntegration imageServiceIntegration;

    public List<PostRs> getAllPosts() {
        return postRepository.findAll().stream().map(PostConverter::postToResponse).toList();
    }

    public List<PostRs> getAllPostsInRegion(Long regionId) {
        return postRepository.findAllByLocationRegionId(regionId).stream().map(PostConverter::postToResponse).toList();
    }

    public List<PostRs> getAllUserPostsInRegion(Long userId, Long regionId) {
        return postRepository.findAllByUserInfoIdAndLocationRegionId(userId, regionId).stream().map(PostConverter::postToResponse).toList();
    }

    public List<PostRs> getAllUserPosts(Long userId) {
        return postRepository.findByUserInfoId(userId).stream().map(PostConverter::postToResponse).toList();
    }

    public PostRs saveNewPost(PostNewRq postNewRq) throws NotFoundException {
        //Находим локацию
        Location location = locationRepository.findById(postNewRq.getLocationId())
                .orElseThrow(() -> new NotFoundException("Location with id " + postNewRq.getLocationId() + " not found"));
        //Находим Инфу о пользователе
        UserInfo userInfo = userInfoRepository.findById(postNewRq.getUserId())
                .orElseThrow(() -> new NotFoundException("User with id " + postNewRq.getUserId() + " not found"));

        List<ImageDTO> savedImages = imageServiceIntegration.saveImages(postNewRq.getImages());

        Post newPost = postRepository.save(PostConverter.requestToPost(postNewRq, location, userInfo, savedImages));
        return PostConverter.postToResponse(newPost);
    }

    public void deletePost(Long postId) throws NotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Location with id " + postId + " not found"));
        postRepository.delete(post);
    }

}
