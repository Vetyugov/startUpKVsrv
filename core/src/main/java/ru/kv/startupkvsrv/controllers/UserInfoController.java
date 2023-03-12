package ru.kv.startupkvsrv.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kv.startupkvsrv.publuc.exceptions.AlreadyExistException;
import ru.kv.startupkvsrv.publuc.exceptions.NotFoundException;
import ru.kv.startupkvsrv.publuc.dto.UserInfoRq;
import ru.kv.startupkvsrv.publuc.dto.UserInfoRs;
import ru.kv.startupkvsrv.services.UserInfoService;

@Tag(name = "Информация о пользователе")
@RestController
@RequestMapping("/userInfo")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Operation(
            summary = "Запрос на получение информации о пользователе по никнейму",
            responses = {
                    @ApiResponse(
                            description = "Информация о пользователе", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserInfoRs.class))
                    )
            }
    )
    @GetMapping("/{nickName}")
    public UserInfoRs getInfo(@PathVariable String nickName) {
        return userInfoService.getInfoByNickName(nickName);
    }

    @Operation(
            summary = "Запрос на добавление нового пользователя",
            responses = {
                    @ApiResponse(
                            description = "Сохраненная информация о пользователе", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserInfoRs.class))
                    )
            }
    )
    @PostMapping
    public UserInfoRs saveNewUserInfo(@RequestBody UserInfoRq userInfoRs) throws AlreadyExistException {
        return userInfoService.saveNew(userInfoRs);
    }

    @Operation(
            summary = "Запрос на обновление информации о пользователе",
            responses = {
                    @ApiResponse(
                            description = "Обновленная информация о пользователе", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserInfoRs.class))
                    )
            }
    )
    @PutMapping("/{userInfoId}")
    public UserInfoRs updateUserInfo(@PathVariable Long userInfoId, @RequestBody UserInfoRq toUpdate) throws NotFoundException {
        return userInfoService.update(toUpdate, userInfoId);
    }

}
