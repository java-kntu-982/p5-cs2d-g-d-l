package ir.ac.kntu.cs2d.guns;

public class Pistol extends Gun {

    public void createDeagle(){
        this.name = "Deagle";
        this.price = 650;
        this.damage = 34;
        this.fireRate = 550;
        this.reloadTime = 2.2;
        this.capacity = 7;
        this.teamID = 0;
    }

    public void createGlock(){
        this.name = "Glock";
        this.price = 400;
        this.damage = 21;
        this.fireRate = 300;
        this.reloadTime = 2.3;
        this.capacity = 20;
        this.teamID = 0;
    }

    public void createUSP(){
        this.name = "USP";
        this.price = 500;
        this.damage = 24;
        this.fireRate = 340;
        this.reloadTime = 2.2;
        this.capacity = 12;
        this.teamID = 0;
    }
}
