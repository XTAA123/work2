import java.util.Random;

public class Generator {
    private final int maxValue;
    private final Random random = new Random();

    public Generator(int maxValue) {
        this.maxValue = maxValue;
    }

    public Expression generate() {
        return new Expression(buildNode(3));
    }

    private ExpressionNode buildNode(int depth) {
        if (depth == 0 || random.nextDouble() < 0.3) {
            return new NumberNode(generateFraction());
        }

        int type = random.nextInt(4);
        ExpressionNode left = buildNode(depth - 1);
        ExpressionNode right = buildNode(depth - 1);

        switch (type) {
            case 0: return new AddNode(left, right);
            case 1: return new SubtractNode(ensureLeftGreaterOrEqual(left, right), right);
            case 2: return new MultiplyNode(left, right);
            case 3: return new DivideNode(left, ensureDivisorValid(right));
            default: throw new IllegalStateException();
        }
    }

    private ExpressionNode ensureLeftGreaterOrEqual(ExpressionNode left, ExpressionNode right) {
        while (true) {
            Fraction leftValue = left.evaluate();
            Fraction rightValue = right.evaluate();
            if (leftValue.compareTo(rightValue) >= 0) return left;
            left = buildNode(1); // Generate a new left node
        }
    }

    private ExpressionNode ensureDivisorValid(ExpressionNode node) {
        while (true) {
            Fraction value = node.evaluate();
            if (!value.equals(new Fraction(0, 1)) && value.compareTo(new Fraction(1, 1)) > 0) return node;
            node = new NumberNode(generateFraction());
        }
    }

    private Fraction generateFraction() {
        int denominator = random.nextInt(maxValue - 1) + 1;
        int numerator = random.nextInt(maxValue * denominator) + 1;
        return new Fraction(numerator, denominator);
    }
}