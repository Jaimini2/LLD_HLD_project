package TicTacToe.strategies.BotPlayingStrategies;

import TicTacToe.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyByLevel(BotDifficultyLevel botDifficultyLevel){
        return new EasyBotPlayingStrategy();
    }
}
