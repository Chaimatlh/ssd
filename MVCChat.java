package application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MVCChat {
    public static void main(String[] args) {
        // Participant Management (example)
        ParticipantListModelInterface participantModel = (ParticipantListModelInterface) new ParticipantListModel();
        
        RegisterView registerView = new RegisterView((ParticipantListModelInterface) participantModel, 100, 100);
        ParticipantListView participantListView = new ParticipantListView((ParticipantListModelInterface) participantModel, 600, 100);

        // Message View
        MessageListModel messageListModel = new MessageListModel();
        MessageView messageView = new MessageView(messageListModel, 800, 100);

        // Course View
        CourseView courseView = new CourseView();

        // Display the CourseView
        courseView.display();

        // Add an example message to the MessageListModel
        messageListModel.addMessage("Hello guys hope you all doing well so , this is a sample message!");

        // Display the MessageView
        messageView.display();

        // Simulate getting updated course information (replace with your logic)
        String updatedCourseText = " This is some course information!";

        // Update CourseView with new information (if the updateCourseText method exists)
        if (courseView.getClass().getDeclaredMethods().length > 0) { // Check if method exists
            try {
                Method updateMethod = courseView.getClass().getMethod("updateCourseText", String.class);
                updateMethod.invoke(courseView, updatedCourseText);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                // Handle potential exceptions (method not found, etc.)
                System.out.println("Error updating CourseView: " + e.getMessage());
            }
        }
    }
}