package ir.ac.kntu.cs2d.guns;

public class Gun {

    protected String name;
    protected int price;
    protected int damage;
    protected int fireRate;
    protected double reloadTime;
    protected int capacity;
    protected GunType type;
    protected int teamID;   //0 for both teams     //1 for CT      //-1 for Terrorists

    public Gun() {
    }
}
