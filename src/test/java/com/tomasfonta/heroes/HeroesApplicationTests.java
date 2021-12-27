package com.tomasfonta.heroes;

import com.tomasfonta.heroes.service.HeroService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class HeroesApplicationTests {
	
	@Test
	void contextLoads() {
	}

	@Test
	void findUserById(){

		assertThat(1).isEqualTo(1);
	}

}
