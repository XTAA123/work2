public class Expression {
    private final ExpressionNode root; // 表达式的根节点

    // 构造函数，初始化表达式
    public Expression(ExpressionNode root) {
        this.root = root;
    }

    // 计算表达式的值
    public Fraction evaluate() {
        return root.evaluate();
    }

    // 将表达式转换为字符串表示
    public String toString() {
        return root.toString();
    }
}