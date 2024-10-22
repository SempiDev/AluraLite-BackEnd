package com.example.LiterAluraFinal;

import com.example.LiterAluraFinal.Principal.Principal;
import com.example.LiterAluraFinal.Repositorio.RepositorioAutor;
import com.example.LiterAluraFinal.Repositorio.RepositorioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraFinalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraFinalApplication.class, args);
	}
	@Autowired
	private RepositorioLibro RepositorioLibro;
    @Autowired
	private RepositorioAutor RepositorioAutor;
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal() {

		};
	}
}
