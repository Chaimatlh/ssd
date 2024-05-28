package application;
import javax.swing.*;
import java.awt.*;

public class MessageInformationView extends JFrame {
    private JTextArea messageTextArea;

    public MessageInformationView() {
        setTitle("Message Information View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 370);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(192, 255, 209));

        messageTextArea = new JTextArea();
        messageTextArea.setFont(messageTextArea.getFont().deriveFont(Font.BOLD));
        messageTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panel);
    }

    public void displayMessage(String encryptedContent, String encryptedKey) {
        messageTextArea.setText("Encrypted Content:\n" + encryptedContent
                + "\n\nEncrypted Key:\n" + encryptedKey);
        setVisible(true);
    }

}
