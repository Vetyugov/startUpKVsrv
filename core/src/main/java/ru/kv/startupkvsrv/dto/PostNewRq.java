package ru.kv.startupkvsrv.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostNewRq {
    private Long locationId;
    private String desc;
    private List<ImageDTO> images;
    private Long userId;
}
