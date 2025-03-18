import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

public class CheckerTest {

    @Test
    public void testCheck() throws IOException {
        // 定义测试文件路径
        String exerciseFile = "exercisefile.txt";
        String answerFile = "answerfile.txt";

        // 调用 check 方法
        Checker.check(exerciseFile, answerFile);

        // 读取生成的 Grade.txt 文件并验证内容
        List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("Grade.txt"));
        assertFalse(lines.isEmpty(), "Grade.txt 文件不应为空");

        // 验证 Correct 和 Wrong 的数量是否正确
        String correctLine = lines.get(0);
        String wrongLine = lines.get(1);

        assertTrue(correctLine.startsWith("Correct: "), "第一行应包含 Correct 信息");
        assertTrue(wrongLine.startsWith("Wrong: "), "第二行应包含 Wrong 信息");

        // 打印结果以供检查
        System.out.println("Correct Line: " + correctLine);
        System.out.println("Wrong Line: " + wrongLine);
    }
}