package ir.ac.kntu.cs2d.model;

public class Solider {
    private static int x;
    private static int y;
    private  int tableX;
    private  int tableY;
    private  PistolGun pistol;
    private  MainGun mainGun;
    private static int health=100;
    private static int id;
    private static String name;
    private boolean terrorist;
    private boolean bot;
    private int money=800;

    public  int getX() {
        return x;
    }

    public  void setX(int x) {
        Solider.x = x;
    }

    public  int getY() {
        return y;
    }

    public  void setY(int y) {
        Solider.y = y;
    }

    public  int getTableX() {
        return tableX;
    }

    public  void setTableX(int tableX) {
        this.tableX = tableX;
    }

    public  int getTableY() {
        return tableY;
    }

    public void setTableY(int tableY) {
        this.tableY = tableY;
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        Solider.health = health;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Solider.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Solider.name = name;
    }

    public boolean isTerrorist() {
        return terrorist;
    }

    public void setTerrorist(boolean terrorist) {
        this.terrorist = terrorist;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public PistolGun getPistol() {
        return pistol;
    }

    public void setPistol(PistolGun pistol) {
        this.pistol = pistol;
    }

    public MainGun getMainGun() {
        return mainGun;
    }

    public void setMainGun(MainGun mainGun) {
        this.mainGun = mainGun;
    }
}
