package client;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame {
    private JTextField userInputText;
    private JTextArea chatWindow;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String message = "";
    private String serverIp;
    private Socket socket;

    //конструктор
    public Client(String host){
        super("Клиентская часть");
        serverIp = host;
        userInputText = new JTextField();
        userInputText.setEditable(false);
        userInputText.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sendMessage(e.getActionCommand());
                        userInputText.setText("");
                    }
                }
        );
        add(userInputText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        chatWindow.setBackground(Color.LIGHT_GRAY);
        add(new JScrollPane(chatWindow), BorderLayout.CENTER);
        setSize(300, 600);
        setVisible(true);
    }
    //запуск клиента
    public void startClient(){
        try {
            connectToServer();
            setUpStreams();
            whileChatting();
        }
        catch (EOFException eofException){
            showMessage("\nКлиент оборвал соединение!!!");
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }
    //подключаемся к серверу
    private void connectToServer() throws IOException{
        showMessage("Пытаемся подключиться, ожидай...");
        socket = new Socket(InetAddress.getByName(serverIp), 7777);
        showMessage("\nТеперь ты подключен к: " + socket.getInetAddress().getHostName());
    }
    //настройка потоков для отправки и получения сообщений
    private void setUpStreams()throws IOException{
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.flush();
        inputStream = new ObjectInputStream(socket.getInputStream());
        showMessage("\nДружище, твои потоки готовы к работе!!!");
    }
    //обработка данных во время общения
    private void whileChatting() throws IOException{
        readyToType(true);
        do{
            try {
                message = (String) inputStream.readObject();
                showMessage("\n" + message);
            }
            catch (ClassNotFoundException classNotFoundException){
                showMessage("\nНепонятно!!!");
            }
        }
        while (!message.equals("СЕРВЕР - *"));
    }
    //закрытие потоков и сокетов
    private void closeConnection() {
        showMessage("\nЗакрываем соединение...");
        readyToType(false);
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    //отправка сообщений на сервер
    private void sendMessage(String message){
        try {
            outputStream.writeObject("КЛИЕНТ - " + message);
            outputStream.flush();
            showMessage("\nКЛИЕНТ - " + message);
        }catch (IOException ioException){
            chatWindow.append("\nЧто-то пошло не так во время отправки сообщения...");
        }
    }
    //обновление окна чата
    private void showMessage(final String text){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatWindow.append(text);
                    }
                }
        );
    }
    //установка прав на ввод текста
    private void readyToType(final boolean tof){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        userInputText.setEditable(tof);
                    }
                }
        );
    }
}