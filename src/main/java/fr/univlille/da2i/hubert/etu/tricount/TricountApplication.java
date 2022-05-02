package fr.univlille.da2i.hubert.etu.tricount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.UUID;

@SpringBootApplication
public class TricountApplication extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(TricountApplication.class, args);
	}

}
