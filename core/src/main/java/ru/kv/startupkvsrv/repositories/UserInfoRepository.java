package ru.kv.startupkvsrv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kv.startupkvsrv.dbEntities.main.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByNickname(String nickName);

    Optional<UserInfo> findById(Long id);
}
