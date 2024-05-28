package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseView extends JFrame {
    private JTextField courseIdTextField;
    private JTextField coursePathTextField;
    private JButton validateButton;
    private CourseInformationView courseInformationView;

    private boolean validateCourse(String courseId, String coursePath) {
        // Check if the course ID is not empty
        if (courseId.isEmpty()) {
            return false;
        }

        // Check if the course path is not empty
        if (coursePath.isEmpty()) {
            return false;
        }

        // Add additional validation logic as per your requirements

        // Return true if the course is valid
        return true;
    }

    public CourseView() {
        setTitle("Course Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 370);
        setLocation(300, 100);

        JPanel panel = new JPanel(new BorderLayout());

        courseIdTextField = new JTextField();
        coursePathTextField = new JTextField();
        validateButton = new JButton("Validate");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Course ID:"));
        inputPanel.add(courseIdTextField);
        inputPanel.add(new JLabel("Course Path:"));
        inputPanel.add(coursePathTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(validateButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);

        // Customize button color
        validateButton.setBackground(new Color(255, 192, 209));
        validateButton.setForeground(Color.WHITE);

        // Customize field size
        courseIdTextField.setPreferredSize(new Dimension(200, 25));
        coursePathTextField.setPreferredSize(new Dimension(200, 25));

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseId = courseIdTextField.getText();
                String coursePath = coursePathTextField.getText();

                boolean isValid = validateCourse(courseId, coursePath);

                if (isValid) {
                    // Create a new window to display course information
                    JFrame courseInfoFrame = new JFrame("Course Information");
                    courseInfoFrame.setSize(300, 200);
                    courseInfoFrame.setLocationRelativeTo(null);

                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

                    JLabel idLabel = new JLabel("Course ID: " + courseId);
                    JLabel pathLabel = new JLabel("Course Path: " + coursePath);

                    panel.add(idLabel);
                    panel.add(pathLabel);

                    courseInfoFrame.getContentPane().add(panel);
                    courseInfoFrame.setVisible(true);
                } else {
                    // Handle the case when the course is invalid
                }
            }
        });
    }

    public void setCourseInformationView(CourseInformationView courseInformationView) {
        this.courseInformationView = courseInformationView;
    }

    public void display() {
        setVisible(true);
    }

    public void updateCourse(String updatedCourseText) {
        // Update the course content
        courseInformationView.displayCourseInformation(courseIdTextField.getText(), coursePathTextField.getText());
    }}