package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.crm.generic.baseutility.BaseTest;

/**
 * test class for contact module
 * 
 * @author SUPRIYA PERIYASAMY
 */

public class SearchContactTest extends BaseTest {

	/**
	 * Scenario : login() ==> navigateContact ==> searchContact ==> verify
	 */
	@Test
	public void searchContactTest() {

		/* step1 : login to app */
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("admin", "admin");
	}
}
