package com.team4.Transpeur.Repositories;

import com.team4.Transpeur.Entities.ChatRoom;
import com.team4.Transpeur.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findById(Long id);
}
