package com.hjh.demo.designPatterns;

/** 抽象工厂模式 */
public class AbstractFactory {

	public static void main(String args[]) {
		IFactory f1 = new FactoryA();
		System.out.println(f1.createProductA().name());
		System.out.println(f1.createProductB().name());

		f1 = new FactoryB();
		System.out.println(f1.createProductA().name());
		System.out.println(f1.createProductB().name());
	}

	public interface IProductA {
		String name();
	}

	public interface IProductB {
		String name();
	}

	public interface IFactory {
		IProductA createProductA();

		IProductB createProductB();
	}

	public static class ProductAForFactoryA implements IProductA {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class ProductBForFactoryA implements IProductB {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class ProductAForFactoryB implements IProductA {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class ProductBForFactoryB implements IProductB {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class FactoryA implements IFactory {

		@Override
		public IProductA createProductA() {
			return new ProductAForFactoryA();
		}

		@Override
		public IProductB createProductB() {
			return new ProductBForFactoryA();
		}

	}

	public static class FactoryB implements IFactory {

		@Override
		public IProductA createProductA() {
			return new ProductAForFactoryB();
		}

		@Override
		public IProductB createProductB() {
			return new ProductBForFactoryB();
		}

	}

}
