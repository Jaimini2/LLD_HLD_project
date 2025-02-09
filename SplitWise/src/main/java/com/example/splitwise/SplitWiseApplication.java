package com.example.splitwise;

import com.example.command.CommandExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@EnableJpaAuditing
@SpringBootApplication
public class SplitWiseApplication implements CommandLineRunner {

	private CommandExecutor commandExecutor;


	private Scanner sc = new Scanner(System.in);

	public SplitWiseApplication(){
		commandExecutor = new CommandExecutor();

	}

	public static void main(String[] args) {
		SpringApplication.run(SplitWiseApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
			while(true){
				String input = sc.nextLine();
				commandExecutor.execute(input);
			}
	}
}


// 1. settle-up user : User
//2 . settle-up group : Group
//settleUpController

//settle up Algo
//connect controller to service

