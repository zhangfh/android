package com.lst.burns.scratch.http;

import android.content.Context;
import android.util.Log;

import com.lst.burns.scratch.util.Constant;

import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// use httpcore
// compile files('libs/httpcore-4.3.2.jar')

public class RequestListenerThread extends Thread{
    private static final String tag = RequestListenerThread.class.getSimpleName();

    private int port;
    private String webRoot;

    private ServerSocket serversocket;
    private ExecutorService executorService;

    private HttpParams params;
    private HttpService httpService;
    private Context context;

    public RequestListenerThread(int port, String webRoot, Context context) {
        this.port = port;
        this.webRoot = webRoot;
        this.context = context;

        executorService = Executors.newCachedThreadPool();
    }

    public void destroy() {
        try {
            this.serversocket.close();
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        initHttpServer();

        while (!Thread.interrupted()) {
            try {
                Log.d("ZFH","ready to accept socket");
                Socket socket = serversocket.accept();
                DefaultHttpServerConnection connection = new DefaultHttpServerConnection();
                Log.d("ZFH","ready to bind socket");
                connection.bind(socket, params);

                Thread thread = new WorkThread(httpService, connection);
                thread.setDaemon(true);
                executorService.execute(thread);

            } catch (IOException e) {
                e.printStackTrace();
                this.interrupt();
            }
        }
    }
    private void initHttpServer() {
        try {
            serversocket = new ServerSocket(port);
            serversocket.setReuseAddress(true);

            //设置 http 参数
            params = new BasicHttpParams();
            params.setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8 * 1024)
                    .setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5 * 1000)
                    .setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK,
                            false)
                    .setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true)
                    .setParameter(CoreProtocolPNames.ORIGIN_SERVER, "HttpComponents/1.1");

            BasicHttpProcessor httpProcessor = new BasicHttpProcessor();//http协议处理器
            httpProcessor.addInterceptor(new ResponseDate());//http协议拦截器，响应日期
            httpProcessor.addInterceptor(new ResponseServer());//响应服务器
            httpProcessor.addInterceptor(new ResponseContent());//响应内容
            httpProcessor.addInterceptor(new ResponseConnControl());//响应连接控制

            //http请求处理程序解析器
            HttpRequestHandlerRegistry registry = new HttpRequestHandlerRegistry();

            //http请求处理程序，HttpFileHandler继承于HttpRequestHandler（http请求处理程序)
            registry.register(Constant.Http.BROWSE, new FileBrowseHandler(webRoot,context));

            httpService = new HttpService(httpProcessor,
                    new DefaultConnectionReuseStrategy(), new DefaultHttpResponseFactory());
            httpService.setParams(params);
            httpService.setHandlerResolver(registry);//为http服务设置注册好的请求处理器。

        } catch (IOException e) {
            e.printStackTrace();
            this.interrupt();
        }
    }
}