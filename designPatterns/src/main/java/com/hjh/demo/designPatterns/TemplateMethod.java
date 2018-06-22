package com.hjh.demo.designPatterns;

/** 模板方法模式 */
public class TemplateMethod {

	public static void main(String args[]) {
		AbstractClass obj = new Class1();
		obj.doSomething();
		obj = new Class2();
		obj.doSomething();
	}

	public static abstract class AbstractClass {

		public final void doSomething() {
			doStep1();
			doStep2();
		}

		public abstract void doStep1();

		public abstract void doStep2();
	}

	public static class Class1 extends AbstractClass {

		@Override
		public void doStep1() {
			System.out.println(getClass().getSimpleName() + "->step1");
		}

		@Override
		public void doStep2() {
			System.out.println(getClass().getSimpleName() + "->step2");
		}

	}

	public static class Class2 extends AbstractClass {

		@Override
		public void doStep1() {
			System.out.println(getClass().getSimpleName() + "->step1");
		}

		@Override
		public void doStep2() {
			System.out.println(getClass().getSimpleName() + "->step2");
		}

	}

}
