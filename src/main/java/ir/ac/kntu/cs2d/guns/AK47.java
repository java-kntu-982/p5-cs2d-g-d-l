package ir.ac.kntu.cs2d.guns;

public class AK47 extends Gun {

    public AK47() {
        this.name = "AK47";
        this.price = 2500;
        this.damage = 22;
        this.fireRate = 120;
        this.reloadTime = 2.5;
        this.capacity = 30;
        this.type = GunType.MAIN;
        this.teamID = -1;
    }
}
