package com.example.chesspiece3d;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Scale;

public class BlackPawn3D extends Group implements Black {
    private ObjModelImporter objImporter = new ObjModelImporter();
    private HelloController helloController;
    private boolean myTurn = false;

    public BlackPawn3D(HelloController helloController) {
        this.helloController = helloController;
        objImporter.read(getClass().getResource("/whitepawn1.obj"));
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
        this.translateZProperty().set(15);
        this.translateYProperty().set(10);
        this.translateXProperty().set(5);
    }
}