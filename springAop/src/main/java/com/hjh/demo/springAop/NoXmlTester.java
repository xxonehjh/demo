package com.hjh.demo.springAop;

import java.io.IOException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

public class NoXmlTester {

	public static void main(String args[]) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCfg.class);
		Hello dao = ctx.getBean("helloImpl", Hello.class);
		dao.say();
		System.out.println(dao.getClass().getName());
		ctx.close();
	}

}

@Configuration
@ComponentScan(basePackages = "com.hjh.demo.springAop")
@EnableAspectJAutoProxy // 步骤 1
// @EnableAspectJAutoProxy(proxyTargetClass = true) // 会使用CGLIB
class AppCfg {

}

interface Hello {
	void say();
}

// 步骤 2
@Aspect
@Component
class AroundAspect {

	// 步骤 3
	@Around(value = "execution(* com.hjh.demo.springAop.Hello.*(..))")
	public Object recorded(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("before aop");
		Object result = pjp.proceed();
		System.out.println("after aop");
		return result;
	}

}

@Component("helloImpl")
class HelloImpl implements Hello {

	@Override
	public void say() {
		System.out.println("hello");
	}

}
