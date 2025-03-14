public class NumberNode implements ExpressionNode {
    private final Fraction value; // 数值节点

    // 构造函数，初始化数值节点
    public NumberNode(Fraction value) {
        this.value = value;
    }

    // 返回数值节点的值
    public Fraction evaluate() {
        return value;
    }

    // 将数值节点转换为字符串表示
    public String toString() {
        return value.toString();
    }
}
