import java.util.HashMap;

public class Player {
    private int health;
    private int mana;
    private int strength;
    private int agility;
    private int intelligence;
    private HashMap<String, Item> inventory = new HashMap<>();

    // Состояния
    private PlayerState currentState;
    private PlayerState warriorState;
    private PlayerState mageState;
    private PlayerState rogueState;

    public Player() {
        health = 100;
        mana = 50;
        strength = 10;
        agility = 10;
        intelligence = 10;

        // Инициализация состояний
        warriorState = new WarriorState(this);
        mageState = new MageState(this);
        rogueState = new RogueState(this);

        // Начальное состояние
        currentState = warriorState;
    }

    // Делегирование методов состоянию
    public void attack() {
        currentState.attack();
    }

    public void takeDamage(int damage) {
        currentState.takeDamage(damage);
    }

    public void useSpell() {
        currentState.useSpell();
    }

    public void heal() {
        currentState.heal();
    }

    public String getStateName() {
        return currentState.getStateName();
    }

    // Геттеры и сеттеры для характеристик
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = Math.min(100, Math.max(0, health)); }

    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = Math.min(50, Math.max(0, mana)); }

    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }

    public int getAgility() { return agility; }
    public void setAgility(int agility) { this.agility = agility; }

    public int getIntelligence() { return intelligence; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    // Доступ к состояниям
    public PlayerState getWarriorState() { return warriorState; }
    public PlayerState getMageState() { return mageState; }
    public PlayerState getRogueState() { return rogueState; }

    public void setState(PlayerState state) {
        this.currentState = state;
    }

    // Инвентарь
    public void addItem(Item item) {
        inventory.put(item.getName(), item);
    }

    public String getInventoryNames() {
        if (inventory.isEmpty()) return "пусто";
        return String.join(", ", inventory.keySet());
    }
}
