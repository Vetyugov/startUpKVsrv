package ru.kv.startupkvsrv.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kv.startupkvsrv.dto.PostNewRq;
import ru.kv.startupkvsrv.dto.PostRs;
import ru.kv.startupkvsrv.exceptions.NotFoundException;
import ru.kv.startupkvsrv.services.PostService;

import java.util.List;

@Tag(name = "Посты пользователей", description = "Методы работы с постами пользователей")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

//    @Operation(
//            summary = "Запрос на получение списка всех постов",
//            responses = {
//                    @ApiResponse(
//                            description = "Успешный ответ", responseCode = "200",
//                            content = @Content(schema = @Schema(implementation = Page.class))
//                    )
//            }
//    )
    @GetMapping
    public List<PostRs> getAll(
            @RequestParam(name = "p", defaultValue = "1") @Parameter(description = "Номер страницы") Integer page
    ) {
        if (page < 0){
            page = 1;
        }
        return postService.getAllPosts();
    }

    @Operation(
            summary = "Запрос на получение списка постов пользователя в определенном регионе",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PostRs.class))
                    )
            }
    )
    @GetMapping("/region/{regionId}/user/{userId}")
    private List<PostRs> getAllUserPostsForRegion(
            @PathVariable @Parameter(description = "id региона", required = true) Long regionId,
            @PathVariable @Parameter(description = "id пользователя", required = true) Long userId
    ){
        return postService.getAllUserPostsInRegion(userId, regionId);
    }

    @Operation(
            summary = "Запрос на получение списка постов всех пользователей в определенном регионе",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PostRs.class))
                    )
            }
    )
    @GetMapping("/region/{regionId}")
    private List<PostRs> getAllPostsForRegion(
            @PathVariable @Parameter(description = "id региона", required = true) Long regionId
    ){
        return postService.getAllPostsInRegion(regionId);
    }

    @Operation(
            summary = "Запрос на получение списка постов определенного пользователя со всех регионов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PostRs.class))
                    )
            }
    )
    @GetMapping("/user/{userId}")
    private List<PostRs> getAllUserPosts(
            @PathVariable @Parameter(description = "id пользователя", required = true) Long userId
    ){
        return postService.getAllUserPosts(userId);
    }

    @Operation(
            summary = "Запрос на сохранение поста",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PostRs.class))
                    )
            }
    )
    @PutMapping()
    private PostRs savePost(
            @RequestBody @Parameter(description = "Новый пост", required = true) PostNewRq postNewRq
    ) throws NotFoundException {
        return postService.saveNewPost(postNewRq);
    }

    @DeleteMapping("/{postId}")
    private void deletePost(@PathVariable Long postId) throws NotFoundException{
        postService.deletePost(postId);
    }

}
