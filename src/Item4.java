/** Item 4: Enforce noninstantiability with a private constructor
 *
 * When we wanna make a class only have static methods and static fields, we make a Utility class.
 * In java, java.lang.Math and java.util.Arrays are utility class grouping related methods
 * on primitive values or arrays.
 *
 * Such utility classes were not desinged to be instantiated.
 * In the absence of explicit constructor, however, the compiler provides a public, paremeterless
 * 'default constructor', so a class can be made noninstantiable by including a private constructor.
 * with comment or 'throw the AssertionError()' to avoid be accidentally invoked from within the class.
 *
 *  - side effects: theses classes can't be subclassed.
 * **/

// Noninstantiable utility class
public class Item4 {

    // Suppress default constructor for noninstantiability
    private Item4() {
        throw new AssertionError();
    }

    public static int method(int num) { return num+1; }

    public static double method2(double num) { return num+1; }

    // ...
}