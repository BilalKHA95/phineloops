package view;

import controller.RotationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Level;
import model.pieces.Piece;
import view.pieces.PieceDrawing;

import java.io.FileNotFoundException;

/**
 * Produces a visual representation of the game level.
 */
public class LevelDrawing {

    private Level m_model;
    private GridPane grid;

    public LevelDrawing(Level model, Scene scene, boolean solverApplied) {
        this.m_model = model;
        this.grid = new GridPane();
        this.grid.setVgap(0);
        this.grid.setHgap(0);
        this.grid.setGridLinesVisible(true); // For debugging : to remove
        this.grid.setAlignment(Pos.CENTER);
        this.grid.prefWidthProperty().bind(scene.widthProperty());
        this.grid.prefHeightProperty().bind(scene.heightProperty());
        this.draw(this.grid, scene);

        if (!solverApplied) {
            this.grid.setOnMouseClicked(new RotationController(this.m_model, this));
        }

        this.grid.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public GridPane getGridPane() {
        return this.grid;
    }

    /**
     * Attributes an angle that corresponds to the given orientation piece.
     * @param iv ImageView in which orientation has to be set.
     * @param orientation Orientation to attribute to the image of the piece.
     */
    private void setOrientation(ImageView iv, int orientation) {
        // Based on the property in which orientations for any piece are ordered.
        // The increment of the orientation code (int) is equivalent to a rotation of 90 degrees to the right.
        iv.setRotate(iv.getRotate() + 90 * orientation);
    }

    /**
     * Init the visual representation of the level game.
     * @param grid GridPane that will be filled with drawing of pieces.
     * @param scene Scene that contains all elements.
     * @throws FileNotFoundException
     */
    public void draw(GridPane grid, Scene scene) {
        PieceDrawing iv;

        for (int i = 0; i < this.m_model.getGrid().length; i++) {
            for (int j = 0; j < this.m_model.getGrid()[0].length; j++) {
                Piece currentPiece = this.m_model.getGrid()[i][j];
                // Adding image view for each non empty piece
                if (currentPiece.getId() != 0) {
                    iv = currentPiece.createDrawing();
                    this.setOrientation(iv, currentPiece.getOrientation());

                    // Dynamic resizing of pieces
                    iv.fitWidthProperty().bind(scene.widthProperty().divide(this.m_model.getGrid()[0].length + 1));
                    iv.fitHeightProperty().bind(scene.heightProperty().divide(this.m_model.getGrid().length + 1));

                    iv.setSmooth(true);
                    iv.setPreserveRatio(true);
                    iv.setPickOnBounds(true);
                    iv.setOpacity(0.9);

                    GridPane.setRowIndex(iv, i);
                    GridPane.setColumnIndex(iv, j);
                    grid.add(iv, currentPiece.getColumn_number(), currentPiece.getLine_number());

                    currentPiece.addObserver(iv);
                }
            }
        }
    }

    public void solvedSituation() {
        System.out.println("SOLVED !!!");
        ImageView iv;
        this.grid.setGridLinesVisible(false);
        for (Node n : this.grid.getChildren()) {
            iv = (ImageView) n;
            iv.setOpacity(0.5);
        }
    }

    public void unsolvedSituation() {
        this.grid.setGridLinesVisible(true);
    }
}
