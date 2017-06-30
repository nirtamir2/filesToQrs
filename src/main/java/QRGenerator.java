import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class QRGenerator
{
    private String fileDirectory;
    private int size;
    private int fileNumber = 0;

    public QRGenerator(String fileDirectory, int size)
    {
        this.fileDirectory = fileDirectory;
        this.size = size;
    }


    public void generate(String text) throws IOException
    {
        fileNumber++;
        String filePath = getFilePath();
        File myFile = new File(filePath);
        if(!myFile.exists())
        {
            myFile.createNewFile();
        }
        try
        {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++)
            {
                for (int j = 0; j < CrunchifyWidth; j++)
                {
                    if (byteMatrix.get(i, j))
                    {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, "png", myFile);
        } catch (WriterException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        printSuccessMessage();
    }

    private void printSuccessMessage()
    {
        System.out.println("\n\nYou have successfully created QR Code in "+ getFileName());
    }

    private String getFilePath()
    {
        String fileName =  getFileName();
        return fileDirectory + fileName + ".png";
    }

    private String getFileName()
    {
        return "qr " + fileNumber;
    }
}

