import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


 //测试 Fraction（分数）类的功能，包括四则运算、约分、负数处理等。
public class FractionTest {
    private Fraction f1, f2, f3, f4;

    //初始化测试数据
    @Before
    public void setUp() {
        f1 = new Fraction(1, 2);  // 1/2
        f2 = new Fraction(1, 3);  // 1/3
        f3 = new Fraction(-2, 4); // -1/2 (约分)
        f4 = new Fraction(4, 8);  // 1/2 (约分)
    }

    //测试分数是否能够正确约分
    @Test
    public void testFractionReduction() {
        assertEquals("1/2", f4.toString()); // 4/8 应该被约分成 1/2
    }

    //测试负数分数的正确性
    @Test
    public void testNegativeFraction() {
        assertEquals("-1/2", f3.toString()); // -2/4 应该变成 -1/2
    }

    // 测试分数加法
    @Test
    public void testAddition() {
        assertEquals("5/6", f1.add(f2).toString()); // 1/2 + 1/3 = 5/6
    }

    //测试分数减法
    @Test
    public void testSubtraction() {
        assertEquals("1/6", f1.subtract(f2).toString()); // 1/2 - 1/3 = 1/6
    }

    //测试分数乘法
    @Test
    public void testMultiplication() {
        assertEquals("1/6", f1.multiply(f2).toString()); // 1/2 * 1/3 = 1/6
    }

    //测试分数除法
    @Test
    public void testDivision() {
        assertEquals("1'1/2", f1.divide(f2).toString()); // 1/2 ÷ 1/3 = 3/2
    }

    // 测试两个相等的分数是否被认为相等
    @Test
    public void testEquality() {
        assertEquals(new Fraction(1, 2), f4); // 1/2 应该等于 4/8
    }
}
