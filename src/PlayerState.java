public interface PlayerState {
    void attack();
    void takeDamage(int damage);
    void useSpell();
    void heal();
    String getStateName();
}
