import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Checker {
    // 检查答案
    public static void check(String exerciseFile, String answerFile) throws IOException {
        List<Fraction> correctAnswers = parseExercises(exerciseFile);
        List<Fraction> userAnswers = parseAnswers(answerFile);

        if (correctAnswers.size() != userAnswers.size()) {
            throw new IllegalStateException("题目数量与答案数量不匹配！");
        }

        List<Integer> correct = new ArrayList<>();
        List<Integer> wrong = new ArrayList<>();

        for (int i = 0; i < correctAnswers.size(); i++) {
            if (userAnswers.get(i).equals(correctAnswers.get(i))) {
                correct.add(i + 1);
            } else {
                wrong.add(i + 1);
            }
        }
        writeResult(correct, wrong);
    }


    // 解析练习题文件并计算正确答案
    private static List<Fraction> parseExercises(String filename) throws IOException {
        List<Fraction> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Expression expr;
                try {
                    expr = ExpressionParser.parse(line.split("\\. ")[1]);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                answers.add(expr.evaluate());
            }
        }
        return answers;
    }

    // 解析用户答案文件
    private static List<Fraction> parseAnswers(String filename) throws IOException {
        List<Fraction> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\. ", 2);
                if (parts.length < 2) continue;
                answers.add(parseFraction(parts[1].trim())); // 解析用户答案
            }
        }
        return answers;
    }


    // 解析分数字符串
    public static Fraction parseFraction(String s) {
        s = s.trim();
        if (s.contains("'")) { // 处理带整数部分的分数
            String[] parts = s.split("['/]"); // 分割整数部分、分子和分母
            if (parts.length != 3) throw new IllegalArgumentException("非法分数格式: " + s);
            int whole = Integer.parseInt(parts[0]);
            int num = Integer.parseInt(parts[1]);
            int den = Integer.parseInt(parts[2]);
            return new Fraction(whole * den + num, den);
        } else if (s.contains("/")) { // 处理纯分数
            String[] parts = s.split("/");
            if (parts.length != 2) throw new IllegalArgumentException("非法分数格式: " + s);
            return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else { // 处理整数
            return new Fraction(Integer.parseInt(s), 1);
        }
    }


    // 将检查结果写入文件
    private static void writeResult(List<Integer> correct, List<Integer> wrong) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Grade.txt"))) {
            writer.write("Correct: " + correct.size() + " (");
            writer.write(String.join(", ", correct.stream().map(Object::toString).toArray(String[]::new)));
            writer.write(")\nWrong: " + wrong.size() + " (");
            writer.write(String.join(", ", wrong.stream().map(Object::toString).toArray(String[]::new)));
            writer.write(")");
        }
    }
}
