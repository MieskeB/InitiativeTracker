package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    private final int columns = 25;
    private final int width = 512;
    private final int height = 512;

    public void startScene(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(this.columns, this.columns, this.columns, this.columns));
        grid.setVgap(10);
        grid.setHgap(10);

        Scene scene = new Scene(grid, this.width, this.height);


        Text sceneTitle = new Text("Initiative tracker UI");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        {
            Label entities = new Label();


            TextField amountOfEntities = new TextField();
            amountOfEntities.setText("1");
            amountOfEntities.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!amountOfEntities.getText().equals("")) {
                        if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                            amountOfEntities.setText(oldValue);
                        } else {
                            int number = Integer.parseInt(amountOfEntities.getText());
                            if (number < 1 || number > 8) {
                                amountOfEntities.setText(oldValue);
                            }
                        }
                    }
                }
            });
            amountOfEntities.setPrefColumnCount(5);
            GridPane.setConstraints(amountOfEntities, 1, 1);
            grid.getChildren().add(amountOfEntities);
        }

        TextField tbUsername = new TextField();
        tbUsername.setPromptText("Username");
        tbUsername.setPrefColumnCount(5);
        GridPane.setConstraints(tbUsername, 1, 2);
        grid.getChildren().add(tbUsername);


        primaryStage.setTitle("Initiative Tracker UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
