package ticTacToe;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    ArrayList<Button> listOfButtons = new ArrayList<>();
    Label textOnTop = new Label("Turn: X");

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

    @Override
    public void start(Stage stage) {

        GameLogic.getInstance().clear();
        textOnTop.setFont(Font.font("Monospaced", 40));
        GridPane playField = new GridPane();
        playField.setPadding(new Insets(10, 10, 10, 10));
        playField.setVgap(5);
        playField.setHgap(5);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridPane.setConstraints(createButton(playField), i, j);
            }
        }
        BorderPane pane = new BorderPane();
        pane.setTop(textOnTop);
        pane.setCenter(playField);

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

    public void disableButtons(ArrayList<Button> buttons) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(true);
        }
    }

    public void calculateWin(ArrayList<Button> listOfButtons) {
        if (GameLogic.getInstance().calculateWin()) {
            textOnTop.setText("The end!");
            disableButtons(listOfButtons);
        } else {
            GameLogic.getInstance().changePlayer();
            textOnTop.setText("Turn: " + GameLogic.getInstance().getCurrentPlayer().value);
        }
    }

    public Button createButton(GridPane playField) {

        Button currentButton = new Button(" ");
        currentButton.setFont(Font.font("Monospaced", 40));
        listOfButtons.add(currentButton);

        currentButton.setOnAction((event) -> {

            int x = GridPane.getRowIndex(currentButton);
            int y = GridPane.getColumnIndex(currentButton);
            if (GameLogic.getInstance().getField(x, y).equals(" ")) {
                String currentPlayer = GameLogic.getInstance().getCurrentPlayer().value;
                currentButton.setText(currentPlayer);
                GameLogic.getInstance().setField(x, y, currentPlayer);
                calculateWin(listOfButtons);

            }

        });

        playField.getChildren().add(currentButton);
        return currentButton;
    }

}
