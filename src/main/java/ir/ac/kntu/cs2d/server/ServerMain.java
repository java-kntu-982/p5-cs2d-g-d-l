package ir.ac.kntu.cs2d.server;

import ir.ac.kntu.cs2d.presenter.PlayerModel;
import ir.ac.kntu.cs2d.view.Observer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ServerMain {

    private static LinkedList<ClientHandler> clients = new LinkedList<>();
    private static ArrayList<ArrayList<Integer>> mapTable = new ArrayList<>();
    private static ArrayList<ArrayList<PlayerModel>> playerTable = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> gunTable = new ArrayList<>();

    public static void main(String[] arg) throws IOException {

        ServerSocket socket;
        Socket client;

        int serverPort = 50128;
        System.out.println("Waiting for client!");
        while (true) {
            try {
                socket = new ServerSocket(serverPort);
                System.out.println("Server Online");
                break;
            } catch (IOException ignored) {
            }
        }
        while (true){

            try {
                client = socket.accept();
                ClientHandler clientHandler = new ClientHandler(client,clients.size()+1);
                clients.add(clientHandler);
                System.out.println("Player number " + clients.size() + " joined");

                break;

            } catch (IOException ignored) {
            }
        }

    }

    public static ArrayList<ArrayList<Integer>> readMapFromFile(){

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/maps/dust2.txt")))) {
            String line;
            while ((line=reader.readLine()) != null){
                ArrayList<Integer> temp = new ArrayList<>();
                for(int i=0;i<line.length();i++){
                    temp.add(Integer.parseInt(String.valueOf(line.charAt(i))));

                }
                mapTable.add(temp);
            }
        } catch (Exception ignored){
        }

        playerTable.clear();
        gunTable.clear();

        for (ArrayList<Integer> integers : mapTable) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < integers.size(); j++) {
                temp.add(0);
            };
            gunTable.add(temp);
        }

        return mapTable;
    }

    public static LinkedList<ClientHandler> getPlayers() {
        return clients;
    }

    public static void setPlayers(LinkedList<ClientHandler> players) {
        ServerMain.clients = players;
    }

    public static ArrayList<ArrayList<Integer>> getMapTable() {
        return mapTable;
    }

    public static void setMapTable(ArrayList<ArrayList<Integer>> mapTable) {
        ServerMain.mapTable = mapTable;
    }

    public static ArrayList<ArrayList<PlayerModel>> getPlayerTable() {
        return playerTable;
    }

    public static void setPlayerTable(ArrayList<ArrayList<PlayerModel>> playerTable) {
        ServerMain.playerTable = playerTable;
    }

    public static ArrayList<ArrayList<Integer>> getGunTable() {
        return gunTable;
    }

    public static void setGunTable(ArrayList<ArrayList<Integer>> gunTable) {
        ServerMain.gunTable = gunTable;
    }
}
