package com.anything.demo.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class ClientEx {

    public static void main(String[] args){
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);
        try {
            socket = new Socket("localhost", 9999);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                System.out.print("❤️ >> ");
                String outMsg = sc.nextLine();
                out.write(outMsg + "\n");
                out.flush();

                if("bye".equalsIgnoreCase(outMsg)){
                    log.info("❤️ Stop");
                    break;
                }

                String inMsg = in.readLine();
                System.out.println("💜 :"+inMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sc.close();
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
