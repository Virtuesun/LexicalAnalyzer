package deyang.li.lexicalanalyzer.main;

import java.io.*;

/**
 * Created by lidey on 2017/4/17.
 */
public class FileHelper {
    public static String read(String filePath){
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();

            try {
                FileInputStream stream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(stream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(reader);

                String lineText = bufferedReader.readLine();
                while (lineText != null){
                    stringBuilder.append(lineText);
                    lineText = bufferedReader.readLine();
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
     return stringBuilder.toString().trim();
    }
}
