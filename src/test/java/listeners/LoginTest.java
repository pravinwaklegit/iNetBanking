package listeners;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
	
	@Test
	void loginByEmail()
	{
		System.out.println("Login by Email");
		Assert.assertEquals("Pravin", "Pravin");
	}
	
	@Test
	void loginByFacebook()
	{
		System.out.println("Login by Facebook");
		Assert.assertEquals("Pravin", "Latika");
	}


}
