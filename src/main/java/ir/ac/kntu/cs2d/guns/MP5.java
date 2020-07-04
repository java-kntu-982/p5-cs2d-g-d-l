package ir.ac.kntu.cs2d.guns;

public class MP5 extends Gun {

    public MP5() {
        this.name = "MP5";
        this.price = 1500;
        this.damage = 13;
        this.fireRate = 120;
        this.reloadTime = 3.1;
        this.capacity = 30;
        this.type = GunType.MAIN;
        this.teamID = 0;
    }
}
