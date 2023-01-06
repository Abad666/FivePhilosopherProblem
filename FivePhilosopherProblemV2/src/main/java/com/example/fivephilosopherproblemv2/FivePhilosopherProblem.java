package com.example.fivephilosopherproblemv2;

import com.example.fivephilosopherproblemv2.Chopstick;
import com.example.fivephilosopherproblemv2.Philosopher;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class FivePhilosopherProblem extends Application {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final int TABLE_SIZE = 400;
    private static final int PHILOSOPHER_SIZE = 50;
    static Group layout = new Group();
    static Scene scene = new Scene(layout, TABLE_SIZE, TABLE_SIZE);
    static Circle[] philosopherCircles = new Circle[NUM_PHILOSOPHERS];
    static Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        // Create philosophers and chopsticks

        Chopstick[] chopsticks = new Chopstick[NUM_PHILOSOPHERS];
        //Circle[] philosopherCircles = new Circle[NUM_PHILOSOPHERS];
        Text[] philosopherNames = new Text[NUM_PHILOSOPHERS];

        for(int i = 0; i < NUM_PHILOSOPHERS; i++)
        {
            chopsticks[i] = new Chopstick();
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            int leftChopstickIndex = i;
            int rightChopstickIndex = (i + 1) % NUM_PHILOSOPHERS;
            int x = TABLE_SIZE / 2 + (int) (TABLE_SIZE / 2 * Math.cos(i * 2 * Math.PI / NUM_PHILOSOPHERS));
            int y = TABLE_SIZE / 2 + (int) (TABLE_SIZE / 2 * Math.sin(i * 2 * Math.PI / NUM_PHILOSOPHERS));
            philosopherCircles[i] = new Circle(x, y, PHILOSOPHER_SIZE / 2, Color.RED);
            philosopherNames[i] = new Text(x - PHILOSOPHER_SIZE / 4, y - PHILOSOPHER_SIZE / 4, "Philosopher " + i);
            philosophers[i] = new Philosopher(philosopherNames[i], philosopherCircles[i], leftChopstickIndex, rightChopstickIndex, chopsticks);
        }




        // Create GUI elements for philosophers and chopsticks
        /*Circle[] philosopherCircles = new Circle[NUM_PHILOSOPHERS];

        Text[] philosopherNames = new Text[NUM_PHILOSOPHERS];*/
        Line[] chopstickLines = new Line[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            int x = TABLE_SIZE / 2 + (int) (TABLE_SIZE / 2 * Math.cos(i * 2 * Math.PI / NUM_PHILOSOPHERS));
            int y = TABLE_SIZE / 2 + (int) (TABLE_SIZE / 2 * Math.sin(i * 2 * Math.PI / NUM_PHILOSOPHERS));
            philosopherCircles[i] = new Circle(x, y, PHILOSOPHER_SIZE / 2, Color.RED);
            chopstickLines[i] = new Line(x, y, TABLE_SIZE / 2, TABLE_SIZE / 2);
            philosopherNames[i] = new Text(x - PHILOSOPHER_SIZE / 4, y - PHILOSOPHER_SIZE / 4, "Philosopher " + i);
            root.getChildren().addAll(philosopherCircles[i], chopstickLines[i], philosopherNames[i]);
        }
        // Create and show the scene
        Scene scene = new Scene(root, TABLE_SIZE, TABLE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Five Philosopher Problem");
        primaryStage.show();

        // Start the philosophers
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }

    public static void update() {
        Platform.runLater(() -> {
            ;
            // Update the layout by adding the updated philosopher circles
            //layout.getChildren().clear();
            for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
                if (philosophers[i].isEating) {
                    philosopherCircles[i].setFill(Color.GREEN);
                } else {
                    philosopherCircles[i].setFill(Color.RED);
                }
            }


            // Update the scene with the updated layout


        });
    }

}
