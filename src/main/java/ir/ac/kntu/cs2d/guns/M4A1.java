package ir.ac.kntu.cs2d.guns;

public class M4A1 extends Gun {

    public M4A1() {
        this.name = "M4A1";
        this.price = 3100;
        this.damage = 22;
        this.fireRate = 120;
        this.reloadTime = 3.1;
        this.capacity = 30;
        this.type = GunType.MAIN;
        this.teamID = 1;
    }
}
