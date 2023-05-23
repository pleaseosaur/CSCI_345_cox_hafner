
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DisplayGame {
    private ComboBox<Integer> playerSelect;
    private Button startButton;
    private Scene scene;

    public DisplayGame() {
        Image boardImage = new Image("/image/board.jpg");
        ImageView boardView = new ImageView(boardImage);
        StackPane root = new StackPane(boardView);

        scene = new Scene(root, 1200, 900);
    }

    public Scene getScene() {
        return scene;
    }

    public void startGame(GameManager manager) {
        playerSelect = new ComboBox<>();
        playerSelect.getItems().addAll(2, 3, 4, 5, 6, 7, 8);
        playerSelect.setValue(2);
        playerSelect.setLayoutX(500);
        playerSelect.setLayoutY(500);

        startButton = new Button("Start Game");
        startButton.setLayoutX(500);
        startButton.setLayoutY(550);
        startButton.setOnAction(e -> {
            manager.setupGame(playerSelect.getValue());
        });

        StackPane root = (StackPane) scene.getRoot();
        root.getChildren().addAll(playerSelect, startButton);
    }
}
