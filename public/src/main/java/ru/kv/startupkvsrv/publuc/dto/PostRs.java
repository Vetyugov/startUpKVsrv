package ru.kv.startupkvsrv.publuc.dto;

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
    private List<Long> images;
    private UserInfoRs user;
    private Instant createTime;
}