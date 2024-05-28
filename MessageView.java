package application;

import javax.swing.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;


import application.MessageListModel.MessageObserver;

import java.awt.*;
import java.util.Base64;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageView extends JFrame implements MessageObserver {

    private MessageListModel messageListModel;
    private JTextArea messageTextArea;
    private JTextField receiverTextField;
    private JTextArea messageContentTextArea;
    private JButton submitButton;
 // Generate a symmetric encryption key
    private SecretKey generateSymmetricKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Set the key size 128 
        return keyGenerator.generateKey();
    }

    // Encrypt the message content using a symmetric key
    private byte[] encryptMessageContent(String messageContent, SecretKey symmetricKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, symmetricKey);
        return cipher.doFinal(messageContent.getBytes());
    }

    // Decrypt the message content using a symmetric key
    private String decryptMessageContent(byte[] encryptedContent, SecretKey symmetricKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, symmetricKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedContent);
        return new String(decryptedBytes);
    }

    // Generate an asymmetric key pair
    private KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Set the key size
        return keyPairGenerator.generateKeyPair();
    }

    // Encrypt the symmetric key using the receiver's public key
    private byte[] encryptSymmetricKey(SecretKey symmetricKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(symmetricKey.getEncoded());
    }

    // Decrypt the symmetric key using the receiver's private key
    private SecretKey decryptSymmetricKey(byte[] encryptedKey, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedKey);
        return new SecretKeySpec(decryptedBytes, "AES");
    }
    
    
    
    
    public MessageView(MessageListModel messageListModel, int x, int y) {
    	
        this.messageListModel = messageListModel;
        this.messageListModel.addObserver(this);

        setTitle("Message View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 370);
        setLocation(x, y);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(192, 255, 209));

        messageTextArea = new JTextArea();
        messageTextArea.setFont(messageTextArea.getFont().deriveFont(Font.BOLD));
        messageTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.setBackground(new Color(230, 230, 230));

        inputPanel.add(new JLabel("Receiver:"));
        receiverTextField = new JTextField();
        inputPanel.add(receiverTextField);

        inputPanel.add(new JLabel("Message Content:"));
        messageContentTextArea = new JTextArea();
        JScrollPane messageContentScrollPane = new JScrollPane(messageContentTextArea);
        inputPanel.add(messageContentScrollPane);

        submitButton = new JButton("Submit");
        inputPanel.add(submitButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        setContentPane(panel);

        // Customize button color
        submitButton.setBackground(new Color(255, 192, 209));
        submitButton.setForeground(Color.WHITE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiver = receiverTextField.getText();
                String messageContent = messageContentTextArea.getText();

                try {
                    // Generate a symmetric key
                    SecretKey symmetricKey = generateSymmetricKey();

                    // Encrypt the message content
                    byte[] encryptedContent = encryptMessageContent(messageContent, symmetricKey);

                    // Generate an asymmetric key pair
                    KeyPair keyPair = generateKeyPair();

                    // Encrypt the symmetric key using the receiver's public key
                    byte[] encryptedKey = encryptSymmetricKey(symmetricKey, keyPair.getPublic());

                    // Create a new Message object with the encrypted content and key
                    Message message = new Message(keyPair.getPublic().toString(), receiver, encryptedContent, encryptedKey);

                    // Display the encrypted message information
                    MessageInformationView messageInformationView = new MessageInformationView();
                    messageInformationView.displayMessage(
                            Base64.getEncoder().encodeToString(encryptedContent),
                            Base64.getEncoder().encodeToString(encryptedKey));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void display() {
        setVisible(true);
    }

    @Override
    public void update(String message) {
        if (messageTextArea != null) {
            messageTextArea.append(message + "\n");
        } else {
            System.out.println("Warning: messageTextArea not set in MessageView!");
        }
    }
}
