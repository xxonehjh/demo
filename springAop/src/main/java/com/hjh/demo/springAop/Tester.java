package com.hjh.demo.springAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tester {

	public static void main(String args[]) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
		Hello helloService = context.getBean("hello", Hello.class);
		helloService.say();
		context.close();
	}

	public interface Hello {
		void say();
	}

	public static class HelloImpl implements Hello {
		@Override
		public void say() {
			System.out.println("你好");
		}
	}

	public static class Logger {
		public Object recorded(ProceedingJoinPoint pjp) throws Throwable {
			System.out.println("before aop");
			Object result = pjp.proceed();
			System.out.println("after aop");
			return result;
		}
	}

}