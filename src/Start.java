public class Start {
    private Game game;

    public Start(Game game) {
        this.game = game;

    }


    public void playGame() {
        // Main game loop
        while (!game.gameEnd) {
            game.gameEnd=true;

        }


    }

    public void endGame() {
        game.gameEnd(true);
    }
}
