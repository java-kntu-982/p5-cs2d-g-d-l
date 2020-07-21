package ir.ac.kntu.cs2d.presenter;

import ir.ac.kntu.cs2d.view.Observer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Client extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Socket sock=null;
        int serverPort=50128;
        String hostAddress="127.0.0.1";
        Observer.enterName().show();

            try
            {
                sock = new Socket(hostAddress, serverPort);

            }
            catch(IOException io)
            {
                System.out.println("Connection error!");
            }
            System.out.println("Connected to server successfully ...");




    }
    public static void main() {
        launch();
    }
}
