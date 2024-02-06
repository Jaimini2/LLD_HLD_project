package com.example.bookmyshow;

import models.BaseModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMYshowApplication {

	public static void main(String[] args) {
		BaseModel bs = new BaseModel();
		SpringApplication.run(BookMYshowApplication.class, args);

		bs.getId();
	}

}
