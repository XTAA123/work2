public class SubtractNode implements ExpressionNode {
    private final ExpressionNode left;
    private final ExpressionNode right;

    // 构造函数，初始化减法节点
    public SubtractNode(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }

    // 计算减法节点的值
    public Fraction evaluate() {
        return left.evaluate().subtract(right.evaluate());
    }

    // 将减法节点转换为字符串表示
    public String toString() {
        return left + " - " + right;
    }
}