package TicTacToe.strategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Player;
import TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{

    private Map<Integer, HashMap<Symbol,Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {

        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!counts.containsKey(col)){
            counts.put(col,new HashMap<>());
        }

        HashMap<Symbol,Integer> colMap = counts.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol,0);
        }
        colMap.put(symbol,colMap.get(symbol) + 1);

       return  colMap.get(symbol) == board.getSize();
    }
}
