package ru.kv.startupkvsrv.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class PostRs {
    private Long id;
    private String location;
    private String desc;
    private List<ImageDTO> images;
    private UserInfoRs user;
    private Instant createTime;
}
