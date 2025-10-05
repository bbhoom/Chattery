package com.chat.websocket.chatroom;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository  extends MongoRepository<Chatroom, String> {
    Optional<Chatroom> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
