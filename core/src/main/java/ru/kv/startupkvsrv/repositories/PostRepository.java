package ru.kv.startupkvsrv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kv.startupkvsrv.dbEntities.main.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("select p from Post p where p.location.region.id = ?1")
    List<Post> findAllByLocationRegionId(@Param("regionId")Long regionId);

    List<Post> findAllByUserInfoIdAndLocationRegionId(@Param("userId")Long userId, @Param("regionId")Long regionId);

    List<Post> findByUserInfoId(@Param("userId")Long userId);
}
