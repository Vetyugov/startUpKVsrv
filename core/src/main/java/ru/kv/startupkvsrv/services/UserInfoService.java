package ru.kv.startupkvsrv.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.kv.startupkvsrv.converters.UserInfoConverter;
import ru.kv.startupkvsrv.dbEntities.main.UserInfo;
import ru.kv.startupkvsrv.dto.UserInfoRq;
import ru.kv.startupkvsrv.dto.UserInfoRs;
import ru.kv.startupkvsrv.exceptions.AlreadyExistException;
import ru.kv.startupkvsrv.repositories.UserInfoRepository;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfoRs getInfoByNickName(String nickName) {
        UserInfo userInfo = userInfoRepository.findByNickname(nickName)
                .orElseThrow(() -> new NotFoundException("User with nickName '" + nickName + "' not found"));
        return UserInfoConverter.userInfoToResponse(userInfo);
    }

    public UserInfoRs saveNew(UserInfoRq newUserInfo) throws AlreadyExistException {
        if (userInfoRepository.findByNickname(newUserInfo.getNickname()).isPresent()) {
            throw new AlreadyExistException("User with nickname '" + newUserInfo.getNickname() + "' is already exists");
        }
        UserInfo saved = userInfoRepository.save(UserInfoConverter.requestToUserInfo(newUserInfo, null));
        return UserInfoConverter.userInfoToResponse(saved);
    }

    public UserInfoRs update(UserInfoRq userInfoRq, Long userInfoId) throws ru.kv.startupkvsrv.exceptions.NotFoundException {
        if (userInfoId == null || userInfoRepository.findById(userInfoId).isPresent()) {
            throw new NotFoundException("User with id = " + userInfoId + "not found");
        }
        UserInfo updated = userInfoRepository.save(UserInfoConverter.requestToUserInfo(userInfoRq, userInfoId));
        return UserInfoConverter.userInfoToResponse(updated);
    }
}
