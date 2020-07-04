package ir.ac.kntu.cs2d.guns;

public class Deagle extends Gun {

    public Deagle() {
        this.name = "Deagle";
        this.price = 650;
        this.damage = 34;
        this.fireRate = 550;
        this.reloadTime = 2.2;
        this.capacity = 7;
        this.type = GunType.PISTOL;
        this.teamID = 0;
    }
}
