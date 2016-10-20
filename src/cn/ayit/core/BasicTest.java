package cn.ayit.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BasicTest {
	protected ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
     
}
