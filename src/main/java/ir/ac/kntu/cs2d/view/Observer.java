package ir.ac.kntu.cs2d.view;

import ir.ac.kntu.cs2d.presenter.PlayerModel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Observer  {

    static LinkedList<Stage> stages = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> mapTable = new ArrayList<>();

    public static Stage createStage(){
        Stage stage = new Stage();
        stage.show();
        stages.add(stage);
        return stage;
    }
    public static void enterName(Stage stage) throws Exception{
        Image image = new Image(new FileInputStream("./src/main/resources/images/mnm1.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(600);
        imageView.setFitWidth(900);
        TextField UName=new TextField();
        UName.setMaxWidth(100);
        Label userName=new Label("User Name:");
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
                try {
                    PlayerModel.setName(UName.getText());
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
        getMapTable();
        Group root = new Group();
        Scene scene = new Scene(root,840,700,true);
        Stage primaryStage =new Stage();
        primaryStage.setScene(scene);
        ParallelCamera camera = new ParallelCamera();
        camera.setScaleY(0.24);
        camera.setScaleX(0.24);

        scene.setCamera(camera);

        Circle playerPicture = new Circle();
        playerPicture.setRadius(3.5);
        int iniX=0,iniY=0,endX=0,endY=0;
        for(int i=0;i<mapTable.size();i++){
            for(int j=0;j<mapTable.get(i).size();j++){
                if(mapTable.get(i).get(j) == 7){
                    iniX = i;
                    iniY = j;
                    break;
                }
            }
        }
        for(int i=0;i<mapTable.size();i++) {
            for (int j = 0; j < mapTable.get(i).size(); j++) {
                if (mapTable.get(i).get(j) == 7 && mapTable.get(i).get(j+1) != 7) {
                    endX = i;
                }
                if (mapTable.get(i).get(j) == 7 && mapTable.get(i+1).get(j) != 7) {
                    endY = j;
                    break;
                }
            }
        }

        //int randomTableX = new Random().nextInt(endX-iniX)+iniX;
        //int randomTableY = new Random().nextInt(endY-iniY)+iniY;

        System.out.println(endX+" "+iniX+" "+endY+" "+iniY);

        setPlayerPos(playerPicture,30,50);

        //setPlayerPos(randomTableX,randomTableY);
        playerPicture.setFill(new ImagePattern(new Image("file:src/main/resources/images/ct-top.png")));

        ImageCursor cursor = new ImageCursor(new Image("file:src/main/resources/images/aim-cursor.png"));
        scene.setCursor(cursor);

        camera.setTranslateX(playerPicture.getCenterX()-scene.getWidth()*0.12);
        camera.setTranslateY(playerPicture.getCenterY()-scene.getHeight()*0.12);


        ArrayList<Rectangle> rectangles = new ArrayList<>();
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

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            boolean moved = false;
            if(event.getCode().equals(KeyCode.RIGHT)){
                if(mapTable.get(PlayerModel.getTableY()).get(PlayerModel.getTableX()+1)<1 || mapTable.get(PlayerModel.getTableY()).get(PlayerModel.getTableX()+1)>4){
                    setPlayerPos(playerPicture,PlayerModel.getTableX()+1,PlayerModel.getTableY());
                    moved = true;
                }

            } else if(event.getCode().equals(KeyCode.LEFT)){
                if(mapTable.get(PlayerModel.getTableY()).get(PlayerModel.getTableX()-1)<1 || mapTable.get(PlayerModel.getTableY()).get(PlayerModel.getTableX()-1)>4){
                    setPlayerPos(playerPicture,PlayerModel.getTableX()-1,PlayerModel.getTableY());
                    moved = true;
                }
            }else if(event.getCode().equals(KeyCode.UP)){
                if(mapTable.get(PlayerModel.getTableY()-1).get(PlayerModel.getTableX())<1 || mapTable.get(PlayerModel.getTableY()-1).get(PlayerModel.getTableX())>4){
                    setPlayerPos(playerPicture,PlayerModel.getTableX(),PlayerModel.getTableY()-1);
                    moved = true;
                }
            }else if(event.getCode().equals(KeyCode.DOWN)){
                if(mapTable.get(PlayerModel.getTableY()+1).get(PlayerModel.getTableX())<1 || mapTable.get(PlayerModel.getTableY()+1).get(PlayerModel.getTableX())>4){
                    setPlayerPos(playerPicture,PlayerModel.getTableX(),PlayerModel.getTableY()+1);
                    moved = true;
                }
            }
            System.out.println(mapTable.get(PlayerModel.getTableY()).get(PlayerModel.getTableX()));
            if(moved){
                camera.setTranslateX(playerPicture.getCenterX()-scene.getWidth()*0.12);
                camera.setTranslateY(playerPicture.getCenterY()-scene.getHeight()*0.12);
            }
        });
//        scene.setCamera(camera);
//
        root.getChildren().addAll(rectangles);
        root.getChildren().addAll(playerPicture);
        root.getChildren().add(camera);
        return scene;
    }

    public static void getMapTable(){
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/maps/dust2.txt")))) {
            String line;
            while ((line=reader.readLine()) != null){
                ArrayList<Integer> temp = new ArrayList<>();
                for(int i=0;i<line.length();i++){
                    temp.add(Integer.parseInt(String.valueOf(line.charAt(i))));

                }
                mapTable.add(temp);
            }
        } catch (Exception ignored){}
    }

    public static void setPlayerPos(Circle playerPicture,int x,int y){
        PlayerModel.setTableX(x);
        PlayerModel.setTableY(y);
        PlayerModel.setX(x*7);
        PlayerModel.setY(y*7);
        playerPicture.setCenterX(PlayerModel.getX()+2.5);
        playerPicture.setCenterY(PlayerModel.getY()+2.5);
    }
}
