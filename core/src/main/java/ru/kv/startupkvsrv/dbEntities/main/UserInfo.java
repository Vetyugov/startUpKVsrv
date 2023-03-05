package ru.kv.startupkvsrv.dbEntities.main;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder


@Getter
@Setter
@Entity
@Table(name = "user_info", schema = "main_app")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false, length = Integer.MAX_VALUE)
    private String nickname;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "surname", length = Integer.MAX_VALUE)
    private String surname;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "country", length = Integer.MAX_VALUE)
    private String country;

    @Column(name = "city", length = Integer.MAX_VALUE)
    private String city;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY)
    private Set<Post> posts = new LinkedHashSet<>();

}