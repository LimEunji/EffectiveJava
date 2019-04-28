/*Enforce the singleton property with a private constructor or an enum type

Before 1.5, there is two ways implementing singletons.
these two ways have a caveat related with 'Reflection'
An privileged client can invoke the private constructor 'Reflectively'*/

// first approach: public static field approach
public class Item3 {
    public static final Item3 INSTANCE = new Item3();
    private Item3() {}

    public void method() {}
}

// second approach: static factory method
// - advantage: You can change your mind
//              whether the class should be a singleton without changing API
class Item3_2 {
    private static final Item3_2 INSTANCE = new Item3_2();
    private Item3_2() {}
    public Item3_2 getInstance() { return INSTANCE; }
    public void method() {}
}

// After 1.5 third approach to implementing singletons
// A single-element enum type
// - advantage1: concise
// - advantage2: serialization machinery for free
// - advantage3: ironclad guarantee against multiple instantiation,
//               even in the face of sophisticated serialization and reflection attack
enum Item3_3 {
    INSTANCE;

    //private Item3_3() {}

    public void method() {
        System.out.println("Item3_3");
    }
}
