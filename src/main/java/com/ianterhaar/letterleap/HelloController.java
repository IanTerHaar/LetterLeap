package com.ianterhaar.letterleap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML
    private Label tile1, tile2, tile3, tile4, tile5;
    @FXML
    private Label tile6, tile7, tile8, tile9, tile10;
    @FXML
    private Label tile11, tile21, tile31, tile41, tile51;
    @FXML
    private Label tile61, tile71, tile81, tile91, tile101;
    @FXML
    private Label tile111, tile211, tile311, tile411, tile511;
    @FXML
    private Label tile611, tile711, tile811, tile911, tile1011;

    @FXML
    private Button btnEnter, btnZ, btnX1, btnC, btnV, btnB, btnN, btnM, btnBack, btnA,
            btnS, btnD, btnF, btnG, btnH, btnJ, btnK, btnL, btnQ, btnW, btnE, btnR,
            btnT, btnY, btnU, btnI, btnO, btnP;

    private int currentRow = 0;
    private int currentColumn = 0;

    // Example word to guess
    private String wordToGuess = "HELLO";

    @FXML
    private void onHelloButtonClick() {
        System.out.println("Enter Button Clicked!");
        System.out.println("Current Row: " + currentRow);
        System.out.println("Current Column: " + currentColumn);

        String guess = getCurrentGuess();
        System.out.println("Current Guess: " + guess);

        checkGuess(guess);
        currentRow++; // Increment the row after checking the guess
        currentColumn = 0; // Reset the column after checking the guess

        System.out.println("Row after increment: " + currentRow);
        System.out.println("Column after reset: " + currentColumn);
    }

    // Method to handle button clicks
    @FXML
    private void onButtonClick(ActionEvent event) {
        Button source = (Button) event.getSource();
        String letter = source.getText();
        System.out.println("Button Clicked: " + letter);

        // Add letter to current tile
        addLetterToTile(letter);

        // Move to the next tile
        moveToNextTile();
    }

    // Method to add a letter to the current tile
    private void addLetterToTile(String letter) {
        Label currentTile = getCurrentTile();
        if (currentTile != null) {
            currentTile.setText(letter);
            System.out.println("Added letter " + letter + " to tile: " + currentTile.getId());
        }
    }

    // Method to get the current tile
    private Label getCurrentTile() {
        String tileId = "tile" + ((currentRow * 5) + currentColumn + 1);
        System.out.println("Current Tile ID: " + tileId);
        return (Label) btnEnter.getScene().lookup("#" + tileId);
    }

    // Method to move to the next tile
    private void moveToNextTile() {
        currentColumn++;
        if (currentColumn >= 5) {
            currentColumn = 0;
        }
        System.out.println("Moved to next tile. Current Column: " + currentColumn);
    }

    // Method to get the current guess
    private String getCurrentGuess() {
        StringBuilder guess = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            Label tile = (Label) btnEnter.getScene().lookup("#tile" + (currentRow * 5 + i + 1));
            guess.append(tile.getText());
        }
        return guess.toString();
    }

    // Method to check the guess
    private void checkGuess(String guess) {
        for (int i = 0; i < guess.length(); i++) {
            Label tile = (Label) btnEnter.getScene().lookup("#tile" + (currentRow * 5 + i + 1));
            if (guess.charAt(i) == wordToGuess.charAt(i)) {
                tile.setStyle("-fx-background-color: green;");
            } else if (wordToGuess.contains(String.valueOf(guess.charAt(i)))) {
                tile.setStyle("-fx-background-color: yellow;");
            } else {
                tile.setStyle("-fx-background-color: gray;");
            }
        }
    }
}
