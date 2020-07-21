package ir.ac.kntu.cs2d.server;

import ir.ac.kntu.cs2d.presenter.PlayerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    private Socket client;
    private static ServerSocket server;
    private static ArrayList<ArrayList<Integer>> mapTable = ServerMain.readMapFromFile();
    private static ArrayList<ArrayList<PlayerModel>> playerTable = ServerMain.getPlayerTable();
    private static ArrayList<ArrayList<Integer>> gunTable = ServerMain.getGunTable();
    private BufferedReader clientMs = null;
    private BufferedReader inputMs = null;
    private PrintStream serverMs = null;
    private int number;

    public ClientHandler(Socket client,int number) {
        this.client = client;
        this.number = number;
        try {
            clientMs = new BufferedReader(new InputStreamReader(client.getInputStream()));
            inputMs = new BufferedReader(new InputStreamReader(System.in));
            serverMs = new PrintStream(client.getOutputStream());
        } catch (IOException ignored) {
        }

        assert serverMs != null;
        serverMs.println(number);
    }
}
