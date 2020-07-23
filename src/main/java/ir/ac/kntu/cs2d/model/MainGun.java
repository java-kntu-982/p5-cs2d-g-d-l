package ir.ac.kntu.cs2d.model;


import java.util.ArrayList;
import java.util.Map;

public class MainGun extends Gun {
    ArrayList<MainGun> mainGuns=new ArrayList<>();
    public MainGun createAK47(){
        this.name = "AK47";
        this.price = 2500;
        this.damage = 22;
        this.fireRate = 120;
        this.reloadTime = 2.5;
        this.capacity = 30;
        this.teamID = -1;
        return this;
    }

    public MainGun createFamas(){
        this.name = "Famas";
        this.price = 2250;
        this.damage = 14;
        this.fireRate = 120;
        this.reloadTime = 3.3;
        this.capacity = 25;
        this.teamID = 1;
        return this;
    }


    public MainGun createM4A1(){
        this.name = "M4A1";
        this.price = 3100;
        this.damage = 22;
        this.fireRate = 120;
        this.reloadTime = 3.1;
        this.capacity = 30;
        this.teamID = 1;
        return this;
    }

    public void setMainGuns() {
        mainGuns.add(createFamas());
        mainGuns.add(createM4A1());
    }

    public MainGun createMP5(){
        this.name = "MP5";
        this.price = 1500;
        this.damage = 13;
        this.fireRate = 120;
        this.reloadTime = 3.1;
        this.capacity = 30;
        this.teamID = 0;
        return this;
    }

    public void createP90(){
        this.name = "P90";
        this.price = 2350;
        this.damage = 11;
        this.fireRate = 80;
        this.reloadTime = 3.3;
        this.capacity = 50;
        this.teamID = 0;
    }

    public void createGalil(){
        this.name = "Galil";
        this.price = 2000;
        this.damage = 13;
        this.fireRate = 120;
        this.reloadTime = 2.6;
        this.capacity = 35;
        this.teamID = -1;
    }
}

