import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;

//测试 ExpressionParser（表达式解析器）是否能够正确解析数学表达式
public class ExpressionParserTest {

    //测试整数解析
    @Test
    public void testParseInteger() throws ParseException {
        Expression expr = ExpressionParser.parse("5");
        assertEquals("5", expr.evaluate().toString());
    }

    //测试分数解析
    @Test
    public void testParseFraction() throws ParseException {
        Expression expr = ExpressionParser.parse("3/4");
        assertEquals("3/4", expr.evaluate().toString());
    }

    //测试带分数解析
    @Test
    public void testParseMixedFraction() throws ParseException {
        Expression expr = ExpressionParser.parse("1'1/2");
        assertEquals("1'1/2", expr.evaluate().toString());
    }
}
