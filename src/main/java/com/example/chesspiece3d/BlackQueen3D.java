package com.example.chesspiece3d;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

public class BlackQueen3D extends Group implements Black, Queen3D {
    private ObjModelImporter objImporter = new ObjModelImporter();
    private HelloController helloController;
    private boolean myTurn = false;

    public BlackQueen3D(HelloController helloController) {
        this.helloController = helloController;
        objImporter.read(getClass().getResource("/queen.obj"));
        MeshView[] meshView2 = objImporter.getImport();
        objImporter.close();
        this.getChildren().addAll(meshView2);


        this.getTransforms().addAll(
                new Scale(25, 25, 25)
        );
        this.translateZProperty().set(15);
        this.translateYProperty().set(10);
        this.translateXProperty().set(-2);
        Rotate rotatePieceZ = new Rotate(3, Rotate.Z_AXIS);
        this.getTransforms().add(rotatePieceZ);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.gray(0.4));
        for (Node node : this.getChildren()) {
            if (node instanceof MeshView) {
                ((MeshView) node).setMaterial(material);
            }
        }

        this.setOnMouseEntered(event -> mouseEntered());
        this.setOnMouseExited(event -> mouseExited());
    }

    public void mouseEntered(){
        if(myTurn) {
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.gray(0.6));
            for (Node node : this.getChildren()) {
                if (node instanceof MeshView) {
                    ((MeshView) node).setMaterial(material);
                }
            }
        }
    }

    public void mouseExited(){
        if(myTurn) {
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.gray(0.4));
            for (Node node : this.getChildren()) {
                if (node instanceof MeshView) {
                    ((MeshView) node).setMaterial(material);
                }
            }
        }
    }

    public void changePlayersTurn(boolean change){
        myTurn = change;
    }

    public void restorePosition(){
        this.getTransforms().addAll(
                new Scale(25, 25, 25)
        );
        this.translateZProperty().set(15);
        this.translateYProperty().set(10);
        this.translateXProperty().set(-2);
        Rotate rotatePieceZ = new Rotate(3, Rotate.Z_AXIS);
        this.getTransforms().add(rotatePieceZ);
    }
}