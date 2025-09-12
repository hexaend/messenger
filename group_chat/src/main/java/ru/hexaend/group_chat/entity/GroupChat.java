package ru.hexaend.group_chat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "group_chats")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupChat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name="name", nullable = false)
    private String name;

    @CreatedBy
    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @ElementCollection
    @CollectionTable(name = "group_chat_users", joinColumns = @JoinColumn(name = "group_chat_id"))
    @Column(name = "user_id", nullable = false)
    private Set<String> users = new HashSet<>();

    @Column(name = "is_deleted", nullable = false)
    @NotNull
    private Boolean isDeleted = false;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(name="updated_at", nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    public void addUser(String userId) {
        if (!users.contains(userId)) {
            users.add(userId);
        }
    }

    public void removeUser(String userId) {
        users.remove(userId);
    }
}
