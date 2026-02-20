public class Main {
    public static void main(String[] args) {
        GameController game = new GameController();
        GameUI ui = new GameUI(game);
        ui.start();
    }
}
