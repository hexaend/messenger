package ru.hexaend.personal_chat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.hexaend.personal_chat.entity.PersonalChat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonalChatRepository extends CrudRepository<PersonalChat, UUID> {

    @Query("""
    SELECT pc
    FROM PersonalChat pc
    WHERE (pc.userId = :userId AND pc.ownerId = :ownerId AND pc.isDeleted = FALSE) OR (pc.userId = :ownerId AND pc.ownerId = :userId AND pc.isDeleted = FALSE)
""")
    Optional<PersonalChat> findPersonalChatByUserIdAndOwnerIdMyRealisation(@Param("userId") String userId, @Param("ownerId") String ownerId);

    Optional<PersonalChat> findPersonalChatByIsDeletedFalseAndId(UUID id);
}