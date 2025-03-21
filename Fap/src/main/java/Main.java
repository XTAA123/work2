import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // 解析命令行参数
            Map<String, String> params = parseArgs(args);

            // 定义参数常量
            final String PARAM_COUNT = "-n";
            final String PARAM_RANGE = "-r";
            final String PARAM_EXERCISE_FILE = "-e";
            final String PARAM_ANSWER_FILE = "-a";

            // 生成练习题和答案
            if (params.containsKey(PARAM_COUNT) && params.containsKey(PARAM_RANGE)) {
                validatePositiveInteger(params.get(PARAM_COUNT), PARAM_COUNT);
                validatePositiveInteger(params.get(PARAM_RANGE), PARAM_RANGE);

                int count = Integer.parseInt(params.get(PARAM_COUNT));
                int range = Integer.parseInt(params.get(PARAM_RANGE));

                Generator generator = new Generator(range);
                List<Expression> exercises = new ArrayList<>();
                List<Fraction> answers = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    Expression expr = generator.generate();
                    exercises.add(expr);
                }

                // 批量计算答案
                for (Expression expr : exercises) {
                    answers.add(expr.evaluate());
                }

                writeExercises(exercises);
                writeAnswers(answers);
            }
            // 检查答案
            else if (params.containsKey(PARAM_EXERCISE_FILE) && params.containsKey(PARAM_ANSWER_FILE)) {
                Checker.check(params.get(PARAM_EXERCISE_FILE), params.get(PARAM_ANSWER_FILE));
            }
            // 参数无效
            else {
                throw new IllegalArgumentException("非法参数！请检查输入格式。");
            }
        } catch (NumberFormatException e) {
            System.err.println("参数值必须为整数！");
        } catch (IllegalArgumentException e) {
            System.err.println("参数错误: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("发生未知错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 验证参数是否为正整数
    private static void validatePositiveInteger(String value, String paramName) {
        if (value == null || !value.matches("\\d+") || Integer.parseInt(value) <= 0) {
            throw new IllegalArgumentException(paramName + " 必须为正整数！");
        }
    }

    // 解析命令行参数
    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-n":
                case "-r":
                case "-e":
                case "-a":
                    params.put(args[i], args[++i]);
                    break;
            }
        }
        return params;
    }

    // 将练习题写入文件
    private static void writeExercises(List<Expression> exercises) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Exercises.txt"))) {
            for (int i = 0; i < exercises.size(); i++) {
                writer.write((i+1) + ". " + exercises.get(i) + "\n");
            }
        }
    }

    // 将答案写入文件
    private static void writeAnswers(List<Fraction> answers) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Answers.txt"))) {
            for (int i = 0; i < answers.size(); i++) {
                writer.write((i+1) + ". " + answers.get(i) + "\n");
            }
        }
    }
}