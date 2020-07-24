package ir.ac.kntu.cs2d.view;

import ir.ac.kntu.cs2d.model.MainGun;
import ir.ac.kntu.cs2d.model.PistolGun;
import ir.ac.kntu.cs2d.model.Solider;
import ir.ac.kntu.cs2d.presenter.PlayerModel;
import javafx.application.Platform;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Observer  {

    static LinkedList<Stage> stages = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> mapTable = new ArrayList<>();
    static Map<Circle,Solider> soliderMap=new HashMap<>();
    private static String name;


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Observer.name = name;
    }

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
        String musicFile = "./src/main/java/music.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(20);
        mediaPlayer.play();
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
                    name=UName.getText();
                    System.out.println("Hello "+ Observer.getName());
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

        setPlayerPos(playerPicture,30,37);

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
        ArrayList<Solider> soliders=new ArrayList<>();
        for (int i=0;i<10;i++){
            Solider solider=new Solider();
            soliders.add(solider);
        }
        for (int i=0;i<5;i++) {
            soliders.get(i).setTerrorist(false);
            PistolGun pistolGun=new PistolGun();
            soliders.get(i).setPistol(pistolGun.createUSP());
        }
        for (int i=5;i<10;i++) {
            soliders.get(i).setTerrorist(true);
            PistolGun pistolGun=new PistolGun();
            soliders.get(i).setPistol(pistolGun.createGlock());
        }
        ArrayList<Circle> soliderPic=new ArrayList<>();
        int i=0;
        for (i=0;i<5;i++){

            Circle circle=new Circle();
            circle.setRadius(3.5);
            circle.setFill(Color.BLUEVIOLET);
            //setPlayersPos(circle,25,35);
            soliderPic.add(circle);
        }
        for (i=5;i<10;i++){
            Circle circle=new Circle();
            circle.setRadius(3.5);
            circle.setFill(Color.RED);

            //setPlayersPos(circle,107,40);
            soliderPic.add(circle);
        }
        for (i=0;i<10;i++){
            soliderMap.put(soliderPic.get(i),soliders.get(i));
        }
        for (i=0;i<5;i++) {
            setPlayersPos(soliderPic.get(i),25,35);
        }
        for (i=5;i<10;i++) {
            setPlayersPos(soliderPic.get(i),107,40);
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
            else if (event.getCode().equals(KeyCode.B)){
                if (mapTable.get(PlayerModel.getTableY()+1).get(PlayerModel.getTableX())==7){
                    Stage s=new Stage();
                    Image image = null;
                    try {
                        image = new Image(new FileInputStream("./src/main/resources/images/back.jpg"));
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    ImageView imageView = new ImageView(image);
                    imageView.setX(0);
                    imageView.setY(0);
                    imageView.setFitHeight(500);
                    imageView.setFitWidth(450);
                    Group root1 = new Group(imageView);
                    Scene scene1 = new Scene(root1, 450, 500);
                    Label l1=new Label("Glock");
                    Label l2=new Label("USP");
                    Label l3=new Label("Deagle");
                    Label l4=new Label("M4A1");
                    Label l5=new Label("Famas");
                    Label l6=new Label("MP5");
                    ArrayList<Label> labels=new ArrayList<>();labels.add(l1);labels.add(l2);labels.add(l3);labels.add(l4);labels.add(l5);labels.add(l6);
                    Text inf=new Text();
                    Text buy=new Text();
                    Text money=new Text();
                    PistolGun pistolGun=new PistolGun();
                    pistolGun.setPistolGuns();
                    MainGun mainGun=new MainGun();
                    mainGun.setMainGuns();
                    VBox vBox=new VBox(l1,l2,l3,l4,l5,l6);
                    vBox.setTranslateX(10);
                    vBox.setTranslateY(10);
                    vBox.setSpacing(10);
                    root1.getChildren().addAll(vBox);
                    root1.getChildren().addAll(inf,buy,money);
                    PlayerModel.setMoney(800);

                    for (Label label:labels){
                        label.setTextFill(Color.WHITE);
                        label.setFont(Font.font("Cambria",25));
                        label.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                label.setScaleX(1.2);
                                label.setScaleY(1.2);
                            }
                        });
                        label.setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                label.setScaleX(1);
                                label.setScaleY(1);
                            }
                        });
                        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                if (label.getText().equals("Famas")){
                                    inf.setText("Price :2250      Damage :14");
                                    inf.setTranslateY(280);
                                    inf.setFont(Font.font("Cambria",28));
                                    inf.setFill(Color.CYAN);
                                    buy.setText("Buy                "+PlayerModel.getMoney());
                                    buy.setFont(Font.font("Cambria",30));
                                    buy.setTranslateX(20);
                                    buy.setTranslateY(330);
                                    buy.setFill(Color.LIGHTSTEELBLUE);
                                    buy.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1.2);
                                            buy.setScaleY(1.2);
                                        }
                                    });
                                    buy.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1);
                                            buy.setScaleY(1);
                                        }
                                    });
                                    buy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setText("Buy                "+PlayerModel.getMoney());
                                            if (PlayerModel.getMoney()>=2250){
                                                PlayerModel.setMoney(PlayerModel.getMoney()-2250);
                                                buy.setText("Buy                "+PlayerModel.getMoney());
//                                                buy.setFont(Font.font("Cambria",30));
                                            }
                                        }
                                    });
                                }
                                if (label.getText().equals("M4A1")){
                                    inf.setText("Price :400      Damage :22");
                                    inf.setTranslateY(280);
                                    inf.setFont(Font.font("Cambria",28));
                                    inf.setFill(Color.CYAN);
                                    buy.setText("Buy                "+PlayerModel.getMoney());
                                    buy.setFont(Font.font("Cambria",30));
                                    buy.setTranslateX(20);
                                    buy.setTranslateY(330);
                                    buy.setFill(Color.LIGHTSTEELBLUE);
                                    buy.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1.2);
                                            buy.setScaleY(1.2);
                                        }
                                    });
                                    buy.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1);
                                            buy.setScaleY(1);
                                        }
                                    });
                                    buy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            if (PlayerModel.getMoney()>=400){
                                                PlayerModel.setMoney(PlayerModel.getMoney()-400);
                                                buy.setText("Buy                "+PlayerModel.getMoney());
//                                                buy.setFont(Font.font("Cambria",30));
                                            }
                                        }
                                    });
                                }
                                if (label.getText().equals("Deagle")){
                                    inf.setText("Price :650      Damage :34");

                                    inf.setTranslateY(280);
                                    inf.setFont(Font.font("Cambria",28));
                                    inf.setFill(Color.CYAN);
                                    buy.setText("Buy                "+PlayerModel.getMoney());
                                    buy.setFont(Font.font("Cambria",30));
                                    buy.setTranslateX(20);
                                    buy.setTranslateY(330);
                                    buy.setFill(Color.LIGHTSTEELBLUE);
                                    buy.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1.2);
                                            buy.setScaleY(1.2);
                                        }
                                    });
                                    buy.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1);
                                            buy.setScaleY(1);
                                        }
                                    });
                                    buy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            if (PlayerModel.getMoney()>=650){
                                                PlayerModel.setMoney(PlayerModel.getMoney()-650);
                                                buy.setText("Buy                "+PlayerModel.getMoney());
//                                                buy.setFont(Font.font("Cambria",30));
                                            }
                                        }
                                    });
                                }
                                if (label.getText().equals("USP")){
                                    inf.setText("Price :500      Damage :24");
                                    inf.setTranslateY(280);
                                    inf.setFont(Font.font("Cambria",28));
                                    inf.setFill(Color.CYAN);
                                    buy.setText("Buy                "+PlayerModel.getMoney());
                                    buy.setTranslateX(20);
                                    buy.setTranslateY(330);
                                    buy.setFont(Font.font("Cambria",30));
                                    buy.setFill(Color.LIGHTSTEELBLUE);
                                    buy.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1.2);
                                            buy.setScaleY(1.2);
                                        }
                                    });
                                    buy.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1);
                                            buy.setScaleY(1);
                                        }
                                    });
                                    buy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            if (PlayerModel.getMoney()>=500){
                                                PlayerModel.setMoney(PlayerModel.getMoney()-500);
                                                buy.setText("Buy                "+PlayerModel.getMoney());
//                                                buy.setFont(Font.font("Cambria",30));
                                            }
                                        }
                                    });
                                }
                                if (label.getText().equals("Glock")){
                                    inf.setText("Price :400      Damage :21");
                                    inf.setTranslateY(280);
                                    inf.setFont(Font.font("Cambria",28));
                                    inf.setFill(Color.CYAN);
                                    buy.setText("Buy                "+PlayerModel.getMoney());
                                    buy.setFont(Font.font("Cambria",30));
                                    buy.setTranslateX(20);
                                    buy.setTranslateY(330);
                                    buy.setFill(Color.LIGHTSTEELBLUE);
                                    buy.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1.2);
                                            buy.setScaleY(1.2);
                                        }
                                    });
                                    buy.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1);
                                            buy.setScaleY(1);
                                        }
                                    });
                                    buy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            if (PlayerModel.getMoney()>=400){
                                                PlayerModel.setMoney(PlayerModel.getMoney()-400);
                                                buy.setText("Buy                "+PlayerModel.getMoney());

                                            }
                                        }
                                    });
                                }
                                if (label.getText().equals("MP5")){
                                    money.setText(""+PlayerModel.getMoney());
                                    money.setTranslateX(200);
                                    inf.setText("Price :1500$      Damage :13");
                                    inf.setTranslateY(280);
                                    inf.setFont(Font.font("Cambria",28));
                                    inf.setFill(Color.CYAN);
                                    buy.setText("Buy                "+PlayerModel.getMoney());

                                    buy.setFont(Font.font("Cambria",30));
                                    buy.setTranslateX(20);
                                    buy.setTranslateY(330);
                                    buy.setFill(Color.LIGHTSTEELBLUE);
                                    buy.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1.2);
                                            buy.setScaleY(1.2);
                                        }
                                    });
                                    buy.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setScaleX(1);
                                            buy.setScaleY(1);
                                        }
                                    });
                                    buy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            buy.setText("Buy                "+PlayerModel.getMoney());
                                            if (PlayerModel.getMoney()>=1500){
                                                PlayerModel.setMoney(PlayerModel.getMoney()-1500);
                                                buy.setText("Buy                "+PlayerModel.getMoney());
                                            }
                                        }
                                    });
                                }
                            }
                        });

                    }
                    //root.getChildren().addAll(vBox);
                    s.setScene(scene1);
                    s.show();
                }
            }
           // System.out.println(mapTable.get(PlayerModel.getTableY()).get(PlayerModel.getTableX()));
            //System.out.println(playerPicture.getCenterY());
            for (Circle circle:soliderPic){
                Random random=new Random();
                int direction=random.nextInt(4)+1;
                switch (direction){
                    case 1:
                        if(mapTable.get(soliderMap.get(circle).getTableY()).get(soliderMap.get(circle).getTableX()+1)<1 || mapTable.get(soliderMap.get(circle).getTableY()).get(soliderMap.get(circle).getTableX()+1)>4) {
                            setPlayersPos(circle, soliderMap.get(circle).getTableX()+1, soliderMap.get(circle).getTableY());
                        }
                        break;
                    case 2:
                        if(mapTable.get(soliderMap.get(circle).getTableY()).get(soliderMap.get(circle).getTableX()-1)<1 || mapTable.get(soliderMap.get(circle).getTableY()).get(soliderMap.get(circle).getTableX()-1)>4) {
                            setPlayersPos(circle, soliderMap.get(circle).getTableX() - 1, soliderMap.get(circle).getTableY());
                        }
                        break;
                    case 3:
                        if(mapTable.get(soliderMap.get(circle).getTableY()-1).get(soliderMap.get(circle).getTableX())<1 || mapTable.get(soliderMap.get(circle).getTableY()-1).get(soliderMap.get(circle).getTableX())>4) {
                            setPlayersPos(circle,soliderMap.get(circle).getTableX() , soliderMap.get(circle).getTableY() - 1);
                        }
                        break;
                    case 4:
                        if(mapTable.get(soliderMap.get(circle).getTableY()+1).get(soliderMap.get(circle).getTableX())<1 || mapTable.get(soliderMap.get(circle).getTableY()+1).get(soliderMap.get(circle).getTableX())>4) {
                            setPlayersPos(circle, soliderMap.get(circle).getTableX(), soliderMap.get(circle).getTableY()+ 1);
                        }
                        break;

                        }
            }

            if(moved){
                camera.setTranslateX(playerPicture.getCenterX()-scene.getWidth()*0.12);
                camera.setTranslateY(playerPicture.getCenterY()-scene.getHeight()*0.12);
            }
        });
//        scene.setCamera(camera);
//
        root.getChildren().addAll(soliderPic);
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
        playerPicture.setCenterX(PlayerModel.getX()+3.5);
        playerPicture.setCenterY(PlayerModel.getY()+3.5);
    }
    public static void setPlayersPos(Circle playerPicture,int x,int y){
        soliderMap.get(playerPicture).setTableX(x);
        soliderMap.get(playerPicture).setTableY(y);
        soliderMap.get(playerPicture).setX(x*7);
        soliderMap.get(playerPicture).setY(y*7);
        playerPicture.setCenterX(soliderMap.get(playerPicture).getX()+3.5);
        playerPicture.setCenterY(soliderMap.get(playerPicture).getY()+3.5);
    }


}
