public class MultiplyNode implements ExpressionNode {
    private final ExpressionNode left;
    private final ExpressionNode right;

    // 构造函数，初始化乘法节点
    public MultiplyNode(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }

    // 计算乘法节点的值
    public Fraction evaluate() {
        return left.evaluate().multiply(right.evaluate());
    }

    // 将乘法节点转换为字符串表示
    public String toString() {
        return wrap(left) + " × " + wrap(right);
    }

    // 如果子节点是加法或减法节点，添加括号
    private String wrap(ExpressionNode node) {
        return (node instanceof AddNode || node instanceof SubtractNode) ?
                "(" + node + ")" : node.toString();
    }
}