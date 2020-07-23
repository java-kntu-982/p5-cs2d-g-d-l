package ir.ac.kntu.cs2d.presenter;

import ir.ac.kntu.cs2d.model.MainGun;
import ir.ac.kntu.cs2d.model.PistolGun;
import ir.ac.kntu.cs2d.view.Observer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class PlayerModel extends Application {

    private static Socket client=null;
    private static int serverPort=50128;
    private static String hostAddress="127.0.0.1";

    private static int x;
    private static int y;
    private static int tableX;
    private static int tableY;
    private static PistolGun pistol;
    private static MainGun mainGun;
    private static int health;
    private static int id;
    private static String name;
    private boolean terrorist;
    private boolean bot;

    private static BufferedReader serverMs;
    private static BufferedReader inputMs;
    private static PrintStream clientMs;

    public static void main(String []args){
        while(true){
            try{
                client = new Socket(hostAddress, serverPort);
                serverMs = new BufferedReader(new InputStreamReader(client.getInputStream()));
                inputMs = new BufferedReader(new InputStreamReader(System.in));
                clientMs = new PrintStream(client.getOutputStream());
                id = serverMs.read();
                System.out.println("Player number " + id + " joined server successfully");
                launch(args);
                //start(Observer.createStage(id));
                break;
            } catch (Exception ignored){
            }
        }
    }

    /*public PlayerModel() {
        while(true){
            try{
                client = new Socket(hostAddress, serverPort);
                serverMs = new BufferedReader(new InputStreamReader(client.getInputStream()));
                inputMs = new BufferedReader(new InputStreamReader(System.in));
                clientMs = new PrintStream(client.getOutputStream());
                id = serverMs.read();
                System.out.println("Player number " + id + " joined server successfully");
                launch();
                //start(Observer.createStage(id));
                break;
            } catch (Exception ignored){
            }
        }
    }*/

    @Override
    public void start(Stage stage) throws Exception {
        try{
            Observer.enterName(Observer.createStage());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        PlayerModel.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        PlayerModel.y = y;
    }

    public static int getTableX() {
        return tableX;
    }

    public static void setTableX(int tableX) {
        PlayerModel.tableX = tableX;
    }

    public static int getTableY() {
        return tableY;
    }

    public static void setTableY(int tableY) {
        PlayerModel.tableY = tableY;
    }

    public static PistolGun getPistol() {
        return pistol;
    }

    public static void setPistol(PistolGun pistol) {
        PlayerModel.pistol = pistol;
    }

    public static MainGun getMainGun() {
        return mainGun;
    }

    public static void setMainGun(MainGun mainGun) {
        PlayerModel.mainGun = mainGun;
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        PlayerModel.health = health;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        PlayerModel.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        PlayerModel.name = name;
    }

    public boolean isTerrorist() {
        return terrorist;
    }

    public void setTerrorist(boolean terrorist) {
        this.terrorist = terrorist;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }
}
