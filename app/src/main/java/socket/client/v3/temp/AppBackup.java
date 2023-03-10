/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package socket.client.v3.temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import socket.client.v3.App;

public class AppBackup {

    Socket socket;
    BufferedReader br, keyboard;
    BufferedWriter bw;

    class ReadThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    String clientInput = br.readLine();
                    System.out.println("받은 메시지 : " + clientInput);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public AppBackup() {
        try {
            socket = new Socket("192.168.200.41", 10000);

            bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            keyboard = new BufferedReader(
                    new InputStreamReader(System.in));

            Thread t1 = new Thread(new ReadThread());
            t1.start();

            // 메인 쓰레드 : 쓰기
            while (true) {
                // 메인 쓰레드는 쓰기 실행(메시지 전송)
                String keyboardInput = keyboard.readLine();
                bw.write(keyboardInput + "\n"); // 버퍼에 쓰기
                bw.flush(); // 전송
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new App();
    }
}

// 1. 소켓 생성 - (서버 연결)

// 2. 쓰기 버퍼 생성

// 3. 읽기 버퍼 생성

// 4. 키보드 읽기 버퍼 생성

// 5. 메인쓰레드는 메시지 전송

// 6. 읽기쓰레드는 메시지 받기