package ru.kv.startupkvsrv.publuc.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoRq {
    private String nickname;
    private String name;
    private String surname;
    private byte[] avatar;
    private String country;
    private String city;
}