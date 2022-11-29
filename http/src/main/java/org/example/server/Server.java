package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        System.out.println("server is running...");
        for (; ; ) {
            Socket socket = ss.accept();
            System.out.println("connected from" + socket.getRemoteSocketAddress());
            Thread thread = new Handler(socket);
            thread.start();
        }
    }
}

class Handler extends Thread {
    Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream inputStream = this.socket.getInputStream()) {
            try (OutputStream outputStream = this.socket.getOutputStream()) {
                handle(inputStream, outputStream);
            }
        } catch (Exception e) {
            try {
                this.socket.close();
            } catch (IOException ioe) {
            }
            System.out.println("client disconnected.");
        }
    }

    private void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
        System.out.println("=============== handle begin ===============");
        System.out.println("Process new http request...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        //处理HTTP请求
        boolean requestOk = false;
        String first = reader.readLine();
        System.out.println("first：" + first);
        if (first.startsWith("GET / HTTP/1.")) {
            requestOk = true;
        }
        for (; ; ) {
            String header = reader.readLine();
            if (header.isEmpty()) {//读取到空行时，HTTP Header读取完毕
                break;
            }
            System.out.println(header);
        }
        System.out.println(requestOk ? "Response OK" : "Response Error");
        if (!requestOk) {
            //发送错误响应
            writer.write("404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            String data = "<html><body><h1>Hello World!</h1></body></html";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n");//空行标识Header和Body的分隔
            writer.write(data);
            writer.flush();
        }
        System.out.println("=============== handle end ===============");
    }
}
