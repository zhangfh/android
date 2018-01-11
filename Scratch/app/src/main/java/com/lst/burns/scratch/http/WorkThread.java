package com.lst.burns.scratch.http;

import android.util.Log;

import org.apache.http.HttpException;
import org.apache.http.HttpServerConnection;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpService;

import java.io.IOException;

public class WorkThread extends Thread {

    private final HttpService httpService;
    private final HttpServerConnection connection;

    public WorkThread(HttpService httpService, HttpServerConnection connection)
    {
        this.httpService = httpService;
        this.connection = connection;
    }

    @Override
    public void run()
    {
        HttpContext context = new BasicHttpContext();
        try
        {
            Log.d("ZFH","workthread handler");
            while (!Thread.interrupted() && connection.isOpen())
            {
                Log.d("ZFH","workthread httpService handleRequest");
                httpService.handleRequest(connection, context);
            }
        }
        catch (HttpException e)
        {
            e.printStackTrace();
            interrupted();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            interrupted();
        }
        finally
        {
            try
            {
                connection.shutdown();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}