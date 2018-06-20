package com.hjh.demo.designPatterns;

/**
 * 单例模式
 */
public class Singleton {

	private static class SingletonHolder {
		private static final Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}

	private Singleton() {
	}

}
