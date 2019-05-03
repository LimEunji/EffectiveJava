import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Item5: Avoid creating unnecessary objects
 *
 * An object can always be reused if it is immutable.
 *
 * */
public class Item5 {
    String str1 = new String("stringette"); //Don't do this!
    String str2 = "stringette"; //같은 virtual machine에서 실행되는 다른 코드에서도 재사용될 수 있다!
}

//Don't implement like this!
class Item5_person1 {
    private final Date birthDate;

    Item5_person1(Date birthDate) {
        this.birthDate = birthDate;
    }

    //Don't do this! Many instances are created unnecessarily when it is invoked.
    public boolean isBabyBoomer() {
        //Unnecessary allocation of expensive object
        Calendar gmtCal =
                Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 &&
                birthDate.compareTo(boomEnd)   <  0;
    }
}

class Item5_person2 {
    private final Date birthDate;

    private static final Date BOOM_START;
    private static final Date BOOM_END;

    // static initializer
    static {
        Calendar gmtCal =
                Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }

    Item5_person2(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 &&
                birthDate.compareTo(BOOM_END)   <  0;
    }
}

class Item5_UsePrimitive {
    //Hideously slow program! Use primitive type rather than wrapper class
    public static void addAllPositiveInt() {
        Long sum = 0L; //Don't do this! Use long instead of Long because of the autoboxing and unboxing
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}