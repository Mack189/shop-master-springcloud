package com.mack.common.utils;

public interface ILock {

    boolean trLock(long timeoutSec);

    void unlock();
}
