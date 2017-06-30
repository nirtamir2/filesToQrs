import java.io.IOException;
import java.util.List;

public class QrsFromFileCreator
{
    private String filepath;
    private String qrDirPath;
    private int qrSize;
    private int charsPerQr;

    public QrsFromFileCreator()
    {
    }

    public void start() throws IOException
    {
        String fileString = getFileString(filepath);
        createQrs(fileString);
        System.out.println(fileString);
    }

    private void createQrs(String fileString) throws IOException
    {
        List<String> fileBinaryStrings = StringSplitter.split(fileString, charsPerQr);
        createQrs(fileBinaryStrings);
    }

    private void createQrs(List<String> fileBinaryStrings) throws IOException
    {
        QRGenerator generator = new QRGenerator(qrDirPath, qrSize);
        for (String qrCodeString : fileBinaryStrings)
        {
            System.out.println(qrCodeString);
            generator.generate(qrCodeString);
        }
    }

    private String getFileString(String path) throws IOException
    {
        FileToBinary fileToBinary = new FileToBinary();
        return new String(fileToBinary.getBinaryData(path), "UTF-8");

    }

    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public void setQrDirPath(String qrDirPath)
    {
        this.qrDirPath = qrDirPath;
    }

    public void setQrSize(int qrSize)
    {
        this.qrSize = qrSize;
    }

    public void setCharsPerQr(int charsPerQr)
    {
        this.charsPerQr = charsPerQr;
    }
}
