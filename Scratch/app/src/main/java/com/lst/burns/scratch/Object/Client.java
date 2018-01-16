package com.lst.burns.scratch.Object;

import java.util.LinkedHashMap;

public class Client {

    public static LinkedHashMap<Integer, Client> sClientMap = new LinkedHashMap<>();

    private static int sCounter;

    private int mId;
    private String mName;
    private ClientCallback mCallback;

    public Client(String name, ClientCallback callback) {
        mId = sCounter++;
        mName = name;
        mCallback = callback;
        sClientMap.put(mId, this);
    }

    public static void disposeAll() {
        sClientMap.clear();
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }

    public synchronized void onPostReceived(Post post) {
        System.out.println("onPostReceived thread:" + Thread.currentThread().getName() );
        mCallback.onNewPost(
                sClientMap.get(post.getReceiverId()),
                sClientMap.get(post.getSenderId()),
                post.getMessage());
    }

    public synchronized void dispose() {
        sClientMap.remove(mId);
    }

    public interface ClientCallback {
        void onNewPost(Client receiver, Client sender, String message);
    }
}