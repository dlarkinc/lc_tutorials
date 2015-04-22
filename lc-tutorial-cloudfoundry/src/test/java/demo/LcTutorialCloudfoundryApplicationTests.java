package demo;

import ie.cit.caf.larkin.cfdemo.LcTutorialCloudfoundryApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LcTutorialCloudfoundryApplication.class)
@WebAppConfiguration
public class LcTutorialCloudfoundryApplicationTests {

	@Test
	public void contextLoads() {
	}

}
