package com.example.command;

import com.example.controllers.SettleUpController;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    List<Command> commands ;
    SettleUpUserCommand settleUpUserCommand;

    public CommandExecutor(){
        commands = new ArrayList<>();
        commands.add(new SettleUpUserCommand(new SettleUpController()));

    }

    public CommandExecutor(SettleUpUserCommand settleUpUserCommand){
        this.settleUpUserCommand = settleUpUserCommand;
        commands = new ArrayList<>();
        commands.add(this.settleUpUserCommand);
    }

    public void addCommand(Command command){
        commands.add(command);

    }

    public void removeCommand(Command command){
        commands.remove(command);
    }

    public void execute(String input){
        for (Command command : commands){
            if(command.matches(input)){
                command.execute(input);
                break;
            }
        }
        throw  new RuntimeException("Command not found ");

    }
}
