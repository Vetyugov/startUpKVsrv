package ru.kv.startupkvsrv.converters;

import ru.kv.startupkvsrv.dbEntities.main.UserInfo;
import ru.kv.startupkvsrv.publuc.dto.UserInfoRq;
import ru.kv.startupkvsrv.publuc.dto.UserInfoRs;

public class UserInfoConverter {
    public static UserInfoRs userInfoToResponse(UserInfo userInfo){
        return UserInfoRs.builder()
                .id(userInfo.getId())
                .nickname(userInfo.getNickname())
                .avatar(userInfo.getAvatar())
                .build();
    }

    public static UserInfo requestToUserInfo(UserInfoRq userInfoRq, Long userInfoId){
        return UserInfo.builder()
                .id(userInfoId)
                .nickname(userInfoRq.getNickname())
                .name(userInfoRq.getName())
                .surname(userInfoRq.getSurname())
                .avatar(userInfoRq.getAvatar())
                .country(userInfoRq.getCountry())
                .city(userInfoRq.getCity())
                .build();
    }

}
