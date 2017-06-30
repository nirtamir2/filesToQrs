import java.io.*;

public class FileToBinary
{
    public byte[] getBinaryData(String path) throws IOException
    {
        RandomAccessFile f = new RandomAccessFile(new File(path), "r");
        byte[] b = new byte[(int)f.length()];
        f.readFully(b);
        return b;
    }
}
