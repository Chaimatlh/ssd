package application;
import java.util.ArrayList;
import java.util.List;

public class ParticipantListModel {
    private List<Participant> participantList;
    private List<Observer> observers;

    public interface Observer {
        void update();
    }

    public ParticipantListModel() {
        participantList = new ArrayList<>();
        observers = new ArrayList<>();
    }
    // this is the participant registre id pseudo and password 
    public void registerParticipant(String id, String pseudo, String password) {
        Participant participant = new Participant(id, pseudo, password);
        participantList.add(participant);
        notifyObservers();
    }

    public boolean authenticateParticipant(String id, String password) {
        for (Participant participant : participantList) {
            if (participant.getId().equals(id) && participant.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public List<Participant> getParticipants() {
        return participantList;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}