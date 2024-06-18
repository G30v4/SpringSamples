package dev.g30v4.spring.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.g30v4.spring.data.dao.db1.UserDao;
import dev.g30v4.spring.data.dao.db2.ProductDao;
import dev.g30v4.spring.data.dto.Product;
import dev.g30v4.spring.data.dto.User;

@SpringBootTest
class MongoMultiConnApplicationTests {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	@Test
	void testInsertUser() {
		var u = User.builder()
			.name("Pepito")
			.username("pepito")
			.createdAt(ZonedDateTime.now())
			.build();
		var ui = userDao.insert(u);
		assertNotNull(ui);
		assertEquals(ui.getUsername(), u.getUsername());

		var p = Product.builder()
				.name("Zapatito")
				.description("Como hecho en casa")
				.createdAt(ZonedDateTime.now())
				.build();
		var pi = productDao.insert(p);
		assertNotNull(pi);
		assertEquals(pi.getName(), p.getName());
	}

}
