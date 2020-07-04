package ir.ac.kntu.cs2d.guns;

public class Glock extends Gun {

    public Glock() {
        this.name = "Glock";
        this.price = 400;
        this.damage = 21;
        this.fireRate = 300;
        this.reloadTime = 2.3;
        this.capacity = 20;
        this.type = GunType.PISTOL;
        this.teamID = 0;
    }
}
