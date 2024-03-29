import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int health;
    private Weapon currentWeapon;

    //constructor that sets the room where the player spawns.
    public Player(Room firstRoom) {
        this.currentRoom = firstRoom;
        this.inventory = new ArrayList<>();
        this.health = 70;
    }

    // methods so the player can move to different rooms
    public void goNorth() {
        if (currentRoom.getNorth() != null) {
            currentRoom = currentRoom.getNorth(); //Bruger getter metode til at sette ny currentRoom til den, som er North fra currentRoom
            System.out.println("going north");
            currentRoomPrint();
        } else {
            notPossible();
        }
    }

    public void goSouth() {
        if (currentRoom.getSouth() != null) {
            currentRoom = currentRoom.getSouth();
            System.out.println("going south");
            currentRoomPrint();
        } else {
            notPossible();
        }
    }

    public void goEast() {
        if (currentRoom.getEast() != null) {
            currentRoom = currentRoom.getEast();
            System.out.println("going east");
            currentRoomPrint();
        } else {
            notPossible();
        }
    }

    public void goWest() {
        if (currentRoom.getWest() != null) {
            currentRoom = currentRoom.getWest();
            System.out.println("going west");
            currentRoomPrint();
        } else {
            notPossible();
        }
    }

    //current room name and description
    public void currentRoomPrint() {
        System.out.println(currentRoom.getName() + " " + currentRoom.getDescription());
    }

    public void notPossible() {
        System.out.println("You cannot go there");
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    private Item findItemInInventory(String shortName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }

//    public Item findFood(String shortName) {
//        for (Item item : (inventory || currentRoom))  {
//            if (item.getShortName().equalsIgnoreCase(shortName)) {
//                return item;
//        } return null;
//    }
//    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public Item dropItem(String shortName) {
        Item pickedItem = findItemInInventory(shortName);
        if (pickedItem != null) {
            removeItem(pickedItem);
            getCurrentRoom().addItem(pickedItem);
        }
        return pickedItem;
    }

    public Item takeItem(String shortName) {
        Item pickedItem = getCurrentRoom().removeItem(shortName);
        addItem(pickedItem);
        return pickedItem;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }


    public ReturnMessage eatItem(String shortName) {
        Item item = findItemInInventory(shortName);
        if (item == null) {
            return ReturnMessage.NOT_FOUND;
        } else if (item instanceof Food food) {
            removeItem(item);
            health += food.getHP();
            return ReturnMessage.OK;
        } else {
            return ReturnMessage.NOT_OK;
        }
    }

    public ReturnMessage equipWeapon(String shortName) {
        Item item = findItemInInventory(shortName);
        //Leder efter et våben i inventory
        if (item == null) {
            return ReturnMessage.NOT_FOUND;
        } else if (item instanceof Weapon) {
            if (((Weapon) item).isRangedWeapon) {
                currentWeapon = (Weapon) item;
                inventory.remove(item);
                return ReturnMessage.OK_AMMO;
            } else {
                currentWeapon = (Weapon) item;
                inventory.remove(item);
                return ReturnMessage.OK;
            }
        } else {
            return ReturnMessage.NOT_OK;
        }
    }

    public ReturnMessage attack() {
        if (currentWeapon == null) { //Hvis man ikke har equipped våben
            return ReturnMessage.NOT_FOUND;
        }
        if (currentRoom.) {
            if (currentWeapon.remainingUses() > 0) {
                currentWeapon.useWeapon();
                return ReturnMessage.OK_AMMO;
                //if(Enemy.isEnemyDead)
                //currentWeapon.damage
            } else {
                return ReturnMessage.NOT_OK;
                //sout "Der er ikke mere ammo i dit våben"
            }
        } else {
            return ReturnMessage.OK; //hvis man har et melee weapon
            }
        }


    public ReturnMessage attack() {
        if (currentWeapon == null) { //Hvis man ikke har equipped våben
            return ReturnMessage.NOT_FOUND;
        }
        if (currentRoom.)
            if (currentWeapon.isRangedWeapon) {
            if (currentWeapon.remainingUses() > 0) {
                currentWeapon.useWeapon();
                return ReturnMessage.OK_AMMO;
                //if(Enemy.isEnemyDead)
                //currentWeapon.damage

            } else {
                return ReturnMessage.NOT_OK;
                //sout "Der er ikke mere ammo i dit våben"
            }
        } else {
            return ReturnMessage.OK; //hvis man har et mellee weapon
        }
    }
    }