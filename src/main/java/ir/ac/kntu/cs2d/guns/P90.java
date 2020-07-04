package ir.ac.kntu.cs2d.guns;

public class P90 extends Gun {

    public P90() {
        this.name = "P90";
        this.price = 2350;
        this.damage = 11;
        this.fireRate = 80;
        this.reloadTime = 3.3;
        this.capacity = 50;
        this.type = GunType.MAIN;
        this.teamID = 0;
    }
}
