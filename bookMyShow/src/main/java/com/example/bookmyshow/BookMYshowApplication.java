package com.example.bookmyshow;

import com.example.bookmyshow.controllers.UserController;
import com.example.bookmyshow.dtos.SignUpRequestDTO;
import com.example.bookmyshow.dtos.SignUpResponseDTO;
import com.example.bookmyshow.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMYshowApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;

	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
		signUpRequestDTO.setEmail("jaimini2.mehta@Scaler.com");
		signUpRequestDTO.setPassword("password");

		SignUpResponseDTO user = userController.signUp(signUpRequestDTO);

	}

	public static void main(String[] args) {
		BaseModel bs = new BaseModel();
		SpringApplication.run(BookMYshowApplication.class, args);

		bs.getId();
	}


}
