package com.hjh.demo.designPatterns;

/** 抽象工厂模式 */
public class AbstractFactory {

	public static void main(String args[]) {
		IFactory f1 = new FactoryA();
		System.out.println(f1.createPartA().name());
		System.out.println(f1.createPartB().name());

		f1 = new FactoryB();
		System.out.println(f1.createPartA().name());
		System.out.println(f1.createPartB().name());
	}

	public interface IPartA {
		String name();
	}

	public interface IPartB {
		String name();
	}

	public interface IFactory {
		IPartA createPartA();

		IPartB createPartB();
	}

	public static class PartAForFactoryA implements IPartA {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class PartBForFactoryA implements IPartB {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class PartAForFactoryB implements IPartA {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class PartBForFactoryB implements IPartB {

		@Override
		public String name() {
			return this.getClass().getName();
		}

	}

	public static class FactoryA implements IFactory {

		@Override
		public IPartA createPartA() {
			return new PartAForFactoryA();
		}

		@Override
		public IPartB createPartB() {
			return new PartBForFactoryA();
		}

	}

	public static class FactoryB implements IFactory {

		@Override
		public IPartA createPartA() {
			return new PartAForFactoryB();
		}

		@Override
		public IPartB createPartB() {
			return new PartBForFactoryB();
		}

	}

}
