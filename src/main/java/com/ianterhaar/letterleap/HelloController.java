package com.ianterhaar.letterleap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private Label lblCorrectGuess;

    @FXML
    private Button btnEnter, btnZ, btnX1, btnC, btnV, btnB, btnN, btnM, btnBack, btnA,
            btnS, btnD, btnF, btnG, btnH, btnJ, btnK, btnL, btnQ, btnW, btnE, btnR,
            btnT, btnY, btnU, btnI, btnO, btnP;

    private List<Label> tiles;
    private int currentRow = 0;
    private int currentColumn = 0;
    private final int columns = 5;

    // Example word to guess
    private String wordToGuess;

    private List<String> loadWordsFromFile() {
        List<String> words = new ArrayList<>();

        InputStream inputStream = getClass().getResourceAsStream("/com/ianterhaar/letterleap/data/5letters.txt");

        if (inputStream == null) {
            System.out.println("File not found! Check the path and file location.");
        } else {
            System.out.println("File loaded successfully.");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    words.add(line.trim().toUpperCase());  // Assuming words are in lowercase, convert them to uppercase
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return words;
    }


    private void selectRandomWord() {
        List<String> words = loadWordsFromFile();
        if (!words.isEmpty()) {
            Random random = new Random();
            wordToGuess = words.get(random.nextInt(words.size()));
            System.out.println("Word to Guess: " + wordToGuess); // For debugging purposes
        } else {
            System.out.println("Word list is empty or not loaded!");
        }
    }

    @FXML
    private void initialize() {


        Platform.runLater(() -> {
            tiles = new ArrayList<>();
            tiles.add(tile1); tiles.add(tile2); tiles.add(tile3); tiles.add(tile4); tiles.add(tile5);
            tiles.add(tile6); tiles.add(tile7); tiles.add(tile8); tiles.add(tile9); tiles.add(tile10);
            tiles.add(tile11); tiles.add(tile21); tiles.add(tile31); tiles.add(tile41); tiles.add(tile51);
            tiles.add(tile61); tiles.add(tile71); tiles.add(tile81); tiles.add(tile91); tiles.add(tile101);
            tiles.add(tile111); tiles.add(tile211); tiles.add(tile311); tiles.add(tile411); tiles.add(tile511);
            tiles.add(tile611); tiles.add(tile711); tiles.add(tile811); tiles.add(tile911); tiles.add(tile1011);
            selectRandomWord();

        });
    }

    @FXML
    private void onHelloButtonClick() {
        System.out.println("Enter Button Clicked!");
        System.out.println("Current Row: " + currentRow);
        System.out.println("Current Column: " + currentColumn);

        String guess = getCurrentGuess();
        System.out.println("Current Guess: " + guess);

        checkGuess(guess);

        // Move to next row after checking the guess
//        currentRow++;
        currentColumn = 0;

        System.out.println("Row after increment: " + currentRow);
        System.out.println("Column after reset: " + currentColumn);

        if (isCorrectGuess()) {

            changeCorrectGuessLabel();
            disableButtons();
        }
    }

    @FXML
    private void backButton() {

        if (currentColumn == 0 && currentRow > 0) {
            currentRow--;
            currentColumn = columns - 1;
        } else if (currentColumn > 0) {
            currentColumn--;
        }

        // Remove the letter from the current tile
        Label currentTile = getCurrentTile();
        if (currentTile != null && !currentTile.getText().isEmpty()) {
            currentTile.setText("");
            System.out.println("Removed letter from tile: " + currentTile.getId());
        }
    }

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

    private void addLetterToTile(String letter) {
        Label currentTile = getCurrentTile();
        if (currentTile != null) {
            currentTile.setText(letter);
            System.out.println("Added letter " + letter + " to tile: " + currentTile.getId());
        }
    }

    private void removeLetterFromTile() {

        Label currentTile = getCurrentTile();
        if (currentTile != null) {
            currentTile.setText("");
            System.out.println("Removed letter from current tile: " + currentTile.getId());
        }
    }

    private Label getCurrentTile() {
        int tileIndex = currentRow * columns + currentColumn;
        if (tileIndex < tiles.size()) {
            return tiles.get(tileIndex);
        }
        return null;
    }

    private void moveToNextTile() {
        currentColumn++;
        if (currentColumn >= columns) {
            currentColumn = 0;
            currentRow++;
        }
        System.out.println("Moved to next tile. Current Row: " + currentRow + ", Current Column: " + currentColumn);
    }

    private String getCurrentGuess() {
        StringBuilder guess = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            int tileIndex = currentRow * columns + i -5;
            if (tileIndex < tiles.size()) {
                Label tile = tiles.get(tileIndex);
                guess.append(tile.getText());
            }
        }
        return guess.toString();
    }

    private boolean isCorrectGuess() {

        return wordToGuess.equals(getCurrentGuess());
    }

    private void changeCorrectGuessLabel(){

        lblCorrectGuess.setOpacity(1);
        String hexColor = "#008000";
        Color color = Color.web(hexColor);
        CornerRadii cornerRadii = new CornerRadii(7);
        BackgroundFill backgroundFill = new BackgroundFill(color, cornerRadii, Insets.EMPTY);
        lblCorrectGuess.setBackground(new Background(backgroundFill));
    }

    private void disableButtons() {
        btnEnter.setDisable(true);
        btnZ.setDisable(true);
        btnX1.setDisable(true);
        btnC.setDisable(true);
        btnV.setDisable(true);
        btnB.setDisable(true);
        btnN.setDisable(true);
        btnM.setDisable(true);
        btnBack.setDisable(true);
        btnA.setDisable(true);
        btnS.setDisable(true);
        btnD.setDisable(true);
        btnF.setDisable(true);
        btnG.setDisable(true);
        btnH.setDisable(true);
        btnJ.setDisable(true);
        btnK.setDisable(true);
        btnL.setDisable(true);
        btnQ.setDisable(true);
        btnW.setDisable(true);
        btnE.setDisable(true);
        btnR.setDisable(true);
        btnT.setDisable(true);
        btnY.setDisable(true);
        btnU.setDisable(true);
        btnI.setDisable(true);
        btnO.setDisable(true);
        btnP.setDisable(true);
    }


    private void checkGuess(String guess) {
        for (int i = 0; i < guess.length(); i++) {
            int tileIndex = currentRow * columns + i -5;
            if (tileIndex < tiles.size()) {
                Label tile = tiles.get(tileIndex);
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
}
