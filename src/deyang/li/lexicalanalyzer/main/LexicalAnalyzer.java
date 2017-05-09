package deyang.li.lexicalanalyzer.main;

import deyang.li.lexicalanalyzer.entity.ConstEntity;
import deyang.li.lexicalanalyzer.error.Errors;
import deyang.li.lexicalanalyzer.error.IllegalDeclaration;
import deyang.li.lexicalanalyzer.entity.Type;
import deyang.li.lexicalanalyzer.error.Exceptions;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

    /**
     * 常量声明串解析函数
     *
     * @param str 常量声明串
     * @return 常量声明集合
     */
    public List<ConstEntity> analyzer(String str) throws IllegalDeclaration {
        List<ConstEntity> listConstants = new ArrayList<ConstEntity>();
        str = str.trim();
        // 不是常量声明字符串不必做处理
        if (!str.startsWith("const ")) {
            throw new IllegalDeclaration(Exceptions.NOT_CONSTANT_STATEMENT,str);
        }
        //常量说明串需要以";"结束
        if (!str.endsWith(";")) {
            throw new IllegalDeclaration(Exceptions.INCORRECT_ENDS,str);
        }

        // 去除"const"和";"
        String newStr = str.substring(5, str.length() - 1).trim();
        // 按","拆分声明
        String[] singleDeclarations = newStr.split(",");

        // 处理每一个单独声明
        for (String singleDeclaration : singleDeclarations) {
            // 提取常量名和常量值
            String nameStr = "";
            String valueStr = "";
            try {
                nameStr = singleDeclaration.substring(0, singleDeclaration.indexOf("=")).trim();
                valueStr = singleDeclaration.substring(singleDeclaration.indexOf("=") + 1).trim();
            } catch (IndexOutOfBoundsException iobe) {
                // 单独声明不含有等号，无效的声明
                throw new IllegalDeclaration(Exceptions.NO_EQUALS, singleDeclaration);
            }

            if ("".equals(nameStr)) {
                throw new IllegalDeclaration(Exceptions.NO_NAME, singleDeclaration);
            }

            // 封装每个单独的声明并加入结果集
            ConstEntity entity = process(nameStr, valueStr);
            if (listConstants.contains(entity)){
                entity.setError(Errors.EXIST_NAME);
            }
            listConstants.add(entity);
        }

        return listConstants;
    }

    /**
     * 处理每个单独的声明
     *
     * @param nameStr  常量名串
     * @param valueStr 常量值串
     * @return 一个常量的封装
     */
    private ConstEntity process(String nameStr, String valueStr) {
        ConstEntity entity = new ConstEntity();

        // 设置常量名,常量值
        entity.setName(nameStr);
        entity.setValue(valueStr);
        // 判断常量名是否合法
        if (!nameStr.matches("^[A-Za-z][A-Za-z0-9_]*$")) {
            entity.setError(Errors.NOT_IDENTIFIER);
        }
        else if ("".equals(valueStr)) {
            // 判断常量是否有值
            entity.setError(Errors.NO_VALUE);
        } else if (valueStr.startsWith("'")) {
            // 判断是否为字符
            if (valueStr.length() > 3) {
                entity.setError(Errors.EXTRA_CHARACTER);
            } else if (valueStr.length() < 2 || !valueStr.endsWith("'")) {
                entity.setError(Errors.INCORRECT_CHAR_ENDS);
            } else {
                entity.setType(Type.CHAR);
            }
        } else if (valueStr.startsWith("\"")) {
            // 判断是否为字符串
            if (valueStr.length() < 2 || !valueStr.endsWith("\"")) {
                entity.setError(Errors.INCORRECT_STRING_ENDS);
            } else {
                entity.setType(Type.STRING);
            }
        } else if (valueStr.matches("^[+|-]?\\d+$")) {
            // 判断是否为数值
            if (valueStr.startsWith("+") || valueStr.startsWith("-")) {
                if (valueStr.charAt(1) == '0' && valueStr.length() > 2) {
                    entity.setError(Errors.INCORRECT_INTEGER_ZERO_START);
                } else {
                    entity.setType(Type.INTEGER);
                }
            } else if (valueStr.startsWith("0") && valueStr.length() > 1) {
                entity.setError(Errors.INCORRECT_INTEGER_ZERO_START);
            } else {
                entity.setType(Type.INTEGER);
            }
        } else if (valueStr.matches("^[+|-]?\\d+.\\d+$")) {
            if (valueStr.startsWith("+") || valueStr.startsWith("-")) {
                if (valueStr.charAt(1) == '0' && valueStr.substring(0, valueStr.indexOf('.')).length() > 2) {
                    entity.setError(Errors.INCORRECT_FLOAT_ZERO_START);
                } else {
                    entity.setType(Type.FLOAT);
                }
            } else if (valueStr.startsWith("0") && valueStr.substring(0, valueStr.indexOf('.')).length() > 1) {
                entity.setError(Errors.INCORRECT_FLOAT_ZERO_START);
            } else {
                entity.setType(Type.FLOAT);
            }
        } else if ("true".equals(valueStr) || "false".equals(valueStr)) {
            entity.setType(Type.BOOLEAN);
        } else {
            entity.setError(Errors.NOT_ANY_TYPE);
        }

        return entity;
    }

    /**
     * 打印声明列表内容
     *
     * @param listConstants 需要打印的列表
     */
    public void display(List<ConstEntity> listConstants) {
        int int_num = 0;
        int char_num = 0;
        int string_num = 0;
        int float_num = 0;
        int bool_num = 0;
        for (ConstEntity entity : listConstants) {
            System.out.println(entity);
            if (entity.getError() != null) {
                continue;
            }
            switch (entity.getType()) {
                case INTEGER:
                    int_num += 1;
                    break;
                case CHAR:
                    char_num += 1;
                    break;
                case STRING:
                    string_num += 1;
                    break;
                case FLOAT:
                    float_num += 1;
                    break;
                case BOOLEAN:
                    bool_num += 1;
                default:
                    break;
            }
        }

        System.out.println();
        System.out.println("int_num=" + int_num + "; char_num=" + char_num + "; string_num=" + string_num
                + "; float_num=" + float_num + "; bool_num=" + bool_num);
    }
}
