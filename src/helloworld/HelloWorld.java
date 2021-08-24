/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author bdegryse
 */
public class HelloWorld extends Application {
    Image upArrow = new Image(getClass().getResource("upArrow.png").toString());
    Image downArrow = new Image(getClass().getResource("downArrow.png").toString());
    Image victory = new Image(getClass().getResource("victory.jpeg").toString());
    TextField userInput = new TextField();
    ImageView picture = new ImageView();
    
    Random rnd = new Random();
    int answer = rnd.nextInt(99) + 1;//1-99
    int guesses = 0;
    
    @Override
    public void start(Stage primaryStage) {
        picture.setFitHeight(200);
        picture.setPreserveRatio(true);
        userInput.setAlignment(Pos.CENTER);
        
        Button btn = new Button();
        Button close = new Button();
        Button restart = new Button();
        btn.setText("Guess");
        close.setText("Close");
        restart.setText("Restart");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            guesses++;
            CheckGuess(userInput.getText());
        }
        });
        close.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            primaryStage.close();
        }
        });
        restart.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            answer = rnd.nextInt(99) + 1;
            guesses = 0;
            userInput.setDisable(false);
        }
        });
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setAlignment(Pos.CENTER);
        
        hb.getChildren().addAll(restart, btn, close);
        
        root.setSpacing(20);
        root.getChildren().addAll(picture, userInput, hb);
        
        Scene scene = new Scene(root, 400, 300);
        
        primaryStage.setTitle("Number Guesser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void CheckGuess(String guessString) {
        try {
            int guess = Integer.parseInt(guessString);
            if (guess > answer) {
                picture.setImage(downArrow);
            }
            else if (guess < answer) {
                picture.setImage(upArrow);
            }
            else {
                picture.setImage(victory);
                userInput.setText(guesses + " guesses");
                userInput.setDisable(true);
            }
        }
        catch (Exception e) {
            userInput.setText("Please enter numbers only");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
