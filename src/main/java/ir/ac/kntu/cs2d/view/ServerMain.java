package ir.ac.kntu.cs2d.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ServerMain extends Application {
    public static void main(String[] args) {
        launch(args);
        // TODO
    }

/*    private ServerMain() {
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root,840,700,true);
        primaryStage.setScene(scene);
        primaryStage.show();


        ArrayList<ArrayList<Integer>> mapTable = new ArrayList<>();
        ArrayList<Rectangle> rectangles = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/maps/dust2.txt")))) {
            String line;
            while ((line=reader.readLine()) != null){
                ArrayList<Integer> temp = new ArrayList<>();
                for(int i=0;i<line.length();i++){
                    temp.add(Integer.parseInt(String.valueOf(line.charAt(i))));

                }
                mapTable.add(temp);
            }
        }

        for(int i=0;i<mapTable.size();i++){
            for(int j=0;j<mapTable.get(i).size();j++){

                Rectangle rectangle =new Rectangle(7,7);

                switch ((mapTable.get(i).get(j))){
                    case 0:rectangle.setFill(Color.rgb(255,255,255));break;
                    case 1:rectangle.setFill(Color.rgb(239,228,176));break;
                    case 2:rectangle.setFill(Color.rgb(255,201,14));break;
                    case 3:rectangle.setFill(Color.rgb(185,122,87));break;
                    case 4:rectangle.setFill(Color.rgb(127,127,127));break;
                    case 5:rectangle.setFill(Color.rgb(237,26,34));break;
                    case 7:rectangle.setFill(Color.rgb(186,229,240));break;
                    case 8:rectangle.setFill(Color.rgb(254,181,206));break;
                }
                rectangles.add(rectangle);
                rectangle.setX(7 * j);
                rectangle.setY(7 * i);
            }
        }

//        ParallelCamera camera = new ParallelCamera();
//        camera.setScaleY(0.24);
//        camera.setScaleX(0.24);
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
//            if(event.getCode().equals(KeyCode.RIGHT)){
//                camera.setTranslateX(camera.getTranslateX()+5);
//            } else if(event.getCode().equals(KeyCode.LEFT)){
//                camera.setTranslateX(camera.getTranslateX()-5);
//            }else if(event.getCode().equals(KeyCode.UP)){
//                camera.setTranslateY(camera.getTranslateY()-5);
//            }else if(event.getCode().equals(KeyCode.DOWN)){
//                camera.setTranslateY(camera.getTranslateY()+5);
//            }
//        });
//        scene.setCamera(camera);
//
        root.getChildren().addAll(rectangles);
//        root.getChildren().add(camera);
    }
}

