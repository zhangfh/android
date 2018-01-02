package com.lst.burns.pictureediting;

/**
 * 用于自定义View绘画操作之后通知Activity中回退和前进按钮状态改变
 */
public interface IDrawView {

    void setBack(boolean savePath);
    void setNext(boolean deletePath);

}