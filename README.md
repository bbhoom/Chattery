# Real-Time Chat Application

A modern, responsive one-to-one chat application built with Spring Boot, WebSocket, and MongoDB.

![Chat Application](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-Database-green)
## Images


<img width="1336" height="756" alt="image" src="https://github.com/user-attachments/assets/1bff637d-33f9-4153-b7d3-ad393d01f9cd" />

<img width="1890" height="837" alt="image" src="https://github.com/user-attachments/assets/728d82ba-33fa-4912-95af-e6df7c4cf49f" />



## Features

- **Real-time messaging** - Instant message delivery using WebSocket (STOMP protocol)
- **User authentication** - Nickname validation with fullname verification
- **Persistent sessions** - Auto-reconnect after page refresh
- **Chat history** - All messages stored in MongoDB
- **Online status** - See who's currently online
- **Unread message notifications** - Badge counters for new messages
- **Modern UI** - Responsive design with gradient themes and smooth animations
- **Mobile friendly** - Optimized for all screen sizes

## Tech Stack

### Backend
- **Spring Boot** - Application framework
- **Spring WebSocket** - Real-time communication
- **STOMP** - Messaging protocol
- **MongoDB** - NoSQL database for users and messages
- **Lombok** - Boilerplate code reduction

### Frontend
- **Vanilla JavaScript** - No framework dependencies
- **SockJS** - WebSocket fallback support
- **STOMP.js** - STOMP protocol client
- **CSS3** - Modern styling with gradients and animations
- **SessionStorage** - Client-side state persistence

## Prerequisites

- Java 17 or higher
- MongoDB (running on localhost:27017)
- Maven 3.6+
- Modern web browser

## Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/chat-application.git
cd chat-application
```

2. **Configure MongoDB**

Update `application.properties` if your MongoDB is not on default settings:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/chat-app
```

3. **Build the project**
```bash
mvn clean install
```

4. **Run the application**
```bash
mvn spring-boot:run
```

5. **Access the application**

Open your browser and navigate to:
```
http://localhost:8080
```


## API Endpoints

### REST Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users` | Get all online users |
| GET | `/messages/{senderId}/{receiverId}` | Get chat history between two users |
| POST | `/validate-user` | Validate user credentials |

### WebSocket Endpoints

| Destination | Description |
|-------------|-------------|
| `/app/user.addUser` | Register new user connection |
| `/app/user.disconnectUser` | Handle user disconnect |
| `/app/chat` | Send private message |
| `/user/{nickname}/queue/messages` | Subscribe to private messages |

## Usage

1. **First-time login**
   - Enter a unique nickname
   - Enter your full name
   - Click "Enter Chatroom"

2. **Returning user**
   - Enter your existing nickname
   - Enter the same full name used during registration
   - If the full name doesn't match, an error will be displayed

3. **Sending messages**
   - Click on a user from the online users list
   - Type your message in the input field
   - Press Enter or click Send

4. **Notifications**
   - Unread message count appears on user items
   - Badge counter shows number of unread messages
   - Counter clears when you open the chat

## Features in Detail

### Session Persistence
- User credentials saved in sessionStorage
- Automatic reconnection after page refresh
- Chat state restoration (reopens last active chat)

### User Validation
- Prevents nickname hijacking
- Enforces consistent identity (nickname + fullname pair)
- Shows inline error messages for failed authentication

### Real-time Updates
- Instant message delivery via WebSocket
- Live online status updates
- Automatic user list refresh on connection changes

## Configuration

### WebSocket Configuration
```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // Configure STOMP endpoints and message broker
}
```

### MongoDB Collections
- **users** - Stores user information and status
- **chatMessages** - Stores all chat messages
- **chatRooms** - Maps user pairs to chat room IDs

## Troubleshooting

**Problem: Cannot connect to WebSocket**
- Ensure Spring Boot application is running
- Check browser console for connection errors
- Verify WebSocket endpoint is accessible

**Problem: Messages not persisting**
- Check MongoDB connection
- Verify database and collections exist
- Check server logs for errors

**Problem: User validation fails**
- Clear browser sessionStorage
- Verify MongoDB has correct user data
- Check backend validation endpoint logs

## Future Enhancements

- [ ] Group chat support
- [ ] File/image sharing


## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Made with ❤️ using Spring Boot and WebSocket
