package pl.akademiakodu.kwejk2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.akademiakodu.kwejk2.model.Category;
import pl.akademiakodu.kwejk2.repository.CategoryRepository;

@SpringBootApplication
public class MemsApplication implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(MemsApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Category fun = new Category();
		fun.setId((long) 1);
		fun.setName("FUN");

		Category others = new Category();
		others.setId((long) 2);
		others.setName("OTHERS");

		categoryRepository.save(fun);
		categoryRepository.save(others);

	}
}
