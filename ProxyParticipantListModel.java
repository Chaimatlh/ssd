package application;

import java.util.List;

import application.ParticipantListModel.Observer;

public  class ProxyParticipantListModel implements ParticipantListModelInterface {
    private ParticipantListModelInterface realModel;

    public ProxyParticipantListModel(ParticipantListModelInterface realModel) {
        this.realModel = realModel;
    }

    @Override
    public void registerParticipant(String id, String pseudo, String password) {
        // Add additional checks or functionality here
        if (isValidParticipant(id, pseudo, password)) {
            realModel.registerParticipant(id, pseudo, password);
        } else {
            // Handle invalid participant registration
            System.out.println("Invalid participant registration: " + id + ", " + pseudo);
        }
    }

    @Override
    public boolean authenticateParticipant(String id, String password) {
        // Add additional checks or functionality here
        return realModel.authenticateParticipant(id, password);
    }

    @Override
    public List<Participant> getParticipants() {
        return realModel.getParticipants();
    }

    public void addObserver(Observer observer) {
        realModel.removeObserver(observer);
    }

    public void removeObserver(ParticipantListView participantListView) {
        realModel.addObserver(participantListView);
    }

    private boolean isValidParticipant(String id, String pseudo, String password) {
        // Add your own validation logic here
        return !id.isEmpty() && !pseudo.isEmpty() && !password.isEmpty();
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