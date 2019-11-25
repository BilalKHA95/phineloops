package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.L;
import model.Level;
import model.Piece;

import java.util.Iterator;

public class PhineLoopsMainGUI extends Application {

    static final int WIDTH = 500;
    static final int HEIGHT = 500;

    static final int GRID_WIDTH = 400;
    static final int GRID_HEIGHT = 400;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        GridPane grid = new GridPane();
        grid.setVgap(0);
        grid.setHgap(0);
        grid.setPadding(new Insets(10));
        grid.setGridLinesVisible(true); // For debugging : to remove
        grid.setMinSize(GRID_WIDTH, GRID_HEIGHT);
        grid.setMaxSize(WIDTH, HEIGHT);
        grid.setAlignment(Pos.CENTER);

        Level model = this.initLevel();
        LevelDrawing view = new LevelDrawing(model);

        view.draw(grid);
        grid.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().add(grid);
        stage.setTitle("Phine Loops Game");
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Phine Loops Game");
        stage.setScene(scene);
        stage.show();
    }

    // Tmp method : for debugging only
    private Level initLevel() {
        int width = 3;
        int height = 9;
        Level lvl = new Level(height, width);
        Piece[][] grid = lvl.getGrid();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[j][i] = new L(2, j, i);
                System.out.println(grid[j][i]);
            }
        }
        System.out.println(grid);

        return lvl;
    }
}