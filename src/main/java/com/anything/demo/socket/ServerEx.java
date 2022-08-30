package com.anything.demo.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class ServerEx {
    public static void main(String[] args){
        ServerSocket server = null;
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);
        try {
            int port = 9999;
            server = new ServerSocket(port);
            log.info("Server Connect...{}", port);

            socket = server.accept();
            log.info("Client Accept");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                String inMsg = in.readLine();
                if("bye".equalsIgnoreCase(inMsg)){ // equalsIgnoreCase : 대소문자 무시
                    log.info("Client Stop");
                    break;
                }

                System.out.println("❤️ :" +inMsg);
                System.out.print("💜 >>");
                String outMsg = sc.nextLine();
                out.write(outMsg + "\n");
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sc.close();
                in.close();
                out.close();
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
