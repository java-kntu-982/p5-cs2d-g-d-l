package ir.ac.kntu.cs2d.view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Observer  {
    public static Stage enterName() throws Exception{
        Stage stage=new Stage();
        Image image = new Image(new FileInputStream("./src/main/resources/images/mnm1.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(600);
        imageView.setFitWidth(900);
        TextField UName=new TextField();
        UName.setMaxWidth(100);
        Label userName=new Label("    User Name:");
        userName.setTextFill(Color.WHITE);
        Button submit=new Button("Submit");
        VBox vBox=new VBox();
        vBox.setSpacing(10);
        vBox.setTranslateX(400);
        vBox.setTranslateY(350);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(userName,UName,submit);
        submit.setOnMouseClicked(mouseEvent -> {
            if (UName.getText() != null && !UName.getText().isEmpty()){
                System.out.println("Hello");
                try {
                    stage.setScene(clientMenu(stage));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
            else {
                System.out.println("Please enter your User Name");
            }
        });

        Group root = new Group(imageView);
        Scene scene = new Scene(root, 900, 600);
        root.getChildren().addAll(vBox);
        stage.setTitle("CS2D");
        stage.setScene(scene);

        return stage;
    }

    public static Scene clientMenu(Stage stage) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("./src/main/resources/images/mnm.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(600);
        imageView.setFitWidth(900);
        Label start=new Label("  New Game");
        start.setFont(Font.font("Cambria",40));
        start.setTranslateX(330);
        start.setTranslateY(450);
        start.setTextFill(Color.WHITE);
        start.setOnMouseEntered(mouseEvent -> {
            start.setScaleX(1.2);
            start.setScaleY(1.2);

        });
        start.setOnMouseExited(mouseEvent -> {
            start.setScaleX(1);
            start.setScaleY(1);
        });
        start.setOnMouseClicked(mouseEvent -> {
            try {
                stage.setScene(startGame());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });




        Group root = new Group(imageView);
        Scene scene = new Scene(root, 900, 600);
        root.getChildren().add(start);
        stage.setTitle("CS2D");
        stage.setScene(scene);
        return scene;

    }
    public static Scene startGame() throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root,840,700,true);
        Stage primaryStage =new Stage();
        primaryStage.setScene(scene);

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
        return scene;
    }


}
