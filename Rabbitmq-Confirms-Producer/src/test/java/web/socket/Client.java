package web.socket;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/8/4 20:26
 **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // 创建 Socket 并连接到服务器
            Socket socket = new Socket("localhost", 8000);
            System.out.println("Connected to server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            String message;
            // 从控制台读取消息并发送给服务器
            while ((message = consoleIn.readLine()) != null) {
                out.println(message);
                // 接收服务器回复的消息并打印到控制台
                System.out.println("Server: " + in.readLine());
            }

            consoleIn.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
