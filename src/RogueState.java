public class RogueState implements PlayerState {
    private Player player;
    private boolean stealthActive = false; // флаг для способности "Скрытность"

    public RogueState(Player player) {
        this.player = player;
    }

    @Override
    public void attack() {
        int baseDamage = player.getStrength() + player.getAgility();
        if (stealthActive) {
            baseDamage *= 3;
            System.out.println("🗡️ Разбойник атакует из скрытности с тройным уроном!");
            stealthActive = false;
        } else {
            // Шанс крита 30%
            if (Math.random() < 0.3) {
                baseDamage *= 2;
                System.out.println("⚡ Критический удар!");
            }
        }
        System.out.println("🗡️ Разбойник наносит удар кинжалом. Урон: " + baseDamage);
    }

    @Override
    public void takeDamage(int damage) {
        // Шанс уклонения 20%
        if (Math.random() < 0.2) {
            System.out.println("💨 Разбойник увернулся от атаки!");
        } else {
            player.setHealth(player.getHealth() - damage);
            System.out.println("🤕 Разбойник получил урон: " + damage);
        }
    }

    @Override
    public void useSpell() {
        if (!stealthActive) {
            stealthActive = true;
            System.out.println("🌑 Разбойник использует Скрытность! Следующая атака будет тройной.");
        } else {
            System.out.println("❌ Вы уже в скрытности.");
        }
    }

    @Override
    public void heal() {
        // Лечение без маны, но слабое
        player.setHealth(player.getHealth() + 5);
        System.out.println("🍃 Разбойник использует травы, восстановлено 5 здоровья.");
    }

    @Override
    public String getStateName() {
        return "🗡️ Разбойник";
    }
}
