package ir.ac.kntu.cs2d.guns;

public class USP extends Gun {

    public USP() {
        this.name = "USP";
        this.price = 500;
        this.damage = 24;
        this.fireRate = 340;
        this.reloadTime = 2.2;
        this.capacity = 12;
        this.type = GunType.PISTOL;
        this.teamID = 0;
    }
}
