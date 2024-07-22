package com.springbook.template;

public abstract class Super {
	/*
	 *  기본 알고리즘 골격을 담은 메소드를 템플릿 메소드라 부른다.
	 *  템플릿 메소드는 서브클래스에서 오버라이드하거나 구현할 메소드를 사용한다.
	 */
	public void templateMethod() {
		hookMethod();
		abstractMethod();
	}
	protected void hookMethod() {} // 선택적으로 오버라이드 가능한 훅 메소
	protected abstract void abstractMethod(); // 서브클래스에서 반드시 구현해야하는 추상메소
}
