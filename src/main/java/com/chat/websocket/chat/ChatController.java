//Code for transfer of text messages between users
package com.chat.websocket.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;
    // makes new queue for user if not or adds to queue

    @MessageMapping("/chat")
    public void proccessMessage(@Payload ChatMessage chatMessage) {// The @Payload annotation tells Spring to map the
                                                                   // incoming message body to this object
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        // eg: bhoom/queue/messages
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),
                "/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .receiverId(savedMsg.getReceiverId())
                        .content(savedMsg.getMessage())
                        .build());
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable String senderId,
            @PathVariable String receiverId) {
        var messages = chatMessageService.findChatMessages(senderId, receiverId);
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, receiverId));
    }
}
