package TicTacToe.controllers;

import TicTacToe.Exception.InvalidBotCountException;
import TicTacToe.Exception.InvalidPlayerException;
import TicTacToe.models.Game;
import TicTacToe.models.GameState;
import TicTacToe.models.Player;
import TicTacToe.strategies.WinningStrategy;

import java.util.List;

public class GameController {
    Game game;
    public Game startGame(int dimension,
                          List<Player> players,
                          List<WinningStrategy>winningStrategies) throws InvalidBotCountException, InvalidPlayerException {

        //create the object of the game
        //validate the paramters
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();

        //Game.getBuilder().addPlayer().addPlayer().
    }

    public void displayBoard(Game game){
            game.printBoard();
    }

    public void makeMove(Game game){
            game.makeMove();
    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(){

    }
}
