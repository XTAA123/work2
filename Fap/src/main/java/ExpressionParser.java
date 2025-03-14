import java.text.ParseException;

public class ExpressionParser {
    // 解析表达式字符串
    public static Expression parse(String input) throws ParseException {
        return new Expression(new Parser(input).parse());
    }

    private static class Parser {
        private final String input;
        private int pos = 0;

        Parser(String input) {
            this.input = input.replaceAll(" ", "");// 去除空格
        }

        // 解析整个表达式
        ExpressionNode parse() throws ParseException {
            ExpressionNode node = parseExpression();
            if (pos < input.length()) {
                throw new ParseException("Unexpected character: " + input.charAt(pos), pos);
            }
            return node;
        }

        // 解析表达式（加减法）
        private ExpressionNode parseExpression() throws ParseException {
            ExpressionNode node = parseTerm();
            while (pos < input.length()) {
                char c = input.charAt(pos);
                if (c == '+' || c == '-') {
                    pos++;
                    ExpressionNode right = parseTerm();
                    node = c == '+' ? new AddNode(node, right) : new SubtractNode(node, right);
                } else {
                    break;
                }
            }
            return node;
        }

        // 解析项（乘除法）
        private ExpressionNode parseTerm() throws ParseException {
            ExpressionNode node = parseFactor();
            while (pos < input.length()) {
                char c = input.charAt(pos);
                if (c == '×' || c == '÷') {
                    pos++;
                    ExpressionNode right = parseFactor();
                    node = c == '×' ? new MultiplyNode(node, right) : new DivideNode(node, right);
                } else {
                    break;
                }
            }
            return node;
        }

        // 解析因子（括号或数值）
        private ExpressionNode parseFactor() throws ParseException {
            if (pos >= input.length()) {
                throw new ParseException("Unexpected end of input", pos);
            }
            if (input.charAt(pos) == '(') {
                pos++;
                ExpressionNode node = parseExpression();
                if (pos >= input.length() || input.charAt(pos++) != ')') {
                    throw new ParseException("Missing closing parenthesis", pos);
                }
                return node;
            }
            return parseNumber();
        }

        // 解析数值（整数或分数）
        private ExpressionNode parseNumber() throws ParseException {
            int start = pos;
            // 提取整数部分（如果有）
            StringBuilder number = new StringBuilder();
            while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                number.append(input.charAt(pos));
                pos++;
            }

            // 检查是否存在单引号（带分数）
            if (pos < input.length() && input.charAt(pos) == '\'') {
                pos++; // 跳过单引号
                // 提取分子部分
                StringBuilder numerator = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    numerator.append(input.charAt(pos));
                    pos++;
                }
                // 检查斜杠和分母
                if (pos >= input.length() || input.charAt(pos) != '/') {
                    throw new ParseException("Invalid mixed number format: missing '/'", pos);
                }
                pos++; // 跳过'/'
                StringBuilder denominator = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    denominator.append(input.charAt(pos));
                    pos++;
                }
                // 转换为假分数
                int whole = Integer.parseInt(number.toString());
                int num = Integer.parseInt(numerator.toString());
                int den = Integer.parseInt(denominator.toString());
                return new NumberNode(new Fraction(whole * den + num, den));
            } else if (pos < input.length() && input.charAt(pos) == '/') {
                // 处理纯分数（如3/4）
                pos++; // 跳过'/'
                StringBuilder denominator = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    denominator.append(input.charAt(pos));
                    pos++;
                }
                int num = number.length() > 0 ? Integer.parseInt(number.toString()) : 0;
                int den = Integer.parseInt(denominator.toString());
                return new NumberNode(new Fraction(num, den));
            } else {
                // 处理整数或单独的数字
                if (number.length() == 0) {
                    throw new ParseException("Invalid number", start);
                }
                return new NumberNode(new Fraction(Integer.parseInt(number.toString()), 1));
            }
        }
    }
}