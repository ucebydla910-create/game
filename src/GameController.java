import java.util.HashMap;

public class GameController {
    private Player player;
    private Room currentRoom;
    private HashMap<String, Room> world;

    public GameController() {
        player = new Player();
        initWorld();
        currentRoom = world.get("start");
    }

    private void initWorld() {
        world = new HashMap<>();

        // Создаём комнаты
        Room start = new Room("Начальная комната", "Вы в маленькой тёмной комнате. На стене висит факел.");
        Room cave = new Room("Пещера", "Сырая пещера с низким потолком. В углу кто-то шевелится.");

        // Добавляем предметы
        start.addItem(new Item("факел", 1.0));
        cave.addItem(new Item("золотая монета", 0.1));

        // Связываем комнаты
        start.setExit("север", cave);
        cave.setExit("юг", start);

        world.put("start", start);
        world.put("cave", cave);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void moveTo(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("Вы переместились: " + currentRoom.getDescription());
        } else {
            System.out.println("Туда нельзя пройти.");
        }
    }

    public void lookAround() {
        System.out.println(currentRoom.getDescription());
        if (!currentRoom.getItems().isEmpty()) {
            System.out.println("Вы видите предметы: " + currentRoom.getItemNames());
        }
        if (currentRoom.hasEnemy()) {
            System.out.println("В комнате враг: " + currentRoom.getEnemy().getName());
        }
    }

    public void takeItem(String itemName) {
        Item item = currentRoom.removeItem(itemName);
        if (item != null) {
            player.addItem(item);
            System.out.println("Вы взяли " + item.getName());
        } else {
            System.out.println("Здесь нет такого предмета.");
        }
    }

    public void showInventory() {
        System.out.println("Инвентарь: " + player.getInventoryNames());
    }
}
