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

    private int x;
    private int y;
    private int tableX;
    private int tableY;
    private PistolGun pistol;
    private MainGun mainGun;
    private int health;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTableX() {
        return tableX;
    }

    public void setTableX(int tableX) {
        this.tableX = tableX;
    }

    public int getTableY() {
        return tableY;
    }

    public void setTableY(int tableY) {
        this.tableY = tableY;
    }

    public PistolGun getPistol() {
        return pistol;
    }

    public void setPistol(PistolGun pistol) {
        this.pistol = pistol;
    }

    public MainGun getMainGun() {
        return mainGun;
    }

    public void setMainGun(MainGun mainGun) {
        this.mainGun = mainGun;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getId() {
        return id;
    }

    public static void setId(int id) {
        PlayerModel.id = id;
    }

    public String getName() {
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
