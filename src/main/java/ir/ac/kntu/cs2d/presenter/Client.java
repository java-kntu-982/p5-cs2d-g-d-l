package ir.ac.kntu.cs2d.presenter;

import ir.ac.kntu.cs2d.view.Observer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static ArrayList<PlayerModel> players = new ArrayList<>();

    public static void main(String []args){
        Scanner input = new Scanner(System.in);

        PlayerModel player = new PlayerModel();
        players.add(player);
        int answer;
/*        while (true){
            System.out.println("Enter 1 to add a player");
            answer = input.nextInt();
            if(answer == 1){
                *//*PlayerModel player = new PlayerModel();
                players.add(player);*//*
                break;
            }
        }*/
    }
}
