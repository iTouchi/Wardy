package com.wardy.wardy;

import com.wardy.wardy.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WardyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void productTest() {
		Product _product = new Product("Zelda", 59,"Games","image.com");

		String result = _product.getTitle();

		assertEquals("Zelda", result);

	}

}
