package com.lh.demo.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private ReentrantLock mLock;

    @Test
    public void testLock() throws Exception {
        mLock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    aTest();
                }
            }).run();
        }
    }

    public void aTest() {
        mLock.lock();
        System.out.println("------aTest------");
    }
}
