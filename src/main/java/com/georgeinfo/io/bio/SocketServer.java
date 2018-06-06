package com.georgeinfo.io.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 阻塞式IO模型下的Socket服务端
 *
 * @author George (GeorgeWorld@qq.com)
 */
public class SocketServer {
    public void startup() throws Exception {
        // 服务监听端口
        int port = BIOConfig.PORT;
        ServerSocket server = new ServerSocket(port);
        System.out.println("## Socket服务端正在等待客户连接的到来……");

        //使用线程池，每一个Socket客户端进来的连接对象，都使用一个单独的线程来受理，
        // 为了避免频繁地创建和销毁线程（开辟和清理线程私有内存区域），所以使用线程池，
        // 同时也避免了并发过高时，创建过多线程耗尽资源
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        while (true) {
            Socket socket = server.accept();

            //一旦有新的连接进来，则立刻创建一个线程来受理本次连接的数据流的读写操作，然后把这个线程
            //放到线程池里去执行。
            Runnable runnable = () -> {
                try {
                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = inputStream.read(bytes)) != -1) {
                        // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                        sb.append(new String(bytes, 0, len, "UTF-8"));
                    }
                    System.out.println("## 客户端传送过来的数据是： " + sb);
                    inputStream.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            threadPool.submit(runnable);
        }

    }
}
