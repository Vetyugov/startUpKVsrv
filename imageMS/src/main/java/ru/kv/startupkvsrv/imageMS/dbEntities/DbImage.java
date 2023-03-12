package ru.kv.startupkvsrv.imageMS.dbEntities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Getter
@Setter
@Entity
@Table(name = "image", schema = "main_app_images")
public class DbImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long id;

    @Column(name = "image", nullable = false)
    private byte[] image;

}