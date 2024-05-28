package application;

import java.util.List;

import application.ParticipantListModel.Observer;

public interface ParticipantListModelInterface {
    void registerParticipant(String id, String pseudo, String password);
    boolean authenticateParticipant(String id, String password);
    List<Participant> getParticipants();
    void addObserver(RegisterView registerView);
    void removeObserver(Observer observer);
	void addObserver(ParticipantListView participantListView);
}