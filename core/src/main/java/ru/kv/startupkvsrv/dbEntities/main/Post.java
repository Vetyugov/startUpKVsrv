package ru.kv.startupkvsrv.dbEntities.main;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.kv.startupkvsrv.dbEntities.locations.Location;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Getter
@Setter
@Entity
@Table(name = "post", schema = "main_app")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "\"desc\"", length = Integer.MAX_VALUE)
    private String desc;

    @OneToMany(mappedBy = "post")
    private Set<Image> images = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "location_id", nullable = false, referencedColumnName = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "app_user_id")
    private UserInfo userInfo;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}