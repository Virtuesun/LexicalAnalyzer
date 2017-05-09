package deyang.li.lexicalanalyzer.test;

import deyang.li.lexicalanalyzer.entity.ConstEntity;
import deyang.li.lexicalanalyzer.error.Errors;
import deyang.li.lexicalanalyzer.error.IllegalDeclaration;
import deyang.li.lexicalanalyzer.main.FileHelper;
import deyang.li.lexicalanalyzer.main.LexicalAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lidey on 2017/4/17.
 */
public class TestLexicalAnalyzerFromFile {
    public static void main(String[] args){
        List<ConstEntity> resultList = new ArrayList<>();
        LexicalAnalyzer lex = new LexicalAnalyzer();

        //从文件读取const声明
        String inpurText = FileHelper.read("D:\\test.txt").trim();
        //用分号拆分语句
        String[] lines = inpurText.split(";");
        //将分号添加回原语句
        int len = lines.length;
        for (int i=0;i<len-1;++i){
            lines[i] = lines[i] + ";";
        }
        lines[len-1] = inpurText.endsWith(";") ? lines[len-1]+";" : lines[len-1];

        try {
            for (String line : lines) {
                List<ConstEntity> listConstants = lex.analyzer(line);
                for (ConstEntity entity:listConstants){
                    if (resultList.contains(entity)){
                        entity.setError(Errors.EXIST_NAME);
                    }
                    resultList.add(entity);
                }
            }
        }catch (IllegalDeclaration e){
            System.out.println(e.getMessage());
        }

        lex.display(resultList);

    }
}
