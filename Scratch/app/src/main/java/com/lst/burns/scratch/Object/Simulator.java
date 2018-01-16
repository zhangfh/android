package com.lst.burns.scratch.Object;

import android.util.Log;

import java.util.Random;

public class Simulator  implements Runnable {

    private PostOffice mPostOffice;
    private Client.ClientCallback mCallback;
    private Random mRandom;
    private Thread mThread;
    private boolean controller;

    public Simulator(PostOffice postOffice, Client.ClientCallback callback) {
        mPostOffice = postOffice;
        mCallback = callback;
        mRandom = new Random(System.currentTimeMillis());
        mThread = new Thread(this);
        controller = true;
    }

    public Simulator createClients(int num) {
        for (int i = 0; i < num; i++) {
            try {
                mPostOffice.register(new Client("BOT " + i, mCallback));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public synchronized void start() {
        if (!mThread.isAlive())
            mThread.start();
    }

    @Override
    public void run() {
        System.out.println("start mThread");
        System.out.println("current thread:" + Thread.currentThread().getName() );
        controller = true;
        while (controller) {

            Log.i("ZFH","Client.sClientMap.size() " + Client.sClientMap.size());
            int client1Id = mRandom.nextInt(Client.sClientMap.size());//sender
            int client2Id = mRandom.nextInt(Client.sClientMap.size());//receiver
            while (client1Id == client2Id) {
                client2Id = mRandom.nextInt(Client.sClientMap.size());
            }

            System.out.println("cliten1:" + client1Id + " client2:" + client2Id);
            try {
                mPostOffice.sendPost(new Post(client1Id, client2Id, getRandomMessage()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private String getRandomMessage() {
        int val = mRandom.nextInt(10);

        switch (val) {
            case 0:
                return "Happy Christmas!";
            case 1:
                return "How are you buddy?";
            case 2:
                return "I am so proud of you!";
            case 3:
                return "It's holiday hahaha!";
            case 4:
                return ":P";
            case 5:
                return "LOL!";
            case 6:
                return "Wow!";
            case 7:
                return "Bugger off!";
            case 8:
                return "I love you!";
            case 9:
                return "Go to hell :>";
            default:
                return "Hmm";
        }
    }

    public void stop() {
        controller = false;
        Client.disposeAll();
    }
}