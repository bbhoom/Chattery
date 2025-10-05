package com.chat.websocket.chatroom;

import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository ChatRoomRepository;

    public Optional<String> getChatRoomId(
            String senderId,
            String receiverId,
            boolean createNewRoomIfNotExist) {
        return ChatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(Chatroom::getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExist) {
                        var chatId = createChatId(senderId, receiverId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChatId(String senderId, String receiverId) {
        var chatId = String.format("%s_%s", senderId, receiverId);// bhoom_dev(ex chatid)
        Chatroom senderReceiver = Chatroom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .receiverId(receiverId)
                .build();
        Chatroom receiverSender = Chatroom.builder()
                .chatId(chatId)
                .senderId(receiverId)
                .receiverId(senderId)
                .build();// we create two chatrooms for sender and recipient by switching the ids
        ChatRoomRepository.save(senderReceiver);
        ChatRoomRepository.save(receiverSender);
        return chatId;
    }

}
