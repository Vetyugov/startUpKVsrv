package ru.kv.startupkvsrv.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDTO {
    private long imageId;
    private byte[] image;
}
