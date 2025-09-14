package ru.hexaend.group_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import ru.hexaend.group_chat.entity.GroupChat;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface GroupChatRepository extends JpaRepository<GroupChat, UUID> {
    @Query("""
SELECT g from GroupChat as g
join g.users u
where g.id = :id and g.isDeleted = false and (u = :user or g.ownerId = :user)
""")
    Optional<GroupChat> findByChatIdAndUser(UUID id, String user);
}