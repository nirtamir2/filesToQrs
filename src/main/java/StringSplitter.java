import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringSplitter
{
    public static List<String> split(String original, int length) throws IOException
    {
        List<String> strings = new ArrayList<String>();
        ByteArrayInputStream bis = new ByteArrayInputStream(original.getBytes());
        int n = 0;
        byte[] buffer = new byte[length];
        while ((n = bis.read(buffer)) > 0) {
            String result = "";
            for (byte b : buffer) {
                result += (char) b;
            }
            Arrays.fill(buffer, (byte) 0);
            strings.add(result);
        }
        return strings;
    }
}
