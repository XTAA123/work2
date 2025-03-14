import org.junit.Test;
import static org.junit.Assert.*;

//测试 Expression（表达式）计算是否正确
public class ExpressionTest {

    //测试简单的加法计算
    @Test
    public void testSimpleAddition() {
        Expression expr = new Expression(new AddNode(new NumberNode(new Fraction(1, 2)), new NumberNode(new Fraction(1, 3))));
        assertEquals("5/6", expr.evaluate().toString());
    }

    //测试简单的减法计算
    @Test
    public void testSimpleSubtraction() {
        Expression expr = new Expression(new SubtractNode(new NumberNode(new Fraction(3, 4)), new NumberNode(new Fraction(1, 4))));
        assertEquals("1/2", expr.evaluate().toString());
    }

    //测试乘法计算
    @Test
    public void testMultiplication() {
        Expression expr = new Expression(new MultiplyNode(new NumberNode(new Fraction(2, 3)), new NumberNode(new Fraction(3, 4))));
        assertEquals("1/2", expr.evaluate().toString());
    }

    //测试除法计算
    @Test
    public void testDivision() {
        Expression expr = new Expression(new DivideNode(new NumberNode(new Fraction(3, 4)), new NumberNode(new Fraction(2, 5))));
        assertEquals("1'7/8", expr.evaluate().toString());
    }

    //测试将加法节点转换为字符串
    @Test
    public void testToString1() {
        Expression expr = new Expression(new AddNode(new NumberNode(new Fraction(1, 2)), new NumberNode(new Fraction(1, 3))));
        assertEquals("1/2 + 1/3", expr.toString());
    }

    //测试将减法节点转换为字符串
    @Test
    public void testToString2() {
        Expression expr = new Expression(new SubtractNode(new NumberNode(new Fraction(1, 2)), new NumberNode(new Fraction(1, 3))));
        assertEquals("1/2 - 1/3", expr.toString());
    }

    //测试将乘法节点转换为字符串
    @Test
    public void testToString3() {
        Expression expr = new Expression(new MultiplyNode(new NumberNode(new Fraction(1, 2)), new NumberNode(new Fraction(1, 3))));
        assertEquals("1/2 × 1/3", expr.toString());
    }

    //测试将除法节点转换为字符串
    @Test
    public void testToString4() {
        Expression expr = new Expression(new DivideNode(new NumberNode(new Fraction(1, 2)), new NumberNode(new Fraction(1, 3))));
        assertEquals("1/2 ÷ 1/3", expr.toString());
    }
}
