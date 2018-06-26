package com.hjh.demo.springIoc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tester {

	public interface Hello {
		void say();
	}

	public static class HelloImpl implements Hello {
		@Override
		public void say() {
			System.out.println("你好");
		}
	}

	public static void main(String args[]) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
		Hello helloService = context.getBean("hello", Hello.class);
		helloService.say();
		context.close();
	}

}
