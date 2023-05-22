package com.example.chesspiece3d;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Scale;
/**
 * This class represents the piece for a white bishop.
 * @author Hugo Andersson
 */
public class WhiteBishop3D extends Group implements White{

    /**
     * This importer is used to import .obj-files which contains the piece that has been made in Blender.
     */
    private ObjModelImporter objImporter = new ObjModelImporter();

    /**
     * This boolean is used for the class to know when hovering over the piece should change color of it and when not.
     */
    private boolean myTurn = true;

    /**
     * This constructor reads the .obj-file and adds the meshview array to the group, which then is scaled and moved to be
     * displayed in the center of its parent button. The color of the piece is set to white and methods for hovering over
     * the piece is set.
     */
    public WhiteBishop3D() {
        objImporter.read(getClass().getResource("/bishop.obj"));
        MeshView[] meshView2 = objImporter.getImport();
        objImporter.close();
        this.getChildren().addAll(meshView2);


        this.getTransforms().addAll(
                new Scale(25, 25, 25)
        );
        this.translateZProperty().set(15);
        this.translateYProperty().set(10);
        this.translateXProperty().set(-2);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.GHOSTWHITE);
        for (Node node : this.getChildren()) {
            if (node instanceof MeshView) {
                ((MeshView) node).setMaterial(material);
            }
        }

        this.setOnMouseEntered(event -> mouseEntered());
        this.setOnMouseExited(event -> mouseExited());
    }

    /**
     * This method sets the color of the piece a little darker than the default color, if it is that players turn,
     * which clarifies for the user whose turn it is.
     */
    public void mouseEntered() {
        if (myTurn) {
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.gray(0.8));
            for (Node node : this.getChildren()) {
                if (node instanceof MeshView) {
                    ((MeshView) node).setMaterial(material);
                }
            }
        }
    }


    /**
     * This method sets the color to default once the mouse has left the piece, if it is that players turn.
     */
    public void mouseExited() {
        if (myTurn) {
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.GHOSTWHITE);
            for (Node node : this.getChildren()) {
                if (node instanceof MeshView) {
                    ((MeshView) node).setMaterial(material);
                }
            }
        }
    }

    /**
     * This method is used to control when the methods mouseEntered() and mouseExited() should result in changing of color.
     * @param change the boolean which controls if color change or not.
     */
    public void changePlayersTurn(boolean change){
        myTurn = change;
    }
}
