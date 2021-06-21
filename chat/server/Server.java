package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;


//1) Спросить у подключившегося пользователя его имя.
//        2) Рассылая сообщения необходимо писать имя того, от кого сообщение.
//        3) Не присылать сообщение клиенту который его отправил

public class Server {
    public static void main(String[] args) {
        ArrayList<Socket> clientSockets = new ArrayList<>();
        HashMap<Integer,String> clientSocketsMap = new HashMap();
        try {
            ServerSocket serverSocket = new ServerSocket(9300); // Создаём серверный сокет
            System.out.println("Сервер запущен");
            while (true){ // бесконечный цикл для ожидания подключения клиентов
                System.out.println("Ожидаю подключения клиентов...");
                Socket socket = serverSocket.accept(); // Ожидаем подключения клиента
                clientSockets.add(socket);
                DataInputStream in = new DataInputStream(socket.getInputStream()); // Поток ввода
                DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // Поток вывода
                System.out.println("Клиент подключился");
                out.writeUTF("Введите имя пользователя");
                clientSocketsMap.put(socket.getPort(),in.readUTF());
                System.out.println(clientSocketsMap);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String request = null;
                        String s=null;
                        while (true){
                            try{
                                request = in.readUTF(); // Принимает сообщение от клиента
                                s = ("Клиент '" + clientSocketsMap.get(socket.getPort()) + "' прислал: "
                                        + request.toUpperCase(Locale.ROOT));
                                System.out.println(s);
                               for (Socket clientSocket: clientSockets) { // Перебираем клиентов которые подключенны в настоящий момент
                                   DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream()); // Поток вывода
                                        if (clientSocket.getPort()!=socket.getPort()) {
                                            out.writeUTF(s); // Рассылает принятое сообщение всем клиентам
                                          }
                                }
                            }catch (IOException ex){
                                ex.printStackTrace();
                                clientSockets.remove(socket); // Удаление сокета, когда клиент отключился
                                break;
                            }
                        }
                    }
                });
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
