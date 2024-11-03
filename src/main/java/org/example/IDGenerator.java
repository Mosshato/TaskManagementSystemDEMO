package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {
    private static AtomicInteger taskIdCounter = new AtomicInteger(1001);

    public static int getNewTaskId() {
        return taskIdCounter.getAndIncrement();
    }
}
