package com.example.fivephilosopherproblemv2;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//Hierarchy solution.

import javafx.scene.text.Text;

import java.util.concurrent.ThreadLocalRandom;

import static com.example.fivephilosopherproblemv2.FivePhilosopherProblem.update;

public class Philosopher extends Thread {

    private static final int MIN_ACTION_TIME = 500;
    private static final int MAX_ACTION_TIME = 1000;


    public static Chopstick[] availableChopsticks = new Chopstick[5];

    private final Text philosopherName;
    public boolean isEating;
    public final Circle philosopherCircle;
    private int leftChopstickIndex;
    private int rightChopstickIndex;

    public Philosopher(Text philosopherName, Circle philosopherCircle, int leftChopstickIndex, int rightChopstickIndex, Chopstick chopArray[]) {
        this.philosopherName = philosopherName;
        this.philosopherCircle = philosopherCircle;
        this.leftChopstickIndex = leftChopstickIndex;
        this.rightChopstickIndex = rightChopstickIndex;
        this.availableChopsticks = chopArray;

    }

public void run()
{
    while(true) {
        think();
        update();

        if (availableChopsticks[leftChopstickIndex].isAvailable() && availableChopsticks[rightChopstickIndex].isAvailable()) {
            eat();
            update();
        }
    }


}

private void think()
{
    try
    {
        isEating=false;

        System.out.println(this.philosopherName.getText().toString()+" is thinking.");
        this.philosopherCircle.setFill(Color.RED);
        //Simulate thinking
        Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_ACTION_TIME, MAX_ACTION_TIME));


    } catch(InterruptedException e)
    {
        //do nothing
    }

}

private void eat()
{
    try
    {
        isEating=true;
        if(leftChopstickIndex < rightChopstickIndex)
        {
            System.out.println(this.philosopherName.getText().toString()+" acquires the left chopstick of index "+leftChopstickIndex);
            availableChopsticks[leftChopstickIndex].Acquire();
            System.out.println(this.philosopherName.getText().toString()+" acquires the right chopstick of index "+rightChopstickIndex);
            availableChopsticks[rightChopstickIndex].Acquire();
        }
        else
        {
            System.out.println(this.philosopherName.getText().toString()+" acquires the right chopstick of index "+rightChopstickIndex);
            availableChopsticks[rightChopstickIndex].Acquire();
            System.out.println(this.philosopherName.getText().toString()+" acquires the left chopstick of index "+leftChopstickIndex);
            availableChopsticks[leftChopstickIndex].Acquire();
        }
        System.out.println(this.philosopherName.getText().toString()+" is eating.");
        this.philosopherCircle.setFill(Color.GREEN);
        Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_ACTION_TIME, MAX_ACTION_TIME));
        if(leftChopstickIndex < rightChopstickIndex) {
            System.out.println(this.philosopherName.getText().toString()+" releases the right chopstick of index "+rightChopstickIndex);
            availableChopsticks[rightChopstickIndex].Release();
            System.out.println(this.philosopherName.getText().toString()+" releases the left chopstick of index "+leftChopstickIndex);
            availableChopsticks[leftChopstickIndex].Release();
        }
        else
        {
            System.out.println(this.philosopherName.getText().toString()+" releases the left chopstick of index "+rightChopstickIndex);
            availableChopsticks[leftChopstickIndex].Release();
            System.out.println(this.philosopherName.getText().toString()+" releases the right chopstick of index "+leftChopstickIndex);
            availableChopsticks[rightChopstickIndex].Release();
        }

    }
    catch(InterruptedException e)
    {
        //Nothing.
    }
}


}
