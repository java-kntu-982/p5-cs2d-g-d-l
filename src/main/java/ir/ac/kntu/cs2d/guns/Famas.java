package ir.ac.kntu.cs2d.guns;

public class Famas extends Gun {

    public Famas() {
        this.name = "Famas";
        this.price = 2250;
        this.damage = 14;
        this.fireRate = 120;
        this.reloadTime = 3.3;
        this.capacity = 25;
        this.type = GunType.MAIN;
        this.teamID = 1;
    }
}
