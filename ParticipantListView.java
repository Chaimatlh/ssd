package application;

import javax.swing.*;

import application.ParticipantListModel.Observer;

import java.awt.*;
import java.util.List;

public  class ParticipantListView extends JFrame implements ParticipantListModelInterface{
    private ProxyParticipantListModel participantModel;
    private JTextArea participantTextArea;

    public ParticipantListView(ParticipantListModelInterface participantModel, int h, int v) {
        this.participantModel = new ProxyParticipantListModel(participantModel);
        this.participantModel.removeObserver(this);

        setTitle("Participant List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 370);
        setLocation(h, v);

        JPanel panel = new JPanel(new BorderLayout());

        participantTextArea = new JTextArea();
        participantTextArea.setFont(participantTextArea.getFont().deriveFont(Font.BOLD));
        participantTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(participantTextArea);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBackground(new Color(255, 192, 209)); 
        
        setContentPane(panel);
        setVisible(true);
    }

    public void update() {
        List<Participant> participants = participantModel.getParticipants();
        StringBuilder sb = new StringBuilder();

        for (Participant participant : participants) {
            sb.append("ID: ").append(participant.getId()).append("\n");
            sb.append("Pseudo: ").append(participant.getPseudo()).append("\n");
            sb.append("Password: ").append(participant.getPassword()).append("\n\n");
        }

        participantTextArea.setText(sb.toString());
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