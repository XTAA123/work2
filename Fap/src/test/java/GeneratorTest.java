import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratorTest {
    // 测试生成的表达式不超过最大值
    @Test
    public void testGeneratedExpressionWithinRange() {
        int maxValue = 10;
        Generator generator = new Generator(maxValue);
        Expression expr = generator.generate();
        Fraction result = expr.evaluate();
        String str = result.toString();

        // 解析带分数、假分数和整数三种情况
        int numerator;
        int denominator;

        if (str.contains("'")) { // 带分数格式，如 "2'1/2"
            String[] parts = str.split("'");
            int whole = Integer.parseInt(parts[0]);
            String[] fractionParts = parts[1].split("/");
            int remNumerator = Integer.parseInt(fractionParts[0]);
            denominator = Integer.parseInt(fractionParts[1]);
            numerator = whole * denominator + remNumerator;
        } else if (str.contains("/")) { // 假分数格式，如 "3/2"
            String[] fractionParts = str.split("/");
            numerator = Integer.parseInt(fractionParts[0]);
            denominator = Integer.parseInt(fractionParts[1]);
        } else { // 整数格式，如 "5"
            numerator = Integer.parseInt(str);
            denominator = 1;
        }

        int maxAllowed = maxValue * denominator;
        assertTrue("分子超过最大值" + maxAllowed + "，当前值为：" + numerator,
                numerator <= maxAllowed);
    }

    // 测试生成的表达式不为空
    @Test
    public void testGeneratedExpressionNotNull() {
        Generator generator = new Generator(10);
        Expression expr = generator.generate();
        assertNotNull(expr);
    }
}