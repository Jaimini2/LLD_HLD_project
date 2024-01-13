package TicTacToe.models;

import TicTacToe.Exception.InvalidBotCountException;
import TicTacToe.Exception.InvalidPlayerException;
import TicTacToe.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Board board;

    private List<Player> players;

    private List<Move> moves;

    private GameState gameState;

    private int nextPlayerIndex;

    private Player winner;

    private Game(int dimension,List<Player>players,List<WinningStrategy>winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winner = null;
        this.moves = new ArrayList<>();
    }

    public static  Builder getBuilder(){
        return new Builder();
    }

    public void makeMove() {
        Player currentMovePlayer = players.get(nextPlayerIndex);
        System.out.println("It is "+currentMovePlayer.getName() + "'s turn." +
                "Please make your move ");

       Move move =  currentMovePlayer.makeMove(board);

       if(!validateMove(move)){
           System.out.println("Invalid move!");
           return;
       }

       int row = move.getCell().getRow();
       int col = move.getCell().getCol();

       Cell cellToBeUpdated = board.getBoard().get(row).get(col);
       cellToBeUpdated.setCellState(CellState.FILLED);
       cellToBeUpdated.setPlayer(currentMovePlayer);

       Move finalMove = new Move(cellToBeUpdated,currentMovePlayer);
       moves.add(finalMove);

       nextPlayerIndex+=1;
       nextPlayerIndex %= players.size();

       if(checkWinner(board,finalMove)){
           gameState = GameState.SUCCESS;
           winner = currentMovePlayer;
       }else if(moves.size() == board.getSize() * board.getSize()){
           gameState = GameState.DRAW;
       }
        System.out.println("Player " + currentMovePlayer.getName() + " moved at " + move.getCell().getRow() + " , " + move.getCell().getCol());


    }

    public boolean checkWinner(Board board,Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(move,board)){
                return true;
            }
        }

        return false;
    }

    public boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getSize()){
            return false;
        }
        if(col >= board.getSize()){
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public static class Builder{

        private int dimension;

        private List<Player> players;

        private List<WinningStrategy>winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addPlayer(Player player){
            this.players.add(player);
        return this;
        }

        public Builder addWinningStrategies(WinningStrategy winningStrategy){
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        public void validate() throws InvalidBotCountException, InvalidPlayerException {
            //validate bot count

            //we can break this code into multiple functions to maintain code readability
            int botCount = 0;

            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount+=1;
                }
            }

            if(botCount > 1){
                throw new InvalidBotCountException();
            }
            //validate players count
            if(players.size() != dimension - 1){
                throw new InvalidPlayerException();
            }
            //validate unique symbol for every player

            Map<Character,Integer> symbolCounts = new HashMap<>();
            for(Player playr : players){
                if(!symbolCounts.containsKey(playr.getSymbol().getSymbol())){
                    symbolCounts.put(playr.getSymbol().getSymbol(),1);
                }else{
                    throw  new RuntimeException();
                }
            }


        }

        public Game build() throws InvalidBotCountException, InvalidPlayerException {


            //first do the validation

            validate();
            return new Game(this.dimension,
                    this.players,
                    this.winningStrategies);
        }



    }

    public void printBoard(){
        board.printBoard();
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    private List<WinningStrategy> winningStrategies;
    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }



}
