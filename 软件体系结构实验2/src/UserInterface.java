import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class UserInterface extends JFrame {
    private JTextField inputFileField;
    private JComboBox<String> styleComboBox;
    private JTextArea resultTextArea;
    private JLabel descriptionLabel;

    public UserInterface() {
        setTitle("文件处理程序");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建输入文件名文本框和选择风格下拉框
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputFileField = new JTextField(40);
        inputPanel.add(new JLabel("请输入文件名："));
        inputPanel.add(inputFileField);

        styleComboBox = new JComboBox<>(new String[]{"主程序-子程序", "面向对象", "事件系统", "管道-过滤器"});
        inputPanel.add(new JLabel("请选择软件体系结构风格："));
        inputPanel.add(styleComboBox);

        // 创建处理结果文本区域和方法说明标签
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultTextArea = new JTextArea(20, 80);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        descriptionLabel = new JLabel();
        resultPanel.add(descriptionLabel, BorderLayout.SOUTH);

        // 创建处理按钮
        JButton processButton = new JButton("处理");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFile = inputFileField.getText();
                int selectedStyle = styleComboBox.getSelectedIndex() + 1;

                File input = new File(inputFile);
                File output = new File("D:\\output.txt");

                try {
                    String result = processInputFile(input, output, selectedStyle);
                    resultTextArea.setText(result);
                    setDescription(selectedStyle);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(UserInterface.this, "处理文件时出错：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 添加组件到窗口
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        add(processButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private String processInputFile(File inputFile, File outputFile, int selectedStyle) throws IOException {
        StringBuilder resultBuilder = new StringBuilder();

        switch (selectedStyle) {
            case 1:
                MainSubprogramStyle.process(inputFile, outputFile);
                resultBuilder.append("使用主程序-子程序风格处理完成！");
                break;
            case 2:
                ObjectOrientedStyle.process(inputFile, outputFile);
                resultBuilder.append("使用面向对象风格处理完成！");
                break;
            case 3:
                EventSystemStyle.process(inputFile, outputFile);
                resultBuilder.append("使用事件系统风格处理完成！");
                break;
            case 4:
                PipeFilterStyle.process(inputFile, outputFile);
                resultBuilder.append("使用管道-过滤器风格处理完成！");
                break;
            default:
                resultBuilder.append("无效的选择！");
                break;
        }

        // 读取输出文件内容并追加到结果中
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            resultBuilder.append("\n输出文件内容：\n");
            while ((line = reader.readLine()) != null) {
                resultBuilder.append(line).append("\n");
            }
        }

        return resultBuilder.toString();
    }

    private void setDescription(int selectedStyle) {
        switch (selectedStyle) {
            case 1:
                descriptionLabel.setText("方法说明：主程序-子程序风格将文件处理过程分解为多个子程序，依次调用它们来处理文件。");
                break;
            case 2:
                descriptionLabel.setText("方法说明：面向对象风格将文件处理过程封装在一个类中，使用面向对象的思想来处理文件。");
                break;
            case 3:
                descriptionLabel.setText("方法说明：事件系统风格使用事件机制来处理文件，通过触发事件来执行相应的处理操作。");
                break;
            case 4:
                descriptionLabel.setText("方法说明：管道-过滤器风格将文件处理过程分解为多个过滤器，通过管道连接它们来处理文件。");
                break;
            default:
                descriptionLabel.setText("");
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserInterface ui = new UserInterface();
                ui.setVisible(true);
            }
        });
    }
}