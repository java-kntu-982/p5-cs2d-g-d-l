package ir.ac.kntu.cs2d.guns;

public class Galil extends Gun {

    public Galil() {
        this.name = "Galil";
        this.price = 2000;
        this.damage = 13;
        this.fireRate = 120;
        this.reloadTime = 2.6;
        this.capacity = 35;
        this.type = GunType.MAIN;
        this.teamID = -1;
    }
}
