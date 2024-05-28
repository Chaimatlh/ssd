package application;

import javax.swing.*;

import application.ParticipantListModel.Observer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public  class RegisterView extends JFrame implements ParticipantListModelInterface {
    private ParticipantListModelInterface participantModel;
    
   
    private JTextField idField;
    private JTextField pseudoField;
    private JPasswordField passwordField;
    // Add a new field to store your phone number
    
    
    private boolean isConfirmationStep;
    private String verificationCode;

    public RegisterView(ParticipantListModelInterface participantModel, int h, int v) {
        this.participantModel = new ProxyParticipantListModel(participantModel);
        this.participantModel.addObserver(this);
     // Store the phone number
        
        setTitle("Registration of New User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 370);
        setLocation(h, v);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(idLabel.getFont().deriveFont(Font.BOLD));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);

        idField = new JTextField();
        JLabel pseudoLabel = new JLabel("Pseudo:");
        pseudoLabel.setFont(pseudoLabel.getFont().deriveFont(Font.BOLD));
        pseudoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pseudoField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.BOLD));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(Color.LIGHT_GRAY);
        registerButton.setFont(registerButton.getFont().deriveFont(Font.BOLD));

        panel.setBackground(new Color(255, 192, 209));

        // Add a confirmation step
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.LIGHT_GRAY);
        confirmButton.setFont(confirmButton.getFont().deriveFont(Font.BOLD));
        confirmButton.setVisible(false);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String pseudo = pseudoField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (!isConfirmationStep && isValidInput(id, pseudo, password)) {
                    // Generate a random verification code
                    verificationCode = generateVerificationCode();

                    // Send verification code via email or SMS
                    if (sendVerificationCode(id, verificationCode)) {
                        isConfirmationStep = true;
                        idField.setEnabled(false);
                        pseudoField.setEnabled(false);
                        passwordField.setEnabled(false);
                        registerButton.setEnabled(false);
                        confirmButton.setVisible(true);
                        JOptionPane.showMessageDialog(RegisterView.this, "Verification code sent. Please check your email or SMS.");
                    } else {
                        JOptionPane.showMessageDialog(RegisterView.this, "Failed to send verification code. Please try again.");
                    }
                } else if (isConfirmationStep && isValidConfirmationCode()) {
                    participantModel.registerParticipant(id, pseudo, password);
                    clearFields();
                    isConfirmationStep = false;
                    idField.setEnabled(true);
                    pseudoField.setEnabled(true);
                    passwordField.setEnabled(true);
                    registerButton.setEnabled(true);
                    confirmButton.setVisible(false);
                    JOptionPane.showMessageDialog(RegisterView.this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(RegisterView.this, "Invalid input. Please try again.");
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidConfirmationCode()) {
                    participantModel.registerParticipant(idField.getText().trim(), pseudoField.getText().trim(),
                            new String(passwordField.getPassword()).trim());
                    clearFields();
                    isConfirmationStep = false;
                    idField.setEnabled(true);
                    pseudoField.setEnabled(true);
                    passwordField.setEnabled(true);
                    registerButton.setEnabled(true);
                    confirmButton.setVisible(false);
                    JOptionPane.showMessageDialog(RegisterView.this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(RegisterView.this, "Invalid verification code. Please try again.");
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(pseudoLabel);
        panel.add(pseudoField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(confirmButton);

        setContentPane(panel);
        setVisible(true);
    }

    public void update() {
        // Update view if needed
    }

    private void clearFields() {
        idField.setText("");
        pseudoField.setText("");
        passwordField.setText("");
    }

    private boolean isValidInput(String id, String pseudo, String password) {
        // Check if the input contains special characters or potentially harmful patterns
        String specialChars = "[!@#$%^&*()',.?\":{}|<>]";
        String harmfulPatterns = "(?i)(insert|update|delete|drop|alter|create)";

        if (id.matches(".*" + specialChars + ".*") || pseudo.matches(".*" + specialChars + ".*") || password.matches(".*" + specialChars + ".*")) {
            return false;
        }

        if (id.matches(".*" + harmfulPatterns + ".*") || pseudo.matches(".*" + harmfulPatterns + ".*") || password.matches(".*" + harmfulPatterns + ".*")) {
            return false;
        }

        // Additional input validation if needed
        return true;
    }

    private String generateVerificationCode() {
        // Generate a random verification code
        // Implement your code generation logic here
        return "123456"; // Replace with your code generation logic
    }

    private boolean isValidConfirmationCode() {
        // Validate the entered confirmation code
        String enteredCode = JOptionPane.showInputDialog(RegisterView.this, "Enter verification code:");
        return enteredCode != null && enteredCode.equals(verificationCode);
    }

    private boolean sendVerificationCode(String id, String code) {
        // Implement your code to send the verification code via email or SMS
        // Return true if the code is successfully sent, false otherwise
        return true; // Replace with your code to send the verification code
    }

	@Override
	public void registerParticipant(String id, String pseudo, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean authenticateParticipant(String id, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Participant> getParticipants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addObserver(RegisterView registerView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addObserver(ParticipantListView participantListView) {
		// TODO Auto-generated method stub
		
	}
}