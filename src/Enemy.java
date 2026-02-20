public class Enemy {
    private String name;
    private int health;
    private int damage;
    private EnemyState currentState;
    // состояния...
    public void attack(Player player) { currentState.attack(player); }
    public void takeDamage(int dmg) { currentState.takeDamage(dmg); }
}
