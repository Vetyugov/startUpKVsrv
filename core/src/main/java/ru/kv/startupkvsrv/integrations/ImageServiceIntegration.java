package ru.kv.startupkvsrv.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.kv.startupkvsrv.publuc.dto.ImageDTO;
import ru.kv.startupkvsrv.publuc.dto.ImageNewRq;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageServiceIntegration {
    private final WebClient imageServiceWebClient;

    public List<ImageDTO> saveImages(List<ImageNewRq> newImages) {
        return newImages.stream().map(i -> {
            return imageServiceWebClient.post()
                    .uri("imagesMS/image")
                    .bodyValue(i)
                    .retrieve()
                    .bodyToMono(ImageDTO.class)
                    .block();
                }

        ).toList();

    }
}
