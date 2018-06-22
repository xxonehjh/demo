package com.hjh.demo.designPatterns;

/**工厂模式*/
public class Factory {

	public interface IProduct {
		String name();
	}

	/**普通工厂*/
	public interface IFactory {
		IProduct createProduct();
	}

	public static class ProductA implements IProduct {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class ProductB implements IProduct {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class FactoryA implements IFactory {
		@Override
		public IProduct createProduct() {
			return new ProductA();
		}
	}

	public static class FactoryB implements IFactory {
		@Override
		public IProduct createProduct() {
			return new ProductB();
		}
	}

	/**简单工厂*/
	public static class SimpleFactory {
		public static IProduct createProduct(String type) {
			switch (type) {
			case "A":
				return new ProductA();
			case "B":
				return new ProductB();
			default:
				throw new java.lang.IllegalArgumentException("unknow type:" + type);
			}
		}
	}

	public static void main(String args[]) {
		IFactory factory = new FactoryA();
		System.out.println(factory.createProduct().name());
		factory = new FactoryB();
		System.out.println(factory.createProduct().name());
		System.out.println(SimpleFactory.createProduct("A").name());
		System.out.println(SimpleFactory.createProduct("B").name());
	}

}
