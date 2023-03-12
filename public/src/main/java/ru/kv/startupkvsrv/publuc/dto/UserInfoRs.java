package ru.kv.startupkvsrv.publuc.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoRs {
    private long id;
    private String nickname;
    private byte[] avatar;
}
