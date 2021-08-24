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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author bdegryse
 */
public class HelloWorld extends Application {
    //sets up images
    Image upArrow = new Image(getClass().getResource("upArrow.png").toString());
    Image downArrow = new Image(getClass().getResource("downArrow.png").toString());
    Image victory = new Image(getClass().getResource("victory.jpeg").toString());
    
    //sets up input box and the image view
    TextField userInput = new TextField();
    ImageView picture = new ImageView();
    
    //gets random number and starts tracking guesses
    Random rnd = new Random();
    int answer = rnd.nextInt(99) + 1;//1-99
    int guesses = 0;
    
    @Override
    public void start(Stage primaryStage) {
        picture.setFitHeight(200);//sets height to be constant
        picture.setPreserveRatio(true);//makes sure the ration is preserved to prevent stretching
        userInput.setAlignment(Pos.CENTER);//centers the input box
        
        //creates the three buttons
        Button btn = new Button();
        Button close = new Button();
        Button restart = new Button();
        //sets their names
        btn.setText("Guess");
        close.setText("Close");
        restart.setText("Restart");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //increases guess count and checks if the guess is correct
            guesses++;
            CheckGuess(userInput.getText());
        }
        });
        close.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //closes the application
            primaryStage.close();
        }
        });
        restart.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //gets a new number to guess, resets guess count, and re-enables the user input
            answer = rnd.nextInt(99) + 1;
            guesses = 0;
            userInput.setDisable(false);
        }
        });
        
        //creates centered vbox
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        
        //creates centered hbox
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setAlignment(Pos.CENTER);
        
        //adds the buttons to the hbox
        hb.getChildren().addAll(restart, btn, close);
        
        //adds the picture, input box, and buttons to the vbox
        root.getChildren().addAll(picture, userInput, hb);
        
        //adds the vbox to the scene
        Scene scene = new Scene(root, 400, 300);
        
        //adds the scene, sets the title, and displays the application
        primaryStage.setTitle("Number Guesser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void CheckGuess(String guessString) {
        try {
            int guess = Integer.parseInt(guessString);//gets the user's input
            if (guess > answer) {
                picture.setImage(downArrow);//displays a down arrow if the guess was too high
            }
            else if (guess < answer) {
                picture.setImage(upArrow);//displays a up arrow if the guess was too low
            }
            else {
                //shows victory image, displays total guesses, and disables the input box
                picture.setImage(victory);
                userInput.setText(guesses + " guesses");
                userInput.setDisable(true);
            }
        }
        catch (Exception e) {
            userInput.setText("Please enter numbers only");//tells the user to enter numbers, displays if they entered anything other than numbers
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
