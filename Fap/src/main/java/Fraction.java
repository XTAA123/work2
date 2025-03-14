public class Fraction {
    private final int numerator; // 分子
    private final int denominator; // 分母

    // 构造函数，初始化分数并约分
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException("Denominator cannot be zero");
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        int gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    // 分数加法
    public Fraction add(Fraction other) {
        int newNum = numerator * other.denominator + other.numerator * denominator;
        int newDen = denominator * other.denominator;
        return new Fraction(newNum, newDen);
    }

    // 分数减法
    public Fraction subtract(Fraction other) {
        return add(new Fraction(-other.numerator, other.denominator));
    }

    public int compareTo(Fraction other) {
        int thisNum = this.numerator * other.denominator;
        int otherNum = other.numerator * this.denominator;
        return Integer.compare(thisNum, otherNum);
    }

    // 分数乘法
    public Fraction multiply(Fraction other) {
        return new Fraction(numerator * other.numerator, denominator * other.denominator);
    }

    // 分数除法
    public Fraction divide(Fraction other) {
        return multiply(new Fraction(other.denominator, other.numerator));
    }

    // 判断两个分数是否相等
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Fraction)) return false;
        Fraction other = (Fraction) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }

    // 将分数转换为字符串表示
    public String toString() {
        if (denominator == 1) return String.valueOf(numerator);
        int whole = numerator / denominator;
        int remainder = Math.abs(numerator % denominator);
        if (whole != 0) {
            return remainder == 0 ?
                    String.valueOf(whole) :
                    String.format("%d'%d/%d", whole, remainder, denominator);
        }
        return String.format("%d/%d", numerator, denominator);
    }

    // 计算最大公约数
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}