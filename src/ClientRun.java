import javax.swing.JFrame;

public class ClientRun {
    public static void main(String[] args) {
        Client kaimann;
        kaimann = new Client("127.0.0.1");
        kaimann.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kaimann.startClient();
    }
}
