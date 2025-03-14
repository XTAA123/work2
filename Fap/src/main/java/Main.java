import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, String> params = parseArgs(args);
            // 如果参数包含 -n 和 -r，生成练习题和答案
            if (params.containsKey("-n") && params.containsKey("-r")) {
                int count = Integer.parseInt(params.get("-n"));
                int range = Integer.parseInt(params.get("-r"));
                Generator generator = new Generator(range);
                List<Expression> exercises = new ArrayList<>();
                List<Fraction> answers = new ArrayList<>();

                // 生成指定数量的练习题和答案
                for (int i = 0; i < count; i++) {
                    Expression expr = generator.generate();
                    exercises.add(expr);
                    answers.add(expr.evaluate());
                }

                // 将练习题和答案写入文件
                writeExercises(exercises);
                writeAnswers(answers);
            }
            // 如果参数包含 -e 和 -a，检查答案
            else if (params.containsKey("-e") && params.containsKey("-a")) {
                Checker.check(params.get("-e"), params.get("-a"));
            }
            // 参数无效，抛出异常
            else {
                throw new IllegalArgumentException("Invalid arguments");
            }
        } catch (Exception e) {
            e.printStackTrace();
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