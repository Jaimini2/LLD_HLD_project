package TicTacToe.models;

import TicTacToe.strategies.BotPlayingStrategies.BotPlayingStrategy;
import TicTacToe.strategies.BotPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player{

    BotDifficultyLevel botDifficultyLevel;

    BotPlayingStrategy botPlayingStrategy;

    public Bot(int id,
               String name,
               Symbol symbol,
               BotDifficultyLevel botDifficultyLevel){
        super(id,name,PlayerType.BOT,symbol);
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyByLevel(botDifficultyLevel);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public Move makeMove(Board board){
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
