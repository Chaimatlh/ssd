package application;

import javax.swing.*;
import java.awt.*;

public   class CourseInformationView extends JFrame {
    private JLabel courseLabel;
    private JLabel pathLabel;

    public CourseInformationView() {
        setTitle("Course Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.setBackground(new Color(255, 192, 209));

        courseLabel = new JLabel("Course: ");
        panel.add(new JLabel("Course: "));
        panel.add(courseLabel);

        pathLabel = new JLabel("Path: ");
        panel.add(new JLabel("Path: "));
        panel.add(pathLabel);

        setContentPane(panel);
    }

    public void displayCourseInformation(String course, String path) {
        courseLabel.setText(course);
        pathLabel.setText(path);
        setVisible(true);
    }
    public void displayMessageInformation(String submittedReceiver, String submittedMessage) {
        // Display the submitted receiver and message information
        // For example, you can show them in separate labels or concatenate them into a single string
        String message = "Receiver: " + submittedReceiver + "<br>" +
                         "Message: " + submittedMessage;

        // Create a new JLabel to display the message
        JLabel messageLabel = new JLabel("<html>" + message + "</html>");

        // Add the message label to the panel
        JPanel panel = (JPanel) getContentPane();
        panel.add(new JLabel("Message: "));
        panel.add(messageLabel);

        // Update the panel's layout
        panel.setLayout(new GridLayout(3, 2));

        // Refresh the view
        revalidate();
        repaint();

        // Make the frame visible
        setVisible(true);
    }

	public void displayCourseInformation(String courseId, String coursePath, Object courseContent) {
		// TODO Auto-generated method stub
		
	}
}