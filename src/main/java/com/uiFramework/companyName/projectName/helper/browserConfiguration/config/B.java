package com.uiFramework.companyName.projectName.helper.browserConfiguration.config;

public class B implements A {

	public void test1() {

	}

	public void test2() {

	}

	public static void main(String[] args) {
		A obj = new B();
		obj.test1();
		obj.test2();

		C.reader = new B();
		C.reader.test1();
		C.reader.test2();
	}

}
