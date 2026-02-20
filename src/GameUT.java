import java.util.Scanner;

public class GameUI {
    private GameController game;
    private Scanner scanner;
    private boolean running;

    public GameUI(GameController game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void start() {
        System.out.println("Добро пожаловать в текстовую RPG!");
        System.out.println("Ваш класс: " + game.getPlayer().getStateName());
        System.out.println("Команды: осмотреть, идти [направление], взять [предмет], инвентарь, статус, атаковать, способность, лечиться, сменить класс [воин/маг/разбойник], выход");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            processCommand(input);
        }
    }

    private void processCommand(String input) {
        if (input.isEmpty()) return;

        String[] parts = input.split(" ");
        String command = parts[0];

        switch (command) {
            case "осмотреть":
                game.lookAround();
                break;
            case "идти":
                if (parts.length > 1) game.moveTo(parts[1]);
                else System.out.println("Укажите направление.");
                break;
            case "взять":
                if (parts.length > 1) game.takeItem(parts[1]);
                else System.out.println("Что взять?");
                break;
            case "инвентарь":
                game.showInventory();
                break;
            case "статус":
                showStatus();
                break;
            case "атаковать":
                game.getPlayer().attack();
                break;
            case "способность":
                game.getPlayer().useSpell();
                break;
            case "лечиться":
                game.getPlayer().heal();
                break;
            case "сменить":
                if (parts.length > 2 && parts[1].equals("класс")) {
                    changeClass(parts[2]);
                } else {
                    System.out.println("Формат: сменить класс [воин/маг/разбойник]");
                }
                break;
            case "выход":
                running = false;
                System.out.println("Игра завершена.");
                break;
            default:
                System.out.println("Неизвестная команда.");
        }
    }

    private void showStatus() {
        Player p = game.getPlayer();
        System.out.println("=== Статус ===");
        System.out.println("Класс: " + p.getStateName());
        System.out.println("Здоровье: " + p.getHealth() + "/100");
        System.out.println("Мана: " + p.getMana() + "/50");
        System.out.println("Сила: " + p.getStrength());
        System.out.println("Ловкость: " + p.getAgility());
        System.out.println("Интеллект: " + p.getIntelligence());
    }

    private void changeClass(String className) {
        switch (className) {
            case "воин":
                game.getPlayer().setState(game.getPlayer().getWarriorState());
                System.out.println("Вы стали Воином!");
                break;
            case "маг":
                game.getPlayer().setState(game.getPlayer().getMageState());
                System.out.println("Вы стали Магом!");
                break;
            case "разбойник":
                game.getPlayer().setState(game.getPlayer().getRogueState());
                System.out.println("Вы стали Разбойником!");
                break;
            default:
                System.out.println("Неизвестный класс. Доступны: воин, маг, разбойник.");
        }
    }
}
