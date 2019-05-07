/**
 * Item7: Avoid finalizers
 *
 * Java의 finalizer를 c++의 소멸자와 비슷한 것으로 생각해서는 안된다.
 *  - finalizer는 요청 즉시 실행되지 않을 수 있다.
 *  - 어떤 스레드가 finalizer를 실행할 지 알 수 없다. (그 스레드의 우선순위가 매우 낮을 수 있다. -> OutOfMemoryError)
 *  - JVM에 따라 finalizer 실행 방식이 다를 수 있다. 테스트 환경에서는 온전히 실행되던 프로그램이 고객의 컴퓨터에서는 실패하는 경우가 발생.
 *
 * "Finalizer를 직접 사용하지 말자!"
 *
 * 그럼 종료가 필요한 리소스를 가지는 클래스의 캡슐화는 어떻게 할까요?
 *     "명시적인 종료 메서드를 제공합시다!"
 *
 * Java 중에서의 종료 메서드를 사용하는 예시
 *  - InputStream, OutputStream, java.sql.Connection의 close()
 *  - java.util.Timer의 cancel()
 *  - java.util.awt Graphics.dispose(), Window.dispose()
 *
 * 명시적 종료 메서드들은 try-finally 구조와 함께 많이 사용된다.
 *
 * 그렇다면 finalizer를 굳이 쓰는 경우는?
 *  첫번째. 개발자가 명시적 종료 메서드 호출을 잊는 경우를 대비하기 위해.
 *  두번째. native peer를 가지는 객체가 있을 때 (native peer가 즉시
 *  종료되어야 하는 리소스를 가진다면 클래스는 명시적 종료 메서드를 반드시 호출해야 한다.)
 *      native peer는 어떤 의미인지 궁금해서 찾아보니 JNI라는 생소한 개념과 연관된 것이었다.
 *      JNI(Java Native Interface)는 jvm 위에서 실행되고 있는 자바코드가,
 *      네이티브 응용 프로그램, C,C++ 그리고 어셈블리 같은 다른 언어들로 작성된
 *      라이브러리들을 호출하거나 반대로 호출되는 것을 가능하게 하는 프로그래밍 프레임워크.
 * */

public class Item7 {
    public void method1() {
        Foo foo = new Foo();
        try {
            // Do what must be done with foo
        } finally {
            foo.terminate(); // Explicit termination method
        }
    }
}

class Foo {
    public void terminate() { }
}
