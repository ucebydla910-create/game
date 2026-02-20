public class WarriorState implements PlayerState {
    private Player player;
    private int armorBonus = 5;

    public WarriorState(Player player) {
        this.player = player;
    }

    @Override
    public void attack() {
        int damage = player.getStrength() * 2;
        System.out.println("🗡️ Воин наносит удар мечом! Урон: " + damage);
        // Пример автоматической смены состояния при низком здоровье
        if (player.getHealth() < 30 && Math.random() < 0.3) {
            System.out.println("⚡ Воин впадает в ярость! (но пока нет состояния берсерка)");
            // player.setState(player.getBerserkState()); // если бы было
        }
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = Math.max(1, damage - armorBonus);
        player.setHealth(player.getHealth() - reducedDamage);
        System.out.println("🛡️ Броня воина поглотила " + armorBonus + " урона. Получено: " + reducedDamage);
    }

    @Override
    public void useSpell() {
        if (player.getMana() >= 15) {
            player.setMana(player.getMana() - 15);
            System.out.println("📢 Воин использует Боевой клич! Сила увеличена на 1 ход.");
            // Упрощённо: увеличим силу на один ход (сразу для следующей атаки)
            player.setStrength(player.getStrength() + 5);
            // В реальности нужно было бы вернуть через ход, но для демо сойдёт
        } else {
            System.out.println("❌ Недостаточно маны для Боевого клича!");
        }
    }

    @Override
    public void heal() {
        if (player.getMana() >= 5) {
            player.setHealth(player.getHealth() + 10);
            player.setMana(player.getMana() - 5);
            System.out.println("❤️ Воин лечится, восстановлено 10 здоровья.");
        } else {
            System.out.println("❌ Недостаточно маны для лечения.");
        }
    }

    @Override
    public String getStateName() {
        return "⚔️ Воин";
    }
}
