package ru.hexaend.group_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import ru.hexaend.group_chat.entity.GroupChat;

import java.util.UUID;

public interface GroupChatRepository extends JpaRepository<GroupChat, UUID> {
}