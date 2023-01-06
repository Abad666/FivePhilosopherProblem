package com.example.fivephilosopherproblemv2;

public class Chopstick {
    private boolean isHeld;

    public synchronized void Acquire()
    {
        isHeld = true;
    }

    public synchronized boolean isAvailable()
    {
        return !isHeld;
    }
    public synchronized void Release()
    {
        isHeld = false;
    }
}
