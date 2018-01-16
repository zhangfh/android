package com.lst.burns.scratch.Interface;

public interface ICounterService {
    public  void startCounter(int intVal, ICounterCallback callback);
    public void stopCounter();
}