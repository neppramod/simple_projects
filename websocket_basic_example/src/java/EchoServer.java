
import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


// relative name for
// ws://localhost:8080/EchoChamber/echo
@ServerEndpoint("/echo")
public class EchoServer {
    /**
     * @OnOpen allows us to intercept the the creation of a new session.
     * The session class allows us to send data to the user
     * In the method onOpen, we'll let the user know that the handshake was
     * successful.
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " has opened a connection");
        try {
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {ex.printStackTrace();}
    }
    
    /**
     * When a user sends a message to the server, this method will interceptthe message
     * and allow us to react to it. For now the message is read as a String
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {ex.printStackTrace();}
    }
    
    /**
     * The user closes the connection
     */
    @OnClose
    public void onClose(Session session) {
       System.out.println("Session " + session.getId() + " has ended");
    }
}
