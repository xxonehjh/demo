package com.hjh.demo.designPatterns;

/**
 * 单例模式
 */
public class Singleton {
	
	public static void main(String args[]) {
		Singleton.getInstance().hello();
	}

	private static class SingletonHolder {
		private static final Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}

	private Singleton() {
	}
	
	public void hello() {
		System.out.println("hello");
	}

}
