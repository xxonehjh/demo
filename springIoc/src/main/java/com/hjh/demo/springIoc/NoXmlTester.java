package com.hjh.demo.springIoc;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

public class NoXmlTester {

	public static void main(String args[]) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCfg.class);
		Hello dao1 = ctx.getBean("helloImpl", Hello.class);
		dao1.say();
		Hello dao2 = ctx.getBean("myHello", Hello.class);
		dao2.say();
		System.out.println(dao1 == dao2);
		System.in.read();
		ctx.close();
	}

}

interface Hello {
	void say();
}

@Repository("helloImpl")
class HelloImpl implements Hello {

	@Override
	public void say() {
		System.out.println("hello");
	}

}

@Configuration
@ComponentScan(basePackages = "com.hjh.demo.springIoc")
class AppCfg {

	@Bean
	Hello myHello() {
		return new HelloImpl();
	}

}