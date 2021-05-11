import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;

public class MyFirstTest {

    @Test
    public void firstTest() {
        System.out.println("Hello, World!");

        String firstName = "Dmitro";
        String lastName = "Dmi";
        String email = "best@bestisc.lv";
        int age = 34;

        System.out.println("First name: " + firstName + "\nLast name: " + lastName); // obratniij n - perenos na novuju stroku.

        int commentCount = 36;
        int newComments = 22;

        String stringCommentCount = "36";
        String stringNewCount = "22";

        System.out.println(commentCount + newComments);
        System.out.println(stringCommentCount + stringNewCount);

        printSum("15", "3");
        printSum("268", "2");
    }

    private void printSum(String a, String b) {
        int first = Integer.parseInt(a);
        int second = Integer.parseInt(b);

        System.out.println(first + second);
        System.out.println("sum is: " + first + second);
        System.out.println("sum is: " + (first + second));
    }
}
