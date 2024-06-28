import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ApkScanWin {
    private JFrame jFrame;
    private JTextField textField1;
    private JButton button1;
    private JTextArea textArea1;
    private JLabel label1;
    private JLabel label2;

    public ApkScanWin() {
        $$$setupUI$$$();
        this.button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ApkScanWin.this.fileChooser();
                super.mouseClicked(e);
            }
        });
        this.exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ApkScanWin.this.jFrame.dispose();
            }
        });
    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        // Initialize buttons
        button1 = new JButton("Select File");
        exitButton = new JButton("Exit");

        // Initialize text fields
        textField1 = new JTextField(20);
        textField2 = new JTextField(20);

        // Initialize text area
        textArea1 = new JTextArea(10, 30);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        // Initialize labels
        label1 = new JLabel("放置apk文件/[Put apk here]");
        label2 = new JLabel("检测结果/[Detected result]");

        // Create a panel for buttons and text fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 1));
        inputPanel.add(label1);
        inputPanel.add(textField1);
        inputPanel.add(label2);
        inputPanel.add(textField2);
        inputPanel.add(button1);

        // Add components to main panel
        panel1.add(inputPanel, BorderLayout.CENTER);
        panel1.add(new JScrollPane(textArea1), BorderLayout.NORTH);
        panel1.add(exitButton, BorderLayout.SOUTH);
    }

    private JTextField textField2;
    private JButton exitButton;
    private JPanel panel1;
    private Map<String, String> markNameMap;

    public void init() {
        this.jFrame = new JFrame("Awesome APKScan PKID");

        // Create and set FileTransferHandler
        FileTransferHandler fileTransferHandler = new FileTransferHandler(this);
        this.panel1.setTransferHandler(fileTransferHandler);
        this.textField1.setTransferHandler(fileTransferHandler);
        this.textField2.setTransferHandler(fileTransferHandler);
        this.button1.setTransferHandler(fileTransferHandler);
        this.textArea1.setTransferHandler(fileTransferHandler);
        this.exitButton.setTransferHandler(fileTransferHandler);

        StringBuilder sb = new StringBuilder();
        Set<String> seenValues = new HashSet<>();
        for (Map.Entry<String, String> entry : markNameMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!seenValues.contains(value)) {
                seenValues.add(value);
                sb.append(value).append(" ");
            }
        }
        System.out.println(sb.toString());
        textArea1.setText("Designed by Bin4xin https://github.com/bin4xin/\n" +
                "Source from legend https://www.legendsec.org/\nEnjoy ;)\n" +
                "已知壳\n" + sb.toString());
        // Set content pane and default close operation
        this.jFrame.setContentPane(this.panel1);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set initial size and make the frame resizable
        this.jFrame.setSize(1440, 1080);
        this.jFrame.setResizable(true);

        // Center the frame on the screen
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int) (toolkit.getScreenSize().getWidth() - this.jFrame.getWidth()) / 2;
        int y = (int) (toolkit.getScreenSize().getHeight() - this.jFrame.getHeight()) / 2;
        this.jFrame.setLocation(x, y);
        this.jFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustFontSize();
            }
        });
        // Make the frame visible
        this.jFrame.setVisible(true);
        adjustFontSize();
    }

    public void adjustFontSize() {
        int frameWidth = jFrame.getWidth();
        int frameHeight = jFrame.getHeight();

        // Calculate a base font size based on frame size
        int fontSize = Math.min(frameWidth, frameHeight) / 25;

        Font font = new Font("等距更纱黑体 SC", Font.PLAIN, fontSize);

        button1.setFont(font);
        exitButton.setFont(font);
        textField1.setFont(font);
        textField2.setFont(font);
        textArea1.setFont(font);
        label1.setFont(font);
        label2.setFont(font);
    }

    public void fileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.apk", "apk", "apk", "apk");


        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(new JPanel());

        if (returnVal == 0) {

            String absolutePath = chooser.getSelectedFile().getAbsolutePath();
            this.textField1.setText(absolutePath);

            if (absolutePath.endsWith(".apk")) {

                List<String> result = MyUtil.readZipFile(absolutePath);

                if (result.size() == 1 && ((String) result.get(0)).startsWith("ERROR:")) {
                    this.textArea1.setText(result.get(0));
                } else {

                    boolean flag = true;

                    for (String fileName : result) {
                        if (this.markNameMap.containsKey(fileName)) {

                            this.textField2.setText("《" + (String) this.markNameMap.get(fileName) + "》加固");
                            flag = false;

                            break;
                        }
                    }
                    if (flag) {
                        this.textField2.setText(" 此apk未采用加固或为未知加固厂商！");
                    }
                }
            }
        }
    }


    public JTextField getTextField1() {
        return this.textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getButton1() {
        return this.button1;
    }

    public void setButton1(JButton button1) {
        this.button1 = button1;
    }

    public JTextArea getTextArea1() {
        return this.textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JTextField getTextField2() {
        return this.textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JButton getExitButton() {
        return this.exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JPanel getPanel1() {
        return this.panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public Map<String, String> getMarkNameMap() {
        return this.markNameMap;
    }

    public void setMarkNameMap(Map<String, String> markNameMap) {
        this.markNameMap = markNameMap;
    }
}






