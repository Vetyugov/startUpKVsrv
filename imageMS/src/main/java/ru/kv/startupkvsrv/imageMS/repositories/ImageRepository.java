package ru.kv.startupkvsrv.imageMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kv.startupkvsrv.imageMS.dbEntities.DbImage;

@Repository
public interface ImageRepository extends JpaRepository<DbImage, Long> {

}
