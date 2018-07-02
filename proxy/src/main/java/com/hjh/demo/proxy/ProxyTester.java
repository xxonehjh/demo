package com.hjh.demo.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xxonehjh
 */
public class ProxyTester {

	public interface Hello {
		void say();
	}

	public static class HelloImpl implements Hello {

		@Override
		public void say() {
			System.out.println("hi");
		}

	}

	public static class MyInvocationHandler implements InvocationHandler {

		private Object target;

		public MyInvocationHandler(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			System.out.println("before invoke " + proxy.getClass().getName() + ":" + method.getDeclaringClass().getName() + ":" + method.getName());
			Object result = method.invoke(target, args);
			System.out.println("after invoke " + proxy.getClass().getName() + ":" + method.getDeclaringClass().getName() + ":" + method.getName());
			return result;
		}

	}

	public static void main(String args[]) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 设置是否保存生成的代理类文件 - 保存于用户路径下 com\sun\proxy\$Proxy0.class
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		{ // 方式一
			Class<?> proxyClass = Proxy.getProxyClass(Hello.class.getClassLoader(), Hello.class);
			final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
			final InvocationHandler ih = new MyInvocationHandler(new HelloImpl());
			Hello hello = (Hello) cons.newInstance(ih);
			hello.say();
		}
		{ // 方式二
			Hello ihello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[] { Hello.class },
					new MyInvocationHandler(new HelloImpl()));
			ihello.say();
		}
	}

}
