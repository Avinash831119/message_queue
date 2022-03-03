package com.example.util;

import java.util.concurrent.atomic.AtomicLong;

public class Util {

    private static final AtomicLong TS = new AtomicLong();

    public static long getUnique64BitNumber() {
        long micros = System.currentTimeMillis() * 1000;
        for ( ; ; ) {
            long value = TS.get();
            if (micros <= value)
                micros = value + 1;
            if (TS.compareAndSet(value, micros))
                return micros;
        }
    }
}
