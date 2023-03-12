package ru.kv.startupkvsrv.imageMS.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kv.startupkvsrv.imageMS.services.ImageService;
import ru.kv.startupkvsrv.publuc.dto.ImageDTO;
import ru.kv.startupkvsrv.publuc.dto.ImageNewRq;
import ru.kv.startupkvsrv.publuc.exceptions.NotFoundException;

@Tag(name = "Изображения пользователей", description = "Методы для работы с изображениями пользователей")
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @Operation(
            summary = "Запрос на получение изображения по ссылке",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ImageDTO.class))
                    )
            }
    )
    @GetMapping("/{imageLink}")
    private ImageDTO getImageByLink(
            @PathVariable @Parameter(description = "id изображения", required = true) Long imageLink
    ) throws NotFoundException {
        return imageService.findImageById(imageLink);
    }

    @Operation(
            summary = "Запрос на сохранение изображения в БД",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ImageDTO.class))
                    )
            }
    )
    @PostMapping()
    private ImageDTO saveImage(
            @RequestBody @Parameter(description = "Новое изображение", required = true) ImageNewRq newImage
    ) {
        return imageService.saveImage(newImage);
    }


    @Operation(
            summary = "Запрос на удаление изображения из БД",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ImageDTO.class))
                    )
            }
    )
    @DeleteMapping("/{imageLink}")
    private void deleteImage(
            @PathVariable @Parameter(description = "id изображения", required = true) Long imageLink
    ) throws NotFoundException {
        imageService.deleteImage(imageLink);
    }

}
