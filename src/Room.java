import java.util.HashMap;
import java.util.Set;

public class Room {
    private String name;
    private String description;
    private HashMap<String, Room> exits = new HashMap<>();
    private HashMap<String, Item> items = new HashMap<>();
    private Enemy enemy; // для дополнительного задания (опционально)

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    public Item removeItem(String itemName) {
        return items.remove(itemName);
    }

    public Set<String> getItemNames() {
        return items.keySet();
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public String getDescription() {
        return description;
    }

    // Методы для врага (дополнительное задание)
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public boolean hasEnemy() {
        return enemy != null && enemy.isAlive();
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
