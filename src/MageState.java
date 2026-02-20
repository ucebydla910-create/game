public class MageState implements PlayerState {
    private Player player;

    public MageState(Player player) {
        this.player = player;
    }

    @Override
    public void attack() {
        int damage = player.getStrength() / 2; // слабая физ. атака
        System.out.println("🧙 Маг слабо бьёт посохом. Урон: " + damage);
    }

    @Override
    public void takeDamage(int damage) {
        player.setHealth(player.getHealth() - damage);
        System.out.println("😵 Маг получает урон: " + damage + " (без защиты)");
    }

    @Override
    public void useSpell() {
        if (player.getMana() >= 15) {
            int damage = player.getIntelligence() * 3;
            player.setMana(player.getMana() - 15);
            System.out.println("🔥 Маг использует Огненный шар! Урон: " + damage);
        } else {
            System.out.println("❌ Недостаточно маны для Огненного шара!");
        }
    }

    @Override
    public void heal() {
        if (player.getMana() >= 10) {
            player.setHealth(player.getHealth() + 15);
            player.setMana(player.getMana() - 10);
            System.out.println("💚 Маг лечится магией, восстановлено 15 здоровья.");
        } else {
            System.out.println("❌ Недостаточно маны для лечения.");
        }
    }

    @Override
    public String getStateName() {
        return "🔥 Маг";
    }
}
