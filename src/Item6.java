import java.util.Arrays;
import java.util.EmptyStackException;

/** Item 6: Eliminate obsolete object references
 *
 * Java를 쓰면 memory leak을 전혀 신경쓰지 않아도 될까? 아니다!!!
 *
 *
 * 메모리 누출은 일반적으로 명백한 오류로 나타나지 않아서 수년동안 시스템에
 * 남아있을 수 있다! 주의 깊은 검사나 '힙 프로파일러' 라고 하는 디버깅 도구를
 * 사용하면 발견할 수 있다. 따라서 이런 문제를 미리 발생하지 않도록
 * 주의하는 것이 중요하다.
 * */

// Let's smell the 'memory leak'!
public class Item6 {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Item6() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        //return elements[--size]; => Don't do this!
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    /**
     * 적어도 하나 이상의 요소를 위한 공간이 있는지 확인
     * 대략적으로 배열이 늘어나야 할 때 2배씩 늘린다.
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
