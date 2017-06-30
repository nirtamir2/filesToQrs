import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame
{
    private JTextField qrSizeTextField = new JTextField(4);
    private JTextField qrAdressTextField = new JTextField(100);
    private JTextField fileAdressTextField = new JTextField(100);
    private JTextField qrCharsPerQrTextField = new JTextField(4);

    public MainFrame() throws HeadlessException
    {
        super("QR CREATOR");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(createFileAdressPanel());
        add(createQRAdressPanel());
        add(createQRSizePanel());
        add(createCharsPerQrPanel());
        add(getStartButton());
        setSize(1500, 500);
        setVisible(true);
    }

    private JPanel createQRSizePanel()
    {
        JPanel p = new JPanel(new FlowLayout());
        p.add(qrSizeTextField);
        p.add(new JLabel("גודל QR"));
        return p;
    }

    private JPanel createCharsPerQrPanel()
    {
        JPanel p = new JPanel(new FlowLayout());
        p.add(qrCharsPerQrTextField);
        p.add(new JLabel("מספר תווים בQR"));
        return p;
    }

    private JButton getStartButton()
    {
        JButton startButton = new JButton("התחל");
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                generateQrsOfFile();
            }
        });

        return startButton;
    }

    private void generateQrsOfFile()
    {
        QrsFromFileCreator creator = createQrGenerator();
        try
        {
            creator.start();
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    private QrsFromFileCreator createQrGenerator()
    {
        QrsFromFileCreator creator = new QrsFromFileCreator();
        creator.setQrDirPath(gatorAddressText());
        creator.setFilepath(gemfireAddress());
        creator.setCharsPerQr(Integer.parseInt(qrCharsPerQr()));
        creator.setQrSize(Integer.parseInt(getQrSize()));
        return creator;
    }


    private JPanel createQRAdressPanel()
    {
        JPanel p = new JPanel(new FlowLayout());
        p.add(qrAdressTextField);
        p.add(new JLabel("כתובת תיקיית QRים"));
        return p;
    }

    private JPanel createFileAdressPanel()
    {
        JPanel p = new JPanel(new FlowLayout());
        p.add(fileAdressTextField);
        p.add(new JLabel("כתובת לקובץ"));
        return p;
    }

    private String getQrSize()
    {
        return qrSizeTextField.getText();
    }

    private String gatorAddressText()
    {
        return qrAdressTextField.getText();
    }

    private String gemfireAddress()
    {
        return fileAdressTextField.getText();
    }

    private String qrCharsPerQr()
    {
        return qrCharsPerQrTextField.getText();
    }


}
