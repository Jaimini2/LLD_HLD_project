import TicTacToe.Exception.InvalidBotCountException;
import TicTacToe.Exception.InvalidPlayerException;
import TicTacToe.controllers.GameController;
import TicTacToe.models.*;
import TicTacToe.strategies.ColWinningStrategy;
import TicTacToe.strategies.DiagonalWinningStrategy;
import TicTacToe.strategies.RowWinningStrategy;
import TicTacToe.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {


    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerException {
        GameController gameController = new GameController();
        System.out.println("Game is starting");

        try{

            int dimensionForGame = 3;
            List<Player> players = new ArrayList<>();

            players.add(new Player(1,"Jaimini", PlayerType.HUMAN,new Symbol('X')));
            players.add(new Bot(2,"GPT",new Symbol('0'),BotDifficultyLevel.EASY));

            List<WinningStrategy> winningStrategies = new ArrayList<>();
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());

            Game game = gameController.startGame(dimensionForGame,players,winningStrategies);

            gameController.displayBoard(game);
            while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                gameController.makeMove(game);
                gameController.displayBoard(game);


            }

            System.out.println("The Game is finished ! ");

            GameState gameState = gameController.checkState(game);

            if (gameState.equals(GameState.DRAW)) {

                System.out.println("The Result is DRAW !!!");
            }else{
                System.out.println("The winner is " + gameController.getWinner(game).getName());
            }

        }catch (Exception e){
            System.out.println("Game has stopped working");
        }

//       Game game1 =  gameController.startGame(3,new ArrayList<>(),new ArrayList<>());
//
//        gameController.displayBoard(game1);
//
//        while (gameController.checkState(game1).equals(GameState.IN_PROGRESS)){
//            gameController.makeMove(game1);
//            gameController.displayBoard(game1);
//        }
//
//
//        if(gameController.checkState(game1).equals(GameState.DRAW)){
//            System.out.println("Game ends in a Draw");
//        }else if(gameController.checkState(game1).equals(GameState.SUCCESS)){
//            System.out.println("Winner is "  + gameController.getWinner());
//        }
    }


}
