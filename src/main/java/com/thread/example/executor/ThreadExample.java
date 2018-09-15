package com.thread.example.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.scheduling.concurrent.DefaultManagedAwareThreadFactory;

import java.util.concurrent.*;

/**
 * @author peng.li
 */
@Slf4j
public class ThreadExample{

    private volatile static ThreadExample threadExample;


    private ThreadExample(){

    }

    public static ThreadExample getInstance() {
        if (threadExample == null) {
            synchronized (ThreadExample.class) {
                if (threadExample == null) {
                    threadExample = new ThreadExample();
                }
            }
        }
        return threadExample;
    }


    public static void main( String[] args ) {

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                ThreadExample threadExample;
                for (int i = 0; i < 10; i++){

                    log.info("create object class num : {} , object class : {}",i,threadExample = getInstance());
                }
            }
        }).start();

    }

}
