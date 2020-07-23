package ir.ac.kntu.cs2d.model;

import java.util.ArrayList;

public class PistolGun extends Gun{
    public  ArrayList<PistolGun> pistolGuns=new ArrayList<>();

    public PistolGun createDeagle(){
        this.name = "Deagle";
        this.price = 650;
        this.damage = 34;
        this.fireRate = 550;
        this.reloadTime = 2.2;
        this.capacity = 7;
        this.teamID = 0;
        return this;
    }

    public void setPistolGuns() {
        pistolGuns.add(this.createDeagle());
        pistolGuns.add(this.createGlock());
        pistolGuns.add(this.createUSP());

    }

    public PistolGun createGlock(){
        this.name = "Glock";
        this.price = 400;
        this.damage = 21;
        this.fireRate = 300;
        this.reloadTime = 2.3;
        this.capacity = 20;
        this.teamID = 0;
        return this;
    }

    public PistolGun createUSP(){
        this.name = "USP";
        this.price = 500;
        this.damage = 24;
        this.fireRate = 340;
        this.reloadTime = 2.2;
        this.capacity = 12;
        this.teamID = 0;
        return this;
    }

}
