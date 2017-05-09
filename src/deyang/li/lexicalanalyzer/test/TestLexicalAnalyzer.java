package deyang.li.lexicalanalyzer.test;

import java.util.List;
import java.util.Scanner;

import deyang.li.lexicalanalyzer.entity.ConstEntity;
import deyang.li.lexicalanalyzer.error.IllegalDeclaration;
import deyang.li.lexicalanalyzer.main.LexicalAnalyzer;

public class TestLexicalAnalyzer {

    public static void main(String[] args) {

        LexicalAnalyzer lex = new LexicalAnalyzer();
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("请输入常量说明串：");
            String declarationStr = input.nextLine();
            try {
                List<ConstEntity> list = lex.analyzer(declarationStr);
                System.out.println("处理结果：");
                lex.display(list);
                break;
            } catch (IllegalDeclaration e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        input.close();
    }

}
