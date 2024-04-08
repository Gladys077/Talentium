package cohorte16.homeservice;

import cohorte16.homeservice.security.EncryptData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void encriptarDatos(){
		String password ="passwornd super secreto";
		String storedHash = EncryptData.encryptPassword(password);
		 Assertions.assertTrue(EncryptData.verifyPassword(password,storedHash));
	}

	@Test
	public void FallarencriptarDatos(){
		String password ="passwornd super secreto";
		String storedHash = EncryptData.encryptPassword(password);
		Assertions.assertFalse(EncryptData.verifyPassword("password",storedHash));
	}
}
