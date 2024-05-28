package application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class MessageListModel {
	  private List<String> messages;
	  private List<MessageObserver> observers;
	  private Object messageTextArea; // Optional reference to messageTextArea

	  public interface MessageObserver {
	    void update(String message);
	  }

	  public MessageListModel() {
	    messages = new ArrayList<>();
	    observers = new ArrayList<>();
	  }

	  public void addMessage(String message) {
	    messages.add(message);
	    notifyObservers(message);
	  }

	  public List<String> getMessages() {
	    return messages;
	  }

	  public void addObserver(MessageObserver observer) {
	    observers.add(observer);
	  }

	  public void removeObserver(MessageObserver observer) {
	    observers.remove(observer);
	  }

	  private void notifyObservers(String message) {
	    for (MessageObserver observer : observers) {
	      observer.update(message);
	    }
	  }

	  // Set a reference to the messageTextArea (optional)
	  public void setMessageTextArea(JTextArea messageTextArea) {
	    this.messageTextArea = messageTextArea;
	  }
	}
