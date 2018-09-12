import javax.swing.JFrame;

public class ServerTest {
    public static void main(String[] args) {
        Server iserver = new Server();
        iserver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iserver.startServer();
    }
}
