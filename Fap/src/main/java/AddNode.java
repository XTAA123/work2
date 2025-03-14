public class AddNode implements ExpressionNode {
    private final ExpressionNode left; // 左子节点
    private final ExpressionNode right; // 右子节点

    // 构造函数，初始化加法节点
    public AddNode(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }

    // 计算加法节点的值
    public Fraction evaluate() {
        return left.evaluate().add(right.evaluate());
    }

    // 将加法节点转换为字符串表示
    public String toString() {
        return left + " + " + right;
    }
}
