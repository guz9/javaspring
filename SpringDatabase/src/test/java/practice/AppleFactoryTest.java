package practice;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AppleFactoryTest {
	
	@Test
	public void getAppleTest() {
		AppleFactory appleFactory = new AppleFactory();
		
		AppleConfig config = new AppleConfig();
		
		config.setColor("보라색");
		config.setPrice(800);
		
		appleFactory.setConfig(config);
		
		Apple apple = appleFactory.getApple();
		// 이렇게 하면 또 새 사과를 찍어냄
		Apple apple2 = appleFactory.getApple();
		
		log.info(apple);
		log.info(apple2);
		
		assertNotNull(apple);
		
	}
}
